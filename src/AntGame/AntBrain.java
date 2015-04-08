
package AntGame;

import AntGame.Tokens.*;
import AntGame.exceptions.*;
import java.util.LinkedList;
import java.util.ArrayList;
import java.io.*;



/**
 * @author ms660
 */

public class AntBrain {

    private String colour;
    private AntWorld antWorld;
    private Graph instructions;
    private LinkedList<Ant> ants;
    private int currentAnt;


    /**
     * Constructor takes a file as input and checks if the input file is a valid Ant Brain.
     * 
     * @param b .ant File 
     * @throws IOException 
     */
    public AntBrain (File b) throws IOException, AntBrainException
    {

        ArrayList<String> brain = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader(b));
        
        String line = null;
        
        while ((line = br.readLine()) != null) {
            
            //System.out.println(line);
            brain.add(line);
            
        }
        
        try {
        
            instructions = new Graph();
            validAntBrain(brain);
                    
         } catch (AntBrainException e) 
         {
             throw new AntBrainException("Ant Brain not valid: " + e.getMessage());
         }
    
        
         System.out.println(brain.size() + " instructions loaded");
        

    }

    /**
     * Validates each instruction in the Ant Brain and to our instructions if valid, throws an exception otherwise.
     * 
     * @param brain 
     * @return true if valid, throws an exception if not
     * @throws AntBrainException when brain is not valid
     */
    public boolean validAntBrain(ArrayList<String> brain) throws AntBrainException
    {
        
        Instruction i;
        int j = 0;
        int range = brain.size();
        
        while (j < brain.size())
        {
            
            String inst = brain.get(j);
            String[] line = brain.get(j).split(" ");
            
            
            //Sense(SenseDirection sd, int state1, int state2, Condition c)
            //[0-9][0-9]* : Only positive whole numbers allowed.            
            if (inst.matches("Sense \\w+ [0-9][0-9]* [0-9][0-9]* \\w+( [0-5])*"))
            {
                String _sensedir = line[1];
                int _state1 = Integer.parseInt(line[2]);
                int _state2 = Integer.parseInt(line[3]);
                String _cond = line[4];
                
                
                
                SenseDirection sd;
                Condition c;
               
                //SenseDirection (Here|Ahead|LeftAhead|RightAhead)
                if (_sensedir.equals("Here"))
                {
                    sd = new Here();
                }
                else if (_sensedir.equals("Ahead"))
                {
                    sd = new Ahead();
                }
                else if (_sensedir.equals("LeftAhead"))
                {
                    sd = new LeftAhead();
                }
                else if (_sensedir.equals("RightAhead"))
                {
                    sd = new RightAhead();
                }
                else {
                    
                    throw new AntBrainException("Line " + (j+1) + " not a valid instruction (" + inst + ") Should be one of the following: Here, Ahead, LeftAhead, RightAhead");
                }
                
                //_state1 and _state2 validation
                if (_state1 >= range || _state2 >= range)
                {
                    throw new AntBrainException("Line " + (j+1) + " not a valid instruction (" + inst + ") States should be less than " + range);
                }
                
                if (_cond.equals("Friend"))
                {
                    c = new Friend();
                }
                else if (_cond.equals("Foe"))
                {   
                    c = new Foe();        
                }
                else if (_cond.equals("FriendWithFood"))
                {   
                    c = new FriendWithFood();        
                }
                else if (_cond.equals("FoeWithFood"))
                {   
                    c = new FoeWithFood();        
                }
                else if (_cond.equals("Food"))
                {   
                    c = new Food();        
                }
                else if (_cond.equals("Rock"))
                {   
                    c = new Rock();        
                }
                else if (_cond.equals("Marker") && line.length == 6)
                {   
                    c = new Marker(colour, Integer.parseInt(line[5]));        
                }
                else if (_cond.equals("FoeMarker"))
                {   
                    c = new Marker(getFoeColour(), -1);        
                }
                else if (_cond.equals("Home"))
                {   
                    c = new Home();
                }
                else if (_cond.equals("FoeHome"))
                {   
                    c = new FoeHome();
                }
                
                else {
                    
                    throw new AntBrainException("Line " + (j+1) + " not a valid instruction (" + inst + "). Not a valid Condition");
                    
                }
                
                i = new ISense(sd, _state1, _state2, c);
              
            }
            
            //Mark (int marker, int state)
            else if (inst.matches("Mark [0-5] [0-9][0-9]*"))
            {
                
                int _marker = Integer.parseInt(line[1]);
                int _state = Integer.parseInt(line[2]);
                
                Marker marker = new Marker(colour, _marker);
                
                 //_state1 and _state2 validation
                if (_state >= range)
                {
                    throw new AntBrainException("Line " + (j+1) + " not a valid instruction (" + inst + ") States should be less than " + range);
                }
                
                i = new IMark(marker, _state);
                
            }
            
            //Unmark (int marker, int state)
            else if (inst.matches("Unmark [0-5] [0-9][0-9]*"))
            {
                
                int _marker = Integer.parseInt(line[1]);
                int _state = Integer.parseInt(line[2]);
                
                Marker marker = new Marker(colour, _marker);
                
                 //_state1 and _state2 validation
                if (_state >= range)
                {
                    throw new AntBrainException("Line " + (j+1) + " not a valid instruction (" + inst + ") States should be less than " + range);
                }
                
                i = new IUnmark(marker, _state);
            }
            
            //Pickup (int state1, int state2)
            else if (inst.matches("PickUp [0-9][0-9]* [0-9][0-9]*"))
            {
                int _state1 = Integer.parseInt(line[1]);
                int _state2 = Integer.parseInt(line[2]);
                
                 //_state1 and _state2 validation
                if (_state1 >= range || _state2 >= range)
                {
                    throw new AntBrainException("Line " + (j+1) + " not a valid instruction (" + inst + ") States should be less than " + range);
                }
                
                i = new IPickup(_state1, _state2);
            }
            
            //Drop (int state)
            else if (inst.matches("Drop [0-9][0-9]*"))
            {
                int _state = Integer.parseInt(line[1]);
                
                 //_state1 and _state2 validation
                if (_state >= range)
                {
                    throw new AntBrainException("Line " + (j+1) + " not a valid instruction (" + inst + ") States should be less than " + range);
                }
                
                i = new IDrop(_state);
            }
            
            //Turn (Left_or_Right lr, int state)
            else if (inst.matches("Turn (Left|Right) [0-9][0-9]*"))
            {
                String _lr = line[1];
                int _state = Integer.parseInt(line[2]);
                
                Left_or_Right lr;
                
                //_state1 and _state2 validation
                if (_state >= range)
                {
                    throw new AntBrainException("Line " + (j+1) + " not a valid instruction (" + inst + ") States should be less than " + range);
                }
                
                if (_lr.equals("Left"))
                {
                    lr = new Left();
                }
                else
                {
                    lr = new Right();
                }
                
                i = new ITurn(lr, _state);
                
            }
            
            //Move (int state1, int state2)
            else if (inst.matches("Move [0-9][0-9]* [0-9][0-9]*"))
            {
                int _state1 = Integer.parseInt(line[1]);
                int _state2 = Integer.parseInt(line[2]);
                
                 //_state1 and _state2 validation
                if (_state1 >= range || _state2 >= range)
                {
                    throw new AntBrainException("Line " + (j+1) + " not a valid instruction (" + inst + ") States should be less than " + range);
                }
                
                i = new IMove(_state1, _state2);
            }
            
            //Flip (int state1, int state2)
            else if (inst.matches("Flip [0-9][0-9]* [0-9][0-9]* [0-9][0-9]*"))
            {
                int _flip =  Integer.parseInt(line[1]);
                int _state1 = Integer.parseInt(line[2]);
                int _state2 = Integer.parseInt(line[3]);
                
                if (_state1 >= range || _state2 >= range)
                {
                    throw new AntBrainException("Line " + (j+1) + " not a valid instruction (" + inst + ") States should be less than " + range);
                }
                
                i = new IFlip(_flip, _state1, _state2);
                
            }
            
            else {
             
                 throw new AntBrainException("Line " + (j+1) + " not a valid instruction (" + inst + ")");
                
            }
          
            
            instructions.insert(j, i);
            
            j++;
        }
     
        return true;
    }

    /**
     * Sets the AntWorld the Brain is playing in and populates the Ants in the AntHill
     * 
     * @param world AntWorld
     */
    public void setAntWorld (AntWorld world)
    {
        this.antWorld = world;
        populateAnts();
    }

    /**
     * Sets the colour of the AntBrain and all of the Ants belonging to that AntBrain
     * 
     * @param colour 
     */
    public void setColour (String colour)
    {
        this.colour = colour;
        
        for (Ant a : ants)
        {
            a.setColour(colour);
        }
    }

    /**
     * Returns the teams colour
     * 
     * @return the teams colour
     */
    public String getColour()
    {
        return this.colour;
   
    }

    
    /**
    * Returns the next instructions available.
    * @param state
    * @return set of next Instructions
    */
   
    /*
    public Instruction[] getNextInstructions(int state)
    {
        return instructions.getNextStates(state);
        
    }
    */
    
    public void populateAnts(){
        
        
    }
    
    public void step(){
        
        
        
        
        
    }
    
  
    
    /**
     * Returns the other teams colour
     * 
     * @param c Current team colour
     * @return Opposite teams colour
     */
    private String getFoeColour()
    {
        if (colour == "Black")
        {
            return "Red";
        }
        else {
            return "Black";
        }
    }
    
}
