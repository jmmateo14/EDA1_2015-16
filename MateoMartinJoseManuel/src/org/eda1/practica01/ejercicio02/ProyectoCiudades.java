package org.eda1.practica01.ejercicio02;

import java.util.ArrayList;

public class ProyectoCiudades {

	private String proyecto;
	private ArrayList<String> ciudades;
	
	public ProyectoCiudades(){
		proyecto = "";
		ciudades = null;
		//Tambien lo podemos dejar vacio
		//combeniente hacerlo para que no se produzca ciertos errores
	}
	
	public ProyectoCiudades(String pro){
		proyecto = pro;
		ciudades = new ArrayList<String>();
	}

	public String getProyecto() {
		return proyecto;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	public void setCiudades(ArrayList<String> ciudades) {
		this.ciudades = ciudades;
	}
	
	public void addCiudad (String ciudad){
		if(! ciudades.contains(ciudad))
			ciudades.add(ciudad);
	}
	
	public String getCiudad(int index){
		if(index < 0 || index >= ciudades.size())
			return null;
		return ciudades.get(index);
	}

	public int size(){
		return ciudades.size();
	}

	public ArrayList<String> getCiudades() {
		return ciudades;
	}
}
