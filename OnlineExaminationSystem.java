package JavaProgramming;

import java.util.Scanner;

class User {
  private String username, password;

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean authenticate(String uname, String pass) {
    return (uname.equals(this.username) && pass.equals(this.password));
  }

}

class Session {
  private long startTime;
  private long duration;

  Session(long duration) {
    this.duration = duration;
  }

  public void startTimer() {
    startTime = System.currentTimeMillis();
  }

  public boolean isSessionExpired() {
    long elapsedTime = System.currentTimeMillis() - startTime;
    return elapsedTime >= duration;
  }
}

class MCQs {
  private String question;
  private int correctOption;
  private String[] options = new String[4];

  public MCQs(String question, String[] options, int correctOption) {
    this.question = question;
    this.options = options;
    this.correctOption = correctOption;
  }

  public void displayQuestion() {
    System.out.println(question);
    for (String option : options) {
      System.out.print(option + "      ");
    }
    System.out.println();
  }

  public boolean checkAnswer(int answer) {
    if (answer == this.correctOption) {
      return true;
    }
    return false;
  }

}

public class OnlineExaminationSystem {
  @SuppressWarnings("resource")
  public static void main(String[] args) {
    Scanner obj = new Scanner(System.in);
    String loginUname, loginPass, choice;
    boolean registered = false, loggedIn = false;
    int marks = 0, answer = 0;
    User user = new User();

    while (true) {
      System.out.print("Enter 1 to register/update, 2 to login, 3 to read the Quiz Instructions and 4 to start the quiz : ");
      choice = obj.nextLine();

      if (choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4")) {

        if (choice.equals("1")) {
          Scanner scan = new Scanner(System.in);
          System.out.print("Create your Username : ");
          user.setUsername(scan.nextLine());
          System.out.print("Create your Password : ");
          user.setPassword(scan.nextLine());
          registered = true;
          System.out.println("\nRegistration successfull, You can now Login to your account..!!\n");
        }

        if (choice.equals("2") && registered == false) {
          System.out.println("\nRegister first to login...!!\n");
        }
        if (choice.equals("2") && registered == true) {
          while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter your Username to login : ");
            loginUname = scan.nextLine();
            System.out.print("Enter your Password to login : ");
            loginPass = scan.nextLine();
            if (user.authenticate(loginUname, loginPass)) {
              System.out.println("\nLogin successfull, You can now start your Quiz...!!\n");
              loggedIn = true;
              break;
            } else {
              System.out.println("\nIncorrect Username or Password\n");
            }
          }
        }

        if (choice.equals("3")) {
          System.out.println(
              "\nQuiz Instructions : \n1) There will be 10 Questions which you have to answer in 10 mins. \n2) Marks will be awarded on the basis : +4 for every correct answer, -1 for every wrong attempt and 0 for unattempted question.\n3) Only input the option number for every MCQ and 0 to skip the question.\n");
        }

        if (choice.equals("4") && loggedIn == false) {
          System.out.println("\nLogin first to start the quiz...!!\n");
        }
        if (choice.equals("4") && loggedIn == true) {
          MCQs[] mcqs = new MCQs[10];
          mcqs[0] = new MCQs("A) Who is the father of Computers?",
              new String[] { "", "1. James Gosling", "2. Charles Babbage", "3. Dennis Ritchie",
                  "4. Bjarne Stroustrup" },
              2);
          mcqs[1] = new MCQs("B) Which one of the following is not a Java feature?",
              new String[] { "", "1. Object-oriented", "2. Use of pointers", "3. Portable",
                  "4. Dynamic and Extensible" },
              2);
          mcqs[2] = new MCQs("C) Which of the following is the smallest unit of data in a computer?",
              new String[] { "", "1. Bit", "2. KB", "3. Nibble", "4. Byte" }, 1);
          mcqs[3] = new MCQs("D) Which of the following is not a type of computer code?",
              new String[] { "", "1. EDIC", "2. ASCII", "3. BCD", "4. EBCDIC" }, 1);
          mcqs[4] = new MCQs("E) Who invented Java Programming?",
              new String[] { "", "1. Guido van Rossum", "2. James Gosling", "3. Dennis Ritchie",
                  "4. Bjarne Stroustrup" },
              2);
          mcqs[5] = new MCQs("F) What is the extension of java code files?",
              new String[] { "", "1. .js", "2. .txt", "3. .class", "4. .java" }, 4);
          mcqs[6] = new MCQs("G) Which of the following computer language is written in binary codes only?",
              new String[] { "", "1. java", " 2. machine language", " 3. C-language", " 4. javascript" }, 2);
          mcqs[7] = new MCQs("H) Which environment variable is used to set the java path?",
              new String[] { "", "1.  MAVEN_Path", "2. JavaPATH", "3. JAVA", "4. JAVA_HOME" }, 4);
          mcqs[8] = new MCQs("I) Which class provides system independent server side implementation?",
              new String[] { "", "1. Server", "2. ServerReader", "3. Socket", "4. ServerSocket" }, 4);
          mcqs[9] = new MCQs("J) Which component is used to compile, debug and execute the java programs?",
              new String[] { "", "1. JRE", "2. JIT", "3. JDK", "4. JVM" }, 3);

          Scanner scan = new Scanner(System.in);
          Session session = new Session(1 * 60 * 1000);
          session.startTimer();
          int i;
          for (i = 0; i < 10; i++) {
            mcqs[i].displayQuestion();

            while (!session.isSessionExpired()) {
              System.out.print("=>  Enter correct option number (1-4) :  ");
              answer = scan.nextInt();
              break;
            }

            if (session.isSessionExpired()) {
              System.out.println("\nTimes Up !! Results are autosubmitted...");
              break;
            }

            if (mcqs[i].checkAnswer(answer)) {
              System.out.println("Correct Answer...\n");
              marks += 4;
            } else if (answer == 0) {
              System.out.println("Question Skipped.\n");
              continue;
            } else {
              marks -= 1;
              System.out.println("Incorrect Answer...\n");
            }
            if (i == 10) {
              System.out.print("\nEnter Y/y to submit the results immediately and see the obtained marks : ");
              String submit = scan.nextLine();
              if (submit.equals("Y") || submit.equals("y")) {
                break;
              } else {
                System.out.println("\nResults will get autosubmitted when time gets over !!");
              }
            }
          }
          System.out.printf("\nTotal marks obtainer %d out of %d \n", marks, 40);
          System.out.println("Closing Session & Logging out...");
          break;
        }
      } else {
        System.out.println("Please enter a valid numeric choice only");
      }
    }
  }
}