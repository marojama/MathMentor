package interfazGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import principal.Principal;
import xml.Examen;
import xml.Pregunta;

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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PantallaProfesora extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaProfesora frame = new PantallaProfesora();
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
	public PantallaProfesora() {
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

		JButton btnGestionarUsuarios = new JButton("Añadir usuarios");
		btnGestionarUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String usuario = JOptionPane.showInputDialog("Introduce un usuario");
				if (usuario.equals("")) {
					JOptionPane.showMessageDialog(PantallaProfesora.this, "El usuario no puede ser vacio",
							"Error usuario", JOptionPane.ERROR_MESSAGE);
				} else {
					boolean existe = Principal.existeUsuario(usuario);
					if (existe) {
						int opcion = JOptionPane.showOptionDialog(PantallaProfesora.this,
								"El usuario existe, ¿quieres borrarlo?", "Borrar usuario", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Si", "No" }, "Si");
						if (opcion == 0) {
							Principal.borrarUsuario(usuario);
						}
					} else {
						int opcion = JOptionPane.showOptionDialog(PantallaProfesora.this,
								"El usuario no existe, ¿quieres crearlo?", "Crear usuario", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Si", "No" }, "Si");
						if (opcion == 0) {
							Principal.crearUsuario(usuario);
						}
					}
				}
			}
		});
		btnGestionarUsuarios.setForeground(Color.WHITE);
		btnGestionarUsuarios.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		btnGestionarUsuarios.setBackground(new Color(100, 182, 172));
		btnGestionarUsuarios.setBounds(490, 200, 300, 50);
		contentPane.add(btnGestionarUsuarios);

		JButton btnVerEnvios = new JButton("Ver envios");
		btnVerEnvios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String[] listaAlumnos = Principal.nombreAlumnos();
				if (listaAlumnos.length==0) {
					JOptionPane.showMessageDialog(PantallaProfesora.this, "Aún no tienes alumnos", "No hay alumnos",
							JOptionPane.OK_OPTION);
				} else {
					String alumno = (String) JOptionPane.showInputDialog(PantallaProfesora.this, "Selecciona el alumno",
							"Selección alumno", JOptionPane.QUESTION_MESSAGE, null, listaAlumnos, listaAlumnos[0]);
					String[] listaExamenes = Principal.nombresExamenes(alumno);
					if (listaExamenes.length==0) {
						JOptionPane.showMessageDialog(PantallaProfesora.this,
								"Aún no ha realizado los examenes el alumnos", "No hay examenes a mostrar",
								JOptionPane.OK_OPTION);
					} else {
						String seleccion = (String) JOptionPane.showInputDialog(PantallaProfesora.this,
								"Selecciona el alumno", "Selección alumno", JOptionPane.QUESTION_MESSAGE, null,
								listaExamenes, listaExamenes[0]);
						new ExamenInterfazRevisar(seleccion, alumno).setVisible(true);
						PantallaProfesora.this.dispose();
					}
				}
			}
		});
		btnVerEnvios.setForeground(Color.WHITE);
		btnVerEnvios.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		btnVerEnvios.setBackground(new Color(100, 182, 172));
		btnVerEnvios.setBounds(490, 300, 300, 50);
		contentPane.add(btnVerEnvios);

		JButton btnVerEstadsticas = new JButton("Juegos");
		btnVerEstadsticas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cambiarPantallaJuegos();
			}
		});
		btnVerEstadsticas.setForeground(Color.WHITE);
		btnVerEstadsticas.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		btnVerEstadsticas.setBackground(new Color(100, 182, 172));
		btnVerEstadsticas.setBounds(490, 400, 300, 50);
		contentPane.add(btnVerEstadsticas);

		JButton btnCrearExamen = new JButton("Crear examen");
		btnCrearExamen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				crearExamen();
			}
		});
		btnCrearExamen.setForeground(Color.WHITE);
		btnCrearExamen.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		btnCrearExamen.setBackground(new Color(100, 182, 172));
		btnCrearExamen.setBounds(490, 500, 300, 50);
		contentPane.add(btnCrearExamen);

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
				new PantallaInicial().setVisible(true);
				dispose();
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

	protected void crearExamen() {
		Examen e=new Examen();
		List<Pregunta> preguntas=new ArrayList<>();
		
		String[] listaAlumnos = Principal.nombreAlumnos();
		if (listaAlumnos.length==0) {
			JOptionPane.showMessageDialog(PantallaProfesora.this, "Aún no tienes alumnos", "No hay alumnos",
					JOptionPane.OK_OPTION);
		} else {
			String alumno = (String) JOptionPane.showInputDialog(PantallaProfesora.this, "Selecciona el alumno",
					"Selección alumno", JOptionPane.QUESTION_MESSAGE, null, listaAlumnos, listaAlumnos[0]);
			
			String tema=JOptionPane.showInputDialog(PantallaProfesora.this, "Introduzca el nombre del tema", "Tema", JOptionPane.QUESTION_MESSAGE);
			e.setTema(tema);
			
			String pregunta="";
			while(pregunta!=null) {
				pregunta=JOptionPane.showInputDialog(PantallaProfesora.this, "Introduzca la pregunta", "Pregunta", JOptionPane.QUESTION_MESSAGE);
				if(pregunta!=null) {
					Pregunta p=new Pregunta();
					p.setEnunciado("<html>"+pregunta+"</html>");
					int n=Integer.parseInt(JOptionPane.showInputDialog(PantallaProfesora.this, "Introduzca el número de respuestas", "Número de respuestas", JOptionPane.QUESTION_MESSAGE));
					List<String> respuestas=new ArrayList<>();
					boolean correcta=false;
					for(int i=0;i<n;i++) {
						String r=JOptionPane.showInputDialog(PantallaProfesora.this, "Introduzca la respuestas "+(i+1), "Respuestas", JOptionPane.QUESTION_MESSAGE);
						respuestas.add("<html>"+r+"</html>");
						if(!correcta) {
							int opcion = JOptionPane.showOptionDialog(PantallaProfesora.this,
									"¿La respuesta que acabas de introducir es la correcta?", "Respuesta correcta", JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Si", "No" }, "Si");
							if (opcion == 0) {
								p.setCorrecta(i);
								correcta=true;
							}
						}
					}
					p.setRespuestas(respuestas);
					preguntas.add(p);
				}
			}
			e.setPreguntas(preguntas);
			e.setFecha(LocalDateTime.now());
			Principal.enviarExamen(e, tema, alumno);
		}
				
	}

	private void cambiarPantallaJuegos() {
		Juegos j = new Juegos("Profe");
		j.setVisible(true);
		this.dispose();
	}

}
