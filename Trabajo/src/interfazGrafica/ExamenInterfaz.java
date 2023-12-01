package interfazGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import principal.Principal;
import xml.Examen;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ExamenInterfaz extends JFrame{

	private JFrame frame;
	private Examen examen;

	/**
	 * Create the application.
	 * @param seleccion 
	 * @param seleccion2 
	 */
	public ExamenInterfaz(String seleccion) {
		this.examen=Principal.pedirExamen(seleccion);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(218, 255, 239));
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MathMentor");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Courier New", Font.PLAIN, 70));
		lblNewLabel.setBounds(10, 11, 1248, 84);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblPlanta1 = new JLabel();
		lblPlanta1.setBounds(89, 0, 216, 510);
		frame.getContentPane().add(lblPlanta1);
		
		JLabel lblPlanta2 = new JLabel();
		lblPlanta2.setBounds(985, 480, 200, 205);
		frame.getContentPane().add(lblPlanta2);
		
		JLabel lblAtras = new JLabel("");
		lblAtras.setBounds(10, 11, 50, 50);
		frame.getContentPane().add(lblAtras);
		
		JPanel panel = new JPanel();
		panel.setBounds(330, 106, 609, 408);
		frame.getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {609, 0};
		gbl_panel.rowHeights = new int[] {136, 68, 68, 68, 68};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("New radio button");
		rdbtnNewRadioButton_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnNewRadioButton_2.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_rdbtnNewRadioButton_2 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_2.fill = GridBagConstraints.BOTH;
		gbc_rdbtnNewRadioButton_2.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnNewRadioButton_2.gridx = 0;
		gbc_rdbtnNewRadioButton_2.gridy = 1;
		panel.add(rdbtnNewRadioButton_2, gbc_rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("New radio button");
		rdbtnNewRadioButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnNewRadioButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_1.fill = GridBagConstraints.BOTH;
		gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnNewRadioButton_1.gridx = 0;
		gbc_rdbtnNewRadioButton_1.gridy = 2;
		panel.add(rdbtnNewRadioButton_1, gbc_rdbtnNewRadioButton_1);
		
		JRadioButton radioButton = new JRadioButton("New radio button");
		radioButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_radioButton = new GridBagConstraints();
		gbc_radioButton.fill = GridBagConstraints.BOTH;
		gbc_radioButton.insets = new Insets(0, 0, 5, 0);
		gbc_radioButton.gridx = 0;
		gbc_radioButton.gridy = 3;
		panel.add(radioButton, gbc_radioButton);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
		gbc_rdbtnNewRadioButton.fill = GridBagConstraints.BOTH;
		gbc_rdbtnNewRadioButton.gridx = 0;
		gbc_rdbtnNewRadioButton.gridy = 4;
		panel.add(rdbtnNewRadioButton, gbc_rdbtnNewRadioButton);
	}
}
