package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import modelo.State;
import visual.Frame;
import visual.PanelConexao;
import visual.PanelPrincipal;

public class ControladorConexao implements ActionListener, KeyListener{
	
	PanelConexao panelC;
	PanelPrincipal panelP;
	ControladorPrincipal cp;
	Frame f;
	String ip, porta;
	public ControladorConexao(PanelConexao pc, Frame f) {
		this.f = f;
		panelC = pc;
		panelC.setFocusable(true);
		panelC.requestFocusInWindow();
		addEventos();
	}

	private void addEventos() {
		panelC.getEnter().addActionListener(this);
		panelC.getPorta().addKeyListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == panelC.getEnter()) {
			conectar();
		}
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			conectar();
		}
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	private void conectar(){
		ip = panelC.getIP().getText();
		porta = panelC.getPorta().getText();
			
		// SEM TRATAMENTO, POR ENQUANTO, PARA RECEBER IP/PORTA 100% CERTO
			
		// SE A CAIXA DO IP ESTIVER VAZIA, INICIA UM SERVER COM A PORTA DIGITADA
		// CASO CONTRÁRIO, INICIA CLIENTE E TENTA ENTRAR NUM SERVER COM O IP E PORTA DIGITADOS
		if(panelC.getIP().getText().isEmpty()) {
			Main.server = new Server(porta);
			State.state = State.SERVER;
				
			// SETA O IP PARA CONEXÃO COMO O DA MÁQUINA QUE VIRA SERVIDOR
			InetAddress ipLocal = null;
			try {
				ipLocal = InetAddress.getLocalHost();
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			}
			ip = ipLocal.getHostAddress();
				
		} else {
			Main.cliente = new Cliente(ip, porta);
			State.state = State.CLIENTE;
		}
		mudarPanel();
	}
	
	private void mudarPanel() {
		panelP = new PanelPrincipal();
		panelP.setIP(ip, panelC.getPorta().getText());
		f.setContentPane(panelP);
		f.repaint();
		f.revalidate();
		cp = new ControladorPrincipal(f, panelP);
	}

}
