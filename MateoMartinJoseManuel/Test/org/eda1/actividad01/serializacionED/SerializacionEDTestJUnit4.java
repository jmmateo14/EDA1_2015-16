package org.eda1.actividad01.serializacionED;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.eda1.actividad01.serializacionED.ArrayList;
import org.eda1.actividad01.serializacionED.CiudadBarrios;
import org.eda1.actividad01.serializacionED.ProgramaSerializacion;
import org.junit.Before;
import org.junit.Test;

public class SerializacionEDTestJUnit4 {

	String directorioEntrada;

	@Before
	public void setUp() throws Exception {

		directorioEntrada = System.getProperty("user.dir");

		directorioEntrada = directorioEntrada + File.separator + "src"
				+ File.separator + "org" + File.separator + "eda1"
				+ File.separator + "actividad01" + File.separator
				+ "serializacionED" + File.separator;
	}

	@SuppressWarnings("unchecked")
	@Test
	public void test() throws FileNotFoundException, IOException, ClassNotFoundException {
		String inputFile = directorioEntrada + "ciudadBarrios.txt";
		String inputFileEmpty = directorioEntrada + "ciudadBarriosVacia.txt";
		String storeFile = directorioEntrada + "andalucia.dat";

		ArrayList<CiudadBarrios> cB = null;
		
		assertEquals(ProgramaSerializacion.mostrarEstructura(cB), null);

		cB = ProgramaSerializacion.cargarArchivo(inputFileEmpty);
		assertEquals(ProgramaSerializacion.mostrarEstructura(cB), "[]");

		cB = ProgramaSerializacion.cargarArchivo(inputFile);
		
		String salida = "";
		salida += "[Almeria, 36.5, -2.28, {Regiones, Nueva_Andalucia, Cruz_Caravaca, Araceli, Villablanca, Barrio_Alto, Paseo_Almeria, Zapillo}]" + "\n";
		salida += "[Granada, 37.11, -3.35, {Albaicin, Sacromonte, Los_Pajaritos, Chana, Cartuja, Zaidin}]" + "\n";
		salida += "[Malaga, 36.43, -4.25, {La_Goleta, El_Palo, La_Trinidad, La_Malagueta, La_Merced}]" + "\n";
		salida += "[Jaen, 37.46, -3.47, {La_Gloria, San_Ildefonso, Juderia, Las_Fuentezuelas}]"	+ "\n";
		salida += "[Cordoba, 37.53, -4.47, {Del_Carmen, Arenal, San_Francisco, Fuensanta}]"	+ "\n";
		salida += "[Sevilla, 37.23, -5.59, {Triana, Pineda, San_Pablo, Nervion, Macarena}]"	+ "\n";
		salida += "[Cadiz, 36.32, -6.18, {San_Juan, Mentidero, Santa_Maria}]" + "\n";
		salida += "[Huelva, 37.16, -6.57, {Reina_Victoria, Moret, Principe_Felipe}]" + "\n";

		assertEquals(ProgramaSerializacion.mostrarEstructura(cB), salida);

		// object stream connected to file "storeFile" for output
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storeFile));

		// send object and close down the output stream
		oos.writeObject(cB);
		oos.flush();
		oos.close();

		
		cB.add(new CiudadBarrios("Murcia", 37.59, -1.07));
		cB.add(new CiudadBarrios("Albacete", 39.00, -1.52));

		String salida1 = salida;
		salida1 += "[Murcia, 37.59, -1.07, {}]" + "\n";
		salida1 += "[Albacete, 39.0, -1.52, {}]" + "\n";


		assertEquals(ProgramaSerializacion.mostrarEstructura(cB), salida1);

		// object stream connected to file "storeFile" for output
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(storeFile));

		// reconstruct object and allocate new current object
		ArrayList<CiudadBarrios> rCB;
		rCB = (ArrayList<CiudadBarrios>) ois.readObject();

		// output object after recall from the file
		assertEquals(ProgramaSerializacion.mostrarEstructura(rCB), salida);

		ois.close();
		

	}

}
