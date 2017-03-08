package org.eda1.practica03.ejercicio01;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;

public class ProcesarDirecciones {	
	
	private TreeMap<String, TreeMap<String, Integer>> mapa;
	
	public ProcesarDirecciones() {
		super();
		this.mapa = new TreeMap<String, TreeMap<String, Integer>>();
	}

	/**
	* Metodo que lee las lineas (IP, maquina) del archivo de entrada correspondiente y genera un TreeMap de objetos Direccion-Maquina
	* @param ruta del archivo de las lineas (IP, maquina)
	* @return 
	*/
	public void cargarArchivo(String archivo){
		
        String archivoEntrada = archivo;
        String cadena;
        StringTokenizer tokenizer=null;
        BufferedReader in = null;
        String dir="", maq="";
        Integer cont = null;

        try {
		    in = new BufferedReader(new FileReader(archivoEntrada));
		    while((cadena = in.readLine()) != null){
		    	tokenizer = new StringTokenizer(cadena, " ");
				while(tokenizer.hasMoreTokens()){
					dir = tokenizer.nextToken();
					maq = tokenizer.nextToken();
				}
				//Si no existe la dirección en el mapa...creamos una nueva entrada;
				if (!this.mapa.containsKey(dir)){
					this.mapa.put(dir, new TreeMap<String,Integer>());
				}
				//Si no existe este nombre de máquina --> creamos una nueva entrada en el mapa anidado
				//Si existe --> actualizamos contador.
				cont = this.mapa.get(dir).get(maq);
				if (cont == null)
					this.mapa.get(dir).put(maq, 1);
				else
					this.mapa.get(dir).put(maq, cont+1);
				
		    }		    
		    in.close();
		}catch (IOException e){
		}
		
	}

	public int tamano(){
		return this.mapa.size();
	}
	
	public void generarDirecciones(String archivo){   
		try{
			PrintWriter f = new PrintWriter(new FileWriter(archivo));
			for(Entry <String, TreeMap<String, Integer>> e1 : mapa.entrySet()){
				String cadena = e1.getKey() + " ==> {";
				TreeMap<String, Integer> m = e1.getValue();
				int n = 0;
				for(Entry <String, Integer> e2 : m.entrySet()){
					cadena = cadena + e2.getKey() + "=" + e2.getValue();
					if(n < m.entrySet().size() - 1)
						cadena = cadena + ", ";
					n++;
				}
				cadena = cadena + "}\n";
				f.write(cadena);
			}
			f.close();
		}catch(IOException e){
		}
	}


	public void generarIncidencias(String archivo){
		try{
			PrintWriter f = new PrintWriter(new FileWriter(archivo));
			for(Entry <String, TreeMap<String, Integer>> e1 : mapa.entrySet()){
				if(e1.getValue().size() > 1){
					String cadena = e1.getKey() + " ==> {";
					TreeMap<String, Integer> m = e1.getValue();
					int n = 0;
					for(Entry <String, Integer> e2 : m.entrySet()){
						cadena = cadena + e2.getKey() + "=" + e2.getValue();
						if(n < m.entrySet().size() - 1)
							cadena = cadena + ", ";
						n++;
				}
				cadena = cadena + "}\n";
				f.write(cadena);
			}
		}
		f.close();
		}catch(IOException e){
		}
    }
	

	public ArrayList<String> maquinasConContadorMayorQue(int c){
		ArrayList<String> l = new ArrayList<String>();
		for(Entry <String, TreeMap<String, Integer>> e1 : mapa.entrySet()){
			TreeMap<String, Integer> m = e1.getValue();		
			for(Entry <String, Integer> e2 : m.entrySet()){
				if(e2.getValue() > c)
					l.add(e2.getKey());
			}
		}
		return l;	
	}

	public int maquinasConContadorIgualA(int c){
		int n = 0;
		for(Entry <String, TreeMap<String, Integer>> e1 : mapa.entrySet()){
			TreeMap<String, Integer> m = e1.getValue();		
			for(Entry <String, Integer> e2 : m.entrySet()){
				if(e2.getValue() == c)
					n++;
			}
		}
		return n;
	}

	public int valorContador(String direccion, String maquina){
		//si no existe la direccion retorno 0
		if(! mapa.containsKey(direccion))
			return 0;
		//si existe cojo el mapa de direcciones y busco el nombre
		TreeMap<String, Integer> m = mapa.get(direccion);
		//al igual que antes si no exixte la maquina retorna 0 
		if(! m.containsKey(maquina))
			return 0;
		return m.get(maquina);
	}

	public ArrayList<String> incidenciasGeneradasPor(String direccion) {
		ArrayList<String> l = new ArrayList<String>();
		if(!mapa.containsKey(direccion))
			return null;
		TreeMap<String, Integer> m = mapa.get(direccion);
		if(m.size() == 1)
			return null;
		for(Entry<String, Integer> e : m.entrySet()){
			l.add(e.getKey());
		}
		return l;
	}

	public int numeroDeIncidenciasGeneradasPor(String direccion) {
		//si no exixte la direccion no hay incidencia
		if(!mapa.containsKey(direccion))
			return 0;
		TreeMap<String, Integer> m = mapa.get(direccion);
		//y si solo esta una vez tampoco hay incidencias
		if(m.size() == 1)
			return 0;
		return m.size();
	}

}
