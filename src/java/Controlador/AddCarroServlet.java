/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Carro;
import Modelo.MenuDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author diego
 */
@WebServlet(name = "AddCarroServlet", urlPatterns = {"/add-al-carro"})
public class AddCarroServlet extends HttpServlet {

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
            out.println("<title>Servlet AddCarroServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddCarroServlet at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try(PrintWriter out = response.getWriter()){
            ArrayList<Carro> carroList = new ArrayList<>();
            MenuDAO dao = new MenuDAO();
            int id = Integer.parseInt(request.getParameter("id_menu"));
            Carro cm = new Carro();
            cm.setId_menu(dao.traerMenu(id).getId_menu());
            cm.setNombre_menu(dao.traerMenu(id).getNombre_menu());
            cm.setValor(dao.traerMenu(id).getValor_menu());
            cm.setCantidad(1);
            HttpSession session = request.getSession();
            ArrayList<Carro> carro_list = (ArrayList<Carro>) session.getAttribute("carro-list");
            
            if(carro_list == null){
                carroList.add(cm);
                session.setAttribute("carro-list", carroList);
                response.sendRedirect("menu_comprar.jsp");
            }else{
                carroList = carro_list;
                boolean exist = false;
                
                for(Carro c:carro_list){
                    if(c.getId_menu() == id){
                        exist = true;
                        response.sendRedirect("menu_carro.jsp");
                    }
                }
                if(!exist){
                        carroList.add(cm);
                        response.sendRedirect("menu_comprar.jsp");
                    }
            }
        }
    }
    

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
