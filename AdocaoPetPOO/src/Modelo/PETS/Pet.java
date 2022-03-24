package Modelo.PETS;

import java.util.Date;

public abstract class Pet {
	
	private String nome;
	private int idade;
	private boolean genero;
	private float altura;
	private Date dataDeAdocao;
	private String descricao;
	private float peso;
	
	public abstract String toString();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public boolean getGenero() {
		return genero;
	}

	public void setGenero(boolean genero) {
		this.genero = genero;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public Date getDataDeAdocao() {
		return dataDeAdocao;
	}

	public void setDataDeAdocao(Date dataDeAdocao) {
		this.dataDeAdocao = dataDeAdocao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

}
