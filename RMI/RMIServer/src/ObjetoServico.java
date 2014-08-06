
/**
 * @(#)ObjetoServico.java
 *
 *
 * @author
 * @version 1.00 2013/11/2
 */
import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import rmiproject.Jogador;

public class ObjetoServico extends UnicastRemoteObject implements InterfaceServicos {
    ArrayList<Jogador> jogadoresList = new ArrayList<Jogador>();
    String mensagem;
    
    public ObjetoServico() throws RemoteException {
        super();
    }

    public int soma(int x, int y) throws RemoteException {
        return x + y;
    }

    public String conectar(Jogador usuario) throws RemoteException {
        jogadoresList.add(usuario);
        System.out.println("Usuário "+usuario+" conectado.");
        return "Bem-vindo " + usuario.getNome() + "!!!\n Voce esta conectado!!";
    }

    public String jogar() {
        return "Jogando!!";
    }

    public String enviarMensagem(Jogador usuario,String msg) {
        mensagem = usuario.getNome()+":"+msg;
        return "Mensagem recebida e sera analizada";
    }

    public String mostraJogadores() throws RemoteException {
        System.out.println("Mostrar todos os jogadores!");
        String lista = "Os jogadores online:\n";
        for(Jogador j : jogadoresList){
            lista += j.getNome()+"\n";
        }
        return lista;
    }

    public void sairJogador(Jogador usuario) throws RemoteException {
        
         for(Jogador j : jogadoresList){
             if (j.getNome().equals(usuario.getNome())) {
                 System.out.println(usuario+" saiu.");
                 jogadoresList.remove(j);
             }
        }
        
    }
    public String receberMensagem() throws RemoteException {
        return mensagem;
    }

}
