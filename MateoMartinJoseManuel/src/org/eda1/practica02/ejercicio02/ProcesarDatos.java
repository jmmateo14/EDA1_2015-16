package org.eda1.practica02.ejercicio02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.eda1.estructurasdedatos.AVLTree;

public class ProcesarDatos {

	public static AVLTree<EmpresaProyectos> cargarArchivo(String archivo){
		AVLTree<EmpresaProyectos> avl = new AVLTree<EmpresaProyectos>();
		
		try {
			BufferedReader f = new BufferedReader(new FileReader(archivo));
			String linea = f.readLine();
			while(linea != null){
				Scanner sc = new Scanner(linea);
				String empresa = sc.next();
				String proyecto = sc.next();
				String ciudad = sc.next();
				addEmpresaProyectoCiudadWithFind(avl, empresa, proyecto, ciudad);
				linea = f.readLine();
			}
			f.close();
		} catch (IOException e) {
			
		}
		return avl;
	}
	
	public static boolean addEmpresaProyectoCiudad(AVLTree<EmpresaProyectos> listaEmpresas, String empresa, String proyecto, String ciudad){
		EmpresaProyectos e = new EmpresaProyectos(empresa);
		e.addProyectoCiudad(proyecto, ciudad);
		return listaEmpresas.add(e);
	}
	
	public static boolean addEmpresaProyectoCiudadWithFind(AVLTree<EmpresaProyectos> listaEmpresas, String empresa, String proyecto, String ciudad){
		EmpresaProyectos e = new EmpresaProyectos(empresa);
		EmpresaProyectos busquedaE = listaEmpresas.find(e);
		if(busquedaE == null){
			e.addProyectoCiudad(proyecto, ciudad);
			listaEmpresas.add(e);
			return true;
		}
		busquedaE.addProyectoCiudadWithFind(proyecto, ciudad);
		return false;
	}
	
	public static void mostrarEmpresasProyectosCiudades(AVLTree<EmpresaProyectos> listaEmpresas){

	}
	
	public static void guardarEmpresasProyectosCiudades(AVLTree<EmpresaProyectos> listaEmpresas, String archivo){
		
	}
	
	public static int numeroProyectosEmpresa(AVLTree<EmpresaProyectos> listaEmpresas, String empresa){
		EmpresaProyectos e = new EmpresaProyectos(empresa);
		EmpresaProyectos busqueda = listaEmpresas.find(e);
		if(busqueda == null)
			return 0;
		return busqueda.size();
	}
	
	public static int numeroCiudadesProyecto(AVLTree<EmpresaProyectos> listaEmpresas, String proyecto){
		for(EmpresaProyectos e : listaEmpresas){
			ProyectoCiudades p = new ProyectoCiudades(proyecto);
			ProyectoCiudades busqueda = e.getProyectosCiudades().find(p);
			if(busqueda != null)
				return busqueda.size();
		}
		return 0;
	}
	
	public static int numeroCiudadesEmpresa(AVLTree<EmpresaProyectos> listaEmpresas, String empresa){
		EmpresaProyectos e = new EmpresaProyectos(empresa);
		EmpresaProyectos busqueda = listaEmpresas.find(e);
		if(busqueda == null)
			return 0;
		AVLTree<String> ciudades = new AVLTree<String>();
		for(ProyectoCiudades p : busqueda.getProyectosCiudades()){
			for(String c : p.getCiudades()){
				ciudades.add(c);
			}
		}
		return ciudades.size();
	}
	
