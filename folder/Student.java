public class Student extends Person {
    private int id;

    public Student(String firstName, String lastName, int id){
        super(firstName,lastName);
        this.id = id;
    }

    public int getID(){
        return id;
    }

    public boolean equals(Object o){
        if (o instanceof Student){
            Student p = (Student) o;
            return super.equals(p) && p.getID() == id;
        }
        return false;
    }

    public String toString(){
        return id+" - "+super.toString();
    }
}
