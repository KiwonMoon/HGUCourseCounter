package edu.handong.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import edu.handong.analysis.datamodel.Course;
import edu.handong.analysis.datamodel.Student;
import edu.handong.analysis.utils.NotEnoughArgumentException;
import edu.handong.analysis.utils.Utils;

public class HGUCoursePatternAnalyzer {

	private HashMap<String,Student> students;
	
	/**
	 * This method runs our analysis logic to save the number courses taken by each student per semester in a result file.
	 * Run method must not be changed!!
	 * @param args
	 */
	public void run(String[] args) {
		
		try {
			// when there are not enough arguments from CLI, it throws the NotEnoughArgmentException which must be defined by you.
			if(args.length<2)
				throw new NotEnoughArgumentException();
		} catch (NotEnoughArgumentException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		String dataPath = args[0]; // csv file to be analyzed
		String resultPath = args[1]; // the file path where the results are saved.
		ArrayList<String> lines = Utils.getLines(dataPath, true);
		
		students = loadStudentCourseRecords(lines);
		
		// To sort HashMap entries by key values so that we can save the results by student ids in ascending order.
		Map<String, Student> sortedStudents = new TreeMap<String,Student>(students); 
		
		// Generate result lines to be saved.
		ArrayList<String> linesToBeSaved = countNumberOfCoursesTakenInEachSemester(sortedStudents);
		//System.out.println(linesToBeSaved); //###############
		
		// Write a file (named like the value of resultPath) with linesTobeSaved.
		Utils.writeAFile(linesToBeSaved, resultPath);
	}
	
	/**
	 * This method create HashMap<String,Student> from the data csv file.
	 * Key is a student id and the corresponding object is an instance of Student.
	 * The Student instance have all the Course instances taken by the student.
	 * @param lines
	 * @return
	 */
	private HashMap<String,Student> loadStudentCourseRecords(ArrayList<String> lines) {
		students = new HashMap<String, Student>();//course먼저 만들어서 루프돌면서 student instance 가져오
		HashMap<String, ArrayList<Student>> keyByCourse = new HashMap<String, ArrayList<Student>>();
		//ArrayList<Student> takenCourse = new ArrayList<Student>();
		//Course courses = new Course(lines);
		
		
		for (String line: lines) {
			Course courses = new Course(line);
			String key = courses.getStudetId();
			
			if(students.containsKey(key)) {
				students.get(key).addCourse(courses);
			} else {
				Student student = new Student(line);
				student.addCourse(courses);
				students.put(key, student);
			}
			
		}
		
		return students; // do not forget to return a proper variable.
	}

	/**
	 * This method generate the number of courses taken by a student in each semester.
	 * The result file look like this:
	 * StudentID, TotalNumberOfSemestersRegistered, Semester, NumCoursesTakenInTheSemester
	 * 0001,14,1,9
     * 0001,14,2,8
	 * ....
	 * 
	 * 0001,14,1,9 => this means, 0001 student registered 14 semeters in total.
	 * In the first semeter (1), the student took 9 courses.
	 * 
	 * 
	 * @param sortedStudents
	 * @return
	 */
	private ArrayList<String> countNumberOfCoursesTakenInEachSemester(Map<String, Student> sortedStudents) {
		//Map<String, Student> sortedStudents = new TreeMap<String,Student>(students); 
		//ArrayList<String> linesToBeSaved = countNumberOfCoursesTakenInEachSemester(sortedStudents);
		ArrayList<String> numberOfCoursesTakenInEachSemester = new ArrayList<String>();
		int totalSemester = 0;
		
		for(String line: sortedStudents.keySet()) {
			Student stu = new Student(line);
			stu = sortedStudents.get(line);
			numberOfCoursesTakenInEachSemester.add(line);
		}
		
		//numberOfCoursesTakenInEachSemester.add(0, sortedStudents.get(line));
		
		/*
		for(int i = 1; i <= sortedStudents.size(); i++) {
			
			//Student studentCheck = sortedStudents.get(Integer.toString(i));
			//System.out.println(studentCheck.getStudentId());
			
			System.out.println(studentCheck.getSemestersByYearAndSemester().size());
			totalSemester = studentCheck.getSemestersByYearAndSemester().size();
		}
		
		for(int i = 1; i < sortedStudents.size(); i++) {
			Student studentCheck = sortedStudents.get(Integer.toString(i)); //for print studentID
			String studentId = studentCheck.getStudentId();
			System.out.println(studentId);
			totalSemester = studentCheck.getSemestersByYearAndSemester().size(); //
			
			for(int j = 1; j < studentCheck.getSemestersByYearAndSemester().size(); j++) {
				int numOfCoursesInNthSemester = studentCheck.getNumCourseInNthSementer(j);
				String lineResult = studentId + "," + totalSemester + "," + j + "," + numOfCoursesInNthSemester;
			}
		}*/
		
		return numberOfCoursesTakenInEachSemester; // do not forget to return a proper variable.
	}
}
