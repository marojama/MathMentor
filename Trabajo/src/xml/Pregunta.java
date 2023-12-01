package xml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pregunta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5674492430205039008L;
	private String enunciado;
	private int correcta;
	private List<String> respuestas;
	
	public Pregunta() {
		this.respuestas=new ArrayList<String>();
	}

	public Pregunta(String enunciado, ArrayList<String> respuestas, int correcta) {
		this.enunciado = enunciado;
		this.respuestas = respuestas;
		this.correcta=correcta;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	//@XmlElement(name="respuesta")
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
}
