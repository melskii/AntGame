package AntGame;

/**
 * Created by ms660 on 30/03/2015.
 */

import AntGame.Tokens.Instruction;
import java.util.ArrayList;
import java.util.HashMap;

public class Graph {


    private ArrayList<Instruction> vertex;
    private HashMap<Integer, ArrayList<Integer>> edge;

    public Graph()
    {
        this.vertex = new ArrayList<Instruction>();
        this.edge = new HashMap<Integer, ArrayList<Integer>>();
    }

    public void insertInstruction(int i, Instruction inst)
    {
        vertex.add(i, inst);
        insertEdge(i, inst);
    }

    public Instruction getInstruction (int i)
    {
       return vertex.get(i);
    }

    public void insertEdge(int i, Instruction inst)
    {

        ArrayList<Integer> edgeStates = new ArrayList<Integer>();


        if (inst instanceof iMark) {

            edgeStates.add(iMark.state);
            edge.put(i, edgeStates);
        }

        else if (inst instanceof iUnMark) {

            edgeStates.add(iUnmark.state);
            edge.put(i, edgeStates);

        }



    }






}


