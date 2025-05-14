package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import modelo.Util;

public class PanelPrincipal extends JPanel{
	
	JLabel logo;
	JTextField texto;
	JPanel div, blocoTxt, mensagensFundo;
	JScrollPane mensagens;
	JButton enter, anexo;
	
	public PanelPrincipal() {
		setBackground(new Color(235, 235, 235));
		this.setLayout(null);
		this.setPreferredSize(new Dimension(500, 600));
		this.add(getLogo());
		this.add(getEnter());
		this.add(getAnexo());
		this.add(getTexto());
		this.add(getBlocoTxt());
		this.add(getMensagensFundo());
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

	public JButton getEnter() {
		if(enter == null) {
			enter = new JButton();
			enter.setBackground(new Color(250, 250, 250));
			enter.setIcon(Util.resizeIcon("enter", 26, 20));
			enter.setHorizontalAlignment(SwingConstants.CENTER);
			enter.setBorder(null);
			enter.setBounds(400, 522, 48, 36);
		}
		return enter;
	}

	public JButton getAnexo() {
		if(anexo == null) {
			anexo = new JButton();
			anexo.setBackground(new Color(250, 250, 250));
			anexo.setIcon(Util.resizeIcon("clip", 19, 21));
			anexo.setHorizontalAlignment(SwingConstants.CENTER);
			anexo.setBorder(null);
			anexo.setBounds(52, 522, 50, 36);
		}
		return anexo;
	}

	public JTextField getTexto() {
		if(texto == null) {
			texto = new JTextField();
			texto.setBackground(new Color(250, 250, 250));
			texto.setForeground(new Color(45, 45, 45));
			texto.setBorder(null);
			texto.setFont(new Font("Montserrat", Font.BOLD, 14));
			texto.setBounds(110, 522, 280, 36);
		}
		return texto;
	}

	public JScrollPane getMensagens() {
		if(mensagens == null) {
			mensagens = new JScrollPane();
			mensagens.setBorder(null);
			mensagens.setBounds(0, 0, 470, 426);
		}
		return mensagens;
	}

	public JPanel getBlocoTxt() {
		if(blocoTxt == null) {
			blocoTxt = new JPanel();
			blocoTxt.setBackground(new Color(250, 250, 250));
			blocoTxt.setBorder(new LineBorder(new Color(210, 210, 210), 2, true));
			blocoTxt.setBounds(50, 520, 400, 40);
		}
		return blocoTxt;
	}

	public JPanel getMensagensFundo() {
		if(mensagensFundo == null) {
			mensagensFundo = new JPanel();
			mensagensFundo.setBackground(new Color(250, 250, 250));
			mensagensFundo.setBorder(new LineBorder(new Color(210, 210, 210), 2, true));
			mensagensFundo.setBounds(15, 70, 470, 426);
			mensagensFundo.add(getMensagens());
		}
		return mensagensFundo;
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
