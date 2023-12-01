package xml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pregunta implements Serializable{

	private String enunciado;
	
	private List<String> respuestas;
	
	public Pregunta() {
		this.respuestas=new ArrayList<String>();
	}

	public Pregunta(String enunciado, ArrayList<String> respuestas) {
		this.enunciado = enunciado;
		this.respuestas = respuestas;
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
	
	
}
