
/**
 * @(#)AppCliente.java
 *
 *
 * @author
 * @version 1.00 2013/11/2
 */
import java.rmi.*;
import java.util.Scanner;

public class AppCliente {

    public static void main(String args[]) {
        try {
            InterfaceServicos i = (InterfaceServicos) Naming.lookup("rmi://localhost:1099/ObjetoServico");
            Game game =  new Game();
            game.connectToServer(i);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            System.out.println("Causa: " + e.getCause());
        }
        
    }
    
}
