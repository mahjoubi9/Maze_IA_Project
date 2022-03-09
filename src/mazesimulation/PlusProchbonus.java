/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesimulation;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Stack;

/* This is a robot witch uses a memory to remember
 * where it has been earlier. */
public class PlusProchbonus extends Joueur{

	
	private Stack<Position> positionStack;
	private HashMap<Position, Position> visitedHashMap;
        public  static ArrayList<Position> positiondfs ;
	private Position neighbors[];
	
	/* Constructor of DfsJoueur. Initiate the fields. */
	public PlusProchbonus(Maze maze){
		super(maze);
		this.visitedHashMap = new HashMap<Position, Position>();
		this.positionStack = new Stack<Position>();
		this.neighbors = new Position[4];
                
               this.positiondfs = new ArrayList<Position>();
		this.visitedHashMap.put(this.getCurrentPosition(), this.getCurrentPosition());
	}

	
	/* This method overrides the default move method in the
	 * super class.*/
	@Override
	public void move(){

		this.neighbors[0] = this.getCurrentPosition().getPosToNorth();
		this.neighbors[1] = this.getCurrentPosition().getPosToEast();
		this.neighbors[2] = this.getCurrentPosition().getPosToWest();
		this.neighbors[3] = this.getCurrentPosition().getPosToSouth();
		
		boolean culDeSac = true; /* Represents, if the robot is at a dead end or not. */
		
		/* Checks all neighbors, to see if any neighbor is available
		 * and that the robot hasn't been at that position before. */
		for(Position position : neighbors){
			if (this.maze.isMovable(position) && !this.visitedHashMap.containsKey(position)){
				culDeSac = false;
				this.positionStack.push(this.getCurrentPosition()); 		/* Adds the current position to the stack. */
				this.positiondfs.add(this.getCurrentPosition());
                               /*  for (int i =0;i<positiondfs.size();i++) {
                   
               System.out.println("ffffffffffffffffff");
                        System.out.println("runSimulationmemory : X ="+ positiondfs.get(i).getX());
                        System.out.println("runSimulationmemory : Y ="+ positiondfs.get(i).getY());
                
            } */
                                this.visitedHashMap.put(position, position); 				/* Adds upcoming position to the visitedHashMap. */
				this.setCurrentPosition(position); 							/* Set the current position to the upcoming position. */
				break;
			}
		}
		
		/* If the robot is at a dead end, it pops the top position of
		 * the stack, and moves to that position.
		 * If the stack is empty, the programs gives a error message. */
		if(culDeSac){
			try{
				this.setCurrentPosition(this.positionStack.pop());	
                        this.positiondfs.remove(this.getCurrentPosition());
			}catch(EmptyStackException e){
				throw new Error("MemoryRobot can't reach goal!");
			}
		}
	}

    public void setPositionStack(Stack<Position> positionStack) {
        this.positionStack = positionStack;
    }

    public void setVisitedHashMap(HashMap<Position, Position> visitedHashMap) {
        this.visitedHashMap = visitedHashMap;
    }

    public void setPositiondfs(ArrayList<Position> positiondfs) {
        this.positiondfs = positiondfs;
    }

    public void setNeighbors(Position[] neighbors) {
        this.neighbors = neighbors;
    }

    public Stack<Position> getPositionStack() {
        return positionStack;
    }

    public HashMap<Position, Position> getVisitedHashMap() {
        return visitedHashMap;
    }

    public ArrayList<Position> getPositiondfs() {
        return positiondfs;
    }

    public Position[] getNeighbors() {
        return neighbors;
    }
}