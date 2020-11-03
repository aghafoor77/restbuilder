package com.ri.se.generator;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ri.se.generator.entities.Defination;

public class IOOperations {
	public void write(String className, String code) {
		File file = new File(className);

		System.err.println("...............................................................");
		System.out.println("Creating " + file.getName() + " at " + file.getParent() + " . . . ");
		try (FileOutputStream fos = new FileOutputStream(file);
				BufferedOutputStream bos = new BufferedOutputStream(fos)) {

			bos.write(code.getBytes());
			bos.close();
			fos.close();
			System.out.println("" + file.getName() + " file created successfully at " + file.getParent() + " !");

		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Problems when creating " + file.getName() + " file at " + file.getParent() + " !");
		}
	}

	public Defination createDefination() {
		System.err.println("...............................................................");
		System.err.println("");
		File file = new File("genrest.json");
		System.out.println("Reading 'genrest.json' from " + file.getAbsolutePath() + " . . . ");

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String json = usingBufferedReader("genrest.json");
			Defination def = objectMapper.readValue(json, Defination.class);
			System.out.println("" + file.getName() + "  successfully loaded for creating defiantion from "
					+ file.getAbsolutePath() + " !");
			System.err.println("");
			return def;

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
			return null;
		}
	}

	private String usingBufferedReader(String filePath) throws IOException {
		StringBuilder contentBuilder = new StringBuilder();
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String sCurrentLine;
		while ((sCurrentLine = br.readLine()) != null) {
			contentBuilder.append(sCurrentLine).append("\n");
		}
		return contentBuilder.toString();
	}

}
