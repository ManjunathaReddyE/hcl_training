package com.stream.case3;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class TestMain {


	
		public static Course COURSE_JAVA = new Course("C001", "JAVA", LocalDate.of(2022, 1, 01), LocalDate.of(2022, 03, 30));
		public static Course COURSE_HIBERNATE = new Course("C002", "HIBERNATE", LocalDate.of(2022, 1, 02), LocalDate.of(2022, 1, 05));
		public static Course COURSE_SQL= new Course("C003", "SQL", LocalDate.of(2022, 6, 9), LocalDate.of(2022, 6, 15));
		public static List<Course> listOfCourses = Arrays.asList(COURSE_JAVA, COURSE_HIBERNATE, COURSE_SQL);
		public static List<Student> listOfStudents;
		
		public static void main(String[] args) {
			

			Map<String, Double> s1score = new HashMap<>();
			s1score.put("JAVA", 88.5);
			s1score.put("HIBERNATE", 88.5);
			Student hari = new Student("S1", "hari", Arrays.asList(COURSE_HIBERNATE, COURSE_JAVA), s1score);

			Map<String, Double> ramaScore = new HashMap<>();
			ramaScore.put("HIBERNATE", 88.5);
			ramaScore.put("JAVA", 88.5);
			Student rama = new Student("S2", "rama", Arrays.asList(COURSE_HIBERNATE, COURSE_JAVA), ramaScore);

			Map<String, Double> shivaScore = new HashMap<>();
			shivaScore.put("JAVA", 88.5);
			shivaScore.put("HIBERNATE", 88.5);
			Student shiva = new Student("S3", "Shiva", Arrays.asList(COURSE_JAVA, COURSE_HIBERNATE), shivaScore);

			listOfStudents = Arrays.asList(hari, rama, shiva);
			
			//  Get all student details based on given course input 
		
			List<Student> stulist=getStudentEnrolledForCourse("JAVA");
			
			stulist.forEach(System.out::println);
			
			//Get the average score of all students for any given course
			
			double avgscore=getAverageScoreForCourse("JAVA");

			System.out.println("avgscore: "+avgscore);
			
			
			LocalDate today = LocalDate.of(2022, 6, 07);
			System.out.println("  Courses avail for week");
			Optional<Course> c1= getAvailableCoursesforDurationTime(today.plusWeeks(1));
			if(c1.isPresent())
			{
				System.out.println(c1.get().getId());
			}
			System.out.println(" Courses avail for next month");
			Optional<Course> c2= getAvailableCoursesforDurationTime(today.plusMonths(1));
			if(c2.isPresent())
			{
				System.out.println(c2.get().getId());
			}
		}


		private static Optional<Course> getAvailableCoursesforDurationTime(LocalDate plusWeeks) {
			return listOfCourses.stream().filter(course -> plusWeeks.isAfter(course.getStartDate()) && plusWeeks.isBefore(course.getEndDate())).findFirst();
		}


		
		
		private static double getAverageScoreForCourse(String course) {
			

			List<Student> list1 = listOfStudents.stream().filter(c->c.getCourseEnrolled().stream().map(Course::getTitle).anyMatch(titile->titile.equals(course))).collect(Collectors.toList());

			double avg=0.0;
			for(Student s:list1)
			{
				Map<String, Double> mp=s.getScore();
				
				//mp.entrySet().forEach(entry->{ System.out.println(entry.getKey()+" :: "+entry.getValue()); });
				
				avg =avg+mp.get(course);
				
				
			}
			System.out.println("==avg "+avg);
			return avg;
			
			//Double avg =  listOfStudents.stream().filter(student -> student.getCourseEnrolled().stream().map(Student::getScore).anyMatch(title -> title.equals(course))).collect(Collectors.averagingDouble((s) ->s.getScore()));
			//double avgscore =(double)listOfStudents.stream().collect(Collectors.averagingInt(s->s.getScore().entrySet().));  
			//Double avg =  listOfStudents.stream().filter(student -> student.getCourseEnrolled().stream().map(Student::getScore).anyMatch(title -> title.equals(course))).collect(Collectors.averagingDouble((s) ->s.getScore()));
					
			// Map<String, Double> a = Arrays.stream(OperatorType.values()).collect(toMap(OperatorType::getKey, OperatorType::getSupportedtypes));
			 
			//return avgscore;
		}
		
		
		private static List<Student> getStudentEnrolledForCourse(String course) {
			
			 List<Student> stlist=listOfStudents.stream().filter(p->p.getCourseEnrolled().stream().map(Course::getTitle).anyMatch(titile->titile.equals(course))).collect(Collectors.toList());

			 System.out.println("stlist  "+stlist);
			 return stlist;
			
			//return studentList.stream().filter(student -> student.getCourseEnrolled().stream().map(Course::getTitle)					.anyMatch(title -> title.equals(course))).toList();
		}

}
