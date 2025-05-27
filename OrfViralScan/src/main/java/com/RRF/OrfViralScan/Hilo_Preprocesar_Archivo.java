/**
 * «Copyright 2025 Roberto Reinosa Fernández»
 * 
 * This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 **/

package com.RRF.OrfViralScan;

import static com.RRF.OrfViralScan.Interfaz.jButton3;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Hilo_Preprocesar_Archivo implements Runnable {
    
    public static Thread t_Preprocesar_Archivo;
    
    public static String entrada;
    public static String salida;
    
    public Hilo_Preprocesar_Archivo(String entrada, String salida){
        
        Hilo_Preprocesar_Archivo.entrada = entrada;
        Hilo_Preprocesar_Archivo.salida = salida;
        
        t_Preprocesar_Archivo = new Thread(this, "Hilo_Busqueda_ORFs"); 
        t_Preprocesar_Archivo.start();
        
    }
    
    @Override
    public void run(){
        
        try {
            
            Preprocesar_Archivo.cargarPreprocesarArchivo(Hilo_Preprocesar_Archivo.entrada, Hilo_Preprocesar_Archivo.salida);
            
            jButton3.setEnabled(true);
            
        } catch (Exception e) {
            
            Logger.getLogger(Hilo_Preprocesar_Archivo.class.getName()).log(Level.SEVERE, null, e);
            jButton3.setEnabled(true);
        }
        
    }
    
}
