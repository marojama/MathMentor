package Estadisticas;

import java.io.Serializable;

public class Jugador implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private int victorias;
	private int intentos;
	private long mejorTiempo;

	public Jugador() {
	}

	public Jugador(String nombre, int victorias, int intentos, long mejorTiempo) {
		this.nombre = nombre;
		this.victorias = victorias;
		this.intentos = intentos;
		this.mejorTiempo = mejorTiempo;
	}

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

	@Override
	public String toString() {
		return nombre + " - Victorias=" + victorias + " - Intentos=" + intentos + " - Tiempo=" + mejorTiempo/1000 +"s";
	}

}
