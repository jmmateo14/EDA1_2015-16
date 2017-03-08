package org.eda1.actividad01.serializacionED;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Scanner;

public class ProgramaSerializacion {

	public static String mostrarEstructura(ArrayList<CiudadBarrios> cB) {//El String lo ponemos por que en el test nos dice que devuelve una cadena
		if(cB == null)
		return null;
		
		if (cB.isEmpty())
			return "[]";
		String cadena = "";
		for (int i = 0; i < cB.size(); i++){
			cadena = cadena + "[" + cB.get(i).toString() + "]\n";
		}
		
		
		return cadena;
	}

	public static ArrayList<CiudadBarrios> cargarArchivo(String inputFile) {
		//como no tiene throw, tenemos que hacer la lectura dentro de un try-catch
		ArrayList<CiudadBarrios> cB = new ArrayList<CiudadBarrios>();
		try{
			BufferedReader f = new BufferedReader(new FileReader(inputFile));
				
			String cadena = f.readLine();
			while(cadena != null){
				Scanner sc = new Scanner (cadena);
				sc.useDelimiter(" "); // Por defecto el delimitador del scanner es el espacio
				sc.useLocale(Locale.ENGLISH); //Usa el punto en vez de la coma
				
				String ciudad = sc.next();
				double latitud = sc.nextDouble();
				double longitud = sc.nextDouble();
				CiudadBarrios ciudadBarrios = new CiudadBarrios(ciudad, latitud, longitud);
				int numBarrios = sc.nextInt();
				for (int i = 0; i < numBarrios; i++) {
					String barrio = sc.next(); //coje un barrio
					ciudadBarrios.addBarrio(barrio); //lo guardo
				}
				cB.add(ciudadBarrios); //cuando tiene toda la linia convertida a ciudadBarrios, la mete en la lista final
				cadena = f.readLine();
			}
			f.close();
			
		}
		catch(Exception e){
			
		}
		return cB; 
	}

}
