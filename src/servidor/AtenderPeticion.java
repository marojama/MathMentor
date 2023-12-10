package servidor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import xml.Examen;
import xml.Jugador;
import xml.Pregunta;

public class AtenderPeticion extends Thread {

	private Socket s;
	private ObjectInputStream is;
	private ObjectOutputStream os;
	private String usuario;
	private String nomFich;
	
	public AtenderPeticion(Socket s) {

		try {
			this.s = s;
			os = new ObjectOutputStream(s.getOutputStream());
			is = new ObjectInputStream(s.getInputStream());
			this.usuario = "";
			// Almacen de usuarios
			File f = new File("Usuarios.txt");
			if (!f.exists()) {
				try {
					f.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// Estadisticas juegos
			File j1 = new File("AdivinarNum.xml");
			if (!j1.exists()) {
				try {
					j1.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			File j2 = new File("HeridosYMuertos.xml");
			if (!j2.exists()) {
				try {
					j2.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void run() {
		try {
			String opcion = is.readLine();

			while (opcion != null) {
				if (opcion.equals("Iniciar sesion")) {
					inicioSesion();
				} else if (opcion.equals("Examenes")) {
					String[] nomExam = devolverNomExam(this.usuario);
					os.writeObject(nomExam);
					os.flush();
				} else if (opcion.equals("Examen")) {
					String e = is.readLine();
					Examen examen = devolverExam(this.usuario, e);
					os.writeObject(examen);
					os.flush();
				} else if (opcion.equals("Examen Alumno")) {
					String e = is.readLine();
					String u = is.readLine();
					Examen examen = devolverExam(u, e);
					File dir = new File(u);
					File imagen = null;
					ImageIcon i = null;
					for (File f : dir.listFiles()) {
						if (f.getName().startsWith(e.split("Resuelto")[0]) && f.getName().contains("Imagen")) {
							imagen = f;
							i = new ImageIcon(Files.readAllBytes(imagen.toPath()));
						}
					}
					os.writeObject(examen);
					os.writeObject(i);
					os.flush();
				} else if (opcion.equals("Gestionar usuarios")) {
					gestionarUsuarios();
				} else if (opcion.equals("Examen hecho")) {
					this.nomFich = is.readLine();
					String usuario = is.readLine();
					recibirExamen(nomFich, usuario, false);
				} else if (opcion.equals("Examen nuevo")) {
					this.nomFich = is.readLine();
					String usuario = is.readLine();
					recibirExamen(nomFich, usuario, true);
				} else if (opcion.equals("Fichero")) {
					fichero();
				} else if (opcion.equals("Alumnos")) {
					String[] nomAlum = devolverNomAlumnos();
					os.writeObject(nomAlum);
					os.flush();
				} else if (opcion.equals("Nombres examenes")) {
					String e = is.readLine();
					String[] nomExam = devolverNomExamenes(e);
					os.writeObject(nomExam);
					os.flush();
				} else if (opcion.equals("AdivinarNum")) {
					adivinarNum();
				} else if (opcion.equals("HeridosYMuertos")) {
					heridosYMuertos();
				} else if (opcion.equals("Estadisticas")) {
					String juego = is.readLine();
					List<Jugador> lista = enviarEstadisticas(juego);
					os.writeObject(lista);
					os.flush();
				} else if (opcion.equals("Enviar estadisticas")) {
					String juego = is.readLine();
					String usuario = is.readLine();
					int intentos = is.readInt();
					long tiempo = is.readLong();
					guardarEstadisticas(juego, usuario, intentos, tiempo);
				}
				opcion = is.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void guardarEstadisticas(String juego, String usuario2, int intentos, long tiempo) {
		List<Jugador> estads= enviarEstadisticas(juego);

		if(estads==null) {
			Jugador j = new Jugador(usuario2, 1, intentos, tiempo);
			estads=new ArrayList<>();
			estads.add(j);
		}
		else {
			boolean esta = false;

			for (Jugador jugador : estads) {
				if (jugador.getNombre().equals(usuario2)) {
					esta = true;
					if (jugador.getIntentos() > intentos) {
						jugador.setIntentos(intentos);
					}
					if (jugador.getMejorTiempo() > tiempo) {
						jugador.setMejorTiempo(tiempo);
					}
					int v=jugador.getVictorias();
					jugador.setVictorias(++v);
				}
			}

			if (!esta) {
				Jugador j = new Jugador(usuario2, 1, intentos, tiempo);
				estads.add(j);
			}
		}
		
		incluirEstads(juego,estads);

	}

	private void incluirEstads(String juego, List<Jugador> estads) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();

			Element jugadores = doc.createElement("jugadores");

			for (int i = 0, n = estads.size(); i < n; i++) {
				Element jugador = doc.createElement("jugador");

				Element nombre = doc.createElement("nombre");
				nombre.appendChild(doc.createTextNode(estads.get(i).getNombre()));
				jugador.appendChild(nombre);

				Element victorias = doc.createElement("victorias");
				victorias.appendChild(doc.createTextNode(String.valueOf(estads.get(i).getVictorias())));
				jugador.appendChild(victorias);

				Element intentos = doc.createElement("intentos");
				intentos.appendChild(doc.createTextNode(String.valueOf(estads.get(i).getIntentos())));
				jugador.appendChild(intentos);

				Element mejorTiempo = doc.createElement("mejorTiempo");
				mejorTiempo.appendChild(doc.createTextNode(String.valueOf(estads.get(i).getMejorTiempo())));
				jugador.appendChild(mejorTiempo);

				jugadores.appendChild(jugador);

			}

			doc.appendChild(jugadores);

			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			DOMSource source = new DOMSource(doc);

			File f=new File(juego+".xml");
			if(!f.exists()) {
				f.createNewFile();
			}

			StreamResult result = new StreamResult(f);
			t.transform(source, result);
		} catch (IOException | ParserConfigurationException | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private List<Jugador> enviarEstadisticas(String juego) {
		try {
			File f = new File(juego + ".xml");
			if(f.length()==0) {
				return null;
			}
			else {
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document doc = db.parse(f);

				List<Jugador> lista = new ArrayList<>();

				// Elemento <jugadores>
				Element raiz = doc.getDocumentElement();
				NodeList p = raiz.getElementsByTagName("jugador");
				for (int i = 0; i < p.getLength(); i++) {
					Jugador j = new Jugador();
					j.setNombre(((Element) p.item(i)).getElementsByTagName("nombre").item(0).getTextContent());
					j.setVictorias(Integer.parseInt(((Element) p.item(i)).getElementsByTagName("victorias").item(0).getTextContent()));
					j.setIntentos(Integer.parseInt(((Element) p.item(i)).getElementsByTagName("intentos").item(0).getTextContent()));
					j.setMejorTiempo(Integer.parseInt(((Element) p.item(i)).getElementsByTagName("mejorTiempo").item(0).getTextContent()));
					lista.add(j);
				}
				return lista;
			}
		} catch (ParserConfigurationException | SAXException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DOMException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return null;
	}

	private void heridosYMuertos() {
		try {
			String leido = is.readLine();
			int n = Integer.parseInt(leido);
			int adivina = aleatorioNoRep4();

			int[] adivinaArray = new int[4];
			adivinaArray[3] = adivina % 10;
			adivinaArray[2] = (adivina / 10) % 10;
			adivinaArray[1] = (adivina / 100) % 10;
			adivinaArray[0] = (adivina / 1000) % 10;

			while (n != adivina) {
				int heridos = 0;
				int muertos = 0;

				int[] nArray = new int[4];
				nArray[3] = n % 10;
				nArray[2] = (n / 10) % 10;
				nArray[1] = (n / 100) % 10;
				nArray[0] = (n / 1000) % 10;

				for (int i = 0; i < 4; i++) {
					if (nArray[i] == adivinaArray[i]) {
						muertos++;
					} else {
						for (int j = 0; j < 4; j++) {
							if (nArray[i] == adivinaArray[j]) {
								heridos++;
							}
						}
					}
				}
				os.writeBytes("Resultado: " + heridos + " heridos y " + muertos + " muertos\n");
				os.flush();
				leido = is.readLine();
				n = Integer.parseInt(leido);
			}
			os.writeBytes("Adivinado\n");
			os.flush();
		} catch (NumberFormatException | IOException e) {
			try {
				os.writeBytes("Error\n");
				os.flush();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	public static int aleatorioNoRep4() {
		int primero = (int) (Math.random() * 10); // Num entre 0-9
		int[] numero = new int[4];
		int i = 0;

		numero[0] = primero;

		int segundo = (int) (Math.random() * 10);

		while (i == 0) {
			if (segundo != primero) {
				numero[1] = segundo;
				i = 1;
			} else {
				segundo = (int) (Math.random() * 10);
			}
		}

		i = 0;
		int tercero = (int) (Math.random() * 10);

		while (i == 0) {
			if (tercero != primero && tercero != segundo) {
				numero[2] = tercero;
				i = 1;
			} else {
				tercero = (int) (Math.random() * 10);
			}
		}

		i = 0;
		int cuarto = (int) (Math.random() * 10);

		while (i == 0) {
			if (cuarto != primero && cuarto != segundo && cuarto != tercero) {
				numero[3] = cuarto;
				i = 1;
			} else {
				cuarto = (int) (Math.random() * 10);
			}
		}

		return (numero[0] * 1000 + numero[1] * 100 + numero[2] * 10 + numero[3]);
	}

	private void adivinarNum() {
		try {
			String leido = is.readLine();
			int n = Integer.parseInt(leido);
			int adivina = (int) (Math.random() * 100 + 1);

			while (n != adivina) {
				if (n > adivina) {
					os.writeBytes("Te has pasado\n");
				} else {
					os.writeBytes("Te has quedado corto\n");
				}
				os.flush();
				leido = is.readLine();
				n = Integer.parseInt(leido);
			}
			os.writeBytes("Adivinado\n");
			os.flush();
		} catch (NumberFormatException e) {
			try {
				os.writeBytes("Error\n");
				os.flush();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void gestionarUsuarios() {
		try {
			String opcion = is.readLine();
			if (opcion.equals("Borrar usuario")) {
				boolean borrado = borrarUsuario(is.readLine());
				if (borrado) {
					os.writeBytes("Borrado correcto\n");
					os.flush();
				}
			} else if (opcion.equals("Crear usuario")) {
				boolean creado = crearUsuario(is.readLine());
				if (creado) {
					os.writeBytes("Creado correcto\n");
					os.flush();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void fichero() {
		try {
			String nom = is.readLine();
			String[] separado = nom.split("\\.");
			String extension = separado[separado.length - 1];
			File f = new File("./" + usuario + "/" + nomFich.split(".xml")[0] + "Imagen." + extension);
			try (FileOutputStream fout = new FileOutputStream(f)) {
				long tam = is.readLong();
				byte[] buf = new byte[1024];
				int leido = is.read(buf);
				int suma = 0;
				while (suma < tam) {
					fout.write(buf, 0, leido);
					suma = suma + leido;
					if (suma < tam) {
						leido = is.read(buf);
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	private void inicioSesion() {
		try {
			String usuario = is.readLine();
			boolean existeUsuario = comprobarUsuario(usuario);
			if (existeUsuario) {
				this.usuario = usuario;
				os.writeBytes("Inicio sesion correcto\n");
				os.flush();
			} else {
				os.writeBytes("Error usuario\n");
				os.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String[] devolverNomExamenes(String e) {
		File carpeta = new File(e);
		File[] ficheros = carpeta.listFiles();
		if(ficheros==null) {
			return null;
		}
		String[] aux = new String[ficheros.length];
		int num = 0;
		for (int i = 0, n = ficheros.length; i < n; i++) {
			if (ficheros[i].getName().contains("Resuelto")) {
				aux[i] = ficheros[i].getName();
				num++;
			}
		}
		String[] examenes = new String[num];
		if (num != 0) {
			int j = 0;
			for (int i = 0, n = ficheros.length; i < n; i++) {
				if (aux[i] != null) {
					examenes[j] = aux[i];
					j++;
				}
			}
		}
		return examenes;
	}

	private String[] devolverNomAlumnos() {
		File f = new File("Usuarios.txt");
		String[] aux = new String[(int) f.length()];
		int num = 0;
		try (DataInputStream fin = new DataInputStream(new FileInputStream(f))) {
			String leido = fin.readLine();
			while (leido != null) {
				aux[num] = leido;
				num++;
				leido = fin.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String[] nomAlum = new String[num];
		if (num != 0) {
			int j = 0;
			for (int i = 0, n = (int) f.length(); i < n; i++) {
				if (aux[i] != null) {
					nomAlum[j] = aux[i];
					j++;
				}
			}
		}
		return nomAlum;
	}

	private void recibirExamen(String nomFich, String usuario2, boolean nuevo) {
		try {
			Examen ex = (Examen) is.readObject();

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();

			Element examen = doc.createElement("examen");

			Element tema = doc.createElement("tema");
			tema.appendChild(doc.createTextNode(ex.getTema()));
			examen.appendChild(tema);

			Element fecha = doc.createElement("fecha");
			fecha.appendChild(doc.createTextNode(ex.getFecha().toString()));
			examen.appendChild(fecha);

			Element preguntas = doc.createElement("preguntas");
			List<Pregunta> preg = ex.getPreguntas();

			if (nuevo) {

				for (int i = 0, n = preg.size(); i < n; i++) {
					Element pregunta = doc.createElement("pregunta");
					pregunta.setAttribute("correcta", String.valueOf(preg.get(i).getCorrecta()));

					Element enunciado = doc.createElement("enunciado");
					enunciado.appendChild(doc.createTextNode(preg.get(i).getEnunciado()));
					pregunta.appendChild(enunciado);

					List<String> respuestas = preg.get(i).getRespuestas();
					for (int j = 0, m = respuestas.size(); j < m; j++) {
						Element respuesta = doc.createElement("respuesta");
						respuesta.appendChild(doc.createTextNode(respuestas.get(j)));
						pregunta.appendChild(respuesta);
					}
					preguntas.appendChild(pregunta);
				}

				examen.appendChild(preguntas);

			} else {
				Element numCorrectas = doc.createElement("numCorrectas");
				numCorrectas.appendChild(doc.createTextNode(String.valueOf(ex.getNumCorrectas())));
				examen.appendChild(numCorrectas);

				for (int i = 0, n = preg.size(); i < n; i++) {
					Element pregunta = doc.createElement("pregunta");
					pregunta.setAttribute("correcta", String.valueOf(preg.get(i).getCorrecta()));
					pregunta.setAttribute("contestada", String.valueOf(preg.get(i).getContestada()));

					Element enunciado = doc.createElement("enunciado");
					enunciado.appendChild(doc.createTextNode(preg.get(i).getEnunciado()));
					pregunta.appendChild(enunciado);

					List<String> respuestas = preg.get(i).getRespuestas();
					for (int j = 0, m = respuestas.size(); j < m; j++) {
						Element respuesta = doc.createElement("respuesta");
						respuesta.appendChild(doc.createTextNode(respuestas.get(j)));
						pregunta.appendChild(respuesta);
					}
					preguntas.appendChild(pregunta);
				}

				examen.appendChild(preguntas);
			}

			doc.appendChild(examen);

			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			DOMSource source = new DOMSource(doc);

			File f;
			if (nuevo) {
				f = new File("./" + usuario2 + "/" + nomFich + ".xml");
				if (!f.exists()) {
					f.createNewFile();
				}
			} else {
				f = new File("./" + usuario2 + "/" + nomFich.split(".xml")[0] + "Resuelto.xml");
				if (!f.exists()) {
					f.createNewFile();
				}
				File antiguo = new File("./" + usuario2 + "/" + nomFich);
				antiguo.delete();
			}

			StreamResult result = new StreamResult(f);
			t.transform(source, result);
		} catch (ClassNotFoundException | IOException | ParserConfigurationException | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Examen devolverExam(String usuario2, String e) {
		try {
			File exam = new File(usuario2 + "/" + e);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(exam);

			Examen examen = new Examen();
			ArrayList<Pregunta> preguntas = new ArrayList<>();
			// Elemento <examen>
			Element raiz = doc.getDocumentElement();
			// DateFormat format=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
			// examen.setFecha(format.parse(raiz.getElementsByTagName("fecha").item(0).getTextContent()));
			// System.out.println(examen.getFecha().toString());
			examen.setTema(raiz.getElementsByTagName("tema").item(0).getTextContent());
			NodeList p = raiz.getElementsByTagName("pregunta");
			for (int i = 0; i < p.getLength(); i++) {
				Pregunta preg = new Pregunta();
				preg.setEnunciado(((Element) p.item(i)).getElementsByTagName("enunciado").item(0).getTextContent());
				NodeList resp = ((Element) p.item(i)).getElementsByTagName("respuesta");
				ArrayList<String> respuestas = new ArrayList<>();
				for (int j = 0; j < resp.getLength(); j++) {
					respuestas.add(resp.item(j).getTextContent());
				}
				preg.setRespuestas(respuestas);
				preg.setCorrecta(Integer.parseInt(((Element) p.item(i)).getAttribute("correcta")));
				String contestada = (((Element) p.item(i)).getAttribute(("contestada")));
				if (!contestada.equals("")) {
					preg.setContestada(Integer.parseInt(contestada));
				}
				preguntas.add(preg);
			}
			examen.setPreguntas(preguntas);
			return examen;
		} catch (ParserConfigurationException | SAXException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DOMException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return null;
	}

	private String[] devolverNomExam(String usuario2) {
		File carpeta = new File(usuario2);
		File[] ficheros = carpeta.listFiles();
		String[] aux = new String[ficheros.length];
		int num = 0;
		for (int i = 0, n = ficheros.length; i < n; i++) {
			if (!ficheros[i].getName().contains("Resuelto") && !ficheros[i].getName().contains("Imagen")) {
				aux[i] = ficheros[i].getName();
				num++;
			}
		}

		String[] examenes = new String[num];
		if (num != 0) {
			int j = 0;
			for (int i = 0, n = (int) ficheros.length; i < n; i++) {
				if (aux[i] != null) {
					examenes[j] = aux[i];
					j++;
				}
			}
		}

		return examenes;
	}

	public boolean comprobarUsuario(String u) {
		if (u.equals("marojamaProfe") || u.equals("invitado")) {
			return true;
		} else {
			try (BufferedReader bfich = new BufferedReader(new FileReader("Usuarios.txt"));) {
				String leido = bfich.readLine();
				while (leido != null) {
					if (leido.equals(u)) {
						return true;
					}
					leido = bfich.readLine();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean borrarUsuario(String u) {
		File original, temporal;
		boolean borrado = false;

		try (BufferedReader bfich = new BufferedReader(new FileReader("Usuarios.txt"));
				BufferedWriter btemp = new BufferedWriter(new FileWriter("copia.tmp"))) {
			String leido = bfich.readLine();
			while (leido != null) {
				if (!leido.equals(u)) {
					btemp.write(leido);
					btemp.newLine();
				} else {
					borrado = true;
					borrarCarpetaExamenes(u);
				}
				leido = bfich.readLine();
			}
			bfich.close();
			btemp.close();
			original = new File("Usuarios.txt");
			original.delete();
			temporal = new File("copia.tmp");
			temporal.renameTo(original);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return borrado;
	}

	public void borrarCarpetaExamenes(String u) {
		File carpeta = new File(u);
		boolean borrado = carpeta.delete();
		// Tiene examenes dentro, no se puede borrar
		if (!borrado) {
			for (File f : carpeta.listFiles()) {
				f.delete();
			}
			carpeta.delete();
		}
	}

	public boolean crearUsuario(String u) {
		boolean creado = false;

		try (BufferedWriter bfich = new BufferedWriter(new FileWriter("Usuarios.txt", true))) {
			bfich.write(u);
			bfich.newLine();
			File carpetaExamenes = new File(u);
			carpetaExamenes.mkdir();
			creado = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return creado;
	}
}
