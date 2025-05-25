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
				System.out.println("Server On! Porta: " + Integer.toString(serverSocket.getLocalPort()) + " IP: " + serverSocket.getInetAddress());
				
				clienteSocket = serverSocket.accept();
				
				String clienteIP = clienteSocket.getInetAddress().toString();
				System.out.println("Cliente logado! || " + clienteIP);
				
				in = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
				out = new PrintWriter(clienteSocket.getOutputStream(), true);
				
				new Thread(() -> {
					String mensagem;
					try {
						while ((mensagem = serverReceba()) != null) {
							System.out.println("Mensagem do cliente: " + mensagem);
						}
					} catch (Exception e) {
						System.out.println("Conexão encerrada.");
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
	}

}
