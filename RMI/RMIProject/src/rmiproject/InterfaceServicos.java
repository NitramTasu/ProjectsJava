
/**
 * @(#)InterfaceServico.java
 *
 *
 * @author
 * @version 1.00 2013/11/2
 */
import java.rmi.Remote;
import java.rmi.RemoteException;
import rmiproject.Jogador;

public interface InterfaceServicos extends Remote {

    public int soma(int x, int y) throws RemoteException;

    public String conectar(Jogador usuario) throws RemoteException;

    public String jogar() throws RemoteException;

    public String enviarMensagem(Jogador usuario,String mensagem) throws RemoteException;
    
    public String mostraJogadores() throws RemoteException;
    
    public void sairJogador(Jogador usuario) throws RemoteException;
    
    public String receberMensagem() throws RemoteException;

}
