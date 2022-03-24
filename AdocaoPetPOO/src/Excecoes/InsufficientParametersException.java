package Excecoes;

public class InsufficientParametersException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public String getMessage(String usoCorreto) {
		return "fail: parâmetros inválidos, uso correto: '" + usoCorreto + "'";
	}

}
