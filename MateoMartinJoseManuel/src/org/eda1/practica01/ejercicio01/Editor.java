package org.eda1.practica01.ejercicio01;

import java.util.*;

import javax.management.RuntimeErrorException;

public class Editor {
     public final static char COMMAND_START = '$';
     public final static char DELIMITER = '%';

     public final static String INSERT_COMMAND = "$Insert";

     public final static String DELETE_COMMAND = "$Delete";

     public final static String LINE_COMMAND = "$Line";

     public final static String DONE_COMMAND = "$Done";
     
     public final static String LAST_COMMAND = "$Last";

     public final static String GETLINES_COMMAND = "$GetLines";
     
     public final static String CHANGE_COMMAND = "$Change";
     
     public final static String BAD_LINE_MESSAGE =
        "Error: a command should start with " + COMMAND_START + ".\n";

     public final static String BAD_COMMAND_MESSAGE =
        "Error: not one of the given commands.\n";

     public final static String INTEGER_NEEDED =
        "Error: The command should be followed by a blank space, " +
        "\nfollowed by an integer.\n";

     public final static String TWO_INTEGERS_NEEDED =
        "Error: The command should be followed by a blank space, " +
        "\nfollowed by an integer, followed by a blank space, " +
        "followed by an integer.\n";

     public final static String FIRST_GREATER =
        "Error: the first line number is greater than the second.\n";

     public final static String FIRST_LESS_THAN_ZERO =
        "Error: the first line number given is less than 0.\n";

     public final static String SECOND_TOO_LARGE =
        "Error: the second line number given is greater than the " +
        "\nnumber of the last line in the text.\n";

     public final static String M_LESS_THAN_ZERO =
        "Error: the number is less than 0.\n";

     public final static String M_TOO_LARGE =
        "Error: the number is larger than the number of lines in the text.\n";

     public final static String LINE_TOO_LONG =
        "Error: the line exceeds the maximum number of characters allowed, ";

     public final static String INCORRECT_DELIMITERS_NUMBER =
        "Error: Delimiter must occur three times. Please try again.\n";
     
     public final static String NO_DELIMITERS_BEGIN_END =
        "Error: Bad Expression format, delimiters should be at the beginning " + 
        "\nand at the end. Please try again.\n";

     public final static String TWO_CONSECUTIVE_DELIMITERS_AT_THE_BEGINNING =
        "Error: Bad Expression format, two consecutive delimiters " +
        "\nat the beginning. Please try again.\n";

     public final static int MAX_LINE_LENGTH = 80;


     protected LinkedList<String> text;

     protected ListIterator<String> current;

     protected boolean inserting;
     

     public Editor() {
         text = new LinkedList<String>();
         current = text.listIterator();
         inserting = false;
     }

     public String interpret (String s) {
    	 if (s.equals(""))
    		 throw new RuntimeException (BAD_LINE_MESSAGE);
    	 Scanner sc = new Scanner (s);
    	 String comando = sc.next();
    	 if (comando.equals(INSERT_COMMAND)) {
    		 inserting = true;
    		 return null;
    	 }
    	 if (comando.equals(DELETE_COMMAND)) {
    		 inserting = false;
    		 tryToDelete (sc);
    		 return null;
    	 }
    	 if (comando.equals(LINE_COMMAND)) {
    		 inserting = false;
    		 tryToSetCurrentLineNumber (sc);
    		 return null;
    	 }
    	 if (comando.equals(DONE_COMMAND)) {
    		 inserting = false;
    		 String cadena = done ();
    		 return cadena;
    	 }
    	 if (comando.equals(LAST_COMMAND)) {
    		 inserting = false;
    		 String cadena = last();
    		 return cadena;
    	 }
    	 if (comando.equals(GETLINES_COMMAND)) {
    		 inserting = false;
    		 String cadena = tryToGetLines(sc);
    		 return cadena;
    	 }
    	 if (comando.equals(CHANGE_COMMAND)) {
    		 inserting = false;
    		 tryToChange(sc);
    		 return null;
    	 }
    	 if (inserting == false) {
    		 if (comando.charAt(0) != COMMAND_START)
    			 throw new RuntimeException (BAD_LINE_MESSAGE);
    		 else
    			 throw new RuntimeException(BAD_COMMAND_MESSAGE);
    	 }
    	 else {
    		 insert (s);
    	 }
    	 return null;


     }
     
     protected void tryToDelete (Scanner sc) {
    	 if ( ! sc.hasNextInt())
    		 throw new RuntimeException (TWO_INTEGERS_NEEDED);
    	 int m = sc.nextInt();
    	 if ( ! sc.hasNextInt())
    		 throw new RuntimeException (TWO_INTEGERS_NEEDED);
    	 int n = sc.nextInt();
    	 delete (m, n);
     }
     
