/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.sql.Connection;

/**
 *
 * @author crn70
 */
public class MateriaData {
    
    private Connection con = null;
    
    public MateriaData(miConexion conexion){
        this.con = conexion.buscarConexion();
    }
    
}
