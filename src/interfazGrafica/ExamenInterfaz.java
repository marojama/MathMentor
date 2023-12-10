// Interfaz Examenes
// Aplicación: MathMentor
// Autor: Marta Rojas

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

public class ExamenInterfaz extends JFrame {

	private JPanel contentPane;
	private JRadioButton[] respuestas;
	private JButton btnComprobarSiguiente;
	private JLabel lblPregunta;
	private JPanel panel;
	private JLabel lblNumPreg;
	private JButton btnEnviarArchivo;

	private boolean comprobado = false;
	private int actualPregunta = 0;
	private Examen examen;
	private int correctas = 0;
	private String nombreFich;
	private ImageIcon imagen;

	/**
	 * Interfaz para ver los examenes para el alumno o para corregir los enviados
	 * para la profesora
	 * 
	 * @param seleccion: el examen a resolver
	 * @param usuario:   el usuario que va a resolver el examen
	 * @param profe:     true si es la profesora, false si es el alumno
	 */
	public ExamenInterfaz(String seleccion, String usuario, boolean profe) {
		setResizable(false);
		this.nombreFich = seleccion;
		// Pedimos al principal el examen a realizar/corregir
		this.examen = Principal.pedirExamen(seleccion);
		// Si es la profe, pedimos además la posible imagen que halla mandado el alumno
		if (profe) {
			this.imagen = Principal.pedirImagen();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(218, 255, 239));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Decoración interfaz

		JLabel titulo = new JLabel("MathMentor");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Courier New", Font.PLAIN, 70));
		titulo.setBounds(10, 11, 1248, 84);
		contentPane.add(titulo);

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

		// "Botón" volver atrás
		JLabel lblAtras = new JLabel("");
		lblAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Si es la profesora
				if (profe) {
					new PantallaProfesora().setVisible(true);
					dispose();
				}
				// Si es el alumno
				else {
					new ExamenesYJuegos(usuario).setVisible(true);
					dispose();
				}
			}
		});
		lblAtras.setIcon(imagen3);
		lblAtras.setBounds(10, 11, 50, 50);
		contentPane.add(lblAtras);

		panel = new JPanel();
		panel.setBounds(330, 148, 609, 408);
		contentPane.add(panel);
		// Dividimos la pantalla segun el numero de respuestas + 1(la pregunta)
		int numRespuestas = examen.getPreguntas().get(0).getRespuestas().size();
		GridLayout grid = new GridLayout(numRespuestas + 1, 1);
		panel.setLayout(grid);

		// Label de la pregunta
		lblPregunta = new JLabel();
		lblPregunta.setText(examen.getPreguntas().get(0).getEnunciado());
		lblPregunta.setVerticalAlignment(SwingConstants.TOP);
		lblPregunta.setHorizontalAlignment(SwingConstants.LEFT);
		lblPregunta.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		panel.add(lblPregunta);

		// Respuestas
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
		// Si es la profesora corrigiendolo, que le aparezca directamente corregido
		if (profe) {
			corregirRespuestaProfe();
		}

		// Boton comprobar/siguiente pregunta
		btnComprobarSiguiente = new JButton("Comprobar");
		if (profe) {
			btnComprobarSiguiente.setText("Siguiente");
		}
		btnComprobarSiguiente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Si es la profesora
				if (profe) {
					// Si es salir, volvemos atras
					if (btnComprobarSiguiente.getText().equals("Salir")) {
						new PantallaProfesora().setVisible(true);
						dispose();
						// Si no, pasamos a la siguiente pregunta
					} else {
						pasarSiguiente(profe);
					}
				} else {
					// Si aun no hemos corregido la pregunta
					if (!comprobado) {
						// Por si no ha seleccionado ninguna pregunta
						boolean seleccionado = false;
						for (int i = 0; i < respuestas.length && !seleccionado; i++) {
							if (respuestas[i].isSelected()) {
								seleccionado = true;
							}
						}
						if (seleccionado) {
							// Si ha seleccionado, corregimos una respuesta
							corregirRespuesta();
						} else {
							JOptionPane.showMessageDialog(ExamenInterfaz.this, "Debes seleccionar una respuesta.",
									"Ninguna respuesta seleccionada", JOptionPane.ERROR_MESSAGE);
						}
						// Si ya está la pregunta corregida
					} else {
						// Finalizar examen
						if (btnComprobarSiguiente.getText().equals("Finalizar")) {
							JOptionPane.showMessageDialog(panel,
									"¡Enhorabuena! Has terminado. Tu resultado final ha sido de " + correctas
											+ " preguntas correctas de " + examen.getPreguntas().size()
											+ ". Tus resultados se han enviado a tu profe.\nRecuerda enviar una foto con las cuentas realizadas en el botón Enviar Archivo. SOLO SE ENVIA UNA FOTO!",
									"Fin examen", JOptionPane.OK_OPTION);
							examen.setNumCorrectas(correctas);
							Principal.enviarExamen(examen, nombreFich, usuario);
							btnComprobarSiguiente.setText("Salir");
							btnEnviarArchivo.setEnabled(true);
							// Salir a la pantalla anterior
						} else if (btnComprobarSiguiente.getText().equals("Salir")) {
							new ExamenesYJuegos(usuario).setVisible(true);
							dispose();
							// Pasar a la siguiente pregunta
						} else {
							pasarSiguiente(profe);
						}
					}
				}
			}
		});
		btnComprobarSiguiente.setForeground(Color.WHITE);
		btnComprobarSiguiente.setBackground(new Color(100,182,172));
		btnComprobarSiguiente.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnComprobarSiguiente.setBounds(560, 567, 141, 37);
		contentPane.add(btnComprobarSiguiente);

		// Label con el nombre del tema
		JLabel lblTema = new JLabel();
		lblTema.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTema.setText(examen.getTema());
		lblTema.setBounds(330, 106, 322, 31);
		contentPane.add(lblTema);

		// Label con el num de pregunta y las totales
		lblNumPreg = new JLabel();
		lblNumPreg.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumPreg.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNumPreg.setBounds(783, 106, 156, 31);
		lblNumPreg.setText(this.actualPregunta + "/" + examen.getPreguntas().size());
		contentPane.add(lblNumPreg);

		// Si es la profe, ver archivo
		if (profe) {
			btnEnviarArchivo = new JButton("Ver Archivo");
			if (imagen == null) {
				btnEnviarArchivo.setEnabled(false);
			}
			btnEnviarArchivo.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (btnEnviarArchivo.isEnabled()) {
						mostrarImagen();
					}
				}
			});
			// Si es el alumno, enviar un fichero
		} else {
			btnEnviarArchivo = new JButton("Enviar Archivo");
			btnEnviarArchivo.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// Para que el usuario pueda seleccionar un archivo de su equipo
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setDialogTitle("Seleccionar Archivo");

					int seleccion = fileChooser.showOpenDialog(ExamenInterfaz.this);

					if (seleccion == JFileChooser.APPROVE_OPTION) {
						File archivo = fileChooser.getSelectedFile();
						// Le pedimos al principal que lo mande
						Principal.enviarArchivo(archivo);
					}
				}
			});
			btnEnviarArchivo.setEnabled(false);
		}
		btnEnviarArchivo.setForeground(Color.WHITE);
		btnEnviarArchivo.setBackground(new Color(100,182,172));
		btnEnviarArchivo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEnviarArchivo.setBounds(750, 567, 175, 37);
		contentPane.add(btnEnviarArchivo);

		// Evento para que al cerrar la aplicación cierre los socket
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Principal.cerrar();
			}
		});
	}

	/**
	 * Método para poder mostrar la imagen
	 */
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
		// Imagen vertical
		if (width < height) {
			redWid = 400;
			redHei = redWid * height / width;
		}
		// Imagen horizontal
		else {
			redHei = 400;
			redWid = redHei * width / height;
		}
		// Imagen redimensionada
		ImageIcon imagenMostrar = new ImageIcon(image.getScaledInstance(redWid, redHei, Image.SCALE_SMOOTH));
		jfImagenExamen.setBounds(0, 0, imagenMostrar.getIconWidth(), imagenMostrar.getIconHeight() + 50);
		JLabel lblImagenExamen = new JLabel("");
		lblImagenExamen.setBounds(jfImagenExamen.getBounds());
		lblImagenExamen.setIcon(imagenMostrar);
		jfImagenExamen.setResizable(false);
		contenedorImagen.add(lblImagenExamen);
	}

	/**
	 * Método que muestra la pregunta corregida en el caso de la profesora (en el
	 * caso de tener ya el examen resuelto por el alumno)
	 */
	public void corregirRespuestaProfe() {
		// Guardamos el indice de la correcta guardado en el examen
		int correcta = examen.getPreguntas().get(actualPregunta).getCorrecta();
		// Pintamos de verde la respuesta correcta
		respuestas[correcta].setBackground(Color.GREEN);

		// Guardamos el indice de la contestada por el alumno
		int numContestada = examen.getPreguntas().get(actualPregunta).getContestada();
		// Seleccionamos la contestada por el alumno
		respuestas[numContestada].setSelected(true);

		// Si la correcta no coincide con la contestada, la ponemos de rojo
		if (numContestada != correcta) {
			respuestas[numContestada].setBackground(Color.RED);
		}

		// Si es la última
		if (this.actualPregunta + 1 == this.examen.getPreguntas().size()) {
			btnComprobarSiguiente.setText("Salir");
		}
	}

	/**
	 * Método que corrige una respuesta automáticamente
	 */
	public void corregirRespuesta() {
		// Guardamos el indice de la correcta guardado en el examen
		int correcta = examen.getPreguntas().get(actualPregunta).getCorrecta();
		// Pintamos de verde la respuesta correcta
		respuestas[correcta].setBackground(Color.GREEN);

		// Buscamos la respuesta seleccionada
		for (int i = 0, n = respuestas.length; i < n; i++) {
			if (respuestas[i].isSelected()) {
				// Si coincide con la correcta, sumamos 1 a las respondidas bien
				if (i == correcta) {
					this.correctas++;
					// Sino la pintamos de rojo
				} else {
					respuestas[i].setBackground(Color.RED);
				}
				// Guardamos el índice de la pregunta contestada, para que pueda verlo el
				// profesor
				examen.getPreguntas().get(actualPregunta).setContestada(i);
			}
		}
		// Si aun quedan preguntas por contestar
		if (examen.getPreguntas().size() > this.actualPregunta + 1) {
			btnComprobarSiguiente.setText("Siguiente");
			// Si es la última pregunta
		} else {
			this.actualPregunta++;
			lblNumPreg.setText(actualPregunta + "/" + examen.getPreguntas().size());
			btnComprobarSiguiente.setText("Finalizar");
		}
		// Para que la siguiente vez, el mismo botón pase de pregunta en vez de
		// corregirla
		this.comprobado = true;
	}

	/**
	 * Método que pasa de pregunta
	 */
	public void pasarSiguiente(boolean profe) {
		// Actualizamos el número de la pregunta actual y el marcador
		this.actualPregunta++;
		lblNumPreg.setText(this.actualPregunta + "/" + examen.getPreguntas().size());

		// Actualizamos el layout según el número de respuestas de la siguiente pregunta
		int numRespuestas = examen.getPreguntas().get(this.actualPregunta).getRespuestas().size();
		GridLayout grid = new GridLayout(numRespuestas + 1, 1);
		panel.setLayout(grid);

		// Cambiamos el enunciado y las respuestas
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

		// Si es la profesora, que le aparezca corregida
		if (profe) {
			corregirRespuestaProfe();
		} else {
			// Si es el alumno, Volvemos a cambiar el botón, para que esta vez compruebe la
			// respuesta
			btnComprobarSiguiente.setText("Comprobar");
			this.comprobado = false;
		}

	}
}
