/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesimulation;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Stack;

/* This is a robot witch uses a memory to remember
 * where it has been earlier. */
public class ClavierSimulation extends Joueur {

    private Stack<Position> positionStack;
    private HashMap<Position, Position> visitedHashMap;
    private Position pos;
    private Position lastPosition = new Position(1, 1);
    public  static int score =0 ;

  

    public enum RobotType {
        Memory, RightHand, Random, clavier, Right, Left, South, North
    }
    private MazeSimulation.RobotType type;

    /* Constructor of MemoryRobot. Initiate the fields. */
    public ClavierSimulation(Maze maze) {
        super(maze);
        this.visitedHashMap = new HashMap<Position, Position>();
        this.positionStack = new Stack<Position>();
        this.visitedHashMap.put(this.getCurrentPosition(), this.getCurrentPosition());
    }

    public ClavierSimulation(Maze maze, MazeSimulation.RobotType r) {
        super(maze);
        type = r;
        pos = null;
        System.out.println(type.toString());
        this.visitedHashMap = new HashMap<Position, Position>();
        this.positionStack = new Stack<Position>();
        this.visitedHashMap.put(this.getCurrentPosition(), this.getCurrentPosition());
        pos = this.getCurrentPosition();
        
        if (type == MazeSimulation.RobotType.North) {
            //this.pos = this.getCurrentPosition().getPosToNorth();
            Position pose = this.getCurrentPosition();
              this.pos.setY(pose.getY());

           lastPosition.setX(pose.getX());
           lastPosition.setY(pose.getY());
            //for(int m=0;m<this.getMaze().getListmurs().size();m++){
              //  System.out.println("ddddddddddddddddddddddddd"+this.getMaze().getListmurs().get(m).getY());
                if(this.getMaze().isMovable(new Position(pose.getX(), pose.getY()-1))){
                 this.pos.setY(pose.getY() - 1);

           lastPosition.setX(pose.getX());
           lastPosition.setY(pose.getY() + 1);
            ///////////////////////
            //    }
              
            }
          
            for(int i =0 ;i<this.getMaze().getListbonus().size();i++){
                 if(lastPosition.equals(this.maze.getListbonus().get(i))){
                score+=5;
                  System.out.println("*****************************************************************");
                System.out.println("Votre Score est :" + score);
                
            }
                System.out.println("Position X= " + lastPosition.getX());
                System.out.println("Position Y= " + lastPosition.getY());
            }
            
             for(int i =0 ;i<this.getMaze().getListobstacle().size();i++){
                 if(lastPosition.equals(this.maze.getListobstacle().get(i))){
                score-=2;
                 System.out.println("*****************************************************************");
                System.out.println("Votre Score devient :" + score);
                
            }
                System.out.println("Position X= " + lastPosition.getX());
                System.out.println("Position Y= " + lastPosition.getY());
             }  
        }
        
        if (type == MazeSimulation.RobotType.Left) {
            // this.pos = this.getCurrentPosition().getPosToEast();
            Position pose = this.getCurrentPosition();
     this.pos.setX(pose.getX());

            lastPosition.setX(pose.getX());
            lastPosition.setY(pose.getY());
             if(this.getMaze().isMovable(new Position(pose.getX() + 1, pose.getY()))){
                    this.pos.setX(pose.getX() + 1);

            lastPosition.setX(pose.getX() - 1);
            lastPosition.setY(pose.getY());
             }
            //System.out.println(this.getMaze().getMazeData().containsValue("B"));
            for(int i =0 ;i<this.getMaze().getListbonus().size();i++){
                 if(lastPosition.equals(this.maze.getListbonus().get(i))){
                // this.setScore(this.getScore()+1);
                score+=5;
                  System.out.println("*****************************************************************");
                System.out.println("Votre Score est :" + score);
            }

            System.out.println("Position X= " + lastPosition.getX());
            System.out.println("Position Y= " + lastPosition.getY());
            }
           
             for(int i =0 ;i<this.getMaze().getListobstacle().size();i++){
                 if(lastPosition.equals(this.maze.getListobstacle().get(i))){
                score-=2;
                 System.out.println("*****************************************************************");
                System.out.println("Votre Score devient :" + score);
                
            }
                System.out.println("Position X= " + lastPosition.getX());
                System.out.println("Position Y= " + lastPosition.getY());
             }
        }
        
        
        if (type == MazeSimulation.RobotType.Right) {
            //this.pos = this.getCurrentPosition().getPosToWest();
            Position pose = this.getCurrentPosition();
            this.pos.setX(pose.getX());

            lastPosition.setX(pose.getX());
            lastPosition.setY(pose.getY());
            
             if(this.getMaze().isMovable(new Position(pose.getX() - 1, pose.getY()))){
              this.pos.setX(pose.getX() - 1);

            lastPosition.setX(pose.getX() + 1);
            lastPosition.setY(pose.getY());
             }
            for(int i =0 ;i<this.getMaze().getListbonus().size();i++){
                 if(lastPosition.equals(this.maze.getListbonus().get(i))){
                // this.setScore(this.getScore()+1);
                score+=5;
                  System.out.println("*****************************************************************");
                System.out.println("Votre Score est :" + score);
            }
                 
             System.out.println("Position X= " + pose.getX());
             System.out.println("Position Y= " + pose.getY());
            }
           
             for(int i =0 ;i<this.getMaze().getListobstacle().size();i++){
                 if(lastPosition.equals(this.maze.getListobstacle().get(i))){
                score-=2;
                 System.out.println("*****************************************************************");
                System.out.println("Votre Score devient :" + score);
                
            }
                System.out.println("Position X= " + lastPosition.getX());
                System.out.println("Position Y= " + lastPosition.getY());
             }
        }
        if (type == MazeSimulation.RobotType.South) {
            // this.pos = this.getCurrentPosition().getPosToSouth();
            Position pose = this.getCurrentPosition();
            this.pos.setY(pose.getY());

            lastPosition.setX(pose.getX());
            lastPosition.setY(pose.getY());
            
             if(this.getMaze().isMovable(new Position(pose.getX(), pose.getY()+1))){
                 this.pos.setY(pose.getY() + 1);

            lastPosition.setX(pose.getX());
            lastPosition.setY(pose.getY() - 1);
             }
           for(int i =0 ;i<this.getMaze().getListbonus().size();i++){
                 if(lastPosition.equals(this.maze.getListbonus().get(i))){
                 //this.setScore(this.getScore()+1);
                 score+=5;
                  System.out.println("*****************************************************************");
                System.out.println("Votre Score est :" + score);
            }
             System.out.println("Position X= " + pose.getX());
             System.out.println("Position Y= " + pose.getY());
           }
           
            for(int i =0 ;i<this.getMaze().getListobstacle().size();i++){
                 if(lastPosition.equals(this.maze.getListobstacle().get(i))){
                score-=2;
                 System.out.println("*****************************************************************");
                System.out.println("Votre Score devient :" + score);
                
            }
                System.out.println("Position X= " + lastPosition.getX());
                System.out.println("Position Y= " + lastPosition.getY());
             }
        }
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Maze getMaze() {
        return maze;
    }

    public int getScore() {
        return score;
    }

    public Stack<Position> getPositionStack() {
        return positionStack;
    }

    public HashMap<Position, Position> getVisitedHashMap() {
        return visitedHashMap;
    }

    public Position getPos() {
        return pos;
    }

    public Position getLastPosition() {
        return lastPosition;
    }

    public MazeSimulation.RobotType getType() {
        return type;
    }

    public void setPositionStack(Stack<Position> positionStack) {
        this.positionStack = positionStack;
    }

    public void setVisitedHashMap(HashMap<Position, Position> visitedHashMap) {
        this.visitedHashMap = visitedHashMap;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public void setLastPosition(Position lastPosition) {
        this.lastPosition = lastPosition;
    }

    public void setType(MazeSimulation.RobotType type) {
        this.type = type;
    }

    
    
    /* This method overrides the default move method in the
	 * super class.*/
    @Override
    public void move() {

        /* Represents, if the robot is at a dead end or not. */

 /* Checks all neighbors, to see if any neighbor is available
		 * and that the robot hasn't been at that position before. */
   System.out.println("///////////////////////////////////////////////////");
        System.out.println("X :"+pos.getX());
          System.out.println("Y :"+pos.getY());
        if (this.maze.isMovable(pos) && !this.visitedHashMap.containsKey(pos) &&pos.equals(new Position(6, 1))){
               System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            this.positionStack.push(this.getCurrentPosition());
            /* Adds the current position to the stack. */
            this.visitedHashMap.put(pos, pos);
            //  System.out.println("Position X= " + pos.getX());
            //  System.out.println("Position Y= " + pos.getY());
            System.out.println("Position");

            /* Adds upcoming position to the visitedHashMap. */
 /*pos.setX(1);
                pos.setY(1); */
            //pos.setX(1);
            // pos.setY(2);
            this.setCurrentPosition(pos);
            // System.out.println("X= " + this.getCurrentPosition().getX());
            // System.out.println("Y= " + this.getCurrentPosition().getY());
            /* Set the current position to the upcoming position. */
        }
    }

    /* If the robot is at a dead end, it pops the top position of
		 * the stack, and moves to that position.
		 * If the stack is empty, the programs gives a error message. */
}
//////////////////////////////////////////////////////////
/*
    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) { */
// int touch = ke.getKeyCode();
//if(touch==KeyEvent.VK_UP)gerant.setHaut(true);
//      if(touch==KeyEvent.VK_DOWN)gerant.setBas(true);
//    if(touch==KeyEvent.VK_LEFT)gerant.setGauche(true);
//  if(touch==KeyEvent.VK_RIGHT)gerant.setDroite(true);

// if (touch == KeyEvent.VK_UP) {
//   System.out.println("hhhhhhhhh");
//  };
//}
//@Override
// public void keyReleased(KeyEvent ke) {
//  int touch = ke.getKeyCode();
//if(touch==KeyEvent.VK_UP)gerant.setHaut(false);
// if(touch==KeyEvent.VK_DOWN)gerant.setBas(false);
// if(touch==KeyEvent.VK_LEFT)gerant.setGauche(false);
// if(touch==KeyEvent.VK_RIGHT)gerant.setDroite(false);
//  if (touch == KeyEvent.VK_UP) {
//      System.out.println("hhhhhhhhh");
//  };
//}
// aajoute un bolean comme setHaut setbas setgauch
