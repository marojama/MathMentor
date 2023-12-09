package interfazGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Estadisticas.Jugador;
import principal.Principal;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class Estadisticas extends JFrame {

	private JPanel contentPane;
	private DefaultListModel<String> lm;
	private JList<String> lista;
	private List<Jugador> estads;
	private String juego;
	private String usuario;

	/**
	 * Create the frame.
	 */
	public Estadisticas(String juego, String usuario) {

		this.usuario = usuario;
		this.juego = juego;
		estads = Principal.pedirEstads(juego);

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
				new Juegos(usuario).setVisible(true);
				dispose();
			}
		});
		lblAtras.setIcon(imagen3);
		lblAtras.setBounds(10, 11, 50, 50);
		contentPane.add(lblAtras);

		// Evento para que al cerrar la aplicación cierre los socket y no salte
		// excepcion en el servidor
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Principal.cerrar();
			}
		});

		lm = new DefaultListModel<>();
		lista = new JList<>(lm);

		JScrollPane scrollPane = new JScrollPane(lista);
		scrollPane.setBounds(345, 100, 590, 580);
		getContentPane().add(scrollPane);

//		JButton btOrdenarTiempo = new JButton("Ordenar por tiempo");
//		btOrdenarTiempo.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				ordenarTiempo();
//			}
//		});
//		contentPane.add(btOrdenarTiempo);
//		
//		JButton btOrdenarVictorias = new JButton("Ordenar por victorias");
//		btOrdenarVictorias.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				ordenarVictorias();
//			}
//		});
//		contentPane.add(btOrdenarVictorias);
//		
//		JButton btOrdenarIntentos = new JButton("Ordenar por intentos");
//		btOrdenarIntentos.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				ordenarIntentos();
//			}
//		});
//		contentPane.add(btOrdenarIntentos);
		actualizarLista(estads);
	}

	public void actualizarLista(List<Jugador> estads) {
		lm.clear();
		if(estads!=null) {
			for (Jugador jugador : estads) {
				lm.addElement(jugador.toString());
			}
		}
		
	}

	protected void ordenarIntentos() {
		actualizarLista(estads);
	}

	protected void ordenarVictorias() {
		actualizarLista(estads);
	}

	protected void ordenarTiempo() {
		actualizarLista(estads);
	}
}
