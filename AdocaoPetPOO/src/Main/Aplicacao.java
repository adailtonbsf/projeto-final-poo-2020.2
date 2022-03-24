package Main;

import java.util.Scanner;

import Excecoes.InsufficientParametersException;
import Excecoes.InvalidPetNameException;
import Modelo.USUARIOS.Admin;
import Modelo.USUARIOS.Usuario;

public class Aplicacao {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		String[] comando = null;
		Controle controle = new Controle();
		
		int estagio = 0; //Variavel controladora dos estagios

		do{ //Inicio do laço que envolve tudo.
//=======================================================================================
			do{ //Laço do estagio inicial(Login/cadastro).
				if(estagio == 0){
					System.out.println("Digite qual opção você deseja (sem aspas):\n"
					+ "-> 'login <login> <senha>' (Realizar login na conta)\n"
					+ "-> 'cadastrar <login> <senha> <nome>' (Ir para menu de cadastro)\n"
					+ "-> 'encerrar' (Encerra a execução do programa)");
					
					comando = scanner.nextLine().split(" ");
					
					switch(comando[0]){
						case "login": //Fazer login
						try {
							if(controle.parametrosSuficientes(comando, 3)) 
								if(controle.login(comando[1], comando[2])) estagio = 1;
								else System.out.println("\nfail: login ou senha inválidos\n");
						} catch (InsufficientParametersException e) {
							System.out.println("\n" + e.getMessage("login <login> <senha>") + "\n");
						}
							break;
							
						case "cadastrar": //Cadastrar-se
							try {
								if(controle.parametrosSuficientes(comando, 4)) {
									System.out.println("-> Confirme a senha:");
									if(controle.confirmarSenha(comando[2], scanner.nextLine().split(" ")[0])) {
										String nome = comando[3];
										for(int i = 4; i < comando.length; i++) nome += " " + comando[i];
										if(!controle.cadastrar(comando[1], comando[2], nome)) System.out.println("\nfail: login já existente!\n");
										System.out.println("\n-> Cadastrado com sucesso!\n");
									} else System.out.println("\nfail: senha inválida!\n");
								}
							} catch (InsufficientParametersException e) {
								System.out.println("\n" + e.getMessage("cadastrar <login> <senha> <nome>"));
							}
							break;
							
						case "encerrar": //Encerrar
							System.out.print("\n-> Programa encerrado!");
							break;
							
						default: //Opção inválida
							System.out.println("\n-> O comando '" + comando[0] + "' não é um comando válido, tente novamente:\n");
					}
				}
			}while(estagio == 0 && !comando[0].equalsIgnoreCase("encerrar"));
//=======================================================================================
			do{ //Estagio secundario(Após o login).
				
				if(estagio == 1){
					System.out.print("\nBem vindo(a) " + ((controle.getUsuarioAtual() instanceof Usuario) ? 
							((Usuario) controle.getUsuarioAtual()).getNome():"Admin") + ", ");
					
					if(controle.getUsuarioAtual() instanceof Admin){ 	//Logado como admin
						
						System.out.println("digite qual opção você deseja (sem aspas):\n"
								+ "-> 'addPet <cachorro:gato:hamster:passaro>' (Adiciona um pet para a adoção)\n"
								+ "-> 'editarPet <nomeDoPet> <nome:descricao:altura:peso:idade> <novaInfo>' (Edita a informação de um pet)\n"
								+ "-> 'mostrarPets' (Mostra todos os pets)\n"
								+ "-> 'mostrarCatalogo' (Mostra os pets disponíveis para adoção)\n"
								+ "-> 'mostrarAdotados' (Mostra os pets adotados)\n"
								+ "-> 'mostrarUsuarios' (Mostra os usuarios cadastrados no sistema)\n"
								+ "-> 'verAdmin' (Mostra os dados do admin)\n"
								+ "-> 'trocarSenha <senhaAtual> <pin> <novaSenha>' (troca a senha do admin)\n"
								+ "-> 'trocarPin <pinAtual> <novoPin>' (troca a senha do admin)\n"
								+ "-> 'inicializar' (adiciona alguns usuarios e pets genéricos) \n"
								+ "-> 'sair' (Desloga da conta atual)\n"
								+ "-> 'encerrar' (Encerra a execução do programa)");
						comando = scanner.nextLine().split(" ");
						switch(comando[0]){
							case "inicializar":
								controle.inicializarRepositorio();
								break;
							case "addPet":
								controle.doarPet(comando, scanner);
								break;
							case "editarPet":
								controle.editarPet(comando);
								break;
							case "mostrarCatalogo": //Mostra os pets disponíveis para adoção
								controle.mostrarCatalogo();
								break;
							case "mostrarPets": //Mostra os pets disponíveis para adoção
								controle.mostrarPets();
								break;
							case "mostrarAdotados": //Mostra os pets disponíveis para adoção
								controle.mostrarAdotados();
								break;
							case "mostrarUsuarios": //Mostra os usuarios cadastrados
								controle.mostrarUsuarios();
								break;
							case "verAdmin": //Mostra os dados do admin
								controle.verAdmin();
								break;
							case "trocarSenha": // troca a senha do admin
              					try{
              						controle.trocarSenhaA(comando, scanner);
              					} catch (InsufficientParametersException e){
              						System.out.println("\n" + e.getMessage("trocarSenha <senhaAtual> <pin> <novaSenha>"));
    							}
              					break;
							case "trocarPin": // troca a senha do admin
								controle.trocarPin(comando, scanner);
              					break;
							case "sair": //Sair da conta
								controle.setUsuarioAtual(null);
								System.out.println("\n-> Você saiu da conta!\n");
								estagio = 0;
								break;
							case "encerrar": //Encerrar
								System.out.print("\n-> Programa encerrado!");
								break;
							default: //Opção inválida
								System.out.println("\n-> O comando '" + comando[0] + "' não é um comando válido, tente novamente:");
						}
						
					} else { //Logago como usuário normal
						
						System.out.println("digite qual opção você deseja (sem aspas):\n"
								+ "-> 'adotar <nomeDoPet>' (Adota um pet)\n"
								+ "-> 'doarDinheiro <quantia>' (Doa uma quantia em dinheiro)\n"
								+ "-> 'doarPet <cachorro:gato:hamster:passaro>' (Doa um pet para a adoção)\n"
								+ "-> 'devolver <nomeDoPet>' (Devolve um pet adotado)\n"
								+ "-> 'mostrarCatalogo ou"
									+ " mostrarCatalogo <cachorro:gato:hamster:passaro>' (Mostra os pets disponíveis para adoção)\n"
								+ "-> 'perfil' (Ver informações do usuário)\n"
								+ "-> 'trocarSenha <login> <senhaAtual> <novaSenha>' (troca a senha do usuario atual)\n"
								+ "-> 'sair' (Desloga da conta atual)\n"
								+ "-> 'encerrar' (Encerra a execução do programa)");
						comando = scanner.nextLine().split(" ");
						switch(comando[0]){
							case "doarDinheiro": //Doar dinheiro
								controle.doarDinheiro(comando);
								break;
							case "perfil": //Visualizar dados do usuário
								controle.verPerfil();
								break;
							case "adotar": //Adota um pet
								if(controle.adotar(comando))
									System.out.println("\n-> Pet adotado com sucesso");
								break;
							case "doarPet": //Doar um pet para a adoção
								controle.doarPet(comando, scanner);
								break;
							case "devolver": //Devolve um pet para a adoção
								try {
									if(controle.devolverPet(comando, scanner))
										System.out.println("\n-> Pet devolvido com sucesso");
								} catch (InvalidPetNameException e) {
									System.out.println("\n" + e.getMessage());
								}
								break;
							case "mostrarCatalogo": //Mostra os pets disponíveis para adoção
								if(comando.length == 1) {
									controle.mostrarCatalogo();
								} else {
									controle.mostrarCatalogo(comando);
								}
								break;
							case "trocarSenha": //trocar a senha do usuario atual
	              				try{
	              					controle.trocarSenhaU(comando, scanner);
	              				}catch (InsufficientParametersException e){
	      							System.out.println("\n" + e.getMessage("trocarSenha <login> <senhaAtual> <novaSenha>"));
	    						}
								break;
							case "sair": //Sair da conta
								controle.setUsuarioAtual(null);
								System.out.println("\n-> Você saiu da conta!\n");
								estagio = 0;
								break;
							case "encerrar": //Encerrar
								System.out.print("\n-> Programa encerrado!");
								break;
							default: //Opção inválida
								System.out.println("\n-> O comando '" + comando[0] + "' não é um comando válido, tente novamente:");
						}
					}
				}
			}while(estagio == 1 && !comando[0].equalsIgnoreCase("encerrar"));
//=======================================================================================
		}while((estagio == 0 && !comando[0].equalsIgnoreCase("encerrar")) 
				|| (estagio == 1 && !comando[0].equalsIgnoreCase("encerrar"))); //Fim do laço que envolve tudo.
		
		scanner.close();	
	}

}