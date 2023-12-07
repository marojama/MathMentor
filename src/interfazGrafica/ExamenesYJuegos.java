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
import java.util.ArrayList;

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

		btnExamenes = new JButton("Examenes");
		btnExamenes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String[] listaExamenes = Principal.nombreExamenes();
				if (listaExamenes[0] == null) {
					JOptionPane.showMessageDialog(ExamenesYJuegos.this,
							"Hoy no tienes examenes pendientes, pero puedes disfrutar de los juegos para repasar",
							"No hay examenes disponibles", JOptionPane.OK_OPTION);
				} else {
					String seleccion = (String) JOptionPane.showInputDialog(ExamenesYJuegos.this,
							"Selecciona el examen a realizar", "Selección examen", JOptionPane.QUESTION_MESSAGE, null,
							listaExamenes, listaExamenes[0]);
					new ExamenInterfaz(seleccion, usuario).setVisible(true);
					ExamenesYJuegos.this.dispose();
				}
			}
		});
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

		JLabel lblAtras = new JLabel("");
		lblAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (usuario == null) {
					new PantallaProfesora().setVisible(true);
					dispose();
				} else {
					new PantallaInicial().setVisible(true);
					dispose();
				}
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
	}

	public ExamenesYJuegos(String usuario) {
		this();
		this.usuario = usuario;
		if (usuario != null && usuario.equals("invitado")) {
			btnExamenes.setVisible(false);
		}
	}

	private void cambiarPantallaJuegos() {
		Juegos j = new Juegos(this.usuario);
		j.setVisible(true);
		this.dispose();
	}

}
