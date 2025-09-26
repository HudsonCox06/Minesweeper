public class Tile{
	public boolean hidden = true;
	public boolean isFlagged = false;
	public int type;
	public Tile(){
		hidden = true;
		isFlagged = false;
		type = 0;
	}
	public void setType(int t){
		type = t;
	}

	public boolean isBomb(){
		if(type==-1){
			return true;
		}
		else{
			return false;
		}
	}
}