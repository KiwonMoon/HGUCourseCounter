package edu.handong.analysis.datamodel;

import java.util.ArrayList;

import org.apache.commons.csv.CSVRecord;

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
	/* constructor에서 line을 받아 split해서 field초기화*/
	public Course(String line) {
		//Course courses = new Course(line);
		ArrayList<String> lines = new ArrayList<String>();
		lines.add(0, line);
		
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
		
		}
	
	public String getStudentId() {
		return studentId;
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	public String getYearMonthGraduated() {
		return yearMonthGraduated;
	}
	
	public String getFirstMajor() {
		return firstMajor;
	}
	
	public String getSecondMajor() {
		return secondMajor;
	}
	
	public String getCourseCode() {
		return courseCode;
	}
	
	public String getCourseCredit() {
		return courseCredit;
	}

	public int getYearTaken() {
		return yearTaken;
	}
	
	public int getSemesterCourseTaken() {
		return semesterCourseTaken;
	}
	
	}
	
