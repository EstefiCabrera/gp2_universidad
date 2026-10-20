/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.Materia;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author crn70
 */
    public class MateriaData {
    
    private Connection con = null;
    
    public MateriaData(miConexion conexion){
        this.con = conexion.buscarConexion();
        
    }

    public MateriaData() {
    }
    
    public void guardarMateria(Materia m){
        String sql = "INSERT INTO materia(nombre, estado) VALUES (?,?)";  
        
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
            ps.setString(1,m.getNombre());
            ps.setBoolean(2, m.isEstado());
            ps.executeUpdate();     
            
            ResultSet rs = ps.getGeneratedKeys(); 
            if(rs.next()){
                m.setIdMateria(rs.getInt(1));
            System.out.println("Materia guardada con exito. ID: "+m.getIdMateria());
            }else
                System.out.println("No se pudo guardar materia");
            ps.close();
            System.out.println("Guardado!");
        } catch (SQLException ex) {
            System.out.println("Error al acceder a la tabla de materia:"+ex.getMessage());    
        }
    }
        
    public boolean actualizarMateria(Materia m){
        String query = "UPDATE materia SET nombre= ?, estado =? WHERE idMateria=?;";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, m.getNombre());
            ps.setBoolean(2, m.isEstado());
            ps.setInt(3, m.getIdMateria());
            
            int exito = ps.executeUpdate();
            
            ps.close();
            
            if(exito == 1){
                return true;
            }else{
                return false;
            }
            
        } catch (SQLException ex) {
            System.out.println("Error al actualizar"+ex.getMessage());
            return false;
        }
    }

    public Materia buscarMateria(int idMateria) throws SQLException{


   Materia m = null;

    String sql = "SELECT * FROM Materia WHERE idMateria = ?";

    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idMateria);

        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            m = new Materia();

            m.setIdMateria(rs.getInt("idMateria"));
            m.setNombre(rs.getString("nombre"));
            m.setEstado(rs.getBoolean("activo"));
        }

        ps.close();

    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
    }
 return m;
}



public void eliminarMateria(int idMateria){
        String query = "DELETE FROM materia WHERE idMateria = ?";
        
        try{
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idMateria);
            int exito = ps.executeUpdate();
            if(exito == 1) {
                System.out.println("La materia ha sido eliminada correctamente");
            }else{
                System.out.println("No se ha encontrado la materia con ese ID");
            }
            ps.close();
            
        }catch(SQLException ex){
            System.out.println("Ah ocurrido un error");
        }
    }


}
        

