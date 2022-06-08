package com.stream.case3;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {

	private String sId;
	private String sName;
	private List<Course> courseEnrolled;
	private Map<String, Double> score;
	public Student(String sId, String sName, List<Course> courseEnrolled, Map<String, Double> score) {
		super();
		this.sId = sId;
		this.sName = sName;
		this.courseEnrolled = courseEnrolled;
		this.score = score;
	}
	public String getsId() {
		return sId;
	}
	public void setsId(String sId) {
		this.sId = sId;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public List<Course> getCourseEnrolled() {
		return courseEnrolled;
	}
	public void setCourseEnrolled(List<Course> courseEnrolled) {
		this.courseEnrolled = courseEnrolled;
	}
	public Map<String, Double> getScore() {
		return score;
	}
	public void setScore(Map<String, Double> score) {
		this.score = score;
	}
	
	
	
	
	
	
}