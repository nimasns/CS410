package edu.pdx.cs410J.seyed;

import edu.pdx.cs410J.lang.Human;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**                                                                                 
 * This class is represents a <code>Student</code>.                                 
 */                                                                                 
public class Student extends Human {                                                

  private final double gpa;
  private List<String> classes;
  private String gender;


  /**                                                                               
   * Creates a new <code>Student</code>                                             
   *                                                                                
   * @param name                                                                    
   *        The student's name                                                      
   * @param classes                                                                 
   *        The names of the classes the student is taking.  A student              
   *        may take zero or more classes.                                          
   * @param gpa                                                                     
   *        The student's grade point average                                       
   * @param gender                                                                  
   *        The student's gender ("male" or "female", case insensitive)             
   */                                                                               
  public Student(String name, List<String> classes, double gpa, String gender) {
    super(name);
    this.gpa = gpa;
    this.classes = classes;
    this.gender = gender;
  }

  /**                                                                               
   * All students say "This class is too much work"
   */
  @Override
  public String says() {                                                            
    throw new UnsupportedOperationException("Not implemented yet");
  }
                                                                                    
  /**                                                                               
   * Returns a <code>String</code> that describes this                              
   * <code>Student</code>.                                                          
   */                                                                               
  public String toString() {
    return getName() + " has a GPA of " + gpa + " and is taking " +
            formatNumberOfClasses() + formatClassNames() + " " + getGenderProunoun();
  }

  private String getGenderProunoun() {
    return (this.gender.equals("female") ? "She" : "He");
  }

  private String formatClassNames() {
    StringBuilder sb = new StringBuilder();
    int numberOfClasses = this.classes.size();
    for (int i = 0; i < numberOfClasses; i++) {
      String className = this.classes.get(i);
      sb.append(className);

      if (i != numberOfClasses - 1) {
        if (numberOfClasses > 2) {
          sb.append(",");
        }
        sb.append(" ");
      }

      if (i == numberOfClasses - 2) {
        sb.append("and ");
      }
    }
    return (sb.append('.')).toString();
  }

  private String formatNumberOfClasses() {
    int numberOfClasses = classes.size();

    if (numberOfClasses == 0) {
      return "0 classes";
    }
    else if(numberOfClasses == 1) {
      return "1 class: ";
    } else {
      return numberOfClasses + " classes: ";
    }
  }

  /**
   * Main program that parses the command line, creates a
   * <code>Student</code>, and prints a description of the student to
   * standard out by invoking its <code>toString</code> method.
   */
  public static void main(String[] args) {
    System.err.println("Missing command line arguments");
    System.exit(1);
  }

}