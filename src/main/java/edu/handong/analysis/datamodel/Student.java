package edu.handong.analysis.datamodel;

import edu.handong.analysis.datamodel.Course;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Student { //학생객체로 학생이름이 아닌 학번으로 처리
	private String studentId; //constructor
	private ArrayList<Course> coursesTaken; // 학생이 들은 수업 목록(line하나가 course란 instance
	private HashMap<String,Integer> semestersByYearAndSemester;// key: Year-Semester, e.g., 2003-1

	// constructor
	public Student(String studentId) {
		this.studentId = studentId;
		this.coursesTaken = new ArrayList<Course>();
		this.semestersByYearAndSemester = new HashMap<String, Integer>();
	}
	
	/*line을 읽으면서 생성된 Course instance를 
	 * Student instance에 있는 coursesTaken ArrayList에 추가하는 method*/
	public void addCourse(Course newRecord) {
		//coursesTaken.add(newRecord.getCourseName());
		coursesTaken.add(newRecord);
		/*int count = 0;
		for(count=0; count < newRecord.getCourseName().length(); count++) {
			//coursesTaken = new ArrayList<Course>();
			coursesTaken.add(newRecord);
		}*/
	}
	
	public Student getCourseTaken() {
		//String string;
		//System.out.println(coursesTaken);
		Student instance = new Student(coursesTaken.toString());
		return instance;
	}
	
	public String getStudent() {
		return studentId;
	}
	
	
	
	/* 이게 몇번째 학기인지 정보를 가지고 있는 해쉬맵
	 * 이 해쉬맵에 2002-1을 집어넣으면 학생1인 경우에 1이라는 값이 이 해쉬맵 안의 interger로 들어감
	 * 2002-2가 키로 들어가면 인테져는 2
	 * 2003-2의 인테져값은 3
	 * Id를 키값으로 뽑아서 */
	public HashMap<String,Integer> getSemestersByYearAndSemester() {
		//semestersByYearAndSemester = new HashMap<String, Integer>();
		//ArrayList<Course> courseInfos = new ArrayList<Course>();
		//Map<String, ArrayList<String>> hashMap = new HashMap<String, ArrayList<String>>(); //key: stu.id value: 2002-1,2002-2,...
		//Course courseInfo = new Course(studentId);
		
		int count = 0;
		String year = "";
		String semester = "";
		
		for(Course line: coursesTaken) {
			year = Integer.toString(line.getYearTaken());
			semester = Integer.toString(line.getSemesterCourseTaken());
			String yearAndSememester = year + "-" + semester;
			
			if(!semestersByYearAndSemester.containsKey(yearAndSememester)) {
				count++;
				semestersByYearAndSemester.put(yearAndSememester, count);
				//System.out.println(yearAndSememester+count);
			}
		}

		return semestersByYearAndSemester;
	}
	
	
	public int getNumCourseInNthSementer(int semester) {
		int counts = 0;
		String years = "";
		String semesters = "";
		for(Course lines: coursesTaken) {
			years = Integer.toString(lines.getYearTaken());
			semesters = Integer.toString(lines.getSemesterCourseTaken());
			String yearsAndSemesters = years + "-" + semesters;
			if(semestersByYearAndSemester.get(yearsAndSemesters)==semester) {
				counts++;
				//System.out.println("year:" + yearsAndSemesters + "count" + counts);
			}
		}
		return counts;
	}

}
