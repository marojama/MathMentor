package interfazGrafica;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import principal.Principal;
import xml.Examen;

import javax.swing.JButton;
import javax.swing.JFileChooser;

public class ExamenInterfaz extends JFrame {

	private JPanel contentPane;
	private String usuario;
	private Examen examen;
	private boolean comprobado = false;
	private int actualPregunta = 0;
	private JRadioButton[] respuestas;
	JButton btnComprobarSiguiente;
	JLabel lblPregunta;
	JPanel panel;
	private int correctas = 0;
	private String nombreFich;
	JLabel lblNumPreg;
	JButton btnEnviarArchivo;

	public ExamenInterfaz(String seleccion, String usuario) {
		setResizable(false);
		this.usuario = usuario;
		this.nombreFich = seleccion;
		this.examen = Principal.pedirExamen(seleccion);
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
				new ExamenesYJuegos(usuario).setVisible(true);
				dispose();
			}
		});
		lblAtras.setIcon(imagen3);
		lblAtras.setBounds(10, 11, 50, 50);
		contentPane.add(lblAtras);

		btnComprobarSiguiente = new JButton("Comprobar");
		btnComprobarSiguiente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!comprobado) {
					boolean seleccionado = false;
					for (int i = 0; i < respuestas.length && !seleccionado; i++) {
						if (respuestas[i].isSelected()) {
							seleccionado = true;
						}
					}
					if (seleccionado) {
						corregirRespuesta();
					} else {
						JOptionPane.showMessageDialog(ExamenInterfaz.this, "Debes seleccionar una respuesta.",
								"Ninguna respuesta seleccionada", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					if (btnComprobarSiguiente.getText().equals("Finalizar")) {
						JOptionPane.showMessageDialog(panel,
								"¡Enhorabuena! Has terminado. Tu resultado final ha sido de " + correctas
										+ " preguntas correctas de " + examen.getPreguntas().size()
										+ ". Tus resultados se han enviado a tu profe. Recuerda enviar una foto con las cuentas realizadas en el botón Enviar Archivo.",
								"Fin examen", JOptionPane.OK_OPTION);
						examen.setNumCorrectas(correctas);
						examen.setFecha(LocalDateTime.now());
						Principal.enviarExamen(examen, nombreFich, usuario);
						btnComprobarSiguiente.setText("Salir");
						btnEnviarArchivo.setEnabled(true);
					} else if (btnComprobarSiguiente.getText().equals("Salir")) {
						new ExamenesYJuegos(usuario).setVisible(true);
						dispose();
					} else {
						pasarSiguiente();
					}
				}
			}
		});
		btnComprobarSiguiente.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnComprobarSiguiente.setBounds(560, 567, 141, 37);
		contentPane.add(btnComprobarSiguiente);

		JLabel lblAcertadasTotales = new JLabel("");
		lblAcertadasTotales.setBounds(833, 106, 106, 31);
		contentPane.add(lblAcertadasTotales);

		JLabel lblTema = new JLabel("New label");
		lblTema.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTema.setText(examen.getTema());
		lblTema.setBounds(330, 106, 322, 31);
		contentPane.add(lblTema);

		lblNumPreg = new JLabel("");
		lblNumPreg.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumPreg.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNumPreg.setBounds(783, 106, 156, 31);
		lblNumPreg.setText(this.actualPregunta + "/" + examen.getPreguntas().size());
		contentPane.add(lblNumPreg);

		btnEnviarArchivo = new JButton("Enviar Archivo");
		btnEnviarArchivo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Seleccionar Archivo");

				int seleccion = fileChooser.showOpenDialog(ExamenInterfaz.this);

				if (seleccion == JFileChooser.APPROVE_OPTION) {
					File archivo = fileChooser.getSelectedFile();
					Principal.enviarArchivo(archivo);
				}
			}
		});
		btnEnviarArchivo.setEnabled(false);
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

	public void corregirRespuesta() {
		int correcta = examen.getPreguntas().get(actualPregunta).getCorrecta();
		respuestas[correcta].setBackground(Color.GREEN);
		for (int i = 0, n = respuestas.length; i < n; i++) {
			if (respuestas[i].isSelected()) {
				if (i == correcta) {
					this.correctas++;
				} else {
					respuestas[i].setBackground(Color.RED);
				}
				examen.getPreguntas().get(actualPregunta).setContestada(i);
			}
		}
		if (examen.getPreguntas().size() > this.actualPregunta + 1) {
			btnComprobarSiguiente.setText("Siguiente");
		} else {
			this.actualPregunta++;
			lblNumPreg.setText(actualPregunta + "/" + examen.getPreguntas().size());
			btnComprobarSiguiente.setText("Finalizar");
		}
		this.comprobado = true;

	}

	public void pasarSiguiente() {
		this.actualPregunta++;
		lblNumPreg.setText(this.actualPregunta + "/" + examen.getPreguntas().size());
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
		btnComprobarSiguiente.setText("Comprobar");
		this.comprobado = false;
	}
}
