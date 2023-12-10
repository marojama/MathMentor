// Clase Examen
// Aplicación: MathMentor
// Autor: Marta Rojas

package xml;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Examen implements Serializable {

	private static final long serialVersionUID = 1L;
	private String tema;
	private int numCorrectas;
	private List<Pregunta> preguntas;

	/**
	 * Constructor sin parámetros
	 */
	public Examen() {
		this.preguntas = new ArrayList<Pregunta>();
	}

	/**
	 * Constructor con los parámetros iniciales de un examen. No se incluye el número de correctas
	 * ya que es un dato que se añadirá una vez realizado el examen por el alumno
	 * @param tema: el tema/nombre del archivo del examen
	 * @param preguntas: la lista de las preguntas con sus respuestas
	 */
	public Examen(String tema, ArrayList<Pregunta> preguntas) {
		this.tema = tema;
		this.preguntas = preguntas;
	}

	//Getters y setters
	
	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public List<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}

	public int getNumCorrectas() {
		return numCorrectas;
	}

	public void setNumCorrectas(int numCorrectas) {
		this.numCorrectas = numCorrectas;
	}

	//toString
	
	@Override
	public String toString() {
		return "Examen [tema=" + tema + ", numCorrectas=" + numCorrectas + ", preguntas="
				+ preguntas + "]";
	}
}
