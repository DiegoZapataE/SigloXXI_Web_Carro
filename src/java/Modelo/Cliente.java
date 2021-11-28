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
public class Cliente {
    int rut_cliente;
    String digito_verificador_cliente;
    String clave_cliente;
    String nombre_cliente;
    String papellido_cliente;
    String sapellido_cliente;
    int telefono_cliente;
    String direccion_cliente;
    String email_cliente;
    String estado_cliente;
    int roles_id_rol;
    
    public Cliente(){

    }

    public Cliente(int rut_cliente, String digito_verificador_cliente, String clave_cliente, String nombre_cliente, String papellido_cliente, String sapellido_cliente, int telefono_cliente, String direccion_cliente, String email_cliente, String estado_cliente, int roles_id_rol) {
        this.rut_cliente = rut_cliente;
        this.digito_verificador_cliente = digito_verificador_cliente;
        this.clave_cliente = clave_cliente;
        this.nombre_cliente = nombre_cliente;
        this.papellido_cliente = papellido_cliente;
        this.sapellido_cliente = sapellido_cliente;
        this.telefono_cliente = telefono_cliente;
        this.direccion_cliente = direccion_cliente;
        this.email_cliente = email_cliente;
        this.estado_cliente = estado_cliente;
        this.roles_id_rol = roles_id_rol;
    }

    public int getRut_cliente() {
        return rut_cliente;
    }

    public void setRut_cliente(int rut_cliente) {
        this.rut_cliente = rut_cliente;
    }

    public String getDigito_verificador_cliente() {
        return digito_verificador_cliente;
    }

    public void setDigito_verificador_cliente(String digito_verificador_cliente) {
        this.digito_verificador_cliente = digito_verificador_cliente;
    }

    public String getClave_cliente() {
        return clave_cliente;
    }

    public void setClave_cliente(String clave_cliente) {
        this.clave_cliente = clave_cliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getPapellido_cliente() {
        return papellido_cliente;
    }

    public void setPapellido_cliente(String papellido_cliente) {
        this.papellido_cliente = papellido_cliente;
    }

    public String getSapellido_cliente() {
        return sapellido_cliente;
    }

    public void setSapellido_cliente(String sapellido_cliente) {
        this.sapellido_cliente = sapellido_cliente;
    }

    public int getTelefono_cliente() {
        return telefono_cliente;
    }

    public void setTelefono_cliente(int telefono_cliente) {
        this.telefono_cliente = telefono_cliente;
    }

    public String getDireccion_cliente() {
        return direccion_cliente;
    }

    public void setDireccion_cliente(String direccion_cliente) {
        this.direccion_cliente = direccion_cliente;
    }

    public String getEmail_cliente() {
        return email_cliente;
    }

    public void setEmail_cliente(String email_cliente) {
        this.email_cliente = email_cliente;
    }

    public String getEstado_cliente() {
        return estado_cliente;
    }

    public void setEstado_cliente(String estado_cliente) {
        this.estado_cliente = estado_cliente;
    }

    public int getRoles_id_rol() {
        return roles_id_rol;
    }

    public void setRoles_id_rol(int roles_id_rol) {
        this.roles_id_rol = roles_id_rol;
    }

    
}
