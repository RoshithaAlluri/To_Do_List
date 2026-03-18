// StuSave1.java
import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class StuSave1 {
    public static void main(String[] args) {
        // 1. Load Hibernate Configuration
        Configuration cfg = new Configuration();
        cfg.configure("/hibernate.cfg.xml");
        
        // 2. Build SessionFactory and open Session
        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();
        Scanner sc = new Scanner(System.in);

        // 

        while (true) {
            System.out.println("\n1. Insert Student");
            System.out.println("2. Display Student by ID (Read)");
            System.out.println("3. Delete Student");
            System.out.println("4. Update Student Details");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int option = sc.nextInt();

            Transaction tx = null; // Declare transaction outside the switch
            
            switch (option) {
                case 1:
                    // Insert Student (Create)
                    System.out.print("Enter Student Name: ");
                    sc.nextLine(); 
                    String sname = sc.nextLine();
                    System.out.print("Enter Course: ");
                    String course = sc.nextLine();
                    System.out.print("Enter Fees: ");
                    double fees = sc.nextDouble();

                    Student student = new Student(0, sname, course, fees);
                    tx = session.beginTransaction();
                    session.save(student);
                    tx.commit();
                    System.out.println("Student Inserted Successfully.");
                    break;

                case 2:
                    // Display Student by ID (Read)
                    System.out.print("Enter Student ID: ");
                    int sid = sc.nextInt();
                    Student s = session.get(Student.class, sid);
                    if (s != null) {
                        System.out.println(s);
                    } else {
                        System.out.println("Student not found!");
                    }
                    break;

                case 3:
                    // Delete Student (Delete)
                    System.out.print("Enter Student ID to Delete: ");
                    sid = sc.nextInt();
                    s = session.get(Student.class, sid);
                    if (s != null) {
                        tx = session.beginTransaction();
                        session.delete(s);
                        tx.commit();
                        System.out.println("Student Deleted Successfully.");
                    } else {
                        System.out.println("Student not found!");
                    }
                    break;

                case 4:
                    // Update Student (Update)
                    System.out.print("Enter Student ID to Update: ");
                    sid = sc.nextInt();
                    s = session.get(Student.class, sid);
                    if (s != null) {
                        System.out.print("Enter New Name: ");
                        sc.nextLine();
                        sname = sc.nextLine();
                        System.out.print("Enter New Course: ");
                        course = sc.nextLine();
                        System.out.print("Enter New Fees: ");
                        fees = sc.nextDouble();

                        s.setSname(sname);
                        s.setCourse(course);
                        s.setFees(fees);

                        tx = session.beginTransaction();
                        session.update(s);
                        tx.commit();
                        System.out.println("Student Updated Successfully.");
                    } else {
                        System.out.println("Student not found!");
                    }
                    break;

                case 5:
                    // Display All Students (Read All)
                    // HQL (Hibernate Query Language) equivalent of your "FROM Employee"
                    Query query = session.createQuery("FROM Student"); 
                    List<Student> stuList = query.list();
                    System.out.println("\n******** Student List ********");
                    for (Student stuObj : stuList) {
                        System.out.println(stuObj);
                    }
                    System.out.println("********************************");
                    break;

                case 6:
                    // Exit
                    System.out.println("Exiting...");
                    session.close();
                    sf.close();
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid Option! Try Again.");
            }
        }
    }
}