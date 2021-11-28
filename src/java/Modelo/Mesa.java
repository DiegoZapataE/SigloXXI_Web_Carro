/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Felipe Fuentes
 */
public class Mesa {
    int id_mesa;
    int estado_mesa_id_estado_mesa;
    int clientes_rut_cliente;
    
    public Mesa(){

    }

    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }

    public int getEstado_mesa_id_estado_mesa() {
        return estado_mesa_id_estado_mesa;
    }

    public void setEstado_mesa_id_estado_mesa(int estado_mesa_id_estado_mesa) {
        this.estado_mesa_id_estado_mesa = estado_mesa_id_estado_mesa;
    }

    public int getClientes_rut_cliente() {
        return clientes_rut_cliente;
    }

    public void setClientes_rut_cliente(int clientes_rut_cliente) {
        this.clientes_rut_cliente = clientes_rut_cliente;
    }
    
}
