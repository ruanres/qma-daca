package ruan.eloy.backend.entity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TutorTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Tutor tutor;
    private Student student;

    @Before
    public void setUp() {
        student = new Student(
                "115110981", "12345", "StudentName",
                "student@email", "password", "83911119999");
        tutor = new Tutor("Math", 5, student);
    }

    @Test
    public void saveEntity() {
        Tutor savedTutor = testEntityManager.persist(tutor);
        assertThat(savedTutor.getId()).isNotNull();
        assertThat(savedTutor.getMoney()).isEqualTo(tutor.getMoney());
        assertThat(savedTutor.getStudent()).isEqualTo(tutor.getStudent());
        assertThat(savedTutor.getProficiency()).isEqualTo(tutor.getProficiency());
        assertThat(savedTutor.getSubject()).isEqualTo(tutor.getSubject());
        assertThat(savedTutor.getRating()).isEqualTo(tutor.getRating());
    }
}
