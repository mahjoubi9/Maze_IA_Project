/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesimulation;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TimerTask;


import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


public class MazeSimulation {
	
	public enum RobotType {Memory,RightHand,Random,clavier,Right,Left,South,North,robotbonus}
     
       JFrame thewindows2 ;
       int  time=60;
       boolean timebool = true;
	
	private Coloration[][] mazeDisplay;
	private class RobotButtonListener implements ActionListener {
		private RobotType type;
		private MazeSimulation mazeSimulation;
		
		public RobotButtonListener(RobotType r, MazeSimulation mazeSimulation) {
			type=r;
			this.mazeSimulation=mazeSimulation;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			new Thread(new Runnable(){

				@Override
				public void run() {
					if(type==RobotType.Memory) {
                                             System.out.println("clavier fonctin");
						mazeSimulation.runSimulationmemory(new DfsJoueur(maze));
                                                System.out.println("clavier fonctin");
					
					}else if(type==RobotType.robotbonus) {
                                             System.out.println("clavier fonctin");
						mazeSimulation.runSimulationbonus(new PlusProchbonus(maze));
                                                System.out.println("clavier fonctin");
					
					}else if(type==RobotType.Random){
						mazeSimulation.runSimulation(new JoueurAleatoire(maze));
					}else if(type==RobotType.clavier){
                                            
                                                System.err.println(maze.toString());
                                                System.out.println("clavier fonctin");
						mazeSimulation.runSimulation(new ClavierSimulation(maze));
					}else if(type==RobotType.Left){
                                            
                                                System.out.println("clavier fonctin");
						mazeSimulation.runSimulationClavier(new ClavierSimulation(maze,type));
					}else if(type==RobotType.Right){
                                            
                                                System.out.println("clavier fonctin");
						mazeSimulation.runSimulationClavier(new ClavierSimulation(maze,type));
					}else if(type==RobotType.North){
                                            
						mazeSimulation.runSimulationClavier(new ClavierSimulation(maze,type));
					}else if(type==RobotType.South){
                                            
                                                System.err.println(maze.toString());
						mazeSimulation.runSimulationClavier(new ClavierSimulation(maze,type));
					}
					
				}}).start();
			
		}
		
	}
	
	public static final int MAX_STEPS=2000;
	private Maze maze;
	private JTextField messageField;
        private JTextField scorefield;
        
        private JTextField zoneTime;
	private JPanel mazePanel;
	private Dimension d;
	private static MazeSimulation ms;

	public MazeSimulation() throws InterruptedException {

	JFrame  theWindow;
		File file=null;
		boolean done=false;
		d = null;
		JFileChooser fileChooser;

		//Setup main window
		theWindow = new JFrame("maze");
		theWindow.setSize(900, 900);
		theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    theWindow.setBackground(Color.WHITE);
		mazePanel = new JPanel();
		theWindow.add(mazePanel,BorderLayout.CENTER);
		messageField = new JTextField();
		theWindow.add(messageField,BorderLayout.SOUTH);
		JPanel topPanel=new JPanel();
		topPanel.setLayout(new GridLayout(1,3));
                JPanel rhitPanel=new JPanel();
                rhitPanel.setBackground(Color.GRAY);
               
                JPanel scorepanel=new JPanel();
		JButton memButton = new JButton("Show Goal");
		topPanel.add(memButton);
                JButton memButtonClavier = new JButton("Show bonus");
		topPanel.add(memButtonClavier);
                
		//JButton rightButton = new JButton("RightHandRuleRobot");
		//topPanel.add(rightButton);
	/*	JButton randomButton = new JButton("JoueurAleatoire");
		topPanel.add(randomButton); */
                scorefield = new JTextField();
                scorefield.setBounds(30, 30, 100, 100);
                topPanel.add(scorefield);
		JButton clearButton = new JButton("Clear");
                
                zoneTime = new JTextField();
                zoneTime.setBounds(30, 30, 100, 100);
                topPanel.add(zoneTime);
                
              
                      JButton Rightbutton = new JButton("Left");
		rhitPanel.add(Rightbutton);
                JButton Leftbutton = new JButton("Right");
		rhitPanel.add(Leftbutton);
          
                
                
                JButton southbutton = new JButton("South");
                southbutton.setBounds(600, 30, 300, 30);
		rhitPanel.add(southbutton);
                
                JButton northbutton = new JButton("North");
		rhitPanel.add(northbutton);
		
               
                
               /* clearButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {	
				setupMazePanel();
                                
                                
                                ///////////////////////////////
                                 JPanel panel = new JPanel();
                LayoutManager layout = new FlowLayout();  
                panel.setLayout(layout);
                                   JOptionPane.showMessageDialog(theWindow, "Game Over your score is 0 !","final", JOptionPane.WARNING_MESSAGE);
                                
                                /////////////////////////////
                                
                                
                                
			}}*/
                      //  );
		//topPanel.add(clearButton);
		theWindow.add(topPanel,BorderLayout.NORTH);
                theWindow.add(rhitPanel,BorderLayout.WEST);

		//Load maze
		fileChooser=new JFileChooser();
		FileFilter filter = new FileNameExtensionFilter("Maze file", "maze");
		fileChooser.setFileFilter(filter);
		do {
			int returnVal=fileChooser.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				file = fileChooser.getSelectedFile();
			} else {
				System.exit(0); //Canceled by user
			}
			try {
				d=calcSize(new FileReader(file));
				mazeDisplay=new Coloration[d.width][d.height];
				mazePanel.setLayout(new GridLayout(d.width,d.height));

				//Create a maze from a text file
				maze=new Maze(new FileReader(file));
				done=true;
			} catch (FileNotFoundException e) {
				System.err.println("Unable to open maze file");
			} catch (IOException e) {
				System.err.println("Unable to read maze file");
			}
		} while(!done);
		
