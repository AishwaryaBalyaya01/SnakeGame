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
	static final int delay = 100;
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
		if(running) {
		for(int i =0;i<screen_height/unit_size;i++) {
			g.drawLine(i*unit_size, 0, i*unit_size, screen_height); //x1,y1,x2,y2
			g.drawLine(0,i*unit_size, screen_width, i*unit_size);// 
		}
		g.setColor(Color.red);
		g.fillOval(appleX, appleY, unit_size, unit_size);
		
		for(int i = 0;i<body_parts;i++) {
			if(i==0) {
				g.setColor(Color.green);
				g.fillRect(x[i], y[i], unit_size,unit_size);
			}
			else {
				g.setColor(new Color(45,180,0));
				g.fillRect(x[i], y[i], unit_size,unit_size);
			}
		}
		}
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
		if((x[0]== appleX) && (y[0]== appleY)) {
			body_parts++;
			applesEaten++;
			newApple();
		}
	}
	public void checkCollisions() {
		// checks if head collides with body
		for(int i = body_parts;i>0;i--) {
			if((x[0]==x[i]) && (y[0]==y[i])) running = false;
		}
		
		//check if heads touches the left border
		if(x[0]<0) running = false;
		
		//check if heads touches the right border
		if(x[0]>screen_width) running = false;
		
		//check if heads touches the top border
		if(y[0]<0) running = false;
				
		//check if heads touches the bottom border
		if(y[0]>screen_height) running = false;
		
		if(!running) timer.stop();
	}
	public void gameOver(Graphics g) {
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(running) {
			move();
			checkApple();
			checkCollisions();
		}
		repaint();
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				direction = 'L';
				break;
			case KeyEvent.VK_RIGHT:
				direction = 'R';
				break;
			case KeyEvent.VK_UP:
				direction = 'U';
				break;
			case KeyEvent.VK_DOWN:
				direction = 'D';
				break;
			}
		}
	}

}
