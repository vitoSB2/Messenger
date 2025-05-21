package controlador;

import visual.Frame;
import visual.PanelConexao;

public class Main {
	
	Frame f;
	PanelConexao pc;
	ControladorConexao cc;
	public static Server server;
	public static Cliente cliente;
	
	public Main() {
		pc = new PanelConexao();
		f = new Frame(pc);
		cc = new ControladorConexao(pc, f);
	}

	public static void main(String[] args) {
		new Main();
	}

}
