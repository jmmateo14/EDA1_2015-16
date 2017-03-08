package org.eda1.actividad02.ejercicio01;

import java.util.*;

public class Heap<T> {
	protected static final int DEFAULT_INITIAL_CAPACITY = 7;

	protected ArrayList<T> theHeap;

	protected Comparator<T> comparator;

	/**
	 * Inicializa el Heap a una capacidad inicial de initialCapacity, y con
	 * elementos que están ordenados por un objeto Comparator dado.
	 * 
	 * @param initialCapacity
	 *            - Capacidad inicial para el Heap.
	 * @param comp
	 *            - el objeto Comparator.
	 * 
	 */
	public Heap(int initialCapacity, Comparator<T> comp) {
		if (initialCapacity < 1)
			throw new IllegalArgumentException();
		theHeap = new ArrayList<T>(initialCapacity);
		comparator = comp;
	} // constructor con una capacidad inicial y un Comparator

	/**
	 * Inicializa el Heap con una capacidad inicial por defecto
	 * DEFAULT_INITIAL_CAPACITY y con elementos en una clase que implemente la
	 * interfaz Comparable
	 * 
	 */
	public Heap() {
		this(DEFAULT_INITIAL_CAPACITY, null);
	} // constructor por defecto

	/**
	 * Inicializa el Heap a una capacidad inicial de initialCapacity, y con
	 * elementos en una clase que implementa la interface Comparable
	 * 
	 * @param initialCapacity
	 *            - la capacidad inicial del Heap.
	 * 
	 */
	public Heap(int initialCapacity) {
		this(initialCapacity, null);
	} // constructor con una capacidad inicial

	/**
	 * Inicializa el Heap a una capacidad inicial de initialCapacity, y con
	 * elementos comparados según el objeto Comparator comp.
	 * 
	 * @param comp
	 *            - el objeto Comparator utilizado para comparar elementos en el
	 *            Heap
	 * 
	 */
	public Heap(Comparator<T> comp) {
		this(DEFAULT_INITIAL_CAPACITY, comp);
	} // constructor con parámetro Comparator

	/**
	 * Inicializa este Heap con un objeto Heap pasado por parámetro Los
	 * elementos en este Heap se compararán como se especifiquen en el objeto
	 * Heap que se pasa como parámetro The worstTime(n) is O(n), donde n es el
	 * número de elementos en el Heap pasado como parámetro.
	 * 
	 * @param otherHeap
	 *            - el Heap que se va a copiar en en objeto heap actual
	 * 
	 */
	public Heap(Heap<T> otherHeap) {
		theHeap = new ArrayList<T>(otherHeap.theHeap);
		comparator = otherHeap.comparator;
	} // constructor copia

	/**
	 * Devuelve el número de elementos en el Heap
	 * 
	 * @return número de elementos que hay en este Heap.
	 * 
	 */
	public int size() {
		return theHeap.size();
	} // metodo size

	/**
	 * Determina si el Heap no tiene elementos (está vacio).
	 * 
	 * @return true - si el heap no tiene elementos, en otro caso false;
	 * 
	 */
	public boolean isEmpty() {
		return theHeap.isEmpty();
	} // método isEmpty

	/**
	 * Inserta un elemento en el Heap. The worstTime(n) is O(n) and
	 * averageTime(n) is constant.
	 * 
	 * @param element
	 *            - el elemento que va a ser insertado en el Heap
	 * 
	 */
	public void add(T element) {
		theHeap.add(element);
		siftUp();
	} // metodo add

	/**
	 * Restaura las propiedades del Heap, empezando desde el final hasta la raiz
	 * 
	 * The worstTime(n) is O(log n), and averageTime(n) is constant.
	 * 
	 */
	protected void siftUp() {
		int child = theHeap.size() - 1, parent;

		while (child > 0) {
			parent = (child - 1) >> 1;	// >> 1 is slightly faster than / 2
										// => parent = (child - 1) / 2
			if (compare(theHeap.get(child), theHeap.get(parent)) >= 0)
				break;
			swap(parent, child);
			child = parent;
		}
	} // metodo siftUp

	/**
	 * Compara dos elementos dados según Comparable o un objeto Comparator
	 * 
	 * @param element1
	 *            - uno de los elementos a comparar.
	 * @param element2
	 *            - el otro elemento a comparar.
	 * 
	 * @return un entero negativo, 0, o un entero positivo, dependiendo de si
	 *         element1 es menor que, igual a o mayor que element2.
	 * 
	 */
	protected int compare(T element1, T element2) {
		return (comparator == null ? ((Comparable<T>) element1)
				.compareTo(element2) : comparator.compare(element1, element2));
		// if (comparator == null )
		// 	return ((Comparable<T>)element1).compareTo(element2);
		// else
		// 	return comparator.compare(element1, element2);
	} // metodo compare

	/**
	 * Intercambia dos elementos del Heap (parent y child).
	 * 
	 * @param parent
	 *            - el índice del elemento padre (parent).
	 * @param child
	 *            - el índice del elemento hijo (child).
	 * 
	 */
	protected void swap(int parent, int child) {
		T temp = theHeap.get(parent);//doy a mi vb aux el valor del padre
		theHeap.set(parent, theHeap.get(child));//el padre
		theHeap.set(child, temp);
	} // metodo swap

