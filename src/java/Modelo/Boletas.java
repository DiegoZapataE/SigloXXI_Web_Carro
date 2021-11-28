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
public class Boletas {
    
    int id_boleta;
    String detalle_boleta;
    int valor_boleta;
    Date fecha_boleta;
    int tipo_pago_id_tipo_pago;
    int estado_boleta_id_estado_boleta;
    String rut;
    
    public Boletas() {

    }

    public Boletas(int id_boleta, String detalle_boleta, int valor_boleta, Date fecha_boleta, int tipo_pago_id_tipo_pago, int estado_boleta_id_estado_boleta, String rut) {
        this.id_boleta = id_boleta;
        this.detalle_boleta = detalle_boleta;
        this.valor_boleta = valor_boleta;
        this.fecha_boleta = fecha_boleta;
        this.tipo_pago_id_tipo_pago = tipo_pago_id_tipo_pago;
        this.estado_boleta_id_estado_boleta = estado_boleta_id_estado_boleta;
        this.rut = rut;
    }

    public int getId_boleta() {
        return id_boleta;
    }

    public void setId_boleta(int id_boleta) {
        this.id_boleta = id_boleta;
    }

    public String getDetalle_boleta() {
        return detalle_boleta;
    }

    public void setDetalle_boleta(String detalle_boleta) {
        this.detalle_boleta = detalle_boleta;
    }

    public int getValor_boleta() {
        return valor_boleta;
    }

    public void setValor_boleta(int valor_boleta) {
        this.valor_boleta = valor_boleta;
    }

    public Date getFecha_boleta() {
        return fecha_boleta;
    }

    public void setFecha_boleta(Date fecha_boleta) {
        this.fecha_boleta = fecha_boleta;
    }

    public int getTipo_pago_id_tipo_pago() {
        return tipo_pago_id_tipo_pago;
    }

    public void setTipo_pago_id_tipo_pago(int tipo_pago_id_tipo_pago) {
        this.tipo_pago_id_tipo_pago = tipo_pago_id_tipo_pago;
    }

    public int getEstado_boleta_id_estado_boleta() {
        return estado_boleta_id_estado_boleta;
    }

    public void setEstado_boleta_id_estado_boleta(int estado_boleta_id_estado_boleta) {
        this.estado_boleta_id_estado_boleta = estado_boleta_id_estado_boleta;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }
    
}
