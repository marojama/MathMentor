package servidor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
			
			boolean existeUsuario=false;
			do {
				String usuario=bin.readLine();
				existeUsuario=comprobarUsuario(usuario);
				if(existeUsuario) {
					this.usuario=usuario;
					bout.write("Inicio sesion correcto\n");
				}
				else {
					bout.write("Error usuario\n");
				}
			}while(!existeUsuario);
			
			String opcion=bin.readLine();
			
			if(opcion.equals("Nerdle")) {
				
			}else if(opcion.equals("Examen")) {
				
			}else if(opcion.equals("Usuarios")) {
				
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
			try {
				BufferedReader bfich=new BufferedReader(new InputStreamReader(new FileInputStream("Usuarios.txt")));
				String leido=bfich.readLine();
				while(leido!=null) {
					if(leido.equals(usuario)) {
						return true;
					}
					leido=bfich.readLine();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

}
