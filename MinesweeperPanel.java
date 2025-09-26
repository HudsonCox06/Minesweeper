import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MinesweeperPanel extends JPanel{
	MinesweeperFrame frame;
	Tile[][] tileMap;
	JLabel[][] tileLabels;

	public MinesweeperPanel(MinesweeperFrame f){
		frame = f;
		tileMap = frame.model.tileMap;
		setLayout(new GridLayout(frame.xSquares, frame.ySquares));
		tileLabels = new JLabel[frame.xSquares][frame.ySquares];
		initializeButtons();
	}

	public void initializeButtons(){
		for(int x = 0; x < frame.xSquares; x++){
			for(int y = 0; y < frame.ySquares; y++){
				tileLabels[x][y] = new JLabel();
				tileLabels[x][y].setPreferredSize(new Dimension(25, 25));
				updateTileIcon(tileLabels[x][y], x, y);

				final int posX = x;
				final int posY = y;
				tileLabels[x][y].addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if(SwingUtilities.isLeftMouseButton(e)){
							frame.model.leftClick(posX, posY);
							updateAllTiles();
						}
						else if(SwingUtilities.isRightMouseButton(e)){
							frame.model.rightClick(posX, posY);

							updateTileIcon(tileLabels[posX][posY], posX, posY);
						}
						frame.headerPanel.updateFlagsNum();

					}
				});
				add(tileLabels[x][y]);
			}
		}
	}


	public void updateTileIcon(JLabel tileLabel, int x, int y){
		if(tileMap[x][y].hidden){
			if(tileMap[x][y].isFlagged){
				tileLabel.setIcon(new ImageIcon("squares/square_Flagged.png"));
			}
			else{
				tileLabel.setIcon(new ImageIcon("squares/square_Covered.png"));
			}
		}
		else{
			tileLabel.setIcon(new ImageIcon("squares/square_"+frame.model.returnType(x,y)+".png"));
		}
	}


	public void updateAllTiles(){
		for(int x = 0; x<frame.xSquares; x++){
			for(int y = 0; y<frame.ySquares; y++){
				updateTileIcon(tileLabels[x][y], x, y);
			}
		}
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
}