		//Configure button actions
		ActionListener memRobot=new RobotButtonListener(RobotType.Memory,this);
		memButton.addActionListener(memRobot);
		ActionListener rightRobot=new RobotButtonListener(RobotType.RightHand,this);
		//rightButton.addActionListener(rightRobot);
		ActionListener randomRobot=new RobotButtonListener(RobotType.Random,this);
		//randomButton.addActionListener(randomRobot);
                ActionListener ButtonClavier=new RobotButtonListener(RobotType.robotbonus,this);
		memButtonClavier.addActionListener(ButtonClavier);
                     ActionListener ButtonRight=new RobotButtonListener(RobotType.Right,this);
		Rightbutton.addActionListener(ButtonRight);
                     ActionListener Buttonleft=new RobotButtonListener(RobotType.Left,this);
		Leftbutton.addActionListener(Buttonleft);
                     ActionListener ButtonSouth=new RobotButtonListener(RobotType.South,this);
		southbutton.addActionListener(ButtonSouth);
                     ActionListener ButtonNourth=new RobotButtonListener(RobotType.North,this);
		northbutton.addActionListener(ButtonNourth);
		
		//Initialize the display
		setupMazePanel();
		theWindow.setVisible(true);
                thewindows2 = theWindow;
                  //////////////////////////////////////////////
                zoneTime.setText("60");
                
              /*  long start = System.currentTimeMillis();
Thread.currentThread().sleep(1000);
System.out.println("Run time : " + (System.currentTimeMillis() - start));
 zoneTime.setText(Integer.toString((int) (System.currentTimeMillis() - start))); */

                     Timer timer = new Timer();
                int millis = 1000; //n*1000 millisecond = n second --> n minutes = n*60*1000
                
                while(time>0)
                {
                   timer.schedule(new TimerTask() {
                  @Override
                  public void run() {
                      System.out.println("ggggfgftyfvyui " );
                     
                      try {
                          Thread.currentThread().sleep(1000);
                      } catch (InterruptedException ex) {
                          Logger.getLogger(MazeSimulation.class.getName()).log(Level.SEVERE, null, ex);
                      }
                                  //    zoneTime.setText(Integer.toString(60-t));
                    time--;
                    zoneTime.setText(Integer.toString(time));
                    //360000-((60-time)*1000)+1000000)
                     if(Integer.parseInt(zoneTime.getText())==0){
                        ///////////////////////////////
                                 JPanel panel = new JPanel();
                LayoutManager layout = new FlowLayout();  
                panel.setLayout(layout);
                                   JOptionPane.showMessageDialog(thewindows2, "Time Over !","final", JOptionPane.WARNING_MESSAGE);
                                time =60;
                                
                    setupMazePanel();
                    timebool= false;
                    
                                /////////////////////////////
                             
                   }
                  }
                }, millis);
                   if(time<59)
                       break; 
                } 
                
                
                
