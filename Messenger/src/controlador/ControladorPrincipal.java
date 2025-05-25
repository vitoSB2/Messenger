package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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

		// CASO CLIQUE NO BOTÃO ANEXAR PARA SELECIONAR O ARQUIVO
		if(e.getSource() == panelP.getAnexo()) {
			// CRIA UMA ABA PARA A SELEÇÃO DE ARQUIVO
			JFileChooser fileChooser = new JFileChooser();
	        int resultado = fileChooser.showOpenDialog(null);
	        
	        // SE O ARQUIVO FOR SELECIONADO
	        if (resultado == JFileChooser.APPROVE_OPTION) {
	        	// GUARDA O ARQUIVO
	            arquivo = fileChooser.getSelectedFile();
				double tamanhoMB = arquivo.length() / (1024.0 * 1024.0);
	            
	            // TESTA SE O ARQUIVO EXISTE
	            if(arquivo.exists() && tamanhoMB < 1) {
	    	        panelP.getTexto().setText(arquivo.getName());
					panelP.getTexto().setEnabled(false);
					importado = true;
	            } else if (!arquivo.exists()){
					JOptionPane.showMessageDialog(null, "O Arquivo selecionado não existe!", 
					"Aviso", JOptionPane.WARNING_MESSAGE);
				// TESTA DE O ARQUIVO SELECIONADO É MAIOR QUE 1MB
				} else if (tamanhoMB > 1){
					JOptionPane.showMessageDialog(null, "O Arquivo selecionado é maior que 1MB!", 
					"Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
			enviar();
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	private void enviar(){
		// SE TIVER COM UM ARQUIVO SELECIONADO
		if(importado){
			panelP.criarArquivoEnviado(arquivo);
			panelP.getTexto().setText("");
			panelP.getTexto().setEnabled(true);
			importado = false;
		// SE NÃO TIVER NENHUM ARQUIVO SELECIONADO, MANDA MENSAGEM NORMAL
		} else if (panelP.getTexto().getText().isBlank())
			panelP.getTexto().setText("");
		else {
			mandaMensagem();
			panelP.getTexto().setText("");
		}
	}
	
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
	
}
