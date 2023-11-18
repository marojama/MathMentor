package interfazGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		
		JButton btnVerEstadsticas = new JButton("Juegos");
		btnVerEstadsticas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cambiarPantallaJuegos();
			}
		});
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
		
		ImageIcon imagen1=new ImageIcon("./planta1.png");
		ImageIcon imagen2=new ImageIcon("./planta2.png");
		
		JLabel lblPlanta1 = new JLabel();
		lblPlanta1.setIcon(imagen1);
		lblPlanta1.setBounds(89, 0, 216, 510);
		contentPane.add(lblPlanta1);
		
		JLabel lblPlanta2 = new JLabel();
		lblPlanta2.setIcon(imagen2);
		lblPlanta2.setBounds(985, 480, 200, 205);
		contentPane.add(lblPlanta2);
	}
	
	private void cambiarPantallaJuegos() {
		Juegos j=new Juegos();
		j.setVisible(true);
		this.dispose();
	}

}
