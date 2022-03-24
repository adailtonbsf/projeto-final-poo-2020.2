package Excecoes;

public class InvalidPetNameException extends Exception {
	
private static final long serialVersionUID = 1L;
	
	public String getMessage() {
		return "fail: nenhum pet foi encontrado com esse nome";
	}

}
