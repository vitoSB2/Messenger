package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import modelo.Util;

public class PanelConexao extends JPanel{
	
	JLabel logo, prompt;
	JTextField IP;
	JPanel div, blocoTxt;
	JButton enter;
	
	public PanelConexao() {
		setBackground(new Color(235, 235, 235));
		this.setLayout(null);
		this.setPreferredSize(new Dimension(500, 600));
		this.add(getLogo());
		this.add(getPrompt());
		this.add(getEnter());
		this.add(getIP());
		this.add(getBlocoTxt());
		this.add(getDiv());
	}

	public JLabel getLogo() {
		if(logo == null) {
			logo = new JLabel();
			logo.setIcon(Util.resizeIcon("logo", 32, 29));
			logo.setForeground(new Color(45, 45, 45));
			logo.setFont(new Font("Montserrat", Font.BOLD, 24));
			logo.setBounds(35, 22, 240, 29);
			logo.setText("Messenger LP3");
		}
		return logo;
	}

	public JLabel getPrompt() {
		if(prompt == null) {
			prompt = new JLabel();
			prompt.setHorizontalAlignment(SwingConstants.CENTER);
			prompt.setForeground(new Color(45, 45, 45));
			prompt.setFont(new Font("Montserrat", Font.BOLD, 15));
			prompt.setBounds(50, 265, 400, 17);
			prompt.setText("Insira o IP da máquina que você deseja se conectar:");
		}
		return prompt;
	}

	public JButton getEnter() {
		if(enter == null) {
			enter = new JButton();
			enter.setBackground(new Color(250, 250, 250));
			enter.setIcon(Util.resizeIcon("enter", 26, 20));
			enter.setBorder(new LineBorder(new Color(210, 210, 210), 1, true));
			enter.setHorizontalAlignment(SwingConstants.CENTER);
			enter.setBorder(null);
			enter.setBounds(400, 297, 48, 36);
		}
		return enter;
	}

	public JTextField getIP() {
		if(IP == null) {
			IP = new JTextField();
			IP.setBackground(new Color(250, 250, 250));
			IP.setForeground(new Color(45, 45, 45));
			IP.setBorder(null);
			IP.setFont(new Font("Montserrat", Font.BOLD, 14));
			IP.setBounds(60, 297, 340, 36);
		}
		return IP;
	}

	public JPanel getBlocoTxt() {
		if(blocoTxt == null) {
			blocoTxt = new JPanel();
			blocoTxt.setBackground(new Color(250, 250, 250));
			blocoTxt.setBorder(new LineBorder(new Color(210, 210, 210), 2, true));
			blocoTxt.setBounds(50, 295, 400, 40);
		}
		return blocoTxt;
	}

	public JPanel getDiv() {
		if(div == null) {
			div = new JPanel();
			div.setBackground(new Color(242, 242, 242));
			div.setBorder(new LineBorder(new Color(210, 210, 210), 2, true));
			div.setBounds(15, 70, 470, 515);
		}
		return div;
	}
	
}
