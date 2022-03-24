package Modelo.USUARIOS;

import java.text.DecimalFormat;

public class Usuario implements Autenticavel, Comparable<Usuario> {
	
	private String login;
    private String senha;
    private float totalDoado;
    private String nome;
    
    public Usuario(String login, String senha, String nome){
        this.login=login;
        this.senha=senha;
        this.nome=nome;
    }
    
    public void trocarSenha(String senha){
        this.senha=senha;
    }
    
    public void doarDinheiro(float valor){
        totalDoado+=valor;
    }
    
    public String getLogin(){
        return login;
    }
    
    public String getNome(){
        return nome;
    }
    
    public String getSenha(){
        return senha;
    }
    public String toString(){
        return "Nome: " + nome + " | Login: " + login + " | Senha: " + senha +" | Total doado: " + 
        		DecimalFormat.getCurrencyInstance().format(totalDoado);
    }
    
    public int compareTo(Usuario o){
        return this.getNome().compareTo(o.getNome());
    }

}
