package edu.handong.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import java.io.Reader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import edu.handong.analysis.datamodel.Course;
import edu.handong.analysis.datamodel.Student;
import edu.handong.analysis.utils.NotEnoughArgumentException;
import edu.handong.analysis.utils.Utils;

public class HGUCoursePatternAnalyzer {

	private HashMap<String,Student> students;
	
	String inputVal, i="";
	String outputVal, o="";
	String analysisVal;
	String a="";
	String courseCodeVal, c="";
	String startYearVal;
	String endYearVal;
	boolean help, h;
	
	
	/**
	 * This method runs our analysis logic to save the number courses taken by each student per semester in a result file.
	 * Run method must not be changed!!
	 * @param args
	 * @throws IOException 
	 */
	public void run(String[] args) throws IOException {
		
		/*Options options = new Options(); 
		createOption(options);
		parseOption(options, args);*/
		
		
		try {
			// when there are not enough arguments from CLI, it throws the NotEnoughArgmentException which must be defined by you.
			if(args.length<2)
				throw new NotEnoughArgumentException();
		} catch (NotEnoughArgumentException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		/*if(parseOption(options, args)){
			if (help){
				printHelp(options);
				return;
			}
		}*/
		
		
		
		String dataPath = args[0]; // csv file to be analyzed
		//String dataPath = inputVal;
		String resultPath = args[1]; // the file path where the results are saved.
		//String resultPath = outputVal;
		
		ArrayList<String> lines = Utils.getLines(dataPath, true);
			
		//Key is a student id and the corresponding object is an instance of Student.
		students = loadStudentCourseRecords(lines);
		
		// To sort HashMap entries by key values so that we can save the results by student ids in ascending order.
		Map<String, Student> sortedStudents = new TreeMap<String,Student>(students); 
		
		ArrayList<String> linesToBeSaved = countNumberOfCoursesTakenInEachSemester(sortedStudents);
		//Year,Semester,CouseCode, CourseName,TotalStudents,StudentsTaken,Rate => 결과
		/*Year: The year analized. It shows the results between years specified by the options -s and -e 
		Semester: Semester number for the given year.
		CourseCode: Course code number
		CourseName: Corresponding course name the the given course code
		TotalStudents:  The number of students who enrolled in the given year and semester.
		StudentsTaken:  The number of students taking the given course in the given year and semester.
		Rate: rate of students taking the given course. (StudentsTaken / TotalStudents) Show 1 digit after the floating point with '%' e.g. 33.3% (Refer to String format we learned in the class)
		Display results in ascending order of year and semester.*/
		
		/*HashMap<String, Integer> totalStudents = new HashMap<String, Integer>();
		totalStudents = numberOfTotalStudentsInYearAndSem(students);
		
		HashMap<String, Integer> studentsTaken = new HashMap<String, Integer>();
		studentsTaken = numberOfStudentsTakenCourse(students);
		
		ArrayList<String> rate = new ArrayList<String>();
		rate = rateOfStudents(totalStudents, studentsTaken);*/
		
		if(a == "1") {
			// Generate result lines to be saved.
			//ArrayList<String> linesToBeSaved = countNumberOfCoursesTakenInEachSemester(sortedStudents);
			
			// Write a file (named like the value of resultPath) with linesTobeSaved.
			//Utils.writeAFile(linesToBeSaved, resultPath);
			Utils.writeAFile(linesToBeSaved, outputVal);
		} else if(a == "2") {
			//Utils.writeAFile(rate, outputVal);
		}
		
		
		
	}


	private boolean parseOption(Options options, String[] args) {
		// TODO Auto-generated method stub
		CommandLineParser parser = new DefaultParser();
		
		try {
			CommandLine cmd = parser.parse(options, args);
			
			inputVal = cmd.getOptionValue("i");
			outputVal = cmd.getOptionValue("o");
			analysisVal = cmd.getOptionValue("a");
			courseCodeVal = cmd.getOptionValue("c");
			startYearVal = cmd.getOptionValue("s");
			endYearVal = cmd.getOptionValue("e");
			help = cmd.hasOption("h");
			
		} catch(Exception e) {
			System.out.println(options);
			return false;
		}
		return true;
	}
	
	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "HGU Course Analyzer";
		String footer = "" ; // Leave this empty.
		formatter.printHelp("HGUCourseCounter", header, options, footer, true);
	}

