package helper;

import java.util.ArrayList;

/**
 * Created by NAPOLEON on 5/11/2015.
 */
public class NoteManager extends ArrayList<Note> {



    public NoteManager(){
        super();
    }






    public boolean exists(Note inNote){
        for (Note note : this){
            if(inNote.equals(note)) return true;
        }
        return false;
    }


}
