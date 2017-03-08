package org.eda1.prueba01_2;

import java.util.Comparator;

public class HorasComparatorGreater implements Comparator<Trabajador> {

	@Override
    public int compare(Trabajador t1, Trabajador t2){
		Double notaMedia1 = t1.mediaHorasMes();
    	Double notaMedia2 = t2.mediaHorasMes();
    	if (notaMedia1 < notaMedia2) return 1; 
        if (notaMedia1 > notaMedia2) return -1;
        return t1.compareTo(t2);
	  } 
}
