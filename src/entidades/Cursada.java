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
    
    private int idCursada;
    private Alumno alumno;
    private Materia materia;
    private float nota;
    private float asistencia;
    private LocalDate cursa;

    public Cursada(int idCursada, Alumno alumno, Materia materia, float nota, float asistencia, LocalDate cursa) {
        this.idCursada = idCursada;
        this.alumno = alumno;
        this.materia = materia;
        this.nota = nota;
        this.asistencia = asistencia;
        this.cursa = cursa;
    }
    
    public Cursada(Alumno alumno, Materia materia, float nota, float asistencia, LocalDate cursa) {
        this.alumno = alumno;
        this.materia = materia;
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

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
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

    public void setAsist(float asistencia) {
        this.asistencia = asistencia;
    }

    public LocalDate getCursa() {
        return cursa;
    }

    public void setCursa(LocalDate cursa) {
        this.cursa = cursa;
    }

    @Override
    public String toString() {
        String insc=idCursada+" "+alumno.getIdAlumno()+", "+alumno.getDni()+", "+alumno.getNombre()+", "+alumno.isActivo()+", "+materia.getNombre();
        return insc;
    }

}
