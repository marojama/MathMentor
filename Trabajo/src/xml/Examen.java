package xml;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@XmlRootElement(name="examen")
//@XmlType(propOrder= {"tema","fecha","preguntas"})
public class Examen implements Serializable{

	private int id;
	private boolean activo;
	private String tema;
	private LocalDateTime fecha;
	private int numCorrectas;

	
	private List<Pregunta> preguntas;
	
	public Examen() {
		this.preguntas=new ArrayList<Pregunta>();
	}
	
	public Examen(int id, boolean activo, String tema, LocalDateTime fecha, ArrayList<Pregunta> preguntas) {
		this.id = id;
		this.activo = activo;
		this.tema = tema;
		this.fecha = fecha;
		this.preguntas = preguntas;
	}
	
	//@XmlAttribute
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	//@XmlAttribute
	public boolean isActivo() {
		return activo;
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo;
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
	
	//@XmlElementWrapper(name="preguntas")
	//@XmlElement(name="pregunta")
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
		return "Examen [id=" + id + ", activo=" + activo + ", tema=" + tema + ", fecha=" + fecha + ", preguntas="
				+ preguntas + ", getId()=" + getId() + ", isActivo()=" + isActivo() + ", getTema()=" + getTema()
				+ ", getFecha()=" + getFecha() + ", getPreguntas()=" + getPreguntas() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
