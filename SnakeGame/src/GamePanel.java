import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener{
	
	static final int screen_width = 600;
	static final int screen_height = 600;
	static final int unit_size = 25;
	static final int game_units = (screen_width*screen_height)/unit_size;
	static final int delay = 75;
	final int[] x = new int[game_units];
	final int[] y = new int[game_units];
	int body_parts = 6;
	int applesEaten=0;
	int appleX;
	int appleY;
	char direction = 'R';
	boolean running = false;
	Timer timer;
	Random random;
	
	GamePanel(){
		random = new Random();
		this.setPreferredSize(new Dimension(screen_width,screen_height));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}
	
	public void startGame() {
		newApple();
		running=true;
		timer = new Timer(delay,this);
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		for(int i =0;i<screen_height/unit_size;i++) {
			g.drawLine(i*unit_size, 0, i*unit_size, screen_height);
			g.drawLine(0,i*unit_size, screen_width, i*unit_size);
		}
		g.setColor(Color.red);
		g.fillOval(appleX, appleY, unit_size, unit_size);
	}
	
	public void newApple() {
		appleX = random.nextInt((int)(screen_width/unit_size))*unit_size; //to place evenly in the unit place
		appleY = random.nextInt((int)(screen_height/unit_size))*unit_size; //to place evenly in the unit place
	}
	
	public void move() {
		for(int i =body_parts;i>0;i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		switch(direction) {
		case'U':
			y[0] = y[0]-unit_size;
			break;
		case'D':
			y[0] = y[0]+unit_size;
			break;
		case'L':
			x[0] = x[0]-unit_size;
			break;
		case'R':
			x[0] = x[0]+unit_size;
			break;
		}
	}
	public void checkApple() {
		
	}
	public void checkCollisions() {
		
		
	}
	public void gameOver(Graphics g) {
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			
		}
	}

}
