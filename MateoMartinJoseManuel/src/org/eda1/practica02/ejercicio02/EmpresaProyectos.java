package org.eda1.practica02.ejercicio02;

import org.eda1.estructurasdedatos.AVLTree;

public class EmpresaProyectos implements Comparable{

	private String empresa;
	private AVLTree<ProyectoCiudades> proyectosCiudades;
	
	public  EmpresaProyectos(){
		
	}
	
	public EmpresaProyectos(String empr){
		empresa = empr;
		proyectosCiudades = new AVLTree<ProyectoCiudades>();
	}
	
	public boolean addProyectoCiudad(String proyecto, String ciudad){
		ProyectoCiudades p = new ProyectoCiudades(proyecto);
		p.addCiudad(ciudad);
		return proyectosCiudades.add(p);
	}
	
	public boolean addProyectoCiudadWithFind(String proyecto, String ciudad){
		ProyectoCiudades p = new ProyectoCiudades(proyecto);
		ProyectoCiudades busqueda = proyectosCiudades.find(p);
		if(busqueda == null){
			p.addCiudad(ciudad);
			proyectosCiudades.add(p);
			return true;
		}
		busqueda.addCiudad(ciudad);
		return false;
	}
	
	public void setEmpresa(String empr){
		this.empresa = empresa;
	}
	
	public String getEmpresa(){
		return empresa;
	}
	
	public AVLTree<ProyectoCiudades> getProyectosCiudades(){
		return proyectosCiudades;
	}
	
	public int compareTo(Object otraEmpresaProyectos){
		EmpresaProyectos otra = (EmpresaProyectos) otraEmpresaProyectos;
		return empresa.compareTo(otra.empresa);
	}
	
	public int size(){
		return proyectosCiudades.size();
	}
	
}
