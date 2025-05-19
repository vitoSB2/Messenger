package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
		panelC.getIP().addKeyListener(this);
		panelC.getEnter().addActionListener(this);
		
	}
	
	private void mudarPanel() {
		panelP = new PanelPrincipal();
		panelP.setIP(ip);
		f.setContentPane(panelP);
		f.repaint();
		f.revalidate();
		cp = new ControladorPrincipal(f, panelP);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == panelC.getEnter()) {
			ip = panelC.getIP().getText();
			System.out.println(ip);
			porta = panelC.getPorta().getText();
			mudarPanel();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			mudarPanel();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
