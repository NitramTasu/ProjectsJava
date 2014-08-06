
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmiproject.Jogador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Martin Schwans
 */
public class Game {

    String resposta;
    Scanner scanner;
    String usuario;
    Jogador jogador;
    String mensagem;

    public Game() {
        scanner = new Scanner(System.in);
    }

    public void connectToServer(InterfaceServicos i) {
        try {
            System.out.println("Informe o nome de usuario:");
            usuario = scanner.nextLine();
            jogador = new Jogador(usuario);
            resposta = i.conectar(jogador);

            if (resposta.contains("Bem-vindo")) {
                startGame(i);
            } else {
                System.out.println("Usuario nao cadastrado!");
            }

        } catch (RemoteException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void startGame(InterfaceServicos i) {
        String opcao;
        do {

            System.out.println("---Menu---\n1-Jogar\n2-Mostrar Jogadores\n3-Enviar Mensagem\n4-Receber Mensagem\n5-Sair\nQual é a opção?");
            opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    try {
                        resposta = i.jogar();
                        System.out.println(resposta);
                    } catch (RemoteException ex) {
                        System.out.println("Deu pau:" + ex);
                    }
                    break;
                case "2":
                    try {
                        resposta = i.mostraJogadores();
                    } catch (RemoteException ex) {
                        Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println(resposta);
                    break;
                case "3":
                    try {
                        System.out.println("Qual eh a  mensagem?");
                        mensagem = scanner.nextLine();
                        resposta = i.enviarMensagem(jogador,mensagem);
                    } catch (RemoteException ex) {
                        Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println(resposta);
                    break;
                case "4":
                    try {
                        resposta = i.receberMensagem();
                    } catch (RemoteException ex) {
                        Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("\nA mensagem foi:\n"+resposta+"\n");
                    break;
                case "5":
                    try {
                        i.sairJogador(jogador);
                    } catch (RemoteException ex) {
                        Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("Ate a proxima!");
                    break;
            }

        } while (!opcao.equals("5"));

    }

}
