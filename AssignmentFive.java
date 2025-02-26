import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import com.google.inject.name.Names;


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

    @Inject
    public Aid(IStudentDal studentDal, @Named("studentId") int studentId) {
        this.studentDal = studentDal;
        this.student = studentDal.getStudent(studentId);
    }

    public void printStudentInfo() {
        System.out.println("Name: " + student.getName() + " and Id: " + student.getId());
    }
}

class AidInject extends AbstractModule {
    @Override
    protected void configure() {
        super.configure();
        bind(IStudentDal.class).to(StudentDal.class);
        bind(Integer.class)
                .annotatedWith(Names.named("studentId"))
                .toInstance(10);
    }
}

public class AssignmentFive {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AidInject());
        Aid aid = injector.getInstance(Aid.class);
        aid.printStudentInfo();
    }
}
