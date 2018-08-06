package ruan.eloy.backend.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StudentTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Student student;
    private Tutor mathTutor;
    private Tutor javaTutor;

    @Before
    public void setUp() {
        student = new Student(
                "115110981", "12345", "StudentName",
                "student@email", "password", "83911119999");

        mathTutor = new Tutor("Math", 3, student);
        javaTutor = new Tutor("Java", 4, student);
    }

    @Test
    public void saveEntity() {
        Student savedStudent = testEntityManager.persistAndFlush(student);

        assertThat(savedStudent.getId()).isNotNull();
        assertThat(savedStudent.getRegistration()).isEqualTo(student.getRegistration());
        assertThat(savedStudent.getCourseCode()).isEqualTo(student.getCourseCode());
        assertThat(savedStudent.getName()).isEqualTo(student.getName());
        assertThat(savedStudent.getEmail()).isEqualTo(student.getEmail());
        assertThat(savedStudent.getPassword()).isEqualTo(student.getPassword());
        assertThat(savedStudent.getPhone()).isEqualTo(student.getPhone());
        assertThat(savedStudent.getRating()).isEqualTo(student.getRating());
    }

    @Test
    public void addTutors() {
        assertThat(student.getTutors().size()).isEqualTo(0);

        student.addTutor(mathTutor);
        student.addTutor(javaTutor);

        Student savedTutor = testEntityManager.persistAndFlush(student);
        assertThat(savedTutor.getTutors().size()).isEqualTo(2);
    }

    @Test
    public void removeTutors() {
        Tutor savedMathTutor = testEntityManager.persist(mathTutor);
        Tutor savedJavaTutor = testEntityManager.persist(javaTutor);

        student.addTutor(savedMathTutor);
        student.addTutor(savedJavaTutor);

        Student savedStudent = testEntityManager.persistAndFlush(student);
        assertThat(savedStudent.getTutors().size()).isEqualTo(2);

        savedStudent.removeTutor(savedMathTutor.getId());

        savedStudent = testEntityManager.persistAndFlush(savedStudent);
        assertThat(savedStudent.getTutors().size()).isEqualTo(1);
        assertThat(savedStudent.getTutors().contains(savedJavaTutor)).isTrue();
    }
}
