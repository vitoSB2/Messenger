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
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import modelo.MyFile;

public class Server {
	public int porta;
	public String ip, mensagem_s, mensagem_c;
	public BufferedReader in;
	public PrintWriter out;
	public ServerSocket serverSocket, serverSocketFile;
	public Socket clienteSocket, clienteSocketFile;
    public DataOutputStream dtOutputStream;
    public FileInputStream fileInputStream;
    public File arquivo;
    public boolean fechou;
	
	public Server(String porta) {
		
		this.porta = Integer.parseInt(porta);
		fechou = false;
		
		// THREAD DE MENSAGENS DO SERVIDOR
		new Thread(()->{
			try {
				
				serverSocket = new ServerSocket(this.porta); // INICIA O SERVER
				clienteSocket = serverSocket.accept(); // CRIA O SOCKET COM O CLIENTE
				
				in = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream())); // CRIA O FLUXO DE RECEBIMENTO DE TEXTO
				out = new PrintWriter(clienteSocket.getOutputStream(), true); // CRIA O FLUXO DE ENVIO DE TEXTO
				
				new Thread(() -> {
					String mensagem;
					try {
						while ((mensagem = serverReceba()) != null) {
							ControladorPrincipal.panelP.criarMensagemRecebida(mensagem); // CRIA A MENSAGEM NO PANEL
        					ControladorPrincipal.panelP.repaint();
						}
					} catch (Exception e) {
					}
				}).start();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}).start();
		
		// THREAD DE ARQUIVOS DO SERVIDOR
        new Thread(()->{
        	try {
        		serverSocketFile = new ServerSocket(this.porta+1);
        		clienteSocketFile = serverSocketFile.accept();
        		
                dtOutputStream = new DataOutputStream(clienteSocketFile.getOutputStream()); //envia arquivo ao servidor
        		
        		// THREAD PRA VERIFICAR CHEGADA DE ARQUIVO
        		new Thread(() -> {
        			while(!fechou) {
        				try {
        					DataInputStream dataInputStream = new DataInputStream(clienteSocketFile.getInputStream());  //Pega dados enviados pelo Cliente

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
        					closeServer();
        					e.printStackTrace();
        				} catch (IOException error) {
        					closeServer();
        					error.printStackTrace();
        				}
        			}
        		}).start();
        		
        	} catch (IOException e) {
        		e.printStackTrace();
        	}
        	
        }).start();
	}
	
	// MANDA MENSAGEM DE TEXTO
	public void serverSend(String msgS) {
		if(out != null)
    		out.println(msgS);
    	else 
    		System.out.println("Erro ao enviar mensagem: conexão não estabelecida ou fechada.");
	}
	
	// MANDA ARQUIVOS
	public void serverSendFile(File arquivo) {
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
	
	// FECHA O SERVIDOR
	public void closeServer() {
		try {
			clienteSocket.close();
			serverSocket.close();
			clienteSocketFile.close();
			serverSocketFile.close();
			fechou = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		Main.f.dispose();
	}

}
