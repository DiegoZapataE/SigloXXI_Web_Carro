/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Reserva;
import Modelo.ReservaDAO;
import Modelo.Cliente;
import Modelo.ClienteDAO;
import Modelo.EmailDAO;
import Modelo.HorarioReserva;
import Modelo.HorarioReservaDAO;
import java.io.IOException;
import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Felipe Fuentes
 */
public class Resv extends HttpServlet {

    ReservaDAO resDAO=new ReservaDAO();
    Reserva r=new Reserva();
    Cliente c=new Cliente();
    ClienteDAO cDAO = new ClienteDAO();
    EmailDAO email = new EmailDAO();
     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String accion=request.getParameter("accion");
        HttpSession session = request.getSession(true); // reusar
        switch (accion){
            case "Reservar":
                request.getRequestDispatcher("reservar.jsp").forward(request, response);
                break;
            case "ListarReservas":
                //Buscar al cliente logeado
                String emailC=String.valueOf(session.getAttribute("email"));
                ClienteDAO clDAO = new ClienteDAO();
                int rutSes = clDAO.traerCliente(emailC).getRut_cliente();
                
                //Listar las reservas del cliente
                List<Reserva>datos=resDAO.listarReservas(rutSes);
                System.out.println(Arrays.toString(datos.toArray()));
                request.setAttribute("datos", datos);
                request.getRequestDispatcher("panelCliente.jsp").forward(request, response);
                resDAO.listarReservas(rutSes);
                break;
            case "Volver":
                request.getRequestDispatcher("Resv?accion=ListarReservas").forward(request, response);
                break;
            case "Salir":
                //Cerrar sesión
                session.removeAttribute("email");
                response.sendRedirect(request.getContextPath() + "/index.jsp");
                break;
            case "ModificarVentana":
                request.getRequestDispatcher("modificarCliente.jsp").forward(request, response);
            case "MenuComprar":
                request.getRequestDispatcher("menu_comprar.jsp").forward(request, response);
            case "PanelCliente":
                request.getRequestDispatcher("Resv?accion=ListarReservas").forward(request, response);
            case "MenuCarro":
                request.getRequestDispatcher("menu_carro.jsp").forward(request, response);
            case "Inicio":
                request.getRequestDispatcher("Inicio.jsp").forward(request, response);
            case "Cancelar":
                HorarioReservaDAO hrDAO = new HorarioReservaDAO();
                //Recuperar datos
                int id=Integer.parseInt(request.getParameter("id"));
                String emailBuscar = String.valueOf(session.getAttribute("email"));
                Date fechaActual = new Date(System.currentTimeMillis());
                Date fechaReservaExistente = resDAO.traerReserva(id).getFecha_reserva();
                int diferenciaDias = (int)((fechaReservaExistente.getTime() - fechaActual.getTime()) / (1000 * 60 * 60 * 24));
                
                //Condición para saber si la reserva se está cancelando el día antes o no
                if(diferenciaDias>1)
                {
                    //Setear datos para que el correo recupere la información del cliente
                    c.setRut_cliente(cDAO.traerCliente(emailBuscar).getRut_cliente());
                    c.setDigito_verificador_cliente(cDAO.traerCliente(emailBuscar).getDigito_verificador_cliente());
                    c.setNombre_cliente(String.valueOf(cDAO.traerCliente(emailBuscar).getNombre_cliente()));
                    c.setPapellido_cliente(String.valueOf(cDAO.traerCliente(emailBuscar).getPapellido_cliente()));
                    c.setSapellido_cliente(String.valueOf(cDAO.traerCliente(emailBuscar).getSapellido_cliente()));

                    r.setFecha_registro(resDAO.traerReserva(id).getFecha_registro());
                    r.setFecha_reserva(resDAO.traerReserva(id).getFecha_reserva());
                    r.setRut_solicitante(resDAO.traerReserva(id).getRut_solicitante());
                    r.setMesas_id_mesa(resDAO.traerReserva(id).getMesas_id_mesa());
                    r.setHorario_reservas_id_horario_reserva(resDAO.traerReserva(id).getHorario_reservas_id_horario_reserva());
                    int id_horario = resDAO.traerReserva(id).getHorario_reservas_id_horario_reserva();
                    System.out.println(id_horario);
                    String horario = hrDAO.recuperarHorario(id_horario).getHorario_reserva();
                    r.setHorario_reserva(horario);
                    System.out.println(horario);

                    //Ejecutar las acciones
                    resDAO.eliminarReserva(id);
                    email.sendEmailReservaCancelada(c, r, emailBuscar);
                    request.getRequestDispatcher("Resv?accion=ListarReservas").forward(request, response);
                }else{
                    boolean error = true;
                    request.setAttribute("ErrorCancelar", error);
                    request.getRequestDispatcher("Resv?accion=ListarReservas").forward(request, response);
                }
                break;
            case "Registrar":
                ClienteDAO cliDAO = new ClienteDAO();
                ReservaDAO resDAO = new ReservaDAO();
                HorarioReservaDAO horDAO = new HorarioReservaDAO();
                //Recuperar variables del formulario y el resto de datos
                HttpSession sessionReservar = request.getSession(true); // reusar
                String emailCliente=String.valueOf(sessionReservar.getAttribute("email"));
                int rutSession = cliDAO.traerCliente(emailCliente).getRut_cliente();
                String fechaRegistro=java.time.LocalDate.now().toString();
                Date fechaReserva=Date.valueOf(request.getParameter("fechaReserva"));
                int horarioReserva=Integer.parseInt(request.getParameter("horarioReserva"));
                int idMesa=Integer.parseInt(request.getParameter("idMesa"));
                String horario = horDAO.recuperarHorario(horarioReserva).getHorario_reserva();
                
                //Variables para determinar si la reserva ya está creada
                String fechaReservada = String.valueOf(resDAO.traerReservaPorFecha(fechaReserva).getFecha_reserva());
                String fechaReservadaEscogida=request.getParameter("fechaReserva");
                int horarioReservado = resDAO.traerReservaPorFecha(fechaReserva).getHorario_reservas_id_horario_reserva();
                int MesaReservada = resDAO.traerReservaPorFecha(fechaReserva).getMesas_id_mesa();

                //Condición para validar si la reserva está creada o no
                if(horarioReserva==0 || idMesa==0){
                    boolean error = true;
                    request.setAttribute("errorData", error);
                    request.getRequestDispatcher("reservar.jsp").forward(request, response);
                }else{
                    if(fechaReservadaEscogida.equals(fechaReservada) && horarioReserva==horarioReservado && idMesa==MesaReservada){
                        boolean error = true;
                        request.setAttribute("ErrorReservar", error);
                        request.getRequestDispatcher("reservar.jsp").forward(request, response);
                    }else{
                        c.setRut_cliente(rutSession);
                        c.setDigito_verificador_cliente(String.valueOf(sessionReservar.getAttribute("dv")));
                        c.setNombre_cliente(String.valueOf(sessionReservar.getAttribute("nombre")));
                        c.setPapellido_cliente(String.valueOf(sessionReservar.getAttribute("papellido")));
                        c.setSapellido_cliente(String.valueOf(sessionReservar.getAttribute("sapellido")));                

                        r.setFecha_registro(Date.valueOf(fechaRegistro));
                        r.setFecha_reserva(fechaReserva);
                        r.setRut_solicitante(rutSession);
                        r.setMesas_id_mesa(idMesa);
                        r.setHorario_reservas_id_horario_reserva(horarioReserva);
                        r.setHorario_reserva(horario);

                        resDAO.crearReserva(r);
                        email.sendEmailReserva(c, r, emailCliente);
                        request.getRequestDispatcher("Resv?accion=ListarReservas").forward(request, response);
                    }
                }
                break;
            default:
                throw new AssertionError();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
