// Clase Jugador
// Aplicación: MathMentor
// Autor: Marta Rojas

package xml;

import java.io.Serializable;

public class Jugador implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nombre;
	private int victorias;
	private int intentos;
	private long mejorTiempo;

	/**
	 * Constructor sin parámetros, necesario por ser serializable
	 */
	public Jugador() {
	}

	/**
	 * Constructor con todos los parámetros
	 * @param nombre: nombre del usuario
	 * @param victorias: número de victorias totales para un juego
	 * @param intentos: mejor número de intentos usados
	 * @param mejorTiempo: mejor tiempo empleado para resolver un juego
	 */
	public Jugador(String nombre, int victorias, int intentos, long mejorTiempo) {
		this.nombre = nombre;
		this.victorias = victorias;
		this.intentos = intentos;
		this.mejorTiempo = mejorTiempo;
	}

	//Getters y setters
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getVictorias() {
		return victorias;
	}

	public void setVictorias(int victorias) {
		this.victorias = victorias;
	}

	public int getIntentos() {
		return intentos;
	}

	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}

	public long getMejorTiempo() {
		return mejorTiempo;
	}

	public void setMejorTiempo(long mejorTiempo) {
		this.mejorTiempo = mejorTiempo;
	}
	
	//toString

	@Override
	public String toString() {
		return "Jugador [nombre=" + nombre + ", victorias=" + victorias + ", intentos=" + intentos + ", mejorTiempo="
				+ mejorTiempo + "]";
	}

}
