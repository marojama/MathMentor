package xml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@XmlRootElement(name="examen")
//@XmlType(propOrder= {"tema","fecha","preguntas"})
public class Examen implements Serializable{

	private int id;
	private boolean activo;
	private String tema;
	private Date fecha;

	
	private List<Pregunta> preguntas;
	
	public Examen() {
		this.preguntas=new ArrayList<Pregunta>();
	}
	
	public Examen(int id, boolean activo, String tema, Date fecha, ArrayList<Pregunta> preguntas) {
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
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	//@XmlElementWrapper(name="preguntas")
	//@XmlElement(name="pregunta")
	public List<Pregunta> getPreguntas() {
		return preguntas;
	}
	
	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}

	@Override
	public String toString() {
		return "Examen [id=" + id + ", activo=" + activo + ", tema=" + tema + ", fecha=" + fecha + ", preguntas="
				+ preguntas + ", getId()=" + getId() + ", isActivo()=" + isActivo() + ", getTema()=" + getTema()
				+ ", getFecha()=" + getFecha() + ", getPreguntas()=" + getPreguntas() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
