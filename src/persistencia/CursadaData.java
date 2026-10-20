/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.Alumno;
import entidades.Cursada;
import entidades.Materia;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Connection;
import org.mariadb.jdbc.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author estef
 */
public class CursadaData {
    private Connection con = null;
    private Connection con = null;
    private MateriaData md=new MateriaData();
    private AlumnoData ad=new AlumnoData();
    
    public CursadaData(miConexion conexion) {
        this.con = (Connection) conexion.buscarConexion();
    }

    public void guardarCursada(Cursada cursa){
        String sql="INSERT INTO cursada(idAlumno, idMateria, nota) VALUES (?,?,?)";
        
        try {
        PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1,cursa.getAlumno().getIdAlumno());
        ps.setInt(2,cursa.getMateria().getIdMateria());
        ps.setFloat(3,cursa.getNota());
        ps.executeUpdate();
        ResultSet rs=ps.getGeneratedKeys();
        if(rs.next()) {
            
            cursa.setIdCursada(rs.getInt(1));
            JOptionPane.showMessageDialog(null, "Cursada registrada");
        }

        ps.close();
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla cursada");
        }
          
    }
    
    public void actualizarNota(int idAlumno, int idMateria, float nota) {
        String sql="UPDATE cursada SET nota = ? WHERE idAlumno = ? and idMateria = ?";


        try {
            
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setFloat(1, nota);
            ps.setInt(2, idAlumno);
            ps.setFloat(3, nota);
            
            int filas=ps.executeUpdate();
        
            if(filas>0) {
                JOptionPane.showMessageDialog(null, "Nota actualizada");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla cursada");
        }
        
    }
    
    public void borrarCursadaAlumno(int idAlumno, int idMateria) {
        
        String sql="DELETE FROM cursada WHERE idAlumno = ? and idMateria = ?";
        
        try {
            
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);
            
            int filas=ps.executeUpdate();
            
            if(filas>0) {
                JOptionPane.showMessageDialog(null, "Cursada borrada");
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla cursada");        }
        }
    
    public List<Cursada> obtenerCursadas() {
        
        ArrayList<Cursada> cursadas=new ArrayList<>();
            
        String sql="SELECT * FROM cursada";
        
        try {
            
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()) {
            
                Cursada cursa=new Cursada();
                cursa.setIdCursada(rs.getInt("idCursa"));
                Alumno alu=ad.buscarAlumno(rs.getInt("idAlumno"));
                Materia mat=md.buscarMateria(rs.getInt("idMateria"));
                cursa.setAlumno(alu);
                cursa.setMateria(mat);
                cursa.setNota(rs.getFloat("nota"));
                cursadas.add(cursa);
                
            }
            ps.close();
            
        } catch (SQLException ex) {
        
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla cursada");
            
        }
        return cursadas;
    }
    
    public List<Cursada> obtenerCursadasPorAlumno(int idAlumno) {
    
        ArrayList<Cursada> cursadas=new ArrayList<>();
            
        String sql="SELECT * FROM cursada WHERE idAlumno = ?";
        
        try {
            
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs=ps.executeQuery();
            while(rs.next()) {
            
                Cursada cursa=new Cursada();
                cursa.setIdCursada(rs.getInt("idCursa"));
                Alumno alu=ad.buscarAlumno(rs.getInt("idAlumno"));
                Materia mat=md.buscarMateria(rs.getInt("idMateria"));
                cursa.setAlumno(alu);
                cursa.setMateria(mat);
                cursa.setNota(rs.getFloat("nota"));
                cursadas.add(cursa);
                
            }
            ps.close();
            
        } catch (SQLException ex) {
        
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla cursada");
            
        }
        return cursadas;

    }
    
    public List<Materia> obtenerMateriasCuradas(int idAlumno) {
    
        ArrayList<Materia> materias=new ArrayList<>();
        
        String sql="SELECT cursada.idMateria, nombre FROM cursada," + 
                   " materia WHERE cursada.idMateria = materia.idMateria" +
                   " AND cursada.idAlumno = ?;";
        
        try {
            
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()) {
                
                Materia materia=new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materias.add(materia);
                
            }
            ps.close();
        
        } catch (SQLException ex) {
        
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla cursada");
            
        }
        return materias;
        
    }
    
    public List<Materia> obtenerMateriasNoCursadas(int idAlumno) {
    
        ArrayList<Materia> materias=new ArrayList<>();
                
        String sql="SELECT * FROM materia WHERE activo = 1 AND idMateria not in " + 
                   "(SELECT idMateria FROM cursada WHERE idAlumno = ?)";
        
        try {
            
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()) {
                
                Materia materia=new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materias.add(materia);
                
            }
            ps.close();
        
        } catch (SQLException ex) {
        
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla cursada");
            
        }
        return materias;
        
    }
    
    public List<Alumno> obtenerAlumnosPorMateria(int idMateria) {
    
        ArrayList<Alumno> alumnosMateria=new ArrayList<>();
        
        String sql="SELECT a.idAlumno, dni, nombre, fecNac, activo " +
                   "FROM cursada i, alumno a WHERE i.idAlumno AND idMateria = ? AND a.activo = 1";
        
        try {
            
            PreparedStatement ps=con.prepareCall(sql);
            ps.setInt(1, idMateria);
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()) {
            
                Alumno alumno=new Alumno();
                alumno.setId(rs.getInt("idAlumno"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFecNac(rs.getDate("fecNac").toLocalDate());
                alumno.setActivo(rs.getBoolean("activo"));
                alumnosMateria.add(alumno);
                
            }
            ps.close();
        
        } catch (SQLException ex) {
         
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla cursada");
            
        }
        return alumnosMateria;
        
    }
    
}
