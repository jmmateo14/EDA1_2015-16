package org.eda1.prueba01_2;


import java.util.ArrayList;
import java.util.Locale;

public class Trabajador implements Comparable<Trabajador>{
	private String nombre;
	private ArrayList<Double> horasDia;
	
	private String format(double num){
		return String.format(Locale.US, "%.2f", num);
	}
	
	public Trabajador (String nombre, ArrayList<Double> horasDia){
		this.nombre = nombre.toUpperCase();
		this.horasDia = horasDia;
		if (horasDia != null)
			this.horasDia = new ArrayList<Double>(horasDia);
		else
			this.horasDia = new ArrayList<Double>();
	}
	
	public double horasMes(){
		Double resultado = 0.0;
		if (horasDia.isEmpty()) return 0.0;
		for (Double v : horasDia){
			if (v == null) continue;
			resultado += v;
		}
		return resultado;
	}
	
	public double mediaHorasMes(){
		return this.horasMes() / 30;
	}
	
	@Override
	public String toString(){
		return this.nombre + " <" + format(this.horasMes()) + ", " + format(this.mediaHorasMes()) + ">";
	}
	
	public String toStringExtend(){
		return this.nombre + " --> " + this.horasDia.toString();
	}
	
	@Override
	public int compareTo(Trabajador otro){
		//Orden natural --> Apellido1 Apellido2 Nombre ¿split?
		
		return 0;
	}
}