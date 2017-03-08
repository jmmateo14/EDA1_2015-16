package org.eda1.practica01.ejercicio02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class ProcesarDatos {

	public static ArrayList<EmpresaProyectos> cargarArchivo(
			String stringArchivoEntrada) {
		ArrayList<EmpresaProyectos> l = new ArrayList<EmpresaProyectos> ();
		try {
			BufferedReader f = new BufferedReader 
					(new FileReader (stringArchivoEntrada));
			String cadena = f.readLine();
			while (cadena != null) {
				Scanner sc = new Scanner (cadena);
				sc.useDelimiter(" ");
				String empresa = sc.next();
				String proyecto = sc.next();
				String ciudad = sc.next();
				boolean encon = false;
				for (int i=0; i<l.size(); i++) {
					EmpresaProyectos e = l.get(i);
					if (e.getEmpresa().equals(empresa)) {
						e.addProyectoCiudad(proyecto, ciudad);
						encon = true;
						break;
					}
				}
				if ( ! encon) {
					EmpresaProyectos nueva = new EmpresaProyectos (empresa);
					nueva.addProyectoCiudad(proyecto, ciudad);
					l.add(nueva);
				}
				cadena = f.readLine();
			}
			f.close();
		}
		catch (Exception e) {
			System.out.println("Excepcion");
		}
		return l;
	}
	
	public static void guardarEmpresasProyectosCiudades(
			ArrayList<EmpresaProyectos> listaEmpresas, String archivo) {
		try {
			PrintWriter pw = new PrintWriter (new FileWriter (archivo));
			for (int i=0; i<listaEmpresas.size(); i++) {
				EmpresaProyectos e = listaEmpresas.get(i);
				for (int j=0; j<e.getProyectosCiudades().size(); j++) {
					ProyectoCiudades p = e.getProyectoCiudades(j);
					for (int k=0; k<p.getCiudades().size(); k++) {
						pw.write(e.getEmpresa()+" "+p.getProyecto()+
								" "+p.getCiudad(k)+"\n");
					}
				}
			}
			
			pw.close();
		}
		catch (IOException e) {
		}
	}

	public static String devolverEmpresasProyectosCiudades(
			ArrayList<EmpresaProyectos> listaEmpresas) {
		String salida = "";
		for (int i=0; i<listaEmpresas.size(); i++) {
			EmpresaProyectos e = listaEmpresas.get(i);
			salida = salida + e.getEmpresa() + ": ";
			for (int j=0; j<e.getProyectosCiudades().size(); j++) {
				ProyectoCiudades p = e.getProyectoCiudades(j);
				salida = salida + p.getProyecto() + "<";
				for (int k=0; k<p.getCiudades().size(); k++) {
					salida = salida + p.getCiudad(k);
					if (k != p.getCiudades().size()-1)
						salida = salida + ", ";
				}
				if (j == e.getProyectosCiudades().size()-1)
					salida = salida + ">";
				else
					salida = salida + ">; ";
			}
			salida = salida + "\n";
		}
		return salida;
	}

	public static ArrayList<String> enumerarEmpresasCiudad(
			ArrayList<EmpresaProyectos> listaEmpresas, String string) {
		ArrayList<String> l = new ArrayList<String> ();
		for (int i=0; i<listaEmpresas.size(); i++) {
			EmpresaProyectos e = listaEmpresas.get(i);
			boolean encon = false;
			for (int j=0; j<e.getProyectosCiudades().size() && ! encon; j++) {
				ProyectoCiudades p = e.getProyectoCiudades(j);
				if (p.getCiudades().contains(string)) {
					l.add(e.getEmpresa());
					encon = true;
				}
			}
		}
		return l;
	}

	public static ArrayList<String> enumerarProyectosCiudad(
			ArrayList<EmpresaProyectos> listaEmpresas, String string) {
		ArrayList<String> l = new ArrayList<String> ();
		for (int i=0; i<listaEmpresas.size(); i++) {
			EmpresaProyectos e = listaEmpresas.get(i);
			for (int j=0; j<e.getProyectosCiudades().size(); j++) {
				ProyectoCiudades p = e.getProyectoCiudades(j);
				if (p.getCiudades().contains(string)) {
					l.add(p.getProyecto());
				}
			}
		}
		return l;
	}

	public static int contarCiudadesEmpresa(
			ArrayList<EmpresaProyectos> listaEmpresas, String empresa) {
		
		int i = 0;
		boolean encon = false;
		while (i<listaEmpresas.size() && !encon) {
			if (listaEmpresas.get(i).getEmpresa().equals(empresa))
				encon = true;
			else 
				i++;
		}
		if (! encon)
			return 0;
		EmpresaProyectos e = listaEmpresas.get(i);
		ArrayList<String> lista = new ArrayList<String> ();
		for (int j=0; j<e.getProyectosCiudades().size(); j++) {
			ProyectoCiudades p = e.getProyectoCiudades(j);
			for (int k=0; k<p.getCiudades().size(); k++) {
				String ciudad = p.getCiudad(k);
				if ( ! lista.contains(ciudad))
					lista.add(ciudad);
			}
		}
		return lista.size();
	}

	public static ArrayList<String> enumerarCiudadesProyectoEmpresa(
			ArrayList<EmpresaProyectos> listaEmpresas, String proyecto,
			String empresa) {
		ArrayList<String> lista = new ArrayList<String> ();
		int i = 0;
		boolean enconEmpresa = false;
		while (i<listaEmpresas.size() && !enconEmpresa) {
			if (listaEmpresas.get(i).getEmpresa().equals(empresa))
				enconEmpresa = true;
			else
				i++;
		}		
		if (!enconEmpresa)
			return null;
		EmpresaProyectos e1 = listaEmpresas.get(i);
		int j = 0;
		boolean enconProyecto = false;
		while (j<e1.getProyectosCiudades().size() && !enconProyecto) {
			if (e1.getProyectoCiudades(j).getProyecto().equals(proyecto))
				enconProyecto = true;
			else
				j++;
		}
		if (!enconProyecto)
			return null;
		ProyectoCiudades p1 = e1.getProyectoCiudades(j);
		for (i=0; i<listaEmpresas.size(); i++) {
			if ( ! listaEmpresas.get(i).getEmpresa().equals(empresa)) {
				EmpresaProyectos e = listaEmpresas.get(i);
				for (j=0; j<e.getProyectosCiudades().size(); j++) {
					ProyectoCiudades p = e.getProyectoCiudades(j);
					for (int k=0; k<p.getCiudades().size(); k++) {
						if (p1.getCiudades().contains(p.getCiudad(k))) {
							if ( ! lista.contains(p.getCiudad(k)))
								lista.add(p.getCiudad(k));
						}
					}
				}
			}
		}
		return lista;
	}

}

