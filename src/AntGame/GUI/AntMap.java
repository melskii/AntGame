    /*
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
    
    private JTable tournamentTable;
    
    private JLabel lblWinner;
    
    
    
    /*
    public static void main(String[] args) {
        
        AntMap m = new AntMap();  
    }*/

    
    public AntMap(LinkedHashMap<String, Object> game)
    {
        super("Ant Map");
        
        this.game = game; 
        this.reRun = new ReRun(true);
        
        createAndShowGUI();
    }


    private  void createAndShowGUI() {
 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        if (game.containsKey("World"))
        {
            System.out.println(game.get("World"));
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
        currentPlayers[0] = "Player 1";
        currentPlayers[1] = "Player 2";        
        playerStats = new String[2][2];
        
        playerStats[0][0] = "Kills";
        playerStats[0][1] = "Kills";
        playerStats[1][0] = "Food";
        playerStats[1][1] = "Food";
        
        scoreboardTable = new JTable (playerStats, currentPlayers);
        
        JPanel scorePanel = new JPanel();
        scorePanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        add(scorePanel, BorderLayout.EAST);
        scorePanel.setPreferredSize(new Dimension(250, getHeight()));
        
        GroupLayout layout = new GroupLayout(scorePanel);
        scorePanel.setLayout(layout);
        
        JScrollPane scrollTable = new JScrollPane(scoreboardTable);
        
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
                if (reRun.rerun())
                {
                    runGame();
                }
                else {
                    JOptionPane.showMessageDialog(mapPanel, "A game is already currently running");
                }
                
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

        boolean start = false;

        JOptionPane.showMessageDialog(mapPanel, "Running the Game");
        control.remove(control);

        //remove(mapPanel);

        try {
            //As the world will always be the end file.
            for (int i = 1; i < (game.size()); i ++)
            {

                String _key = "Brain " + i;

                System.out.println("_key = " + _key);
                
                AntBrain _brain = (AntBrain)game.get(_key);
                _brain = _brain.getCopyAntBrain();
                game.put(_key, _brain);

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

        
        System.out.println("Run Game");
        
        if (start)
        {
            
            run.setText("Re-run");
            reRun.setReRun(false);
            
            
        
            RunnableGame run = new RunnableGame(game, mapPanel, statusLabel, reRun);
            Thread runThread = new Thread(run);
            runThread.start();

            
            paintloop:
            for (int i = 0; i < 20000; i++)
            {
                remove(mapPanel);

                mapPanel = new MapPanel(world);
                add(mapPanel);
              
                if (reRun.rerun())
                {
                    runThread.interrupt();
                    break paintloop;
                }

                pack();
            }
            
            System.out.println("Final Re_run " + reRun.rerun());
            //pack();
            
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
            
            System.out.println(gen.x + " , " + gen.y);
            
            System.out.println("hit here");
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

