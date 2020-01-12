import javax.swing.JFrame;

public class Main {
		//Painter¥Dµ{¦¡
	  	public static void main(String[] args)
	  	{
		   Painter painter = new Painter();
		   painter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   painter.setSize(800,700);
		   painter.setVisible(true);
		   painter.setLocationRelativeTo(null);
	  	}
}
