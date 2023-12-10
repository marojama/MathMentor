// Clase AtenderPeticion (Hilo que atenderá a un cliente)
// Aplicación: MathMentor
// Autor: Marta Rojas

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

			// Creamos objectInput y output para mandar directamente los objetos examen
			// Y que sea el servidor el que se ocupe de pasar al DOM, ya que, como el
			// principal tiene que ejecutar toda la interfaz gráfica, el servidor tiene
			// menor carga de trabajo

			// Además, los creamos como atributos, para poder hacer que el hilo sea
			// programado por procedimientos (métodos)
			os = new ObjectOutputStream(s.getOutputStream());
			is = new ObjectInputStream(s.getInputStream());
			this.usuario = "";
			// Almacen de usuarios
			File f = new File("Usuarios.txt");
			if (!f.exists()) {
				try {
					f.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			// Estadisticas juegos
			File j1 = new File("AdivinarNum.xml");
			if (!j1.exists()) {
				try {
					j1.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			File j2 = new File("HeridosYMuertos.xml");
			if (!j2.exists()) {
				try {
					j2.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * El método run del hilo
	 */
	public void run() {
		try {
			// Con opción, el principal nos dice qué quiere ejecutar
			String opcion = is.readLine();

			// Mientras que los socket sigan abiertos
			while (opcion != null) {
				// Comprobar si un usuario existe
				if (opcion.equals("Iniciar sesion")) {
					inicioSesion();
				}
				// Añadir/Borrar usuarios
				else if (opcion.equals("Gestionar usuarios")) {
					gestionarUsuarios();
				}
				// Nombre examenes a realizar para el alumno
				else if (opcion.equals("Examenes")) {
					String[] nomExam = devolverNomExam();
					os.writeObject(nomExam);
					os.flush();
				}
				// Nombre examenes realizados por un alumno
				else if (opcion.equals("Nombres examenes")) {
					String e = is.readLine();
					String[] nomExam = devolverNomExamenes(e);
					os.writeObject(nomExam);
					os.flush();
				}
				// Examen a realizar por un alumno concreto
				else if (opcion.equals("Examen")) {
					String e = is.readLine();
					Examen examen = devolverExam(this.usuario, e);
					os.writeObject(examen);
					os.flush();
				}
				// Examen a corregir de un alumno concreto y posible imagen mandada
				else if (opcion.equals("Examen Alumno")) {
					String exam = is.readLine();
					String user = is.readLine();
					Examen examen = devolverExam(user, exam);
					ImageIcon imag = devolverImagen(user, exam);
					os.writeObject(examen);
					os.writeObject(imag);
					os.flush();
				}
				// Recibir un examen hecho por un alumno
				else if (opcion.equals("Examen hecho")) {
					this.nomFich = is.readLine();
					String usuario = is.readLine();
					recibirExamen(nomFich, usuario, false);
				}
				// Guardar un examen nuevo para un alumno
				else if (opcion.equals("Examen nuevo")) {
					this.nomFich = is.readLine();
					String usuario = is.readLine();
					recibirExamen(nomFich, usuario, true);
				}
				// Recibir un fichero del Principal
				else if (opcion.equals("Fichero")) {
					fichero();
				}
				// Enviar nombre de todos los alumnos
				else if (opcion.equals("Alumnos")) {
					String[] nomAlum = devolverNomAlumnos();
					os.writeObject(nomAlum);
					os.flush();
				}
				// Juego Adivinar número
				else if (opcion.equals("AdivinarNum")) {
					adivinarNum();
				}
				// Juego Heridos y Muertos
				else if (opcion.equals("HeridosYMuertos")) {
					heridosYMuertos();
				}
				// Recibir estadísticas nuevas
				else if (opcion.equals("Estadisticas")) {
					String juego = is.readLine();
					List<Jugador> lista = enviarEstadisticas(juego);
					os.writeObject(lista);
					os.flush();
				}
				// Enviar estadisticas de un juego concreto
				else if (opcion.equals("Enviar estadisticas")) {
					String juego = is.readLine();
					String usuario = is.readLine();
					int intentos = is.readInt();
					long tiempo = is.readLong();
					guardarEstadisticas(juego, usuario, intentos, tiempo);
				}
				// Volver a leer opcion
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

	/**
	 * Método para enviar una (posible) imagen enviada por el alumno
	 * 
	 * @param user el alumno
	 * @param exam el examen realizado
	 * @return un ImageIcon con la imagen, o null en caso contrario
	 */
	private ImageIcon devolverImagen(String user, String exam) {
		// directorio donde se almacena la informacion de un usuario
		File dir = new File(user);
		File imagen = null;
		ImageIcon i = null;
		for (File f : dir.listFiles()) {
			// Si el fichero se llama como el examen seguido de Imagen, es el archivo que
			// buscabamos
			if (f.getName().startsWith(exam.split("Resuelto")[0]) && f.getName().contains("Imagen")) {
				imagen = f;
				try {
					// Guardamos los bytes de la imagen para pasarselo al principal
					i = new ImageIcon(Files.readAllBytes(imagen.toPath()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return i;
	}

	/**
	 * Método para almacenar nuevas estadisticas para un jugador concreto
	 * 
	 * @param juego    el juego para el que se han conseguido las estadisticas
	 * @param usuario2 el nombre del usuario
	 * @param intentos el numero de intentos empleados
	 * @param tiempo   el tiempo empleado
	 */
	private void guardarEstadisticas(String juego, String usuario2, int intentos, long tiempo) {
		// Cogemos las estadisticas ya almacenadas
		List<Jugador> estads = enviarEstadisticas(juego);

		// Si aun no hay estadisticas, añadimos la que teniamos como nueva
		if (estads == null) {
			// Un 1 en victorias, ya que seria la primera
			Jugador j = new Jugador(usuario2, 1, intentos, tiempo);
			estads = new ArrayList<>();
			estads.add(j);
		}
		// Si ya hay estadisticas almacenadas, comprobamos si el usuario ya estaba
		// registrado. Si lo estaba, tenemos que añadirle una victoria mas, y guardar el
		// mejor tiempo y el mejor numero de intentos entre lo almacenado y lo nuevo
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
					int v = jugador.getVictorias();
					jugador.setVictorias(++v);
				}
			}
			// Si no estaba, añadimos las nuevas estadisticas, con victoria 1, la primera
			if (!esta) {
				Jugador j = new Jugador(usuario2, 1, intentos, tiempo);
				estads.add(j);
			}
		}
		// Guardamos las nuevas estadisticas
		incluirEstads(juego, estads);
	}

	/**
	 * Método que guarda en un xml las estadisticas almacenadas
	 * 
	 * @param juego  el juego al que le pertenecen las estadisticas/ nombre del
	 *               archivo
	 * @param estads la lista con las estadisticas que queremos almacenar
	 */
	private void incluirEstads(String juego, List<Jugador> estads) {
		try {
			// Nuevo arbol
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();

			// <jugadores>
			Element jugadores = doc.createElement("jugadores");

			// Recorremos la lista añadiendo todos los jugadores
			for (int i = 0, n = estads.size(); i < n; i++) {
				// Creamos un elemento jugador para cada uno
				Element jugador = doc.createElement("jugador");

				// Guardamos el nombre
				Element nombre = doc.createElement("nombre");
				nombre.appendChild(doc.createTextNode(estads.get(i).getNombre()));
				jugador.appendChild(nombre);

				// Guardamos las victorias
				Element victorias = doc.createElement("victorias");
				victorias.appendChild(doc.createTextNode(String.valueOf(estads.get(i).getVictorias())));
				jugador.appendChild(victorias);

				// Guardamos los intentos
				Element intentos = doc.createElement("intentos");
				intentos.appendChild(doc.createTextNode(String.valueOf(estads.get(i).getIntentos())));
				jugador.appendChild(intentos);

				// Guardamos el mejorTiempo
				Element mejorTiempo = doc.createElement("mejorTiempo");
				mejorTiempo.appendChild(doc.createTextNode(String.valueOf(estads.get(i).getMejorTiempo())));
				jugador.appendChild(mejorTiempo);

				// Anidamos
				jugadores.appendChild(jugador);

			}

			// Anidamos en el DOM
			doc.appendChild(jugadores);

			// Lo pasamos a DOM
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			DOMSource source = new DOMSource(doc);

			// Creamos el archivo por si no existe
			File f = new File(juego + ".xml");
			if (!f.exists()) {
				f.createNewFile();
			}

			// Metemos el DOM en el fichero
			StreamResult result = new StreamResult(f);
			t.transform(source, result);
		} catch (IOException | ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que lee las estadisticas almacenadas y las devuelve como una lista de
	 * jugadores
	 * 
	 * @param juego el juego concreto del que queremos conocer las estadisticas
	 * @return la lista de las estadisticas o null en caso de no haber
	 */
	private List<Jugador> enviarEstadisticas(String juego) {
		try {
			File f = new File(juego + ".xml");
			// Si aun no hay estadisticas devolvemos null
			if (f.length() == 0) {
				return null;
			} else {
				// Recuperamos el DOM
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document doc = db.parse(f);

				List<Jugador> lista = new ArrayList<>();

				// Elemento <jugadores>
				Element raiz = doc.getDocumentElement();
				NodeList p = raiz.getElementsByTagName("jugador");
				// Creamos un objeto jugador para cada uno
				for (int i = 0; i < p.getLength(); i++) {
					Jugador j = new Jugador();
					j.setNombre(((Element) p.item(i)).getElementsByTagName("nombre").item(0).getTextContent());
					j.setVictorias(Integer.parseInt(
							((Element) p.item(i)).getElementsByTagName("victorias").item(0).getTextContent()));
					j.setIntentos(Integer
							.parseInt(((Element) p.item(i)).getElementsByTagName("intentos").item(0).getTextContent()));
					j.setMejorTiempo(Integer.parseInt(
							((Element) p.item(i)).getElementsByTagName("mejorTiempo").item(0).getTextContent()));
					// Los añadimos a la lista
					lista.add(j);
				}
				return lista;
			}
		} catch (ParserConfigurationException | SAXException | IOException | DOMException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	/**
	 * Método para jugar al juego de Heridos y Muertos. El servidor piensa en un
	 * número de 4 cifras no repetidas. El cliente va probando a intentar adivinar
	 * el número, sabiendo que, si en el número que prueba una cifra está en el
	 * número del servidor en la misma posición, el servidor dirá que hay un muerto.
	 * Si una cifra está en el número pero en distinta posición, el servidor dirá
	 * que está herido.
	 */
	private void heridosYMuertos() {
		try {
			String leido = is.readLine();
			// Si no es un número lo que ha introducido el usuario, devolverá error
			int n = Integer.parseInt(leido);
			// Número de 4 cifras sin cifras repetidas
			int adivina = aleatorioNoRep4();

			// Guardamos las cifras en un array
			int[] adivinaArray = new int[4];
			adivinaArray[3] = adivina % 10;
			adivinaArray[2] = (adivina / 10) % 10;
			adivinaArray[1] = (adivina / 100) % 10;
			adivinaArray[0] = (adivina / 1000) % 10;

			// Mientras que no adivine el numero
			while (n != adivina) {
				int heridos = 0;
				int muertos = 0;

				// Guardamos el numero del usuario en un array
				int[] nArray = new int[4];
				nArray[3] = n % 10;
				nArray[2] = (n / 10) % 10;
				nArray[1] = (n / 100) % 10;
				nArray[0] = (n / 1000) % 10;

				// Comprobamos si las cifras coinciden con las del usuario
				for (int i = 0; i < 4; i++) {
					// Misma posición
					if (nArray[i] == adivinaArray[i]) {
						muertos++;
					} else {
						for (int j = 0; j < 4; j++) {
							// Distinta posición
							if (nArray[i] == adivinaArray[j]) {
								heridos++;
							}
						}
					}
				}
				// Devolvemos el resultado
				os.writeBytes("Resultado: " + heridos + " heridos y " + muertos + " muertos\n");
				os.flush();
				// Esperamos nuevo intento
				leido = is.readLine();
				n = Integer.parseInt(leido);
			}
			// Devolvemos adivinado cuando gane
			os.writeBytes("Adivinado\n");
			os.flush();
		}
		// Error de lectura
		catch (NumberFormatException | IOException e) {
			try {
				os.writeBytes("Error\n");
				os.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Número de 4 cifras aleatorio sin repetidos
	 * 
	 * @return número de 4 cifras aleatorio sin repetidas
	 */
	private static int aleatorioNoRep4() {
		// Número entre 0-9
		int primero = (int) (Math.random() * 10);
		int[] numero = new int[4];
		int i = 0;

		// El primero seguro que no es repetido
		numero[0] = primero;

		// Buscamos el segundo
		int segundo = (int) (Math.random() * 10);

		// Mientras que el segundo coincida con el primero, buscamos otro
		while (i == 0) {
			if (segundo != primero) {
				numero[1] = segundo;
				i = 1;
			} else {
				segundo = (int) (Math.random() * 10);
			}
		}

		i = 0;
		// Buscamos el tercero
		int tercero = (int) (Math.random() * 10);

		// Mientras que el tercero coincida con el primero o el segundo, buscamos otro
		while (i == 0) {
			if (tercero != primero && tercero != segundo) {
				numero[2] = tercero;
				i = 1;
			} else {
				tercero = (int) (Math.random() * 10);
			}
		}

		i = 0;
		// Buscamos el cuarto
		int cuarto = (int) (Math.random() * 10);

		// Mientras que el cuarto coincida con alguno de los anteriores, buscamos otro
		while (i == 0) {
			if (cuarto != primero && cuarto != segundo && cuarto != tercero) {
				numero[3] = cuarto;
				i = 1;
			} else {
				cuarto = (int) (Math.random() * 10);
			}
		}

		// Devolvemos el número
		return (numero[0] * 1000 + numero[1] * 100 + numero[2] * 10 + numero[3]);
	}

	/**
	 * Método para jugar al juego de adivinar un número. El servidor piensa en un
	 * número entre 1 y 100. El usuario manda un número con el que intenta
	 * adivinarlo. Si el número del usuario es mayor, el servidor le dirá que se ha
	 * pasado. En cambio, si el número del usuario es menor, el servidor le dirá que
	 * se ha quedado corto.
	 */
	private void adivinarNum() {
		try {
			String leido = is.readLine();
			// Si nos manda algo que no sea un entero, le mandamos error y se acaba el juego
			int n = Integer.parseInt(leido);
			// Número entre 1-100
			int adivina = (int) (Math.random() * 100 + 1);

			// Mientras que no adivine, seguimos jugando
			while (n != adivina) {
				// Si lo leido es mayor que el número pensado
				if (n > adivina) {
					os.writeBytes("Te has pasado\n");
				}
				// Si lo leido es menor que el numero pensado
				else {
					os.writeBytes("Te has quedado corto\n");
				}
				os.flush();
				// Volvemos a esperar un número del cliente
				leido = is.readLine();
				n = Integer.parseInt(leido);
			}
			os.writeBytes("Adivinado\n");
			os.flush();
		}
		// En caso de error, se acaba el juego
		catch (NumberFormatException e) {
			try {
				os.writeBytes("Error\n");
				os.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para la gestión de los usuarios. Permite añadir y borrar nuevos
	 * usuarios
	 */
	private void gestionarUsuarios() {
		try {
			String opcion = is.readLine();
			// El usuario quiere borrar un usuario
			if (opcion.equals("Borrar usuario")) {
				boolean borrado = borrarUsuario(is.readLine());
				if (borrado) {
					os.writeBytes("Borrado correcto\n");
					os.flush();
				}
			}
			// El usuario quiere añadir un usuario
			else if (opcion.equals("Crear usuario")) {
				boolean creado = crearUsuario(is.readLine());
				if (creado) {
					os.writeBytes("Creado correcto\n");
					os.flush();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para borrar un usuario de lo almacenado
	 * 
	 * @param u usuario a borrar
	 * @return true si se borró correctamente, false en caso contrario
	 */
	public boolean borrarUsuario(String u) {
		File original, temporal;
		boolean borrado = false;

		// Recorremos el fichero de usuarios y vamos creando uno nuevo con los usuarios
		// sin el usuario a borrar
		try (BufferedReader bfich = new BufferedReader(new FileReader("Usuarios.txt"));
				BufferedWriter btemp = new BufferedWriter(new FileWriter("copia.tmp"))) {
			String leido = bfich.readLine();
			while (leido != null) {
				// Si no es el usuario a borrar, lo copiamos en el temporal
				if (!leido.equals(u)) {
					btemp.write(leido + "\n");
				}
				// Cuando lo encontremos, ese no lo escribimos en el temporal, y borramos su
				// carpeta
				else {
					borrado = true;
					borrarCarpetaExamenes(u);
				}
				leido = bfich.readLine();
			}
			// Cerramos los dos ficheros
			bfich.close();
			btemp.close();
			// Borramos el antiguo
			original = new File("Usuarios.txt");
			original.delete();
			// Renombramos el nuevo como el anterior
			temporal = new File("copia.tmp");
			temporal.renameTo(original);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return borrado;
	}

	/**
	 * Método para borrar la carpeta de un alumno. Posiblemente no vacía
	 * 
	 * @param u usuario a borrar
	 */
	public void borrarCarpetaExamenes(String u) {
		File carpeta = new File(u);
		boolean borrado = carpeta.delete();
		// Tiene examenes dentro, no se puede borrar directamente
		if (!borrado) {
			for (File f : carpeta.listFiles()) {
				f.delete();
			}
			carpeta.delete();
		}
	}

	/**
	 * Método para añadir un nuevo usuario y crear la carpeta donde se almacenarán
	 * sus examenes
	 * 
	 * @param u nuevo usuario
	 * @return true en caso de añadirse correctamente y false en caso contrario
	 */
	public boolean crearUsuario(String u) {
		boolean creado = false;

		// Le ponemos true para poder añadir el usuario al final del fichero y no
		// reescribirlo entero
		try (BufferedWriter bfich = new BufferedWriter(new FileWriter("Usuarios.txt", true))) {
			bfich.write(u);
			bfich.newLine();
			// Creamos la carpeta donde añadiremos los examenes del usuario
			File carpetaExamenes = new File(u);
			carpetaExamenes.mkdir();
			creado = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return creado;
	}

	/**
	 * Método para guardar un fichero binario en la carpeta del usuario
	 */
	private void fichero() {
		try {
			// Nombre del fichero
			String nom = is.readLine();
			// Separamos por los puntos
			String[] separado = nom.split("\\.");
			// Guardamos la extension del fichero
			String extension = separado[separado.length - 1];
			// Lo guardamos en la carpeta del usuario, con el nombre del fichero del examen
			// seguido de Imagen y con la extensión original
			File f = new File("./" + usuario + "/" + nomFich.split(".xml")[0] + "Imagen." + extension);
			try (FileOutputStream fout = new FileOutputStream(f)) {
				// Tamaño del fichero para saber cuanto leer
				long tam = is.readLong();
				// Lectura por bloques
				byte[] buf = new byte[1024];
				int leido = is.read(buf);
				int suma = 0;
				// Leemos hasta haber leido tanto como el tamaño que nos pasaron
				while (suma < tam) {
					fout.write(buf, 0, leido);
					suma = suma + leido;
					if (suma < tam) {
						leido = is.read(buf);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Método para comprobar si un usuario existe o no. Escribe "Inicio sesion
	 * correcto" si existe y "Error usuario" en otro caso
	 */
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
			e.printStackTrace();
		}
	}

	/**
	 * Método para devolver los nombres de los examenes resueltos de un usuario
	 * 
	 * @param e el nombre del usuario
	 * @return una lista con los nombres de los examenes o null en caso de error o
	 *         no haber
	 */
	private String[] devolverNomExamenes(String e) {
		File carpeta = new File(e);
		File[] ficheros = carpeta.listFiles();
		// Si no tiene examenes, devolvemos null
		if (ficheros == null) {
			return null;
		}
		String[] aux = new String[ficheros.length];
		int num = 0;
		// Guardamos los examenes resueltos
		for (int i = 0, n = ficheros.length; i < n; i++) {
			if (ficheros[i].getName().contains("Resuelto")) {
				aux[i] = ficheros[i].getName();
				num++;
			}
		}
		// Volvemos a guardar, pero en una lista del tamaño correcto
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

	/**
	 * Método para devolver el nombre de los alumnos almacenador
	 * 
	 * @return lista con los alumnos o null en caso de error o no haber
	 */
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
			e.printStackTrace();
		}

		// Reescribimos con una lista del tamaño correcto
		String[] nomAlum = null;
		if (num != 0) {
			nomAlum = new String[num];
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

	/**
	 * Método para recibir un examen y almacenarlo
	 * 
	 * @param nomFich  nombre con el que se guardará
	 * @param usuario2 usuario al que le corresponde el examen
	 * @param nuevo    true si es un examen nuevo y false en caso de ser un examen
	 *                 ya realizado
	 */
	private void recibirExamen(String nomFich, String usuario2, boolean nuevo) {
		try {
			// Leemos el examen
			Examen ex = (Examen) is.readObject();

			// DOM
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();

			// <examen>
			Element examen = doc.createElement("examen");

			// <tema>
			Element tema = doc.createElement("tema");
			tema.appendChild(doc.createTextNode(ex.getTema()));
			examen.appendChild(tema);

			// Wrapper con el resto de preguntas
			Element preguntas = doc.createElement("preguntas");
			List<Pregunta> preg = ex.getPreguntas();

			// Si es un examen nuevo, no guardamos ni la contestada ni el numero de
			// correctas
			if (nuevo) {
				// Para cada pregunta, guardamos el enunciado, la correcta y las respuestas
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
			}
			// Si el examen ya se ha realizado, tenemos que guardar la contestada y el
			// numero de correctas
			else {
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

			// DOM
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			DOMSource source = new DOMSource(doc);

			File f;
			// Si es nuevo, lo guardamos con el nombre pasado en la carpeta del alumno
			if (nuevo) {
				f = new File("./" + usuario2 + "/" + nomFich + ".xml");
				if (!f.exists()) {
					f.createNewFile();
				}
			}
			// Si ya esta relleno, lo guardamos con nombre Resuelto
			else {
				f = new File("./" + usuario2 + "/" + nomFich.split(".xml")[0] + "Resuelto.xml");
				if (!f.exists()) {
					f.createNewFile();
				}
				// Borramos el ya realizado, para que no se pueda volver a realizar y trampear
				// los resultados
				File antiguo = new File("./" + usuario2 + "/" + nomFich);
				antiguo.delete();
			}

			// Guardamos el xml
			StreamResult result = new StreamResult(f);
			t.transform(source, result);
		} catch (ClassNotFoundException | IOException | ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para devolver un examen a partir del usuario y el nombre del examen
	 * 
	 * @param usuario2 usuario al que le corresponde el examen
	 * @param e        nombre del examen
	 * @return el objeto examen
	 */
	private Examen devolverExam(String usuario2, String e) {
		try {
			File exam = new File(usuario2 + "/" + e);

			// DOM
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(exam);

			Examen examen = new Examen();
			ArrayList<Pregunta> preguntas = new ArrayList<>();

			// Elemento <examen>
			Element raiz = doc.getDocumentElement();
			// Elemento <tema>
			examen.setTema(raiz.getElementsByTagName("tema").item(0).getTextContent());
			// Si es un examen resuelto, añadimos el numero de correctas
			if (e.contains("Resuelto")) {
				examen.setNumCorrectas(
						Integer.parseInt(raiz.getElementsByTagName("numCorrectas").item(0).getTextContent()));
			}
			// Recorremos los nodos pregunta
			NodeList p = raiz.getElementsByTagName("pregunta");
			for (int i = 0; i < p.getLength(); i++) {
				Pregunta preg = new Pregunta();
				// Guardamos el enunciado
				preg.setEnunciado(((Element) p.item(i)).getElementsByTagName("enunciado").item(0).getTextContent());
				// Recorremos las respuestas
				NodeList resp = ((Element) p.item(i)).getElementsByTagName("respuesta");
				ArrayList<String> respuestas = new ArrayList<>();
				for (int j = 0; j < resp.getLength(); j++) {
					respuestas.add(resp.item(j).getTextContent());
				}
				preg.setRespuestas(respuestas);
				// Guardamos la correcta
				preg.setCorrecta(Integer.parseInt(((Element) p.item(i)).getAttribute("correcta")));
				String contestada = (((Element) p.item(i)).getAttribute(("contestada")));
				// Si existe ese atributo, lo guardamos
				if (!contestada.equals("")) {
					preg.setContestada(Integer.parseInt(contestada));
				}
				preguntas.add(preg);
			}
			examen.setPreguntas(preguntas);
			return examen;
		} catch (ParserConfigurationException | SAXException | IOException | DOMException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	/**
	 * Método para devolver los nombres de los examenes pendientes para un alumno
	 * 
	 * @return la lista con los nombres de los examenes o null en caso de error o
	 *         estar vacia
	 */
	private String[] devolverNomExam() {
		// Carpeta donde se guardan los examenes
		File carpeta = new File(this.usuario);
		File[] ficheros = carpeta.listFiles();
		String[] aux = new String[ficheros.length];
		int num = 0;
		for (int i = 0, n = ficheros.length; i < n; i++) {
			if (!ficheros[i].getName().contains("Resuelto") && !ficheros[i].getName().contains("Imagen")) {
				aux[i] = ficheros[i].getName();
				num++;
			}
		}
		// Guardamos los examenes en una lista del tamaño correcto
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

	/**
	 * Método que comprueba si un usuario puede acceder a la aplicación. Es decir,
	 * si es la profesora, el invitado o un usuario registrado
	 * 
	 * @param u el usuario a comprobar
	 * @return true en caso de ser un usuario valido y false en caso contrario
	 */
	public boolean comprobarUsuario(String u) {
		// Casos especiales. Profesora e invitado
		if (u.equals("Profe") || u.equals("invitado")) {
			return true;
		} 
		// Buscamos en el archivo de usuarios si existe dicho usuario
		else {
			try (BufferedReader bfich = new BufferedReader(new FileReader("Usuarios.txt"));) {
				String leido = bfich.readLine();
				while (leido != null) {
					if (leido.equals(u)) {
						return true;
					}
					leido = bfich.readLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
