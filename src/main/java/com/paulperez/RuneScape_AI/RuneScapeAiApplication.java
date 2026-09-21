package com.paulperez.RuneScape_AI;

import com.paulperez.RuneScape_AI.AI_Utility.Chunker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RuneScapeAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RuneScapeAiApplication.class, args);

		String testString = "Intro paragraph here.\n" +
				"==Locations==\n" +
				"Location data here.\n" +
				"==Drops==\n" +
				"Drop data here.";
		Chunker chunker = new Chunker();

		chunker.makeChunksList(testString);
	}


}
