package edu.handong.analysis.utils;

import edu.handong.analysis.datamodel.Course;
import java.util.ArrayList;
import java.util.Scanner;//
import java.util.HashMap;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;//
import java.io.DataOutputStream;

/*getLines method는 주어진 file path로 csv파일을 읽어, ArrayList<String> 객체를 리턴해 줍니다.
 * 한 element가 한 line의 string을 가지고 있습니다.
 * 두번째 argument는 true일 경우 첫라인을 ArrayList에 포함을 하지 않도록 하는 옵션입니다.
 * file이름 Command Line Interface로 받
 * 
 * writeAFile은 ArrayList에 저장된 결과 정보를 두번째 argument의 파일에 저장이 되게 하는 것입니다.
 * 만약에 file에 존재하지 않는 디렉터리가 존재할 경우, FileNotFoundException이 발생할 수 있는데,
 * 이를 막기 위해서는 directory가 존재안할 경우 생성을 해주는 논리가 필요합니다.
 * 아래 tip에 hint가 나와 있습니다. */
public class Utils {

	public static ArrayList<String> getLines(String file,boolean removeHeader) {
		
		ArrayList<String> lines = new ArrayList<String>();
		//___________1
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
			File file = new File(targetFileName);
			FileOutputStream fos = new FileOutputStream(file);
			DataOutputStream dos = new DataOutputStream(fos);
			
			for(String line: lines) {
				dos.write((line+",").getBytes());
			}
			dos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
