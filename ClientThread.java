import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author Thiago Emanuel Silva Antunes Lopes
 */
public class ClientThread {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String port = "60000";
        //String ip = "localhost";
        String ip = "127.0.0.1";
        
        //Saida para o cliente feita por deafult no Java somo o Ssytem.out
        
        //Entrada do cliente feita pelo System.in + BufferedReader
        BufferedReader fromUser = new BufferedReader(new InputStreamReader(System.in));
        
        //Saida para o servidor
        DataOutputStream toServer;
        
        //Entrada do servidor
        BufferedReader fromServer;
        
        String phraseFromServer = "";
        String phraseFromUser = "";
        String resultadoresposta = "0";
        
        try {
            System.out.println("criando socket");
            
            Socket clientSocket = new Socket(ip, Integer.parseInt(port));
            
            System.out.println("conectou");
            
            toServer = new  DataOutputStream(clientSocket.getOutputStream());
            fromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            //casando o comportamento do servidor e do cliente
            while( ! phraseFromUser.equals("0") && resultadoresposta!="1") {
                //fazedo a comunicação
                
                System.out.println("Esperando comando do servidor");
                phraseFromServer = fromServer.readLine();
                System.out.println("Comando do servidor recebido: " + phraseFromServer);
                phraseFromServer = fromServer.readLine();
                System.out.println(phraseFromServer);

                
                phraseFromUser = fromUser.readLine();
                toServer.writeBytes(phraseFromUser + "\n");
                phraseFromServer = fromServer.readLine();
                System.out.println(phraseFromServer);
                resultadoresposta = fromServer.readLine();
            }
            clientSocket.close();
        } catch(Exception e) {
            System.out.println("erro no Cliente");
            System.out.println(e.toString());
        }
        
    }
    
}
