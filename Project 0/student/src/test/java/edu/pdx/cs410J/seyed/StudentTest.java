package edu.pdx.cs410J.seyed;

import org.hamcrest.CoreMatchers;
import org.junit.Ignore;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringJoiner;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.startsWith;
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
        return new Student(name, new ArrayList<>(), gpa, "Doesn't matter");
    }

    @Test
    public void studentWithZeroClasses() {
        ArrayList<String> classes = new ArrayList<>();
        Student student = createStudentWithClasses(classes);
        assertThat(student.toString(), endsWith("0 classes."));
    }

    @Test
    public void studentWithOneClasses() {
        ArrayList<String> classes = new ArrayList<>();
        classes.add("Java");
        Student student = createStudentWithClasses(classes);
        assertThat(student.toString(), containsString("1 class:"));
    }

    @Test
    public void studentWithThreeClasses() {
        ArrayList<String> classes = new ArrayList<>();
        classes.add("Algorithms");
        classes.add("Operating Systems");
        classes.add("Java");
        Student student = createStudentWithClasses(classes);
        assertThat(student.toString(), containsString("3 classes:"));
    }

    @Test
    public void toStringContainsNameOfClasses() {
        ArrayList<String> classes = new ArrayList<>();
        classes.add("Java");
        Student student = createStudentWithClasses(classes);
        assertThat(student.toString(), containsString("1 class: Java."));

    }

    @Test
    public void toStringContainsNameOfThreeClasses() {
        ArrayList<String> classes = new ArrayList<>();
        classes.add("Algorithms");
        classes.add("Operating Systems");
        classes.add("Java");
        Student student = createStudentWithClasses(classes);
        assertThat(student.toString(), containsString("3 classes: Algorithms, Operating Systems, and Java."));
    }

    @Test
    public void toStringContainsNameOfTwoClasses() {
        ArrayList<String> classes = new ArrayList<>();
        classes.add("Algorithms");
        classes.add("Java");
        Student student = createStudentWithClasses(classes);
        assertThat(student.toString(), containsString("2 classes: Algorithms and Java."));
    }

    private Student createStudentWithClasses(ArrayList<String> classes) {
        return new Student("Name", classes, 3.64, "Doesn't matter");
    }

    @Test
    public void nicelyFormatFirstSentenceOfToString() {
        Student dave = getDaveStudent();

        String firstSentence = "Dave has a GPA of 3.64 and is taking 3 classes: Algorithms, Operating Systems, and Java.";

        assertThat(dave.toString(), startsWith(firstSentence));
    }

    private Student getDaveStudent() {
        ArrayList<String> classes = new ArrayList<>();
        classes.add("Algorithms");
        classes.add("Operating Systems");
        classes.add("Java");
        return new Student("Dave", classes, 3.64, "male");
    }


    @Test
    public void maleStudentHasMalePronounInToString() {
        String gender = "male";
        Student male = studentGender(gender);
        assertThat(male.toString(), containsString("He"));
    }

    @Test
    public void femaleStudentHasMalePronounInToString() {
        Student male = studentGender("female");
        assertThat(male.toString(), containsString("She"));
    }

    private Student studentGender(String gender) {
        return new Student("", new ArrayList<>() , 1.23, gender);
    }


    @Ignore
    @Test
    public void allStudentAttributesAreIncludedInToString() {
        Student dave = getDaveStudent();

        String expected = "Dave has a GPA of 3.64 and is taking 3 classes: Algorithms, Operating Systems, and Java. He says \"This class is too much work\".";

        assertThat(dave.toString(), equalTo(expected));
    }
}
