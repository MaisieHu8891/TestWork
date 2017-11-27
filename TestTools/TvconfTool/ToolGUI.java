package TestTools.TvconfTool;
import java.awt.*;
import javax.swing.*;

class SimpleFrame extends JFrame {
	Toolkit kit = Toolkit.getDefaultToolkit();
	Dimension screenSize = kit.getScreenSize();
	int screenWidth = screenSize.width;
	int screenHeight = screenSize.height;
	
	int DEFAULT_WIDTH = screenWidth / 3;
	int DEFAULT_HEIGHT = screenHeight /3;

	public SimpleFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setLocation(screenWidth / 3, screenHeight / 3);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}
}

class SimplePanel extends JPanel{
	JButton changevalue = new JButton("替换");
	JButton MD5Sign = new JButton("更新sign");
	JButton choosefile = new JButton("选择文件所在位置:");
	static JTextField textField0 = new JTextField(20);
	JLabel label2= new JLabel("待替换内容:",JLabel.RIGHT);
	JLabel label3 = new JLabel("要替换成:",JLabel.RIGHT);
	static JTextField textField1 = new JTextField(20);
	static JTextField textField2 = new JTextField(20);	
	public void addContents(){	
		add(choosefile);	
		add(textField0);
		add(Box.createVerticalStrut(5));
		add(label2);
		add(textField1);
		add(Box.createVerticalStrut(5));
		add(label3);
		add(textField2);
		add(Box.createVerticalStrut(5));
		add(changevalue);
		add(MD5Sign);
		Chooseconffile choosemyfile = new Chooseconffile();
		Change changel = new Change();
		Sign signl = new Sign();
		choosefile.addActionListener(choosemyfile);
		changevalue.addActionListener(changel);
		MD5Sign.addActionListener(signl);
	}
}

public class ToolGUI {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				SimpleFrame frame = new SimpleFrame();
				frame.setTitle("测试工具-修改tvconf");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				SimplePanel panel = new SimplePanel();
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				panel.addContents();
				frame.add(panel);				
			}

		});
	}

}