package principal;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import interfazGrafica.InicioSesion;

public class Principal {
	
	private static Socket s;

	public static void main(String[] args) {
		try {
			s=new Socket("localhost",55555);
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						InicioSesion frame = new InicioSesion();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static boolean existeUsuario(String usuario) {
		try {
			BufferedReader bin=new BufferedReader(new InputStreamReader(s.getInputStream()));
			BufferedWriter bout=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			
			bout.write(usuario+"\n");
			bout.flush();
			String respuesta=bin.readLine();
			if(respuesta.equals("Inicio sesion correcto")) {
				return true;
			}else {
				return false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
		
	}

}
