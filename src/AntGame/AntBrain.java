/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame;

import AntGame.Tokens.Marker;
import AntGame.Tokens.IMark;
import AntGame.Tokens.IUnmark;
import AntGame.Tokens.IPickUp;
import AntGame.Tokens.Instruction;
import AntGame.Tokens.IDrop;
import java.util.LinkedList;
import java.util.ArrayList;


public class AntBrain {

    private String colour;
    private AntWorld antWorld;
    private Graph instructions;
    private LinkedList<Ant> ants;
    private int currentAnt;


    public AntBrain (String b)
    {

        instructions = new Graph();

    }

    public boolean validAntBrain(ArrayList<String> brain)
    {

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
            */

            index++;
        }


        return false;
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
