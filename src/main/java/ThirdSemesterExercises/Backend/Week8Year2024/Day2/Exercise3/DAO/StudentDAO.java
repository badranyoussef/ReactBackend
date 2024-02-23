package ThirdSemesterExercises.Backend.Week8Year2024.Day2.Exercise3.DAO;

import ThirdSemesterExercises.Backend.Week8Year2024.Day2.Exercise3.Semester;
import ThirdSemesterExercises.Backend.Week8Year2024.Day2.Exercise3.Student;
import ThirdSemesterExercises.Backend.Week8Year2024.Day2.Exercise3.Teacher;

import java.util.List;

public interface StudentDAO {

    // Create a dao package and add a new interface called StudentDAO and add the following code to the interface:

    // find all students in the system with the first name Anders
    List<Student> findAllStudentsByFirstName(String firstName);

    // find all students in the system with the last name And
    List<Student> findAllStudentsByLastName(String lastName);

    // find the total number of students, for a semester given the semester name as a parameter
    long findTotalNumberOfStudentsBySemester(String semesterName);

    // find the total number of students that has a particular teacher
    long findTotalNumberOfStudentsByTeacher(Teacher teacher);

    // find the teacher who teaches the most semesters
    Teacher findTeacherWithMostSemesters();

    // find the semester that has the fewest students
    Semester findSemesterWithFewestStudents();

    // find all students, encapsulated as StudentInfoDTO
    StudentInfoDTO getAllStudentInfo(int id);
}