/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.time.LocalDate;

/**
 *
 * @author estef
 */
public class Cursada {
    
    private int idCursada = -1;
    private float nota;
    private float asistencia;
    private LocalDate cursa;

    public Cursada(float nota, float asistencia, LocalDate cursa) {
        this.nota = nota;
        this.asistencia = asistencia;
        this.cursa = cursa;
    }

    public Cursada() {
    }
    
    public int getIdCursada() {
        return idCursada;
    }

    public void setIdCursada(int idCursada) {
        this.idCursada = idCursada;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public float getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(float asistencia) {
        this.asistencia = asistencia;
    }

    public LocalDate getCursa() {
        return cursa;
    }

    public void setCursa(LocalDate cursa) {
        this.cursa = cursa;
    }
    
    
    
}
