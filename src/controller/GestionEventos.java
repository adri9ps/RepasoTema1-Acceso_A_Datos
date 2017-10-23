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
import view.*;

public class GestionEventos {

	private GestionDatos model;
	private LaunchView view;
	private ActionListener actionListener_comparar, actionListener_buscar, actionListener_copiar, actionListener_enviar,
			actionListener_recu, actionListener_mostrar;

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
				// TODO: Llamar a la funcion call_compararContenido
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
				// TODO: Llamar a la funcion call_compararContenido
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
}
