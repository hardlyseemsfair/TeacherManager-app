package tasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import handlers.ServerRequestHandler;
import helper.HelpMessage;
import helper.HelpMessageManager;
import helper.Note;
import helper.NoteManager;
import util.Config;

/**
 * Created by NAPOLEON on 5/14/2015.
 */
public class CommitNotesTask extends AsyncTask <NoteManager, Integer, Integer>{

    private OnTaskCompleteListener mCallback;

    public CommitNotesTask(Activity activity) {
        mCallback = (OnTaskCompleteListener) activity;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(Integer res) {
        if(res == -1){
            Log.i("COMMIT UPDATE", "Returning - Nothing to commit");
        } else {
            Log.i("COMMIT UPDATE", "Returning from commit");
        }
    }

    @Override
    protected Integer doInBackground(NoteManager... obj) {
        NoteManager nm =  obj[0];
        nm = setUpdateNotes(nm);
        int responseCode = -1;
        //If nm and hmm are empty there is nothing to send, all current table date exist on server
        if(!nm.isEmpty()) {
            JSONObject json = ServerRequestHandler.sendNotesToServer(nm);
            try {
                if (json.getString(Config.KEY_SUCCESS) != null) {
                    Log.i("COMMIT UPDATE", "Processing JSON string: " + json);
                    String res = json.getString(Config.KEY_SUCCESS);
                    if (Integer.parseInt(res) == 1) {

                    } else {
                        // Error in process
                        Log.i("COMMIT UPDATE", "insert failure");
                    }
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.i("COMMIT UPDATE", "Returning from getting student list");

        }
        return responseCode;
    }

    private NoteManager setUpdateNotes(NoteManager noteManager){
        NoteManager nm = new NoteManager();
        for(Note n : noteManager){
            if(!n.isOnServer()){
                nm.add(n);
            }
        }
        return nm;
    }



}
