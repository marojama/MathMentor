// Servidor de la aplicación
// Aplicación: MathMentor
// Autor: Marta Rojas

package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Servidor {

	public static void main(String[] args) {

		// Pool de hilos de tamaño variable
		ExecutorService pool = Executors.newCachedThreadPool();

		// Servidor esperando en el puerto 55555
		try (ServerSocket ss = new ServerSocket(55555)) {

			while (true) {
				try {
					// Aceptamos cliente
					Socket s = ss.accept();
					// Atendemos al cliente con uno de los hilos
					pool.execute(new AtenderPeticion(s));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
