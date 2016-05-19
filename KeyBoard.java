import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class KeyBoard extends JFrame implements KeyListener
{	
	private JTextArea textArea;
	private String[] keys = {"~","1","2","3","4","5","6","7","8","9","0","-","=","Backspace",
							 "Tab","Q","W","E","R","T","Y","U","I","O","P","[","]","\\",
							 "Caps","A","S","D","F","G","H","J","K","L",";","\'","Enter",
							 "Shift","Z","X","C","V","B","N","M",",",".","/","^",
							 " ","<","v",">"
							};
	private int[] nums = {192,49,50,51,52,53,54,55,56,57,48,45,61,8,
					  	  9,81,87,69,82,84,89,85,73,79,80,91,93,92,
					      20,65,83,68,70,71,72,74,75,76,59,222,10,
					  	  16,90,88,67,86,66,78,77,44,46,47,38,
					      32,37,40,39
					     };
	private JButton[] labs = new JButton[57];
	private final String target = "The quick brown fox jumped over a lazy dog.";
	private JLabel error = new JLabel("Error report: 0 error");
	public KeyBoard(){
		super("Typing Application");
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel title1 = new JLabel("Type some text using your keyboard. The keys you press will be higlighted and the text will be display.");
		JLabel title2 = new JLabel("Note:Clicking the buttons with your mouse will not perform any action.");
		JLabel title3 = new JLabel("Please Typing 'The quick brown fox jumped over a lazy dog.'(without quotation marks).                                                           ");
		textArea = new JTextArea(8,70);
		textArea.setEnabled(true);
		textArea.setDisabledTextColor(Color.BLACK);
		add(title1);
		add(title2);
		add(title3);
		add(error);
		add(textArea);
		textArea.addKeyListener(this);
		for(int i = 0; i < keys.length; i++)
		{
			JButton x = new JButton(keys[i]);
			x.setPreferredSize(new Dimension(46,46));
			if(keys[i] == "Backspace") x.setPreferredSize(new Dimension(100,46));
			if(keys[i] == "Tab") x.setPreferredSize(new Dimension(80,46));
			if(keys[i] == "Caps") x.setPreferredSize(new Dimension(80,46));
			if(keys[i] == "Shift") x.setPreferredSize(new Dimension(100,46));
			if(keys[i] == "Enter") x.setPreferredSize(new Dimension(80,46));
			if(keys[i] == " ") x.setPreferredSize(new Dimension(80,46));
			if(keys[i] == "^") 
			{
				JLabel k = new JLabel("");
				k.setPreferredSize(new Dimension(20,46));
				add(k);
			}
			else if(keys[i] == " ")
			{
				x = new JButton(" ");
				x.setPreferredSize(new Dimension(300,46));
				JLabel k = new JLabel(" ");
				k.setPreferredSize(new Dimension(200,46));
				add(k);
			}
			else if(keys[i] == "<")
			{
				JLabel k = new JLabel(" ");
				k.setPreferredSize(new Dimension(74,46));
				add(k);
			}
			x.setBackground(Color.WHITE);
			add(x);
			labs[i] = x;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// System.out.println("pressed"+e.getKeyCode());
		for(int i = 0; i < labs.length; i++){
			if(nums[i] == e.getKeyCode()){
				labs[i].setBackground(Color.CYAN);
				break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		for(int i = 0; i < labs.length; i++){
			if(nums[i] == e.getKeyCode()){
				labs[i].setBackground(Color.WHITE);
				break;
			}
			error.setText(recoder());
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
	public String recoder()
	{
		String now = textArea.getText();
		int total = 0;
		int[] num = new int[256];
		for(int i = 0 ; i < 256; i++) num[i] = 0;
		for(int i = 0; i < now.length() && i < target.length(); i++)
		{
			if(now.charAt(i) != target.charAt(i))
			{
				total++;
				num[target.charAt(i)]++;
			}
		}
		String ans = "Error report: " + total + " errors ";
		for(int i = 0; i < 256; i++)
		{
			if(num[i] != 0)
			{
				ans += " " + num[i] + " " + (char)i;
			}
		}
		return ans;
	}
}