	public static  String devolverEmpresasProyectosCiudades(AVLTree<EmpresaProyectos> listaEmpresas){
String cadena = "";
		
		//recorro todas las empresas
		for (EmpresaProyectos e : listaEmpresas) {
			//creo la cadena de comienzo de la empresa
			cadena = cadena + e.getEmpresa() + ": ";
			int i = 0;
			//recorro todos los proyectos
			for (ProyectoCiudades p : e.getProyectosCiudades()) {
				//creo la cadena de comienzo del proyecto
				cadena = cadena + p.getProyecto() + "<";
				int j = 0;
				for (String c : p.getCiudades()) {
					cadena = cadena + c;
					j++;
					//utilizo el contador para saber si es el ultimo
					if (j < p.getCiudades().size())
						cadena = cadena + ", ";
				}
				//cadena de fin de proyecto
				cadena = cadena + ">";
				i++;
				//utilizo el contador para saber si se trata del ultimo o no
				if (i < e.getProyectosCiudades().size()) //no es el ultimo
					cadena = cadena + "; ";
			}
			//cadena de fin de empresa
			//no necesito contador porque TODAS las empresas acaban igual
			cadena = cadena + "\n";
		}
		return cadena;
	}

	
	public static ArrayList<String> devolverEmpresasCiudad(AVLTree<EmpresaProyectos> listaEmpresas, String ciudad){
		ArrayList<String> l = new ArrayList<String>();
		for (EmpresaProyectos e : listaEmpresas) {
			for (ProyectoCiudades p : e.getProyectosCiudades()) {
				if(p.getCiudades().contains(ciudad)){
					l.add(e.getEmpresa());
					break;
					/*if(! l.contains(e.getEmpresa()))
						l.add(e.getEmpresa());
						*/
				}
			}
		}
		return l;
	}
	
	public static ArrayList<String> devolverProyectosCiudad (AVLTree<EmpresaProyectos> listaEmpresas, String ciudad){
		ArrayList<String> l = new ArrayList<String>();
		for (EmpresaProyectos e : listaEmpresas) {
			for (ProyectoCiudades p : e.getProyectosCiudades()) {
				if(p.getCiudades().contains(ciudad)){
					l.add(p.getProyecto());
				}
			}
		}
		return l;
	}
	
	public static ArrayList<String> devolverCiudadesEmpresa(AVLTree<EmpresaProyectos> listaEmpresas, String empresa){
		ArrayList<String> l = new ArrayList<String>();
		EmpresaProyectos e = new EmpresaProyectos(empresa);
		EmpresaProyectos e2 = listaEmpresas.find(e);  //e2 es el resultado de la busqueda
		if(e2 != null){
			for(ProyectoCiudades p : e2.getProyectosCiudades()){
				for(String c : p.getCiudades()){
					if( !l.contains(c))
						l.add(c);
				}
			}
		}
		
		return l;
	}
	
	
	public static ArrayList<String> devolverCiudadesProyectoEmpresa(AVLTree<EmpresaProyectos> listaEmpresas, String proyecto, String empresa){
		ArrayList<String> l = new ArrayList<String>();
		EmpresaProyectos e = new EmpresaProyectos(empresa);
		EmpresaProyectos e2 = listaEmpresas.find(e);
		if(e2 == null)
			return l;
		
		ProyectoCiudades p = new ProyectoCiudades(proyecto);
		ProyectoCiudades p2 = e2.getProyectosCiudades().find(p);
		if(p2 == null)
			return l;
		for(EmpresaProyectos e3 : listaEmpresas){
			if(! e3.equals(e2)){
				for(ProyectoCiudades p3 : e3.getProyectosCiudades()){
					for(String c : p3.getCiudades()){
						if(p2.getCiudades().contains(c)){
							if(!l.contains(c)){
								l.add(c);
							}
						}
					}
				}
			}
		}
		return l;
	}
	
	public static ArrayList<String> devolverEmpresaParesProyectoCiudadesComunes(AVLTree<EmpresaProyectos> listaEmpresas, String empresa){
		ArrayList<String> l = new ArrayList<String>();
		EmpresaProyectos e = new EmpresaProyectos(empresa);
		EmpresaProyectos e2 = listaEmpresas.find(e);
		if(e2 == null)
			return l;
		
		for(ProyectoCiudades p1 : e2.getProyectosCiudades()){
			for(ProyectoCiudades p2 : e2.getProyectosCiudades()){
				if(p1.compareTo(p2) < 0){
					for(String c : p1.getCiudades()){
						if(p2.getCiudades().contains(c)){
							String cadena = p1.getProyecto() + " - " + p2.getProyecto() + " => " + c;
							l.add(cadena);
						}
					}
				}
			}
		}
		return l;
	}
}
