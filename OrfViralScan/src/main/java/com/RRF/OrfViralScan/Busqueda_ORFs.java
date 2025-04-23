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
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Busqueda_ORFs {
    
    public static final int alcance = 2000000;
    public static final int alcance_Marco = 3;
	
    public static String sec_Nucl1[] = new String[alcance];
    public static String sec_Nucl2[] = new String[alcance];
    public static String sec_Nucl3[] = new String[alcance];
	
    public static String sec_aa1[] = new String[alcance];
    public static String sec_aa2[] = new String[alcance];
    public static String sec_aa3[] = new String[alcance];
	
    public static String sec_Nucl1Antiparalela[] = new String[alcance];
    public static String sec_Nucl2Antiparalela[] = new String[alcance];
    public static String sec_Nucl3Antiparalela[] = new String[alcance];
	
    public static String sec_aa1Antiparalela[] = new String[alcance];
    public static String sec_aa2Antiparalela[] = new String[alcance];
    public static String sec_aa3Antiparalela[] = new String[alcance];
		
    public static String cadena1Global;
    public static String cadena2Global;
    public static DecimalFormat df = new DecimalFormat("#.00");
    
    public static String salidaFinal;
    
    public static int contadorGlobalID;
    
    public static void cargarBusquedaORFs(String entrada, String salida, int orientacion) {
		
        try{
        
            salidaFinal = "";

            String coleccion1;

            coleccion1 = "";
            FileReader f = new FileReader(entrada);
            BufferedReader b = new BufferedReader(f);

            FileWriter salidaArchivo = new FileWriter(salida);

            String linea;

            while((linea = b.readLine()) != null) {

                if (Thread.currentThread().isInterrupted()) {

                    break;

                }
                
                if(linea.toCharArray()[0] != '>'){

                    coleccion1 += linea;

                }

            }

            String coleccion2 = coleccion1;
            cadena1Global = coleccion1;
            coleccion1 = coleccion1.concat("^^^^^^");

            char cadena[] = coleccion1.toCharArray();

            Convertidor_ARN(cadena);

            coleccion2 = new StringBuilder(coleccion2).reverse().toString();

            String coleccion2Antiparalela = "";

            for(int i = 0; i < coleccion2.length(); i++) {

                if (Thread.currentThread().isInterrupted()) {

                    break;

                }
                
                if(coleccion2.toCharArray()[i] == 'A') {

                    coleccion2Antiparalela += "T";

                }else if(coleccion2.toCharArray()[i] == 'C') {

                    coleccion2Antiparalela += 'G';

                }else if(coleccion2.toCharArray()[i] == 'T') {

                    coleccion2Antiparalela += "A";

                }else if(coleccion2.toCharArray()[i] == 'G') {

                    coleccion2Antiparalela += "C";

                }else {

                    coleccion2Antiparalela += "?";

                }

            }

            coleccion2 = coleccion2Antiparalela;
            cadena2Global = coleccion2;
            coleccion2 = coleccion2.concat("^^^^^^");

            char cadena2[] = coleccion2.toCharArray();

            Convertidor_ARN(cadena2);

            for(int i = 0; i < sec_Nucl1.length; i++){
                
                if (Thread.currentThread().isInterrupted()) {

                    break;

                }
                
                sec_Nucl1[i]="Sequence = ";
                sec_Nucl2[i]="Sequence = ";
                sec_Nucl3[i]="Sequence = ";

                sec_aa1[i]="Sequence = ";
                sec_aa2[i]="Sequence = ";
                sec_aa3[i]="Sequence = ";

                sec_Nucl1Antiparalela[i]="Sequence = ";
                sec_Nucl2Antiparalela[i]="Sequence = ";
                sec_Nucl3Antiparalela[i]="Sequence = ";

                sec_aa1Antiparalela[i]="Sequence = ";
                sec_aa2Antiparalela[i]="Sequence = ";
                sec_aa3Antiparalela[i]="Sequence = ";

            }

            salidaFinal += "Analysis Results: " + "\r\n\r\n";
            salidaFinal += "Nucleotide Sequences: " + "\r\n\r\n";

            contadorGlobalID = 0;

            if(orientacion == 1 || orientacion == 3) {

                salidaFinal += "Frame 1: " + "\r\n\r\n"; 
                Identificador_Secuencias_Nucleotidos(cadena, sec_Nucl1, 0, 0);			

                salidaFinal += "Frame 2: " + "\r\n\r\n";
                Identificador_Secuencias_Nucleotidos(cadena, sec_Nucl2, 1, 1);

                salidaFinal += "Frame 3: " + "\r\n\r\n";
                Identificador_Secuencias_Nucleotidos(cadena, sec_Nucl3, 2, 2);

            }

            if(orientacion == 2 || orientacion == 3) {

                salidaFinal += "Antiparallel Frame 1: " + "\r\n\r\n";
                Identificador_Secuencias_Nucleotidos(cadena2, sec_Nucl1Antiparalela, 0, 0);			

                salidaFinal += "Antiparallel Frame 2: " + "\r\n\r\n";
                Identificador_Secuencias_Nucleotidos(cadena2, sec_Nucl2Antiparalela, 1, 1);

                salidaFinal += "Antiparallel Frame 3: " + "\r\n\r\n";
                Identificador_Secuencias_Nucleotidos(cadena2, sec_Nucl3Antiparalela, 2, 2);

            }			

            salidaFinal += "Amino Acid Sequences : " + "\r\n\r\n";

            contadorGlobalID = 0;

            if(orientacion == 1 || orientacion == 3) {

                salidaFinal += "Frame 1: " + "\r\n\r\n";
                codigo_Letras_Aminoacidos(sec_Nucl1, sec_aa1, 0);

                salidaFinal += "Frame 2: " + "\r\n\r\n";
                codigo_Letras_Aminoacidos(sec_Nucl2, sec_aa2, 1);

                salidaFinal += "Frame 3: " + "\r\n\r\n";
                codigo_Letras_Aminoacidos(sec_Nucl3, sec_aa3, 2);

            }

            if(orientacion == 2 || orientacion == 3) {

                salidaFinal += "Antiparallel Frame 1: " + "\r\n\r\n";
                codigo_Letras_Aminoacidos(sec_Nucl1Antiparalela, sec_aa1Antiparalela, 0);

                salidaFinal += "Antiparallel Frame 2: " + "\r\n\r\n";
                codigo_Letras_Aminoacidos(sec_Nucl2Antiparalela, sec_aa2Antiparalela, 1);

                salidaFinal += "Antiparallel Frame 3: " + "\r\n\r\n";
                codigo_Letras_Aminoacidos(sec_Nucl3Antiparalela, sec_aa3Antiparalela, 2);

            }

            salidaArchivo.write(salidaFinal);
            salidaArchivo.close();
            b.close();
			
        }catch(Exception e){
            
            Logger.getLogger(Busqueda_ORFs.class.getName()).log(Level.SEVERE, null, e);
            jButton3.setEnabled(true);
            Cancelar_Hilos.cargarCancelarHilos();
            
        }
        
    }
	
    public static void Convertidor_ARN(char cadena[]){
		
        try{
        
            for(int i = 0; i<cadena.length;i++){

                if (Thread.currentThread().isInterrupted()) {

                    break;

                }
                
                if(cadena[i] == 'a'){

                    cadena[i] = 'A';

                }else if(cadena[i] == 't' || cadena[i] == 'T' || cadena[i]== 'u'){

                    cadena[i] = 'U';

                }else if(cadena[i] == 'g'){

                    cadena[i] = 'G';

                }else if(cadena[i] == 'c'){

                    cadena[i] = 'C';

                }else if (cadena[i] != 'c' && cadena[i] != 'C' && cadena[i] != 'a' && cadena[i] != 'A' && cadena[i] != 't' && cadena[i] != 'T' 
                    && cadena[i] != 'g' && cadena[i] != 'G' && cadena[i] != '^'){

                    cadena[i] = '?';

                }

            }
        
        }catch(Exception e){
            
            Logger.getLogger(Busqueda_ORFs.class.getName()).log(Level.SEVERE, null, e);
            jButton3.setEnabled(true);
            Cancelar_Hilos.cargarCancelarHilos();
            
        }
		
    }

    public static void Identificador_Secuencias_Nucleotidos(char cadena[], String sec_Nucl[], int marco,
            int marco_Estatico){
	
        try{
        
            int guardar_Inicio = 0;
            int guardar_Final = 0;
            String Base;
            int contador_String_secNucl = 0;
            int contador_Tamano_secNucl = 0;
            boolean nueva_Secuencia = false;
            boolean nuevo_Inicio = true;
            char secuencia_ARN_Temporal[];
            String cadenaUnida = "";

            for(; marco<= cadena.length -4; marco+=3){

                if (Thread.currentThread().isInterrupted()) {

                    break;

                }

                if(cadena[marco]=='A' && cadena[marco+1]=='U' && cadena[marco+2]=='G' && nuevo_Inicio == true){

                    guardar_Inicio= marco;
                    nueva_Secuencia = true;
                    nuevo_Inicio = false;

                }else if((cadena[marco]=='U' && cadena[marco+1]=='G' && cadena[marco+2]=='A')||
                    (cadena[marco]=='U' && cadena[marco+1]=='A' && cadena[marco+2]=='A')||
                    (cadena[marco]=='U' && cadena[marco+1]=='A' && cadena[marco+2]=='G')){

                    if(nueva_Secuencia == true){ 

                        guardar_Final = marco+2;
                       
                        for(int x = guardar_Inicio; x<= guardar_Final; x++){
                            
                            if (Thread.currentThread().isInterrupted()) {

                                break;

                            }
                            
                            Base = String.valueOf(cadena[x]); 

                            sec_Nucl[contador_String_secNucl]=sec_Nucl[contador_String_secNucl].concat(Base);

                        }

                        nueva_Secuencia = false;
                        nuevo_Inicio = true;

                        contador_Tamano_secNucl++;
                        contador_String_secNucl++;

                    }

                }

            }

            for(int i = 0; i<sec_Nucl.length; i++){

                if (Thread.currentThread().isInterrupted()) {

                    break;

                }

                if(sec_Nucl[i].equals("Sequence = ")){

                    break;

                }

                secuencia_ARN_Temporal = sec_Nucl[i].toCharArray();

                if(secuencia_ARN_Temporal.length<=101){

                    continue;

                }

                contadorGlobalID++;

                salidaFinal += "#ID = " + contadorGlobalID + "\r\n";

                cadenaUnida = "";

                for(int y = 0; y < secuencia_ARN_Temporal.length; y++){

                    if (Thread.currentThread().isInterrupted()) {

                        break;

                    }
                    
                    if(y % 100 == 0 && y != 0){

                        salidaFinal += "\r\n";

                    }

                    if(secuencia_ARN_Temporal[y] == 'U') {

                        salidaFinal += "T";
                        cadenaUnida += "T";

                    }else {

                        salidaFinal += secuencia_ARN_Temporal[y];
                        cadenaUnida += secuencia_ARN_Temporal[y];

                    }
                }

                cadenaUnida = cadenaUnida.replace("Sequence = ", "");

                String global1Temporal = "";
                String global2Temporal = "";
                
                for(int recorrerGlobal = 0; recorrerGlobal < cadena1Global.length(); recorrerGlobal++){
                    
                    if(cadena1Global.toCharArray()[recorrerGlobal] != 'A' && cadena1Global.toCharArray()[recorrerGlobal] != 'C' && cadena1Global.toCharArray()[recorrerGlobal] != 'T'
                            && cadena1Global.toCharArray()[recorrerGlobal] != 'G'){
                        
                        global1Temporal += "?";
                        
                    }else{
                        
                        global1Temporal += cadena1Global.toCharArray()[recorrerGlobal];
                        
                    }
                    
                    if(cadena2Global.toCharArray()[recorrerGlobal] != 'A' && cadena2Global.toCharArray()[recorrerGlobal] != 'C' && cadena2Global.toCharArray()[recorrerGlobal] != 'T'
                            && cadena2Global.toCharArray()[recorrerGlobal] != 'G'){
                        
                        global2Temporal += "?";
                        
                    }else{
                        
                        global2Temporal += cadena2Global.toCharArray()[recorrerGlobal];
                        
                    }
                    
                }
                
                cadena1Global = global1Temporal;
                cadena2Global = global2Temporal;
                
                int inicio1 = cadena1Global.indexOf(cadenaUnida);
                int fin1 = (inicio1 != -1) ? inicio1 + cadenaUnida.length() - 1 : -1;

                int inicio2 = cadena2Global.indexOf(cadenaUnida);
                int fin2 = (inicio2 != -1) ? inicio2 + cadenaUnida.length() - 1 : -1;

                if(inicio1 != -1) {

                    salidaFinal += "\n\nStart = " + (inicio1 + 1) + ", End = " + (fin1 + 1) + "\r\n";

                }else if(inicio2 != -1) {

                    salidaFinal += "\n\nStart = " + (inicio2 + 1) + ", End = " + (fin2 + 1) + "\r\n";

                }

                salidaFinal += "\nLength: " + cadenaUnida.length() + "\r\n";

                salidaFinal += "\n"+"--------------------------------------------------------------------------------------------------------" + "\r\n\r\n";

            }
	
        }catch(Exception e){
            
            Logger.getLogger(Busqueda_ORFs.class.getName()).log(Level.SEVERE, null, e);
            jButton3.setEnabled(true);
            Cancelar_Hilos.cargarCancelarHilos();
            
        }
        
    }

    public static void codigo_Letras_Aminoacidos(String secuencia[], String aa[], int marco_Estatico){
		
        try{
        
            char secuencias_Temporal[];
            String codones="";
            String bases[] = new String[3];

            for(int i = 0; i<secuencia.length; i++){

                if (Thread.currentThread().isInterrupted()) {

                    break;

                }
                
                secuencias_Temporal = secuencia[i].toCharArray();

                for(int x = 11; x<secuencias_Temporal.length; x+=3){

                    if (Thread.currentThread().isInterrupted()) {

                        break;

                    }
                    
                    bases[0] = String.valueOf(secuencias_Temporal[x]);
                    bases[1] = String.valueOf(secuencias_Temporal[x+1]);
                    bases[2] = String.valueOf(secuencias_Temporal[x+2]);

                    codones = codones.concat(bases[0]+bases[1]+bases[2]);

                    if(codones.equals("UCU") || codones.equals("UCC") || codones.equals("UCA") || codones.equals("UCG") || codones.equals("AGU")
                        || codones.equals("AGC")){

                        aa[i]= aa[i].concat("S");

                    }else if(codones.equals("CGU") || codones.equals("CGC") || codones.equals("CGA") || codones.equals("CGG") || codones.equals("AGA")
                        || codones.equals("AGG")){

                        aa[i] = aa[i].concat("R");

                    }else if(codones.equals("UUA") || codones.equals("UUG") || codones.equals("CUU") || codones.equals("CUC") || codones.equals("CUA")
                        || codones.equals("CUG")){

                        aa[i] = aa[i].concat("L");

                    }else if(codones.equals("GCU") || codones.equals("GCC") || codones.equals("GCA") || codones.equals("GCG")){

                        aa[i] = aa[i].concat("A");

                    }else if(codones.equals("GGU") || codones.equals("GGC") || codones.equals("GGA") || codones.equals("GGG")){

                        aa[i] = aa[i].concat("G");

                    }else if(codones.equals("CCU") || codones.equals("CCC") || codones.equals("CCA") || codones.equals("CCG")){

                        aa[i] = aa[i].concat("P");

                    }else if(codones.equals("ACU") || codones.equals("ACC") || codones.equals("ACA") || codones.equals("ACG")){

                        aa[i] = aa[i].concat("T");

                    }else if(codones.equals("GUU") || codones.equals("GUC") || codones.equals("GUA") || codones.equals("GUG")){

                        aa[i] = aa[i].concat("V");

                    }else if(codones.equals("AUU") || codones.equals("AUC") || codones.equals("AUA")){

                        aa[i] = aa[i].concat("I");

                    }else if(codones.equals("AAU") || codones.equals("AAC")){

                        aa[i] = aa[i].concat("N");

                    }else if(codones.equals("GAU") || codones.equals("GAC")){

                        aa[i] = aa[i].concat("D");

                    }else if(codones.equals("UGU") || codones.equals("UGC")){

                        aa[i] = aa[i].concat("C");

                    }else if(codones.equals("CAA") || codones.equals("CAG")){

                        aa[i] = aa[i].concat("Q");

                    }else if(codones.equals("GAA") || codones.equals("GAG")){

                        aa[i] = aa[i].concat("E");

                    }else if(codones.equals("CAU") || codones.equals("CAC")){

                        aa[i] = aa[i].concat("H");

                    }else if(codones.equals("AAA") || codones.equals("AAG")){

                        aa[i] = aa[i].concat("K");

                    }else if(codones.equals("UUU") || codones.equals("UUC")){

                        aa[i] = aa[i].concat("F");

                    }else if(codones.equals("UAU") || codones.equals("UAC")){

                        aa[i] = aa[i].concat("Y");

                    }else if(codones.equals("AUG")){

                        aa[i] = aa[i].concat("M");

                    }else if(codones.equals("UGG")){

                        aa[i] = aa[i].concat("W");

                    }else if(codones.equals("UAA") || codones.equals("UAG") || codones.equals("UGA")){

                        //Evita fallo al encontrar terminaciones

                    }else{

                        aa[i] = aa[i].concat("?");

                    }

                    codones="";

                }

            }

            char secuencia_AA_Temporal[];

            String cadenaUnida = "";

            for(int i = 0; i<aa.length;i++){

                if (Thread.currentThread().isInterrupted()) {

                    break;

                }
                
                if(aa[i].equals("Sequence = ")){

                    break;

                }

                secuencia_AA_Temporal=aa[i].toCharArray();

                if(secuencia_AA_Temporal.length < 41){

                    continue;	

                }

                contadorGlobalID++;

                salidaFinal += "#ID = " + contadorGlobalID + "\r\n";

                cadenaUnida = "";

                for(int x = 0; x<secuencia_AA_Temporal.length;x++){

                    if (Thread.currentThread().isInterrupted()) {

                        break;

                    }
                    
                    if(x % 100 == 0 && x != 0){

                        salidaFinal += "\r\n";

                    }

                    salidaFinal += secuencia_AA_Temporal[x];
                    cadenaUnida += secuencia_AA_Temporal[x];

                }

                salidaFinal += "\n\nLength: " + cadenaUnida.replace("Sequence = ", "").length() + "\r\n";

                salidaFinal += "\nMolecular Mass ~ " + df.format(masa_Molecular(cadenaUnida.replace("Sequence = ", ""))) + " kDa\r\n\r\n";

                salidaFinal += "\n"+"--------------------------------------------------------------------------------------------------------" + "\r\n\r\n";

            }
        
        }catch(Exception e){
            
            Logger.getLogger(Busqueda_ORFs.class.getName()).log(Level.SEVERE, null, e);
            jButton3.setEnabled(true);
            Cancelar_Hilos.cargarCancelarHilos();
            
        }
            
    }
    
    public static double masa_Molecular(String cadena){
	
        try{
            
            char Analisis_Aminoacidos_Temporal[];		
            double peso_AA_Libres = 0;
            final double peso_Agua = 18.01528;

            final double peso_S = 105.093, peso_R = 174.2017, peso_L = 131.1736, peso_A = 89.0935, peso_G = 75.0669, peso_P = 115.131, peso_T = 119.1197, peso_V = 117.1469,
                peso_I = 131.1736, peso_N = 132.1184, peso_D = 133.1032, peso_C = 121.159, peso_Q = 146.1451, peso_E = 147.1299, peso_H = 155.1552, 
                peso_K = 146.1882, peso_F = 165.19, peso_Y = 181.1894, peso_M = 149.2124, peso_W = 204.2262;

            Analisis_Aminoacidos_Temporal = cadena.toCharArray();

            for(int i = 0; i< Analisis_Aminoacidos_Temporal.length; i++){
                
                if (Thread.currentThread().isInterrupted()) {

                    break;

                }
                
                switch (Analisis_Aminoacidos_Temporal[i]){

                    case 'S':		

                        peso_AA_Libres += peso_S;
                        break;

                    case 'R':

                        peso_AA_Libres += peso_R;
                        break;

                    case 'L':

                        peso_AA_Libres += peso_L;
                        break;

                    case 'A':

                        peso_AA_Libres += peso_A;
                        break;

                    case 'G':

                        peso_AA_Libres += peso_G;
                        break;

                    case 'P':

                        peso_AA_Libres += peso_P;
                        break;

                    case 'T':

                        peso_AA_Libres += peso_T;
                        break;

                    case 'V':

                        peso_AA_Libres += peso_V;
                        break;

                    case 'I':

                        peso_AA_Libres += peso_I;
                        break;

                    case 'N':

                        peso_AA_Libres += peso_N;
                        break;

                    case 'D':

                        peso_AA_Libres += peso_D;
                        break;

                    case 'C':

                        peso_AA_Libres += peso_C;
                        break;

                    case 'Q':

                        peso_AA_Libres += peso_Q;
                        break;

                    case 'E':

                        peso_AA_Libres += peso_E;
                        break;

                    case 'H':

                        peso_AA_Libres += peso_H;
                        break;

                    case 'K':

                        peso_AA_Libres += peso_K;
                        break;

                    case 'F':

                        peso_AA_Libres += peso_F;
                        break;

                    case 'Y':

                        peso_AA_Libres += peso_Y;
                        break;

                    case 'M':

                        peso_AA_Libres += peso_M;
                        break;

                    case 'W':

                        peso_AA_Libres += peso_W;
                        break;

                    default:

                        return -1;

                }

            }

            return (peso_AA_Libres - (peso_Agua * ((Analisis_Aminoacidos_Temporal.length)-1)))/1000;
		
        }catch(Exception e){
            
            Logger.getLogger(Busqueda_ORFs.class.getName()).log(Level.SEVERE, null, e);
            jButton3.setEnabled(true);
            return -1;
            
        }
        
    }
    
} 