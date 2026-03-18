// Student.java
public class Student {
    private int sid;
    private String sname;
    private String course;
    private double fees;

    public Student() {
        super();
    }

    public Student(int sid, String sname, String course, double fees) {
        super();
        this.sid = sid;
        this.sname = sname;
        this.course = course;
        this.fees = fees;
    }

    // Getters and Setters
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }
    
    // toString method for easy printing
    @Override
    public String toString() {
        return "Student [ID=" + sid + ", Name=" + sname + ", Course=" + course + ", Fees=" + fees + "]";
    }
    
    // hashCode and equals methods removed for brevity, but you can copy them from Employee.java
}