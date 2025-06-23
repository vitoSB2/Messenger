package modelo;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Util {

	public static ImageIcon resizeIcon(String nomeDoIcone, int width, int height) {

		ImageIcon imageIcon = new ImageIcon(Util.class.getResource("/resourse/" + nomeDoIcone + ".png"));

		Image image = imageIcon.getImage();
		Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

		return new ImageIcon(scaledImage);

	}
	
}
