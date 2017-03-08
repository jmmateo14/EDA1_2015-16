package org.eda1.actividad02.ejercicio02;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import org.eda1.actividad02.ejercicio01.Heap;

public class HeapSort {
	
	public static void main(String[] args){
		
	}

	public static <T> void sortHeap(ArrayList<T> aList, Comparator<T> comp) {
		Heap<T> monticulo = new Heap<T>(comp);
		for(int i = 0; i < aList.size(); i++){
			monticulo.add(aList.get(i));
		}
		for(int i = 0; i < aList.size(); i++){
			aList.set(i, monticulo.removeMin());
		}
	}

	private static <T> void siftDown(ArrayList<T> aList, int first, int last, Comparator<T> comp){
		int parent = first, child = (parent << 1) + 1;
		while (child < last + 1) {
			if (child < last && comp.compare(aList.get(child), aList.get(child + 1)) > 0)
				child++; 
			if (comp.compare(aList.get(child), aList.get(parent)) >= 0)
				break;
			//swap(parent, child);
			T aux = aList.get(parent);
			aList.set(parent, aList.get(child));
			aList.set(child, aux);
			parent = child;
			child = (parent << 1) + 1; 
		}
		
	}
	
	public static <T> void heapSort(ArrayList<T> aList, Comparator<T> comp) {
		//ordenar array como heap es hacer el makeHeap
		for(int i = aList.size()/2; i >= 0; i--){
			siftDown(aList, i, aList.size() - 1, comp);
		}
		//para cada posicion del array desde la ultima a la primera
		for(int i = aList.size() - 1; i > 0; i--){
			//intercambio con la posicion 0
			T aux = aList.get(0);
			aList.set(0, aList.get(i));
			aList.set(i, aux);
			//hundo desde la posicion 0 a la i - 1 
			siftDown(aList, 0, i - 1, comp);
		}
		Collections.reverse(aList);
		
	}

}
