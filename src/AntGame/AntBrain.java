
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
                
                if (line.length == 6)
                {
                    String _marker = line[5];
                }
                
                SenseDirection sd;
                
               
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
                
                
                
              
            }
            
            //Mark (int marker, int state)
            else if (inst.matches("Mark [0-5] [0-9][0-9]*"))
            {
                
            }
            
            //Unmark (int marker, int state)
            else if (inst.matches("Unmark [0-5] [0-9][0-9]*"))
            {
                
            }
            
            //Pickup (int state1, int state2)
            else if (inst.matches("PickUp [0-9][0-9]* [0-9][0-9]*"))
            {
                
            }
            
            //Drop (int state)
            else if (inst.matches("Drop [0-9][0-9]*"))
            {
                
            }
            
            //Turn (Left_or_Right lr, int state)
            else if (inst.matches("Turn (Left|Right) [0-9][0-9]*"))
            {
                
            }
            
            //Move (int state1, int state2)
            else if (inst.matches("Move [0-9][0-9]* [0-9][0-9]*"))
            {
                
            }
            
            //Flip (int state1, int state2)
            else if (inst.matches("Flip [0-9][0-9]* [0-9][0-9]* [0-9][0-9]*"))
            {
                
            }
            
            else {
             
                 throw new AntBrainException("Line " + (j+1) + " not a valid instruction (" + inst + ")");
                
            }
            
            
            j++;
        }
        
        
        //
        
        /*

        boolean valid = true;
        Instruction token;

        int index = 0;

        while (valid && index < brain.size())
        {

            String i = brain.get(index);
            String[] list = i.split(" ");


            if (i.matches("Mark [0-5] \\d+"))
            {

                int m = Integer.parseInt(list[1]);
                Marker marker = new Marker(colour, m);

                int state = Integer.parseInt(list[2]);

                token = new IMark(marker, state);
                instructions.insertInstruction(index, token);
            }

            else if  (i.matches("Unmark [0-5] \\d+"))
            {

                int m = Integer.parseInt(list[1]);
                Marker marker = new Marker(colour, m);

                int state = Integer.parseInt(list[2]);

                token = new IUnmark(marker, state);
                instructions.insertInstruction(index, token);
            }

            else if (i.matches("PickUp \\d+ \\d+"))
            {
                int state1 = Integer.parseInt(list[1]);
                int state2 = Integer.parseInt(list[2]);

                token = new IPickUp(state1, state2);
                instructions.insertInstruction(index, token);
            }


            else if (i.matches("Drop \\d+"))
            {

                int state = Integer.parseInt(list[1]);

                token = new IDrop(state);
                instructions.insertInstruction(index, token);
            }

            /*
            else if (i.matches("Turn [Left|Right] \\d+"))
            {
                Left_or_Right lr;

                if (list[1].matches("Left"))
                {
                    lr = new Left();
                }
                else {
                    lr = new Right();
                }

                token = new iTurn(lr, list[2]);
                instructions.insertInstruction(index, token);

            }

            else if (i.matches("Move \\d+ \\d+"))
            {
                token = new iMove(list[1], list[2]);
                instructions.insertInstruction(index, token);
            }

            else if (i.matches("Flip \\d+ \\d+ \\d+"))
            {
                token = new iFlip(list[1], list[2], list[3]);
                instructions.insertInstruction(index, token);
            }

            else if (i.matches("Sense \\w+  \\d+ \\d+ \\w+"))
            {
                SenseDirection sd = new SenseDirection();
                Condition c = new Condition();

                if (list[0].matches("Here")){
                    sd = new Here();
                }
                else if (list[0].matches("Ahead")) {
                    sd = new Ahead();
                }
                else if (list[0].matches("LeftAhead")){
                    sd = new LeftAhead();
                }
                else if (list[0].matches("RightAhead")) {
                    sd = new RightAhead();
                }
                else {
                    valid = false;
                }


                //Check the conditions
                if (valid){

                    if (list[3].matches("Friend"))
                    {
                        c = new Friend();
                    }

                    else if (list[3].matches("Foe"))
                    {
                        c = new Foe();
                    }

                    else if (list[3].matches("FriendWithFood")) {

                        c = new FriendWithFood();

                    }

                    else if (list[3].matches("FoeWithFood")) {

                        c = new FoeWithFood();

                    }

                    else if (list[3].matches("Food")) {

                        c = new Food();

                    }

                    else if (list[3].matches("Rock")) {

                        c = new Rock();

                    }

                    else if (list[3].matches("Marker")) {

                        c = new Marker();


                    }

                    else if (list[3].matches("FoeMarker")) {

                        c = new FoeMarker();

                    }

                    else if (list[3].matches("Home")) {

                        c = new Home();

                    }

                    else if (list[3].matches("FoeHome")) {

                        c = new FoeHome();

                    }

                    else {
                        valid = false;
                    }


                }


                if (valid) {

                    token = new Sense(sd, list[1], list[2], c);
                    instructions.insertInstruction(index, token);

                }

            }
            

            index++;
        }

        */

        return true;
    }

    public void setAntWorld (AntWorld world)
    {
        this.antWorld = world;
    }


    public void setColour (String c)
    {
        this.colour = c;
    }

    public String getColour()
    {
        return this.colour;
    }

    public void populateAnts()
    {

    }

    public void step()
    {

    }

    public Instruction getNextInstruction()
    {
        return null;
    }

    public int returnRandomInt()
    {
        return 0;
    }
    
}
