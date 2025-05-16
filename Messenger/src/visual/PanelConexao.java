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
	
	JLabel logo, promptIP, promptPorta;
	JTextField IP, porta;
	JPanel div;
	JButton enter;
	
	public PanelConexao() {
		setBackground(new Color(235, 235, 235));
		this.setLayout(null);
		this.setPreferredSize(new Dimension(500, 600));
		this.add(getLogo());
		this.add(getPromptIP());
		this.add(getPromptPorta());
		this.add(getEnter());
		this.add(getIP());
		this.add(getPorta());
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

	public JLabel getPromptIP() {
		if(promptIP == null) {
			promptIP = new JLabel();
			promptIP.setHorizontalAlignment(SwingConstants.CENTER);
			promptIP.setForeground(new Color(45, 45, 45));
			promptIP.setFont(new Font("Montserrat", Font.BOLD, 15));
			promptIP.setBounds(45, 208, 410, 18);
			promptIP.setText("Insira o IP do servidor que você deseja se conectar:");
		}
		return promptIP;
	}

	public JLabel getPromptPorta() {
		if(promptPorta == null) {
			promptPorta = new JLabel();
			promptPorta.setHorizontalAlignment(SwingConstants.CENTER);
			promptPorta.setForeground(new Color(45, 45, 45));
			promptPorta.setFont(new Font("Montserrat", Font.BOLD, 14));
			promptPorta.setBounds(140, 310, 220, 18);
			promptPorta.setText("Insira a porta para conexão:");
		}
		return promptPorta;
	}

	public JButton getEnter() {
		if(enter == null) {
			enter = new JButton("Conectar");
			enter.setHorizontalTextPosition(SwingConstants.LEFT);
			enter.setIconTextGap(15);
			enter.setBackground(new Color(213, 233, 255));
			enter.setIcon(Util.resizeIcon("enter", 27, 22));
			enter.setBorder(new LineBorder(new Color(167, 190, 215), 2, true));
			enter.setFont(new Font("Montserrat", Font.BOLD, 16));
			enter.setHorizontalAlignment(SwingConstants.CENTER);
			enter.setBounds(175, 423, 150, 48);
		}
		return enter;
	}

	public JTextField getIP() {
		if(IP == null) {
			IP = new JTextField();
			IP.setBackground(new Color(250, 250, 250));
			IP.setForeground(new Color(45, 45, 45));
			IP.setBorder(new LineBorder(new Color(210, 210, 210), 2, true));
			IP.setHorizontalAlignment(SwingConstants.CENTER);
			IP.setFont(new Font("Montserrat", Font.BOLD, 14));
			IP.setBounds(75, 241, 350, 40);
		}
		return IP;
	}

	public JTextField getPorta() {
		if(porta == null) {
			porta = new JTextField();
			porta.setBackground(new Color(250, 250, 250));
			porta.setForeground(new Color(45, 45, 45));
			porta.setBorder(new LineBorder(new Color(210, 210, 210), 2, true));
			porta.setHorizontalAlignment(SwingConstants.CENTER);
			porta.setFont(new Font("Montserrat", Font.BOLD, 14));
			porta.setBounds(150, 343, 200, 40);
		}
		return porta;
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
