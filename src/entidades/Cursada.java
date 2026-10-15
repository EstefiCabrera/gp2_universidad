/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;


/**
 *
 * @author estef
 */
public class Cursada {
    
    private int idCursada = -1;
    private Alumno alumno;
    private Materia materia;
    private float nota;
    private float asistencia;
    private int cursa;
    private int cuatrimestre;

    public Cursada(Alumno alumno, Materia materia, float nota, float asistencia, int cursa, int cuatrimestre) {
        this.alumno = alumno;
        this.materia = materia;
        this.nota = nota;
        this.asistencia = asistencia;
        this.cursa = cursa;
        this.cuatrimestre = cuatrimestre;
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

    public void setAsistencia(float asistencia) {
        this.asistencia = asistencia;
    }

    public int getCursa() {
        return cursa;
    }

    public void setCursa(int cursa) {
        this.cursa = cursa;
    }

    public int getCuatrimestre() {
        return cuatrimestre;
    }

    public void setCuatrimestre(int cuatrimestre) {
        this.cuatrimestre = cuatrimestre;
    }

    
}
