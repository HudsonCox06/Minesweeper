import javax.swing.*;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.geom.*;
public class MinesweeperFrame extends JFrame{

	public MinesweeperGame model;
	public int xSquares;
	public int ySquares;
	public MinesweeperPanel panel;
	public MinesweeperHeader headerPanel;


	public MinesweeperFrame(){
		setTitle("Minesweeper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		xSquares = 20;
		ySquares = 20;
		//Modify xSquares and ySquares for desired board size.
		setSize(25*xSquares, (25*ySquares));
		setResizable(false);

		model = new MinesweeperGame(xSquares, ySquares);
		
		headerPanel = new MinesweeperHeader(this);
		add(headerPanel, BorderLayout.NORTH);
		panel = new MinesweeperPanel(this);
		add(panel, BorderLayout.CENTER);

		setVisible(true);
	}

	public class resetHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			model.newGame();
			remove(panel);
			panel = new MinesweeperPanel(MinesweeperFrame.this);
			add(panel);
			panel.revalidate();
			headerPanel.updateFlagsNum();
		}
	}
	public MinesweeperGame getModel(){
		return model;
	}
}