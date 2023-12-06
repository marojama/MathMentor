package servidor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import xml.Examen;
import xml.Pregunta;

public class AtenderPeticion extends Thread {

	private Socket s;
	private ObjectInputStream is;
	private ObjectOutputStream os;
	private String usuario;

	public AtenderPeticion(Socket s) {
		
		try {
			this.s = s;
			os=new ObjectOutputStream(s.getOutputStream());
			is=new ObjectInputStream(s.getInputStream());
			this.usuario="";
			//Almacen de usuarios
			File f=new File("Usuarios.txt");
			if(!f.exists()) {
				try {
					f.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void run() {
		try		
		{
			String opcion=is.readLine();
			
			while(!opcion.equals("Salir")) {
				if(opcion.equals("Iniciar sesion")) {
					this.usuario=is.readLine();
					boolean existeUsuario=comprobarUsuario(usuario);
					if(existeUsuario) {
						this.usuario=usuario;
						os.writeBytes("Inicio sesion correcto\n");
						os.flush();
					}
					else {
						os.writeBytes("Error usuario\n");
						os.flush();
					}
				}else if(opcion.equals("Nerdle")) {
					
				}else if(opcion.equals("Examenes")) {
					String[] nomExam=devolverNomExam(this.usuario);
					os.writeObject(nomExam);
					os.flush();
					System.out.println("He mandado los nombres");
				}else if(opcion.equals("Examen")) {
					String e=is.readLine();
					Examen examen=devolverExam(this.usuario,e);
					os.writeObject(examen);
					os.flush();
					
				}else if(opcion.equals("Gestionar usuarios")) {
					opcion=is.readLine();
					if(opcion.equals("Borrar usuario")) {
						boolean borrado=borrarUsuario(is.readLine());
						if(borrado) {
							os.writeBytes("Borrado correcto\n");
							os.flush();
						}
					}
					else if(opcion.equals("Crear usuario")) {
						boolean creado=crearUsuario(is.readLine());
						if(creado) {
							os.writeBytes("Creado correcto\n");
							os.flush();
						}
					}
				}
				else if(opcion.equals("Examen hecho")) {
					String nomFich=is.readLine();
					String usuario=is.readLine();
					recibirExamen(nomFich,usuario);
				}
				opcion=is.readLine();
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void recibirExamen(String nomFich, String usuario2) {
		try {
			Examen ex=(Examen) is.readObject();
			
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=db.newDocument();
			
			Element examen=doc.createElement("examen");
			examen.setAttribute("activo", String.valueOf(ex.isActivo()));
			examen.setAttribute("id", String.valueOf(ex.getId()));
			
			Element tema=doc.createElement("tema");
			tema.appendChild(doc.createTextNode(ex.getTema()));
			examen.appendChild(tema);
			
			Element fecha=doc.createElement("fecha");
			fecha.appendChild(doc.createTextNode(ex.getFecha().toString()));
			examen.appendChild(fecha);
			
			Element numCorrectas=doc.createElement("numCorrectas");
			numCorrectas.appendChild(doc.createTextNode(String.valueOf(ex.getNumCorrectas())));
			examen.appendChild(numCorrectas);
			
			Element preguntas=doc.createElement("preguntas");
			
			List<Pregunta> preg=ex.getPreguntas();
			for(int i=0,n=preg.size();i<n;i++) {
				Element pregunta=doc.createElement("pregunta");
				pregunta.setAttribute("correcta", String.valueOf(preg.get(i).getCorrecta()));
				pregunta.setAttribute("contestada", String.valueOf(preg.get(i).getContestada()));
				
				Element enunciado=doc.createElement("enunciado");
				enunciado.appendChild(doc.createTextNode(preg.get(i).getEnunciado()));
				pregunta.appendChild(enunciado);
				
				List<String> respuestas=preg.get(i).getRespuestas();
				for(int j=0,m=respuestas.size();j<m;j++) {
					Element respuesta=doc.createElement("respuesta");
					respuesta.appendChild(doc.createTextNode(respuestas.get(j)));
					pregunta.appendChild(respuesta);
				}
				preguntas.appendChild(pregunta);
			}
			
			examen.appendChild(preguntas);
			
			doc.appendChild(examen);
			
			TransformerFactory tf=TransformerFactory.newInstance();
			Transformer t=tf.newTransformer();
			DOMSource source=new DOMSource(doc);
			File f=new File("./"+usuario2+"/"+nomFich.split(".xml")[0]+"Resuelto.xml");
			if(!f.exists()) {
				f.createNewFile();
			}
			File antiguo=new File("./"+usuario2+"/"+nomFich);
			antiguo.delete();
			StreamResult result=new StreamResult(f);
			t.transform(source, result);
		} catch (ClassNotFoundException | IOException | ParserConfigurationException | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Examen devolverExam(String usuario2, String e) {
		
		try {
			File exam=new File(usuario2+"/"+e);
			DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
			DocumentBuilder db= dbf.newDocumentBuilder();
			Document doc=db.parse(exam);
			
			Examen examen=new Examen();
			ArrayList<Pregunta> preguntas=new ArrayList<>();
			//Elemento <examen>
			Element raiz=doc.getDocumentElement();
			examen.setActivo(Boolean.parseBoolean(raiz.getAttribute("activo")));
			examen.setId(Integer.parseInt(raiz.getAttribute("id")));
			//DateFormat format=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
			//examen.setFecha(format.parse(raiz.getElementsByTagName("fecha").item(0).getTextContent()));
			//System.out.println(examen.getFecha().toString());
			examen.setTema(raiz.getElementsByTagName("tema").item(0).getTextContent());
			NodeList p=raiz.getElementsByTagName("pregunta");
			for(int i=0;i<p.getLength();i++) {
				Pregunta preg=new Pregunta();
				preg.setEnunciado(((Element) p.item(i)).getElementsByTagName("enunciado").item(0).getTextContent());
				NodeList resp=((Element) p.item(i)).getElementsByTagName("respuesta");
				ArrayList<String> respuestas=new ArrayList<>();
				for (int j = 0; j < resp.getLength(); j++) {
					respuestas.add(resp.item(j).getTextContent());
				}
				preg.setRespuestas(respuestas);
				preg.setCorrecta(Integer.parseInt(((Element) p.item(i)).getAttribute("correcta")));
				preguntas.add(preg);
			}
			examen.setPreguntas(preguntas);
			return examen;
		} catch (ParserConfigurationException | SAXException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DOMException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return null;
	}

	private String[] devolverNomExam(String usuario2) {
		File carpeta=new File(usuario2);
		File[] ficheros=carpeta.listFiles();
		String[] examenes=new String[ficheros.length];
		for(int i=0,n=ficheros.length;i<n;i++) {
			if(!ficheros[i].getName().contains("Resuelto")) {
				examenes[i]=ficheros[i].getName();
			}
		}
		return examenes;
	}

	public boolean comprobarUsuario(String u) {
		if(u.equals("marojamaProfe") || u.equals("invitado")) {
			return true;
		}
		else{
			try(BufferedReader bfich=new BufferedReader(new FileReader("Usuarios.txt"));) {
				String leido=bfich.readLine();
				while(leido!=null) {
					if(leido.equals(u)) {
						return true;
					}
					leido=bfich.readLine();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean borrarUsuario(String u) {
		File original,temporal;
		boolean borrado=false;
		
		try(BufferedReader bfich=new BufferedReader(new FileReader("Usuarios.txt"));
		BufferedWriter btemp=new BufferedWriter(new FileWriter("copia.tmp"))) {
			String leido=bfich.readLine();
			while(leido!=null) {
				if(!leido.equals(u)) {
					btemp.write(leido);
					btemp.newLine();
				}
				else {
					borrado=true;
					borrarCarpetaExamenes(u);
				}
				leido=bfich.readLine();
			}
			bfich.close();
			btemp.close();
			original=new File("Usuarios.txt");
			original.delete();
			temporal=new File("copia.tmp");
			temporal.renameTo(original);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return borrado;
	}
	
	public void borrarCarpetaExamenes(String u) {
		File carpeta=new File(u);
		boolean borrado=carpeta.delete();
		//Tiene examenes dentro, no se puede borrar
		if(!borrado) {
			for (File f : carpeta.listFiles()) {
				f.delete();
			}
			carpeta.delete();
		}
	}
	
	public boolean crearUsuario(String u) {
		boolean creado=false;
		
		try(BufferedWriter bfich=new BufferedWriter (new FileWriter ("Usuarios.txt",true))) {
			bfich.write(u);
			bfich.newLine();
			File carpetaExamenes=new File(u);
			carpetaExamenes.mkdir();
			creado=true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return creado;
	}
	
	

}
