package principal;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import interfazGrafica.InicioSesion;
import interfazGrafica.PantallaProfesora;
import xml.Examen;
import xml.Pregunta;

public class Principal {

	private static Socket s;
	private static ObjectInputStream is;
	private static ObjectOutputStream os;

	public static void main(String[] args) {

		try {
			s = new Socket("localhost", 55555);
			is=new ObjectInputStream(s.getInputStream());
			os=new ObjectOutputStream(s.getOutputStream());
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
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static boolean existeUsuario(String usuario) {
		try {
			os.writeBytes("Iniciar sesion\n");
			os.writeBytes(usuario + "\n");
			os.flush();
			String respuesta = is.readLine();
			if (respuesta.equals("Inicio sesion correcto")) {
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	public static boolean borrarUsuario(String usuario) {
		try {
			os.writeBytes("Gestionar usuarios\n");
			os.writeBytes("Borrar usuario\n");
			os.writeBytes(usuario + "\n");
			os.flush();
			String respuesta = is.readLine();
			if (respuesta.equals("Borrado correcto")) {
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public static boolean crearUsuario(String usuario) {
		try {
			os.writeBytes("Gestionar usuarios\n");
			os.writeBytes("Crear usuario\n");
			os.writeBytes(usuario + "\n");
			os.flush();
			String respuesta = is.readLine();
			if (respuesta.equals("Creado correcto")) {
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
	
	public static String[] nombreExamenes(){
		try {
			os.writeBytes("Examenes\n");
			os.flush();
			String[] nomExam=(String[]) is.readObject();
			return nomExam;
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Examen pedirExamen(String seleccion) {
		try {
			os.writeBytes("Examen\n");
			os.writeBytes(seleccion+"\n");
			os.flush();
			Examen Exam= (Examen) is.readObject();
			return Exam;
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static void enviarExamen(Examen examen, String nombreFich, String usuario) {
		try {
			os.writeBytes("Examen hecho\n");
			os.writeBytes(nombreFich+"\n");
			os.writeBytes(usuario+"\n");
			os.writeObject(examen);
			os.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