     protected void tryToSetCurrentLineNumber (Scanner sc) {
    	 if ( ! sc.hasNextInt()) 
    		 throw new RuntimeException(INTEGER_NEEDED);
    	 int m = sc.nextInt();
    	 setCurrentLineNumber (m);
     }
     
     protected void insert (String s) {
    	 if (s.length() > 80)
    		 throw new RuntimeException (LINE_TOO_LONG);
    	 current.add(s);
     }
     
     protected void delete (int m, int n) {
    	 if (m < 0)
    		 throw new RuntimeException(FIRST_LESS_THAN_ZERO);
    	 if (n >= text.size())
    		 throw new RuntimeException(SECOND_TOO_LARGE);
    	 if (m > n)
    		 throw new RuntimeException(FIRST_GREATER);
    	 for (int i = m; i <= n; i++)
    		 text.remove(m);
    	 setCurrentLineNumber(m);
     }
     
     protected void setCurrentLineNumber (int m) {
    	 if (m < 0)
    		 throw new RuntimeException(M_LESS_THAN_ZERO);
    	 if (m > text.size())
    		 throw new RuntimeException(M_TOO_LARGE);
    	 current = text.listIterator(m);
     }
     
     protected String done() {
    	 String cadena = "";
    	 ListIterator<String> iterator = (ListIterator) text.iterator();
    	 while (iterator.hasNext()) {
    		 if (iterator.nextIndex() == current.nextIndex())
    			 cadena = cadena + ">";
    		 else
    			 cadena = cadena + " ";
    		 cadena = cadena + "  ";
    		 String linea = iterator.next();
    		 cadena = cadena + linea;
    		 cadena = cadena + "\n";
    	 }
    	 if (current.nextIndex() == text.size())
    		 cadena = cadena + ">  \n";
    	 return cadena;
     }
     
     protected String last () {
    	 String cadena = Integer.toString(text.size()-1);
    	 //String cadena = (text.size()-1) + "";
    	 return cadena;
     }
     
     protected String tryToGetLines (Scanner sc) {
    	 if ( ! sc.hasNextInt())
    		 throw new RuntimeException (TWO_INTEGERS_NEEDED);
    	 int m = sc.nextInt();
    	 if ( ! sc.hasNextInt())
    		 throw new RuntimeException (TWO_INTEGERS_NEEDED);
    	 int n = sc.nextInt();
    	 String cadena = getLines(m, n);    	 
    	 return cadena;
     }
     
     protected String getLines (int m, int n) {
    	 if (m < 0)
    		 throw new RuntimeException(FIRST_LESS_THAN_ZERO);
    	 if (m > n)
    		 throw new RuntimeException(FIRST_GREATER);
    	 if (n >= text.size())
    		 throw new RuntimeException(SECOND_TOO_LARGE);
    	 String cadena = "";
    	 for (int i=m; i<=n; i++) {
    		 cadena = cadena + i + "\t";
    		 cadena = cadena + text.get(i);
    		 if (i < n)
    			 cadena = cadena + "\n";
    	 }
    	 return cadena; 
     }
     
     protected void tryToChange (Scanner sc) {
   
    	 String resto = sc.next();
    	 while (sc.hasNext()) {
    		 resto = resto + " " + sc.next();
    	 }
    	 change (resto);
     }
     
     protected void change (String parameter) {
    	 //lo primero que hago es contar cuantos delimitadores hay
    	 //para saber si hay mas de tres
    	 int cont = 0;
    	 int posicion = parameter.indexOf(DELIMITER);
    	 while (posicion != -1) {
    		 cont++;
    		 posicion = parameter.indexOf(DELIMITER, posicion+1);
    	 }
    	 if (cont != 3) {
    		 throw new RuntimeException(INCORRECT_DELIMITERS_NUMBER);
    	 }
    	 //compruebo si empieza por delimitador
    	 if (parameter.charAt(0) != DELIMITER)
    	 //if (parameter.substring(0, 1).equals(DELIMITER))
        	 	 throw new RuntimeException(NO_DELIMITERS_BEGIN_END);
    	 if (parameter.charAt(parameter.length()-1) != DELIMITER)
    		 throw new RuntimeException(NO_DELIMITERS_BEGIN_END);
    	 //quito los delimitadores del principio y final
    	 parameter = parameter.substring(1, parameter.length()-1);
    	 //si al principio hay otro %, es error
    	 if (parameter.charAt(0) == DELIMITER)
    		 throw new RuntimeException (TWO_CONSECUTIVE_DELIMITERS_AT_THE_BEGINNING);
    	 //busco la posicion del % del medio
    	 int n = parameter.indexOf(DELIMITER);
    	
    	 String antes = parameter.substring(0, n);
    	 String despues = parameter.substring(n+1);
    	 String cadena = text.get(current.nextIndex());
    	 cadena = cadena.replace(antes, despues);
    	 text.set(current.nextIndex(), cadena);
     }
}
