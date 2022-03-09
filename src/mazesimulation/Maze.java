/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesimulation;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/* This class represents a Maze */
public class Maze {
		
	private Position startPosition;
	public HashMap<Position,Character> mazeData;
        private ArrayList<Position> listbonus = new ArrayList<Position>();
        private ArrayList<Position> listobstacle = new ArrayList<Position>();
        private ArrayList<Position> listmurs = new ArrayList<Position>();
        
        

    public ArrayList<Position> getListobstacle() {
        return listobstacle;
    }

    public void setListobstacle(ArrayList<Position> listobstacle) {
        this.listobstacle = listobstacle;
    }
        

    public void setStartPosition(Position startPosition) {
        this.startPosition = startPosition;
    }

    public void setListmurs(ArrayList<Position> listmurs) {
        this.listmurs = listmurs;
    }

    public void setMazeData(HashMap<Position, Character> mazeData) {
        this.mazeData = mazeData;
    }

    public ArrayList<Position> getListmurs() {
        return listmurs;
    }

    public HashMap<Position, Character> getMazeData() {
        return mazeData;
    }
	
	/* Constructor of class maze, receives a reader and
	 * uses it to hash the values representing the
	 * different parts of the maze. */
	public Maze(FileReader reader){
		this.mazeData = new HashMap<Position,Character>();

		try {
			int data, x = 0, y = 0;
			char dataChar;
						
			while( (data = reader.read() ) != -1 ){	/* While the reader hasn't reached end of file. */
				
				if (data == 10){					/* If the reader has reached new line. */
					x = 0;
					y++;
				}else{
		    		dataChar = (char) data; 		/* Else convert ASCI to Character and add the character to mazeDatata. */
		    		Position posee = new Position(x, y);
                                this.mazeData.put(posee, dataChar);
		    		
		    		if(dataChar == 'S'){
		    			if(this.startPosition == null){
		    				this.startPosition = new Position(x, y);
		    			}else{
			    			throw new Error("The maze contains to meny start positions.\n" +
			    					"Please recheck the maze-file and make sure that there is one S and atleast one G represented in the maze-file.");
		    			}
		    		}else if(dataChar=='B'){
                                    if(!this.getListbonus().contains(posee))
                                     this.getListbonus().add(posee);
                                    /*for(int i =0 ; i<this.getListbonus().size();i++){
                                         System.out.println("XBounus="+this.getListbonus().get(i).getX());
                                    System.out.println("YBounus="+this.getListbonus().get(i).getY());
                                    } */
                                  
                                }else if(dataChar=='█'){
                              
                                     this.getListmurs().add(posee);
                                    
                                     /*for(int i =0 ; i<this.getListbonus().size();i++){
                                        System.out.println(this.getListbonus().size());
                                         System.out.println("Xmur="+this.getListmurs().get(i).getX());
                                    System.out.println("Ymur="+this.getListmurs().get(i).getY());
                                    } */
                                  
                                }else if(dataChar=='O'){
                                    if(!this.getListobstacle().contains(posee))
                                     this.getListobstacle().add(posee);
                                    /*for(int i =0 ; i<this.getListobstacle().size();i++){
                                         System.out.println("XObstacle="+this.getListobstacle().get(i).getX());
                                    System.out.println("YObstacle="+this.getListobstacle().get(i).getY());
                                    } */
                                  
                                }
		    		x++;
				}
			}
                        
			reader.close();
                        for(int i =0 ; i<this.getListobstacle().size();i++){
                                         System.out.println("XObstacle="+this.getListobstacle().get(i).getX());
                                    System.out.println("YObstacle="+this.getListobstacle().get(i).getY());
                                    } 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(this.startPosition == null || !this.mazeData.containsValue('G')){
			throw new Error("The maze does not contain goal positions and/or a start position.\n" +
					"Please recheck the maze-file and make sure that there is one S and atleast one G represented in the maze-file.");
		}
	}

    public void setListbonus(ArrayList<Position> listbonus) {
        this.listbonus = listbonus;
    }

    public ArrayList<Position> getListbonus() {
        return listbonus;
    }
	
	/*A method to check whether a position it movable */
	public boolean isMovable(Position position){
		try{
			return (this.mazeData.get(position) != '█');
		}catch(NullPointerException e){
			return false;		/* If the the position does not exist, return false. */
		}
	}
        public boolean isBounus(Position position){
		try{
			return (this.mazeData.get(position) != 'B');
		}catch(NullPointerException e){
			return false;		/* If the the position does not exist, return false. */
		}
	}
	
	/* Check to see if a specific position is the goal position. */
	public boolean isGoal(Position position){
		try{
			return (this.mazeData.get( position ) == 'G');
		}catch(NullPointerException e){
			return false;		/* If the the position does not exist, return false. */
		}
	}
        
        public boolean isObstacle(Position position){
		try{
			return (this.mazeData.get( position ) == 'O');
		}catch(NullPointerException e){
			return false;		/* If the the position does not exist, return false. */
		}
	}
	
	/* Gets and returns the position of startPosition. */
	public Position getStartPosition(){
		return this.startPosition;
	}
}
