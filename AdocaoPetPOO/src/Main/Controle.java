package Main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import Excecoes.CadastradosNullException;
import Excecoes.InsufficientParametersException;
import Excecoes.InvalidPetNameException;
import Modelo.PETS.Cachorro;
import Modelo.PETS.Gato;
import Modelo.PETS.Hamster;
import Modelo.PETS.Passaro;
import Modelo.PETS.Pet;
import Modelo.USUARIOS.Admin;
import Modelo.USUARIOS.Autenticavel;
import Modelo.USUARIOS.Usuario;

public class Controle {
	
	private HashMap<Pet, Usuario> pets;
	private Visao visao = new Visao();
	private ArrayList <Autenticavel> users;
    private float saldo;
    private Autenticavel usuarioAtual;
    private Admin admin;
    private ArrayList<Usuario> cadastrados;
	
	public Controle() {
		pets = new HashMap<Pet, Usuario>();
		users = new ArrayList<Autenticavel>();
		cadastrados = new ArrayList<Usuario>();
		admin = new Admin("admin", "40028922", "0000");
		users.add(admin);
		cadastrar("123", "123", "Default"); //Remover isso antes de enviar
		//Alguns pets predefinidos para testes
		addAdocao(new Cachorro("Rex", 15, true, 1.75f, 12.3f, "Preto", "Grande", "Média", "Blablabla"));
		addAdocao(new Gato("Miau", 15, true, 1.75f, 12.3f, "Preto", "Grande", "Média"));
		addAdocao(new Hamster("Jerry", 15, true, 1.75f, 12.3f, "Preto", "Grande"));
		addAdocao(new Passaro("Blue", 15, true, 1.75f, 12.3f, "Preto", "Grande"));
	}
	
	public Autenticavel getUsuarioAtual() {
		return usuarioAtual;
	}
	
	public void setUsuarioAtual(Autenticavel aut) {
		this.usuarioAtual = aut;
	}
	
	public boolean addAdocao(Pet p) {
		if(existePet(p.getNome())) {
			return false;
		}
		pets.put(p, null);
		return true;
	}
	
	public boolean adotar(String comando[]) {
		try {
			if(parametrosSuficientes(comando, 2));
			for(Entry<Pet, Usuario> p : pets.entrySet()) {
				if(p.getKey().getNome().equals(comando[1]) && p.getValue() == null) {
					p.setValue((Usuario) usuarioAtual);
					p.getKey().setDataDeAdocao(Calendar.getInstance().getTime());
					return true;
				}
			}
			System.out.println("fail: nenhum pet foi encontrado com esse nome");
		} catch (InsufficientParametersException e) {
			System.out.println("\n" + e.getMessage("adotar <nomeDoPet>"));
		}
		return false;
	}
	
	public boolean existePet(String nomeDoPet) {
		for(Entry<Pet, Usuario> p : pets.entrySet()) {
			if(p.getKey().getNome().equals(nomeDoPet)) {
				return true;
			}
		}
		return false;
	}
	
	public Pet getPet(String nomeDoPet) {
		for(Entry<Pet, Usuario> p : pets.entrySet()) {
			if(p.getKey().getNome().equals(nomeDoPet)) {
				return p.getKey();
			}
		}
		return null;
	}
	
