/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDAO;
import Modelo.EmailDAO;
import com.sun.xml.internal.ws.util.StringUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import org.apache.commons.lang.WordUtils;
import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

/**
 *
 * @author Felipe Fuentes
 */
public class ClienteServlet extends HttpServlet {

    Cliente c=new Cliente();
    ClienteDAO clDAO = new ClienteDAO();
    EmailDAO mailDAO = new EmailDAO();
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
        try(PrintWriter out  = response.getWriter();){
        processRequest(request, response);
        String accion=request.getParameter("accion");
        switch (accion){
        case "IngresarRegistrar":
            request.getRequestDispatcher("registrarC.jsp").forward(request, response);
            break;
        case "RegistrarC":
            //Recuper datos ingresados
            String rutCompletoCliente=request.getParameter("rutCliente");
            String password = sha256Hex(request.getParameter("password"));
            String nombre=request.getParameter("nombre");
            String apellidoPaterno=request.getParameter("primerApellido");
            String apellidoMaterno=request.getParameter("segundoApellido");
            String telefono=request.getParameter("telefono");
            String direccion=request.getParameter("direccion");
            String email_cliente=request.getParameter("email");

            rutCompletoCliente = rutCompletoCliente.toUpperCase();
            rutCompletoCliente = rutCompletoCliente.replace(".", "");
            rutCompletoCliente = rutCompletoCliente.replace("-", "");
            int rutRegistrarCliente = Integer.parseInt(rutCompletoCliente.substring(0, rutCompletoCliente.length() - 1));
            String dv = rutCompletoCliente.substring(rutCompletoCliente.length() - 1);         
            String email_existente=clDAO.traerCliente(email_cliente).getEmail_cliente();
            int rut_existente=clDAO.traerCliente(email_cliente).getRut_cliente();
            
            //Validar que tanto el correo como rut no estén registrados en la tabla cliente de la base de datos
            if(!email_cliente.equals(email_existente) && rutRegistrarCliente != rut_existente){
                //Crear perfil del cliente
                c.setRut_cliente(rutRegistrarCliente);
                c.setDigito_verificador_cliente(dv);
                c.setClave_cliente(password);
                c.setNombre_cliente(StringUtils.capitalize(nombre));
                c.setPapellido_cliente(StringUtils.capitalize(apellidoPaterno));
                c.setSapellido_cliente(StringUtils.capitalize(apellidoMaterno));
                c.setTelefono_cliente(Integer.parseInt(telefono));
                c.setDireccion_cliente(StringUtils.capitalize(direccion));
                c.setEmail_cliente(email_cliente.toLowerCase());

                clDAO.crearCliente(c);
                mailDAO.sendEmailCreacionCliente(c);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }else{
                HttpSession session = request.getSession(true); // reusar
                String mensaje = "El proceso no ha sido realizado con éxito. Por favor ingrese un correo y rut que no esté registrado.";
                session.setAttribute("mensajeRegistrar", mensaje);
                boolean error = true;
                request.setAttribute("ErrorRegistrar", error);
                request.getRequestDispatcher("registrarC.jsp").forward(request, response);
            }
            break;
        case "ModificarC":
            //Recuper datos ingresados
            HttpSession session = request.getSession(true); // reusar
            int rutSes = (int)session.getAttribute("rut");
            String emailCliente=String.valueOf(session.getAttribute("email"));
            String passwordC = sha256Hex(request.getParameter("password"));
            String dvC = String.valueOf(session.getAttribute("dv"));
            String emailNuevo=request.getParameter("emailNuevo");
            String passwordNC=sha256Hex(request.getParameter("passwordNueva"));
            String nombreC=StringUtils.capitalize(request.getParameter("nombre"));
            String apellidoPaternoC=StringUtils.capitalize(request.getParameter("primerApellido"));
            String apellidoMaternoC=StringUtils.capitalize(request.getParameter("segundoApellido"));
            String telefonoC=request.getParameter("telefono");
            String direccionC=StringUtils.capitalize(request.getParameter("direccion"));
            String claveAntigua=clDAO.traerCliente(emailCliente).getClave_cliente();
            System.out.println(passwordC);
            System.out.println(clDAO.traerCliente(emailCliente).getClave_cliente().toLowerCase());
            //Validar si la contraseña actual es igual a la ingresada
            if(passwordC.equals(claveAntigua)){
                //Actualizar datos
                c.setRut_cliente(rutSes);
                c.setDigito_verificador_cliente(dvC);
                c.setClave_cliente(passwordNC);
                c.setNombre_cliente(StringUtils.capitalize(nombreC));
                c.setPapellido_cliente(StringUtils.capitalize(apellidoPaternoC));
                c.setSapellido_cliente(StringUtils.capitalize(apellidoMaternoC));
                c.setTelefono_cliente(Integer.parseInt(telefonoC));
                c.setDireccion_cliente(StringUtils.capitalize(direccionC));
                c.setEmail_cliente(emailNuevo.toLowerCase());

                clDAO.modificarCliente(rutSes, passwordNC, nombreC, apellidoPaternoC, apellidoMaternoC, Integer.parseInt(telefonoC), direccionC, emailNuevo);
                mailDAO.sendEmailActualizacionCliente(c);

                request.getSession().invalidate();
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
            else{
                String mensaje = "El proceso no ha sido realizado con éxito. Por favor ingrese su contraseña actual correcta";
                session.setAttribute("mensajeModificar", mensaje);
                boolean error = true;
                request.setAttribute("ErrorModificar", error);
                request.getRequestDispatcher("modificarCliente.jsp").forward(request, response);
                
            }
            break;
        case "Volver":
            request.getRequestDispatcher("Resv?accion=ListarReservas").forward(request, response);
            break;
        default:
            throw new AssertionError();
            }
        }catch(Exception e){
            e.printStackTrace();
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
