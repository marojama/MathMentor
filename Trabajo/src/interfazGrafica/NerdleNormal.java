package interfazGrafica;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NerdleNormal extends JFrame {

	private JPanel contentPane;
	private JTextField tf_11;
	private JTextField tf_12;
	private JTextField tf_13;
	private JTextField tf_14;
	private JTextField tf_15;
	private JTextField tf_16;
	private JTextField tf_17;
	private JTextField tf_18;
	private JTextField tf_21;
	private JTextField tf_22;
	private JTextField tf_23;
	private JTextField tf_24;
	private JTextField tf_25;
	private JTextField tf_26;
	private JTextField tf_27;
	private JTextField tf_28;
	private JTextField tf_31;
	private JTextField tf_32;
	private JTextField tf_33;
	private JTextField tf_34;
	private JTextField tf_35;
	private JTextField tf_36;
	private JTextField tf_37;
	private JTextField tf_38;
	private JTextField tf_41;
	private JTextField tf_42;
	private JTextField tf_43;
	private JTextField tf_44;
	private JTextField tf_45;
	private JTextField tf_46;
	private JTextField tf_47;
	private JTextField tf_48;
	private JTextField tf_51;
	private JTextField tf_52;
	private JTextField tf_53;
	private JTextField tf_54;
	private JTextField tf_55;
	private JTextField tf_56;
	private JTextField tf_57;
	private JTextField tf_58;
	private JTextField tf_61;
	private JTextField tf_62;
	private JTextField tf_63;
	private JTextField tf_64;
	private JTextField tf_65;
	private JTextField tf_66;
	private JTextField tf_67;
	private JTextField tf_68;
	private JLabel lblNewLabel;
	private String usuario;


	/**
	 * Create the frame.
	 */
	public NerdleNormal(String usuario) {
		this.usuario=usuario;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(218, 255, 239));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn1 = new JButton("1");
		btn1.setFont(new Font("Arial", Font.BOLD, 18));
		btn1.setSize(50, 50);
		contentPane.add(btn1);
		btn1.setForeground(Color.BLACK);
		btn1.setBackground(new Color(226, 232, 240));
		btn1.setLocation(337, 524);
		
		tf_11 = new JTextField();
		tf_11.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_11.setHorizontalAlignment(SwingConstants.CENTER);
		tf_11.setFont(new Font("Arial", Font.BOLD, 22));
		tf_11.setForeground(Color.WHITE);
		tf_11.setBackground(new Color(152, 148, 132));
		tf_11.setBounds(372, 111, 56, 56);
		contentPane.add(tf_11);
		tf_11.setColumns(10);
		
		tf_12 = new JTextField();
		tf_12.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_12.setHorizontalAlignment(SwingConstants.CENTER);
		tf_12.setFont(new Font("Arial", Font.BOLD, 22));
		tf_12.setForeground(Color.WHITE);
		tf_12.setBackground(new Color(152, 148, 132));
		tf_12.setColumns(10);
		tf_12.setBounds(438, 111, 56, 56);
		contentPane.add(tf_12);
		
		tf_13 = new JTextField();
		tf_13.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_13.setHorizontalAlignment(SwingConstants.CENTER);
		tf_13.setFont(new Font("Arial", Font.BOLD, 22));
		tf_13.setBackground(new Color(152, 148, 132));
		tf_13.setForeground(Color.WHITE);
		tf_13.setColumns(10);
		tf_13.setBounds(504, 111, 56, 56);
		contentPane.add(tf_13);
		
		tf_14 = new JTextField();
		tf_14.setHorizontalAlignment(SwingConstants.CENTER);
		tf_14.setFont(new Font("Arial", Font.BOLD, 22));
		tf_14.setBackground(new Color(152, 148, 132));
		tf_14.setForeground(Color.WHITE);
		tf_14.setColumns(10);
		tf_14.setBounds(570, 111, 56, 56);
		contentPane.add(tf_14);
		
		tf_15 = new JTextField();
		tf_15.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_15.setHorizontalAlignment(SwingConstants.CENTER);
		tf_15.setFont(new Font("Arial", Font.BOLD, 22));
		tf_15.setBackground(new Color(152, 148, 132));
		tf_15.setForeground(Color.WHITE);
		tf_15.setColumns(10);
		tf_15.setBounds(636, 111, 56, 56);
		contentPane.add(tf_15);
		
		tf_16 = new JTextField();
		tf_16.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_16.setHorizontalAlignment(SwingConstants.CENTER);
		tf_16.setFont(new Font("Arial", Font.BOLD, 22));
		tf_16.setBackground(new Color(152, 148, 132));
		tf_16.setForeground(Color.WHITE);
		tf_16.setColumns(10);
		tf_16.setBounds(702, 111, 56, 56);
		contentPane.add(tf_16);
		
		tf_17 = new JTextField();
		tf_17.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_17.setHorizontalAlignment(SwingConstants.CENTER);
		tf_17.setFont(new Font("Arial", Font.BOLD, 22));
		tf_17.setBackground(new Color(152, 148, 132));
		tf_17.setForeground(Color.WHITE);
		tf_17.setColumns(10);
		tf_17.setBounds(768, 111, 56, 56);
		contentPane.add(tf_17);
		
		tf_18 = new JTextField();
		tf_18.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_18.setHorizontalAlignment(SwingConstants.CENTER);
		tf_18.setFont(new Font("Arial", Font.BOLD, 22));
		tf_18.setBackground(new Color(152, 148, 132));
		tf_18.setForeground(Color.WHITE);
		tf_18.setColumns(10);
		tf_18.setBounds(834, 111, 56, 56);
		contentPane.add(tf_18);
		
		tf_21 = new JTextField();
		tf_21.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_21.setHorizontalAlignment(SwingConstants.CENTER);
		tf_21.setFont(new Font("Arial", Font.BOLD, 22));
		tf_21.setBackground(new Color(152, 148, 132));
		tf_21.setForeground(Color.WHITE);
		tf_21.setColumns(10);
		tf_21.setBounds(372, 178, 56, 56);
		contentPane.add(tf_21);
		
		tf_22 = new JTextField();
		tf_22.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_22.setHorizontalAlignment(SwingConstants.CENTER);
		tf_22.setFont(new Font("Arial", Font.BOLD, 22));
		tf_22.setBackground(new Color(152, 148, 132));
		tf_22.setForeground(Color.WHITE);
		tf_22.setColumns(10);
		tf_22.setBounds(438, 178, 56, 56);
		contentPane.add(tf_22);
		
		tf_23 = new JTextField();
		tf_23.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_23.setHorizontalAlignment(SwingConstants.CENTER);
		tf_23.setFont(new Font("Arial", Font.BOLD, 22));
		tf_23.setBackground(new Color(152, 148, 132));
		tf_23.setForeground(Color.WHITE);
		tf_23.setColumns(10);
		tf_23.setBounds(504, 178, 56, 56);
		contentPane.add(tf_23);
		
		tf_24 = new JTextField();
		tf_24.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_24.setHorizontalAlignment(SwingConstants.CENTER);
		tf_24.setFont(new Font("Arial", Font.BOLD, 22));
		tf_24.setBackground(new Color(152, 148, 132));
		tf_24.setForeground(Color.WHITE);
		tf_24.setColumns(10);
		tf_24.setBounds(570, 178, 56, 56);
		contentPane.add(tf_24);
		
		tf_25 = new JTextField();
		tf_25.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_25.setHorizontalAlignment(SwingConstants.CENTER);
		tf_25.setFont(new Font("Arial", Font.BOLD, 22));
		tf_25.setBackground(new Color(152, 148, 132));
		tf_25.setForeground(Color.WHITE);
		tf_25.setColumns(10);
		tf_25.setBounds(636, 178, 56, 56);
		contentPane.add(tf_25);
		
		tf_26 = new JTextField();
		tf_26.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_26.setHorizontalAlignment(SwingConstants.CENTER);
		tf_26.setFont(new Font("Arial", Font.BOLD, 22));
		tf_26.setBackground(new Color(152, 148, 132));
		tf_26.setForeground(Color.WHITE);
		tf_26.setColumns(10);
		tf_26.setBounds(702, 178, 56, 56);
		contentPane.add(tf_26);
		
		tf_27 = new JTextField();
		tf_27.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_27.setHorizontalAlignment(SwingConstants.CENTER);
		tf_27.setFont(new Font("Arial", Font.BOLD, 22));
		tf_27.setBackground(new Color(152, 148, 132));
		tf_27.setForeground(Color.WHITE);
		tf_27.setColumns(10);
		tf_27.setBounds(768, 178, 56, 56);
		contentPane.add(tf_27);
		
		tf_28 = new JTextField();
		tf_28.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_28.setHorizontalAlignment(SwingConstants.CENTER);
		tf_28.setFont(new Font("Arial", Font.BOLD, 22));
		tf_28.setBackground(new Color(152, 148, 132));
		tf_28.setForeground(Color.WHITE);
		tf_28.setColumns(10);
		tf_28.setBounds(834, 178, 56, 56);
		contentPane.add(tf_28);
		
		tf_31 = new JTextField();
		tf_31.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_31.setHorizontalAlignment(SwingConstants.CENTER);
		tf_31.setFont(new Font("Arial", Font.BOLD, 22));
		tf_31.setBackground(new Color(152, 148, 132));
		tf_31.setForeground(Color.WHITE);
		tf_31.setColumns(10);
		tf_31.setBounds(372, 245, 56, 56);
		contentPane.add(tf_31);
		
		tf_32 = new JTextField();
		tf_32.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_32.setHorizontalAlignment(SwingConstants.CENTER);
		tf_32.setFont(new Font("Arial", Font.BOLD, 22));
		tf_32.setBackground(new Color(152, 148, 132));
		tf_32.setForeground(Color.WHITE);
		tf_32.setColumns(10);
		tf_32.setBounds(438, 245, 56, 56);
		contentPane.add(tf_32);
		
		tf_33 = new JTextField();
		tf_33.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_33.setHorizontalAlignment(SwingConstants.CENTER);
		tf_33.setFont(new Font("Arial", Font.BOLD, 22));
		tf_33.setBackground(new Color(152, 148, 132));
		tf_33.setForeground(Color.WHITE);
		tf_33.setColumns(10);
		tf_33.setBounds(504, 245, 56, 56);
		contentPane.add(tf_33);
		
		tf_34 = new JTextField();
		tf_34.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_34.setHorizontalAlignment(SwingConstants.CENTER);
		tf_34.setFont(new Font("Arial", Font.BOLD, 22));
		tf_34.setBackground(new Color(152, 148, 132));
		tf_34.setForeground(Color.WHITE);
		tf_34.setColumns(10);
		tf_34.setBounds(570, 245, 56, 56);
		contentPane.add(tf_34);
		
		tf_35 = new JTextField();
		tf_35.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_35.setHorizontalAlignment(SwingConstants.CENTER);
		tf_35.setFont(new Font("Arial", Font.BOLD, 22));
		tf_35.setBackground(new Color(152, 148, 132));
		tf_35.setForeground(Color.WHITE);
		tf_35.setColumns(10);
		tf_35.setBounds(636, 245, 56, 56);
		contentPane.add(tf_35);
		
		tf_36 = new JTextField();
		tf_36.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_36.setHorizontalAlignment(SwingConstants.CENTER);
		tf_36.setFont(new Font("Arial", Font.BOLD, 22));
		tf_36.setBackground(new Color(152, 148, 132));
		tf_36.setForeground(Color.WHITE);
		tf_36.setColumns(10);
		tf_36.setBounds(702, 245, 56, 56);
		contentPane.add(tf_36);
		
		tf_37 = new JTextField();
		tf_37.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_37.setHorizontalAlignment(SwingConstants.CENTER);
		tf_37.setFont(new Font("Arial", Font.BOLD, 22));
		tf_37.setBackground(new Color(152, 148, 132));
		tf_37.setForeground(Color.WHITE);
		tf_37.setColumns(10);
		tf_37.setBounds(768, 245, 56, 56);
		contentPane.add(tf_37);
		
		tf_38 = new JTextField();
		tf_38.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_38.setHorizontalAlignment(SwingConstants.CENTER);
		tf_38.setFont(new Font("Arial", Font.BOLD, 22));
		tf_38.setBackground(new Color(152, 148, 132));
		tf_38.setForeground(Color.WHITE);
		tf_38.setColumns(10);
		tf_38.setBounds(834, 245, 56, 56);
		contentPane.add(tf_38);
		
		tf_41 = new JTextField();
		tf_41.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_41.setHorizontalAlignment(SwingConstants.CENTER);
		tf_41.setFont(new Font("Arial", Font.BOLD, 22));
		tf_41.setBackground(new Color(152, 148, 132));
		tf_41.setForeground(Color.WHITE);
		tf_41.setColumns(10);
		tf_41.setBounds(372, 312, 56, 56);
		contentPane.add(tf_41);
		
		tf_42 = new JTextField();
		tf_42.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_42.setHorizontalAlignment(SwingConstants.CENTER);
		tf_42.setFont(new Font("Arial", Font.BOLD, 22));
		tf_42.setBackground(new Color(152, 148, 132));
		tf_42.setForeground(Color.WHITE);
		tf_42.setColumns(10);
		tf_42.setBounds(438, 312, 56, 56);
		contentPane.add(tf_42);
		
		tf_43 = new JTextField();
		tf_43.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_43.setHorizontalAlignment(SwingConstants.CENTER);
		tf_43.setFont(new Font("Arial", Font.BOLD, 22));
		tf_43.setBackground(new Color(152, 148, 132));
		tf_43.setForeground(Color.WHITE);
		tf_43.setColumns(10);
		tf_43.setBounds(504, 312, 56, 56);
		contentPane.add(tf_43);
		
		tf_44 = new JTextField();
		tf_44.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_44.setHorizontalAlignment(SwingConstants.CENTER);
		tf_44.setFont(new Font("Arial", Font.BOLD, 22));
		tf_44.setBackground(new Color(152, 148, 132));
		tf_44.setForeground(Color.WHITE);
		tf_44.setColumns(10);
		tf_44.setBounds(570, 312, 56, 56);
		contentPane.add(tf_44);
		
		tf_45 = new JTextField();
		tf_45.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_45.setHorizontalAlignment(SwingConstants.CENTER);
		tf_45.setFont(new Font("Arial", Font.BOLD, 22));
		tf_45.setBackground(new Color(152, 148, 132));
		tf_45.setForeground(Color.WHITE);
		tf_45.setColumns(10);
		tf_45.setBounds(636, 312, 56, 56);
		contentPane.add(tf_45);
		
		tf_46 = new JTextField();
		tf_46.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_46.setHorizontalAlignment(SwingConstants.CENTER);
		tf_46.setFont(new Font("Arial", Font.BOLD, 22));
		tf_46.setBackground(new Color(152, 148, 132));
		tf_46.setForeground(Color.WHITE);
		tf_46.setColumns(10);
		tf_46.setBounds(702, 312, 56, 56);
		contentPane.add(tf_46);
		
		tf_47 = new JTextField();
		tf_47.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_47.setHorizontalAlignment(SwingConstants.CENTER);
		tf_47.setFont(new Font("Arial", Font.BOLD, 22));
		tf_47.setBackground(new Color(152, 148, 132));
		tf_47.setForeground(Color.WHITE);
		tf_47.setColumns(10);
		tf_47.setBounds(768, 312, 56, 56);
		contentPane.add(tf_47);
		
		tf_48 = new JTextField();
		tf_48.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_48.setHorizontalAlignment(SwingConstants.CENTER);
		tf_48.setFont(new Font("Arial", Font.BOLD, 22));
		tf_48.setBackground(new Color(152, 148, 132));
		tf_48.setForeground(Color.WHITE);
		tf_48.setColumns(10);
		tf_48.setBounds(834, 312, 56, 56);
		contentPane.add(tf_48);
		
		tf_51 = new JTextField();
		tf_51.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_51.setHorizontalAlignment(SwingConstants.CENTER);
		tf_51.setFont(new Font("Arial", Font.BOLD, 22));
		tf_51.setBackground(new Color(152, 148, 132));
		tf_51.setForeground(Color.WHITE);
		tf_51.setColumns(10);
		tf_51.setBounds(372, 379, 56, 56);
		contentPane.add(tf_51);
		
		tf_52 = new JTextField();
		tf_52.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_52.setHorizontalAlignment(SwingConstants.CENTER);
		tf_52.setFont(new Font("Arial", Font.BOLD, 22));
		tf_52.setBackground(new Color(152, 148, 132));
		tf_52.setForeground(Color.WHITE);
		tf_52.setColumns(10);
		tf_52.setBounds(438, 379, 56, 56);
		contentPane.add(tf_52);
		
		tf_53 = new JTextField();
		tf_53.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_53.setHorizontalAlignment(SwingConstants.CENTER);
		tf_53.setFont(new Font("Arial", Font.BOLD, 22));
		tf_53.setBackground(new Color(152, 148, 132));
		tf_53.setForeground(Color.WHITE);
		tf_53.setColumns(10);
		tf_53.setBounds(504, 379, 56, 56);
		contentPane.add(tf_53);
		
		tf_54 = new JTextField();
		tf_54.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_54.setHorizontalAlignment(SwingConstants.CENTER);
		tf_54.setFont(new Font("Arial", Font.BOLD, 22));
		tf_54.setBackground(new Color(152, 148, 132));
		tf_54.setForeground(Color.WHITE);
		tf_54.setColumns(10);
		tf_54.setBounds(570, 379, 56, 56);
		contentPane.add(tf_54);
		
		tf_55 = new JTextField();
		tf_55.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_55.setHorizontalAlignment(SwingConstants.CENTER);
		tf_55.setFont(new Font("Arial", Font.BOLD, 22));
		tf_55.setBackground(new Color(152, 148, 132));
		tf_55.setForeground(Color.WHITE);
		tf_55.setColumns(10);
		tf_55.setBounds(636, 379, 56, 56);
		contentPane.add(tf_55);
		
		tf_56 = new JTextField();
		tf_56.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_56.setHorizontalAlignment(SwingConstants.CENTER);
		tf_56.setFont(new Font("Arial", Font.BOLD, 22));
		tf_56.setBackground(new Color(152, 148, 132));
		tf_56.setForeground(Color.WHITE);
		tf_56.setColumns(10);
		tf_56.setBounds(702, 379, 56, 56);
		contentPane.add(tf_56);
		
		tf_57 = new JTextField();
		tf_57.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_57.setHorizontalAlignment(SwingConstants.CENTER);
		tf_57.setFont(new Font("Arial", Font.BOLD, 22));
		tf_57.setBackground(new Color(152, 148, 132));
		tf_57.setForeground(Color.WHITE);
		tf_57.setColumns(10);
		tf_57.setBounds(768, 379, 56, 56);
		contentPane.add(tf_57);
		
		tf_58 = new JTextField();
		tf_58.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_58.setHorizontalAlignment(SwingConstants.CENTER);
		tf_58.setFont(new Font("Arial", Font.BOLD, 22));
		tf_58.setBackground(new Color(152, 148, 132));
		tf_58.setForeground(Color.WHITE);
		tf_58.setColumns(10);
		tf_58.setBounds(834, 379, 56, 56);
		contentPane.add(tf_58);
		
		tf_61 = new JTextField();
		tf_61.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_61.setHorizontalAlignment(SwingConstants.CENTER);
		tf_61.setFont(new Font("Arial", Font.BOLD, 22));
		tf_61.setBackground(new Color(152, 148, 132));
		tf_61.setForeground(Color.WHITE);
		tf_61.setColumns(10);
		tf_61.setBounds(372, 447, 56, 56);
		contentPane.add(tf_61);
		
		tf_62 = new JTextField();
		tf_62.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_62.setHorizontalAlignment(SwingConstants.CENTER);
		tf_62.setFont(new Font("Arial", Font.BOLD, 22));
		tf_62.setBackground(new Color(152, 148, 132));
		tf_62.setForeground(Color.WHITE);
		tf_62.setColumns(10);
		tf_62.setBounds(438, 447, 56, 56);
		contentPane.add(tf_62);
		
		tf_63 = new JTextField();
		tf_63.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_63.setHorizontalAlignment(SwingConstants.CENTER);
		tf_63.setFont(new Font("Arial", Font.BOLD, 22));
		tf_63.setBackground(new Color(152, 148, 132));
		tf_63.setForeground(Color.WHITE);
		tf_63.setColumns(10);
		tf_63.setBounds(504, 447, 56, 56);
		contentPane.add(tf_63);
		
		tf_64 = new JTextField();
		tf_64.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_64.setHorizontalAlignment(SwingConstants.CENTER);
		tf_64.setFont(new Font("Arial", Font.BOLD, 22));
		tf_64.setBackground(new Color(152, 148, 132));
		tf_64.setForeground(Color.WHITE);
		tf_64.setColumns(10);
		tf_64.setBounds(570, 447, 56, 56);
		contentPane.add(tf_64);
		
		tf_65 = new JTextField();
		tf_65.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_65.setHorizontalAlignment(SwingConstants.CENTER);
		tf_65.setFont(new Font("Arial", Font.BOLD, 22));
		tf_65.setBackground(new Color(152, 148, 132));
		tf_65.setForeground(Color.WHITE);
		tf_65.setColumns(10);
		tf_65.setBounds(636, 447, 56, 56);
		contentPane.add(tf_65);
		
		tf_66 = new JTextField();
		tf_66.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_66.setHorizontalAlignment(SwingConstants.CENTER);
		tf_66.setFont(new Font("Arial", Font.BOLD, 22));
		tf_66.setBackground(new Color(152, 148, 132));
		tf_66.setForeground(Color.WHITE);
		tf_66.setColumns(10);
		tf_66.setBounds(702, 447, 56, 56);
		contentPane.add(tf_66);
		
		tf_67 = new JTextField();
		tf_67.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_67.setHorizontalAlignment(SwingConstants.CENTER);
		tf_67.setFont(new Font("Arial", Font.BOLD, 22));
		tf_67.setBackground(new Color(152, 148, 132));
		tf_67.setForeground(Color.WHITE);
		tf_67.setColumns(10);
		tf_67.setBounds(768, 447, 56, 56);
		contentPane.add(tf_67);
		
		tf_68 = new JTextField();
		tf_68.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tf_68.setHorizontalAlignment(SwingConstants.CENTER);
		tf_68.setFont(new Font("Arial", Font.BOLD, 22));
		tf_68.setBackground(new Color(152, 148, 132));
		tf_68.setForeground(Color.WHITE);
		tf_68.setColumns(10);
		tf_68.setBounds(834, 447, 56, 56);
		contentPane.add(tf_68);
		
		JButton btn2 = new JButton("2");
		btn2.setFont(new Font("Arial", Font.BOLD, 18));
		btn2.setForeground(Color.BLACK);
		btn2.setBackground(new Color(226, 232, 240));
		btn2.setBounds(397, 524, 50, 50);
		contentPane.add(btn2);
		
		JButton btn3 = new JButton("3");
		btn3.setFont(new Font("Arial", Font.BOLD, 18));
		btn3.setForeground(Color.BLACK);
		btn3.setBackground(new Color(226, 232, 240));
		btn3.setBounds(457, 524, 50, 50);
		contentPane.add(btn3);
		
		JButton btn4 = new JButton("4");
		btn4.setFont(new Font("Arial", Font.BOLD, 18));
		btn4.setForeground(Color.BLACK);
		btn4.setBackground(new Color(226, 232, 240));
		btn4.setBounds(517, 524, 50, 50);
		contentPane.add(btn4);
		
		JButton btn5 = new JButton("5");
		btn5.setFont(new Font("Arial", Font.BOLD, 18));
		btn5.setForeground(Color.BLACK);
		btn5.setBackground(new Color(226, 232, 240));
		btn5.setBounds(577, 524, 50, 50);
		contentPane.add(btn5);
		
		JButton btn6 = new JButton("6");
		btn6.setFont(new Font("Arial", Font.BOLD, 18));
		btn6.setForeground(Color.BLACK);
		btn6.setBackground(new Color(226, 232, 240));
		btn6.setBounds(638, 524, 50, 50);
		contentPane.add(btn6);
		
		JButton btn7 = new JButton("7");
		btn7.setFont(new Font("Arial", Font.BOLD, 18));
		btn7.setForeground(Color.BLACK);
		btn7.setBackground(new Color(226, 232, 240));
		btn7.setBounds(698, 524, 50, 50);
		contentPane.add(btn7);
		
		JButton btn8 = new JButton("8");
		btn8.setFont(new Font("Arial", Font.BOLD, 18));
		btn8.setForeground(Color.BLACK);
		btn8.setBackground(new Color(226, 232, 240));
		btn8.setBounds(758, 524, 50, 50);
		contentPane.add(btn8);
		
		JButton btn9 = new JButton("9");
		btn9.setFont(new Font("Arial", Font.BOLD, 18));
		btn9.setForeground(Color.BLACK);
		btn9.setBackground(new Color(226, 232, 240));
		btn9.setBounds(818, 524, 50, 50);
		contentPane.add(btn9);
		
		JButton btn0 = new JButton("0");
		btn0.setFont(new Font("Arial", Font.BOLD, 18));
		btn0.setForeground(Color.BLACK);
		btn0.setBackground(new Color(226, 232, 240));
		btn0.setBounds(877, 524, 50, 50);
		contentPane.add(btn0);
		
		JButton btnMas = new JButton("+");
		btnMas.setFont(new Font("Arial", Font.BOLD, 20));
		btnMas.setForeground(Color.BLACK);
		btnMas.setBackground(new Color(226, 232, 240));
		btnMas.setBounds(387, 591, 50, 50);
		contentPane.add(btnMas);
		
		JButton btnMenos = new JButton("-");
		btnMenos.setFont(new Font("Arial", Font.BOLD, 20));
		btnMenos.setForeground(Color.BLACK);
		btnMenos.setBackground(new Color(226, 232, 240));
		btnMenos.setBounds(447, 591, 50, 50);
		contentPane.add(btnMenos);
		
		JButton btnPor = new JButton("*");
		btnPor.setFont(new Font("Arial", Font.BOLD, 27));
		btnPor.setForeground(Color.BLACK);
		btnPor.setBackground(new Color(226, 232, 240));
		btnPor.setBounds(507, 591, 50, 50);
		contentPane.add(btnPor);
		
		JButton btnDiv = new JButton("/");
		btnDiv.setFont(new Font("Arial", Font.BOLD, 20));
		btnDiv.setForeground(Color.BLACK);
		btnDiv.setBackground(new Color(226, 232, 240));
		btnDiv.setBounds(567, 591, 50, 50);
		contentPane.add(btnDiv);
		
		JButton btnIgual = new JButton("=");
		btnIgual.setFont(new Font("Arial", Font.BOLD, 20));
		btnIgual.setForeground(Color.BLACK);
		btnIgual.setBackground(new Color(226, 232, 240));
		btnIgual.setBounds(627, 591, 50, 50);
		contentPane.add(btnIgual);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.setFont(new Font("Arial", Font.BOLD, 18));
		btnEnter.setForeground(Color.BLACK);
		btnEnter.setBackground(new Color(226, 232, 240));
		btnEnter.setBounds(688, 591, 90, 50);
		contentPane.add(btnEnter);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Arial", Font.BOLD, 18));
		btnDelete.setForeground(Color.BLACK);
		btnDelete.setBackground(new Color(226, 232, 240));
		btnDelete.setBounds(790, 592, 100, 50);
		contentPane.add(btnDelete);
		
		lblNewLabel = new JLabel("MathMentor");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Courier New", Font.PLAIN, 70));
		lblNewLabel.setBounds(10, 11, 1248, 84);
		contentPane.add(lblNewLabel);
		
		ImageIcon imagen1=new ImageIcon("./planta1.png");
		ImageIcon imagen2=new ImageIcon("./planta2.png");
		ImageIcon imagen3=new ImageIcon("./flechita.png");
		
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
				if(usuario==null) {
					new ExamenesYJuegos().setVisible(true);
				}
				else {
					new ExamenesYJuegos(usuario).setVisible(true);
				}
				dispose();
			}
		});
		lblAtras.setIcon(imagen3);
		lblAtras.setBounds(10, 11, 50, 50);
		contentPane.add(lblAtras);
	}
}