	private void createOption(Options options) {
		// TODO Auto-generated method stub
		
		options.addOption(Option.builder("i").longOpt("input")
				.desc("Set an input file path")
				.hasArg().argName("Input path").required().build());
		
		options.addOption(Option.builder("o").longOpt("output")
				.desc("Set an output file path")
				.hasArg().argName("Output path").required().build());
		
		options.addOption(Option.builder("a").longOpt("analysis")
				.desc("1: Count courses per semester, 2: Count per course name and year")
				.hasArg().argName("Analysis option").required().build());
		
		options.addOption(Option.builder("c").longOpt("coursecode")
				.desc("Course code for '-a 2' option")
				.hasArg().argName("course code").required() //Yes only for '-a 2'
				.build());
		
		options.addOption(Option.builder("s").longOpt("startyear")
				.desc("Set the start year for analysis e.g., -s 2002")
				.hasArg().argName("Start year for analysis").required().build());
		
		options.addOption(Option.builder("e").longOpt("endyear")
				.desc("Set the end year for analysis e.g., -e 2005")
				.hasArg().argName("End year for analysis").required().build());
		
		options.addOption(Option.builder("h").longOpt("help")
		        .desc("Show a Help page")
		        .argName("Help").build());
	}

	/**
	 * This method create HashMap<String,Student> from the data csv file.
	 * Key is a student id and the corresponding object is an instance of Student.
	 * The Student instance have all the Course instances taken by the student.
	 * @param csvParser
	 * @return
	 */
	private HashMap<String,Student> loadStudentCourseRecords(ArrayList<String> lines) {
		students = new HashMap<String, Student>();//course먼저 만들어서 루프돌면서 student instance 가져오
		//HashMap<String, ArrayList<Student>> keyByCourse = new HashMap<String, ArrayList<Student>>();
		//ArrayList<Student> takenCourse = new ArrayList<Student>();
		//Course courses = new Course(csvParser);
		
		
		for (String line: lines) {
			Course courses = new Course(line);
			String key = courses.getStudentId();
			//Student student = new Student(line);
			//student.addCourse(courses);
			//students.put(key, student);
			
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
		ArrayList<String> numOfCoursesTakenInEachSemester = new ArrayList<String>();
		int totalCourse = 0;
		int seqOfSem=0;
		numOfCoursesTakenInEachSemester.add("studentID"  + "," + "TotalNumberOfSemestersRegistered" + "," + "Semester" + "," + "NumCoursesTakenInTheSemester");
		for(String lineString: sortedStudents.keySet()) {
			int count=0;
			Student studentKey = sortedStudents.get(lineString);
			Map<String, Integer> totalSem = new TreeMap<String, Integer>(studentKey.getSemestersByYearAndSemester());
			
			for(String stringLine: totalSem.keySet()) {
				seqOfSem = totalSem.get(stringLine);
				totalCourse = studentKey.getNumCourseInNthSementer(seqOfSem);
				count++;
				//numOfCoursesTakenInEachSemester.add(lineString + "     " + totalSem.size() + "                              " + seqOfSem + "       " + totalCourse);
				numOfCoursesTakenInEachSemester.add(lineString + "," + totalSem.size() + "," + seqOfSem + ","+ totalCourse);
				//numOfCoursesTakenInEachSemester.add(lineString + "\t" + totalSem.size() + "\t" + seqOfSem + "\t" + totalCourse);
				System.out.println(lineString + " " + totalSem.size() + " " + seqOfSem + " " + totalCourse);
			}
		}
		
		return numOfCoursesTakenInEachSemester;
	}
	
	
}
