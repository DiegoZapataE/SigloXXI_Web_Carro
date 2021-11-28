package Modelo;

import Controlador.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class ReservaDAO {
    PreparedStatement ps;
    ResultSet rs;
    Conexion c=new Conexion();
    Connection con; 
    
    public ArrayList<Reserva> listarReservas(int rut){
        ArrayList<Reserva>lista=new ArrayList<>();
        try{
            con=c.conectar();
            CallableStatement cstmt = con.prepareCall("{? = call PKG_RESERVAS_WEB.BUSCAR_RESERVAS_POR_CLIENTE(?)}");
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.setInt(2, rut);
            cstmt.execute();
            rs = (ResultSet)cstmt.getObject(1);
            while (rs.next()){
                Reserva p=new Reserva();
                p.setId_reserva(rs.getInt(1));
                p.setFecha_registro(rs.getDate(2)); 
                p.setFecha_reserva(rs.getDate(3));
                p.setRut_solicitante(rs.getInt(4));
                p.setMesas_id_mesa(rs.getInt(5));
                p.setHorario_reservas_id_horario_reserva(rs.getInt(6));
                p.setHorario_reserva(rs.getString(7));
                lista.add(p);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return lista;
    }
    
    public Reserva traerReserva(int id) {
        Reserva r = new Reserva();
        Connection con = c.conectar();
        try {
            con=c.conectar();
            CallableStatement cstmt = con.prepareCall("{? = call PKG_RESERVAS_WEB.BUSCAR(?)}");
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.setInt(2, id);
            cstmt.execute();
            rs = (ResultSet)cstmt.getObject(1);

            while (rs.next()) {
                r.setId_reserva(rs.getInt("ID_RESERVA"));
                r.setFecha_reserva(rs.getDate("FECHA_RESERVA"));
                r.setRut_solicitante(rs.getInt("RUT_SOLICITANTE"));
                r.setMesas_id_mesa(rs.getInt("MESAS_ID_MESA"));
                r.setHorario_reservas_id_horario_reserva(rs.getInt("HORARIO_RESERVAS_ID_HORARIO_RESERVA"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            c.cerrarConexion(con);
        }
        return r;
    }
    public Reserva traerReservaPorFecha(Date fecha) {
        Reserva r = new Reserva();
        Connection con = c.conectar();
        try {
            con=c.conectar();
            CallableStatement cstmt = con.prepareCall("{? = call PKG_RESERVAS_WEB.BUSCAR_RESERVAS_POR_FECHA(?)}");
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.setDate(2, (java.sql.Date)fecha);
            cstmt.execute();
            rs = (ResultSet)cstmt.getObject(1);

            while (rs.next()) {
                r.setId_reserva(rs.getInt("ID_RESERVA"));
                r.setFecha_reserva(rs.getDate("FECHA_RESERVA"));
                r.setRut_solicitante(rs.getInt("RUT_SOLICITANTE"));
                r.setMesas_id_mesa(rs.getInt("MESAS_ID_MESA"));
                r.setHorario_reservas_id_horario_reserva(rs.getInt("HORARIO_RESERVAS_ID_HORARIO_RESERVA"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            c.cerrarConexion(con);
        }
        return r;
    }
    
    public Reserva verificarReservaDuplicada(java.sql.Date fecha, String horario, int mesa) {
        Reserva r = new Reserva();
        Connection con = c.conectar();
        try {
            con=c.conectar();
            CallableStatement cstmt = con.prepareCall("{? = call PKG_RESERVAS_WEB.BUSCAR_RESERVAS_REPETIDAS(?,?,?)}");
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.setDate(2, fecha);
            cstmt.setString(3, horario);
            cstmt.setInt(4, mesa);
            cstmt.execute();
            rs = (ResultSet)cstmt.getObject(1);
            while (rs.next()) {
                r.setId_reserva(rs.getInt("ID_RESERVA"));
                r.setFecha_reserva(rs.getDate("FECHA_RESERVA"));
                r.setRut_solicitante(rs.getInt("RUT_SOLICITANTE"));
                r.setMesas_id_mesa(rs.getInt("MESAS_ID_MESA"));
                r.setHorario_reservas_id_horario_reserva(rs.getInt("HORARIO_RESERVAS_ID_HORARIO_RESERVA"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            c.cerrarConexion(con);
        }
        return r;
    }
    
    public void eliminarReserva(int id) {
        try {
            con=c.conectar();
            ps=con.prepareStatement("begin\n" +
                                    "  PKG_RESERVAS_WEB.ELIMINAR(?);\n" +
                                    "end;");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public boolean crearReserva(Reserva r){
        boolean retorno = false;
        try{
            con = c.conectar();
            ps = con.prepareStatement("begin\n" +
                                      "  PKG_RESERVAS_WEB.INSERTAR(?,?,?,?,?);\n" +
                                      "end;");
            ps.setDate(1, r.getFecha_registro());
            ps.setDate(2, r.getFecha_reserva());
            ps.setInt(3, r.getRut_solicitante());
            ps.setInt(4, r.getMesas_id_mesa());
            ps.setInt(5, r.getHorario_reservas_id_horario_reserva());
            int update = ps.executeUpdate();
            if (update > 0) {
                retorno = true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return retorno;
    }
    
}