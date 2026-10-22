/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.Cursada;
import entidades.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author estef
 */
public class CursadaData {
    private Connection con = null;
    
    public CursadaData(miConexion conexion){
        this.con = conexion.buscarConexion(); 
    }
    
    public void guardarInscripcion(Cursada insc){
        String sql = "INSERT INTO cursada (idAlumno, idMateria, nota, asist, cursa) VALUES (?,?,?,?,?)";
        try{
            PreparedStatement ps= con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, insc.getAlumno().getIdAlumno());
            ps.setInt(2, insc.getMateria().getIdMateria());
            ps.setFloat(3, insc.getNota());
            ps.setFloat(4, insc.getAsist());
            ps.setInt(5, insc.getCursa());
            
            ps.executeUpdate();
            ResultSet rs= ps.getGeneratedKeys();
            if(rs.next()){
                insc.setIdCursada(rs.getInt(1));
                System.out.println("Inscripcion realizada con exito. ID: "+insc.getIdCursada());
            }
            ps.close();
        }catch (SQLException e){
            System.out.println("Error al guardar inscripcion: "+ e.getMessage());
        }
            
    }
    
      public void anularInscripcion(Cursada insc){
        String sql = "DELETE FROM cursada WHERE idAlumno =? AND idMateria=?";
        try{
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setInt(1, insc.getAlumno().getIdAlumno());
            ps.setInt(2, insc.getMateria().getIdMateria());
            
            int filas = ps.executeUpdate();
            if(filas >0){
                System.out.println("Inscripcion anulada con exito");
            }
            ps.close();
        }catch (SQLException e){
            System.out.println("Error al anular inscripcion: "+ e.getMessage());
        }
            
    }

      public List <Materia> materiasInscriptas(int idAlumno){
        ArrayList <Materia> materias = new ArrayList<>();
        String sql = "SELECT m.idMateria, m.nombre, m.estado FROM cursada c " 
                + "JOIN materia m ON (c.idMateria = m.idMateria) WHERE c.idAlumno=?";
        try{
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
            Materia mat = new Materia();
            mat.setIdMateria(rs.getInt("idMateria"));
            mat.setNombre(rs.getString("nombre"));
            mat.setEstado(rs.getBoolean("estado"));
            materias.add(mat);
        }
            ps.close();
        }catch (SQLException e){
            System.out.println("Error al obtener materias cursadas. "+e.getMessage());
    }
        return materias;
      }
    
      public List <Materia> materiasNoInscriptas(int idAlumno){
        ArrayList <Materia> materias = new ArrayList<>();
        String sql = "SELECT * FROM materia WHERE estado = 1 AND idMateria NOT IN "
                + "(SELECT COALESCE (idMateria, 0) FROM cursada WHERE idAlumno =?)";
        try{
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
            Materia mat = new Materia();
            mat.setIdMateria(rs.getInt("idMateria"));
            mat.setNombre(rs.getString("nombre"));
            mat.setEstado(rs.getBoolean("estado"));
            materias.add(mat);
        }
            ps.close();
        }catch (SQLException e){
            System.out.println("Error al obtener materias no cursadas. "+e.getMessage());
    }
        return materias;
      }
      
 }