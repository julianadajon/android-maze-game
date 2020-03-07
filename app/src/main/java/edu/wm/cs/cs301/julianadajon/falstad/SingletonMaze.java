package edu.wm.cs.cs301.julianadajon.falstad;

import edu.wm.cs.cs301.julianadajon.generation.MazeConfiguration;

/**
 * Created by julianadajon on 11/27/16.
 */

public class SingletonMaze {

    private static SingletonMaze instance;
    private MazeConfiguration mazeConfig;
    private MazeController mazeController;

    public static SingletonMaze Instance(){
        if (instance == null){
            instance = new SingletonMaze();
        }
        return instance;
    }

    public MazeConfiguration getMazeConfig() {
        return mazeConfig;
    }

    public void setMazeConfig(MazeConfiguration m){
        mazeConfig = m;
    }

    public MazeController getMazeController() {return mazeController;}

    public MazeController setMazeController(MazeController mc) {return mazeController = mc;}

    public void kill() {
        mazeConfig = null;
        mazeController = null;
    }

}
