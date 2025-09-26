import java.util.*;

public class MinesweeperGame{
	Tile[][] tileMap;
	int boardWidth;
	int boardHeight;
	int bombNumber;
	int flagsRemaining;
	Map<Integer, String> conversionMap;

	public MinesweeperGame(int x, int y){
		boardWidth = x;
		boardHeight = y;
		bombNumber = (int)((boardWidth*boardHeight)/8);
		conversionMap = new TreeMap<>();
		conversionMap.put(0, "Empty");
		conversionMap.put(1, "One");
		conversionMap.put(2, "Two");
		conversionMap.put(3, "Three");
		conversionMap.put(4, "Four");
		conversionMap.put(5, "Five");
		conversionMap.put(6, "Six");
		conversionMap.put(7, "Seven");
		conversionMap.put(8, "Eight");
		conversionMap.put(-1, "Bomb");

		newGame();
	}

	public MinesweeperGame(int x, int y, int bombs){
		boardWidth = x;
		boardHeight = y;
		bombNumber = bombs;
		conversionMap = new TreeMap<>();
		conversionMap.put(0, "Empty");
		conversionMap.put(1, "One");
		conversionMap.put(2, "Two");
		conversionMap.put(3, "Three");
		conversionMap.put(4, "Four");
		conversionMap.put(5, "Five");
		conversionMap.put(6, "Six");
		conversionMap.put(7, "Seven");
		conversionMap.put(8, "Eight");
		conversionMap.put(-1, "Bomb");

		newGame();
	}

	public void newGame(){
		tileMap = new Tile[boardWidth][boardHeight];
		flagsRemaining = bombNumber;
		for(int x = 0; x<boardWidth; x++){
			for(int y = 0; y<boardHeight; y++){
				tileMap[x][y] = new Tile();
				tileMap[x][y].hidden = true;
			}
		}
		assignBombs();
		assignNumbers();

	}

	public void assignBombs(){
		int x; 
		int y;
		for(int i = 0; i<bombNumber; i++){
			x = (int)(Math.random()*(boardWidth));
			y = (int)(Math.random()*(boardHeight));
			if(tileMap[x][y].isBomb()){
				i--;
			}
			else{
				tileMap[x][y].setType(-1);
			}
		}
	}

	public void assignNumbers(){
		int surroundingBombCount;
		for(int x = 0; x<boardWidth; x++){
			for(int y = 0; y<boardHeight; y++){

				if(tileMap[x][y].isBomb())
					continue;
				surroundingBombCount = 0;

				for(int sX = x-1; sX<=x+1; sX++){

					if(sX<0 || sX>boardWidth-1)
						continue;

					for(int sY = y-1; sY<=y+1; sY++){

						if((sX==x && sY==y) || sY<0 || sY>boardHeight-1)
							continue;
						if(tileMap[sX][sY].isBomb())
							surroundingBombCount++;
					}
				}
				tileMap[x][y].setType(surroundingBombCount);
			}
		}
	}

	public void gameOver(){
		flagsRemaining = 0;
		for(int x = 0; x<boardWidth; x++){
			for(int y = 0; y<boardHeight; y++){
				tileMap[x][y].hidden = false;
			}
		}
	}

	public int getFlags(){
		return flagsRemaining;
	}

	public void floodEmpty(int x, int y, int prevT){
		if(!tileMap[x][y].hidden || (tileMap[x][y].type != 0 && prevT!=0)){
			return;
		}
		tileMap[x][y].hidden = false;
		if(x-1 >= 0){
			floodEmpty(x-1, y, tileMap[x][y].type);
		}
		if(y+1 < boardHeight){
			floodEmpty(x,y+1, tileMap[x][y].type);
		}
		if(x+1 < boardWidth){
			floodEmpty(x+1, y, tileMap[x][y].type);
		}
		if(y-1 >=0){
			floodEmpty(x,y-1, tileMap[x][y].type);
		}
	}

	public void leftClick(int x, int y){
		if(tileMap[x][y].isBomb()){
			gameOver();
		}
		else if(tileMap[x][y].type==0 && tileMap[x][y].hidden){
			floodEmpty(x, y, 0);
		}
		else{
			tileMap[x][y].hidden = false;
		}
	}

	public void rightClick(int x, int y){
		if(tileMap[x][y].hidden){
			if(tileMap[x][y].isFlagged){
				tileMap[x][y].isFlagged = false;
				flagsRemaining++;
			}
			else if(flagsRemaining>0){
				tileMap[x][y].isFlagged = true;
				flagsRemaining--;
			}
		}
	}

	public String returnType(int x, int y){
		return conversionMap.get(tileMap[x][y].type);
	}

	public void printTileInfo(){
		for(int x = 0; x<boardWidth; x++){
			for(int y = 0; y<boardHeight; y++){
				System.out.println("x: "+x+"   y: "+y+"   type: "+tileMap[x][y].type);
			}
		}
	}
}