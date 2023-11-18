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

public class Juegos extends JFrame {

	private JPanel contentPane;

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
		
		JButton btnE_1 = new JButton("e");
		btnE_1.setForeground(Color.WHITE);
		btnE_1.setBackground(new Color(100, 182, 172));
		btnE_1.setBounds(783, 350, 50, 50);
		contentPane.add(btnE_1);
	}

}
