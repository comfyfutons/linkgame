package Board;

public class Line extends Tile{
	private boolean visited;
	private boolean usable;
	private boolean line;
	
	public Line() {
		super("line");
		usable = true;
		visited = false;
		line = false;
	}
	
	public boolean isUsable(){
		return usable;
	}
	
	public void setUsable(boolean usable){
		this.usable = usable;
	}
	
	public boolean isVisited(){
		return visited;
	}
	
	public void setVisited(boolean visited){
		this.visited = visited;
	}
	
	public boolean isLine(){
		return line;
	}
	
	public void toggle(){
		if(line == false){
			line = true;
		} else{
			line = false;
		}
	}
}
