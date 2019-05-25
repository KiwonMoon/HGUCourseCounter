package edu.handong.analysis.datamodel;

import edu.handong.analysis.datamodel.Course;
import java.util.ArrayList;
import java.util.HashMap;

public class Student { //학생객체로 학생이름이 아닌 학번으로 처리
	private String studentId; //constructor
	private ArrayList<Course> coursesTaken; // 학생이 들은 수업 목록(line하나가 course란 instance
	private HashMap<String,Integer> semestersByYearAndSemester;// key: Year-Semester, e.g., 2003-1

	// constructor
	public Student(String studentId) {
		this.studentId = studentId;
	}
	
	/*line을 읽으면서 생성된 Course instance를 
	 * Student instance에 있는 coursesTaken ArrayList에 추가하는 method*/
	public void addCourse(Course newRecord) {
		//coursesTaken.add(newRecord.getCourseName());
		int count = 0;
		
		for(count=0; count < newRecord.getCourseName().length(); count++) {
			coursesTaken = new ArrayList<Course>();
			coursesTaken.add(newRecord);
		}
	}
	
	public Student getCourseTaken() {
		//String string;
		System.out.println(coursesTaken);
		Student instance = new Student(coursesTaken.toString());
		return instance;
	}
	
	public String getStudentId() {
		return studentId;
	}
	
	
	/*수강한 년도와 학기 정보를 이용 해당 학생의 순차적인 학기 정보를 저장하는 hashmap을 만듭니다.
	 * 예를 들어 2001-1에 입학한 학생은 2001-1이 1학기 이고, 2001-2가 2학기 이며 2002-1이 3학기일 겁니다.
	 * 아래와 같은 데이터 구조가 됩니다. 계절학기도 공식학기로 포함합니다.
	 * 이게 몇번째 학기인지 정보를 가지고 있는 해쉬맵
	 * 이 해쉬맵에 2002-1을 집어넣으면 학생1인 경우에 1이라는 값이 이 해쉬맵 안의 interger로 들어감
	 * 2002-2가 키로 들어가면 인테져는 2
	 * 2003-2의 인테져값은 3*/
	public HashMap<String,Integer> getSemestersByYearAndSemester() {
		semestersByYearAndSemester = new HashMap<String, Integer>();
		Integer seq = 1;
		//ArrayList<Course> courseInfo = new ArrayList<Course>();
		//Course courseInfos = new Course(coursesTaken.get(0).getCourseName());
		//Course yearTakens = new Course(Integer.toString(coursesTaken.get(0)));
		//Course courseInfo = new Course(coursesTaken.get(0).toString());
		
		for(int i=0; i < coursesTaken.size(); i++) {
			Course courseInfo = new Course(coursesTaken.get(i).toString());
			Course yearTaken = new Course(Integer.toString(coursesTaken.get(i).getYearTaken()));
			Course semTaken = new Course(Integer.toString(coursesTaken.get(i).getSemesterCourseTaken()));
			
			String key = Integer.toString(courseInfo.getYearTaken()) + "-" + Integer.toString(courseInfo.getSemesterCourseTaken());
			
			semestersByYearAndSemester.put(key, i+1);
			
			
		}
			
		/*for(Course line: coursesTaken) {
			
			int year = line.getYearTaken();
			int semester = line.getSemesterCourseTaken();

			String stringYear = Integer.toString(year);
			String stringSemester = Integer.toString(semester);
			String key = stringYear + "-" + stringSemester;
			Integer sequence = 1;
			
			if(semestersByYearAndSemester.containsKey(key)) {
				semestersByYearAndSemester.get(key).intValue();
			} else {
				sequence = sequence + 1;
				semestersByYearAndSemester.put(key, sequence);
			}
			
			for(String linel: semestersByYearAndSemester.keySet()) {
				System.out.println(key);
			}
			
			/*Integer semesterSequence = 1;
			for(semesterSequence = 1; semesterSequence > studentId.length(); semesterSequence++) {
				
				semestersByYearAndSemester.put(key, semesterSequence);
			}
			semestersByYearAndSemester.put(key, semesterSequence);*/
			
		//}
		
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
