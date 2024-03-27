import java.net.*;
import java.io.*;

public class ThreadChatConnessioni extends Thread {
    private ThreadGestioneServizioChat gestoreChat;
    private Socket client = null;
    private Bufferedreader input = null;
    private PrintWriter output = null;
    Thread me;

    public ThreadChatConnessioni(ThreadGestioneServizioChat gestoreChat, Socket client) {
        this.gestoreChat = gestoreChat;
        this.client = client;

        try {
            this.input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.output = new PintWriter(this.client.getOutputStream(), true);
        } catch (Exception e) {
            System.out.println("Errore nella lettura");
        }
        me = new Thread(this);
        me.start();
    }

    public void run() {
            try {

                String mex = null;
                // rimango in attesa del messaggio mandato dal client
                while ((mex = input.readLine()) != null) {
					gestoreChat.spedisciMessaggio(mex);
                }
                // invoco il metodo del gestoreChat per ripetere a tutti il messaggio ricevuto
            } catch (IOException e) {
                System.out.println("Errore durante la chiusura della connessione: " + e.getMessage());
            }
        }
    }

    public void spedisciMessaggioChat(String messaggio) {
            output.println(messaggio);
    
    }
}