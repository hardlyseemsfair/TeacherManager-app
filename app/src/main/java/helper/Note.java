package helper;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by NAPOLEON on 5/11/2015.
 */
public class Note implements Parcelable {

    private String flag;
    private String title;
    private String note;
    private int rating = -1;
    private boolean onServer = false;
    int id = -1;

    public Note(String category, String title, String message, int rating) {
        this.flag = category;
        this.title = title;
        this.note = message;
        this.rating = rating;
    }

    public Note(String category, String title, String message) {
        this.flag = category;
        this.title = title;
        this.note = message;
    }

    public void setExistsOnServer(){
        onServer = true;
    }

    public void setID(int i){
        id = i;
    }

    public int getID(){
        return id;
    }

    public boolean isOnServer(){
        return onServer;
    }

    public String getCategory() {
        return flag;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return note;
    }

    public int getRating(){
        return rating;
    }

    public String getRatingString() {
        if (rating == -1) {
            return "";
        }
        return Integer.toString(rating);
    }

    public Note(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents(){
        return 0;
    }

    public void readFromParcel(Parcel in){
        this.flag = in.readString();
        this.title = in.readString();
        this.note = in.readString();
        this.rating = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(flag);
        dest.writeString(title);
        dest.writeString(note);
        dest.writeInt(rating);
    }

    public static final Parcelable.Creator<Note> CREATOR
            = new Parcelable.Creator<Note>(){
        public Note createFromParcel(Parcel in){
            return new Note(in);
        }

        public Note[] newArray(int size){
            return new Note[size];
        }
    };

}
