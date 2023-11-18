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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ExamenesYJuegos extends JFrame {

	private JPanel contentPane;
	private JButton btnJuegos;
	private JButton btnExamenes;
	private String usuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExamenesYJuegos frame = new ExamenesYJuegos();
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
	public ExamenesYJuegos() {
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
		
		btnExamenes = new JButton("Examenes");
		btnExamenes.setForeground(Color.WHITE);
		btnExamenes.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		btnExamenes.setBackground(new Color(100, 182, 172));
		btnExamenes.setBounds(490, 258, 300, 50);
		contentPane.add(btnExamenes);
		
		btnJuegos = new JButton("Juegos");
		btnJuegos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cambiarPantallaJuegos();
			}
		});
		btnJuegos.setForeground(Color.WHITE);
		btnJuegos.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		btnJuegos.setBackground(new Color(100, 182, 172));
		btnJuegos.setBounds(490, 350, 300, 50);
		contentPane.add(btnJuegos);
	}
	
	public ExamenesYJuegos(String usuario) {
		this();
		this.usuario=usuario;
		if(usuario.equals("invitado")) {
			btnExamenes.setVisible(false);
		}
	}
	
	private void cambiarPantallaJuegos() {
		Juegos j=new Juegos(this.usuario);
		j.setVisible(true);
		this.dispose();
	}

}
