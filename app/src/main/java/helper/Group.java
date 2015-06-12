package helper;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by NAPOLEON on 5/8/2015.
 */
public class Group {

    private String groupname;

    ArrayList<Student> students;

    public Group(String groupname, ArrayList<Student> students){
        this.groupname = groupname;
        this.students = students;
    }

    public Group(String groupname){
        this.groupname = groupname;
        students = new ArrayList<>();
    }

    public Group(){
        this.groupname = "";
        students = new ArrayList<>();
    }

    public ArrayList<Student> getStudents(){
        return students;
    }

    public Student getStudentByUsername(String username){
        int index = -1;
        for(int i = 0; i < students.size(); i++){
            if(username.equalsIgnoreCase(students.get(i).getUsername())){
                index = i;
                break;
            }
        }
        return students.get(index);
    }

    public boolean userExistsInGroup(String username){
        for(int i = 0; i < students.size(); i++){
            if(username.equalsIgnoreCase(students.get(i).getUsername())){
                return true;
            }
        }
        return false;
    }

    public boolean userExistsInGroup(String firstname, String lastname){
        for(int i = 0; i < students.size(); i++){
            if((firstname + " " + lastname).equalsIgnoreCase(students.get(i).toString())){
                return true;
            }
        }
        return false;
    }


    public String[] getStudentNamesArray(){
        String[] list = new String[students.size()];
        for(int i = 0; i < students.size(); i++){
            String name = students.get(i).getFirstname() + " " + students.get(i).getLastname();
            list[i] = name;
        }
        return list;
    }

    public void add(Student s){
        students.add(s);
    }

    public String getGroupname(){
        return groupname;
    }

    @Override
    public String toString(){
        return "Groupname: " + groupname + " | Members: " + students;
    }


}
