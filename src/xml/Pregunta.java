// Clase Pregunta
// Aplicación: MathMentor
// Autor: Marta Rojas

package xml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pregunta implements Serializable {

	private static final long serialVersionUID = 1L;
	private String enunciado;
	private int correcta;
	private int contestada;
	private List<String> respuestas;

	/**
	 * Constructor sin parámetros, necesario por ser serializable
	 */
	public Pregunta() {
		this.respuestas = new ArrayList<String>();
	}

	/**
	 * Constructor con todos los parámetros iniciales. No se incluye el parámetro
	 * contestada ya que es un parámetro que se incluirá una vez realizado el examen
	 * por el alumno
	 * 
	 * @param enunciado:  enunciado de la pregunta
	 * @param respuestas: lista con las respuestas
	 * @param correcta:   índice de la respuesta correcta
	 */
	public Pregunta(String enunciado, ArrayList<String> respuestas, int correcta) {
		this.enunciado = enunciado;
		this.respuestas = respuestas;
		this.correcta = correcta;
	}

	// Getters y setters
	
	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public List<String> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<String> respuestas) {
		this.respuestas = respuestas;
	}

	public int getCorrecta() {
		return correcta;
	}

	public void setCorrecta(int correcta) {
		this.correcta = correcta;
	}

	public int getContestada() {
		return contestada;
	}

	public void setContestada(int contestada) {
		this.contestada = contestada;
	}

}
