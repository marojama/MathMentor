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

public class Juegos extends JFrame {

	private JPanel contentPane;
	private String usuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Juegos frame = new Juegos();
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
	public Juegos() {
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
		
		JButton btnNerdle = new JButton("Nerdle");
		btnNerdle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cambiarPantallaNerdle();
			}
		});
		btnNerdle.setForeground(Color.WHITE);
		btnNerdle.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		btnNerdle.setBackground(new Color(100, 182, 172));
		btnNerdle.setBounds(428, 258, 300, 50);
		contentPane.add(btnNerdle);
		
		JButton btnJuegos = new JButton("...");
		btnJuegos.setForeground(Color.WHITE);
		btnJuegos.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		btnJuegos.setBackground(new Color(100, 182, 172));
		btnJuegos.setBounds(428, 350, 300, 50);
		contentPane.add(btnJuegos);
		
		JButton btnNewButton = new JButton("<--");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(100, 182, 172));
		btnNewButton.setBounds(10, 11, 50, 50);
		contentPane.add(btnNewButton);
		
		JButton btnE = new JButton("e");
		btnE.setForeground(Color.WHITE);
		btnE.setBackground(new Color(100, 182, 172));
		btnE.setBounds(783, 258, 50, 50);
		contentPane.add(btnE);
		
		JButton btnNewButton_2 = new JButton("<--");
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setBackground(new Color(100, 182, 172));
		btnNewButton_2.setBounds(783, 350, 50, 50);
		contentPane.add(btnNewButton_2);
		
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
	
	public Juegos(String usuario) {
		this();
		this.usuario=usuario;
	}
	
	private void cambiarPantallaNerdle() {
		NerdleNormal nn=new NerdleNormal(this.usuario);
		nn.setVisible(true);
		this.dispose();
	}

}
