package tech.asmussen.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BionicReading {
	
	public static void main(String[] args) {
		
		if (args.length > 0) { // Check if the program should read from a file.
			
			int fileArgumentIndex = -1;
			int outputArgumentIndex = -1;
			
			for (int i = 0; i < args.length; i++) {
				
				if (args[i].equals("-f")) fileArgumentIndex = i;
				else if (args[i].equals("-o")) outputArgumentIndex = i;
			}
			
			if (fileArgumentIndex == -1 || outputArgumentIndex == -1) {
				
				getHelp();
				
				return;
			}
			
			String filePath = args[fileArgumentIndex + 1];
			String outputPath = args[outputArgumentIndex + 1];
			
			try {
				
				BufferedReader reader = new BufferedReader(new FileReader(filePath));
				BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath));
				
				String line;
				
				while ((line = reader.readLine()) != null) {
					
					writer.write(toBionicText(line) + "\n");
				}
				
				reader.close();
				writer.close();
				
				System.out.println("Successfully converted file.");
				
			} catch (IOException e) {
				
				throw new RuntimeException("File not found, exiting...");
			}
			
		} else {
			
			getHelp();
		}
	}
	
	public static String toBionicText(String text) {
		
		StringBuilder output = new StringBuilder();
		
		for (String word : text.split(" ")) {
			
			StringBuilder wordOutput = new StringBuilder();
			
			int middle = (int) Math.ceil((double) word.length() / 2);
			
			String bigWord = word.substring(0, middle);
			String smallWord = word.substring(middle); // Start from the middle and continue to the end.
			
			wordOutput.append("<b>").append(bigWord).append("</b>").append(smallWord);
			
			output.append(wordOutput).append(" ");
		}
		
		return output.toString();
	}
	
	public static void getHelp() {
		
		System.out.println("Arguments:");
		System.out.println("-f /path/to/file.txt");
		System.out.println("-o /path/to/output.html");
		System.out.println();
		System.out.println("Usage:");
		System.out.println("java -jar BionicReading.jar -f /path/to/file.txt -o /path/to/output.html");
	}
}
