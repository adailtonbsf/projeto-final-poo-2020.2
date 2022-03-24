# <p align="center">**Programação Orientada à Objetos - 2020.2**</p>
## **Dupla:** Adailton Bezerra & Gabriel Bezerra
## **Data:** 06/04/2021
## **Professor(a):** Lívia Almada

---
### Obs.: Textos que estiverem entre “<>” significa que é um argumento para o comando.
---

# **➔ Menu Principal** 

<p align="center"> <img src="https://i.imgur.com/bXbuo22.png"/> </p>

### Neste menu inicial temos 3 opções:
## ➞ Login
    Nesta opção você pode realizar seu login tanto como usuário como admin. Para realizar o login basta digitar no console em uma linha única os 3 argumentos a seguir:
        • login (Isso indica que você quer executar o comando de login);
        • <login> (Informe o login do usuário que deseja logar. Caso deseje logar como administrador passe “admin” como parâmetro);
        • <senha> (Informe a senha do usuário que deseja logar. Caso deseje logar como administrador passe “40028922” como parâmetro).

## ➞ Cadastrar
    Nesta opção você poderá registrar um novo usuário no sistema. Para realizar o cadastro basta digitar no console em uma linha única os argumentos a seguir:
        • cadastrar (Isso indica que você quer executar o comando de cadastro);
        • <login> (Informe o login do usuário que deseja cadastrar);
        • <senha> (Informe a senha do usuário que deseja cadastrar);
        • <nome> (Informe o nome do usuário que deseja cadastrar).

    Obs.: Após a inserção desses dados, o programa irá solicitar que confirme a senha. Caso erre a senha, terá de informar os dados novamente.

## ➞ Encerrar
    Interrompe a execução do programa.

<p align="center"> <img src="https://i.imgur.com/ixuTkw6.png"/> </p>

---

# **➔ Menu secundário (Usuário)**

### Após feito o login, caso tenha logado como um usuário comum, o seguinte menu irá aparecer:

<p align="center"> <img src="https://i.imgur.com/MYAnDyu.png"/> </p>

### Neste menu temos as seguintes opções:

## ➞ Adotar
    Nesta opção o usuário poderá adotar um pet, informando o nome do pet o qual deseja adotar.
    (Ex.: adotar Princesa)

## ➞ Doar dinheiro
    Nesta opção o usuário poderá doar uma quantia em dinheiro.
    (Ex.: doarDinheiro 150.5)

## ➞ Doar pet
    Nesta opção o usuário poderá doar um pet para a adoção, para isso ele deverá informa qual tipo de pet ele deseja doar. 
    As informações pedidas podem variar dependendo do tipo de pet que deseja doar.
    (Ex.: doarPet cachorro)

## ➞ Devolver pet
    Nesta opção o usuário poderá devolver para a adoção um pet que ele adotou, para isso ele deverá informa qual o nome do pet adotado que deseja devolver;
    O programa irá solicitar uma confirmação de senha pra realizar o processo, caso a senha inserida seja incorreta o processo é interrompido.
    (Ex.: devolver Rex);

## ➞ Mostrar catálogo
    Nesta opção o usuário poderá visualizar quais pets estão disponíveis para a adoção, para isso basta digitar o comando “mostrarCatalogo” no console, ou caso deseje ver apenas pets de um tipo específico você pode informar qual tipo deseja ver (Ex.: mostrarCatalogo cachorro).

## ➞ Visualizar perfil
    Nesta opção o usuário poderá visualizar as informações do seu perfil, para isso basta digitar o comando “perfil” no console.
    Será informado: nome, login, senha, total doado e os pets adotados (caso tenha adotado algum).

## ➞ Trocar senha
    Nesta opção o usuário poderá alterar sua senha, para isso ele deverá informar o próprio login, senha atual e a nova senha desejada. Após inseridas essas informações o programa solicitará uma confirmação da nova senha para concluir o processo (Ex.: trocarSenha usuario1 123456789 987654321).

## ➞ Sair
    Nesta opção o usuário poderá deslogar da conta, voltando assim para o menu inicial, para isso basta digitar o comando “sair” no console.

## ➞ Encerrar
    Interrompe a execução do programa.

---

# **➔ Menu secundário (Administrador)**

### Após feito o login, caso tenha logado como um administrador, o seguinte menu irá aparecer:

<p align="center"> <img src="https://i.imgur.com/0xAgN3J.png"/> </p>

### Neste menu temos as seguintes opções:

## ➞ Adicionar pet
    Função que funciona semelhantemente a função “doarPet” do menu de usuário comum.

## ➞ Editar pet
    Nesta opção o administrador poderá editar algumas informações (nome, descrição, altura, peso e idade) de um pet específico que ainda não tenha sido adotado, para isso ele deverá informar o nome do pet, a informação que deseja alterar e a nova informação que será colocada
    (Ex.: editarPet Princesa idade 3). 
    
    Obs.: Em relação a idade, não será possível colocar uma idade inferior a idade atual do pet.

## ➞ Mostrar pets
    Nesta opção o administrador poderá ver uma lista com as informações de todos os pets registrados no sistema, para isso basta digitar o comando “mostrarPets” no console.

## ➞ Mostrar catálogo
    Nesta opção o usuário poderá visualizar quais pets estão disponíveis para a adoção, para isso basta digitar o comando “mostrarCatalogo” no console.

## ➞ Mostrar pets adotados
    Nesta opção o administrador poderá ver uma lista com as informações de todos os pets que já foram adotados, para isso basta digitar o comando “mostrarAdotados” no console.

## ➞ Mostrar usuários
    Nesta opção o administrador poderá ver uma lista com as informações de todos os usuários que já foram cadastrados, para isso basta digitar o comando “mostrarUsuarios” no console.

## ➞ Informações do administrador
    Nesta opção o administrador poderá ver as informações da conta admin, para isso basta digitar o comando “verAdmin” no console.

## ➞ Trocar senha
    Nesta opção o admin poderá alterar sua senha, para isso ele deverá informar a senha atual, o PIN (0000 por padrão) e a nova senha desejada.
    Após inseridas essas informações o programa solicitará uma confirmação da nova senha para concluir o processo.
    (Ex.: trocarSenha 40028922 0000 123456789)

## ➞ Trocar PIN
    Nesta opção o admin poderá alterar seu PIN, para isso ele deverá informar o pin atual (0000 por padrão) e o novo pin desejado.
    Após inseridas essas informações o programa solicitará uma confirmação do novo pin para concluir o processo.
    (Ex.: trocarPin 0000 1234)

## ➞ Iniciar repositório
    Nesta opção o admin poderá adicionar aos dados um repositório com alguns usuários e pets para facilitar os testes e a demonstração do funcionamento do programa, para isso basta digitar o comando “inicializar” no console.

## ➞ Sair
    Nesta opção o usuário poderá deslogar da conta, voltando assim para o menu inicial, para isso basta digitar o comando “sair” no console.

## ➞ Encerrar
    Interrompe a execução do programa.

---

# **➔ Responsáveis pela implementação das funcionalidades**

## ➞ Gabriel Bezerra
    • Modelar a interface autenticável e as classes usuário e administrador
    • Login
    • Cadastro
    • Doar dinheiro
    • Mostrar perfil
    • Trocar senhas

## ➞ Adailton Bezerra
    • Modelar a classe pet e suas subclasses
    • Doar pet para adoção
    • Adotar pet
    • Editar pet
    • Mostrar pets
    • Devolver pet
    • Trocar PIN

# <h1 align="center">**Diagrama UML**</h1>
<p align="center"> <img src="https://i.imgur.com/FJdlfIT.png"/> </p>