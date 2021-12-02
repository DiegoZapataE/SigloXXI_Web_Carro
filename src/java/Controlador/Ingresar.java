/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.BoletasDAO;
import Modelo.Cliente;
import Modelo.ClienteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

/**
 *
 * @author Felipe Fuentes
 */
@WebServlet(name = "Ingresar", urlPatterns = {"/Ingresar"})
public class Ingresar extends HttpServlet {

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
        try(PrintWriter out = response.getWriter()){
        response.setContentType("text/html;charset=ISO-8859-1");
        
        //Recuperar datos ingresados del cliente
            String email = request.getParameter("email").toLowerCase();
            String pass = sha256Hex(request.getParameter("password"));
            ClienteDAO cliDAO = new ClienteDAO();
            BoletasDAO bDAO = new BoletasDAO();
            //Validar si los datos son correctos
            if (cliDAO.validarCliente(email, pass)) {
                HttpSession session = request.getSession(true); // reusar
                //Setear atributos a la sesión
                Cliente cli = cliDAO.traerCliente(email);
                session.setAttribute("rut", (int)cli.getRut_cliente());
                session.setAttribute("dv", String.valueOf(cli.getDigito_verificador_cliente()));
                session.setAttribute("password", String.valueOf(cli.getClave_cliente()));
                session.setAttribute("nombre", String.valueOf(cli.getNombre_cliente()));
                session.setAttribute("papellido", String.valueOf(cli.getPapellido_cliente()));
                session.setAttribute("sapellido", String.valueOf(cli.getSapellido_cliente()));
                session.setAttribute("telefono", (int)cli.getTelefono_cliente());
                session.setAttribute("direccion", String.valueOf(cli.getDireccion_cliente()));
                session.setAttribute("email", String.valueOf(cli.getEmail_cliente()));
                session.setAttribute("emailLogin", String.valueOf(cli.getEmail_cliente()));
                int nro_boleta = bDAO.traerBoletaPendientePorRut(cli.getRut_cliente());
                    if(nro_boleta!=0){
                        session.setAttribute("boletaPendiente", nro_boleta);
                    }
                request.getRequestDispatcher("menu_comprar.jsp").forward(request, response);
            } else {    
                boolean error = true;
                request.setAttribute("Error", error);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }catch(Exception e){
            e.printStackTrace();
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
