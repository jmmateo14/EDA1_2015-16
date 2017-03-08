package org.eda1.prueba01_2;

import java.util.Comparator;

public class HorasComparatorLess implements Comparator<Trabajador> {

	@Override
    public int compare(Trabajador t1, Trabajador t2){
		Double horaMedia1 = t1.mediaHorasMes();
    	Double horaMedia2 = t2.mediaHorasMes();
    	if (horaMedia1 < horaMedia2) return -1; 
        if (horaMedia1 > horaMedia2) return +1;
        return t1.compareTo(t2); 
	} 
}
