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
import javax.swing.JOptionPane;

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
                 JOptionPane.showMessageDialog(null, "Inscripcion realizada con exito. ID: "+insc.getIdCursada());
            }
            ps.close();
        }catch (SQLException e){
             JOptionPane.showMessageDialog(null, "Error al guardar inscripcion: "+ e.getMessage());
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
                 JOptionPane.showMessageDialog(null, "Inscripcion anulada con exito");
            }
            ps.close();
        }catch (SQLException e){
             JOptionPane.showMessageDialog(null, "Error al anular inscripcion: "+ e.getMessage());
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
             JOptionPane.showMessageDialog(null, "Error al obtener materias cursadas. "+e.getMessage());
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
             JOptionPane.showMessageDialog(null, "Error al obtener materias no cursadas. "+e.getMessage());
    }
        return materias;
      }
      
      public List<Cursada> obtenerNotasPorAlumno(int idAlumno){
          ArrayList<Cursada> lista= new ArrayList<>();
          
          String sql= "SELECT c.idCursada, c.nota, m.idMateria, m.nombre "
                  + "FROM cursada c "
                  + "JOIN materia m ON (c.idMateria = m.idMateria) "
                  + "WHERE c.idAlumno=?";
           try{
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs= ps.executeQuery();
            
            while(rs.next()){
                Cursada cur = new Cursada();
                cur.setIdCursada(rs.getInt("idCursada"));
                cur.setNota(rs.getFloat("nota"));
                
                Materia mat = new Materia();
                mat.setIdMateria(rs.getInt("idMateria"));
                mat.setNombre(rs.getString("nombre"));
                cur.setMateria(mat);
                lista.add(cur);
        }
            ps.close();
        }catch (SQLException e){
             JOptionPane.showMessageDialog(null, "Error al obtenernotas: "+e.getMessage());
    }
        return lista;
      }
              
         
      public void actualizarNota(int idAlumno, int idMateria, double nota){
          String sql = "UPDATE cursada SET nota =? WHERE idAlumno =? AND idMateria =?";
          try{
              PreparedStatement ps= con.prepareStatement(sql);
              ps.setDouble(1, nota);
              ps.setInt(2, idAlumno);
              ps.setInt(3, idMateria);
              
              int exito = ps.executeUpdate();
              if(exito == 0){
                   JOptionPane.showMessageDialog(null, "No se actualizó la nota");
              }
              ps.close();
          }catch (SQLException e){
                   JOptionPane.showMessageDialog(null, "Error al actualizar la nota: "+e.getMessage());
          }
      }
 }