package AntGame;

/**
 * Created by ms660.
 */

import AntGame.Tokens.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Graph {


    private ArrayList<Instruction> vertex;
    private HashMap<Integer, ArrayList<Integer>> edge;

    /**
     * Constructor for the Graph class
     */
    public Graph()
    {
        this.vertex = new ArrayList<Instruction>();
        this.edge = new HashMap<Integer, ArrayList<Integer>>();
    }

    /**
     * Inserts a vertex into the graph
     * 
     * @param i index 
     * @param inst Instruction
     */
    public void insert (int i, Instruction inst)
    {
        vertex.add(i, inst);
        insertEdge(i, inst);
    }

    /**
     * Returns an instruction from the graph
     * 
     * @param i index
     * @return Instruction
     */
    public Instruction getState (int i)
    {
       return vertex.get(i);
    }

    /**
     * Inserts connecting edges to other vertexes
     * @param i vertex index
     * @param inst vertex instruction
     */
    public void insertEdge(int i, Instruction inst)
    {
        ArrayList<Integer> edges = inst.getStates();
        edge.put(i, edges);
    }
    
    /**
     * Returns the next states (edges)
     * 
     * @param current Index of the current state
     * @return the adjacent states to  the current state
     */
    public Instruction[] getNextStates(int current)
    {
        ArrayList<Integer> state = edge.get(current);
        Instruction[] next = new Instruction[state.size()];
        
        int i = 0;
        
        for (int j : state)
        {
            next[j] = getState(j);
            i++;
        }
        
        return next;
        
    }
    
    /**
     * Returns the size of the instructions.
     * @return the size of the instructions
     */
    public int size()
    {
        return vertex.size();
                
    }

}
