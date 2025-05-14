package visual;

import javax.swing.JFrame;

public class Frame extends JFrame{
	
	public Frame(PanelConexao pc) {
		super();
		this.add(pc);
		this.setTitle("Messenger LP3");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    this.setVisible(true);
	}

}
