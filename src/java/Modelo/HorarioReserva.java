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
public class HorarioReserva {
    int id_horario_reserva;
    String horario_reserva;
    
    public HorarioReserva(){
        
    }

    public HorarioReserva(int id_horario_reserva, String horario_reserva) {
        this.id_horario_reserva = id_horario_reserva;
        this.horario_reserva = horario_reserva;
    }

    public int getId_horario_reserva() {
        return id_horario_reserva;
    }

    public void setId_horario_reserva(int id_horario_reserva) {
        this.id_horario_reserva = id_horario_reserva;
    }

    public String getHorario_reserva() {
        return horario_reserva;
    }

    public void setHorario_reserva(String horario_reserva) {
        this.horario_reserva = horario_reserva;
    }
    
}
