/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesimulation;

/* This is an abstract parent class Joueur */
public abstract class Joueur {

	private Position position;
	protected Maze maze;
	
	/* Constructor of the abstract class Joueur. */
	public Joueur(Maze maze){
		this.maze = maze;
		this.position = maze.getStartPosition();
	}
	
	/* The abstract method move is used by the child
	 * classes. */
	public abstract void move();
	
	/* Return the current position of the robot. */
	public Position getCurrentPosition(){
		return this.position;
	}
	
	/* Sets the current position of the robot. */
	protected void setCurrentPosition(Position position){
		this.position = position;
	}
	
	/* This method check if the robot has reached the goal. */
	public boolean hasReachedGoal(){		
		return this.maze.isGoal(this.position);
	}
}