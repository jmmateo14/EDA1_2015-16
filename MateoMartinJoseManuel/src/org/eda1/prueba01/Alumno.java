package org.eda1.prueba01;

import java.util.ArrayList;
import java.util.Locale;

import javax.management.RuntimeErrorException;

public class Alumno implements Comparable<Alumno> {

	private String nombre;
	private String apellido1;
	private String apellido2;
	private ArrayList<Double> notas;
	
	public Alumno (String nombre, String apellido1, String apellido2){
		this.nombre = nombre.toUpperCase();
		this.apellido1 = apellido1.toUpperCase();
		this.apellido2 = apellido2.toUpperCase();
		notas = new ArrayList<Double>();
	}
	
	public Alumno (String nombre, String apellido1, String apellido2, ArrayList<Double> notas){
		this.nombre = nombre.toUpperCase();
		this.apellido1 = apellido1.toUpperCase();
		this.apellido2 = apellido2.toUpperCase();
		if(notas == null)
			this.notas = new ArrayList<Double>();
		else
			this.notas = (ArrayList<Double>) notas.clone();
	}
	
	public double calcularNotaMedia(){
		if(notas.size() == 0)
			return 0;
		double suma = 0;
		for(Double n : notas){
			if(n != null)
				suma = suma + n;
		}
		return suma / notas.size();
	}
	
	@Override
	public String toString(){
		if(apellido2.compareTo("-") != 0)
			return apellido1 + " " + apellido2 + " " + nombre + " (" + String.format(Locale.ENGLISH, "%.2f", calcularNotaMedia()) + ")";
		else
			return apellido1 + "  " + nombre + " (" + String.format(Locale.ENGLISH, "%.2f", calcularNotaMedia()) + ")";
	}
	
	public String nombreCompleto(){
		if(apellido2.compareTo("-") == 0)
			return nombre + " " + apellido1;
		return nombre + " " + apellido1 + " " + apellido2;
	}
	
	public String consultarExpediente(){
		String cadena = nombreCompleto() + " --> [";
		for(int i = 0; i < notas.size(); i++){
			if(notas.get(i) == null)
				cadena = cadena + "No presentado";
			else
				cadena = cadena + notas.get(i);
			if(i < notas.size() - 1)
				cadena = cadena + ", ";
		}
		cadena = cadena + "]";
		return cadena;
	}
	
	public int compareTo(Alumno otro){
		/*String cadena1 = apellido1+" "+apellido2+" "+nombre;
		String cadena2 = otro.apellido1+" "+otro.apellido2+" "+
					otro.nombre;
		return cadena1.compareTo(cadena2);*/

		if(otro == null)
			throw new RuntimeException("...comparando con nulo...");
		if(apellido1.compareTo(otro.apellido1) > 0)
			return 1;
		if(apellido1.compareTo(otro.apellido1) < 0)
			return -1;
		if(apellido2.compareTo(otro.apellido2) > 0)
			return 1;
		if(apellido2.compareTo(otro.apellido2) < 0)
			return -1;
		return nombre.compareTo(otro.nombre);
	}
}
