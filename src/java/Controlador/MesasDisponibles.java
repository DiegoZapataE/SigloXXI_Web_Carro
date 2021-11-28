/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.HorarioReserva;
import Modelo.HorarioReservaDAO;
import Modelo.Mesa;
import Modelo.MesaDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe Fuentes
 */
@WebServlet(name = "MesasDisponibles", urlPatterns = {"/MesasDisponibles"})
public class MesasDisponibles extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            //Setear de objetos
            MesaDAO mDAO = new MesaDAO();
            HorarioReservaDAO hrDAO = new HorarioReservaDAO();
            String pattern = "dd/MM/yy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String op = request.getParameter("operation");
            
            //Condición cuando cambiamos la fecha. Se actualiza el horario.
            if (op.equals("horarioReserva")) {
                List<HorarioReserva> hlist = hrDAO.listarHorarios();
                Gson json = new Gson();
                String horariosLista = json.toJson(hlist);
                response.setContentType("text/html");
                response.getWriter().write(horariosLista);
            }
            //Condición cuando cambiamos el horario. Se actualizan las mesas.
            if (op.equals("idMesa")) {
                
                int id=Integer.parseInt(request.getParameter("id_horario_reserva"));
                Date fecha=Date.valueOf(request.getParameter("fecha_reserva"));
                System.out.println(simpleDateFormat.format(fecha));
                System.out.println(id);
                List<Mesa> mlist = mDAO.listarMesasDisponibles(simpleDateFormat.format(fecha),id);
                Gson json = new Gson();
                String mesasLista = json.toJson(mlist);
                response.setContentType("text/html");
                response.getWriter().write(mesasLista);
            }
        }
    }
}
