package com.handler;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.sampled.*;
import javax.swing.JButton;

import com.elements.GUI;
import com.util.Sound;


public class AudioPlayer {
		private Timer fadeTimer = new Timer();
		private AudioInputStream audioInputStream;
		private Clip clip_SE;
		private Clip BGM;
		private boolean muted;
		private FloatControl gainControl;
		float currDB = 0F;
		float targetDB = -20F;
		float fadePerStep = 1F; 
		boolean fading = false;
		boolean phase1Sound = true;
		
		public boolean isPhase1Sound() {
			return phase1Sound;
		}

		public AudioPlayer(boolean muted) {
			this.muted = muted;
		}
		
		public void playBGM(String sound) throws LineUnavailableException, IOException, UnsupportedAudioFileException { //0
	    	if(isMuted())
	    		return;
	    	if(BGM != null)BGM.stop();
	    	audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(sound));
	    	BGM = AudioSystem.getClip();
	    	BGM.open(audioInputStream);
	    	BGM.start();
	    	BGM.loop(Clip.LOOP_CONTINUOUSLY);
	    }
		
		public void playSE(String sound) throws LineUnavailableException, IOException, UnsupportedAudioFileException { //0
	    	if(muted)
	    		return;
	    	else
	    	{
	    		System.out.println(isMuted());
	    		audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(sound));
	    		clip_SE = AudioSystem.getClip();
	    		clip_SE.open(audioInputStream);
	    		clip_SE.start();
	    	}
	    }
		
	    public void mute_SE() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
	    	if(muted)
	    	{
	    		clip_SE.stop();
	    	}
	    	else {
	    		clip_SE.setMicrosecondPosition(0);
	    		clip_SE.start();
	    	}
	    	System.out.println(muted);
	    }
	    
	    public void setVolume(double value) {
	        value = (value<=0.0)? 0.0001 : ((value>1.0)? 1.0 : value);
	        try {
	            float dB = (float)(Math.log(value)/Math.log(10.0)*20.0);
	            gainControl.setValue(dB);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
	    
	    public void fadeTheme()
	    {
	    	if (BGM != null) {
	    		gainControl = (FloatControl)BGM.getControl(FloatControl.Type.MASTER_GAIN);
		    	fadeTimer = new Timer();
		    	
		        if (currDB > targetDB) {
		        	fadeTimer.scheduleAtFixedRate(new TimerTask() {
						  @Override
						  public void run() {
							  currDB -= fadePerStep;
				              gainControl.setValue(currDB);
				              
				              if (currDB <= targetDB) 
				              {
				            	  fadeTimer.cancel();
				            	  BGM.stop();
				            	  gainControl.setValue(0F);
				            	  currDB = 0F;
				              }
						  }
						}, 0, 100);
		        }
	    	} 
	    }
	    
	    public void mute_BGM() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
	    	if(muted)
	    		BGM.stop();
	    	else
		    	BGM.start();
	    }
	    
	    public boolean isMuted() {
	    	return this.muted;
	    }
	    
	    public void setMuted(boolean mute) {
	    	this.muted = mute;
	    }
	}