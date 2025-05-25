package controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
	
	public String ip;
    public int porta;
    public Socket clienteSocket;
    public BufferedReader in;
    public PrintWriter out;
    
    public Cliente(String ip, String porta) {
        this.ip = ip;
        this.porta = Integer.parseInt(porta);
        
        new Thread(()->{
        	try {//						   this.ip
        		clienteSocket = new Socket(this.ip, this.porta);
        		
        		in = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
        		out = new PrintWriter(clienteSocket.getOutputStream(), true);
        		
        		// THREAD PRA VERIFICAR CHEGADA DE MENSAGEM
        		new Thread(() -> {
        			String mensagem;
        			try {
        				while ((mensagem = clienteReceba()) != null) {
        					ControladorPrincipal.panelP.criarMensagemRecebida(mensagem);
        					ControladorPrincipal.panelP.repaint();
        				}
        			} catch (Exception e) {
        				e.printStackTrace();
        			}
        		}).start();
        		
        	} catch (IOException e) {
        		e.printStackTrace();
        	}
        	
        }).start();
    }

    public void clienteSend(String msg) {
        out.println(msg);
    }
    
    public String clienteReceba() {
		try {
	        String mensagem = in.readLine();
	        return mensagem;
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
    public void closeServer() {
		try {
			clienteSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
