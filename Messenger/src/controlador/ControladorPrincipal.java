package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import modelo.MyFile;
import modelo.State;
import visual.Frame;
import visual.PanelPrincipal;

public class ControladorPrincipal implements ActionListener, KeyListener{
	
	static PanelPrincipal panelP;
	Frame f;
	File arquivo;
	boolean importado;
	public ControladorPrincipal(Frame f, PanelPrincipal pp) {
		this.f = f;
		panelP = pp;
		panelP.setFocusable(true);
		panelP.requestFocusInWindow();
		addEventos();
	}

	private void addEventos() {
		panelP.getEnter().addActionListener(this);
		panelP.getAnexo().addActionListener(this);
		panelP.getTexto().addKeyListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == panelP.getEnter())
			enviar();

		// BOTÃO DE ANEXAR PARA SELECIONAR O ARQUIVO
		if(e.getSource() == panelP.getAnexo()) {
			// CRIA UMA ABA PARA A SELEÇÃO DE ARQUIVO
			JFileChooser fileChooser = new JFileChooser();
	        int resultado = fileChooser.showOpenDialog(null);
	        
	        // SE O ARQUIVO FOR SELECIONADO
	        if (resultado == JFileChooser.APPROVE_OPTION) {
	        	// GUARDA O ARQUIVO
	            arquivo = fileChooser.getSelectedFile();
	            byte[] tamanho = new byte[(int) arquivo.length()];
	            
	            // TESTA SE O ARQUIVO EXISTE E SE TEM ALGUM CONTEÚDO
	            if(arquivo.exists() && tamanho.length > 0) {
	    	        panelP.getTexto().setText(arquivo.getName());
					panelP.getTexto().setEnabled(false);
					importado = true;
	            } else if (!arquivo.exists()) {
					JOptionPane.showMessageDialog(null, "O Arquivo selecionado não existe!", "Aviso", JOptionPane.WARNING_MESSAGE);
	        	} else {
	            	JOptionPane.showMessageDialog(null, "O Arquivo está vazio!", "Aviso", JOptionPane.WARNING_MESSAGE);
	            }
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			enviar();
		}
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	// QUANDO O BOTÃO DE ENVIAR É PRESSIONADO
	private void enviar(){
		// SE TIVER COM UM ARQUIVO SELECIONADO
		if(importado){
			mandarArquivo();
		// SE NÃO TIVER NENHUM ARQUIVO SELECIONADO, MANDA MENSAGEM NORMAL
		} else if (panelP.getTexto().getText().isBlank()) {
			panelP.getTexto().setText("");
		}else {
			mandaMensagem();
			panelP.getTexto().setText("");
		}
	}
	
	// LÓGICA DE ENVIO DE MENSAGEM DE TEXTO
	private void mandaMensagem() {
		if (State.state == State.CLIENTE) {
			Main.cliente.clienteSend(panelP.getTexto().getText());
		} else if (State.state == State.SERVER) {
			Main.server.serverSend(panelP.getTexto().getText());
		}
		panelP.criarMensagemEnviada(panelP.getTexto().getText());
		f.repaint();
		f.revalidate();
	}
	
	// LÓGICA DE ENVIO DE ARQUIVOS
	private void mandarArquivo() {
		try {
	        byte[] fileContentBytes = new byte[(int) arquivo.length()];
	        FileInputStream fileInputStream = new FileInputStream(arquivo);
	        fileInputStream.read(fileContentBytes);
	        fileInputStream.close();

	        if (State.state == State.CLIENTE) {
	            Main.cliente.clienteSendFile(arquivo);
	        } else if (State.state == State.SERVER) {
	            Main.server.serverSendFile(arquivo);
	        }

	        panelP.criarArquivoEnviado(new MyFile(arquivo.getName(), fileContentBytes));
	        panelP.getTexto().setText("");
	        panelP.getTexto().setEnabled(true);
	        importado = false;
	        f.repaint();
	        f.revalidate();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
}