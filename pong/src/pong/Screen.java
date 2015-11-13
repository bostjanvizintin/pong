package pong;

import java.util.Random;

public class Screen {

	private int width;
	private int height;
	private int[] pixels;
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		this.pixels = new int[width*height];
	}
	
	public void render() {
		Random random = new Random();
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = random.nextInt(16777214);
		}
		System.out.println("Rendering");
	}

	public int getPixel(int i) {
		return pixels[i];
	}
	
	public int getSize() {
		return width*height;
	}
}
