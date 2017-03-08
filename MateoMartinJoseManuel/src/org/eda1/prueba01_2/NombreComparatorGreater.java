package org.eda1.prueba01_2;

import java.util.Comparator;

public class NombreComparatorGreater implements Comparator<Trabajador> {

	@Override
    public int compare(Trabajador t1, Trabajador t2){
		int res;
		res = t1.toStringExtend().compareTo(t2.toStringExtend());
		if (res < 0) return 1;
		if (res > 0) return -1;
		
		return t1.compareTo(t2);
	} 
}
