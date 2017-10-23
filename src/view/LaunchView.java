package view;

import java.awt.Dimension;

import javax.swing.*;
import java.awt.BorderLayout;

public class LaunchView extends JFrame {

	private JButton comparar,buscar;
	private JTextArea textArea;
	private JTextField fichero1,fichero2,palabra;
	private JLabel label_f1,label_f2,label_pal;
	private JCheckBox primera;
	
	private JPanel panel;
	public JButton getBtn_copiar() {
		return btn_copiar;
	}

	public void setBtn_copiar(JButton btn_copiar) {
		this.btn_copiar = btn_copiar;
	}

	private JButton btn_copiar;
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private JTextField txt_id;
	private JLabel lblTtulo;
	private JTextField txt_titulo;
	private JLabel lblNewLabel_1;
	public JTextField getTxt_id() {
		return txt_id;
	}

	public void setTxt_id(JTextField txt_id) {
		this.txt_id = txt_id;
	}

	public JTextField getTxt_titulo() {
		return txt_titulo;
	}

	public void setTxt_titulo(JTextField txt_titulo) {
		this.txt_titulo = txt_titulo;
	}

	public JLabel getLblNewLabel_1() {
		return lblNewLabel_1;
	}

	public void setLblNewLabel_1(JLabel lblNewLabel_1) {
		this.lblNewLabel_1 = lblNewLabel_1;
	}

	public JTextField getTxt_autor() {
		return txt_autor;
	}

	public void setTxt_autor(JTextField txt_autor) {
		this.txt_autor = txt_autor;
	}

	public JTextField getTxt_anyo() {
		return txt_anyo;
	}

	public void setTxt_anyo(JTextField txt_anyo) {
		this.txt_anyo = txt_anyo;
	}

	public JTextField getTxt_editor() {
		return txt_editor;
	}

	public void setTxt_editor(JTextField txt_editor) {
		this.txt_editor = txt_editor;
	}

	public JTextField getTxt_paginas() {
		return txt_paginas;
	}

	public void setTxt_paginas(JTextField txt_paginas) {
		this.txt_paginas = txt_paginas;
	}

	public JButton getBtn_enviar() {
		return btn_enviar;
	}

	public void setBtn_enviar(JButton btn_enviar) {
		this.btn_enviar = btn_enviar;
	}

	private JTextField txt_autor;
	private JLabel lblNewLabel_2;
	private JTextField txt_anyo;
	private JLabel lblNewLabel_3;
	private JTextField txt_editor;
	private JLabel lblNewLabel_4;
	private JTextField txt_paginas;
	private JButton btn_enviar;
	private JButton btn_recu;
	private JButton btn_MostrarLibros;
	
	public LaunchView() {
		
		setBounds(200,200,1000,450);
		setTitle("Proyecto Buffers");	
		panel = new JPanel();
		
		comparar = new JButton("Comparar contenido");
		comparar.setPreferredSize(new Dimension(150, 26));
		buscar = new JButton("Buscar palabra");
		buscar.setPreferredSize(new Dimension(150, 26));
					
		fichero1 = new JTextField("",10);
		fichero2 = new JTextField("",10);
		palabra = new JTextField("",10);
		
		label_f1 = new JLabel("Fichero 1:");
		label_f2 = new JLabel("Fichero 2:");
		label_pal = new JLabel("Palabra:");
		
		primera = new JCheckBox("Primera aparición");

		textArea = new JTextArea(20, 80);
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(50,50,50,50);
		
		textArea.setEditable(false);		
		
		panel.add(comparar);
		panel.add(buscar);
		
		btn_copiar = new JButton("Copiar archivos");
		panel.add(btn_copiar);
		panel.add(label_f1);
		panel.add(fichero1);
		panel.add(label_f2);
		panel.add(fichero2);
		panel.add(label_pal);
		panel.add(palabra);
		panel.add(primera);
		panel.add(scroll);
		
        // Añadimos el JPanel al JFrame
        this.getContentPane().add(panel);		
        
        panel_1 = new JPanel();
        getContentPane().add(panel_1, BorderLayout.SOUTH);
        
        lblNewLabel = new JLabel("ID:");
        panel_1.add(lblNewLabel);
        
        txt_id = new JTextField();
        panel_1.add(txt_id);
        txt_id.setColumns(10);
        
        lblTtulo = new JLabel("Título:");
        panel_1.add(lblTtulo);
        
        txt_titulo = new JTextField();
        panel_1.add(txt_titulo);
        txt_titulo.setColumns(10);
        
        lblNewLabel_1 = new JLabel("Autor:");
        panel_1.add(lblNewLabel_1);
        
        txt_autor = new JTextField();
        panel_1.add(txt_autor);
        txt_autor.setColumns(10);
        
        lblNewLabel_2 = new JLabel("Anyo:");
        panel_1.add(lblNewLabel_2);
        
        txt_anyo = new JTextField();
        panel_1.add(txt_anyo);
        txt_anyo.setColumns(10);
        
        lblNewLabel_3 = new JLabel("Editor:");
        panel_1.add(lblNewLabel_3);
        
        txt_editor = new JTextField();
        panel_1.add(txt_editor);
        txt_editor.setColumns(10);
        
        lblNewLabel_4 = new JLabel("Num paginas:");
        panel_1.add(lblNewLabel_4);
        
        txt_paginas = new JTextField();
        panel_1.add(txt_paginas);
        txt_paginas.setColumns(10);
        
        btn_enviar = new JButton("Enviar");
        panel_1.add(btn_enviar);
        
        btn_recu = new JButton("Recuperar");
        panel_1.add(btn_recu);
        
        btn_MostrarLibros = new JButton("Mostrar");
        panel_1.add(btn_MostrarLibros);
		
	}	
	
	public JButton getBtn_MostrarLibros() {
		return btn_MostrarLibros;
	}

	public void setBtn_MostrarLibros(JButton btn_MostrarLibros) {
		this.btn_MostrarLibros = btn_MostrarLibros;
	}

	public JButton getBtn_recu() {
		return btn_recu;
	}

	public void setBtn_recu(JButton btn_recu) {
		this.btn_recu = btn_recu;
	}

	public JButton getComparar() {
		return comparar;
	}

	public void setComparar(JButton comparar) {
		this.comparar = comparar;
	}

	public JButton getBuscar() {
		return buscar;
	}

	public void setBuscar(JButton buscar) {
		this.buscar = buscar;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
			
	public JTextField getFichero1() {
		return fichero1;
	}

	public void setFichero1(JTextField fichero1) {
		this.fichero1 = fichero1;
	}

	public JTextField getFichero2() {
		return fichero2;
	}

	public void setFichero2(JTextField fichero2) {
		this.fichero2 = fichero2;
	}

	public void showError(String m){
		JOptionPane.showMessageDialog(this.panel,
			    m,
			    "Error",
			    JOptionPane.ERROR_MESSAGE);
	}

	public JTextField getPalabra() {
		return palabra;
	}

	public void setPalabra(JTextField palabra) {
		this.palabra = palabra;
	}

	public JCheckBox getPrimera() {
		return primera;
	}

	public void setPrimera(JCheckBox primera) {
		this.primera = primera;
	}


}