package interfazGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PantallaInicial extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaInicial frame = new PantallaInicial();
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
	public PantallaInicial() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(218, 255, 239));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("MathMentor");
		lblNombre.setFont(new Font("Courier New", Font.PLAIN, 70));
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(10, 11, 1248, 84);
		contentPane.add(lblNombre);
		
		JButton btnIniciarSesion = new JButton("Iniciar sesión");
		btnIniciarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cambiarPantallaInicio();
			}
		});
		btnIniciarSesion.setForeground(new Color(255, 255, 255));
		btnIniciarSesion.setBackground(new Color(100, 182, 172));
		btnIniciarSesion.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		btnIniciarSesion.setBounds(490, 258, 300, 50);
		contentPane.add(btnIniciarSesion);
		
		JButton btnInvitado = new JButton("Invitado");
		btnInvitado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cambiarPantallaJuegos();
			}
		});
		btnInvitado.setForeground(new Color(255, 255, 255));
		btnInvitado.setBackground(new Color(100, 182, 172));
		btnInvitado.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		btnInvitado.setBounds(490, 350, 300, 50);
		contentPane.add(btnInvitado);
	}
	
	private void cambiarPantallaInicio() {
		InicioSesion is=new InicioSesion();
		is.setVisible(true);
		this.dispose();
	}
	
	private void cambiarPantallaJuegos() {
		ExamenesYJuegos ej=new ExamenesYJuegos("invitado");
		ej.setVisible(true);
		this.dispose();
	}
	
}
