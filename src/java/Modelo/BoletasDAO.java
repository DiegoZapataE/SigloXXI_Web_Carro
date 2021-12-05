/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author diego
 */
public class BoletasDAO {
    PreparedStatement ps;
    ResultSet rs;
    Conexion c=new Conexion();
    Connection con; 
    Boletas b = new Boletas();
    
    public boolean insertarBoletas (Boletas b){
        boolean resultado = false;
        try{
            con = c.conectar();
            ps = con.prepareStatement("begin\n" +
                                      "  PKG_BOLETAS_WEB.INSERTAR(?,?,?,?,?,?);\n" +
                                      "end;");
            //for (int i = 0; i < ca.getCantidad(); i++) {
            ps.setString(1, b.getDetalle_boleta());
            ps.setInt(2, b.getValor_boleta());
            ps.setDate(3, b.getFecha_boleta());
            ps.setInt(4, b.getRut());
            ps.setInt(5, b.getTipo_pago_id_tipo_pago());
            ps.setInt(6, b.getEstado_boleta_id_estado_boleta());
            int update = ps.executeUpdate();
            if (update > 0) {
                resultado = true;
            }  
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return resultado;
    }
    
    public Boletas traerUltimaBoleta() {
        Boletas bo = new Boletas();
        Connection con = c.conectar();
        try {
            con=c.conectar();
            CallableStatement cstmt = con.prepareCall("{? = call PKG_BOLETAS_WEB.BUSCAR_ULTIMA_BOLETA()}");
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.execute();
            rs = (ResultSet)cstmt.getObject(1);
            while (rs.next()) {
                bo.setId_boleta(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bo;
    }
    
    public boolean modificarEstadoBoleta(int id, int estado){
        boolean retorno = false;
        con = c.conectar();
        try {
            ps = con.prepareStatement("begin\n" +
                                      "  PKG_BOLETAS_WEB.ACTUALIZAR_ESTADO_BOLETA(?,?);\n" +
                                      "end;");
            ps.setInt(1, id);
            ps.setInt(2, estado);
            
            int update = ps.executeUpdate();
            if (update > 0) {
                retorno = true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            c.cerrarConexion(con);
        }
        return retorno;
    }
    
    public int traerBoletaPendientePorRut(int rut) {
        Connection con = c.conectar();
        int nro_boleta = 0;
        try {
            con=c.conectar();
            CallableStatement cstmt = con.prepareCall("{? = call PKG_BOLETAS_WEB.BUSCAR_BOLETA_PENDIENTE_POR_RUT(?)}");
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.setInt(2, rut);
            cstmt.execute();
            rs = (ResultSet)cstmt.getObject(1);
            while (rs.next()) {
                nro_boleta = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            c.cerrarConexion(con);
        }
        return nro_boleta;
    }
    
   public int traerRutBoleta(int rut) {
        Connection con = c.conectar();
        int Rut = 0;
        try {
            con=c.conectar();
            CallableStatement cstmt = con.prepareCall("{? = call PKG_BOLETAS_WEB.BUSCAR_RUT_BOLETA(?)}");
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.setInt(2, rut);
            cstmt.execute();
            rs = (ResultSet)cstmt.getObject(1);
            while (rs.next()) {
                Rut = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            c.cerrarConexion(con);
        }
        return Rut;
    }
   
   public boolean modificarDetalle(int id, String detalle, int valor){
        boolean retorno = false;
        con = c.conectar();
        try {
            ps = con.prepareStatement("begin\n" +
                                      "  PKG_BOLETAS_WEB.ACTUALIZAR_DETALLE_VALOR(?,?,?);\n" +
                                      "end;");
            ps.setInt(1, id);
            ps.setString(2, detalle);
            ps.setInt(3, valor);
            
            int update = ps.executeUpdate();
            if (update > 0) {
                retorno = true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            c.cerrarConexion(con);
        }
        return retorno;
    }
   
   public Boletas traerBoletaPorID(int id) {
        Boletas bo = new Boletas();
        Connection con = c.conectar();
        try {
            con=c.conectar();
            CallableStatement cstmt = con.prepareCall("{? = call PKG_BOLETAS_WEB.BUSCAR(?)}");
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.setInt(2, id);
            cstmt.execute();
            rs = (ResultSet)cstmt.getObject(1);
            while (rs.next()) {
                bo.setId_boleta(rs.getInt(1));
                bo.setDetalle_boleta(rs.getString(2));
                bo.setValor_boleta(rs.getInt(3));
                bo.setFecha_boleta(rs.getDate(4));
                bo.setRut(rs.getInt(5));
                bo.setTipo_pago_id_tipo_pago(rs.getInt(6));
                bo.setEstado_boleta_id_estado_boleta(rs.getInt(7));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bo;
    }
   
   public boolean insertarSolicitudPago (int boleta, int mesa, int valor){
        boolean resultado = false;
        try{
            con = c.conectar();
            ps = con.prepareStatement("begin\n" +
                                      "  PKG_SOLICITAR_PAGO.INSERTAR(?,?,?);\n" +
                                      "end;");
            ps.setInt(1,boleta);
            ps.setInt(2, mesa);
            ps.setInt(3, valor);
            int update = ps.executeUpdate();
            if (update > 0) {
                resultado = true;
            }  
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return resultado;
    }
}
