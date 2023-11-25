package servidor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Iterator;

public class AtenderPeticion extends Thread {

	private Socket s;
	private String usuario;

	public AtenderPeticion(Socket s) {
		this.s = s;
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
	}

	public void run() {
		try {
			BufferedReader bin=new BufferedReader(new InputStreamReader(s.getInputStream()));
			BufferedWriter bout=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			
			String opcion=bin.readLine();
			
			while(!opcion.equals("Salir")) {
				if(opcion.equals("Iniciar sesion")) {
					this.usuario=bin.readLine();
					boolean existeUsuario=comprobarUsuario(usuario);
					if(existeUsuario) {
						this.usuario=usuario;
						bout.write("Inicio sesion correcto\n");
						bout.flush();
					}
					else {
						bout.write("Error usuario\n");
						bout.flush();
					}
				}else if(opcion.equals("Nerdle")) {
					
				}else if(opcion.equals("Examen")) {
					
				}else if(opcion.equals("Gestionar usuarios")) {
					opcion=bin.readLine();
					if(opcion.equals("Borrar usuario")) {
						boolean borrado=borrarUsuario(bin.readLine());
						if(borrado) {
							bout.write("Borrado correcto\n");
							bout.flush();
						}
					}
					else if(opcion.equals("Crear usuario")) {
						boolean creado=crearUsuario(bin.readLine());
						if(creado) {
							bout.write("Creado correcto\n");
							bout.flush();
						}
					}
				}
				opcion=bin.readLine();
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