	/**
	 * Devuelve el elemento con el menor valor del Heap.
	 * 
	 * @return el elemento con el menor valor del Heap.
	 * 
	 * @throws NoSuchElementException
	 *             - si el Heap está vacío.
	 * 
	 */
	public T getMin() {
		if (theHeap.isEmpty())
			throw new NoSuchElementException("Heap removeMin(): empty heap");

		return theHeap.get(0);
	} // metodo getMin
	
	public T getValue(int i){
		if(i < 0 || i >= size())
			return null;
		return theHeap.get(i);
	}
	

	/**
	 * Elimina el elemento con el menor valor del Heap. The worstTime(n) is
	 * O(log n).
	 * 
	 * @return el elemento eliminado.
	 * 
	 * @throws NoSuchElementException
	 *             - si el Heap está vacío.
	 * 
	 */
	public T removeMin() {
		if (theHeap.isEmpty())
			throw new NoSuchElementException("Heap removeMin(): empty heap");

		T minElem = theHeap.get(0);//guardo la raiz
		theHeap.set(0, theHeap.get(theHeap.size() - 1));//cambio raiz por el ultimo
		theHeap.remove(theHeap.size() - 1);//me cargo el ultimo
		siftDown(0);//y hundo
		return minElem;
	} // metodo removeMin

	/**
	 * Restaura las propiedades del Heap (hundir) The worstTime(n) is O(log n).
	 * 
	 * @param start
	 *            - el índice del Heap donde va a empezar la restauración de la
	 *            propiedad.
	 * 
	 */
	protected void siftDown(int start) {
		int parent = start, child = (parent << 1) + 1;	// parent << 1 is
														// slightly faster than
														// parent * 2
														// => (2 * parent) + 1

		while (child < theHeap.size()) {
			if (child < theHeap.size() - 1
					&& compare(theHeap.get(child), theHeap.get(child + 1)) > 0)
				child++; // child is the right child (child = (2 * parent) + 2)
			if (compare(theHeap.get(child), theHeap.get(parent)) >= 0)
				break;
			swap(parent, child);
			parent = child;
			child = (parent << 1) + 1; // => child = (2 * parent) + 1
		}
	} // function siftDown
	

	public String toString(){
		return theHeap.toString();
	}
	
	protected void siftDown(int start, int end){
		int parent = start, child = (parent << 1) + 1; 

		while (child < end + 1) {
			if (child < end && compare(theHeap.get(child), theHeap.get(child + 1)) > 0)
				child++; // child is the right child (child = (2 * parent) + 2)
			if (compare(theHeap.get(child), theHeap.get(parent)) >= 0)
				break;
			swap(parent, child);
			parent = child;
			child = (parent << 1) + 1; // => child = (2 * parent) + 1
		}
	}
	
	public void assign(int index, T value){
		if(index < 0 || index >= size()){//si no es correcto 
			theHeap.add(value);//añado al final
			return;
		}
		T aux = theHeap.get(index);
		theHeap.set(index, value);
		if(comparator.compare(aux, value) < 0)
			siftDown(index);
		else
			siftUp(index);
	}
	
	public void makeHeap(){
		for(int i = size()/2; i >= 0; i--){
			siftDown(i);
		}
	}
	
	public boolean isHeapTopDown(){
		
	return false;	
	}
	
	public boolean isHeap(){
		for(int i = 1; i < size(); i++){
			int parent = (i - 1) >> 1; //int parent = (i - 1)/2;
		if(comparator.compare(getValue(parent), getValue(i)) > 0)
			return false;
		}
		return true;
		
	}
	
	public String branchMinSum(){
		int min;
		String caminoMin;
		
		min = Integer.MAX_VALUE;
		caminoMin = "";
		for (int i = size()/2; i < size(); i++) {
			int suma = 0;
			String camino = "";
			int j = i;
			while(j >= 0){
				camino = camino + getValue(j) + " ";
				suma = suma + (Integer) getValue(j);
				j = (j - 1) >> 1;
			}
			if(suma < min){
				min = suma;
				caminoMin = camino;
			}
		}
		return "<" + min + "> --- " + caminoMin; 
	}
	
	public void showHeap(){
		
	}
	
	public boolean increaseKey(int i, Integer x){
		if (i < 0 || i >= size()) 
			return false;
		Integer value = (Integer) getValue(i);
		value = value + x;
		assign(i, (T)value);
		return true;
	}
	
	protected void siftUp(int start){
		int child = start, parent;//cambio theHeap.size() - 1, por start

		while (child > 0) {
			parent = (child - 1) >> 1;	// >> 1 is slightly faster than / 2
										// => parent = (child - 1) / 2
			if (compare(theHeap.get(child), theHeap.get(parent)) >= 0)
				break;
			swap(parent, child);
			child = parent;
		}
	}
	
	public boolean decreaseKey(int i, Integer x){
		if (i < 0 || i >= size()) 
			return false;
		Integer value = (Integer) getValue(i);
		value = value - x;
		assign(i, (T)value);
		return true;
	}
	
	public boolean replaceKey(int i, T x){
		if (i < 0 || i >= size()) 
			return false;
		assign(i, (T)x);
		return true;
	}
	
	public boolean delete(int i){
		if (i < 0 || i >= size()) 
			return false;
		T x = getValue(size() - 1);
		theHeap.remove(size() - 1);
		replaceKey(i, x);
		return true;
	}
	
	

} // class Heap

