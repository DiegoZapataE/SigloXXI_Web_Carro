/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Felipe Fuentes
 */
public class HorarioReservaDAO {
    PreparedStatement ps;
    ResultSet rs;
    Conexion c=new Conexion();
    Connection con; 
    
    
    public HorarioReserva recuperarHorario(int id) {
        HorarioReserva hor = new HorarioReserva();
        Connection con = c.conectar();
        try {
            con=c.conectar();
            CallableStatement cstmt = con.prepareCall("{? = call PKG_HORARIO_RESERVAS_WEB.BUSCAR(?)}");
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.setInt(2, id);
            cstmt.execute();
            rs = (ResultSet)cstmt.getObject(1);
            
            while (rs.next()) {
                hor.setId_horario_reserva(rs.getInt("ID_HORARIO_RESERVA"));
                hor.setHorario_reserva(rs.getString("HORARIO_RESERVA"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return hor;
    }
    
    public ArrayList<HorarioReserva> listarHorarios(){
        ArrayList<HorarioReserva>lista=new ArrayList<>();
        try{
            con=c.conectar();
            CallableStatement cstmt = con.prepareCall("{? = call PKG_HORARIO_RESERVAS_WEB.BUSCAR_TODO()}");
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.execute();
            rs = (ResultSet)cstmt.getObject(1);
            while (rs.next()){
                HorarioReserva hr=new HorarioReserva();
                hr.setId_horario_reserva(rs.getInt(1));
                hr.setHorario_reserva(rs.getString(2)); 
                lista.add(hr);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return lista;
    }
    
}
