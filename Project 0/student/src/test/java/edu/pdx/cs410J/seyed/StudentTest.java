package edu.pdx.cs410J.seyed;

import org.junit.Test;

import java.util.ArrayList;
import java.util.StringJoiner;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Unit tests for the Student class.  In addition to the JUnit annotations,
 * they also make use of the <a href="http://hamcrest.org/JavaHamcrest/">hamcrest</a>
 * matchers for more readable assertion statements.
 */
public class StudentTest
{

  @Test
  public void studentNamedPatIsNamedPat() {
    String name = "Pat";
    Student pat = createStudentWithName(name);
    assertThat(pat.getName(), equalTo(name));
  }

  @Test
    public void studentDescriptionContainsName() {
      String name = "Pat";
      Student student = createStudentWithName(name);
      assertThat(student.toString(), containsString(name));
  }

    private Student createStudentWithName(String name) {
        return createStudentWithNameAndGpa(name, 0.0);
    }

    @Test
    public void studentDepscriptionContainGPA() {
        double gpa = 1.23;
        Student student = createStudentWithGpa(gpa);
        assertThat(student.toString(), containsString(String.valueOf(gpa)));
    }

    private Student createStudentWithGpa(double gpa) {
        return createStudentWithNameAndGpa("Name", gpa);
    }

    private Student createStudentWithNameAndGpa(String name, double gpa) {
        return new Student(name, new ArrayList(), gpa, "Doesn't matter");
    }
}