package ThirdSemesterExercises.Backend.Week10Year2024.Day2.DAOs;

import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Persistence.Model.Role;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Persistence.Model.User;

public interface ISecurityDAO {

    User createUser(String username, String password);

    Role createRole(String role);

    User addRoleToUser(String username, String role);
}
