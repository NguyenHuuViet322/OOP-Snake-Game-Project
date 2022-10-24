package com.main;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.elements.GameEngine;

public class SnakeMain {
	public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException, URISyntaxException {
		Thread thread = new Thread() {
			GameEngine window = new GameEngine();
		};
		thread.setPriority((int)(Thread.MAX_PRIORITY*0.8));
		thread.start();
	}
}
