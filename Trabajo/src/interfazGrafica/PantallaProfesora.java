package interfazGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class PantallaProfesora extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaProfesora frame = new PantallaProfesora();
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
	public PantallaProfesora() {
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
		
		JButton btnGestionarUsuarios = new JButton("Gestionar usuarios");
		btnGestionarUsuarios.setForeground(Color.WHITE);
		btnGestionarUsuarios.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		btnGestionarUsuarios.setBackground(new Color(100, 182, 172));
		btnGestionarUsuarios.setBounds(490, 200, 300, 50);
		contentPane.add(btnGestionarUsuarios);
		
		JButton btnVerEnvios = new JButton("Ver envios");
		btnVerEnvios.setForeground(Color.WHITE);
		btnVerEnvios.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		btnVerEnvios.setBackground(new Color(100, 182, 172));
		btnVerEnvios.setBounds(490, 300, 300, 50);
		contentPane.add(btnVerEnvios);
		
		JButton btnVerEstadsticas = new JButton("Ver estadísticas");
		btnVerEstadsticas.setForeground(Color.WHITE);
		btnVerEstadsticas.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		btnVerEstadsticas.setBackground(new Color(100, 182, 172));
		btnVerEstadsticas.setBounds(490, 400, 300, 50);
		contentPane.add(btnVerEstadsticas);
		
		JButton btnCrearExamen = new JButton("Crear examen");
		btnCrearExamen.setForeground(Color.WHITE);
		btnCrearExamen.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		btnCrearExamen.setBackground(new Color(100, 182, 172));
		btnCrearExamen.setBounds(490, 500, 300, 50);
		contentPane.add(btnCrearExamen);
	}

}
