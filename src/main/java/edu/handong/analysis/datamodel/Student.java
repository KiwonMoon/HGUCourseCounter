package edu.handong.analysis.datamodel;

import edu.handong.analysis.datamodel.Course;
import java.util.ArrayList;
import java.util.HashMap;

public class Student { //학생객체로 학생이름이 아닌 학번으로 처리
	private String studentId; //constructor
	private ArrayList<Course> coursesTaken; // 학생이 들은 수업 목록
	private HashMap<String,Integer> semestersByYearAndSemester;// key: Year-Semester, e.g., 2003-1

	// constructor
	public Student(String studentId) {
		this.studentId = studentId;
	}
	
	
	/*line을 읽으면서 생성된 Course instance를 
	 * Student instance에 있는 coursesTaken ArrayList에 추가하는 method*/
	public void addCourse(Course newRecord) {
		coursesTaken.add(newRecord);
	}
	
	public String getStudentId() {
		
		return studentId;
	}
	
	
	/*수강한 년도와 학기 정보를 이용 해당 학생의 순차적인 학기 정보를 저장하는 hashmap을 만듭니다.
	 * 예를 들어 2001-1에 입학한 학생은 2001-1이 1학기 이고, 2001-2가 2학기 이며 2002-1이 3학기일 겁니다.
	 * 아래와 같은 데이터 구조가 됩니다. 계절학기도 공식학기로 포함합니다.*/
	public HashMap<String,Integer> getSemestersByYearAndSemester() {
		
		return semestersByYearAndSemester;
	}
	
	/*순차적 학기 번호를 넣으면 해당 학기의 들은 과목의 개수를 돌려줍니다.
	 * 앞의 hashmap에서 3을 입력하면 해당 학생이 2002-1학기에 들은 과목의 개수를 돌려줍니다.
	 * coursesTaken에 과목들을 쭉 돌면서 년차 및 학기 정보를 조합해서 key (2002-1)를 만든 후
	 * semestersByYearAndSemester에 해당 key값에 해당하는 순차적 학기 번호를 받아와
	 * 3이랑 같으면 count를 하나 늘리식으로 논리를 짜면 됩니다. */
	/* Add getter and setter for the field if needed*/
	public int getNumCourseInNthSementer(int semester) {
		
		return semester;
	}

}
