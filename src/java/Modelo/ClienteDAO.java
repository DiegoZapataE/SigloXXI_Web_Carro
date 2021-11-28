package Modelo;

import Controlador.Conexion;
import java.security.SecureRandom;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author diego
 */
public class ClienteDAO {
    PreparedStatement ps;
    ResultSet rs;
    Conexion c=new Conexion();
    Cliente cli = new Cliente();
    Connection con; 
    
    public boolean validarCliente(String email, String password) {
        boolean retorno = false;
        try {
            con=c.conectar();
            CallableStatement cstmt = con.prepareCall("{? = call PKG_CLIENTES_WEB.VALIDAR_CLIENTE(?,?)}");
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.setString(2, email);
            cstmt.setString(3, password);
            cstmt.execute();
            rs = (ResultSet)cstmt.getObject(1);
            int cont = 0;
            while (rs.next()) {
                cont++;
                if (cont == 1) {
                    retorno = true;
                }
            }
            try{
                con.close();
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return retorno;
    }
    
    public Cliente traerCliente(String email) {
        Connection con = c.conectar();
        try {
            con=c.conectar();
            CallableStatement cstmt = con.prepareCall("{? = call PKG_CLIENTES_WEB.BUSCAR_POR_EMAIL(?)}");
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.setString(2, email);
            cstmt.execute();
            rs = (ResultSet)cstmt.getObject(1);
            while (rs.next()) {
                cli.setRut_cliente(rs.getInt("RUT_CLIENTE"));
                cli.setDigito_verificador_cliente(rs.getString("DIGITO_VERIFICADOR_CLIENTE"));
                cli.setClave_cliente(rs.getString("CLAVE_CLIENTE"));
                cli.setNombre_cliente(rs.getString("NOMBRE_CLIENTE"));
                cli.setPapellido_cliente(rs.getString("PAPELLIDO_CLIENTE"));
                cli.setSapellido_cliente(rs.getString("SAPELLIDO_CLIENTE"));
                cli.setTelefono_cliente(rs.getInt("TELEFONO_CLIENTE"));
                cli.setDireccion_cliente(rs.getString("DIRECCION_CLIENTE"));
                cli.setEmail_cliente(rs.getString("EMAIL_CLIENTE"));
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cli;
    }
    
    public boolean crearCliente(Cliente cl){
        boolean retorno = false;
        try{
            con = c.conectar();
            ps = con.prepareStatement("begin\n" +
                                      "  PKG_CLIENTES_WEB.INSERTAR(?,?,?,?,?,?,?,?,?);\n" +
                                      "end;");
            ps.setInt(1, cl.getRut_cliente());
            ps.setString(2, cl.getDigito_verificador_cliente());
            ps.setString(3, cl.getClave_cliente());
            ps.setString(4, cl.getNombre_cliente());
            ps.setString(5, cl.getPapellido_cliente());
            ps.setString(6, cl.getSapellido_cliente());
            ps.setInt(7, cl.getTelefono_cliente());
            ps.setString(8, cl.getDireccion_cliente());
            ps.setString(9, cl.getEmail_cliente());
            
            int update = ps.executeUpdate();
            if (update > 0) {
                retorno = true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return retorno;
    }
    
    public boolean modificarCliente(int rut_cliente, String clave_cliente, String nombre_cliente, String papellido_cliente, String sapellido_cliente,
    int telefono_cliente, String direccion_cliente, String email_cliente) {
        boolean retorno = false;
        con = c.conectar();
        try {
            ps = con.prepareStatement("begin\n" +
                                      "  PKG_CLIENTES_WEB.ACTUALIZAR(?,?,?,?,?,?,?,?);\n" +
                                      "end;");
            ps.setInt(1, rut_cliente);
            ps.setString(2, clave_cliente);
            ps.setString(3, nombre_cliente);
            ps.setString(4, papellido_cliente);
            ps.setString(5, sapellido_cliente);
            ps.setInt(6, telefono_cliente);
            ps.setString(7, direccion_cliente);
            ps.setString(8, email_cliente);
            
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
    
    public boolean modificarPass(String email, String clave_cliente){
        boolean retorno = false;
        con = c.conectar();
        try {
            ps = con.prepareStatement("begin\n" +
                                      "  PKG_CLIENTES_WEB.ACTUALIZAR_PASS(?,?);\n" +
                                      "end;");
            ps.setString(1, email);
            ps.setString(2, clave_cliente);
            
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

    public String randomString(int len){
        StringBuilder sb = new StringBuilder(len);
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        for(int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }
    
}
