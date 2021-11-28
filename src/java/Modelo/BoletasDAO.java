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
            ps.setInt(4, b.getTipo_pago_id_tipo_pago());
            ps.setInt(5, b.getEstado_boleta_id_estado_boleta());
            ps.setString(6, b.getRut());
            int update = ps.executeUpdate();
            if (update > 0) {
                resultado = true;
            }
            
        //}
            
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
}
