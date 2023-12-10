// Clase Principal
// Aplicación: MathMentor
// Autor: Marta Rojas

package principal;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import javax.swing.ImageIcon;

import interfazGrafica.InicioSesion;
import interfazGrafica.PantallaInicial;
import interfazGrafica.PantallaProfesora;
import xml.Examen;
import xml.Jugador;

public class Principal {

	private static Socket s;
	private static ObjectInputStream is;
	private static ObjectOutputStream os;

	public static void main(String[] args) {

		try {
			// Si ejecutamos el servidor en distinto ordenador habría que cambiar el
			// "localhost" por la ip
			s = new Socket("localhost", 55555);

			// Creamos objectInput y output para mandar directamente los objetos examen
			// Y que sea el servidor el que se ocupe de pasar al DOM, ya que, como el
			// principal tiene que ejecutar toda la interfaz gráfica, el servidor tiene
			// menor carga de trabajo

			// Además, los creamos como atributos, para poder hacer que el principal sea
			// programado por procedimientos (métodos)
			is = new ObjectInputStream(s.getInputStream());
			os = new ObjectOutputStream(s.getOutputStream());

			// Llamamos a la interfaz inicial. Desde las interfaces, se llamará a los
			// métodos del Principal, para poder comunicarnos con el Servidor
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						PantallaInicial frame = new PantallaInicial();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Método que pregunta al servidor si un cierto usuario existe
	 * 
	 * @param usuario el usuario a comprobar
	 * @return true si el usuario existe, y false en otro caso
	 */
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
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Método para pedirle al servidor que borre un usuario
	 * 
	 * @param usuario el usuario a borrar
	 * @return true si se ha borrado correctamente, y false en otro caso
	 */
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
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Método para pedirle al servidor que añada un usuario
	 * 
	 * @param usuario a añadir
	 * @return true si se ha añadido correctamente, false en otro caso
	 */
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
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Método para pedirle al servidor el nombre de los examenes No necesita el
	 * usuario, ya que el hilo que le atiende lo almacena
	 * 
	 * @return una lista con el nombre de los examenes pendientes de realizar o null
	 *         en otro caso
	 */
	public static String[] nombreExamenes() {
		try {
			os.writeBytes("Examenes\n");
			os.flush();
			String[] nomExam = (String[]) is.readObject();
			return nomExam;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Método sobrecargado que devuelve la lista de examenes para un alumno dado
	 * para corregirlos
	 * 
	 * @param alumno el alumno al que le corresponden los examenes
	 * @return la lista de los examenes realizados, o null en caso de error o estar
	 *         vacio
	 */
	public static String[] nombresExamenes(String alumno) {
		try {
			os.writeBytes("Nombres examenes\n");
			os.writeBytes(alumno + "\n");
			os.flush();
			String[] nomAlum = (String[]) is.readObject();
			return nomAlum;
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Método para pedir un examen concreto al servidor
	 * 
	 * @param seleccion el nombre del examen a pedir
	 * @return el objeto examen o null en caso de error
	 */
	public static Examen pedirExamen(String seleccion) {
		try {
			os.writeBytes("Examen\n");
			os.writeBytes(seleccion + "\n");
			os.flush();
			return (Examen) is.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Método sobrecargado para pedir un examen de un alumno al servidor
	 * 
	 * @param seleccion el nombre del examen a pedir
	 * @param alumno    el alumno que realizó el examen
	 * @return el objeto examen o null en caso de error
	 */
	public static Examen pedirExamen(String seleccion, String alumno) {
		try {
			os.writeBytes("Examen Alumno\n");
			os.writeBytes(seleccion + "\n");
			os.writeBytes(alumno + "\n");
			os.flush();
			return (Examen) is.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Método para enviar un examen nuevo o resuelto. Es necesario el parámetro
	 * usuario, ya que el método está tambien pensado para que la profesora pueda
	 * mandar nuevos examenes para sus alumnos
	 * 
	 * @param examen     el examen nuevo o resuelto
	 * @param nombreFich el nombre con el que se guardará el examen
	 * @param usuario    el usuario que lo ha realizado, o para el que está pensado
	 */
	public static void enviarExamen(Examen examen, String nombreFich, String usuario) {
		try {
			if (nombreFich.contains(".xml")) {
				os.writeBytes("Examen hecho\n");
			} else {
				os.writeBytes("Examen nuevo\n");
			}
			os.writeBytes(nombreFich + "\n");
			os.writeBytes(usuario + "\n");
			os.writeObject(examen);
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para enviar un archivo (posiblemente imágenes) al servidor
	 * 
	 * @param archivo el archivo a enviar
	 */
	public static void enviarArchivo(File archivo) {
		try (FileInputStream fin = new FileInputStream(archivo)) {
			os.writeBytes("Fichero\n");
			os.writeBytes(archivo.getName() + "\n");
			os.writeLong(archivo.length());
			byte[] buf = new byte[1024];
			// Lectura por bloques de bytes
			int leido = fin.read(buf);
			while (leido != -1) {
				os.write(buf, 0, leido);
				leido = fin.read(buf);
			}
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que pide al servidor el nombre de todos los alumnos almacenados
	 * 
	 * @return la lista de los alumnos o null en caso de error o estar vacio
	 */
	public static String[] nombreAlumnos() {
		try {
			os.writeBytes("Alumnos\n");
			os.flush();
			return (String[]) is.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Método que pide al servidor la posible imagen que haya mandado el alumno
	 * 
	 * @return un ImageIcon con la imagen mandada por el alumno o null en caso
	 *         contrario
	 */
	public static ImageIcon pedirImagen() {
		try {
			return (ImageIcon) is.readObject();
		} catch (ClassNotFoundException | IOException | ClassCastException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Método que cierra los sockets
	 */
	public static void cerrar() {
		try {
			is.close();
			os.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para jugar a un juego la primera vez (cuando el servidor elige el
	 * número)
	 * 
	 * @param juego el nombre del juego
	 * @param num   el primer intento de adivinar el numero
	 * @return la respuesta del servidor al juego
	 */
	public static String preguntarNumPrimera(String juego, String num) {
		try {
			os.writeBytes(juego + "\n");
			os.writeBytes(num + "\n");
			os.flush();
			return is.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Método para continuar jugando a un juego
	 * 
	 * @param numero el intento de adivinar el numero
	 * @return la respuesta del servidor al juego
	 */
	public static String preguntarNum(String numero) {
		try {
			os.writeBytes(numero + "\n");
			os.flush();
			return is.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Método para pedirle las estadisticas de un juego concreto al servidor
	 * 
	 * @param juego el nombre del juego
	 * @return una lista con objetos jugador, que almacenan las estadisticas de cada
	 *         jugador
	 */
	public static List<Jugador> pedirEstads(String juego) {
		try {
			os.writeBytes("Estadisticas\n");
			os.writeBytes(juego + "\n");
			os.flush();
			List<Jugador> lista = (List<Jugador>) is.readObject();
			return lista;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Método para enviar unas estadisticas nuevas al servidor
	 * 
	 * @param usuario  el nombre del usuario
	 * @param intentos el numero de intentos que ha necesitado
	 * @param tiempo   el tiempo que ha empleado
	 * @param juego    el nombre del juego que estaba jugando
	 */
	public static void enviarEstads(String usuario, int intentos, long tiempo, String juego) {
		// Enviamos las estadisticas en texto plano en vez de como objeto ya que no
		// sabemos el numero de victorias del jugador, solo que ha conseguido una mas
		try {
			os.writeBytes("Enviar estadisticas\n");
			os.writeBytes(juego + "\n");
			os.writeBytes(usuario + "\n");
			os.writeInt(intentos);
			os.writeLong(tiempo);
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
