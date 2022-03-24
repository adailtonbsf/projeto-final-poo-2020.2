package Modelo.USUARIOS;

public class Admin implements Autenticavel {
	
	private String login;
    private String senha;
    private String pin;
    
    public Admin(String login, String senha, String pin){
    	this.login=login;
        this.senha=senha;
        this.pin=pin;
    }
    
    public void trocarSenha(String senha){
        this.senha=senha;
    }
    
    public void trocarPin(String pin){
        this.pin=pin;
    }
    
    public String getPin(){
    	return pin;
    }
    
    public String getLogin(){
        return login;
    }
    
    public String getSenha(){
        return senha;
    }
    
    public String toString(){
        return "pin: "+this.pin+" | login: "+this.login+ " | senha: "+this.senha;
    }

}
