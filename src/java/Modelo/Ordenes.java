/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Date;

/**
 *
 * @author diego
 */
public class Ordenes{
    int id_orden;
    Date fecha_orden;
    int estado_orden_id_estado_orden;
    int mesas_id_mesas;
    int menu_id_menu;
    int boleta_id_boleta;
    int usuarios_rut_usuario;
    
    public Ordenes(){
        
    }

    public Ordenes(int id_orden, Date fecha_orden, int estado_orden_id_estado_orden, int mesas_id_mesas, int menu_id_menu, int boleta_id_boleta, int usuarios_rut_usuario) {
        this.id_orden = id_orden;
        this.fecha_orden = fecha_orden;
        this.estado_orden_id_estado_orden = estado_orden_id_estado_orden;
        this.mesas_id_mesas = mesas_id_mesas;
        this.menu_id_menu = menu_id_menu;
        this.boleta_id_boleta = boleta_id_boleta;
        this.usuarios_rut_usuario = usuarios_rut_usuario;
    }

    public int getId_orden() {
        return id_orden;
    }

    public void setId_orden(int id_orden) {
        this.id_orden = id_orden;
    }

    public Date getFecha_orden() {
        return fecha_orden;
    }

    public void setFecha_orden(Date fecha_orden) {
        this.fecha_orden = fecha_orden;
    }

    public int getEstado_orden_id_estado_orden() {
        return estado_orden_id_estado_orden;
    }

    public void setEstado_orden_id_estado_orden(int estado_orden_id_estado_orden) {
        this.estado_orden_id_estado_orden = estado_orden_id_estado_orden;
    }

    public int getMesas_id_mesas() {
        return mesas_id_mesas;
    }

    public void setMesas_id_mesas(int mesas_id_mesas) {
        this.mesas_id_mesas = mesas_id_mesas;
    }

    public int getMenu_id_menu() {
        return menu_id_menu;
    }

    public void setMenu_id_menu(int menu_id_menu) {
        this.menu_id_menu = menu_id_menu;
    }

    public int getBoleta_id_boleta() {
        return boleta_id_boleta;
    }

    public void setBoleta_id_boleta(int boleta_id_boleta) {
        this.boleta_id_boleta = boleta_id_boleta;
    }

    public int getUsuarios_rut_usuario() {
        return usuarios_rut_usuario;
    }

    public void setUsuarios_rut_usuario(int usuarios_rut_usuario) {
        this.usuarios_rut_usuario = usuarios_rut_usuario;
    }

    
}
