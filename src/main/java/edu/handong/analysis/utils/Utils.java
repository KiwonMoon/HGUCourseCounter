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
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Utils {

	public static ArrayList<String> getLines(String file,boolean removeHeader) throws IOException {
		
		ArrayList<String> lines = new ArrayList<String>();
		
		
		/*String thisLine="";
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
			lines.remove(0);*/
		
		try (
				Reader reader = Files.newBufferedReader(Paths.get(file));
				CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
			){
				int i=0;
				for(CSVRecord csvRecord : csvParser) {
					String line = csvRecord.get(0) + "," + csvRecord.get(1) + "," + csvRecord.get(2) + "," + csvRecord.get(3) + "," + csvRecord.get(4) + "," + csvRecord.get(5) + "," + csvRecord.get(6) + "," + csvRecord.get(7) + "," + csvRecord.get(8);
					if(i!=0)
						lines.add(line);
					i++;
				}
			}
		
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
