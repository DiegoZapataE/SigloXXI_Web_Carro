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
import java.util.ArrayList;
import java.util.Date;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Felipe Fuentes
 */
public class MesaDAO {
    PreparedStatement ps;
    ResultSet rs;
    Conexion c=new Conexion();
    Connection con;
    
    public ArrayList<Mesa> listarMesasDisponibles(String fecha, int horario){
        ArrayList<Mesa>lista=new ArrayList<>();
        try{
            con=c.conectar();
            CallableStatement cstmt = con.prepareCall("{? = call PKG_MESAS_WEB.BUSCAR_MESAS_DISPONIBLES(?,?)}");
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.setString(2, fecha);
            cstmt.setInt(3, horario);
            cstmt.execute();
            rs = (ResultSet)cstmt.getObject(1);
            while (rs.next()){
                Mesa m = new Mesa();
                m.setId_mesa(rs.getInt("ID_MESA"));
                lista.add(m);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());         
        } 
        return lista;
    }
    public Mesa traerMesa(int rut) {
        Mesa m = new Mesa();
        Connection con = c.conectar();
        try {
            con=c.conectar();
            CallableStatement cstmt = con.prepareCall("{? = call PKG_MESAS_WEB.BUSCAR_POR_RUT(?)}");
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.setInt(2, rut);
            cstmt.execute();
            rs = (ResultSet)cstmt.getObject(1);

            while (rs.next()) {
                m.setId_mesa(rs.getInt(1));
                m.setEstado_mesa_id_estado_mesa(rs.getInt(2));
                m.setClientes_rut_cliente(rs.getInt(3));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            c.cerrarConexion(con);
        }
        return m;
    }
}
