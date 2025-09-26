import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MinesweeperHeader extends JPanel{
	private MinesweeperFrame frame;
	MinesweeperPanel panel1;
	JLabel flagNum;


	public MinesweeperHeader(MinesweeperFrame frame){
		this.frame = frame;
		flagNum = new JLabel();
		updateFlagsNum();
		setLayout(new GridLayout());
		setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
		JButton reset = new JButton("Reset");
		reset.addActionListener(frame.new resetHandler());
		add(flagNum);
		add(reset);


	}
	public void updateFlagsNum(){
		int flags = frame.model.getFlags();
		flagNum.setText("Flags Remaining: "+flags);
		repaint();
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);

	}
}
