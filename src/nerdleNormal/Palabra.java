package nerdleNormal;

public class Palabra {
	private int tam;
	private char[] palabra;
	
	public Palabra(int tam, char[] palabra) {
		this.tam=tam;
		this.palabra=palabra;
	}
	
	public Palabra(char[] palabra) {
		this.tam=palabra.length;
		this.palabra=palabra;
	}
	
	public boolean letraCorrecta(int pos, char letra) {
		if(palabra[pos]==letra) {
			return true;
		}
		return false;
	}
	
	public boolean palabraCorrecta(int pos, char[] pal){
		if(tam != pal.length) {
			return false;
		}
		for(int i=0;i<tam;i++) {
			if(!letraCorrecta(i,pal[i])) {
				return false;
			}
		}
		return true;
	}

}
