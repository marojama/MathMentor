// Interfaz inicial
// Aplicación: MathMentor
// Autor: Marta Rojas

package interfazGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import principal.Principal;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PantallaInicial extends JFrame {

	private JPanel contentPane;

	/**
	 * Interfaz de la pantalla principal, 
	 * que permite iniciar sesión, o acceder como invitado
	 */
	public PantallaInicial() {
		
		// Para que en MacOS tambien se vea bien, sino no se leen los botones
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(218, 255, 239));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Decoración interfaz

		JLabel lblNombre = new JLabel("MathMentor");
		lblNombre.setFont(new Font("Courier New", Font.PLAIN, 70));
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(10, 11, 1248, 84);
		contentPane.add(lblNombre);
		
		ImageIcon imagen1 = new ImageIcon("./planta1.png");
		ImageIcon imagen2 = new ImageIcon("./planta2.png");

		JLabel lblPlanta1 = new JLabel();
		lblPlanta1.setIcon(imagen1);
		lblPlanta1.setBounds(89, 0, 216, 510);
		contentPane.add(lblPlanta1);

		JLabel lblPlanta2 = new JLabel();
		lblPlanta2.setIcon(imagen2);
		lblPlanta2.setBounds(985, 480, 200, 205);
		contentPane.add(lblPlanta2);
		
		//Botón para iniciar sesión

		JButton btnIniciarSesion = new JButton("Iniciar sesión");
		btnIniciarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				InicioSesion is = new InicioSesion();
				is.setVisible(true);
				PantallaInicial.this.dispose();
			}
		});
		btnIniciarSesion.setForeground(Color.WHITE);
		btnIniciarSesion.setBackground(new Color(100, 182, 172));
		btnIniciarSesion.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		btnIniciarSesion.setBounds(490, 258, 300, 50);
		contentPane.add(btnIniciarSesion);
		
		//Botón para entrar como invitado

		JButton btnInvitado = new JButton("Invitado");
		btnInvitado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ExamenesYJuegos ej = new ExamenesYJuegos("invitado");
				ej.setVisible(true);
				PantallaInicial.this.dispose();
			}
		});
		btnInvitado.setForeground(Color.WHITE);
		btnInvitado.setBackground(new Color(100, 182, 172));
		btnInvitado.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		btnInvitado.setBounds(490, 350, 300, 50);
		contentPane.add(btnInvitado);

		// Evento para que al cerrar la aplicación cierre los socket
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Principal.cerrar();
			}
		});
	}
}
