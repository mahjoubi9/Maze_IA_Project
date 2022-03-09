/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesimulation;

import java.awt.Color;
import javax.swing.JPanel;


public class Coloration extends JPanel {

	private static final long serialVersionUID = 1L;

	public void setWall() { 
		this.setBackground(Color.BLACK);
		
	}

	public void setEmpty() {
		this.setBackground(Color.WHITE);
		
	}

	public void setRobot() {
		this.setBackground(Color.RED);
		
	}
        public void setGreen() {
		this.setBackground(Color.GREEN);
		
	}

	public void setGoal() {
		this.setBackground(Color.GREEN);
		
	}
        public void setBounus() {
		this.setBackground(Color.PINK);
		
	} 
        public void settransparant() {
		this.setBackground(new Color(1f,0f,0f,.1f));
		
	}
        
        public void setObstacle() {
		this.setBackground(Color.ORANGE);
		
	}
        


}
