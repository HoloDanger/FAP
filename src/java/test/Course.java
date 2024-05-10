package test;

import java.util.Date;

public class Course {
    
    private String courseName;
    private String instructor;
    private Date startDate;
    private Date endDate;
    private String courseTime;
    private String banner;

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

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    // Constructor
    public Course(String courseName, String instructor, Date startDate, Date endDate, String courseTime, String banner) {
        this.courseName = courseName;
        this.instructor = instructor;
        this.startDate = startDate;
        this.endDate = endDate;
        this.courseTime = courseTime;
        this.banner = banner;
    }
}
