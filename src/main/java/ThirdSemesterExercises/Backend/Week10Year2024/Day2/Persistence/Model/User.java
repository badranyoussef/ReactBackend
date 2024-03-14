package ThirdSemesterExercises.Backend.Week10Year2024.Day2.Persistence.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users") //User is a reserved keyword
public class User {
    @Id
    private String username;
    private String password;

    @JoinTable(name = "user_roles", joinColumns = {
            @JoinColumn(name = "user_name", referencedColumnName = "username")},
            inverseJoinColumns = {
                    @JoinColumn(name = "role_name", referencedColumnName = "name")})
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        String salt = BCrypt.gensalt();
        this.password = BCrypt.hashpw(password, salt);
    }

    public void addRole(Role role) {
        roles.add(role);
        role.getUsers().add(this);
    }

    public void removeRole(Role role) {
        roles.remove(role);
        role.getUsers().remove(this);
    }

    public String getRolesAsStrings() {
        if (roles.isEmpty()) {
            return null;
        }
        String result = "";

        for (Role role : roles) {

            for (int i = 0; i <= roles.size(); i++) {
                result += role.getName();
                if (i < roles.size()) {
                    result += " ,";
                }
            }
        }
        return result;
    }

    public boolean verifyUser(String password) {
        return BCrypt.checkpw(password, this.password);
    }
}
