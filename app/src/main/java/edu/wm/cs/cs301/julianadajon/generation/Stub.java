package edu.wm.cs.cs301.julianadajon.generation;
import edu.wm.cs.cs301.julianadajon.falstad.Constants;
import edu.wm.cs.cs301.julianadajon.falstad.Constants.StateGUI;
import edu.wm.cs.cs301.julianadajon.generation.Order;

public class Stub implements Order {
	
	private MazeConfiguration mazeConfig; 
	private int skill;
	private Builder builder;
	private Boolean perfect; 

	
	public Stub(){
	}
	
	public Stub(MazeConfiguration mazeConfig, int skill, Builder builder, Boolean perfect){
		this.mazeConfig = mazeConfig;
		this.skill = skill;
		this.builder = builder;
		this.perfect = perfect;

		
		
	}
	
	@Override
	public int getSkillLevel() {

		return skill;
	}

	@Override
	public Builder getBuilder() {

		return builder;
	}

	@Override
	public boolean isPerfect() {
	
		return perfect;
	}

	@Override
	public void deliver(MazeConfiguration mazeConfig) {

		 this.mazeConfig = mazeConfig;
		 
		
	}

	public void updateProgress(int percentage) {}

	public MazeConfiguration getMazeConfiguration() {
		return mazeConfig;
	}
}