                ///////////////////////////////////////////

	}

	private void setupMazePanel() {
		mazePanel.removeAll();
		for(int i=0;i<d.width;i++) {
			for(int j=0;j<d.height;j++) {
				Coloration dp=new Coloration();
				if(!maze.isMovable(new Position(j,i)))
					dp.setWall();
                                else 
                                    if(!maze.isBounus(new Position(j,i)))
					dp.setBounus();
				else
                                    if(maze.isGoal(new Position(j,i)))
					dp.setGoal();
				else
                                    if(maze.isObstacle(new Position(j,i)))
					dp.setObstacle();
                                else
					dp.setEmpty();
				mazePanel.add(dp);
				mazeDisplay[i][j]=dp;
			}
		}
		mazePanel.doLayout();
	}

	public void runSimulation(Joueur r) {
		
		Position rhRobotPos=r.getCurrentPosition();
		moveRobot(rhRobotPos);
           
		int i=0;
		do { //Simulate the robot moving
			removeRobot(r.getCurrentPosition());
			r.move();
			moveRobot(r.getCurrentPosition());
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				return;
			}
			i++;
		} while(!r.hasReachedGoal() &&
				i<MAX_STEPS);
		if(r.hasReachedGoal())
			updateMessage("Robot reached goal after "+i+" steps");		
	}
        /*
        
        */
        public void runSimulationmemory(DfsJoueur r) {
          
     
		
		Position rhRobotPos=r.getCurrentPosition();
		
           
		moveRobott(rhRobotPos);
		int i=0;
		do { //Simulate the robot moving
			removeRobott(r.getCurrentPosition());
			r.move();
			moveRobott(r.getCurrentPosition());
                       
			i++;
		} while(!r.hasReachedGoal() &&
				i<MAX_STEPS);
		if(r.hasReachedGoal()){
                    
			updateMessage("Robot reached goal after "+i+" steps");	
                                  System.out.println("GGGGGGGGGGGGGGGGGGGGG");
            if(!r.positiondfs.isEmpty()){
                ArrayList<Position> dfsposition = new ArrayList<Position>();
               dfsposition = r.positiondfs;
              // System.out.println(r.positiondfs.get(0).getX());
               
/*ArrayList<Position> lisbonuss = new ArrayList<Position>();
        lisbonuss = this.getMaze().getListbonus();
ArrayList<Position> lisobsracle = new ArrayList<Position>();
lisobsracle = this.getMaze().getListobstacle(); */
               for (int j =0;j<r.positiondfs.size();j++) {
                  //if(r.positiondfs.get(i)) 
               System.out.println("ffffffffffffffffff");
                        System.out.println("runSimulationmemory : X ="+ dfsposition.get(j).getX());
                        System.out.println("runSimulationmemory : Y ="+ dfsposition.get(j).getY());
                        
                       // if(dfsposition.get(j).getX()!=)
                        //mazeDisplay[dfsposition.get(j).getY()][dfsposition.get(j).getX()].setGreen();
                         mazeDisplay[dfsposition.get(j).getY()][dfsposition.get(j).getX()].setGreen();
                         
                
            } 
                Timer timer = new Timer();
                int millis = 5000; //n*1000 millisecond = n second --> n minutes = n*60*1000

                timer.schedule(new TimerTask() {
                  @Override
                  public void run() {
                      System.out.println("hjhjhjhjhjhjhjhjj");
                      setupMazePanel();
                      int aide =Integer.parseInt(scorefield.getText()) -1 ;
                scorefield.setText(Integer.toString(aide));
                      
                      
                  }
                }, millis);
            }
                }	
	}
           public void runSimulationbonus(PlusProchbonus r) {
          
     
		
		Position rhRobotPos=r.getCurrentPosition();
		
           
		moveRobott(rhRobotPos);
		int i=0;
		do { //Simulate the robot moving
			removeRobott(r.getCurrentPosition());
			r.move();
			moveRobott(r.getCurrentPosition());
                       
			i++;
		} while(!r.hasReachedGoal() &&
				i<MAX_STEPS);
		if(r.hasReachedGoal()){
                    
			updateMessage("Robot reached goal after "+i+" steps");	
                                  System.out.println("GGGGGGGGGGGGGGGGGGGGG");
                                  
         ArrayList<Position> listbonus ;
                     listbonus =  this.getMaze().getListbonus();
            if(!listbonus.isEmpty()){
       
              // System.out.println(r.positiondfs.get(0).getX());
               
/*ArrayList<Position> lisbonuss = new ArrayList<Position>();
        lisbonuss = this.getMaze().getListbonus();
ArrayList<Position> lisobsracle = new ArrayList<Position>();
lisobsracle = this.getMaze().getListobstacle(); */

               for (int j =0;j<listbonus.size();j++) {
                  //if(r.positiondfs.get(i)) 
                  /* for (int h =0;h<listbonus.size();h++){
                       if((listbonus.get(j).getX())-r.getCurrentPosition().getX())
                   } */
               System.out.println("ffffffffffffffffff");
                        System.out.println("runSimulationmemory : X ="+ listbonus.get(j).getX());
                        System.out.println("runSimulationmemory : Y ="+ listbonus.get(j).getY());
                        
                       // if(dfsposition.get(j).getX()!=)
                        //mazeDisplay[dfsposition.get(j).getY()][dfsposition.get(j).getX()].setGreen();
                         mazeDisplay[listbonus.get(j).getY()][listbonus.get(j).getX()].setGreen();
                         
                
            } 
                Timer timer = new Timer();
                int millis = 5000; //n*1000 millisecond = n second --> n minutes = n*60*1000

                timer.schedule(new TimerTask() {
                  @Override
                  public void run() {
                      System.out.println("hjhjhjhjhjhjhjhjj");
                      setupMazePanel();
                      int aide =Integer.parseInt(scorefield.getText()) -1 ;
                scorefield.setText(Integer.toString(aide));
                      
                      
                  }
                }, millis);
            }
                }	
	}


    public void setMazeDisplay(Coloration[][] mazeDisplay) {
        this.mazeDisplay = mazeDisplay;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    public void setMessageField(JTextField messageField) {
        this.messageField = messageField;
    }

    public void setScorefield(JTextField scorefield) {
        this.scorefield = scorefield;
    }

    public void setMazePanel(JPanel mazePanel) {
        this.mazePanel = mazePanel;
    }

    public void setD(Dimension d) {
        this.d = d;
    }

    public Coloration[][] getMazeDisplay() {
        return mazeDisplay;
    }

    public static int getMAX_STEPS() {
        return MAX_STEPS;
    }

    public Maze getMaze() {
        return maze;
    }

    public JTextField getMessageField() {
        return messageField;
    }

    public JTextField getScorefield() {
        return scorefield;
    }

    public JPanel getMazePanel() {
        return mazePanel;
    }

    public Dimension getD() {
        return d;
    }
        public void runSimulationClavier(ClavierSimulation r) {
            if(timebool==false){
                r.getPos().setX(0);
                r.getPos().setY(0);
                timebool=true;
                
                
            }
		Position lastosion = r.getLastPosition();
                int sco = r.score;
                if(sco<0){
                    setupMazePanel();
                                
                                
                                ///////////////////////////////
                                 JPanel panel = new JPanel();
                LayoutManager layout = new FlowLayout();  
                panel.setLayout(layout);
                                   JOptionPane.showMessageDialog(thewindows2, "Game Over you lose your score (your score is inferior 0) !","final", JOptionPane.WARNING_MESSAGE);
                                
                                /////////////////////////////
                }
                System.out.println("*************************************************************");
                System.out.println("runSimulationClavier " +sco);
                scorefield.setText("0");
               int scree =Integer.parseInt(scorefield.getText());
               sco += scree;
                scorefield.setText(Integer.toString(sco));
                //updateMessagescore(Integer.toString(sco));
                removeRobot(lastosion);
		Position rhRobotPos=r.getCurrentPosition();
		moveRobot(rhRobotPos);
           
		int i=0;
		do { //Simulate the robot moving
			removeRobot(r.getCurrentPosition());
			r.move();
			moveRobot(r.getCurrentPosition());
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				return;
			}
			i++;
		} while(!r.hasReachedGoal() &&
				i<MAX_STEPS);
		if(r.hasReachedGoal())
			updateMessage("Robot reached goal after "+i+" steps");		
	}

	private void updateMessage(final String string) {
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				messageField.setText(string);

			}

		});

	}
        private void updateMessagescore(final String score) {
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				scorefield.setText(score);

			}

		});

	}
        

	private void removeRobot(final Position pos) {
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				mazeDisplay[pos.getY()][pos.getX()].setEmpty();

			}

		});

	}
        private void removeRobott(final Position pos) {
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {

			}

		});

	}

	private void moveRobot(final Position rhRobotPos) {
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				mazeDisplay[rhRobotPos.getY()][rhRobotPos.getX()].setRobot();

			}

		});
                

	}
        	private void moveRobott(final Position rhRobotPos) {
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {

			}

		});
                

	}

	private Dimension calcSize(Reader r) throws IOException {
		int maxX=0;
		int maxY=0;
		BufferedReader in = new BufferedReader(r);
		String str;
		while((str=in.readLine())!=null) {
			maxY++;
			if(maxX<str.length())
				maxX=str.length();
		}
		in.close();
		return new Dimension(maxY,maxX);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException {

		setMs(new MazeSimulation());
	}

	public static MazeSimulation getMs() {
		return ms;
	}

	public static void setMs(MazeSimulation ms) {
		MazeSimulation.ms = ms;
	}
}