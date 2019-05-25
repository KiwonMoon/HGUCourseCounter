package edu.handong.analysis.datamodel;

import java.util.ArrayList;
import edu.handong.analysis.utils.Utils;

public class Course {
	private String studentId;
	private String yearMonthGraduated;
	private String firstMajor;
	private String secondMajor;
	private String courseCode;
	private String courseName;
	private String courseCredit;
	private int yearTaken;
	private int semesterCourseTaken;

	// Split the line from constructor to initialize the field.
	//The method split(String) is undefined for the type ArrayList<String>
	/* constructor에서 line을 받아 split해서 field초기화
	 * Self-define getter and setter if needed*/
	public Course(String line) {
		//Course courses = new Course(line);
		ArrayList<String> lines = new ArrayList<String>();
		
		//ArrayList<Course> studentId = new ArrayList<Course>();
		//ArrayList<String> lines = Utils.getLines(line, true);
		
		for(String lineOfCSV: lines) {
			
			studentId = lineOfCSV.split(",")[0].trim();
			yearMonthGraduated = lineOfCSV.split(",")[1].trim();
			firstMajor = lineOfCSV.split(",")[2].trim();
			secondMajor = lineOfCSV.split(",")[3].trim();
			courseCode = lineOfCSV.split(",")[4].trim();
			courseName = lineOfCSV.split(",")[5].trim();
			courseCredit = lineOfCSV.split(",")[6].trim();
			yearTaken = Integer.parseInt(lineOfCSV.split(",")[7].trim());
			semesterCourseTaken = Integer.parseInt(lineOfCSV.split(",")[8].trim());
		}
		
		/*studentId = line.split(",")[0].trim();
		yearMonthGraduated = line.split(",")[1].trim();
		firstMajor = line.split(",")[2].trim();
		secondMajor = line.split(",")[3].trim();
		courseCode = line.split(",")[4].trim();
		courseName = line.split(",")[5].trim();
		courseCredit = line.split(",")[6].trim();
		//yearTaken = line.split(",")[7].trim();
		//semesterCourseTaken = line.split(",")[8].trim();*/
		
		}
	}
	
