package org.eda1.prueba01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.eda1.estructurasdedatos.AVLTree;

public class GestionAlumnos {

	private AVLTree<Alumno> arbolAlumnos;

	public GestionAlumnos() {
		arbolAlumnos = new AVLTree<Alumno>();
	}

	public int size(){
		return arbolAlumnos.size();
	}
	
	public String toString(){
		return arbolAlumnos.toString();
	}

	public void cargarDatos(String archivo) {
		try{
			BufferedReader f = new BufferedReader(new FileReader(archivo));
			String cadena = f.readLine();
			while(cadena != null){
				String cadenaNotas = f.readLine();
				Scanner sc1 = new Scanner(cadena);
				String nombre = sc1.next();
				String apellido1 = sc1.next();
				String apellido2 = sc1.next();
				Scanner sc2 = new Scanner (cadenaNotas);
				ArrayList<Double> notas = new ArrayList<Double>();
				while(sc2.hasNext()){
					String nota = sc2.next();
					if(nota.compareTo("-") == 0)
						notas.add(null);
					else
						notas.add(Double.parseDouble(nota));
				}
				Alumno alumno = new Alumno (nombre, apellido1, apellido2, notas);
				arbolAlumnos.add(alumno);
				cadena = f.readLine();
			}
			f.close();
			
		}catch (IOException e){
			
		}
	}
	
	public String consultarExpediente(String apellido1, String apellido2, String nombre){
		Alumno alu = new Alumno (nombre, apellido1, apellido2);
		Alumno aluBuscado = arbolAlumnos.find(alu);
		if(aluBuscado == null)
			return "Este alumno/a no existe";
		return aluBuscado.consultarExpediente();
	}
	
	public String extraerListado(int size, Comparator<Alumno> comp){
		PriorityQueue<Alumno> pq = new PriorityQueue<Alumno>(comp);
		for(Alumno alu : arbolAlumnos){
			pq.add(alu);
		}
		String cadena = "[";
		for(int i = 0; i < size; i++){
			Alumno alu = pq.poll();
			cadena = cadena + alu.nombreCompleto();
			if(i < size - 1)
				cadena = cadena + ", ";
		}
		cadena = cadena + "]";
		return cadena;
	}
}
