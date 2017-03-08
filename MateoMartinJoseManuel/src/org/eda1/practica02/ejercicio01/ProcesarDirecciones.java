package org.eda1.practica02.ejercicio01;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;

import org.eda1.estructurasdedatos.BSTree;

public class ProcesarDirecciones {
	
	BSTree<DireccionMaquinas> treeDirecciones;
	
	
	public ProcesarDirecciones(){
		treeDirecciones = new BSTree<DireccionMaquinas>();
	}
	
	public ProcesarDirecciones(BSTree<DireccionMaquinas> treeDirecciones){
		this.treeDirecciones = treeDirecciones;
	}
	
	public BSTree<DireccionMaquinas> cargarArchivo(String archivo){

		try{//se puede hacer con BufferReader.
			Scanner fichero = new Scanner (new File(archivo));
			while(fichero.hasNextLine()){
				String linea = fichero.nextLine();
				Scanner sc = new Scanner(linea);
				//sc.useDelimiter(" "); por defecto en Scanner el separador es espacio, por lo que no lo necesitamos
				String direccion = sc.next();
				String maquina = sc.next();
				addDireccionMaquinaWithFind(direccion, maquina);
			}
			fichero.close();
			return treeDirecciones;
		}
		catch(Exception e){
			return null;
		}
	}
	
	public void guardarDireccionesIncidencias(String archivo){
		try {
			PrintWriter f = new PrintWriter(new FileWriter(archivo));
			f.write("[");
			for(DireccionMaquinas direc : treeDirecciones){
				f.write("(" + direc.getDireccion() + ", [");
				for(MaquinaContador maqui : direc.getMaquina()){
					f.write(maqui.toString());
				}
				f.write("])");
			}
			f.write("]");
			f.close();
		} catch (IOException e) {
			
		}
	}

	public boolean addDireccionMaquina(String direccion, String maquina){
		DireccionMaquinas dm = new DireccionMaquinas(direccion, maquina);
		if (treeDirecciones.add(dm) == true)
			return true;
		return false;
		
	}
	
	public boolean addDireccionMaquinaWithFind(String direccion, String maquina){
		DireccionMaquinas dm = new DireccionMaquinas(direccion, maquina);
		DireccionMaquinas busqueda = treeDirecciones.find(dm);
		if(busqueda == null){
			addDireccionMaquina(direccion, maquina);
			//treeDirecciones.add(dm);
			return true;
		}
		MaquinaContador mc = new MaquinaContador(maquina);
		busqueda.addMaquinaWithFind(mc);
		return false;
	}
	
	public int maquinasConContador(int contador){
		int count = 0;
		Iterator<DireccionMaquinas> iterator1 = treeDirecciones.iterator();
		while(iterator1.hasNext()){
			DireccionMaquinas direc = iterator1.next();
			Iterator<MaquinaContador> iterator2 = direc.getMaquina().iterator();
			while(iterator2.hasNext()){
				MaquinaContador maqui = iterator2.next();
				if(maqui.getContador() == contador)
					count++;
			}
		}
		return count;
		/*
		for(DireccionMaquinas direc : treeDirecciones){
			for(MaquinaContador maqui : direc.getMaquina()){
				if(maqui.getContador() == contador)
					count++;
			}
		}
		return count;
		*/
	}
	
	public String direccionMaquinasConContador(int contador){
		String cadena = "";
		Iterator<DireccionMaquinas> iterator1 = treeDirecciones.iterator();
		while(iterator1.hasNext()){
			DireccionMaquinas direc = iterator1.next();
			Iterator<MaquinaContador> iterator2 = direc.getMaquina().iterator();
			while(iterator2.hasNext()){
				MaquinaContador maqui = iterator2.next();
				if(maqui.getContador() == contador)
					cadena = cadena + "(" + direc.getDireccion() + ", " + maqui.getMaquina() + ")\n";
			}
		}
		return cadena;
	
	}
	
	public int contadorDeDireccionMaquina(String direccion, String maquina){
	//busco la direccion en el arbol
	DireccionMaquinas direc = new DireccionMaquinas(direccion);
	DireccionMaquinas busqueda = treeDirecciones.find(direc);
	//si no est devuelvo -1
	if(busqueda == null)
		return -1;
	//si esta la direccion busco la maquina
	MaquinaContador maqui = new MaquinaContador(maquina);
	MaquinaContador busqueda2 = busqueda.getMaquina().find(maqui);
	//si no esta devuelve -1
	if(busqueda2 == null)
		return -1;
	//si esta devuelvo el contador
	return busqueda2.getContador();
		
	}
	
	
}

