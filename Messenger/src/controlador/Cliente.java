package controlador;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

import modelo.MyFile;

public class Cliente {
	
	public String ip;
    public int porta;
    public Socket clienteSocket, clienteSocketFile;
    public BufferedReader in;
    public PrintWriter out;
    public DataOutputStream dtOutputStream;
    public FileInputStream fileInputStream;
    public File arquivo;
    public boolean fechou;
    
    public Cliente(String ip, String porta) {
        this.ip = ip;
        this.porta = Integer.parseInt(porta);
        fechou = false;
        
        // THREAD DE MENSAGENS DO CLIENTE
        new Thread(()->{
        	try {//						   this.ip
        		clienteSocket = new Socket(this.ip, this.porta); // INICIA O SOCKET
        		
        		in = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream())); // CRIA O FLUXO DE RECEBIMENTO DE TEXTO
        		out = new PrintWriter(clienteSocket.getOutputStream(), true); // CRIA O FLUXO DE ENVIO DE TEXTO
        		
        		// THREAD PRA VERIFICAR CHEGADA DE MENSAGEM
        		new Thread(() -> {
        			String mensagem;
        			try {
        				while ((mensagem = clienteReceba()) != null) {
        					ControladorPrincipal.panelP.criarMensagemRecebida(mensagem); // CRIA A MENSAGEM NO PANEL
        					ControladorPrincipal.panelP.repaint();
        				}
        			} catch (Exception e) {
        				e.printStackTrace();
        			}
        			closeCliente();
        		}).start();
        		
        	} catch (IOException e) {
        		e.printStackTrace();
        	}
        	
        }).start();
        
        // THREAD DE ARQUIVOS DO CLIENTE
        new Thread(()->{
        	try {//						   this.ip
        		clienteSocketFile = new Socket(this.ip, this.porta+1);
        		
                dtOutputStream = new DataOutputStream(clienteSocketFile.getOutputStream()); //envia arquivo ao servidor
        		
        		// THREAD PRA VERIFICAR CHEGADA DE ARQUIVO
        		new Thread(() -> {
        			while(!fechou) {
        				try {
        					DataInputStream dataInputStream = new DataInputStream(clienteSocketFile.getInputStream());  //Pega dados enviados pelo Server

        					int fileNameLength = dataInputStream.readInt();    //Recebe o tamanho do arquivo enviado

        					if (fileNameLength > 0) {
        						byte[] fileNameBytes = new byte[fileNameLength];
        						dataInputStream.readFully(fileNameBytes, 0, fileNameBytes.length);      //le o tamanho do arquivo
        						String fileName = new String(fileNameBytes);

        						int fileContentLength = dataInputStream.readInt();

        						if (fileContentLength > 0) { 
        							byte[] fileContentBytes = new byte[fileContentLength];
        							dataInputStream.readFully(fileContentBytes, 0, fileContentLength);  //le o conteudo do arquivo
        							
        							// TRANSFORMA OS DADOS EM UM ARQUIVO
        							MyFile arquivoRecebido = new MyFile(fileName, fileContentBytes);
        			                
        			                ControladorPrincipal.panelP.criarArquivoRecebido(arquivoRecebido);
                					ControladorPrincipal.panelP.repaint();
        						} else {
        			                System.out.println("O tamanho do conteúdo do arquivo é inválido: " + fileContentLength);
        			            }
        					}
        				} catch (SocketException e) {
        					closeCliente();
        					e.printStackTrace();
        				} catch (IOException error) {
        					closeCliente();
        					error.printStackTrace();
        				}
        			}
        		}).start();
        		
        	} catch (IOException e) {
        		e.printStackTrace();
        	}
        	
        }).start();
    }
    
    // TESTA SE A CONEXÃO FUNCIONOU
    public boolean isConnected() {
    	return clienteSocket != null && clienteSocket.isConnected();
    }

    // ENVIA MENSAGEM DE TEXTO
    public void clienteSend(String msg) {
        out.println(msg);
    }
    
    // ENVIA ARQUIVO
    public void clienteSendFile(File arquivo) {
    	try {
    		fileInputStream = new FileInputStream(arquivo.getAbsolutePath()); //acessa caminho do arquivo
            String fileName = arquivo.getName();   //manda o nome do arquivo
            byte[] fileNameBytes = fileName.getBytes();      //Transforma o String 'fileName' em um array de bytes

            byte[] fileContentBytes = new byte[(int) arquivo.length()]; //Manda o tamanho exato do arquivo
            fileInputStream.read(fileContentBytes);          //pega o conteudo do arquivo

            dtOutputStream.writeInt(fileNameBytes.length);   //tamanho do arquivo
            dtOutputStream.write(fileNameBytes);

            dtOutputStream.writeInt(fileContentBytes.length);  //conteudo do arquivo em ASCII
            dtOutputStream.write(fileContentBytes);
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
    
    // RECEBE MENSAGEM DE TEXTO
    public String clienteReceba() {
		try {
	        String mensagem = in.readLine();
	        return mensagem;
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
    // FECHA O CLIENTE
    public void closeCliente() {
		try {
			clienteSocket.close();
			clienteSocketFile.close();
			fechou = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		Main.f.dispose();
	}
}
