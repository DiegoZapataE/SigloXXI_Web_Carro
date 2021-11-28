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
import java.sql.Statement;
import oracle.jdbc.OracleTypes;
/**
 *
 * @author diego
 */
public class OrdenesDAO {
    PreparedStatement ps;
    ResultSet rs;
    Conexion c=new Conexion();
    Connection con; 
    
    public boolean insertarOrden (Ordenes o, Carro ca){
        boolean resultado = false;
        try{
            con = c.conectar();
            ps = con.prepareStatement("begin\n" +
                                      "  PKG_ORDENES_WEB.INSERTAR(?,?,?,?,?,?);\n" +
                                      "end;");
            //for (int i = 0; i < ca.getCantidad(); i++) {
            ps.setDate(1, o.getFecha_orden());
            ps.setInt(2, o.getEstado_orden_id_estado_orden());
            ps.setInt(3, o.getMesas_id_mesas());
            ps.setInt(4, o.getMenu_id_menu());
            ps.setInt(5, o.getBoleta_id_boleta());
            ps.setInt(6, o.getUsuarios_rut_usuario());
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
    public Ordenes traerRutCocinaConMenorTiempo() {
        Ordenes m = new Ordenes();
        Connection con = c.conectar();
        try {
            con=c.conectar();
            CallableStatement cstmt = con.prepareCall("{? = call PKG_ORDENES_WEB.BUSCAR_RUT_MENOR_TIEMPO_COCINA()}");
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.execute();
            rs = (ResultSet)cstmt.getObject(1);

            while (rs.next()) {
                m.setUsuarios_rut_usuario(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            c.cerrarConexion(con);
        }
        return m;
    }
    public Ordenes traerRutBarConMenorTiempo() {
        Ordenes m = new Ordenes();
        Connection con = c.conectar();
        try {
            con=c.conectar();
            CallableStatement cstmt = con.prepareCall("{? = call PKG_ORDENES_WEB.BUSCAR_RUT_MENOR_TIEMPO_BAR()}");
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.execute();
            rs = (ResultSet)cstmt.getObject(1);

            while (rs.next()) {
                m.setUsuarios_rut_usuario(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            c.cerrarConexion(con);
        }
        return m;
    }
}
