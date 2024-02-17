package ThirdSemesterExercises.Backend.Week5Year2024.Day1.Exercise4;

import java.time.LocalDate;
import java.time.Period;

class Employee {

    private String name;
    private int age;
    private LocalDate birthday;

    public Employee(String name, LocalDate birthday) {
        this.name = name;
        this.birthday = birthday;
        this.age = calculateAge();

    }

    public int getBirthdayMonth() {
        return birthday.getMonthValue();
    }

    private int calculateAge() {
        LocalDate localDate = LocalDate.now();
        Period period = Period.between(birthday, localDate);
        return period.getYears();
    }

    @Override
    public String toString() {
        return "Name: " + name + " - Age: " + age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public LocalDate getBirtday() {
        return birthday;
    }
}
