/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesimulation;

public class Position {

	private int x;
	private int y;
	
	/* Constructor of the Position class. */
	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
	
	/* Returns the x-position. */
	public int getX(){
		return this.x;
	}
	
	/* Returns the y-position. */
	public int getY(){
		return this.y;
	}
	
	/* Returns the coordinates of the position
	 * at south of this position. */
	public Position getPosToSouth(){
            
		return new Position(this.x, this.y + 1);
	}
	
	/* Returns the coordinates of the position
	 * at north of this position. */
	public Position getPosToNorth(){
		return new Position(this.x, this.y - 1);
	}
	
	/* Returns the coordinates of the position
	 * at west of this position. */
	public Position getPosToWest(){
		return new Position(this.x - 1, this.y);
	}
	
	/* Returns the coordinates of the position
	 * at east of this position. */
	public Position getPosToEast(){
		return new Position(this.x + 1, this.y);
	}
	
	/* This methods checks if a received object has
	 * the same coordinates as this position. */
	@Override
	public boolean equals(Object o){		
		if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (this.x != position.getX()) return false;
        if (this.y != position.getY()) return false;
		
        return true;
	}
	
	/* Returns unique hash code of this position. */
	@Override
    public int hashCode(){
        int result = (int) (this.x ^ (this.x >>> 2));
        result = 31 * result + (int) (this.y ^ (this.y >>> 2));
        return result;
    }
}
