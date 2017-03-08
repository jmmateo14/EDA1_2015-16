package org.eda1.practica02.ejercicio01;

import org.eda1.estructurasdedatos.BSTree;

public class DireccionMaquinas implements Comparable{

	private String direccion;
	private BSTree<MaquinaContador> maquinas;
	
	public DireccionMaquinas(){
		direccion = "";
		maquinas = null;
	}
	
	public DireccionMaquinas(String direccion){
		this.direccion = direccion;
		maquinas = new BSTree<MaquinaContador>();
	}
	
	public DireccionMaquinas(String direccion, String maquina){
		this.direccion = direccion;
		this.maquinas = new BSTree<MaquinaContador>();
		MaquinaContador mc = new MaquinaContador(maquina);
		addMaquina(mc);//maquinas.add(mc)
	}
	//constructor igual al anterior, añades el contador nada mas
	public DireccionMaquinas(String direccion, String maquina, int contador){
		this.direccion = direccion;
		this.maquinas = new BSTree<MaquinaContador>();
		MaquinaContador mc = new MaquinaContador(maquina, contador);
		addMaquina(mc);//maquinas.add(mc)
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public BSTree<MaquinaContador> getMaquina() {
		return maquinas;
	}
	
	public boolean addMaquina(MaquinaContador mc) {
		//return maquina.add(mc);
		if (maquinas.add(mc) == true)
			return true;
		return false;
	}
	
	public boolean addMaquinaWithFind(MaquinaContador mc){
		//hacemos dos veces la busquda. Primero miro si lo contiene y luego lo busco.
		/*if(! maquina.contains(mc)){
			addMaquina(mc);
			return true;
		}
		MaquinaContador busqueda = maquina.find(mc);
		busqueda.incrementarContador();
		return false;*/
		MaquinaContador busqueda = maquinas.find(mc);
		if(busqueda == null){
			addMaquina(mc);
			return true;
		}
		busqueda.incrementarContador();
		return false;
	}
	
	public int compareTo(Object otraDireccionMaquina){
		DireccionMaquinas dm = (DireccionMaquinas) otraDireccionMaquina;
		return this.direccion.compareTo(dm.direccion);
	}
	
	public boolean equals (Object obj){
		DireccionMaquinas dm = (DireccionMaquinas) obj;
		return this.direccion.equals(dm.direccion);
		
	}
	
	public String toString(){
		return "(" + direccion + "," + maquinas.toString() + ")"; //valdria poner maquina nada mas sin el toString
	}
	
}
