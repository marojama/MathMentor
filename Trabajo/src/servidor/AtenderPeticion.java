package servidor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class AtenderPeticion extends Thread {

	private Socket s;
	private String usuario;

	public AtenderPeticion(Socket s) {
		this.s = s;
		this.usuario="";
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
					
				}else if(opcion.equals("Usuarios")) {
					
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
	
	public boolean comprobarUsuario(String usuario) {
		if(usuario.equals("marojamaProfe") || usuario.equals("invitado")) {
			return true;
		}
		else{
			try(BufferedReader bfich=new BufferedReader(new FileReader("Usuarios.txt"));) {
				String leido=bfich.readLine();
				while(leido!=null) {
					System.out.println(leido);
					if(leido.equals(usuario)) {
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

}
