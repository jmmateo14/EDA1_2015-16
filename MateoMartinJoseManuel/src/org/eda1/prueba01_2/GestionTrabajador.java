package org.eda1.prueba01_2;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import org.eda1.estructurasdedatos.AVLTree;
import org.eda1.prueba01.Alumno;

public class GestionTrabajador {
	private AVLTree<Trabajador> arbolPalabra = new AVLTree<Trabajador>();

	public void cargarArchivo(String nombreArchivo){
		
		String[] palabrasLinea = null;
		String linea = "";
		try{
			BufferedReader f = new BufferedReader(new FileReader(nombreArchivo));
			String cadena = f.readLine();
			while(cadena != null){
				String cadenaHoras = f.readLine();
				Scanner sc1 = new Scanner(cadena);
				String nombre = sc1.next();
				Scanner sc2 = new Scanner (cadenaHoras);
				ArrayList<Double> horas = new ArrayList<Double>();
				while(sc2.hasNext()){
					String hora = sc2.next();
					if (linea.isEmpty()) continue;
	
					if(hora.compareTo("-") == 0)
						horas.add(null);
					else
						horas.add(Double.parseDouble(hora));
					
					for (int i=0; i<palabrasLinea.length; i++){
						if (palabrasLinea[i].contains("@")) break;
					}
				}
				//Si no existe el trabajador lo creo y lo añado.
				Trabajador trabajador = new Trabajador (nombre, horas);
				arbolPalabra.add(trabajador);
				cadena = f.readLine();
			}
			
			f.close();
		}catch(IOException e){
			
		}
		
	}

	public int size(){
		return this.arbolPalabra.size();
	}
	
	public boolean add(Trabajador trab){
		return this.arbolPalabra.add(trab);
	}
	
	public Trabajador find(String nombre){
		return this.arbolPalabra.find(new Trabajador(nombre,null));
	}
	
	public ArrayList<Trabajador> toArray(){
		return null;
	}
	
	public ArrayList<Trabajador> toArrayWithOrder(Comparator<Trabajador> comp){
		
		return null;
	}
	
	@Override
	public String toString(){
		return this.arbolPalabra.toString();
	}
}
