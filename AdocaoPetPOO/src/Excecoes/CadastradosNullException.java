package Excecoes;

public class CadastradosNullException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "fail: nenhum usuário foi cadastrado";
	}

}
