package interfazGrafica;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.time.LocalDateTime;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import principal.Principal;
import xml.Examen;

import javax.swing.JButton;
import javax.swing.JFileChooser;

public class ExamenInterfazRevisar extends JFrame {

	private JPanel contentPane;
	private Examen examen;
	private ImageIcon imagen;
	private int actualPregunta = 0;
	private JRadioButton[] respuestas;
	JButton btnComprobarSiguiente= new JButton("Siguiente");
	JLabel lblPregunta;
	JPanel panel;
	JLabel lblNumPreg;
	JButton btnEnviarArchivo;

	public ExamenInterfazRevisar(String seleccion, String alumno) {
		setResizable(false);
		this.examen = Principal.pedirExamen(seleccion, alumno);
		this.imagen=Principal.pedirImagen();
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

		panel = new JPanel();
		panel.setBounds(330, 148, 609, 408);
		contentPane.add(panel);
		int numRespuestas = examen.getPreguntas().get(0).getRespuestas().size();
		GridLayout grid = new GridLayout(numRespuestas + 1, 1);
		panel.setLayout(grid);

		lblPregunta = new JLabel("New label");
		lblPregunta.setText(examen.getPreguntas().get(0).getEnunciado());
		lblPregunta.setVerticalAlignment(SwingConstants.TOP);
		lblPregunta.setHorizontalAlignment(SwingConstants.LEFT);
		lblPregunta.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		panel.add(lblPregunta);

		respuestas = new JRadioButton[numRespuestas];
		ButtonGroup grupo = new ButtonGroup();
		for (int i = 0; i < numRespuestas; i++) {
			respuestas[i] = new JRadioButton(examen.getPreguntas().get(0).getRespuestas().get(i));
			respuestas[i].setFont(new Font("Tahoma", Font.PLAIN, 15));
			respuestas[i].setHorizontalAlignment(SwingConstants.LEFT);
			panel.add(respuestas[i]);
			grupo.add(respuestas[i]);
		}
		this.actualPregunta = 0;
		corregirRespuesta();

		ImageIcon imagen1 = new ImageIcon("./src/planta1.png");
		ImageIcon imagen2 = new ImageIcon("./src/planta2.png");
		ImageIcon imagen3 = new ImageIcon("./src/flechita.png");
		
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
				new PantallaProfesora().setVisible(true);
				dispose();
			}
		});
		lblAtras.setIcon(imagen3);
		lblAtras.setBounds(10, 11, 50, 50);
		contentPane.add(lblAtras);

		btnComprobarSiguiente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnComprobarSiguiente.getText().equals("Salir")) {
					new PantallaProfesora().setVisible(true);
					dispose();
				} else {
					pasarSiguiente();
				}

			}
		});
		btnComprobarSiguiente.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnComprobarSiguiente.setBounds(560, 567, 141, 37);
		contentPane.add(btnComprobarSiguiente);

		JLabel lblTema = new JLabel("New label");
		lblTema.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTema.setText(examen.getTema());
		lblTema.setBounds(330, 106, 322, 31);
		contentPane.add(lblTema);

		lblNumPreg = new JLabel("");
		lblNumPreg.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumPreg.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNumPreg.setBounds(783, 106, 156, 31);
		lblNumPreg.setText(this.actualPregunta + 1 + "/" + examen.getPreguntas().size());
		contentPane.add(lblNumPreg);

		btnEnviarArchivo = new JButton("Ver Archivo");
		if(imagen==null) {
			btnEnviarArchivo.setEnabled(false);
		}
		btnEnviarArchivo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(btnEnviarArchivo.isEnabled()) {
					mostrarImagen();
				}
			}
		});
		btnEnviarArchivo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEnviarArchivo.setBounds(750, 567, 175, 37);
		contentPane.add(btnEnviarArchivo);

		// Evento para que al cerrar la aplicación cierre los socket y no salte
		// excepcion en el servidor
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Principal.cerrar();
			}
		});
	}

	protected void mostrarImagen() {
		JFrame jfImagenExamen = new JFrame();
		JPanel contenedorImagen = new JPanel();
		contenedorImagen.setBorder(new EmptyBorder(5, 5, 5, 5));
		jfImagenExamen.setContentPane(contenedorImagen);
		jfImagenExamen.setVisible(true);
		jfImagenExamen.setTitle("Imagen del examen");
		
		Image image = imagen.getImage();
		int width = imagen.getIconWidth(), height = imagen.getIconHeight();
		int redWid, redHei;
		//Imagen vertical
		if (width < height) {
			redWid = 400;
			redHei = redWid*height/width;
		}
		//Imagen horizontal
		else {
			redHei = 400;
			redWid = redHei*width/height;
		}
		//Imagen redimensionada
		ImageIcon imagenMostrar = new ImageIcon(image.getScaledInstance(redWid, redHei, Image.SCALE_SMOOTH));
		jfImagenExamen.setBounds(0, 0, imagenMostrar.getIconWidth(), imagenMostrar.getIconHeight() + 50);
		JLabel lblImagenExamen = new JLabel("");
		lblImagenExamen.setBounds(jfImagenExamen.getBounds());
		lblImagenExamen.setIcon(imagenMostrar);
		jfImagenExamen.setResizable(false);
		contenedorImagen.add(lblImagenExamen);
	}

	public void corregirRespuesta() {
		int correcta = examen.getPreguntas().get(actualPregunta).getCorrecta();
		respuestas[correcta].setBackground(Color.GREEN);
		int numContestada = examen.getPreguntas().get(actualPregunta).getContestada();
		respuestas[numContestada].setSelected(true);
		if (numContestada != correcta) {
			respuestas[numContestada].setBackground(Color.RED);
		}
		if (this.actualPregunta + 1 == this.examen.getPreguntas().size()) {
			btnComprobarSiguiente.setText("Salir");
		}
	}

	public void pasarSiguiente() {
		this.actualPregunta++;
		lblNumPreg.setText(this.actualPregunta + 1 + "/" + examen.getPreguntas().size());
		int numRespuestas = examen.getPreguntas().get(this.actualPregunta).getRespuestas().size();
		GridLayout grid = new GridLayout(numRespuestas + 1, 1);
		panel.setLayout(grid);

		lblPregunta.setText(examen.getPreguntas().get(this.actualPregunta).getEnunciado());
		for (int i = 0, n = this.respuestas.length; i < n; i++) {
			panel.remove(respuestas[i]);
		}
		respuestas = new JRadioButton[numRespuestas];
		ButtonGroup grupo = new ButtonGroup();
		for (int i = 0; i < numRespuestas; i++) {
			respuestas[i] = new JRadioButton(examen.getPreguntas().get(this.actualPregunta).getRespuestas().get(i));
			respuestas[i].setFont(new Font("Tahoma", Font.PLAIN, 15));
			respuestas[i].setHorizontalAlignment(SwingConstants.LEFT);
			panel.add(respuestas[i]);
			grupo.add(respuestas[i]);
		}
		corregirRespuesta();
	}
}
