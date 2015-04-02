/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame;


public class AntWorld {
    
    Position[][] antworld;
    
    public AntWorld(int x, int y) {   //might be better to have a contructor with antworld[][] parameter to be passed from generator already made rocky etc 
        
        antworld = new Position[x][y];
        
    }
    
    public Position getPosition(int x, int y) { //superfluous?
        return antworld[x][y];
    }
    
    public Position getPosition(Coords c) {
        int x = c.getX();
        int y = c.getY();
        
        return antworld[x][y];
    }
    
    public void killAnt(Coords c) {
        int x = c.getX();
        int y = c.getY();
        
        antworld[x][y].clearAnt();
        antworld[x][y].addFood(5);
    }
    
    public Ant getAnt(Coords c) {
        int x = c.getX();
        int y = c.getY();
        
        return antworld[x][y].getAnt();
    }
    
    public boolean hasAnt(Coords c) {
        int x = c.getX();
        int y = c.getY();
        
        return (!(antworld[x][y].getAnt == null)); //Ugly because Position has no hasAnt()
    }
    
    public boolean isSurrounded(Coords c) { //I did this wrong, doesn't check colour doh
        
        boolean surrounded = true;
        
        for(int i = 0; i < 6; i++) {
            Coords p = adjacentCell(c, i);
            if (!hasAnt(p)) {
                surrounded = false;
            }
            
        }
        return surrounded;
    }
    
    public Coords adjacentCell(Coords c, int dir){  
        int x = c.getX();
        int y = c.getY();
        int newX = x;
        int newY = y;
        
        switch(dir){
            
            case 0:
                newX = x + 1;
                newY = y;
                break;
                
            case 1:
                if (y % 2 == 0) {
                    newX = x;
                    newY = y + 1;
                    break;
                }
                else {
                    newX = x + 1;
                    newY = y + 1;
                    break;
                }
                
            case 2: 
                if (y % 2 == 0) {
                    newX = x - 1;
                    newY = y + 1;
                    break;
                }
                else {
                    newX = x;
                    newY = y + 1;
                    break;
                }
                
            case 3:
                newX = x - 1;
                newY = y;
                break;
                
            case 4:
                if (y % 2 == 0) {
                    newX = x - 1;
                    newY = y -1;
                    break;
                }
                else {
                    newX = x;
                    newY = y - 1;
                    break;
                }
                
            case 5:
                if (y % 2 == 0) {
                    newX = x;
                    newY = y - 1;
                    break;
                }
                else {
                    newX = x + 1;
                    newY = y - 1;
                    break;
                }
        }
        
        //return antworld[newX][newY];
        
        return new Coords(newX, newY);
        
    }
}
