package xml;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Examen implements Serializable {

	private String tema;
	private LocalDateTime fecha;
	private int numCorrectas;

	private List<Pregunta> preguntas;

	public Examen() {
		this.preguntas = new ArrayList<Pregunta>();
	}

	public Examen(String tema, LocalDateTime fecha, ArrayList<Pregunta> preguntas) {
		this.tema = tema;
		this.fecha = fecha;
		this.preguntas = preguntas;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime localDateTime) {
		this.fecha = localDateTime;
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

	@Override
	public String toString() {
		return "Examen [tema=" + tema + ", fecha=" + fecha + ", numCorrectas=" + numCorrectas + ", preguntas="
				+ preguntas + "]";
	}
}
