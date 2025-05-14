package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import visual.Frame;
import visual.PanelPrincipal;

public class ControladorPrincipal implements ActionListener, KeyListener{
	
	PanelPrincipal panelP;
	Frame f;
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
		if(e.getSource() == panelP.getEnter()) {
			System.out.println("" + panelP.getTexto().getText());
		}
	}
	
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			System.out.println("" + panelP.getTexto().getText());
		}
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}	
	
}
