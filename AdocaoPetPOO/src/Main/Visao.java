package Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

import Excecoes.CadastradosNullException;
import Modelo.PETS.Cachorro;
import Modelo.PETS.Gato;
import Modelo.PETS.Hamster;
import Modelo.PETS.Passaro;
import Modelo.PETS.Pet;
import Modelo.USUARIOS.Admin;
import Modelo.USUARIOS.Usuario;

public class Visao {
	
	public String mostrarCatalogo(HashMap<Pet, Usuario> hm) {
		String output = new String();
		boolean disponivel = false;
		if(hm.size() > 0) {
			for(Entry<Pet, Usuario> p : hm.entrySet()) {
				if(p.getValue() == null) {
					if(disponivel) output += "\n";
					output += p.getKey();
					disponivel = true;
				}
			}
		}
		if(disponivel) return output;
		else return "\n-> Nenhum pet disponível para adoção!";
	}
	
	public String mostrarCatalogo(HashMap<Pet, Usuario> hm, String filtro) {
		String output = new String();
		boolean disponivel = false;
		switch(filtro) {
			case "cachorro":
				if(hm.size() > 0) {
					for(Entry<Pet, Usuario> p : hm.entrySet()) {
						if(p.getValue() == null && p.getKey() instanceof Cachorro) {
							output += p.getKey();
							disponivel = true;
						}
					}
				}
				break;
			case "gato":
				if(hm.size() > 0) {
					for(Entry<Pet, Usuario> p : hm.entrySet()) {
						if(p.getValue() == null && p.getKey() instanceof Gato) {
							output += p.getKey();
							disponivel = true;
						}
					}
				}
				break;
			case "hamster":
				if(hm.size() > 0) {
					for(Entry<Pet, Usuario> p : hm.entrySet()) {
						if(p.getValue() == null && p.getKey() instanceof Hamster) {
							output += p.getKey();
							disponivel = true;
						}
					}
				}
				break;
			case "passaro":
				if(hm.size() > 0) {
					for(Entry<Pet, Usuario> p : hm.entrySet()) {
						if(p.getValue() == null && p.getKey() instanceof Passaro) {
							output += p.getKey();
							disponivel = true;
						}
					}
				}
				break;
		}
		if(disponivel) return output;
		else return "\n-> Nenhum pet desse tipo disponível para adoção!";
	}
	
	public String mostrarPets(HashMap<Pet, Usuario> hm) {
		String output = new String();
		boolean disponivel = false;
		if(hm.size() > 0) {
			for(Entry<Pet, Usuario> p : hm.entrySet()) {
				if(p.getValue() != null) {
					output += p.getKey() + "- Adotado por: " + p.getValue().getNome() + " em " + p.getKey().getDataDeAdocao() + "\n";
					disponivel = true;
				} else {
					output += p.getKey();
					disponivel = true;
				}
			}
		}
		if(disponivel) return output;
		else return "-> Nenhum pet foi registrado ainda!";
	}
	
	public String mostrarAdotados(HashMap<Pet, Usuario> hm) {
		String output = new String();
		boolean disponivel = false;
		if(hm.size() > 0) {
			for(Entry<Pet, Usuario> p : hm.entrySet()) {
				if(p.getValue() != null) {
					output += p.getKey() + "- Adotado por: " + p.getValue().getNome() + " em " + p.getKey().getDataDeAdocao() + "\n";
					disponivel = true;
				}
			}
		}
		if(disponivel) return output;
		else return "-> Nenhum pet foi adotado ainda!";
	}
	
	public String mostrarUsuarios(ArrayList<Usuario> cadastrados) throws CadastradosNullException {
		if (cadastrados != null) {
			Collections.sort(cadastrados);
			for(Usuario autenticavel:cadastrados) {
				System.out.print("\n" + autenticavel);
			}
			return "";
		} throw new CadastradosNullException();
	}
	
	public String verAdmin(Admin admin){
		return admin.toString();
	}
	
	public String verPerfil(Usuario user, HashMap<Pet, Usuario> hm) {
		String output = new String();
		
		System.out.print("\n" + user);
		if(hm.containsValue(user)) {
			output += "\nPets adotados: \n";
			for(Entry<Pet, Usuario> p: hm.entrySet()) {
				if(p.getValue() == user)
					output += "\n" + p.getKey() + "- Data de adoção: " + p.getKey().getDataDeAdocao();
			}
		}
		return output;
	}
	
}
