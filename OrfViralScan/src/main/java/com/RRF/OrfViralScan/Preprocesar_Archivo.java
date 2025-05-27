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
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Preprocesar_Archivo {
    
    public static void cargarPreprocesarArchivo(String entrada, String salida) {
        
        try{
        
            FileReader f = new FileReader(entrada);
            BufferedReader b = new BufferedReader(f);

            FileWriter salidaArchivo = new FileWriter(salida);

            String linea;

            boolean saltoInicial = false;

            while((linea = b.readLine()) != null) {

                if (Thread.currentThread().isInterrupted()) {

                    break;

                }
                
                if(linea.trim().isEmpty()){
                    
                    continue;
                    
                }
                
                linea = linea.toUpperCase();

                if(linea.toCharArray()[0] != '>'){

                    salidaArchivo.write(linea.trim());

                }else{

                    if(saltoInicial == true){

                        salidaArchivo.write("\r\n");

                    }else{

                        saltoInicial = true;

                    }

                    salidaArchivo.write(linea + "\r\n");

                }

            }

            salidaArchivo.close();
            b.close();
        
        }catch(IOException e){
            
            Logger.getLogger(Preprocesar_Archivo.class.getName()).log(Level.SEVERE, null, e);
            jButton3.setEnabled(true);    
            Cancelar_Hilos.cargarCancelarHilos();
            
        }
        
    }
    
}
