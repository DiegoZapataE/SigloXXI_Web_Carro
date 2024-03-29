/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Boletas;
import Modelo.BoletasDAO;
import Modelo.Carro;
import Modelo.Cliente;
import Modelo.MenuDAO;
import Modelo.Mesa;
import Modelo.MesaDAO;
import Modelo.Ordenes;
import Modelo.OrdenesDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

/**
 *
 * @author diego
 */
@WebServlet(name = "ComprarServlet", urlPatterns = {"/carro-comprar"})
public class ComprarServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ComprarServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ComprarServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        try(PrintWriter out = response.getWriter()){
            HttpSession session = request.getSession(true);
            //traer todos los menus del carro
            ArrayList<Carro> carro_list = (ArrayList<Carro>) request.getSession().getAttribute("carro-list");
            //autentificacion?
            String email = String.valueOf(session.getAttribute("email"));
            int rutS = (int)session.getAttribute("rut");
            //comprar y lista del carro
            boolean resultado = false;
            
            Boletas b = new Boletas();
            BoletasDAO dao = new BoletasDAO();
            MenuDAO mdao = new MenuDAO();
            MesaDAO medao = new MesaDAO();
            OrdenesDAO oDAO = new OrdenesDAO();
            if (dao.traerBoletaPendientePorRut(rutS)!=0) {
                if(carro_list != null && email != null){
                    int id_boleta = dao.traerBoletaPendientePorRut(rutS);
                    int suma = dao.traerBoletaPorID(id_boleta).getValor_boleta();
                    String detalle = dao.traerBoletaPorID(id_boleta).getDetalle_boleta();
                    for(Carro c:carro_list){

                        suma += c.getCantidad()*c.getValor();
                        detalle += c.getCantidad() + " " + c.getNombre_menu() + " | ";
                    }
                        dao.modificarDetalle(id_boleta, detalle, suma);
                        String fechaRegistro=java.time.LocalDate.now().toString();
                        for(Carro c:carro_list){
                            if(mdao.traerMenu(c.getId_menu()).getTipo_menu_id_tipo_menu()==1){
                                for (int i = 0; i < c.getCantidad(); i++) {
                                Ordenes o = new Ordenes();
                                Mesa m = new Mesa();
                                o.setFecha_orden(Date.valueOf(fechaRegistro)); 
                                o.setEstado_orden_id_estado_orden(1);
                                o.setMesas_id_mesas(medao.traerMesa((int)session.getAttribute("rut")).getId_mesa());
                                o.setMenu_id_menu(c.getId_menu());
                                o.setBoleta_id_boleta(id_boleta);
                                o.setUsuarios_rut_usuario(oDAO.traerRutBarConMenorTiempo().getUsuarios_rut_usuario());
                                //se inicia la clase dao
                                OrdenesDAO daoo = new OrdenesDAO();
                                resultado = daoo.insertarOrden(o, c);
                            }
                        }
                            if(mdao.traerMenu(c.getId_menu()).getTipo_menu_id_tipo_menu()==2){
                                for (int i = 0; i < c.getCantidad(); i++) {
                                Ordenes o = new Ordenes();
                                Mesa m = new Mesa();
                                o.setFecha_orden(Date.valueOf(fechaRegistro)); 
                                o.setEstado_orden_id_estado_orden(1);
                                o.setMesas_id_mesas(medao.traerMesa((int)session.getAttribute("rut")).getId_mesa());
                                o.setMenu_id_menu(c.getId_menu());
                                o.setBoleta_id_boleta(id_boleta);
                                o.setUsuarios_rut_usuario(oDAO.traerRutCocinaConMenorTiempo().getUsuarios_rut_usuario());
                                //se inicia la clase dao
                                OrdenesDAO daoo = new OrdenesDAO();
                                resultado = daoo.insertarOrden(o, c);
                            }   
                        }
                        if(!resultado)break;
                }      
                int id_act = dao.traerUltimaBoleta().getId_boleta();
                session.setAttribute("idBoleta", id_act);
                carro_list.clear();
                    response.sendRedirect("menu_carro.jsp");
                }else{
                    if(email == null)response.sendRedirect("menu_comprar.jsp");{
                    response.sendRedirect("menu_carro.jsp");
                    }
                }
            }
            else {
                
                if(carro_list != null && email != null){
                    String detalle = "";
                    int suma = 0;
                    for(Carro c:carro_list){

                        suma += c.getCantidad()*c.getValor();
                        detalle += c.getCantidad() + " " + c.getNombre_menu() + " | ";
                    }
                        String fechaahora=java.time.LocalDate.now().toString();
                        int id = dao.traerUltimaBoleta().getId_boleta() + 1;
                        b.setId_boleta(id);
                        b.setDetalle_boleta(detalle);
                        b.setValor_boleta(suma);
                        b.setFecha_boleta(Date.valueOf(fechaahora));
                        b.setRut(rutS);
                        b.setTipo_pago_id_tipo_pago(1);
                        b.setEstado_boleta_id_estado_boleta(1);
                            //se inicia la clase dao
                        resultado = dao.insertarBoletas(b);
                        String fechaRegistro=java.time.LocalDate.now().toString();
                        for(Carro c:carro_list){
                            if(mdao.traerMenu(c.getId_menu()).getTipo_menu_id_tipo_menu()==1){
                                for (int i = 0; i < c.getCantidad(); i++) {
                                Ordenes o = new Ordenes();
                                Mesa m = new Mesa();
                                int idBoleta = dao.traerUltimaBoleta().getId_boleta();
                                o.setFecha_orden(Date.valueOf(fechaRegistro)); 
                                o.setEstado_orden_id_estado_orden(1);
                                o.setMesas_id_mesas(medao.traerMesa((int)session.getAttribute("rut")).getId_mesa());
                                o.setMenu_id_menu(c.getId_menu());
                                o.setBoleta_id_boleta(idBoleta);
                                o.setUsuarios_rut_usuario(oDAO.traerRutBarConMenorTiempo().getUsuarios_rut_usuario());
                                //se inicia la clase dao
                                OrdenesDAO daoo = new OrdenesDAO();
                                resultado = daoo.insertarOrden(o, c);
                            }
                        }
                            if(mdao.traerMenu(c.getId_menu()).getTipo_menu_id_tipo_menu()==2){
                                for (int i = 0; i < c.getCantidad(); i++) {
                                Ordenes o = new Ordenes();
                                Mesa m = new Mesa();
                                int idBoleta = dao.traerUltimaBoleta().getId_boleta();
                                o.setFecha_orden(Date.valueOf(fechaRegistro)); 
                                o.setEstado_orden_id_estado_orden(1);
                                o.setMesas_id_mesas(medao.traerMesa((int)session.getAttribute("rut")).getId_mesa());
                                o.setMenu_id_menu(c.getId_menu());
                                o.setBoleta_id_boleta(idBoleta);
                                o.setUsuarios_rut_usuario(oDAO.traerRutCocinaConMenorTiempo().getUsuarios_rut_usuario());
                                //se inicia la clase dao
                                OrdenesDAO daoo = new OrdenesDAO();
                                resultado = daoo.insertarOrden(o, c);
                            }   
                        }
                        if(!resultado)break;
                }      
                int id_act = dao.traerUltimaBoleta().getId_boleta();
                session.setAttribute("idBoleta", id_act);
                carro_list.clear();
                    response.sendRedirect("menu_carro.jsp");
                }else{
                    if(email == null)response.sendRedirect("menu_comprar.jsp");{
                    response.sendRedirect("menu_carro.jsp");
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
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
