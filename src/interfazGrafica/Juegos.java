// Interfaz Juegos
// Aplicación: MathMentor
// Autor: Marta Rojas

package interfazGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import principal.Principal;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Juegos extends JFrame {

	private JPanel contentPane;
	private String usuario;

	/**
	 * Interfaz que contiene los juegos de la aplicación
	 */
	public Juegos() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(218, 255, 239));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Decoración interfaz

		JLabel lblNewLabel = new JLabel("MathMentor");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Courier New", Font.PLAIN, 70));
		lblNewLabel.setBounds(10, 11, 1248, 84);
		contentPane.add(lblNewLabel);

		ImageIcon imagen1 = new ImageIcon("./planta1.png");
		ImageIcon imagen2 = new ImageIcon("./planta2.png");
		ImageIcon imagen3 = new ImageIcon("./flechita.png");
		ImageIcon imagen4 = new ImageIcon("./estadisticas.png");

		JLabel lblPlanta1 = new JLabel();
		lblPlanta1.setIcon(imagen1);
		lblPlanta1.setBounds(89, 0, 216, 510);
		contentPane.add(lblPlanta1);

		JLabel lblPlanta2 = new JLabel();
		lblPlanta2.setIcon(imagen2);
		lblPlanta2.setBounds(985, 480, 200, 205);
		contentPane.add(lblPlanta2);

		// "Botón" ir a la pantalla anterior
		JLabel lblAtras = new JLabel();
		lblAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Usuario invitado. Acceso limitado
				if (usuario == null || usuario.equals("invitado")) {
					new ExamenesYJuegos(usuario).setVisible(true);
					dispose();
					// Profesora. Acceso a opciones de profesora
				} else if (usuario.equals("Profe")) {
					new PantallaProfesora().setVisible(true);
					dispose();
					// Usuario normal. Acceso a examenes y juegos
				} else {
					new ExamenesYJuegos(usuario).setVisible(true);
					dispose();
				}
			}
		});
		lblAtras.setIcon(imagen3);
		lblAtras.setBounds(10, 11, 50, 50);
		contentPane.add(lblAtras);

		// "Botón" para las estadísticas del juego Adivinar Num

		JLabel btnEstadAdNum = new JLabel();
		btnEstadAdNum.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Estadisticas est = new Estadisticas("AdivinarNum", Juegos.this.usuario);
				est.setVisible(true);
				Juegos.this.dispose();
			}
		});
		btnEstadAdNum.setIcon(imagen4);
		btnEstadAdNum.setBounds(783, 250, 50, 50);
		contentPane.add(btnEstadAdNum);

		// "Botón" para las estadísticas del juego Heridos y muertos
		JLabel btnEstadHyM = new JLabel();
		btnEstadHyM.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Estadisticas est = new Estadisticas("HeridosYMuertos", Juegos.this.usuario);
				est.setVisible(true);
				Juegos.this.dispose();
			}
		});
		btnEstadHyM.setIcon(imagen4);
		btnEstadHyM.setBounds(783, 350, 50, 50);
		contentPane.add(btnEstadHyM);

		// Juego Adivinar Número entre 1 y 100.
		// Si el número introducido es mayor, te dice que te has pasado
		// Si el número introducido es menor, te dice que te has quedado corto
		JButton btnAdivinarNum = new JButton("Adivina el número");
		btnAdivinarNum.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Datos para las estadísticas
				int intentos = 1;
				long time = System.currentTimeMillis();
				// Primer mensaje. El usuario nos devuelve una string
				String num = JOptionPane.showInputDialog(Juegos.this, "Adivina el número entre 1 y 100",
						"Adivina el número", JOptionPane.QUESTION_MESSAGE);
				// Le pasamos al principal (que se lo pasará al serrvidor) una String con,
				// posiblemente, un número
				// Si es un número, seguiremos jugando. Si no, se cancelará el juego
				String result = Principal.preguntarNumPrimera("AdivinarNum", num);
				// Mientras no halla error y no hallamos adivinado, seguimos jugando
				while (!result.equals("Adivinado") && !result.equals("Error")) {
					// Con result, mostramos lo que el servidor nos manda, que puede ser: Te has
					// pasado, Te has quedado corto
					num = JOptionPane.showInputDialog(Juegos.this, "Ese no es el número. " + result, "Adivina el número",
							JOptionPane.QUESTION_MESSAGE);
					result = Principal.preguntarNum(num);
					// Vamos sumando el número de intentos que necesita el usuario
					intentos++;
				}
				// Si ha adivinado el número
				if (result.equals("Adivinado")) {
					// Paramos de contar el tiempo
					long tiempo = System.currentTimeMillis() - time;
					// Se acaba el juego
					JOptionPane.showMessageDialog(Juegos.this, "Felicidades! Lo has adivinado", "Fin juego",
							JOptionPane.INFORMATION_MESSAGE);
					if (Juegos.this.usuario != null) {
						// Guardamos las estadísticas
						Principal.enviarEstads(Juegos.this.usuario, intentos, tiempo, "AdivinarNum");
						JOptionPane.showMessageDialog(Juegos.this, "Tus estadísticas se han registrado", "Fin juego",
								JOptionPane.INFORMATION_MESSAGE);
					}
					// Error, el usuario ha introducido un valor no válido
				} else {
					JOptionPane.showMessageDialog(Juegos.this, "Error, no has introducido un número válido",
							"Error juego", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnAdivinarNum.setForeground(Color.WHITE);
		btnAdivinarNum.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		btnAdivinarNum.setBackground(new Color(100, 182, 172));
		btnAdivinarNum.setBounds(428, 250, 300, 50);
		contentPane.add(btnAdivinarNum);

		// Juego Heridos y muertos
		// Se pretende adivinar un número de cuatro cifras sin repetidos
		// Si algún dígito del número introducido está en el número en la misma posición
		// se dirá que está muerto
		// Si está en otra posición, que está herido
		JButton btnHeridosMuertos = new JButton("Heridos y muertos");
		btnHeridosMuertos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Explicación del juego
				JOptionPane.showMessageDialog(Juegos.this, "Estoy pensando en un número de 4 dígitos sin repetidos",
						"Heridos y muertos", JOptionPane.INFORMATION_MESSAGE);
				JOptionPane.showMessageDialog(Juegos.this,
						"Si tu número tiene algún dígito igual que el mio, y está en su lugar, te diré que está muerto.",
						"Heridos y muertos", JOptionPane.INFORMATION_MESSAGE);
				JOptionPane.showMessageDialog(Juegos.this,
						"Si el dígito está en mi número, pero en distinta posición, te diré que está herido",
						"Heridos y muertos", JOptionPane.INFORMATION_MESSAGE);
				String num = JOptionPane.showInputDialog(Juegos.this, "Introduce tu número de 4 cifras",
						"Heridos y muertos", JOptionPane.QUESTION_MESSAGE);
				// Leemos lo introducido por el usuario y se lo pasamos al servidor
				String result = Principal.preguntarNumPrimera("HeridosYMuertos", num);
				// Iniciamos las variables de las estadísticas
				int intentos = 1;
				long time = System.currentTimeMillis();
				// Mientras no halla error y no hallamos adivinado, seguimos jugando
				while (!result.equals("Adivinado") && !result.equals("Error")) {
					// Con result, mostramos lo que el servidor nos manda, es decir, el número de
					// heridos y muertos
					num = JOptionPane.showInputDialog(Juegos.this, "Ese no es el número. " + result, "HeridosYMuertos",
							JOptionPane.QUESTION_MESSAGE);
					result = Principal.preguntarNum(num);
					// Vamos sumando el número de intentos que necesita el usuario
					intentos++;
				}
				// Si ha adivinado el número
				if (result.equals("Adivinado")) {
					// Paramos de contar el tiempo
					long tiempo = System.currentTimeMillis() - time;
					// Se acaba el juego
					JOptionPane.showMessageDialog(Juegos.this, "Felicidades! Lo has adivinado", "Fin juego",
							JOptionPane.INFORMATION_MESSAGE);
					if (!Juegos.this.usuario.equals("")) {
						// Guardamos las estadísticas
						Principal.enviarEstads(Juegos.this.usuario, intentos, tiempo, "HeridosYMuertos");
						JOptionPane.showMessageDialog(Juegos.this, "Tus estadísticas se han registrado", "Fin juego",
								JOptionPane.INFORMATION_MESSAGE);
					}
					// Error, el usuario ha introducido un valor no válido
				} else {
					JOptionPane.showMessageDialog(Juegos.this, "Error, no has introducido un número válido",
							"Error juego", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnHeridosMuertos.setForeground(Color.WHITE);
		btnHeridosMuertos.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		btnHeridosMuertos.setBackground(new Color(100, 182, 172));
		btnHeridosMuertos.setBounds(428, 350, 300, 50);
		contentPane.add(btnHeridosMuertos);

		// Evento para que al cerrar la aplicación cierre los socket y no salte
		// excepcion en el servidor
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Principal.cerrar();
			}
		});
	}

	//Inicio de la interfaz con un usuario
	public Juegos(String usuario) {
		this();
		this.usuario = usuario;
	}
}
