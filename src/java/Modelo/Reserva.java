
package Modelo;

import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;

public class Reserva {
    int id_reserva;
    Date fecha_registro;
    Date fecha_reserva;
    int rut_solicitante;
    int mesas_id_mesa;
    int horario_reservas_id_horario_reserva;
    String horario_reserva;
    
    public Reserva(){
        
    }

    public Reserva(int id_reserva, Date fecha_registro, Date fecha_reserva, int rut_solicitante, int mesas_id_mesa, int horario_reservas_id_horario_reserva, String horario_reserva) {
        this.id_reserva = id_reserva;
        this.fecha_registro = fecha_registro;
        this.fecha_reserva = fecha_reserva;
        this.rut_solicitante = rut_solicitante;
        this.mesas_id_mesa = mesas_id_mesa;
        this.horario_reservas_id_horario_reserva = horario_reservas_id_horario_reserva;
        this.horario_reserva = horario_reserva;
    }

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public Date getFecha_reserva() {
        return fecha_reserva;
    }

    public void setFecha_reserva(Date fecha_reserva) {
        this.fecha_reserva = fecha_reserva;
    }

    public int getRut_solicitante() {
        return rut_solicitante;
    }

    public void setRut_solicitante(int rut_solicitante) {
        this.rut_solicitante = rut_solicitante;
    }

    public int getMesas_id_mesa() {
        return mesas_id_mesa;
    }

    public void setMesas_id_mesa(int mesas_id_mesa) {
        this.mesas_id_mesa = mesas_id_mesa;
    }

    public int getHorario_reservas_id_horario_reserva() {
        return horario_reservas_id_horario_reserva;
    }

    public void setHorario_reservas_id_horario_reserva(int horario_reservas_id_horario_reserva) {
        this.horario_reservas_id_horario_reserva = horario_reservas_id_horario_reserva;
    }

    public String getHorario_reserva() {
        return horario_reserva;
    }

    public void setHorario_reserva(String horario_reserva) {
        this.horario_reserva = horario_reserva;
    }

}
