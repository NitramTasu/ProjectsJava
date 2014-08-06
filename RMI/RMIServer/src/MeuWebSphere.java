
/**
 * @(#)MeuWebSphere.java
 *
 *
 * @author
 * @version 1.00 2013/11/2
 */
import java.rmi.*;

public class MeuWebSphere {

    public static void main(String args[]) {
        try {
            InterfaceServicos i = new ObjetoServico();
            Naming.rebind("rmi://localhost:1099/ObjetoServico", i);
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

}
