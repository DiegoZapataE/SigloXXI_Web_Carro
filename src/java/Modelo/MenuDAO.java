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
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author diego
 */
public class MenuDAO {
    private String query;
    PreparedStatement ps;
    ResultSet rs;
    Conexion c=new Conexion();
    Connection con; 
    
    public ArrayList<Menu> listarMenu(){
        ArrayList<Menu>lista=new ArrayList<>();
        try{
            con=c.conectar();
            CallableStatement cstmt = con.prepareCall("{? = call PKG_MENU_WEB.BUSCAR_TODO()}");
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.execute();
            rs = (ResultSet)cstmt.getObject(1);
            while (rs.next()){
                Menu m=new Menu();
                m.setId_menu(rs.getInt(1));
                m.setNombre_menu(rs.getString(2)); 
                m.setDescripcion_menu(rs.getString(3));
                m.setValor_menu(rs.getInt(4));
                m.setTiempo_preparacion_minutos(rs.getInt(5));
                m.setImagen(rs.getString(6));
                m.setTipo_menu_id_tipo_menu(rs.getInt(7));
                lista.add(m);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return lista;
    }
    public Menu traerMenu(int id) {
        Menu m = new Menu();
        Connection con = c.conectar();
        try {
            con=c.conectar();
            CallableStatement cstmt = con.prepareCall("{? = call PKG_MENU_WEB.BUSCAR(?)}");
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.setInt(2, id);
            cstmt.execute();
            rs = (ResultSet)cstmt.getObject(1);

            while (rs.next()) {
                m.setId_menu(rs.getInt("ID_MENU"));
                m.setNombre_menu(rs.getString("NOMBRE_MENU"));
                m.setDescripcion_menu(rs.getString("DESCRIPCION_MENU"));
                m.setValor_menu(rs.getInt("VALOR_MENU"));
                m.setTiempo_preparacion_minutos(rs.getInt("TIEMPO_PREPARACION_MINUTOS"));
                m.setImagen(rs.getString("IMAGEN"));
                m.setTipo_menu_id_tipo_menu(rs.getInt("TIPO_MENU_ID_TIPO_MENU"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            c.cerrarConexion(con);
        }
        return m;
    }
    public List<Carro> getCarroMenu(ArrayList<Carro> carroList) {
        List<Carro> menus = new ArrayList<>();
        try {
            if(carroList.size()>0){
                for(Carro item:carroList){
                    con=c.conectar();
                    CallableStatement cstmt = con.prepareCall("{? = call PKG_MENU_WEB.BUSCAR(?)}");
                    cstmt.registerOutParameter(1, OracleTypes.CURSOR);
                    cstmt.setInt(2, item.getId_menu());
                    cstmt.execute();
                    rs = (ResultSet)cstmt.getObject(1);
                    while(rs.next()){
                        Carro c = new Carro();
                        c.setId_menu(rs.getInt(1));
                        c.setNombre_menu(rs.getString(2));
                        c.setDescripcion_menu(rs.getString(3));
                        c.setValor_menu(rs.getInt(4)*item.getCantidad());
                        c.setTiempo_preparacion_minutos(rs.getInt(5));
                        c.setImagen(rs.getString(6));
                        c.setTipo_menu_id_tipo_menu(rs.getInt(7));

                        c.setCantidad(item.getCantidad());
                        menus.add(c);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return menus;
    };
    
    public int getTotalValorMenu(ArrayList<Carro> carroLista){
        int sum=0;
        
        try{
            if(carroLista.size()>0){
                for(Carro item:carroLista){
                    query = "select valor_menu from menu where id_menu=?";
                    ps = this.con.prepareStatement(query);
                    ps.setInt(1, item.getId_menu());
                    rs = ps.executeQuery();
                    
                    while(rs.next()){
                        sum+=rs.getInt("valor_menu")*item.getCantidad();
                    }
                }
            } 
        }catch(Exception e){
            e.printStackTrace(); 
        }
        
        return sum;
    }
}
