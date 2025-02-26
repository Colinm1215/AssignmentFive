interface IStudentDal {
    Student getStudent(int id);
}

class Student {
    private final String name;
    private final int id;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}

class StudentDal implements IStudentDal {
    @Override
    public Student getStudent(int id) {
        String nameFromDb = "Joe";
        return new Student(nameFromDb, id);
    }
}

class Aid {
    private final Student student;
    private final IStudentDal studentDal;

    public Aid(int studentId, IStudentDal studentDal) {
        this.studentDal = studentDal;
        this.student = this.studentDal.getStudent(studentId);
    }

    public void printStudentInfo() {
        System.out.println("Name: " + student.getName() + " and Id: " + student.getId());
    }
}

public class AssignmentFive {
    public static void main(String[] args) {
        IStudentDal studentDal = new StudentDal();
        Aid aid = new Aid(10, studentDal);
        aid.printStudentInfo();
    }
}
