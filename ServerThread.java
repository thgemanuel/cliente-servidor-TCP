import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Thiago Emanuel Silva Antunes Lopes
 */
public class ServerThread {
    
    private ArrayList<String> log;
    private String connectionID;
    
    public ServerThread() {
        setLog(new ArrayList<String>());
        setConnectionID("");
    }

    /**
     * @return the log
     */
    public ArrayList<String> getLog() {
        return log;
    }

    /**
     * @param log the log to set
     */
    public void setLog(ArrayList<String> log) {
        this.log = log;
    }

    /**
     * @return the connectionID
     */
    public String getConnectionID() {
        return connectionID;
    }

    /**
     * @param connectionID the connectionID to set
     */
    public void setConnectionID(String connectionID) {
        this.connectionID = connectionID;
    }
    
    
    
    private void writeLog() {
        System.out.println("O log da conexão: " + getConnectionID());
        System.out.println("É: ");
        for(String s : log)
            System.out.println("\t" + s);
    }
    


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("servidor inicializado");
        
        int port = 60000;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            
            while(true) {
                System.out.println("Esperando cliente");
                Socket clientSocket = serverSocket.accept();
                //fica bloqueado até cliente conectar
                
                Thread myThread = new Thread() {
                    
                    @Override
                    public void run() {
                        System.out.println("Cliente conectou");
                        
                        ServerThread connectionInfo = new ServerThread();
                        
                        connectionInfo.setConnectionID(clientSocket.toString());
                        try {
                            connectionInfo.clientHandler(clientSocket);
                        }catch(Exception e){
                            System.out.println("Erro no tratamento do cliente.");
                            System.out.println(e.toString());
                        }
                    }
                };
                myThread.start();
            }
        } catch (Exception e) {
            System.out.println("Erro no servidor");
            System.out.println(e.toString());
        }
    }
    
    private void clientHandler(Socket clientSocket) throws IOException {
        String userLine = "";
        
        System.out.println("Client Handler chamado");
        
        //canal de passagem de informação do servidor para o cliente
        DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
        
        //canal de passagem de informação do cliente para o servidor
        InputStream inputStream = clientSocket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        //genérico - estamos apenas estabelecendo canais de comunicação
        
        //à partir daqui temos comunicação estabelecida - 
        //tratamento específico dependendo do tipo de servidor começa aqui

        ArrayList<String> palavras = new ArrayList<String>();
        palavras.add("Segredoum");
        palavras.add("Segredodois");
        palavras.add("Segredotres");
        palavras.add("Segredoquatro");
        palavras.add("Segredocinco");
        
        Random escolherpalavra = new Random();
        int aleatorio = escolherpalavra.nextInt(5);
        String palavra = palavras.get(aleatorio);
        ArrayList<Integer> vetorprimos = new ArrayList<>();
        int[] primos = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101,103,107,109,113,127,131,137,139,149,151,157,163,167,173,179,181,191,193,197,199,211,223,227,229,233,239,241,251,257,263,269,271,277,281,283,293,307,311,313,317,331,337,347,349,353,359,367,373,379,383,389,397,401,409,419,421,431,433,439,443,449,457,461,463,467,479,487,491,499};
        for(int it : primos)	
            vetorprimos.add(it);
        
        ArrayList<Integer> resultfatoracao = new ArrayList<>();
        Random escolhernumero = new Random();
        int numero = escolhernumero.nextInt(500);
        int numeroaserfatorado = numero;
        int divisor = vetorprimos.get(0);
        int i=0,count=0;
        Boolean resposta = false;
        while(numeroaserfatorado!=1)	
        {
            while(numeroaserfatorado%divisor==0)
            {
                numeroaserfatorado = numeroaserfatorado / divisor;
                count++;
            }			
            if(count>0){	
                resultfatoracao.add(divisor);
                resultfatoracao.add(count);
            }
            i++;
            divisor=vetorprimos.get(i);
            count=0;
        } 
        while( ! userLine.equals("0") && resposta==false) {
            System.out.println("Enviando comandos ao cliente.");
            System.out.println("Numero a ser fatorado:"+ numero);
            //outputStream.writeBytes("\n");
            outputStream.writeBytes(numero+"\n");
            //outputStream.writeBytes("\n");
            outputStream.writeBytes("Escreva a fatoracao do numero dando espacos entre o primo e a exponenciacao:"+"\n");           
            userLine = bufferedReader.readLine();
            if(! userLine.equals("0")){
                String[] item = userLine.split(" ");
                int[] fatoracliente = new int[500];
                for(int j=0;j<item.length;++j)
                    fatoracliente[j] = Integer.parseInt(item[j]);
                
                count=0;
                    for(int j=0;j<resultfatoracao.size() && j<fatoracliente.length;j++){
                        if(fatoracliente[j] == resultfatoracao.get(j)){
                            count++;				
                        } 
                    }
                    if(count==resultfatoracao.size()){
                        outputStream.writeBytes("Palavra secreta:" + palavra+"\n");
                        userLine = "0";
                        outputStream.writeBytes("1"+"\n");
                        resposta = true;
                        break;
                    }
                    else{
                        outputStream.writeBytes("Fatoração esta incorreta"+"\n");
                        outputStream.writeBytes("0"+"\n");
                    }
                    System.out.println("Cliente escreveu: " + userLine);
                    log.add(userLine);
            }
            else{
                outputStream.writeBytes("cliente digitou 0"+"\n");
                outputStream.writeBytes("0"+"\n");
                break;
            }
            System.out.println("Fim");
            writeLog();
        }       
    }
}
