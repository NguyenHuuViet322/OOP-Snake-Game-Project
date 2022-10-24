package com.boss;

import com.util.Image;
import com.util.State.FieldObject;
import java.util.Random;
import com.elements.*;

public class BlockadeCreator extends MoveSetController {
	private int x, y;
	private Random rng;
	
	public BlockadeCreator(int cd, int delay, int duration) {
		super(cd, delay, duration);
		rng = new Random();
	}
	
	public void cast() {
		super.cast();
	}
	
	@Override
	public void choose() { 
		if (delay==DELAY)
			do {
				x = rng.nextInt(35);
				y = rng.nextInt(20);
			} while(GameEngine.field[y][x] != FieldObject.Blank);
			GameEngine.field[y][x] = FieldObject.LockOnObstacles;
		if (delay==10) GameEngine.obstacleSpriteCount=2;
		if (delay==9) GameEngine.obstacleSpriteCount=2;
		if (delay==8) GameEngine.obstacleSpriteCount=2;
		if (delay==7) GameEngine.obstacleSpriteCount=2;
		if (delay==6) GameEngine.obstacleSpriteCount=3;
		if (delay==5) GameEngine.obstacleSpriteCount=3;
		if (delay==4) GameEngine.obstacleSpriteCount=4;
		if (delay==3) GameEngine.obstacleSpriteCount=4;
		if (delay==2) GameEngine.obstacleSpriteCount=5;
		if (delay==1) GameEngine.obstacleSpriteCount=5;
	}
	
	@Override
	public void create() {								//create pernament blockade and reset
		GameEngine.field[y][x] = FieldObject.Obstacles;
		GameEngine.tile[y][x].setIcon(Image.obstaclesImage[1]);
	}
	
	@Override
	public void cleanup() {
		cd = CD;
		duration = DURATION;
		delay = DELAY;
	}
}
