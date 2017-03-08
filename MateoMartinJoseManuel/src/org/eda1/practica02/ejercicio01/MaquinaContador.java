package org.eda1.practica02.ejercicio01;
//toda clase que tenga un compareTo debe implementar Comparable
public class MaquinaContador implements Comparable{

	private String maquina;
	private int contador;
	
	public MaquinaContador (String maquina, int contador){
		this.maquina = maquina;
		this.contador = contador;
	}
	
	public MaquinaContador(String maquina){
		this.maquina = maquina;
		this.contador = 1;
		//contador = 1;
	}
	
	public MaquinaContador(){
		//vacio me inicializa los valos por defecto, es lo mismo a lo siguente.
		maquina = "";
		contador = 0;
	}

	public String getMaquina() {
		return maquina;
	}

	public void setMaquina(String maquina) {
		this.maquina = maquina;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}
	
	public void incrementarContador(){
		contador++;
	}
	
	public int compareTo(Object otraMaquinaContador){
		MaquinaContador mc = (MaquinaContador) otraMaquinaContador;
		return this.maquina.compareTo(mc.maquina);
	}
	
	public boolean equals (Object obj){
		MaquinaContador mc = (MaquinaContador) obj;
		return this.maquina.equals(mc.maquina);
	}
	//hay que mirarlo en el text.
	public String toString(){
		return "[" + maquina + ", " + contador + "]";
	}
	
	
}
