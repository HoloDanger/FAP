package test;

import java.util.Date;

public class Course {

    private int id;
    private String courseName;
    private String instructor;
    private Date startDate;
    private Date endDate;
    private String courseTime;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCourseTime() {
        return courseTime;
    }

    // Constructor
    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public Course(int id, String courseName, String instructor, Date startDate, Date endDate, String courseTime) {
    }
}
