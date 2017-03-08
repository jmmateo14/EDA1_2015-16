package org.eda1.practica03.ejercicio02;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ProcesarDatos {
	
	private TreeMap<String, TreeMap<String, TreeSet<String>>> mapa;
	
	public ProcesarDatos() {
		super();
		this.mapa = new TreeMap<String, TreeMap<String, TreeSet<String>>>();
	}

	/**
	 * Metodo que lee las lineas del archivo correspondiente y las devuelve en un
	 * ED de array de array de String
	 * 
	 * @param notas
	 *            ruta del archivo de las notas
	 * @return un array de String con las notas
	 */
	public void cargarArchivo(String archivo) {
		try {
			BufferedReader f = new BufferedReader(new FileReader(archivo));
			String linea = f.readLine(); //leo la primera
			while(linea != null){
				Scanner sc = new Scanner(linea);
				String empresa = sc.next();
				String proyecto = sc.next();
				String ciudad = sc.next();
				TreeMap<String, TreeSet<String>> mapaProyecto = mapa.get(empresa);
				if(mapaProyecto == null){
					//no existe ese empresa
					mapaProyecto = new TreeMap<String, TreeSet<String>>();
					TreeSet<String> setCiudades = new TreeSet<String>();
					setCiudades.add(ciudad);
					mapaProyecto.put(proyecto, setCiudades);
					mapa.put(empresa, mapaProyecto);
					
				}
				else{
					//si existe la empresa busco el proyecto
					TreeSet<String> setCiudades = mapaProyecto.get(proyecto);
					if(setCiudades == null){
						//no exites ese proyecto
						setCiudades = new TreeSet<String>();
						setCiudades.add(ciudad);
						mapaProyecto.put(proyecto, setCiudades);
					}
					else{
						//si existe el proyecto, inserto la ciudad
						setCiudades.add(ciudad);
					}
				}
				
				linea = f.readLine();//vuelvo a leer
			}
			f.close();
			
		} catch (IOException e) {
		}
	}
	
	
	public int size(){
		return mapa.size();
	}

	
	public TreeSet<String> devolverCiudades() {
		TreeSet<String> ciudades = new TreeSet<String>();
		for(Entry<String, TreeMap<String, TreeSet<String>>> e : mapa.entrySet()){
			for(Entry<String, TreeSet<String>> p : e.getValue().entrySet()){
				for(String c : p.getValue())
					ciudades.add(c);
			}
		}
		return ciudades;
	}

	public TreeSet<String> devolverProyectos() {
	
		TreeSet<String> proyectos = new TreeSet<String>();
		for(Entry<String, TreeMap<String, TreeSet<String>>> e : mapa.entrySet()){
				proyectos.addAll(e.getValue().keySet());
		}
		return proyectos;
	}


	public TreeSet<String> devolverEmpresas() {
		TreeSet<String> empresas = new TreeSet<String>();
		for(String s : mapa.keySet()){
			empresas.add(s);
		}
		return empresas;
	}


	public int numeroProyectosEmpresa(String empresa) {
		TreeMap<String, TreeSet<String>> mapaProyectos = mapa.get(empresa);
		if(mapaProyectos == null)
			return -1;
		return mapaProyectos.size();
	}

	
	public int numeroCiudadesProyecto(String proyecto) {
		for(Entry<String, TreeMap<String, TreeSet<String>>> e : mapa.entrySet()){
			TreeSet<String> setCiudades = e.getValue().get(proyecto);
			if(setCiudades != null)
				return setCiudades.size();
		}
		return -1;
	}

	
	public int numeroCiudadesEmpresa(String empresa) {
		TreeMap<String, TreeSet<String>> mapaProyectos = mapa.get(empresa);
		if(mapaProyectos == null)
			return -1;
		TreeSet<String> setCiudades = new TreeSet<String>();
		for(Entry<String, TreeSet<String>> p : mapaProyectos.entrySet()){
			//setCiudades.addAll(p.getValue());
			for(String s : p.getValue())
				setCiudades.add(s);
		}
		return setCiudades.size();	
	}

	
	public String devolverEmpresasProyectosCiudades() {
		String cadena = "";
		for(Entry<String, TreeMap<String, TreeSet<String>>> e : mapa.entrySet()){
			cadena = cadena + e.getKey() + ": ";
			for(Entry<String, TreeSet<String>> p : e.getValue().entrySet()){
				cadena = cadena + p.getKey() + " <";
				for(String c : p.getValue()){
					cadena = cadena + c;
					if( ! c.equals(p.getValue().last()))
						cadena = cadena + ", ";
				}
				cadena = cadena + ">";
				if( ! p.getKey().equals(e.getValue().lastKey()))
					cadena = cadena + "; ";
			}
			cadena = cadena + "\n";
		}
		return cadena;

	}
	
	
	public TreeSet<String> devolverEmpresasCiudad(String ciudad) {
		TreeSet<String> empresas = new TreeSet<String>();
		
		for(Entry<String, TreeMap<String, TreeSet<String>>> e : mapa.entrySet()){
			for(Entry<String, TreeSet<String>> p : e.getValue().entrySet()){
				if(p.getValue().contains(ciudad))
					empresas.add(e.getKey());
			}
		}
		return empresas;
	
	}

	
	public TreeSet<String> devolverProyectosCiudad(String ciudad) {
		TreeSet<String> proyectos = new TreeSet<String>();
		
		for(Entry<String, TreeMap<String, TreeSet<String>>> e : mapa.entrySet()){
			for(Entry<String, TreeSet<String>> p : e.getValue().entrySet()){
				if(p.getValue().contains(ciudad))
					proyectos.add(p.getKey());
			}
		}
		return proyectos;
	
	}
	
	public TreeSet<String> devolverCiudadesEmpresa(String empresa) {
		TreeMap<String, TreeSet<String>> mapaProyectos = mapa.get(empresa);
		if(mapaProyectos == null)
			return null;
		TreeSet<String> setCiudades = new TreeSet<String>();
		for(Entry<String, TreeSet<String>> p : mapaProyectos.entrySet()){
			//setCiudades.addAll(p.getValue());
			for(String s : p.getValue())
				setCiudades.add(s);
		}
		return setCiudades;	
	
	}


	public TreeSet<String> devolverCiudadesProyectoEmpresa(String proyecto, String empresa) {
		TreeMap<String, TreeSet<String>> mapaProyectos = mapa.get(empresa);
		if(mapaProyectos == null)
			return null;
		TreeSet<String> setCiudades = mapaProyectos.get(proyecto);
		if(setCiudades == null)
			return null;
		TreeSet<String> set = new TreeSet<String>();
		for (Entry<String, TreeMap<String, TreeSet<String>>> e : mapa.entrySet()) {
			if( ! e.getKey().equals(empresa)){
				for(Entry<String, TreeSet<String>> p : e.getValue().entrySet()){
					for(String s : p.getValue()){
						if(setCiudades.contains(s))
							set.add(s);	
					}
				}
			}
		}
		return set;
	}
	

	public TreeSet<String> devolverEmpresaParesProyectoCiudadesComunes(String empresa) {
		TreeMap<String, TreeSet<String>> mapaProyectos = mapa.get(empresa);
		if(mapaProyectos == null)
			return null;
		TreeSet<String> pares = new TreeSet<String>();
		for(Entry<String, TreeSet<String>> p1 : mapaProyectos.entrySet()){
			for(Entry<String, TreeSet<String>> p2 : mapaProyectos.entrySet()){
				if(p2.getKey().compareTo(p1.getKey()) > 0){
					for(String s : p1.getValue()){
						if(p2.getValue().contains(s)){
							String cadena = p1.getKey() + " - " + p2.getKey() + " ==> " + s;
							pares.add(cadena);
						}
					}
				}
			}
		}
		return pares;
	}
	
	public String devolverProyectoConMayorNumeroDeCiudades() {
		int max = 0;
		String maxProy = "";
		
		for (Entry<String, TreeMap<String, TreeSet<String>>> e :
						mapa.entrySet()) {
			for (Entry<String, TreeSet<String>> p : 
						e.getValue().entrySet()) {
				if (p.getValue().size() > max) {
					max = p.getValue().size();
					maxProy = p.getKey();
				}
			}
		}
		return maxProy;
	}

	
	public String devolverEmpresaConMayorNumeroDeProyectos() {
		int max = 0;
		String maxEmp = "";
		
		for (Entry<String, TreeMap<String, TreeSet<String>>> e :
						mapa.entrySet()) {
			if (e.getValue().size() > max) {
				max = e.getValue().size();
				maxEmp = e.getKey();
			}
		}
		return maxEmp;
	}

	public String devolverCiudadConMayorNumeroDeProyectos() {
		TreeSet<String> setCiudades = devolverCiudades();
		int max = 0;
		String maxCiu = "";
		
		for (String s : setCiudades) {
			TreeSet<String> proyectos = devolverProyectosCiudad(s);
			if (proyectos.size() > max) {
				max = proyectos.size();
				maxCiu = s;
			}
		}
		return maxCiu;

	}

}