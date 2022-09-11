package by.anabios13.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    private int personId;
    @NotEmpty(message = "Name should not be empty")
    @Size(min=1,max=30,message = "Name should be between 2 and 30 characters")
    private String name;

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
    @Min(value =1900, message="Year should be before 1900")
    private int yearOfBirth;
}
