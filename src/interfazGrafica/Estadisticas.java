// Interfaz para las estadísticas
// Aplicación: MathMentor
// Autor: Marta Rojas

package interfazGrafica;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import principal.Principal;
import xml.Jugador;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Comparator;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Estadisticas extends JFrame {

	private JPanel contentPane;
	private DefaultListModel<String> lmNombre;
	private JList<String> listaNombre;
	private DefaultListModel<String> lmVictorias;
	private JList<String> listaVictorias;
	private DefaultListModel<String> lmIntentos;
	private JList<String> listaIntentos;
	private DefaultListModel<String> lmTiempo;
	private JList<String> listaTiempo;
	private List<Jugador> estads;

	/**
	 * Interfaz gráfica para mostrar las estadísticas de un juego cualquiera
	 * @param juego: el juego del que queremos mostrar las estadísticas
	 * @param usuario: el usuario, para poder volver a la pantalla anterior con su cuenta
	 */
	public Estadisticas(String juego, String usuario) {

		//Le pedimos al principal la lista con las estadísticas
		estads = Principal.pedirEstads(juego);

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(218, 255, 239));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		//Decoración de la interfaz
		
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

		//"Botón" para volver atras. En este caso, volvemos a la pantalla de juegos siempre
		JLabel lblAtras = new JLabel();
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

		// Evento para que al cerrar la aplicación cierre los sockets
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Principal.cerrar();
			}
		});
		
		//Lista con los nombres

		lmNombre = new DefaultListModel<>();
		listaNombre = new JList<>(lmNombre);

		JScrollPane scrollPane1 = new JScrollPane(listaNombre);
		scrollPane1.setBounds(333, 150, 125, 530);
		getContentPane().add(scrollPane1);
		
		//Lista con las victorias
		
		lmVictorias = new DefaultListModel<>();
		listaVictorias = new JList<>(lmVictorias);

		JScrollPane scrollPane2 = new JScrollPane(listaVictorias);
		scrollPane2.setBounds(490, 150, 125, 530);
		getContentPane().add(scrollPane2);
		
		//Lista con los mejores intentos
		
		lmIntentos = new DefaultListModel<>();
		listaIntentos = new JList<>(lmIntentos);

		JScrollPane scrollPane3 = new JScrollPane(listaIntentos);
		scrollPane3.setBounds(658, 150, 125, 530);
		getContentPane().add(scrollPane3);
		
		//Lista con los mejores tiempos 
		
		lmTiempo = new DefaultListModel<>();
		listaTiempo = new JList<>(lmTiempo);

		JScrollPane scrollPane4 = new JScrollPane(listaTiempo);
		scrollPane4.setBounds(825, 150, 125, 530);
		getContentPane().add(scrollPane4);

		//Botón para ordenar las listas por nombre
		JButton btnNombre = new JButton("Nombre");
		btnNombre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ordenarNombre();
			}
		});
		btnNombre.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnNombre.setBounds(345, 105, 102, 30);
		contentPane.add(btnNombre);

		//Botón para ordenar las listas por tiempo
		JButton btnTiempo = new JButton("Tiempo");
		btnTiempo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ordenarTiempo();
			}
		});
		btnTiempo.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnTiempo.setBounds(835, 105, 100, 30);
		contentPane.add(btnTiempo);

		//Botón para ordenar las listas por victorias
		JButton btnVictorias = new JButton("Victorias");
		btnVictorias.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ordenarVictorias();
			}
		});
		btnVictorias.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnVictorias.setBounds(500, 105, 105, 30);
		contentPane.add(btnVictorias);

		//Botón para ordenar las listas por intentos
		JButton btnIntentos = new JButton("Intentos");
		btnIntentos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ordenarIntentos();
			}
		});
		btnIntentos.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnIntentos.setBounds(666, 106, 110, 30);
		contentPane.add(btnIntentos);

		//Actualizamos las Jlist por primera vez para que contenga los datos
		actualizarLista(estads);
	}

	/**
	 * Método para actualizar las JList a partir de una lista de jugadores
	 * @param estads
	 */
	public void actualizarLista(List<Jugador> estads) {
		lmNombre.clear();
		lmIntentos.clear();
		lmTiempo.clear();
		lmVictorias.clear();
		if (estads != null) {
			for (Jugador jugador : estads) {
				lmNombre.addElement(jugador.getNombre());
				lmIntentos.addElement(String.valueOf(jugador.getIntentos()));
				lmTiempo.addElement(String.valueOf(jugador.getMejorTiempo()));
				lmVictorias.addElement(String.valueOf(jugador.getVictorias()));
			}
		}
	}

	/**
	 * Método para ordenar la lista por nombre (alfabeticamente)
	 */
	protected void ordenarNombre() {
		estads.sort(new Comparator<Jugador>() {
			@Override
			public int compare(Jugador o1, Jugador o2) {
				return o1.getNombre().compareTo(o2.getNombre());
			}
		});
		actualizarLista(estads);
	}

	/**
	 * Método para ordenar la lista por intentos (de menos a mas)
	 */
	protected void ordenarIntentos() {
		estads.sort(new Comparator<Jugador>() {
			@Override
			public int compare(Jugador o1, Jugador o2) {
				return o1.getIntentos()-o2.getIntentos();
			}
		});
		actualizarLista(estads);
	}

	/**
	 * Método para ordenar la lista por victorias (de mas a menos)
	 */
	protected void ordenarVictorias() {
		estads.sort(new Comparator<Jugador>() {
			@Override
			public int compare(Jugador o1, Jugador o2) {
				return o2.getVictorias()-o1.getVictorias();
			}
		});
		actualizarLista(estads);
	}

	/**
	 * Método para ordenar la lista por tiempo (de menor a mayor)
	 */
	protected void ordenarTiempo() {
		estads.sort(new Comparator<Jugador>() {
			@Override
			public int compare(Jugador o1, Jugador o2) {
				return (int)(o1.getMejorTiempo()-o2.getMejorTiempo());
			}
		});
		actualizarLista(estads);
	}
}
