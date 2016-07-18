package edu.pdx.cs410J.seyed;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Integration tests for the {@link Project2} main class.
 */
public class Project2IT extends InvokeMainTestCase {

  /**
   * Invokes the main method of {@link Project2} with the given arguments.
   */
  private MainMethodResult invokeMain(String... args) {

    return invokeMain( Project2.class, args );
  }

  /**
   * Tests that invoking the main method with no arguments issues an error
   */

  @Test
  public void testNoCommandLineArguments() {
    MainMethodResult result = invokeMain();
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getErr(), containsString("Missing command line arguments"));
  }

  /**
   * Tests that the -README command will cause the exit code of 2
   */
  @Test
  public void testReadmeCommand() {
    MainMethodResult result = invokeMain(Project2.class, "-README");
    assertThat(result.getExitCode(), equalTo(2));
  }

  @Test
  public void missingDescription() {
    MainMethodResult result = invokeMain(Project2.class, "Owner");
    assertThat(result.getErr(), containsString("Missing description field!"));
  }

  @Test
  public void missingBeginDate() {
    MainMethodResult result = invokeMain(Project2.class, "Owner", "Description");
    assertThat(result.getErr(), containsString("Missing begin date field!"));
  }

  @Test
  public void missingBeginTime() {
    MainMethodResult result = invokeMain(Project2.class, "Owner", "Description", "5/2/15");
    assertThat(result.getErr(), containsString("Missing begin time field!"));
  }

  @Test
  public void missingEndDate() {
    MainMethodResult result = invokeMain(Project2.class, "Owner", "Description", "5/2/15", "12:30");
    assertThat(result.getErr(), containsString("Missing end date field!"));
  }

  @Test
  public void missingEndTime() {
    MainMethodResult result = invokeMain(Project2.class, "Owner", "Description", "5/2/15", "12:30", "5/12/2015");
    assertThat(result.getErr(), containsString("Missing end time field!"));
  }

  @Test
  public void IncorrectBeginTimeFormat() {
    MainMethodResult result = invokeMain(Project2.class, "Owner", "Description", "5/215", "12:30", "5/12/2015", "1:00");
    assertThat(result.getErr(), containsString("Invalid begin time format!"));
  }

  @Test
  public void IncorrectEndTimeFormat() {
    MainMethodResult result = invokeMain(Project2.class, "Owner", "Description", "5/2/15", "12:30", "5/122015", "1:00");
    assertThat(result.getErr(), containsString("Invalid end time format!"));
  }
}