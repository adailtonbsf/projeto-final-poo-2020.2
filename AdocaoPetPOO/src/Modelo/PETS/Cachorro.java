package Modelo.PETS;

public class Cachorro extends Pet {
	
	private String corDoPelo;
	private String tamanhoDoPelo;
	private String tamanhoDaCauda;
	
	public Cachorro(String nome, int idade, boolean genero, float altura, float peso, String corDoPelo, String tamanhoDoPelo, 
			String tamanhoDaCauda, String descricao) {
		super();
		setNome(nome);
		setIdade(idade);
		setGenero(genero);
		setAltura(altura);
		setDescricao(descricao);
		setPeso(peso);
		this.corDoPelo = corDoPelo;
		this.tamanhoDaCauda = tamanhoDaCauda;
		this.tamanhoDoPelo = tamanhoDoPelo;
	}

	@Override
	public String toString() {
		String output = "\n" + getNome() + ":\n"
				+ "- Espécie: " + this.getClass().getSimpleName() + "\n"
				+ "- Idade: " + getIdade() + " ano(s)\n"
				+ "- Gênero: " + (getGenero() ? "Macho":"Fêmea") + "\n"
				+ "- Altura: " + getAltura() + " cm\n"
				+ "- Peso: " + getPeso() + " kg\n"
				+ "- Pelo: Cor " + getCorDoPelo() + " | Tamanho " + getTamanhoDoPelo() + "\n"
				+ "- Tamanho da Cauda: " + getTamanhoDaCauda() + "\n"
				+ "- Informações: " + getDescricao();
		
		return output;
	}

	public String getCorDoPelo() {
		return corDoPelo;
	}

	public String getTamanhoDoPelo() {
		return tamanhoDoPelo;
	}

	public String getTamanhoDaCauda() {
		return tamanhoDaCauda;
	}

}
