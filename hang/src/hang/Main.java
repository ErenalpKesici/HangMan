package hang;


import java.net.MalformedURLException;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) throws MalformedURLException {
		Frame f=new Frame();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1000,900);
		f.setLocationRelativeTo(null);
	}

}
