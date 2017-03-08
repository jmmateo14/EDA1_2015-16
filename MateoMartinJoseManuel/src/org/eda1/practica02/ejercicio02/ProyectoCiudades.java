package org.eda1.practica02.ejercicio02;

import org.eda1.estructurasdedatos.AVLTree;


public class ProyectoCiudades implements Comparable{

	private String proyecto;
	private AVLTree<String> ciudades;
	
	public ProyectoCiudades(){
		
	}
	
	public ProyectoCiudades(String proy){
		proyecto = proy; //no hace falta this por que se llaman distinto
		ciudades = new AVLTree<String>();
	}
	
	public void setProyecto(String proy) {
		this.proyecto = proy;
	}
	
	public String getProyecto(){
		return proyecto;
	}
	
	public boolean addCiudad(String ciudad){
		if(ciudades.contains(ciudad))
			return false;
		ciudades.add(ciudad);
		return true;
	}
	
	public AVLTree<String> getCiudades(){
		return ciudades;
	}
	
	public int compareTo(Object otroProyectoCiudades){
		ProyectoCiudades pc = (ProyectoCiudades) otroProyectoCiudades;
		return this.proyecto.compareTo(pc.proyecto);
		
	}
	
	public int size(){
		return ciudades.size();
	}
	
}
