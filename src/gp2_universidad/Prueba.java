package gp2_universidad;


import entidades.Alumno;
import persistencia.AlumnoData;
import java.time.LocalDate;
import persistencia.miConexion;

public class Prueba {
    private AlumnoData alumnoData;
    private miConexion conexion;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        LocalDate fecha = LocalDate.now();
        Alumno estudioso = new Alumno(28180533, "Alejandro", LocalDate.now(),false); 
        new Prueba().conectar(estudioso);
        System.out.println("Alumno "+ estudioso.getNombre() + " guardado con exito");
    } 
    
    void conectar(Alumno estudioso){
           
           conexion = new miConexion(); 
        AlumnoData alumnoData = new AlumnoData(conexion); 

          alumnoData.guardarAlumno(estudioso);  

           alumnoData.guardarAlumno(estudioso);
           System.out.println("Datos: "+ estudioso);
    }
}
