package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import modelo.Util;

public class PanelPrincipal extends JPanel{
	
	JLabel logo, IP;
	JTextField texto;
	JPanel div, blocoTxt, mensagensFundo;
	JScrollPane conversa;
	JButton enter, anexo;
	JPanel[] mensagensPane;
	JLabel[] mensagens;
	JButton[] downloads;
	String IPconectado;
	int quantMensagens=37, maxWidth=220, espacamento=20;
	
	public PanelPrincipal() {
		setBackground(new Color(235, 235, 235));
		mensagensPane = new JPanel[1];
		mensagens = new JLabel[1];
		downloads = new JButton[1];
		this.setLayout(null);
		this.setPreferredSize(new Dimension(500, 600));
		this.add(getLogo());
		this.add(getEnter());
		this.add(getAnexo());
		this.add(getTexto());
		this.add(getBlocoTxt());
		this.add(getConversa());
		this.add(getDiv());
	}
	
	public void setIP(String IP) {
		this.IPconectado = IP;
		this.IP.setText("Conectado no IP: " + IPconectado);
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

	public JLabel getIP() {
		if(IP == null) {
			IP = new JLabel();
			IP.setForeground(new Color(160, 160, 160));
			IP.setFont(new Font("Montserrat", Font.BOLD, 14));
			IP.setBounds(0, 20, 470, 17);
			IP.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return IP;
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

	public JScrollPane getConversa() {
		if(conversa == null) {
			conversa = new JScrollPane();
			conversa.setBounds(15, 70, 470, 426);
			conversa.setBorder(new LineBorder(new Color(210, 210, 210), 2, true));
			conversa.setViewportView(getMensagensFundo());
			conversa.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		}
		return conversa;
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
			mensagensFundo.setPreferredSize(new Dimension(470, 420));
			mensagensFundo.setLayout(null);
			mensagensFundo.add(getIP());
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

	public void criarMensagemEnviada(String msg){
		quantMensagens++;
		if(quantMensagens>mensagens.length)
			aumentarTamanhoMensagens();

		mensagens[quantMensagens-1] = new JLabel(msg);
		mensagens[quantMensagens-1].setFont(new Font("Montserrat", Font.BOLD, 14));
		mensagens[quantMensagens-1].setLocation(15, 15);

		// OBTEM-SE A FONTMETRICS ATRAVÉS DO JLABEL
		FontMetrics fm = mensagens[quantMensagens-1].getFontMetrics(mensagens[quantMensagens-1].getFont());
		if(fm.stringWidth(msg) > maxWidth+10){
			// PULA AS LINHAS CASO ALGANCE O MAXIMO DE LARGURA
			msg = "<html><div style='width:" + maxWidth + "px'>" + msg + "</div></html>";
			mensagens[quantMensagens-1].setText(msg);
			fm = mensagens[quantMensagens-1].getFontMetrics(mensagens[quantMensagens-1].getFont());
        	mensagens[quantMensagens-1].setSize(new Dimension(fm.stringWidth(msg), mensagens[quantMensagens - 1].getPreferredSize().height));
		} else
			mensagens[quantMensagens-1].setSize(new Dimension(fm.stringWidth(msg), fm.getHeight()));
		
		mensagens[quantMensagens-1].setSize(mensagens[quantMensagens-1].getPreferredSize());

		mensagensPane[quantMensagens-1] = new JPanel();
		mensagensPane[quantMensagens-1].setLayout(null);
		mensagensPane[quantMensagens-1].setBackground(new Color(213, 233, 255));
		mensagensPane[quantMensagens-1].setBorder(new LineBorder(new Color(167, 190, 215), 1, true));
		mensagensPane[quantMensagens-1].setBounds(
			mensagensFundo.getWidth()-mensagens[quantMensagens-1].getWidth()-60, 
			espacamento, 
			mensagens[quantMensagens-1].getWidth()+30, 
			mensagens[quantMensagens-1].getHeight()+30);

		mensagensPane[quantMensagens-1].add(mensagens[quantMensagens-1]);
		mensagensFundo.add(mensagensPane[quantMensagens-1]);

		espacamento += mensagensPane[quantMensagens-1].getHeight() + 20;

		mensagensFundo.setPreferredSize(new Dimension(mensagensFundo.getWidth(), espacamento));
    	mensagensFundo.revalidate();
	}

	public void criarMensagemRecebida(String msg){
		quantMensagens++;
		if(quantMensagens>mensagens.length)
			aumentarTamanhoMensagens();

		mensagens[quantMensagens-1] = new JLabel(msg);
		mensagens[quantMensagens-1].setFont(new Font("Montserrat", Font.BOLD, 14));
		mensagens[quantMensagens-1].setLocation(15, 15);

		// OBTEM-SE A FONTMETRICS ATRAVÉS DO JLABEL
		FontMetrics fm = mensagens[quantMensagens-1].getFontMetrics(mensagens[quantMensagens-1].getFont());
		if(fm.stringWidth(msg) > maxWidth+10){
			// PULA AS LINHAS CASO ALGANCE O MAXIMO DE LARGURA
			msg = "<html><div style='width:" + maxWidth + "px'>" + msg + "</div></html>";
			mensagens[quantMensagens-1].setText(msg);
			fm = mensagens[quantMensagens-1].getFontMetrics(mensagens[quantMensagens-1].getFont());
        	mensagens[quantMensagens-1].setSize(new Dimension(fm.stringWidth(msg), mensagens[quantMensagens - 1].getPreferredSize().height));
		} else
			mensagens[quantMensagens-1].setSize(new Dimension(fm.stringWidth(msg), fm.getHeight()));
		
		mensagens[quantMensagens-1].setSize(mensagens[quantMensagens-1].getPreferredSize());

		mensagensPane[quantMensagens-1] = new JPanel();
		mensagensPane[quantMensagens-1].setLayout(null);
		mensagensPane[quantMensagens-1].setBackground(new Color(235, 235, 235));
		mensagensPane[quantMensagens-1].setBorder(new LineBorder(new Color(189, 189, 189), 1, true));
		mensagensPane[quantMensagens-1].setBounds(
			30, espacamento, 
			mensagens[quantMensagens-1].getWidth()+30, 
			mensagens[quantMensagens-1].getHeight()+30);

		mensagensPane[quantMensagens-1].add(mensagens[quantMensagens-1]);
		mensagensFundo.add(mensagensPane[quantMensagens-1]);

		espacamento += mensagensPane[quantMensagens-1].getHeight() + 20;

		mensagensFundo.setPreferredSize(new Dimension(mensagensFundo.getWidth(), espacamento));
    	mensagensFundo.revalidate();
	}

	public void criarArquivoEnviado(File arquivo){
		quantMensagens++;
		if(quantMensagens>mensagens.length)
			aumentarTamanhoMensagens();

		mensagens[quantMensagens-1] = new JLabel(arquivo.getName());
		mensagens[quantMensagens-1].setFont(new Font("Montserrat", Font.BOLD, 14));
		mensagens[quantMensagens-1].setLocation(15, 15);

		// OBTEM-SE A FONTMETRICS ATRAVÉS DO JLABEL
		FontMetrics fm = mensagens[quantMensagens-1].getFontMetrics(mensagens[quantMensagens-1].getFont());
		if(fm.stringWidth(arquivo.getName()) > maxWidth-67){
        	mensagens[quantMensagens-1].setSize(new Dimension(maxWidth-77, fm.getHeight()));
		} else
			mensagens[quantMensagens-1].setSize(new Dimension(fm.stringWidth(arquivo.getName()), fm.getHeight()));
		
		mensagens[quantMensagens-1].setSize(mensagens[quantMensagens-1].getPreferredSize());

		downloads[quantMensagens-1] = new JButton();
		downloads[quantMensagens-1].setOpaque(false);
		downloads[quantMensagens-1].setBackground(new Color(213, 233, 255));
		downloads[quantMensagens-1].setBorder(new LineBorder(new Color(167, 190, 215), 1, false));
		downloads[quantMensagens - 1].setIcon(Util.resizeIcon("download", 23, 25));
		downloads[quantMensagens-1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// COLOCAR O ALGORITMO DE BAIXAR AQUI
			}
		});
		downloads[quantMensagens - 1].setBounds(
        	mensagens[quantMensagens - 1].getWidth() + 30, 
			0, 47, 47);

		mensagensPane[quantMensagens-1] = new JPanel();
		mensagensPane[quantMensagens-1].setLayout(null);
		mensagensPane[quantMensagens-1].setBackground(new Color(213, 233, 255));
		mensagensPane[quantMensagens-1].setBorder(new LineBorder(new Color(167, 190, 215), 1, true));
		mensagensPane[quantMensagens-1].setBounds(
			mensagensFundo.getWidth()-mensagens[quantMensagens-1].getWidth()-107, 
			espacamento, 
			mensagens[quantMensagens-1].getWidth()+30+47, 
			mensagens[quantMensagens-1].getHeight()+30);

		mensagensPane[quantMensagens-1].add(mensagens[quantMensagens-1]);
		mensagensPane[quantMensagens-1].add(downloads[quantMensagens-1]);
		mensagensFundo.add(mensagensPane[quantMensagens-1]);

		espacamento += mensagensPane[quantMensagens-1].getHeight() + 20;

		mensagensFundo.setPreferredSize(new Dimension(mensagensFundo.getWidth(), espacamento));
    	mensagensFundo.revalidate();
		mensagensFundo.repaint();
	}

	public void criarArquivoRecebido(File arquivo){
		quantMensagens++;
		if(quantMensagens>mensagens.length)
			aumentarTamanhoMensagens();

		mensagens[quantMensagens-1] = new JLabel(arquivo.getName());
		mensagens[quantMensagens-1].setFont(new Font("Montserrat", Font.BOLD, 14));
		mensagens[quantMensagens-1].setLocation(15, 15);

		// OBTEM-SE A FONTMETRICS ATRAVÉS DO JLABEL
		FontMetrics fm = mensagens[quantMensagens-1].getFontMetrics(mensagens[quantMensagens-1].getFont());
		if(fm.stringWidth(arquivo.getName()) > maxWidth-67){
        	mensagens[quantMensagens-1].setSize(new Dimension(maxWidth-77, fm.getHeight()));
		} else
			mensagens[quantMensagens-1].setSize(new Dimension(fm.stringWidth(arquivo.getName()), fm.getHeight()));
		
		mensagens[quantMensagens-1].setSize(mensagens[quantMensagens-1].getPreferredSize());

		downloads[quantMensagens-1] = new JButton();
		downloads[quantMensagens-1].setOpaque(false);
		downloads[quantMensagens-1].setBackground(new Color(235, 235, 235));
		downloads[quantMensagens-1].setBorder(new LineBorder(new Color(189, 189, 189), 1, false));
		downloads[quantMensagens - 1].setIcon(Util.resizeIcon("download", 23, 25));
		downloads[quantMensagens-1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// COLOCAR O ALGORITMO DE BAIXAR AQUI
			}
		});
		downloads[quantMensagens - 1].setBounds(
        	mensagens[quantMensagens - 1].getWidth() + 30, 
			0, 47, 47);

		mensagensPane[quantMensagens-1] = new JPanel();
		mensagensPane[quantMensagens-1].setLayout(null);
		mensagensPane[quantMensagens-1].setBackground(new Color(235, 235, 235));
		mensagensPane[quantMensagens-1].setBorder(new LineBorder(new Color(189, 189, 189), 1, true));
		mensagensPane[quantMensagens-1].setBounds(
			20, 
			espacamento, 
			mensagens[quantMensagens-1].getWidth()+30+47, 
			mensagens[quantMensagens-1].getHeight()+30);

		mensagensPane[quantMensagens-1].add(mensagens[quantMensagens-1]);
		mensagensPane[quantMensagens-1].add(downloads[quantMensagens-1]);
		mensagensFundo.add(mensagensPane[quantMensagens-1]);

		espacamento += mensagensPane[quantMensagens-1].getHeight() + 20;

		mensagensFundo.setPreferredSize(new Dimension(mensagensFundo.getWidth(), espacamento));
    	mensagensFundo.revalidate();
		mensagensFundo.repaint();
	}

	// AUMENTA A ACAPACIDADE DO ARRAY DAS MENSAGENS
	public void aumentarTamanhoMensagens(){
		JLabel[] aux = new JLabel[quantMensagens];
		JPanel[] auxP = new JPanel[quantMensagens];
		JButton[] auxB = new JButton[quantMensagens];

		for (int i = 0; i < mensagens.length; i++) {
			aux[i] = mensagens[i];
			auxP[i] = mensagensPane[i];
			auxB[i] = downloads[i];
		}

		mensagens = aux;
		mensagensPane = auxP;
		downloads = auxB;
	}
}