package persistencia;

import entidades.Alumno;
import java.sql.Connection;


import java.sql.Date;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class AlumnoData{
    private Connection con = null;
    
    public AlumnoData(miConexion conexion){
        this.con = conexion.buscarConexion();
    }
    
    public void guardarAlumno(Alumno a){    
        String sql = "INSERT INTO alumno(dni, nombre, fecNac, activo) VALUES (?,?,?,?)";  
        
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
            ps.setInt(1,a.getDni());
            ps.setString(2, a.getNombre());
            ps.setDate(3, Date.valueOf(a.getFecNac()));
            ps.setBoolean(4, a.isActivo());
            ps.executeUpdate();     
            
            ResultSet rs = ps.getGeneratedKeys(); 
            if(rs.next())
                a.setId(rs.getInt(1));
            else
                System.out.println("No se pudo tener ID");
            ps.close();
            System.out.println("Guardado!");
        } catch (SQLException ex) {
            System.out.println("No se puede insertar");    
        }
    }
    
    public Alumno buscarAlumno(int dni){
      Alumno a= null;
      String sql = "SELECT * FROM alumno WHERE dni= ?";
      
      PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, dni);
            ResultSet rs= ps.executeQuery();
            while (rs.next()) {  
                a=new Alumno();
                a.setId(rs.getInt("idAlumno"));
                a.setDni(rs.getInt("dni"));
                a.setNombre(rs.getString("nombre"));
                a.setFecNac(rs.getDate("fecNac").toLocalDate());
                a.setActivo(rs.getBoolean("activo"));
            }
            ps.close(); 
            
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a; 
    } 
    
    
    public void actualizarAlumno(Alumno a){
        String query = "UPDATE alumno SET dni= ? WHERE idAlumno=?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, a.getDni());
            ps.setInt(2, a.getIdAlumno());
            //ps.executeUpdate();
            int exito = ps.executeUpdate();
            if(exito == 1){
            System.out.println("DNI actualizado exitosamente");
            }else{
            System.out.println("No se encontró el alumno con ese ID");
            }
            ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    } 
    
  
}

