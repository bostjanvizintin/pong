package pong;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

public class Pong extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	
	private static int width = 500;
	private static int height = 300;
	private static boolean running = true;
	
	private static final int SPEED = 500;
	
	private Thread thread;
	private Screen screen;
	private Game game;
	private JFrame frame;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	public Pong() {
		Dimension size = new Dimension(width,height);
		setPreferredSize(size);
		
		frame = new JFrame();
		game = new Game();
		screen = new Screen(width, height);
	}

	public static void main(String[] args) {
		Pong pong = new Pong();
		pong.frame.setResizable(false);
		pong.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pong.frame.setTitle("Pong");
		pong.frame.add(pong);
		pong.frame.pack();
		pong.frame.setLocationRelativeTo(null);
		pong.frame.setVisible(true);
		
		pong.start();


	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public void run() {
		long currentTimeInMills = System.currentTimeMillis();
		while(running) {
			if(System.currentTimeMillis() - currentTimeInMills > SPEED) {
				update();
				render();
				currentTimeInMills += SPEED;
			}
			
		}
		
	}
	
	public void render() {
		screen.render();
		for (int i = 0; i < screen.getSize(); i++) {
			pixels[i] = screen.getPixel(i);
		}
		repaint();
	}
	
	public void update() {
		
	}
	
	public void paint(Graphics g) {
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		img.setRGB(0, 0, width,height,pixels,0,width);
		g.drawImage(img, 0, 0, null);
	}

}
