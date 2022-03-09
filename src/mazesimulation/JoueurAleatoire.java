/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesimulation;

import java.util.ArrayList;
import java.util.Random;

/*The JoueurAleatoire class represents a robot that uses the
 * method 'random' to find its way to the goal. */
public class JoueurAleatoire extends Joueur{

	private Position neighbors[];
	private Position prevPosition;
	private ArrayList<Position> positionList;
	private Random rand;
	
	/* Constructor of JoueurAleatoire, initiate the fields. */
	public JoueurAleatoire(Maze maze) {
		super(maze);
		this.positionList = new ArrayList<Position>();
		this.neighbors = new Position[4];
		this.prevPosition = this.getCurrentPosition();
		this.rand = new Random();
	}

	
	/*This method overrides the default method in the
	 * super class.*/
	@Override
	public void move() {
		
		this.neighbors[0] = this.getCurrentPosition().getPosToNorth();
		this.neighbors[1] = this.getCurrentPosition().getPosToEast();
		this.neighbors[2] = this.getCurrentPosition().getPosToWest();
		this.neighbors[3] = this.getCurrentPosition().getPosToSouth();
		
		/*Puts all the available neighbors, except the previous position,
		 * in an arrayList. */
		for (Position position: neighbors){
			if (this.maze.isMovable(position) && !position.equals(prevPosition)){
				this.positionList.add(position);
			}
		}
				
		Position position = this.prevPosition;  /* Set the default value of position, witch
		 										 * is later set as the current position of
		 										 * the robot*/
		
		/*If the number of positions in the positionList,
		 * witch contains the available positions,
		 * is less then one element: Set the upcoming position
		 * to a random position from positionList. */
		
		if (this.positionList.size() > 1){
			position = positionList.get( this.rand.nextInt( this.positionList.size() ) );
		
		/*Else if the number of positions in the positionList
		 * equals one: Set the upcoming position to that position.*/
		}else if (this.positionList.size() == 1){
			position = this.positionList.get(0);
		}
		
		this.prevPosition = this.getCurrentPosition();
		this.setCurrentPosition(position);
		
		this.positionList.clear();
	}
	
}