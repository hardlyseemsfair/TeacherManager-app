package tasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import handlers.ServerRequestHandler;
import helper.Note;
import helper.NoteManager;
import helper.Student;
import util.Config;

/**
 * Created by NAPOLEON on 5/14/2015.
 */
public class GetNotesTask extends AsyncTask <Void, NoteManager, NoteManager> {


    private OnTaskCompleteListener mCallback;

    public GetNotesTask(Activity activity) {
        mCallback = (OnTaskCompleteListener) activity;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(NoteManager noteManager) {
        mCallback.addNotes(noteManager);
    }

    @Override
    protected NoteManager doInBackground(Void... v) {
        NoteManager nm = new NoteManager();
        JSONObject json = ServerRequestHandler.getNotes();
        int responseCode = 0;
        try {
            if (json.getString(Config.KEY_SUCCESS) != null) {
                Log.i("GET NOTES", "Processing JSON string: " + json);
                String res = json.getString(Config.KEY_SUCCESS);
                if (Integer.parseInt(res) == 1) {
                    nm = buildNoteManager(json);
                } else {
                    // Error in process
                    Log.i("GET NOTES", "get failure");
                }
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("GET NOTES", "Returning from getting notes list");

        return nm;
    }

    private NoteManager buildNoteManager(JSONObject json){
        NoteManager nm = new NoteManager();
        try{
            JSONArray notesarray = json.getJSONArray("notes");
            Log.i("GET NOTES", "Returning from getting notes array: " + notesarray);
            if(notesarray != null){
                for(int i = 0; i < notesarray.length(); i++){
                    JSONArray noteObj = notesarray.getJSONArray(i);
                    Log.i("GET NOTES", "Note obj: " + noteObj);
                    Note n = new Note(noteObj.getString(0), noteObj.getString(1), noteObj.getString(2), Integer.valueOf(noteObj.getString(3)));
                    n.setID(Integer.valueOf(noteObj.getString(4)));
                    n.setExistsOnServer();
                    nm.add(n);
                }
            }
        } catch (JSONException je){
            Log.i("GET NOTES", "Notes build failure");
        }
        return nm;
    }

}
