
package AntGame;

import AntGame.Tokens.*;
import AntGame.exceptions.*;
import java.util.LinkedList;
import java.util.ArrayList;
import java.io.*;
import java.util.Random;



/**
 * @author ms660
 */

public class AntBrain {

    private String colour;
    private AntWorld antWorld;
    private Graph instructions;
    private LinkedList<Ant> ants;
    private int currentAnt;
    private int deadCount;
    
    private File file;


    /**
     * Constructor takes a file as input and checks if the input file is a valid Ant Brain.
     * 
     * @param b .ant File 
     * @throws IOException 
     */
    public AntBrain (File b) throws IOException, AntBrainException
    {

        this.file = b;
        
        ArrayList<String> brain = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader(b));
        
        String line = null;
        
        while ((line = br.readLine()) != null) {
            
            //System.out.println(line);
            brain.add(line);
            
        }
        
       
        instructions = new Graph();
        validAntBrain(brain);

        ants = new LinkedList<Ant>();       

    }
    
    /**
     * Create a new brain from another existing AntBrain
     * @param brain AntBrain
     * @throws IOException
     * @throws AntBrainException 
     */
    public AntBrain getCopyAntBrain ()  throws IOException, AntBrainException
    {
        System.out.println("generated new brain");
        AntBrain _new =  new AntBrain (file);
        
        return _new;
    }
    
    
    /**
     * 
     * @return Returns text for the label in the GUI
     */
    public String getBrainLabel()
    {
        return instructions.size() + " instructions loaded";
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
                    c = new FoeMarker();        
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
    public void setAntWorld (AntWorld world) throws AntBrainException
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
    
    
    /**
     * Populate the Ant hill Positions for that team with new Ants
     */
    public void populateAnts() throws AntBrainException {
        
        ArrayList<Position> anthill = antWorld.getAntHill(colour);
        
        System.out.println("AntHill size: " + anthill.size());
        
        for (int i = 0; i < anthill.size(); i++)
        {
            Ant ant = new Ant(colour);
            
            ants.add(ant);
            ant.setPosition(anthill.get(i));
            
            try 
            {
                anthill.get(i).addAnt(ant);
            }
            catch (PositionException e) {
                e.getMessage();
            }
        }
        
    }
    
    /**
     * Calculates the amount of food in AntBrains anthill
     * @return food in the Anthill
     */
    public int getBrainScore() throws AntBrainException
    {
        int score = 0;
      
        ArrayList<Position> anthill = antWorld.getAntHill(colour);
        
        for (int i = 0; i < anthill.size(); i++)
        {
            score += anthill.get(i).getFood();
        }
        
        return score;
    }
    
    
    public void step() throws AntException, PositionException
    {
        
        
        
        
        if (deadCount != ants.size())
        {

            Ant ant = ants.get(currentAnt);
            

            if (!ant.isAlive())
            {
                //System.out.println("Step");
                //System.out.println(": Ant Dead");
                if (!ant.countAsDead())
                {
                    deadCount++;
                    ant.updateDeadForAntBrain();
                }
                
                currentAnt = ((currentAnt+1) % ants.size());   
                System.out.println("Dead hit here");
                step();
                
                
            }
            
            else {
                
                //System.out.println("Step (Ant: " + currentAnt + " State: " + ants.get(currentAnt).getState() + " Direction: " + ants.get(currentAnt).getDirection() + ")" );
                
                if (!ant.resting())
                {
                    Instruction state = instructions.getState(ant.getState());
                    Position antPos = ant.getPosition();
                    
                    //System.out.println(antPos);
                    
                    if (state instanceof ISense)
                    {
                        
                        ISense _sense = (ISense)state;
                        
                        ///System.out.println(": State Sense " + _sense.sensedir.getClass());
                        
                        //System.out.println("pos: " + antPos);
                        
                        Position _sensed = null;
                        
                        try {
                            _sensed = antWorld.sensed_cell(antPos, ant.getDirection(), _sense.sensedir);
                        } catch (PositionException e)
                        {
                            System.out.println(e.getMessage());
                        }
                        
                        if (_sensed != null) {
                           // System.out.println(_sensed.toString());
                        }
                        
                        if (_sensed != null && _sensed.cellMatches(_sense.cond, colour))
                        {
                            ant.setState(_sense.state1);
                        }
                        else {
                            ant.setState(_sense.state2);
                        }
                        
                    }
                    
                    else if (state instanceof IMark)
                    {
                        
                        //System.out.println(": State Mark");
                        
                        IMark _mark = (IMark)state;
                        
                        try {
                            antPos.setMarker(_mark.marker);
                        } 
                        
                        catch (Exception e) {
                            
                            String _msg = e.getMessage();
                            System.out.println(_msg);
                        }
                        
                        ant.setState(_mark.state);
                    }
                    
                    else if (state instanceof IUnmark)
                    {
                        //System.out.println(": State Unmark");
                        
                        IUnmark _unmark = (IUnmark) state;
                        
                        antPos.clearMarker(_unmark.marker);
                        
                        ant.setState(_unmark.state);
                    }
                    
                    else if (state instanceof IPickup)
                    {
                        //System.out.println(": State Pickup");
                        
                        IPickup _pickup = (IPickup) state;
                        
                        if (ant.hasFood() || !antPos.hasFood())
                        {
                            ant.setState(_pickup.state2);
                        }
                        
                        else {
                            
                            try {
                                antPos.removeFood();
                                ant.setFood();
                            } catch (Exception e)
                            {
                                String _msg = e.getMessage();
                                System.out.println(_msg);
                            }
                            ant.setState(_pickup.state1);
                        }
                    }
                    
                    else if (state instanceof IDrop)
                    {
                        //System.out.println(": State Drop");
                        
                        IDrop _drop = (IDrop) state;
                        
                        if (ant.hasFood())
                        {
                            
                            try {
                                antPos.addFood(1);
                                ant.clearFood();
                            } catch (Exception e) {
                                String _msg = e.getMessage();
                            } 
                        }
                        
                        ant.setState(_drop.state);
                    }
                    
                    else if (state instanceof ITurn)
                    {
                        //System.out.println(": State Turn");
                        
                        ITurn _turn = (ITurn)state;
                        
                        try {
                        ant.turn(_turn.turn);
                        }
                        catch (Exception e) {
                            String _msg = e.getMessage();
                        }
                        ant.setState(_turn.state);
                    }
                    
                    else if (state instanceof IMove)
                    {
                        //System.out.println(": State Move");
                        
                        IMove _move = (IMove)state;
                        
                        //System.out.println(antPos);
                        //System.out.println(ant.getDirection());
                        
                        Position _newPos = antWorld.adjacentCell(antPos, ant.getDirection());
                        
                        
                        //System.out.println("new pos: " + _newPos);
                        
                        if (_newPos == null || (_newPos.getRocky() || _newPos.getAnt() != null))
                        {
                            ant.setState(_move.state2);
                        }
                        else {
                            
                            antPos.clearAnt();
                            ant.setPosition(_newPos);
                            
                            ant.setState(_move.state1);
                            ant.updateResting();
                            
                            try {
                                
                                _newPos.addAnt(ant);
                                
                            }
                            
                            catch (Exception e)
                            {
                                String _msg = e.getMessage();
                                System.out.println(_msg);
                            }
                            
                            
                            if (antWorld.isSurrounding(antPos, ant.getColour()))
                            {
                                System.out.println(": Killed an ant");
                                
                            }
                            
                            
                        }
                    }
                    
                    
                    //come back to this. Look at number theory
                    else if (state instanceof IFlip)
                    {
                        //System.out.println(": State Flip");
                        
                        IFlip _flip = (IFlip)state;
                        
                        Random ran = new Random();
                        
                        if (ran.nextInt(_flip.flip) == 0)
                        {
                            ant.setState(_flip.state1);
                        }
                        
                        else {
                            
                            ant.setState(_flip.state2);
                            
                    }
                        
                        
                        
                        
                        
                }
                        
                }
                else {
                    
                    //System.out.println(": Ant Resting");
                    
                    ant.updateResting();
                }
               
                currentAnt = ((currentAnt+1) % ants.size());
               
            }
                    
        }
    }
    
    /**
     * Returns the other teams colour
     * 
     * @param c Current team colour
     * @return Opposite teams colour
     */
    public String getFoeColour()
    {
        if (colour == "Black")
        {
            return "Red";
        }
        else {
            return "Black";
        }
    }
    
    /**
     * Get the ant dead count
     * @return dead count
     */
    public String getDeadCount()
    {
        return deadCount + " dead out of " + ants.size();
    }
    
   
    
  
    
    
    
    
    
}
