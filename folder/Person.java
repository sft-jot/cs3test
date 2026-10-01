public class Person
{
    private String firstName;
    private String lastName;
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Person)
        {
            Person p = (Person)obj;
            return lastName.equals(p.lastName) && firstName.equals(p.firstName);
        }
        else
            return false;
    }
    @Override
    public String toString()
    {
        return lastName+", "+firstName;
    }
}
