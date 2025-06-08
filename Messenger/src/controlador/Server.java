package controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public int porta;
	public String ip;
	public String mensagem_s;
	public String mensagem_c;
	public BufferedReader in;
	public PrintWriter out;
	public ServerSocket serverSocket;
	public Socket clienteSocket;
	
	public Server(String porta) {
		
		this.porta = Integer.parseInt(porta);
		
		new Thread(()->{
			try {
				
				serverSocket = new ServerSocket(this.porta);
				clienteSocket = serverSocket.accept();
				
				in = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
				out = new PrintWriter(clienteSocket.getOutputStream(), true);
				
				new Thread(() -> {
					String mensagem;
					try {
						while ((mensagem = serverReceba()) != null) {
							ControladorPrincipal.panelP.criarMensagemRecebida(mensagem);
        					ControladorPrincipal.panelP.repaint();
						}
					} catch (Exception e) {
					}
				}).start();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}).start();
		
	}
	
	public void serverSend(String msgS) {
		out.println(msgS);
	}
	
	public String serverReceba() {
		try {
	        String mensagem = in.readLine();
	        return mensagem;
	    } catch (java.net.SocketException e) {
	        closeServer(); // Fecha o servidor ao detectar o erro
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public void closeServer() {
		try {
			clienteSocket.close();
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Main.f.dispose();
	}

}
