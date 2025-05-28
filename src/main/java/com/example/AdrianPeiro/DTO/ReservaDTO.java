//package com.example.AdrianPeiro.DTO;
//
//import com.example.AdrianPeiro.Modelo.Reserva;
//
//public class ReservaDTO {
//    private int id;
//    private int pistaId;
//    private int usuarioId;
//    private String usuarioNombre;
//    private String pistaNombre;
//    private String fecha;
//    private String horaInicio;
//    private String horaFin;
//
//
//    // getters y setters
//    public ReservaDTO(Reserva reserva) {
//        this.id = reserva.getId();
//        this.usuarioId = reserva.getUsuarioId();
//        this.usuarioNombre = reserva.getUsuarioNombre();
//        this.pistaId = reserva.getPistaId();
//        this.pistaNombre = reserva.getPistaNombre(); // método @Transient en Reserva
//        this.fecha = reserva.getFecha();
//        this.horaInicio = reserva.getHoraInicio();
//        this.horaFin = reserva.getHoraFin();
//
//    }
//
//    public ReservaDTO(int id, int usuarioId, String usuarioNombre, int pistaId, String pistaNombre, String fecha, String horaInicio, String horaFin) {
//    }
//
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public int getUsuarioId() {
//        return usuarioId;
//    }
//
//    public void setUsuarioId(int usuarioId) {
//        this.usuarioId = usuarioId;
//    }
//
//    public String getUsuarioNombre() {
//        return usuarioNombre;
//    }
//
//    public void setUsuarioNombre(String usuarioNombre) {
//        this.usuarioNombre = usuarioNombre;
//    }
//
//    public String getPistaNombre() {
//        return pistaNombre;
//    }
//
//    public void setPistaNombre(String pistaNombre) {
//        this.pistaNombre = pistaNombre;
//    }
//
//    public String getFecha() {
//        return fecha;
//    }
//
//    public void setFecha(String fecha) {
//        this.fecha = fecha;
//    }
//
//    public String getHoraInicio() {
//        return horaInicio;
//    }
//
//    public void setHoraInicio(String horaInicio) {
//        this.horaInicio = horaInicio;
//    }
//
//    public String getHoraFin() {
//        return horaFin;
//    }
//
//    public void setHoraFin(String horaFin) {
//        this.horaFin = horaFin;
//    }
//
//    public int getPistaId() {
//        return pistaId;
//    }
//
//    public void setPistaId(int pistaId) {
//        this.pistaId = pistaId;
//    }
//}
