/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author diego
 */
public class Menu {
    int id_menu;
    String nombre_menu;
    String descripcion_menu;
    int valor_menu;
    int tiempo_preparacion_minutos;
    String imagen;
    int tipo_menu_id_tipo_menu;
    
    public Menu() {
        
    }

    public Menu(int id_menu, String nombre_menu, String descripcion_menu, int valor_menu, int tiempo_preparacion_minutos, String imagen, int tipo_menu_id_tipo_menu) {
        super();
        this.id_menu = id_menu;
        this.nombre_menu = nombre_menu;
        this.descripcion_menu = descripcion_menu;
        this.valor_menu = valor_menu;
        this.tiempo_preparacion_minutos = tiempo_preparacion_minutos;
        this.imagen = imagen;
        this.tipo_menu_id_tipo_menu = tipo_menu_id_tipo_menu;
    }

    public int getId_menu() {
        return id_menu;
    }

    public void setId_menu(int id_menu) {
        this.id_menu = id_menu;
    }

    public String getNombre_menu() {
        return nombre_menu;
    }

    public void setNombre_menu(String nombre_menu) {
        this.nombre_menu = nombre_menu;
    }

    public String getDescripcion_menu() {
        return descripcion_menu;
    }

    public void setDescripcion_menu(String descripcion_menu) {
        this.descripcion_menu = descripcion_menu;
    }

    public int getValor_menu() {
        return valor_menu;
    }

    public void setValor_menu(int valor_menu) {
        this.valor_menu = valor_menu;
    }

    public int getTiempo_preparacion_minutos() {
        return tiempo_preparacion_minutos;
    }

    public void setTiempo_preparacion_minutos(int tiempo_preparacion_minutos) {
        this.tiempo_preparacion_minutos = tiempo_preparacion_minutos;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getTipo_menu_id_tipo_menu() {
        return tipo_menu_id_tipo_menu;
    }

    public void setTipo_menu_id_tipo_menu(int tipo_menu_id_tipo_menu) {
        this.tipo_menu_id_tipo_menu = tipo_menu_id_tipo_menu;
    }

    
    
}
