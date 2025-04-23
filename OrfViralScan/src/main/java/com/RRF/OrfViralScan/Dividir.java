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

public class Dividir {
    
    public static void cargarDividir(String entrada, String carpetaSalida){
        
        try{
        
            String sequence;

            sequence = "";
            FileReader f = new FileReader(entrada);
            BufferedReader b = new BufferedReader(f);

            String linea;

            String encabezado = "";
            
            while((linea = b.readLine()) != null) {

                if (Thread.currentThread().isInterrupted()) {

                    break;

                }

                if(linea.toCharArray()[0] != '>'){

                    sequence += linea;

                }else{
                    
                    encabezado = linea;
                    
                }

            }
                            
            b.close();
            
            int fragmentSize = 100000;
            int length = sequence.length();
            int numFragments = (length + fragmentSize - 1) / fragmentSize;

            int start;
            int end;
            
            FileWriter salidaArchivo;
            
            String fileName;
            
            for (int i = 0; i < numFragments; i++) {
            
                if (Thread.currentThread().isInterrupted()) {

                    break;

                }
                
                start = i * fragmentSize;

                end = Math.min(start + fragmentSize, length);
                String fragment = sequence.substring(start, end);

                fileName = carpetaSalida + "/fragment_" + (i + 1) + ".fasta";
            
                salidaArchivo = new FileWriter(fileName);
                
                salidaArchivo.write(encabezado + "\r\n");
                salidaArchivo.write(fragment);
                salidaArchivo.close();
                
            }

        }catch(IOException e){
            
            Logger.getLogger(Dividir.class.getName()).log(Level.SEVERE, null, e);
            jButton3.setEnabled(true);
            Cancelar_Hilos.cargarCancelarHilos();
            
        }
        
    }
    
}
