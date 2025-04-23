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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Hilo_Rastreador_ORF implements Runnable {
    
    public static Thread t_Rastreador_ORF;
    
    public static String entrada;
    public static String salida;
    public static int longitudMinima;
    public static int longitudMaxima;
    public static int regionInferior;
    public static int regionSuperior;
    public static int orientacion;
    public static int selectorTipoSecuencia;
    
    public Hilo_Rastreador_ORF(String entrada, String salida, int longitudMinima, int longitudMaxima, int regionInferior, int regionSuperior, int orientacion, int selectorTipoSecuencia){
        
        Hilo_Rastreador_ORF.entrada = entrada;
        Hilo_Rastreador_ORF.salida = salida;
        Hilo_Rastreador_ORF.longitudMinima = longitudMinima;
        Hilo_Rastreador_ORF.longitudMaxima = longitudMaxima;
        Hilo_Rastreador_ORF.regionInferior = regionInferior;
        Hilo_Rastreador_ORF.regionSuperior = regionSuperior;
        Hilo_Rastreador_ORF.orientacion = orientacion;
        Hilo_Rastreador_ORF.selectorTipoSecuencia = selectorTipoSecuencia;
        
        
        t_Rastreador_ORF = new Thread(this, "Hilo_Busqueda_ORFs"); 
        t_Rastreador_ORF.start();
        
    }
    
    @Override
    public void run(){
        
        try {
            
            Rastreador_ORF.cargarRastreadorORF(Hilo_Rastreador_ORF.entrada, Hilo_Rastreador_ORF.salida, Hilo_Rastreador_ORF.longitudMinima, Hilo_Rastreador_ORF.longitudMaxima, 
                    Hilo_Rastreador_ORF.regionInferior, Hilo_Rastreador_ORF.regionSuperior, Hilo_Rastreador_ORF.orientacion, Hilo_Rastreador_ORF.selectorTipoSecuencia);
            
            jButton3.setEnabled(true);
            
        } catch (IOException ex) {
            
            Logger.getLogger(Hilo_Rastreador_ORF.class.getName()).log(Level.SEVERE, null, ex);
            jButton3.setEnabled(true);
            
        }
        
    }
    
}
