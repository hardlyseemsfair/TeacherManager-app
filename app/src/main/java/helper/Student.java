package helper;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by NAPOLEON on 5/5/2015.
 */
public class Student implements Parcelable {

    private String firstname;
    private String lastname;
    private String username;



    public Student(String firstname, String lastname, String username) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Student(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents(){
        return 0;
    }

    public void readFromParcel(Parcel in){
        this.firstname = in.readString();
        this.lastname = in.readString();
        this.username = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(firstname);
        dest.writeString(lastname);
        dest.writeString(username);
    }

    public static final Parcelable.Creator<Student> CREATOR
            = new Parcelable.Creator<Student>(){
                public Student createFromParcel(Parcel in){
                    return new Student(in);
                }

                public Student[] newArray(int size){
                    return new Student[size];
                }
    };

    @Override
    public String toString() {
        return firstname + " " + lastname;
    }
}
