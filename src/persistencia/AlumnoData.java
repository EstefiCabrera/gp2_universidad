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
}

