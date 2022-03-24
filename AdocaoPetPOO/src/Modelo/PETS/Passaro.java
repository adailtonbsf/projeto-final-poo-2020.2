package Modelo.PETS;

public class Passaro extends Pet {
	
	private String corDaPena;
	
	public Passaro(String nome, int idade, boolean genero, float altura, float peso, String corDaPena, String descricao) {
		super();
		setNome(nome);
		setIdade(idade);
		setGenero(genero);
		setAltura(altura);
		setDescricao(descricao);
		setPeso(peso);
		this.corDaPena = corDaPena;
	}

	@Override
	public String toString() {
		String output = "\n" + getNome() + ":\n"
				+ "- Espécie: " + this.getClass().getSimpleName() + "\n"
				+ "- Idade: " + getIdade() + " ano(s)\n"
				+ "- Gênero: " + (getGenero() ? "Macho":"Fêmea") + "\n"
				+ "- Altura: " + getAltura() + " cm\n"
				+ "- Peso: " + getPeso() + " kg\n"
				+ "- Cor da Pena: " + getCorDaPena() + "\n"
				+ "- Informações: " + getDescricao();
		
		return output;
	}

	public String getCorDaPena() {
		return corDaPena;
	}

}
