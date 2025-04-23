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

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Cancelar_Hilos {
    
    public static void cargarCancelarHilos(){
        
        try{
           
            if(Hilo_Preprocesar_Archivo.t_Preprocesar_Archivo.isAlive()){
            
                Hilo_Preprocesar_Archivo.t_Preprocesar_Archivo.interrupt();
            
            }
            
        }catch(Exception e){}
        
        try{
           
            if(Hilo_Rastreador_ORF.t_Rastreador_ORF.isAlive()){
            
                Hilo_Rastreador_ORF.t_Rastreador_ORF.interrupt();
            
            }
            
        }catch(Exception e){}
        
        try{
           
            if(Hilo_Busqueda_ORFs.t_Busqueda_ORFs.isAlive()){
            
                Hilo_Busqueda_ORFs.t_Busqueda_ORFs.interrupt();
            
            }
            
        }catch(Exception e){}
        
        try{
           
            if(Hilo_Dividir.t_Dividir.isAlive()){
            
                Hilo_Dividir.t_Dividir.interrupt();
            
            }
            
        }catch(Exception e){}
        
    }
    
}
