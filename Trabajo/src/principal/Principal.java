package principal;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import interfazGrafica.InicioSesion;
import interfazGrafica.PantallaProfesora;
import xml.Examen;
import xml.Pregunta;

public class Principal {

	private static Socket s;

	public static void main(String[] args) {

		ArrayList<String> r = new ArrayList<String>();
		ArrayList<String> r1 = new ArrayList<String>();
		r.add("Bien");
		r.add("De puta madre");
		r.add("Tu puta madre");
		Pregunta p = new Pregunta("Que tal estas?", r);
		r1.add("Me quiero morir");
		Pregunta p2 = new Pregunta("Que tal estas?", r1);
		ArrayList<Pregunta> ps = new ArrayList<>();
		ps.add(p);
		ps.add(p);
		ps.add(p);
		ps.add(p2);
		Examen e = new Examen(1, true, "tema 1", Date.valueOf(LocalDate.now()), ps);

		try {
			JAXBContext context = JAXBContext.newInstance(Examen.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(e, new File("Ejemplo.xml"));
		} catch (JAXBException ñ) {
			ñ.printStackTrace();
		}

		try {
			s = new Socket("localhost", 55555);
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
			BufferedReader bin = new BufferedReader(new InputStreamReader(s.getInputStream()));
			BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			bout.write("Iniciar sesion\n");
			bout.write(usuario + "\n");
			bout.flush();
			String respuesta = bin.readLine();
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
			BufferedReader bin = new BufferedReader(new InputStreamReader(s.getInputStream()));
			BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			bout.write("Gestionar usuarios\n");
			bout.write("Borrar usuario\n");
			bout.write(usuario + "\n");
			bout.flush();
			String respuesta = bin.readLine();
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
			BufferedReader bin = new BufferedReader(new InputStreamReader(s.getInputStream()));
			BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			bout.write("Gestionar usuarios\n");
			bout.write("Crear usuario\n");
			bout.write(usuario + "\n");
			bout.flush();
			String respuesta = bin.readLine();
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
}