	public boolean editar(String nomeDoPet, String info, String novaInfo) {
		for(Entry<Pet, Usuario> p : pets.entrySet()) {
			if(p.getKey().getNome().equals(nomeDoPet) && p.getValue() == null) {
				if(info.equals("nome")) {
					if(!existePet(nomeDoPet)) {
						p.getKey().setNome(novaInfo);
						return true;
					}
				} else if(info.equals("descricao")) {
					p.getKey().setDescricao(novaInfo);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean editar(String nomeDoPet, String info, float novaInfo) {
		for(Entry<Pet, Usuario> p : pets.entrySet()) {
			if(p.getKey().getNome().equals(nomeDoPet)) {
				if(info.equals("altura")) {
					p.getKey().setAltura(novaInfo);
					return true;
				} else if(info.equals("peso")) {
					p.getKey().setPeso(novaInfo);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean editar(String nomeDoPet, String info, int novaInfo) {
		for(Entry<Pet, Usuario> p : pets.entrySet()) {
			if(p.getKey().getNome().equals(nomeDoPet)) {
				if(info.equals("idade")) {
					p.getKey().setIdade(novaInfo);
					return true;
				}
			}
		}
		return false;
	}
	
	public void editarPet(String comando[]) {
		try {
			if(parametrosSuficientes(comando, 4));
			if(existePet(comando[1])) {
				if(comando[2].equals("nome")) {
					editar(comando[1], comando[2], comando[3]);
				} else if(comando[2].equals("descricao")) {
					String descricao = comando[3];
					for(int i = 4; i < comando.length; i++) descricao += " " + comando[i];
					editar(comando[1], comando[2], descricao);
				} else if(comando[2].equals("altura") || comando[2].equals("peso")) {
					editar(comando[1], comando[2], Float.parseFloat(comando[3]));
				} else if(comando[2].equals("idade")) {
					if(Integer.parseInt(comando[3]) >= getPet(comando[1]).getIdade())
						editar(comando[1], comando[2], Integer.parseInt(comando[3]));
					else System.out.println("fail: Um pet não pode ficar mais novo do que é");
						
				} else {
					System.out.println("fail: " + comando[2] + " não é um parâmetro válido");
				}
			} else {
				System.out.println("fail: nenhum pet foi encontrado com esse nome");
			}
		} catch (InsufficientParametersException e) {
			System.out.println("\n" + e.getMessage("editarPet <nomeDoPet> <nome:descricao:altura:peso:idade> <novaInfo>"));
		} catch (Exception e) {
			System.out.println("\nfail: ocorreu um erro (" + e.getMessage() + ")");
		}
	}
	
	public boolean devolverPet(String comando[], Scanner scanner) throws InvalidPetNameException {
		try {
			if(parametrosSuficientes(comando, 2));
			if(existePet(comando[1])) {
				System.out.println("-> Confirme a senha:");
				if(confirmarSenha(usuarioAtual.getSenha(), scanner.nextLine().split(" ")[0])) {
					for(Entry<Pet, Usuario> p : pets.entrySet()) {
						if(p.getKey().getNome().equals(comando[1])) {
							if(p.getValue() == usuarioAtual) {
								p.setValue(null);
								return true;
							}
						}
					}
					System.out.println("\nfail: você não adotou nenhum pet com esse nome");
				} else System.out.println("\nfail: senha inválida!\n");
			} else throw new InvalidPetNameException();
		} catch (InsufficientParametersException e) {
			System.out.println(e.getMessage("devolver <nomeDoPet>"));
		}
		return false;
	}
	
	public void mostrarCatalogo() {
		System.out.println(visao.mostrarCatalogo(pets));
	}
	
	public void mostrarCatalogo(String comando[]) {
		try {
			if(parametrosSuficientes(comando, 2));
			if(comando[1].equals("cachorro") || comando[1].equals("gato") || comando[1].equals("hamster") || comando[1].equals("passaro")) {
				System.out.println(visao.mostrarCatalogo(pets, comando[1]));
			} else System.out.println("\nfail: filtro inválido (Use: cachorro, gato, hamster ou passaro)");
		} catch (InsufficientParametersException e) {
			System.out.println(e.getMessage("mostrarCatalogo <cachorro:gato:hamster:passaro>"));
		}
	}
	
	public void mostrarPets() {
		System.out.println("\n" + visao.mostrarPets(pets));
	}
	
	public void mostrarAdotados() {
		System.out.println("\n" + visao.mostrarAdotados(pets));
	}
	
	public void mostrarUsuarios(){
	    try{
			System.out.print("\n" + visao.mostrarUsuarios(cadastrados));
	    } catch (CadastradosNullException e){
	      System.out.print("\n" + e.getMessage());
	    }
	}
	
	public boolean doarDinheiro(String comando[]){
		try {
			if(parametrosSuficientes(comando, 2));
			float quantia = Float.parseFloat(comando[1]);
			if(quantia <= 0) System.out.println("\nfail: quantia inválida");
			((Usuario) usuarioAtual).doarDinheiro(quantia);
		    saldo = getSaldo() + quantia;
		    return true;
		} catch (InsufficientParametersException e) {
			System.out.println("\n" + e.getMessage("doarDinheiro <quantia>"));
		} catch(Exception e) {
			System.out.println("\nfail: ocorreu um erro (" + e.getMessage() + ")");
		}
		
		return false;
	}
	
	public boolean cadastrar(String login, String senha, String nome){
		for (Autenticavel aut: users){
			if (aut.getLogin().equals(login)){
				return false;
			}
		}
		users.add(new Usuario(login, senha, nome));
		cadastrados.add(new Usuario(login, senha, nome));
		return true;
	}
	
	public boolean login(String login, String senha) {
		for(Autenticavel aut: users) {
			if(aut.getLogin().equals(login)) {
				if(aut.getSenha().equals(senha)) {
					setUsuarioAtual(aut);
					return true;
				} 
			}
		}
		return false;
	}
	
	public boolean confirmarSenha(String senha, String confirmar) {
		if(senha.equals(confirmar)) {
			return true;
		}
		return false;
	}
	

	public void trocarSenhaU(String comando[], Scanner scanner) throws InsufficientParametersException{
	    if (parametrosSuficientes(comando, 4)){
	    	if (usuarioAtual.getLogin().equals(comando[1]) && usuarioAtual.getSenha().equals(comando[2])){
	    		System.out.println("\n-> Confirme a senha nova:");
		        String senha = scanner.nextLine();
		        if(comando[3].equals(senha)){
		        	usuarioAtual.trocarSenha(senha);
		        	if (cadastrados != null){
		        			for(int i=0; i<cadastrados.size(); i++){
		        				if(cadastrados.get(i).getLogin().equals(comando[1])){
		        					cadastrados.remove(i);
		        					cadastrados.add((Usuario) usuarioAtual);
		        					return;
		        				}
		        			}
		        			return;
		        	} else {
		        		System.out.println("\nfail: confirmacao da nova senha mal sucedida");
		        		return;
		        	}
		        } else {
		        	System.out.println("\nfail: dados informados incorretamente");
		        	return;
		        }
	    	} else throw new InsufficientParametersException();
	    }
	}
	
	public void trocarSenhaA(String comando[], Scanner scanner) throws InsufficientParametersException{
		if (parametrosSuficientes(comando, 4)){
			if(comando[1].equals(admin.getSenha()) && comando[2].equals(admin.getPin())){
		        System.out.println("\n-> Confirme a senha nova:");
		        String senha = scanner.nextLine();
		        if(comando[3].equals(senha)){
		        	admin.trocarSenha(senha);
		        } else {
		        	System.out.println("\nfail: confirmacao da nova senha mal sucedida");
		        	return;
		        }
			} else {
				System.out.println("\nfail: dados informados incorretamente");
				return;
			}
		} else throw new InsufficientParametersException();
	}
	
	public void trocarPin(String comando[], Scanner scanner) {
		try {
			if(parametrosSuficientes(comando, 3));
			if(comando[1].equals(admin.getPin())) {
				System.out.println("\n-> Confirme o novo pin:");
				if(confirmarSenha(comando[2], scanner.nextLine().split(" ")[0]))
					admin.trocarPin(comando[2]);
				else
					System.out.println("\nfail: confirmacao do novo pin mal sucedida");
			} else
				System.out.println("\nfail: pin atual incorreto");
		} catch (InsufficientParametersException e) {
			System.out.println("\n" + e.getMessage("trocarPin <pinAtual> <novoPin>"));
		}
		
	}
	
	public boolean parametrosSuficientes(String comando[], int parametrosNecessarios) throws InsufficientParametersException {
		if(comando.length >= parametrosNecessarios) return true;
		throw new InsufficientParametersException();
	}

	public float getSaldo() {
		return saldo;
	}
	
	public void doarPet(String comando[], Scanner scanner) {
		try {
			if(parametrosSuficientes(comando, 2)) {
				Pet p;
				switch(comando[1]) {
					case "cachorro":
						System.out.println("\nDigite as seguintes informações respectivamente:\n"
								+ "-> <nome> <idade> <macho:femea> <altura> <peso> <corDoPelo> <tamanhoDoPelo> "
								+ "<tamanhoDaCauda> <descricao>\n"
								+ "Ex.: Rex 3 macho 56 20 Preto Pequeno Média Foi abandonado na rua");
						comando = scanner.nextLine().split(" ");
						try {
							if(parametrosSuficientes(comando, 9)) {
								String descricao = comando[8];
								for(int i = 9; i < comando.length; i++) descricao += " " + comando[i];
								try {
									if(comando[2].equals("macho") || comando[2].equals("femea")) {
										p = new Cachorro(comando[0], Integer.parseInt(comando[1]), comando[2].equals("macho")
												, Float.parseFloat(comando[3]), Float.parseFloat(comando[4]), comando[5], 
													comando[6], comando[7], descricao);
										if(!addAdocao(p)) System.out.println("\nfail: esse nome já está em uso");
									} else {
										System.out.println("fail: gênero inválido, uso correto: <macho:femea>");
									}
								} catch (Exception e) {
									System.out.println("\nfail: ocorreu um erro (" + e.getMessage() + ")");
								}
							}
						} catch (InsufficientParametersException e) {
							System.out.println("\n" + e.getMessage("<nome> <idade> <macho:femea> <altura> <peso> <corDoPelo>"
									+ " <tamanhoDoPelo> <tamanhoDaCauda> <descricao>"));
						}
						break;
					case "gato":
						System.out.println("\nDigite as seguintes informações respectivamente:\n"
								+ "-> <nome> <idade> <macho:femea> <altura> <peso> <corDoPelo> <tamanhoDoPelo> "
								+ "<descricao>\n"
								+ "Ex.: Miau 2 femea 20 6 Laranja Pequeno Foi encontrado perdido");
						comando = scanner.nextLine().split(" ");
						try {
							if(parametrosSuficientes(comando, 8)) {
								String descricao = comando[7];
								for(int i = 8; i < comando.length; i++) descricao += " " + comando[i];
								try {
									if(comando[2].equals("macho") || comando[2].equals("femea")) {
										p = new Gato(comando[0], Integer.parseInt(comando[1]), comando[2].equals("macho")
												, Float.parseFloat(comando[3]), Float.parseFloat(comando[4]), comando[5], 
													comando[6], descricao);
										if(!addAdocao(p)) System.out.println("\nfail: esse nome já está em uso");
									} else {
										System.out.println("fail: gênero inválido, uso correto: <macho:femea>");
									}
								} catch (Exception e) {
									System.out.println("\nfail: ocorreu um erro (" + e.getMessage() + ")");
								}
							}
						} catch (InsufficientParametersException e) {
							System.out.println("\n" + e.getMessage("<nome> <idade> <macho:femea> <altura> <peso> <corDoPelo>"
									+ " <tamanhoDoPelo> <descricao>"));
						}
						break;
					case "hamster":
						System.out.println("\nDigite as seguintes informações respectivamente:\n"
								+ "-> <nome> <idade> <macho:femea> <altura> <peso> <corDoPelo> <descricao>\n"
								+ "Ex.: Jerry 1 macho 5 0.05 Branco Filho de uma ninhada");
						comando = scanner.nextLine().split(" ");
						try {
							if(parametrosSuficientes(comando, 7)) {
								String descricao = comando[6];
								for(int i = 7; i < comando.length; i++) descricao += " " + comando[i];
								try {
									if(comando[2].equals("macho") || comando[2].equals("femea")) {
										p = new Hamster(comando[0], Integer.parseInt(comando[1]), comando[2].equals("macho")
												, Float.parseFloat(comando[3]), Float.parseFloat(comando[4]), comando[5]
														, descricao);
										if(!addAdocao(p)) System.out.println("\nfail: esse nome já está em uso");
									} else {
										System.out.println("fail: gênero inválido, uso correto: <macho:femea>");
									}
								} catch (Exception e) {
									System.out.println("\nfail: ocorreu um erro (" + e.getMessage() + ")");
								}
							}
						} catch (InsufficientParametersException e) {
							System.out.println("\n" + e.getMessage("<nome> <idade> <macho:femea> <altura> <peso> <corDoPelo>"
									+ " <descricao>"));
						}
						break;
					case "passaro":
						System.out.println("\nDigite as seguintes informações respectivamente:\n"
								+ "-> <nome> <idade> <macho:femea> <altura> <peso> <corDaPena> <descricao>\n"
								+ "Ex.: Blue 3 macho 89 1.2 Azul Resgatado de um contrabandista");
						comando = scanner.nextLine().split(" ");
						try {
							if(parametrosSuficientes(comando, 7)) {
								String descricao = comando[6];
								for(int i = 7; i < comando.length; i++) descricao += " " + comando[i];
								try {
									if(comando[2].equals("macho") || comando[2].equals("femea")) {
										p = new Passaro(comando[0], Integer.parseInt(comando[1]), comando[2].equals("macho")
												, Float.parseFloat(comando[3]), Float.parseFloat(comando[4]), comando[5]
														, descricao);
										if(!addAdocao(p)) System.out.println("\nfail: esse nome já está em uso");
									} else {
										System.out.println("fail: gênero inválido, uso correto: <macho:femea>");
									}
								} catch (Exception e) {
									System.out.println("\nfail: ocorreu um erro (" + e.getMessage() + ")");
								}
							}
						} catch (InsufficientParametersException e) {
							System.out.println("\n" + e.getMessage("<nome> <idade> <macho:femea> <altura> <peso> <corDaPena>"
									+ " <descricao>"));
						}
						break;
					default:
						System.out.println("\n-> O parâmetro '" + comando[1] + "' não é válido, tente novamente:");
				}
			}
		} catch(InsufficientParametersException e) {
			System.out.println("\n" + e.getMessage("doarPet <cachorro:gato:hamster:passaro>"));
		}
	}
	
	public void verPerfil() {
		System.out.println(visao.verPerfil((Usuario) usuarioAtual, pets));
	}
	
	public void verAdmin(){
		System.out.println("\n" + visao.verAdmin(admin));
	}
	
	public void inicializarRepositorio() {
		cadastrar("gabriel15", "1501", "Gabriel");
        cadastrar("NAT4L1A", "hgft", "Natalia");
        cadastrar("carlos23", "casd01", "Carlos");
        cadastrar("albert13", "lu57", "Alberto");
        cadastrar("ferdns47", "lol34", "Fernanda");
        addAdocao(new Cachorro("Max", 1, true, 40, 15, "cinza", "médio", "longa", "nasceu na clínica de adoção, filhote de uma cadela que foi achada na rua"));
        addAdocao(new Gato("Nyan", 2, false, 30, 10, "preto", "pequeno", "nyan-nyan"));
        addAdocao(new Hamster("Darwin", 1, true, 15, 3, "laranja", "G-Force"));
        addAdocao(new Passaro("Rio", 1, false, 20, 3, "azul", " "));
	}
	
}
