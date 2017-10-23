package model;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Libro.Libro;
import view.LaunchView;

public class GestionDatos {
	private LaunchView view;
	FileInputStream fis;
	FileOutputStream fos;
	int numBytes;

	public GestionDatos() {

	}

	// TODO: Implementa una funcion para abrir ficheros
	public BufferedReader abrirFichero(String fichero) {
		try {
			FileReader fileR = new FileReader(fichero);
			BufferedReader fileBR = new BufferedReader(fileR);
			return fileBR;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al abrir " + fichero);
			return null;
		}
	}

	// TODO: Implementa una funcion para cerrar ficheros
	public void cerrarFichero(BufferedReader fichero) {
		try {
			fichero.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("No se ha podido cerrar " + fichero);
		}
	}

	public boolean compararContenido(String fichero1, String fichero2) throws IOException, FileNotFoundException {
		// TODO: Implementa la funcion
		String sCadena1;
		String sCadena2;
		BufferedReader bfr1 = abrirFichero(fichero1);
		BufferedReader bfr2 = abrirFichero(fichero2);

		if (!fichero1.isEmpty() || !fichero2.isEmpty()) {

			sCadena1 = bfr1.readLine();
			sCadena2 = bfr2.readLine();
			while ((sCadena1 != null) && (sCadena2 != null)) {

				if (!sCadena1.equals(sCadena2)) {
					return false;
				}

				sCadena1 = bfr1.readLine();
				sCadena2 = bfr2.readLine();

			}

			cerrarFichero(bfr1);
			cerrarFichero(bfr2);
		} else {
			view.getTextArea().setText("No has introducido algún fichero");
		}
		return true;
	}

	public int buscarPalabra(String fichero1, String palabra, boolean primera_aparicion) throws IOException {
		// TODO: Implementa la funcion
		BufferedReader archivo = abrirFichero(fichero1);
		String cadena;
		if (!palabra.isEmpty()) {
			if (primera_aparicion) {
				int i = 0;
				while ((cadena = archivo.readLine()) != null) {
					i++;
					if (cadena.equals(palabra)) {
						cerrarFichero(archivo);
						return i;
					}

				}
			} else {
				int i = 0, line = -1;
				while ((cadena = archivo.readLine()) != null) {
					i++;
					if (cadena.equals(palabra)) {
						line = i;
					}
				}
				if (line != -1) {
					return line;
				} else {
					return -1;
				}
			}
		}
		return -1;
	}

	public void intentarCerrar(Closeable c) {
		try {
			c.close();
		} catch (IOException e) {

		}
	}

	public int copiarPorBytes(String fichero1, String fichero2) throws IOException, FileNotFoundException {
		// TODO: Implementa la funcion
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(fichero1); // -> Archivo a copiar
			fos = new FileOutputStream(fichero2); // -> Copia del archivo
			byte[] buffer = new byte[1024];
			int len = 0;

			fis.read(buffer);
			fos.write(buffer);
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
				numBytes = numBytes + 1024;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			intentarCerrar(fis);
			intentarCerrar(fos);

		}
		return numBytes;
	}

	public int guardar_libro(Libro libro) throws IOException {

		File ficheroLibro = new File("InfoLibro" + libro.getId_libro() + ".txt");

		if (ficheroLibro.exists()) {
			view.getTextArea().setText("El fichero ya existe");
		} else {

			ficheroLibro.createNewFile();

			ObjectOutputStream objFile = new ObjectOutputStream(new FileOutputStream(ficheroLibro));

			objFile.writeObject(libro);
		}
		return 1;

	}
	public Libro recuperar_Libro(String identificador) throws FileNotFoundException, IOException{
		
		File ficheroLibro = new File("InfoLibro" + identificador + ".txt");
		if(!ficheroLibro.exists()){
			return null;
		}
		ObjectInputStream obj = new ObjectInputStream(new FileInputStream(ficheroLibro));
		Libro lib_recuperado;
		try {
			lib_recuperado = (Libro) obj.readObject();
		} catch (ClassNotFoundException e) {
			return null;
			
		}
		obj.close();
		return lib_recuperado;
		
	}
	
	public ArrayList<Libro> recuperar_todos() throws FileNotFoundException, IOException{
		ArrayList<Libro> todos = new ArrayList<Libro>();
		File carpeta = new File(".");
		String [] archivos = carpeta.list();
		
		for (int i = 0; i < archivos.length; i++) {
			if (archivos [i].startsWith("InfoLibro")){
				String id = archivos[i].substring(9, archivos[i].length()-4);
				System.out.println(id);
				todos.add(recuperar_Libro(id));
			}
			
		}
		
		return todos;
		
	}
}
