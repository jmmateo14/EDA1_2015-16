package org.eda1.practica01.ejercicio02;

import java.util.ArrayList;

public class EmpresaProyectos {

	private String empresa;
	private ArrayList<ProyectoCiudades> proyectosCiudades;
	
	public EmpresaProyectos() {
	}
	
	public EmpresaProyectos (String empr) {
		empresa = empr;
		proyectosCiudades = new ArrayList<ProyectoCiudades> ();
	}
	
	public void addProyectoCiudad (String proyecto, String ciudad) {
		for (int i = 0; i < proyectosCiudades.size(); i++) {
			ProyectoCiudades p = proyectosCiudades.get(i);
			if (p.getProyecto().equals(proyecto)) {
				p.addCiudad(ciudad);
				return;
			}
		}
		ProyectoCiudades nuevo = new ProyectoCiudades(proyecto);
		nuevo.addCiudad(ciudad);
		proyectosCiudades.add(nuevo);
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public ArrayList<ProyectoCiudades> getProyectosCiudades() {
		return proyectosCiudades;
	}
	
	public ProyectoCiudades getProyectoCiudades (int i) {
		if (i < 0 || i >= proyectosCiudades.size())
			return null;
		return proyectosCiudades.get(i);
	}
	
	public int size() {
		return proyectosCiudades.size();
	}
}
