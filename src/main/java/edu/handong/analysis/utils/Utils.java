package edu.handong.analysis.utils;

import java.util.ArrayList;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;//
import java.io.DataOutputStream;

public class Utils {

	public static ArrayList<String> getLines(String file,boolean removeHeader) {
		
		ArrayList<String> lines = new ArrayList<String>();
		String thisLine="";
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			while ((thisLine = br.readLine()) != null) {
				lines.add(thisLine);
			}
			br.close();
		} catch (IOException e) {
			System.err.println("The file path does not exist. Please check your CLI argument!");
		}
		
		if(removeHeader)
			lines.remove(0);
		
		return lines;
	}
	
	public static void writeAFile(ArrayList<String> lines, String targetFileName) {
		
		try {
			File file = new File("/Users/moonkiwon/Desktop/result.csv");
			FileOutputStream fos = new FileOutputStream(file);
			DataOutputStream dos = new DataOutputStream(fos);
			
			for(String line: lines) {
				dos.write((line+"\n").getBytes());
				
				if(!file.exists()) {
					file.getParentFile().mkdirs();
				}
			}
			dos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
