package hang;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

@SuppressWarnings("serial")
public class Frame extends JFrame{

	private JPanel pd;
	private JLabel str,levelLabel;
	private JButton bt,bt_change;
	private JTextField tf;
	private String []options= {"Names","Animals","Countries"};
	private JComboBox <String>cb=new JComboBox<>(options);
	private String w="erenalp kesici";
	public String a="";
	public String tmp="";
	public int z=0,level=1;
	char[] ar;
	private JMenuBar mb;
	private JMenu m;
	private JMenuItem exit,about;
	URL url;
	BufferedImage img;
	Frame() throws MalformedURLException{
		super("Hangman The Game");
		Pu pu=new Pu();
		url =new URL("https://i.ibb.co/Sw9hHPz/1.png");
		pu.setPreferredSize(new Dimension(1000,550));
		pd=new JPanel();
		pd.setBackground(Color.lightGray);
		pd.setLayout(new GridBagLayout());
		GridBagConstraints gb=new GridBagConstraints();
		pd.setPreferredSize(new Dimension(1000,300));
		str=new JLabel();
		gb.gridx=1;
		gb.gridy=5;
		levelLabel=new JLabel(Integer.toString(level)+"/2");
		levelLabel.setFont(new Font("Times Roman",Font.BOLD,25));
		levelLabel.setForeground(Color.CYAN);
		levelLabel.setPreferredSize(new Dimension(200,100));
		pd.add(levelLabel,gb);
		gb.gridx=0;
		gb.gridy=0;
		pd.add(str,gb);
		tf=new JTextField(25);
		tf.setPreferredSize(new Dimension(20,40));
		tf.setFont(new Font("sansserif",Font.BOLD,25));
		tf.setToolTipText("Enter a letter...");
		str.setFont(new Font("ComicSan",Font.BOLD,60));
		str.setForeground(Color.BLUE);
		gb.insets=new Insets(20,20,20,20);
		gb.gridx=0;
		gb.gridy=5;
		pd.add(tf,gb);
		pd.add(cb);
		bt_change=new JButton("Change");
		pd.add(bt_change);
		add(pu,BorderLayout.NORTH);
		add(pd,BorderLayout.SOUTH);
		
		bt=new JButton("Confirm");
		bt.setFont(new Font("Sans Serif",Font.BOLD,20));
		bt.setToolTipText("Confirm action...");
		gb.gridx=1;
		gb.gridy=5;
		pd.add(bt,gb);
		bt.setBounds(525,120,100,25);
		
		JButton btg=new JButton("Guess");
		btg.setFont(new Font("serif",Font.BOLD,20));
		btg.setBackground(Color.red);
		btg.setForeground(Color.white);
		btg.setToolTipText("Guess the whole sentence...");
		gb.gridx=1;
		gb.gridy=6;
		pd.add(btg,gb);
		w=word();
		setStr();
		bt_change.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				w=word();
				setStr();		
			}
		});
		btg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame nf=new JFrame("Guessing the word");
				JPanel nup=new JPanel();
				JPanel ndp=new JPanel();
				JTextField nt=new JTextField(25);
				JButton nbt=new JButton("Guess the word");
				nup.add(nt);
				ndp.add(nbt);
				nt.addKeyListener(new KeyListener() {
					@Override
					public void keyPressed(KeyEvent arg0) {
						if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
							nbt.doClick();
					}

					@Override
					public void keyReleased(KeyEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void keyTyped(KeyEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
				});
				nbt.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(nt.getText().compareTo(w)==0) {
							if(level==1) {
								JOptionPane.showMessageDialog(null,"You guessed the first one right!");
								nf.dispose();
								nt.setText("");
								w=word();
								setStr();
								ar=a.toCharArray();
								level++;
								levelLabel.setText(Integer.toString(level)+"/2");
						}
						else if(level==2) {
							JOptionPane.showMessageDialog(null,"You guessed all of them right!");
							nf.setVisible(false);
							nf.dispose();
							setVisible(false);
							dispose();
						}
						}
						else {
							if(z==8)
							{
								JOptionPane.showMessageDialog(null, "You ran out of lives! the word was "+"'"+w+"'");
								nf.setVisible(false);
								nf.dispose();
								setVisible(false);
								dispose();
							}
							else {
								repaint();
								z++;				
							}
						}
					}
				});
				nf.add(nup,BorderLayout.CENTER);
				nf.add(ndp,BorderLayout.SOUTH);
				nf.setVisible(true);
				nf.setSize(400,100);
				nf.setLocationRelativeTo(null);
			}
		});
		
		JLabel pl=new JLabel("Please enter one letter at a time!");
		pl.setFont(new Font("serif",Font.BOLD,20));
		pl.setBorder(new LineBorder(Color.red,4));
		pl.setVisible(false);
		gb.gridx=0;
		gb.gridy=6;
		pd.add(pl,gb);
		tf.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {		
				if(tf.getText().length()>0) 
					pl.setVisible(true);
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER)				
					bt.doClick();
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(tf.getText().length()>0)
				{
					String es="";
					es+=tf.getText().charAt(0);
					tf.setText(es);
				}
			}
			@Override
			public void keyTyped(KeyEvent arg0) {
			}		
		});

		ar=a.toCharArray();
		bt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				pl.setVisible(false);
				int fl=0,ef=0;
				char c=tf.getText().toLowerCase().charAt(0);
				w=w.toLowerCase();
				for(int i=0;i<w.length();i++) {
					if(c==w.charAt(i)) {
						tmp="";
						ar[i]=c;
						for(int k=0;k<w.length();k++) 				
							tmp+=ar[k];
						str.setText(tmp);
						fl++;
					}
				}
				if(fl==0&&z<8) {
					repaint();
					z++;
				}
				for(int i=0;i<w.length();i++) {
					if(ar[i]=='-') 
						ef++;
				}
				if(ef==0) {
					if(level==1) {
						JOptionPane.showMessageDialog(null,"You guessed the first word right!");
						w=word();
						setStr();
						ar=a.toCharArray();
						level++;
						levelLabel.setText(Integer.toString(level)+"/2");
					}
					else if(level==2) {
						JOptionPane.showMessageDialog(null,"You guessed all of them right!");
						setVisible(false);
						dispose();
					}

				}
				else if(z==8) {
					JOptionPane.showMessageDialog(null, "You ran out of lives! the word was "+"'"+w+"'");
					setVisible(false);
					dispose();
					
				}
				tf.setText("");
			}
			
		});
		
		mb=new JMenuBar();
		m=new JMenu("File");
		exit=new JMenuItem("Exit");
		about=new JMenuItem("About");
		m.add(about);
		m.add(exit);
		mb.add(m);
		setJMenuBar(mb);
		
		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame af=new JFrame("About");
				JPanel ap=new JPanel();
				ap.setLayout(null);
				JLabel al=new JLabel("Erenalp Kesici");
				al.setBounds(100, 25, 500, 25);
				JLabel al2=new JLabel("241 PROJECT     2019");
				al2.setBounds(100, 70, 500, 25);
				al2.setFont(new Font("Times Roman",Font.ITALIC,19));
				al.setFont(new Font("Times Roman",Font.BOLD,23));
				JButton ab=new JButton("OK");
				ab.addKeyListener(new KeyListener() {
					@Override
					public void keyTyped(KeyEvent e) {
						// TODO Auto-generated method stub		
					}
					@Override
					public void keyPressed(KeyEvent e) {
						if(e.getKeyCode()==KeyEvent.VK_ENTER){
							ab.doClick();
						}
					}

					@Override
					public void keyReleased(KeyEvent e) {
						// TODO Auto-generated method stub				
					}			
				});
				ab.setBounds(125, 115, 100, 40);
				ap.add(al);
				ap.add(al2);
				ap.add(ab);
				af.add(ap);
				af.setVisible(true);
				af.setLocationRelativeTo(null);
				af.setSize(400,200);
				af.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				ab.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						af.setVisible(false);
						af.dispose();
					}
				});
			}
		});
		
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame ef=new JFrame("Confirmation");
				JPanel ep=new JPanel();
				JLabel el=new JLabel("Are you sure you want to quit?");
				el.setFont(new Font("Comic San",Font.BOLD,15));
				JButton eb=new JButton("YES");
				eb.addKeyListener(new KeyListener() {
					@Override
					public void keyTyped(KeyEvent e) {		
					}
					@Override
					public void keyPressed(KeyEvent e) {
						if(e.getKeyCode()==KeyEvent.VK_ENTER) {
							eb.doClick();
						}						
					}
					@Override
					public void keyReleased(KeyEvent e) {	
					}				
				});
				eb.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.exit(0);	
					}			
				});
				ep.setLayout(null);
				el.setBounds(20, 0, 260, 25);
				eb.setBounds(90,40,100,30);
				ep.add(el);
				ep.add(eb);
				ef.add(ep);
				ef.setVisible(true);
				ef.setLocationRelativeTo(null);
				ef.setSize(280,140);
				ef.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}		
		});
		try {
			img=ImageIO.read(url);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	public class Pu extends JPanel{
		Pu() {
			repaint();
		}
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Color c=Color.GRAY;
			int thickness=9;
			if(z==0) 
				g.drawImage(img, 0,0, null);
			if(z==1) {
			g.drawImage(img, 0,0, null);
			g.setColor(c);
			g.fillOval(455, 80 , 100 , 	100);
			}
			else if (z==2) {
				g.drawImage(img, 0,0, null);
				g.setColor(c);
				g.fillOval(455, 80	, 100, 	100);
				g.fillRect(495, 150, 20, 260);
			}
			else if(z==3) {
				g.drawImage(img, 0,0, null);
				g.setColor(c);
				g.fillOval(455, 80	, 100, 	100);
				g.fillRect(495, 150, 20, 260);
				Graphics2D g2=(Graphics2D)g;
				g2.setStroke(new BasicStroke(thickness));
				g2.drawLine(495,200, 495-90, 300);
			}
			else if(z==4) {
				g.drawImage(img, 0,0, null);
				g.setColor(c);
				g.fillOval(455, 80	, 100, 	100);
				g.fillRect(495, 150, 20, 260);
				Graphics2D g2=(Graphics2D)g;
				g2.setStroke(new BasicStroke(thickness));
				g2.drawLine(495,200, 495-90, 300);
				g2.drawLine(500, 180, 495+97, 300);
			}
			else if(z==5) {
				g.drawImage(img, 0,0, null);
				g.setColor(c);
				g.fillOval(455, 80	, 100, 	100);
				g.fillRect(495, 150, 20, 260);
				Graphics2D g2=(Graphics2D)g;
				g2.setStroke(new BasicStroke(thickness));
				g2.drawLine(495,200, 495-90, 300);
				g2.drawLine(500, 180, 495+97, 300);
				g2.drawLine(495, 400, 495-80, 450);
			}
			else if(z==6) {
				g.drawImage(img, 0,0, null);
				g.setColor(c);
				g.fillOval(455, 80	, 100, 	100);
				g.fillRect(495, 150, 20, 260);
				Graphics2D g2=(Graphics2D)g;
				g2.setStroke(new BasicStroke(thickness));
				g2.drawLine(495,200, 495-90, 300);
				g2.drawLine(500, 180, 495+97, 300);
				g2.drawLine(495, 400, 495-80, 450);
				g2.drawLine(510,400 , 510+80, 450);
			}
			else if(z>6) {
				g.drawImage(img, 0,0, null);
				g.setColor(c);
				g.fillOval(455, 80	, 100, 	100);
				g.fillRect(495, 150, 20, 260);
				Graphics2D g2=(Graphics2D)g;
				g2.setStroke(new BasicStroke(thickness));
				g2.drawLine(495,200, 495-90, 300);
				g2.drawLine(500, 180, 495+97, 300);
				g2.drawLine(495, 400, 495-80, 450);
				g2.drawLine(510,400 , 510+80, 450);			
				JOptionPane.showMessageDialog(null, "You ran out of lives! the word was "+"'"+w+"'");
				dispose();	
			}
			tf.setText("");
		}
		
	}
	public String word() {
		if(cb.getSelectedItem().equals("Names")) {
			String []rw= {"Liam","Noah","William","James","Logan","Benjamin","Mason","Elijah","Oliver"};
			w=rw[rand(rw.length)];
		}
		else if(cb.getSelectedItem().equals("Animals")){
			String []rw= {"Lion","Dog","Cat","Fish","Moose","Cow","Deer","Alligator","Mouse"};
			w=rw[rand(rw.length)];
		}
		else {
			String []rw= {"United States","Turkey","Germany","France","United Kingdom","Spain","Mexico","Canada","Australia"};
			w=rw[rand(rw.length)];
		}
		return w;
	}
	public int rand(int x) {
		Random rnd=new Random();
		return rnd.nextInt(x);
	}
	public void setStr() {
		a="";
		for(int i=0;i<w.length();i++) {
			if(w.charAt(i)==' ') {
				a+=" ";
				str.setText(a);
			}
			else {
				a+="-";
				str.setText(a);
			}
		}
	}
		
}
