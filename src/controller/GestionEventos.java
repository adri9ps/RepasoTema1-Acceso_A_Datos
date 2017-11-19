package controller;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Libro.*;
import model.*;
import Libro.Libro;
import view.*;

public class GestionEventos {

	private GestionDatos model;
	private LaunchView view;
	private ActionListener actionListener_comparar, actionListener_buscar, actionListener_copiar, actionListener_enviar,
			actionListener_recu, actionListener_mostrar, actionListener_modificar, actionListener_longitud;

	public GestionEventos(GestionDatos model, LaunchView view) {
		this.model = model;
		this.view = view;
	}

	public void contol() {
		actionListener_comparar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				// TODO: Llamar a la funcion call_compararContenido
				try {
					call_compararContenido();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		view.getComparar().addActionListener(actionListener_comparar);
		// ------------------------------------
		actionListener_buscar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				// TODO: Llamar a la funci�n call_buscarPalabra

				try {
					call_buscarPalabra();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		};
		view.getBuscar().addActionListener(actionListener_buscar);
		// ------------------------------------
		actionListener_copiar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				// TODO: Llamar a la funcion call_copiarPorBytes

				try {
					call_copiarPorBytes();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		};
		view.getBtn_copiar().addActionListener(actionListener_copiar);
		// ------------------------------------
		actionListener_enviar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					creaLibro();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		view.getBtn_enviar().addActionListener(actionListener_enviar);
		// ------------------------------------
		actionListener_recu = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					recuperarLibro();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};

		view.getBtn_recu().addActionListener(actionListener_recu);
		// ------------------------------------
		actionListener_mostrar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					listarLibros();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		view.getBtn_MostrarLibros().addActionListener(actionListener_mostrar);
		// ------------------------------------
		actionListener_modificar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					// call_modificarPaginas();
					call_modificarTitulo();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		view.getBtn_ModificarPags().addActionListener(actionListener_modificar);
		// ------------------------------------
		actionListener_longitud = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {

				try {
					call_longitud();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		view.getBtnLongitud().addActionListener(actionListener_longitud);
	}

	private int call_compararContenido() throws FileNotFoundException, IOException {

		// TODO: Llamar a la funcion compararContenido de GestionDatos
		boolean coincidencia = model.compararContenido(view.getFichero1().getText(), view.getFichero2().getText());
		if (coincidencia) {
			view.getTextArea().setText("Los ficheros son iguales");
		} else {
			view.getTextArea().setText("Los ficheros son diferentes");
		}
		// TODO: Gestionar excepciones
		return 1;
	}

	private int call_buscarPalabra() throws IOException {

		// TODO: Llamar a la funci�n buscarPalabra de GestionDatos

		int linea = model.buscarPalabra(view.getFichero1().getText(), view.getPalabra().getText(),
				view.getPrimera().isSelected());

		if (linea == -1) {
			view.getTextArea().setText("La palabra " + view.getPalabra().getText() + " no se encuentra en "
					+ view.getFichero1().getText());

		} else {
			if (view.getPrimera().isSelected()) {
				view.getTextArea().setText("La palabra " + view.getPalabra().getText() + " se encuentra en la linea "
						+ linea + " y es la primera que se ha encontrado");

			} else {
				view.getTextArea().setText("La palabra " + view.getPalabra().getText() + " se encuentra en la linea "
						+ linea + " y es la última que se ha encontrado");

			}
		}

		// TODO: Gestionar excepciones
		return 1;
	}

	private int call_copiarPorBytes() throws FileNotFoundException, IOException {

		// TODO: Llamar a la funcion compararContenido de GestionDatos
		int bytes = model.copiarPorBytes(view.getFichero1().getText(), view.getFichero2().getText());
		if (bytes != -1) {
			view.getTextArea().setText("Se ha copiado correctamente y han sido copiados " + bytes + " bytes");
		} else {
			view.getTextArea().setText("No se ha podido copiar");
		}
		// TODO: Gestionar excepciones

		return 1;
	}

	private void creaLibro() throws IOException {

		if (view.getTxt_id().getText().isEmpty() || view.getTxt_titulo().getText().isEmpty()
				|| view.getTxt_autor().getText().isEmpty() || view.getTxt_anyo().getText().isEmpty()
				|| view.getTxt_editor().getText().isEmpty() || view.getTxt_paginas().getText().isEmpty()) {
			view.getTextArea().setText("Completa todos los campos");
		} else {

			Libro datos_libro = new Libro(view.getTxt_id().getText(), view.getTxt_titulo().getText(),
					view.getTxt_autor().getText(), view.getTxt_anyo().getText(), view.getTxt_editor().getText(),
					view.getTxt_paginas().getText());
			int estado = model.guardar_libro(datos_libro);
			view.getTextArea().setText("Se ha creado el libro correctamente");
			// libros.add(datos_libro);

		}

	}

	// Método que recupere datos de libro mediante ID
	private int recuperarLibro() throws IOException {
		Libro libro_recuperado = model.recuperar_Libro(view.getTxt_id().getText());

		if (libro_recuperado != null) {
			view.getTextArea().setText(libro_recuperado.mostrarDatos());
		}
		return 0;

	}

	private int listarLibros() throws IOException {

		ArrayList<Libro> listadoLibros = model.recuperar_todos();
		if (listadoLibros.size() != 0) {
			String pantalla = "Se han encontrado " + listadoLibros.size() + " libros.\n\n";
			for (int i = 0; i < listadoLibros.size(); i++) {
				pantalla += listadoLibros.get(i).mostrarDatos();

			}
			view.getTextArea().setText(pantalla);
		}
		return 0;

	}

	// #EJ1 Metodo del cambio de año del libro
	private int call_modificarPaginas() throws IOException {

		// Recuperamos el libro
		Libro libroRecuperado = null;
		String añoAnterior = null;
		boolean recuperado = false;
		try {
			// Obten el libro del fichero
			libroRecuperado = model.recuperar_Libro(view.getTxt_id().getText());
			// Obten el año al que estaba anteriormente
			añoAnterior = libroRecuperado.getAño_publicacion();
			// Establece la recuperacion como conseguida
			recuperado = true;
		} catch (Exception e) {
			// Si no se ha podido recuperar, marcalo como false y muestra el
			// error
			view.showError("Error al intentar recuperar el libro con ID " + view.getTxt_id().getText());
			recuperado = false;
		}

		if (recuperado) {
			// Comprobamos que el libro se ha recuperado
			if (libroRecuperado != null) {
				// Se ha recuperado el libro
				view.getTextArea().setText("Libro recuperado...");
				view.getTextArea().setText(libroRecuperado.mostrarDatos());

				// Procedemos a eliminar el anterior libro
				int estado = model.eliminarLibro(libroRecuperado);

				switch (estado) {
				case -3:
					view.showError("Error ya que no existe el libro");
					break;
				case -2:
					view.showError("Error al intentar eliminar el fichero anterior");
					break;
				}

			} else {
				view.showError("Se ha producido un error al intentar recuperar el libro con el ID: "
						+ view.getTxt_id().getText());
			}

			// Cambiamos al nuevo año
			libroRecuperado.setAño_publicacion(view.getTxt_nuevo().getText());

			// Procedemos a escribir nuevo libro
			int estado = model.guardar_libro(libroRecuperado);

			// Mostramos datos nuevo libro
			view.getTextArea().setText("Cambiando año...");
			view.getTextArea().setText(libroRecuperado.mostrarDatos());

			// Errores
			if (estado != 1) {
				// Se ha producido algun error
				switch (estado) {
				case -1:
					view.showError("Se ha producido un error desconocido");
					break;
				case -2:
					view.showError("Ya existe un libro con el mismo ID");
					break;
				}
			} else {
				view.getTextArea().setText(
						"Se ha cambiado el año " + añoAnterior + " al año " + libroRecuperado.getAño_publicacion());
			}

		}

		return 1;
	}

	// #EJ1 Metodo del cambio de año del libro
	private int call_modificarTitulo() throws IOException {

		// Recuperamos el libro
		Libro libroRecuperado = null;
		String tituloAnterior = null;
		boolean recuperado = false;
		try {
			// Obten el libro del fichero
			libroRecuperado = model.recuperar_Libro(view.getTxt_id().getText());
			// Obten el año al que estaba anteriormente
			tituloAnterior = libroRecuperado.getTitulo();
			// Establece la recuperacion como conseguida
			recuperado = true;
		} catch (Exception e) {
			// Si no se ha podido recuperar, marcalo como false y muestra el
			// error
			view.showError("Error al intentar recuperar el libro con ID " + view.getTxt_id().getText());
			recuperado = false;
		}

		if (recuperado) {
			// Comprobamos que el libro se ha recuperado
			if (libroRecuperado != null) {
				// Se ha recuperado el libro
				view.getTextArea().setText("Libro recuperado...");
				view.getTextArea().setText(libroRecuperado.mostrarDatos());

				// Procedemos a eliminar el anterior libro
				int estado = model.eliminarLibro(libroRecuperado);

				switch (estado) {
				case -3:
					view.showError("Error ya que no existe el libro");
					break;
				case -2:
					view.showError("Error al intentar eliminar el fichero anterior");
					break;
				}

			} else {
				view.showError("Se ha producido un error al intentar recuperar el libro con el ID: "
						+ view.getTxt_id().getText());
			}

			// Cambiamos al nuevo titulo
			libroRecuperado.setTitulo(view.getTxt_nuevo().getText());

			// Procedemos a escribir nuevo libro
			int estado = model.guardar_libro(libroRecuperado);

			// Mostramos datos nuevo libro
			view.getTextArea().setText("Cambiando titulo...");
			view.getTextArea().setText(libroRecuperado.mostrarDatos());

			// Errores
			if (estado != 1) {
				// Se ha producido algun error
				switch (estado) {
				case -1:
					view.showError("Se ha producido un error desconocido");
					break;
				case -2:
					view.showError("Ya existe un libro con el mismo ID");
					break;
				}
			} else {
				view.getTextArea().setText(
						"Se ha cambiado el título " + tituloAnterior + " al año " + libroRecuperado.getTitulo());
			}

		}

		return 1;
	}

	// #EJ2 Metodo para leer x numero de palabras
	private int call_longitud() throws IOException {
		int longitud = 0;
		try {
			longitud = Integer.parseInt(view.getTxt_long().getText());
		} catch (Exception e) {
			// No ha introducido un numero valido
			view.showError("No se ha introducido un numero de palabras valido");
			return -1;
		}
		int palabrasEncontradas = model.leerLongitudes(view.getFichero1().getText(), longitud);

		// Muestra palabras encontradass
		if (palabrasEncontradas != 0) {
			// Se han encontrado
			view.getTextArea().setText("Se han localizado " + palabrasEncontradas + " palabras con más de " + longitud
					+ " caracteres en el fichero " + view.getFichero1().getText());
		} else {
			// No se han encontrado palabras
			view.showError("Se han encontrado 0 palabras con " + longitud + " caracteres");
		}

		return 1;
	}

}
