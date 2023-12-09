package interfazGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import principal.Principal;

import java.awt.Color;
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
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Juegos frame = new Juegos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
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

		JLabel lblNewLabel = new JLabel("MathMentor");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Courier New", Font.PLAIN, 70));
		lblNewLabel.setBounds(10, 11, 1248, 84);
		contentPane.add(lblNewLabel);

		JButton btnAdivinarNum = new JButton("Adivina el número");
		btnAdivinarNum.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int intentos=1;
				long time=System.currentTimeMillis();
				String num = JOptionPane.showInputDialog(Juegos.this, "Adivina el número entre 1 y 100",
						"Adivina el número", JOptionPane.QUESTION_MESSAGE);
				String result = Principal.preguntarNumPrimera("Adivinar num",num);
				while (!result.equals("Adivinado") && !result.equals("Error")) {
					num = JOptionPane.showInputDialog(Juegos.this, "Ese no es el número." + result, "Adivina el número",
							JOptionPane.QUESTION_MESSAGE);
					result = Principal.preguntarNum(num);
					intentos++;
				}
				if(result.equals("Adivinado")) {
					long tiempo=System.currentTimeMillis()-time;
					JOptionPane.showMessageDialog(Juegos.this, "Felicidades! Lo has adivinado", "Fin juego",
							JOptionPane.INFORMATION_MESSAGE);
					if(!Juegos.this.usuario.equals("")) {
						Principal.enviarEstads(Juegos.this.usuario,intentos,tiempo,"AdivinarNum");
						JOptionPane.showMessageDialog(Juegos.this, "Tus estadísticas se han registrado", "Fin juego",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(Juegos.this, "Error, no has introducido un número válido", "Error juego",
							JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnAdivinarNum.setForeground(Color.WHITE);
		btnAdivinarNum.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		btnAdivinarNum.setBackground(new Color(100, 182, 172));
		btnAdivinarNum.setBounds(428, 250, 300, 50);
		contentPane.add(btnAdivinarNum);

		JButton btnEstadAdNum = new JButton("<--");
		btnEstadAdNum.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Estadisticas est=new Estadisticas("AdivinarNum",Juegos.this.usuario);
				est.setVisible(true);
				Juegos.this.dispose();
			}
		});
		btnEstadAdNum.setForeground(Color.WHITE);
		btnEstadAdNum.setBackground(new Color(100, 182, 172));
		btnEstadAdNum.setBounds(783, 250, 50, 50);
		contentPane.add(btnEstadAdNum);

		ImageIcon imagen1 = new ImageIcon("./src/planta1.png");
		ImageIcon imagen2 = new ImageIcon("./src/planta2.png");
		ImageIcon imagen3 = new ImageIcon("./src/flechita.png");

		JLabel lblPlanta1 = new JLabel();
		lblPlanta1.setIcon(imagen1);
		lblPlanta1.setBounds(89, 0, 216, 510);
		contentPane.add(lblPlanta1);

		JLabel lblPlanta2 = new JLabel();
		lblPlanta2.setIcon(imagen2);
		lblPlanta2.setBounds(985, 480, 200, 205);
		contentPane.add(lblPlanta2);

		JLabel lblAtras = new JLabel("");
		lblAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (usuario == null) {
					new ExamenesYJuegos().setVisible(true);
					dispose();
				} else if (usuario.equals("Profe")) {
					new PantallaProfesora().setVisible(true);
					dispose();
				} else {
					new ExamenesYJuegos(usuario).setVisible(true);
					dispose();
				}
			}
		});
		lblAtras.setIcon(imagen3);
		lblAtras.setBounds(10, 11, 50, 50);
		contentPane.add(lblAtras);

		JButton btnHeridosMuertos = new JButton("Heridos y muertos");
		btnHeridosMuertos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
				String result = Principal.preguntarNumPrimera("Heridos y ahogados",num);
				int intentos=1;
				long time=System.currentTimeMillis();
				while (!result.equals("Adivinado") && !result.equals("Error")) {
					num = JOptionPane.showInputDialog(Juegos.this, "Ese no es el número." + result,
							"Heridos y muertos", JOptionPane.QUESTION_MESSAGE);
					result = Principal.preguntarNum(num);
					intentos++;
				}
				if(result.equals("Adivinado")) {
					long tiempo=System.currentTimeMillis()-time;
					JOptionPane.showMessageDialog(Juegos.this, "Felicidades! Lo has adivinado", "Fin juego",
							JOptionPane.INFORMATION_MESSAGE);
					if(!Juegos.this.usuario.equals("")) {
						Principal.enviarEstads(Juegos.this.usuario,intentos,tiempo,"HeridosYAhogados");
						JOptionPane.showMessageDialog(Juegos.this, "Tus estadísticas se han registrado", "Fin juego",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(Juegos.this, "Error, no has introducido un número válido", "Error juego",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnHeridosMuertos.setForeground(Color.WHITE);
		btnHeridosMuertos.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		btnHeridosMuertos.setBackground(new Color(100, 182, 172));
		btnHeridosMuertos.setBounds(428, 350, 300, 50);
		contentPane.add(btnHeridosMuertos);

		JButton btnEstadHyM = new JButton("<--");
		btnEstadHyM.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Estadisticas est=new Estadisticas("HeridosYMuertos",Juegos.this.usuario);
				est.setVisible(true);
				Juegos.this.dispose();
			}
		});
		btnEstadHyM.setForeground(Color.WHITE);
		btnEstadHyM.setBackground(new Color(100, 182, 172));
		btnEstadHyM.setBounds(783, 350, 50, 50);
		contentPane.add(btnEstadHyM);

		JButton btnProximamente = new JButton("Proximamente...");
		btnProximamente.setEnabled(false);
		btnProximamente.setForeground(Color.WHITE);
		btnProximamente.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		btnProximamente.setBackground(new Color(100, 182, 172));
		btnProximamente.setBounds(428, 450, 300, 50);
		contentPane.add(btnProximamente);

		JButton btnEstadProx = new JButton("<--");
		btnEstadProx.setEnabled(false);
		btnEstadProx.setForeground(Color.WHITE);
		btnEstadProx.setBackground(new Color(100, 182, 172));
		btnEstadProx.setBounds(783, 450, 50, 50);
		contentPane.add(btnEstadProx);

		// Evento para que al cerrar la aplicación cierre los socket y no salte
		// excepcion en el servidor
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Principal.cerrar();
			}
		});
	}

	public Juegos(String usuario) {
		this();
		this.usuario = usuario;
	}
}
