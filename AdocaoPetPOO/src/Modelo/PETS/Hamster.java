package Modelo.PETS;

public class Hamster extends Pet {
	
	private String corDoPelo;
	
	public Hamster(String nome, int idade, boolean genero, float altura, float peso, String corDoPelo, String descricao) {
		super();
		setNome(nome);
		setIdade(idade);
		setGenero(genero);
		setAltura(altura);
		setDescricao(descricao);
		setPeso(peso);
		this.corDoPelo = corDoPelo;
	}

	@Override
	public String toString() {
		String output = "\n" + getNome() + ":\n"
				+ "- Espécie: " + this.getClass().getSimpleName() + "\n"
				+ "- Idade: " + getIdade() + " ano(s)\n"
				+ "- Gênero: " + (getGenero() ? "Macho":"Fêmea") + "\n"
				+ "- Altura: " + getAltura() + " cm\n"
				+ "- Peso: " + getPeso() + " kg\n"
				+ "- Cor do Pelo: " + getCorDoPelo() + "\n"
				+ "- Informações: " + getDescricao();
		
		return output;
	}

	public String getCorDoPelo() {
		return corDoPelo;
	}

}
