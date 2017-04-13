package exampleoncreatingfixedfragment.example.com.tuts.Model;

/**
 * Created by 450 G1 on 21/03/2017.
 */

public class Course {
    private int courseId;
    private String courseName;
    private String instructor;
    private String price;
    private String imgHeader;
    private String imgIcon;
    private int userID;

    public Course(Integer courseID, String imgIcon) {
        this.courseId = courseID;
        this.imgIcon = imgIcon;
    }

    public Course() {
    }



    public Course(int courseId, String courseName, String instructor, String price, String imgHeader, String imgIcon){
        this.courseId = courseId;
        this.courseName = courseName;
        this.instructor = instructor;
        this.price = price;
        this.imgHeader = imgHeader;
        this.imgIcon = imgIcon;
    }
    public Course(int courseId, String courseName, String instructor, String price, String imgHeader, int userID){
        this.courseId = courseId;
        this.courseName = courseName;
        this.instructor = instructor;
        this.price = price;
        this.imgHeader = imgHeader;
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }


    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImgHeader() {
        return imgHeader;
    }

    public void setImgHeader(String imgHeader) {
        this.imgHeader = imgHeader;
    }

    public String getImgIcon() {
        return imgIcon;
    }

    public void setImgIcon(String imgIcon) {
        this.imgIcon = imgIcon;
    }
}
