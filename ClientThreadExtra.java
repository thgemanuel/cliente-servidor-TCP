import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Thiago Emanuel Silva Antunes Lopes
 */
public class ClientThreadExtra {

    /**
     * @param args the command line arguments
     */
    public static String fatoranum(String numero){
        int numeroaserfatorado = Integer.parseInt(numero);
        String resp = "";
        ArrayList<Integer> vetorprimos = new ArrayList<>();
        int[] primos = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101,103,107,109,113,127,131,137,139,149,151,157,163,167,173,179,181,191,193,197,199,211,223,227,229,233,239,241,251,257,263,269,271,277,281,283,293,307,311,313,317,331,337,347,349,353,359,367,373,379,383,389,397,401,409,419,421,431,433,439,443,449,457,461,463,467,479,487,491,499};
        for(int it : primos)	
            vetorprimos.add(it);
        
        int divisor = vetorprimos.get(0);
        int i=0,count=0;
        while(numeroaserfatorado!=1)	
        {
            while(numeroaserfatorado%divisor==0)
            {
                numeroaserfatorado = numeroaserfatorado / divisor;
                count++;
            }			
            if(count>0){	
                resp=resp+divisor+" "+count+" ";
            }
            i++;
            divisor=vetorprimos.get(i);
            count=0;
        } 
        System.out.println(resp);
        return resp;
    }
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
                System.out.println("Numero a ser fatorado:" + phraseFromServer);
                phraseFromUser = fatoranum(phraseFromServer);
                
                phraseFromServer = fromServer.readLine();
                System.out.println(phraseFromServer);
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
