 /**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame.GUI;


import AntGame.*;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

/**
 *
 * @author ms660
 */
public class AntMap extends JFrame {

    final JFileChooser fc = new JFileChooser();     
    
    private AntWorld world;
    private LinkedHashMap game;
    private int x;
    private int y;
    
    private JMenu control;
    private JMenuItem run;
    private ReRun reRun;
    private JLabel statusLabel;    
    private MapPanel mapPanel;
    private JTable scoreboardTable;
    private String currentPlayers[];
    private String playerStats[][];
    private HashMap<Integer, AntBrain> tournamentScores;
    private JScrollPane scrollTable;
    private boolean tournament;
    
    
    private JLabel lblWinner;
    
    
    
    /*
    public static void main(String[] args) {
        
        AntMap m = new AntMap();  
    }*/

    
    public AntMap(LinkedHashMap<String, Object> game, boolean tournament)
    {
        super("Ant Map");
        
        this.game = game; 
        this.reRun = new ReRun(true);
        this.tournament = tournament;
        
        createAndShowGUI();
        
        //System.out.println("tournament = " + tournament);
    }


    private  void createAndShowGUI() {
 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        if (game.containsKey("World"))
        {
            
            world = (AntWorld)game.get("World");
            
        }

        mapPanel = new MapPanel(world);
        
        createMenuBar();

        JPanel statusPanel = new JPanel();
        statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        add(statusPanel, BorderLayout.SOUTH);
        statusPanel.setPreferredSize(new Dimension(getWidth(), 16));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
        statusLabel = new JLabel("Run Game to begin");
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusPanel.add(statusLabel);
        
        currentPlayers = new String[2];
        currentPlayers[0] = "Player";
        currentPlayers[1] = "Player";        
        
        playerStats = new String[4][2];
        
        playerStats[0][0] = "Player:";
        playerStats[0][1] = "Player:";
        playerStats[1][0] = "Colour:";
        playerStats[1][1] = "Colour:";
        playerStats[2][0] = "Score:";
        playerStats[2][1] = "Score:";
        playerStats[3][0] = "Dead:";
        playerStats[3][1] = "Dead:";
        
        
        scoreboardTable = new JTable (playerStats, currentPlayers);
        
        JPanel scorePanel = new JPanel();
        scorePanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        add(scorePanel, BorderLayout.EAST);
        scorePanel.setPreferredSize(new Dimension(250, getHeight()));
        
        GroupLayout layout = new GroupLayout(scorePanel);
        scorePanel.setLayout(layout);
        
        scrollTable = new JScrollPane(scoreboardTable);
        
        scrollTable.setPreferredSize(new Dimension(25, 25));
        
        scoreboardTable.enable(false);
        
        JLabel lblScoreboard = new JLabel("Scoreboard");
        //JLabel lblWin = new JLabel("Winner: ");
        lblWinner = new JLabel("Winner: ");
        lblScoreboard.setFont (lblScoreboard.getFont ().deriveFont (24.0f));
        
        //lblWin.setFont (lblScoreboard.getFont ().deriveFont (14.0f));
        lblWinner.setFont (lblScoreboard.getFont ().deriveFont (14.0f));
        
        
        String columns[] = new String[2];
        columns[0] ="Round";
        columns[1] = "Winner";
        String rows[][] = new String[1][1];
        
        rows[0][0] = "Round 1";
        
        //tournamentTable = new JTable(rows, columns);
        
        //JScrollPane scrollTournament = new JScrollPane(tournamentTable);
        //tournamentTable.enable(false);
        
        
        
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                //.addComponent(lblWinner)
                
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblScoreboard)
                    //.addComponent(lblWin)
                    .addComponent(lblWinner)
                //.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)                             
                    .addComponent(scrollTable))
                    //.addComponent(scrollTournament))
                    
         );
        
         layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(lblScoreboard)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                 
                    //.addComponent(lblWin)
                    .addComponent(lblWinner))
                .addComponent(add(scrollTable))
                //.addComponent(add(scrollTournament))
                //.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    //
                     
                     
                //     
                 //    .addComponent(lblWinner))
                 //   .addComponent(c3))
               //.addComponent(c4)
         );

        
        
        //scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.X_AXIS));
        //statusLabel = new JLabel("Run Game to begin");
        //statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        //scorePanel.add(statusLabel);
        scorePanel.add(scrollTable, BorderLayout.NORTH);


        add(mapPanel);
        
        //f.setContentPane(contentPane);
        //f.add(new MapPanel());

        pack();

        setVisible(true);

        //setResizable(false);
        setLocationRelativeTo(null);  

    }

        
     /**
    * Creates a new Menu bar for the frame.
    */
    
    public void createMenuBar()
    {
        JMenuBar mb = new JMenuBar();
        JMenuItem reset = new JMenuItem("Reset");
        JMenuItem exit = new JMenuItem("Exit");
        
        control = new JMenu("File");
        
        run = new JMenuItem("Run");
        
        
        
        
        run.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e)
            {
                //if (reRun.rerun())
                //{
                    runGame();
                //}
               // else {
                    
               // }
                
            }
        });
        
        exit.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e)
            {
                int exit = JOptionPane.showOptionDialog(mapPanel, 
                        "Are you sure you want to exit?", 
                        "Exit?", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, 
                        null, null, null);
                 // interpret the user's choice
                
                if (exit == JOptionPane.YES_OPTION)
                {
                  close();
                  System.exit(0);
                }
                
            }
        });
        
        reset.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e)
            {
                if (!reRun.rerun())
                {
                    JOptionPane.showMessageDialog(mapPanel, "A game is already currently running");
                }
                else {
                    
                            
                    int reset = JOptionPane.showOptionDialog(mapPanel, 
                            "Are you sure you want to reset?", 
                            "Reset?", 
                            JOptionPane.YES_NO_OPTION, 
                            JOptionPane.QUESTION_MESSAGE, 
                            null, null, null);
                     // interpret the user's choice

                    if (reset == JOptionPane.YES_OPTION)
                    {
                        close();
                        reRun.setReRun(true);
                        AntGameRun t = new AntGameRun();
                        t.mainFrame();
                    } 
                }

            }
        });
        


        //Add in the shortest control.

        control.add(run);
        control.add(reset);
        control.add(exit);
        
        
        mb.add(control);
        
        setJMenuBar(mb);      


    }
    
    public void runGame()
    {
        if (!reRun.rerun())
        {
            JOptionPane.showMessageDialog(mapPanel, "A game is already currently running");
        }
        
        else {
        
            boolean start = true;

            statusLabel.setText("Setting up the Game...");
            //control.remove(run);

            //remove(mapPanel);

            try {
                //As the world will always be the end file.
                for (int i = 1; i < (game.size()); i ++)
                {

                    String _key = "Brain " + i;
                    //System.out.println("_key = " + _key);

                    AntBrain _brain = (AntBrain)game.get(_key);
                    _brain = _brain.getCopyAntBrain();
                    game.put(_key, _brain);
                    
                    //System.out.println("Copy: " + "_brain = " + _brain);
                    
                }
                
                

                AntWorld _world = (AntWorld)game.get("World");
                _world = _world.getCopyAntWorld();
                world = _world;

                game.put("World", _world);

                start = true;


            }

            catch (Exception e)
            {
                System.out.println(e.getMessage());

                JOptionPane.showMessageDialog(mapPanel, "Error");
            }


            //System.out.println("Run Game");

            if (start)
            {

                statusLabel.setText("Running the game...");

                run.setText("Re-run");
                reRun.setReRun(false);

                AntBrain b1 = (AntBrain)game.get("Brain 1");
                AntBrain b2 = (AntBrain)game.get("Brain 2");
                AntWorld w = (AntWorld)game.get("World");
                
                //System.out.println("_brain 1 = " + b1);
                
                Game gamerun = null;
                
                try {
                    
                    gamerun  = new Game(b1, b2 , w);
                    
                
                    
                } catch (Exception e)
                {
                    JOptionPane.showMessageDialog(mapPanel, "Unable to run the game");
                }
                
                Thread runThread = null;
                boolean startThread = false;
                
                
                if (gamerun != null)
                {
                    RunnableGame run = new RunnableGame(gamerun, mapPanel, statusLabel, reRun, lblWinner, scoreboardTable, game, scrollTable);
                    runThread = new Thread(run);
                    
                }
                
     
                if (world.xlength > 0 && gamerun != null)
                {
                    
                    paintloop:
                    for (int i = 0; i < 50000; i++)
                    {
                        
                        
                        remove(mapPanel);

                        mapPanel = new MapPanel(world);
                        add(mapPanel);
  
                        
                        if (startThread) {
                        
                       
                            if (gamerun.getRemainingMoves() == -1)
                            {
                                 updateScoreboard();

                            } 
                        }
                    
                        
                        
                        //updateScoreboard();
                       // System.out.println("reRun.rerun() = " + reRun.rerun());
                        
                        //System.out.println(runThread.getState());
                        
                        if (gamerun.getRemainingMoves() == -1)//
                        {                            
                            System.out.println("hit rerun");
                            
                            runThread.interrupt();
                            pack();
                            //System.out.println("End reRun.rerun() = " + reRun.rerun());
                            break paintloop;
                        }


                    }
                    
                    if (!startThread)
                    {
                        startThread = true;
                        //System.out.println("Hit here thread start");
                        
                        runThread.start();
                         
                       // System.out.println("Hit here thread after start"); 
                        updateScoreboard();
                        
                    }
                    
                    
                    
          
                    pack();
                }
           
            }
        }
        
        
        
        
        
    }
    
    
    public void updateScoreboard()
    {
        if (world.xlength > 0)
        {
            //System.out.println("hit here");
            
            scrollTable.remove(scoreboardTable);

            for (int j = 1; j < (game.size()); j++)
            {

                String _key = "Brain " + j;
                //System.out.println("_key = " + _key);

                AntBrain _brain = (AntBrain)game.get(_key);
                
                //System.out.println(_brain);

                //try {

                    playerStats[0][j-1] = "Player: " + j;
                    playerStats[1][j-1] = "Colour: " + _brain.getColour();
                    //System.out.println("_brain.getColour() = " + _brain.getColour());
                    
                    playerStats[3][j-1] = "Dead: " + _brain.getDeadCount();
                    
                   // System.out.println("_brain = " + _brain);

                    

                try {
                    playerStats[2][j-1] = "Score: " + _brain.getBrainScore();
                    //System.out.println("_brain.getBrainScore() = " + _brain.getBrainScore());
                } catch (Exception ex)
                {
                   System.out.println("Error: " + ex.getMessage());
                }


            }



            scoreboardTable = new JTable (playerStats, currentPlayers);

            scoreboardTable.enable(false);

            scrollTable.add(scoreboardTable);

            scoreboardTable.repaint();
            scrollTable.repaint();

            pack();    
            
        }
        
    }
  
    public void generateAntWorld(File f)
    {
        AntWorldGenerator gen = new AntWorldGenerator();
        
        try {
            world = gen.antWorldGenerator(f);
            
            remove(mapPanel);
            
            
            mapPanel = new MapPanel(world);
            add(mapPanel);
            
            pack();
            
            //System.out.println(gen.x + " , " + gen.y);
            
            //System.out.println("hit here");
        }
        catch (Exception e)
        {
           System.out.println(e.getMessage());
        }
        
        
        
        
    }
    
     /**
     * Exits the JFrame
     */
    public void close()
    {
        setVisible(false);
        dispose();
                
    }

    
    
}

