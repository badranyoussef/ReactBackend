package ThirdSemesterExercises.Backend.Week10Year2024.Day2.Persistence.Model;

import java.util.Set;

public interface ISecurityUser {
    Set<String> getRolesAsStrings();

    boolean verifyPassword(String pw);

    void addRole(Role role);

    void removeRole(Role role);
}
