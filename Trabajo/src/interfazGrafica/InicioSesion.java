package interfazGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import principal.Usuarios;

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

public class InicioSesion extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioSesion frame = new InicioSesion();
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
	public InicioSesion() {
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
		
		textField = new JTextField();
		textField.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		textField.setBounds(420, 241, 434, 50);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario:");
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(10, 240, 387, 50);
		contentPane.add(lblNewLabel_1);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cambiarPantallaPrincipal(textField.getText());
			}
		});
		btnEntrar.setForeground(Color.WHITE);
		btnEntrar.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		btnEntrar.setBackground(new Color(100, 182, 172));
		btnEntrar.setBounds(490, 437, 300, 50);
		contentPane.add(btnEntrar);
		
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
	
	private void cambiarPantallaPrincipal(String usuario) {
		if(usuario.equals("marojamaProfe")) {
			PantallaProfesora pr=new PantallaProfesora();
			pr.setVisible(true);
			this.dispose();
		}
		else if(Usuarios.existeUsuario(usuario)) {
			ExamenesYJuegos ej=new ExamenesYJuegos(usuario);
			ej.setVisible(true);
			this.dispose();
		}
		else {
			JOptionPane.showMessageDialog(this, "No se encuentra tu usuario. Habla con tu profe o comprueba que lo hayas escrito bien.", "Error usuario", JOptionPane.ERROR_MESSAGE);
		}
	}
}
