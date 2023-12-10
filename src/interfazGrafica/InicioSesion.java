// Interfaz Inicio Sesion
// Aplicación: MathMentor
// Autor: Marta Rojas

package interfazGrafica;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import principal.Principal;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InicioSesion extends JFrame {

	private JPanel contentPane;
	private JTextField tfUsuario;

	/**
	 * Interfaz para iniciar sesión, en el caso de tener cuenta
	 */
	public InicioSesion() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(218, 255, 239));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Decoración interfaz

		JLabel titulo = new JLabel("MathMentor");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Courier New", Font.PLAIN, 70));
		titulo.setBounds(10, 11, 1248, 84);
		contentPane.add(titulo);

		ImageIcon imagen1 = new ImageIcon("./planta1.png");
		ImageIcon imagen2 = new ImageIcon("./planta2.png");
		ImageIcon imagen3 = new ImageIcon("./flechita.png");

		JLabel lblPlanta1 = new JLabel();
		lblPlanta1.setIcon(imagen1);
		lblPlanta1.setBounds(89, 0, 216, 510);
		contentPane.add(lblPlanta1);

		JLabel lblPlanta2 = new JLabel();
		lblPlanta2.setIcon(imagen2);
		lblPlanta2.setBounds(985, 480, 200, 205);
		contentPane.add(lblPlanta2);

		// "Botón" para volver a la pantalla anterior
		JLabel lblAtras = new JLabel();
		lblAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new PantallaInicial().setVisible(true);
				dispose();
			}
		});
		lblAtras.setIcon(imagen3);
		lblAtras.setBounds(10, 11, 50, 50);
		contentPane.add(lblAtras);

		// TextField donde el usuario meterá su usuario
		tfUsuario = new JTextField();
		tfUsuario.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		tfUsuario.setBounds(420, 241, 434, 50);
		contentPane.add(tfUsuario);
		tfUsuario.setColumns(10);

		// Label Usuario
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setBounds(10, 240, 387, 50);
		contentPane.add(lblUsuario);

		// Botón para entrar en la aplicación
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Al clicar en entrar, debemos comprobar si es un usuario de la aplicación
				cambiarPantallaPrincipal(tfUsuario.getText());
			}
		});
		btnEntrar.setForeground(Color.WHITE);
		btnEntrar.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		btnEntrar.setBackground(new Color(100, 182, 172));
		btnEntrar.setBounds(490, 437, 300, 50);
		contentPane.add(btnEntrar);

		// Evento para que al cerrar la aplicación cierre los socket
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Principal.cerrar();
			}
		});
	}

	/**
	 * Método para cambiar a la pantalla principal.
	 * Comprueba si es la profesora o sino, pregunta al servidor si dicho usuario está registrado
	 * @param usuario: usuario introducido
	 */
	private void cambiarPantallaPrincipal(String usuario) {
		//Si es la profesora, usuario de pruebas. Habrá que cambiarlo a algo mas dificil de adivinar
		if (usuario.equals("Profe")) {
			PantallaProfesora pr = new PantallaProfesora();
			pr.setVisible(true);
			this.dispose();
		//Si el Principal nos dice que existe (al preguntarle al servidor), entramos
		} else if (Principal.existeUsuario(usuario)) {
			ExamenesYJuegos ej = new ExamenesYJuegos(usuario);
			ej.setVisible(true);
			this.dispose();
		//Si no, mensaje de error
		} else {
			JOptionPane.showMessageDialog(this,
					"No se encuentra tu usuario. Habla con tu profe o comprueba que lo hayas escrito bien.",
					"Error usuario", JOptionPane.ERROR_MESSAGE);
		}
	}
}
