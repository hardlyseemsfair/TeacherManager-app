package helper;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by NAPOLEON on 5/19/2015.
 */
public class HelpMessage implements Parcelable {

    private int id;
    private String student_name;
    private String filename;
    private int rating;
    private String message;
    private int viewed;
    private boolean updateServer = false;


    public HelpMessage(int id, String student_name, String filename, int rating, String message, int viewed) {
        this.id = id;
        this.student_name = student_name;
        this.filename = filename;
        this.rating = rating;
        this.message = message;
        this.viewed = viewed;
    }

    public int getId(){return id;}

    public void setId(int id) { this.id = id; }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getRating() {
        return rating;
    }

    public String getRatingAsString(){
        return Integer.toString(rating);
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getViewed() {
        return viewed;
    }

    public void setViewed(int viewed) {
        this.viewed = viewed;
    }

    public void flagUpdateToServer(){ updateServer = true;}

    public boolean updateToServer(){ return updateServer; }

    public HelpMessage(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents(){
        return 0;
    }

    public void readFromParcel(Parcel in){
        this.id = in.readInt();
        this.student_name = in.readString();
        this.filename = in.readString();
        this.rating = in.readInt();
        this.message = in.readString();
        this.viewed = in.readInt();
        this.updateServer = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(id);
        dest.writeString(student_name);
        dest.writeString(filename);
        dest.writeInt(rating);
        dest.writeString(message);
        dest.writeInt(viewed);
        dest.writeByte((byte) (updateServer ? 1 : 0));
    }

    public static final Parcelable.Creator<HelpMessage> CREATOR
            = new Parcelable.Creator<HelpMessage>(){
        public HelpMessage createFromParcel(Parcel in){
            return new HelpMessage(in);
        }

        public HelpMessage[] newArray(int size){
            return new HelpMessage[size];
        }
    };

    @Override
    public String toString(){
        return "Id:" + id + ", StudentName: " + student_name + ", Filename: " + filename
                + ", Rating: " + rating + ", Message: " + message + ", Viewed: " + viewed + "\n";
    }
}
