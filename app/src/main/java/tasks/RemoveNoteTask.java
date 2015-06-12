package tasks;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import handlers.ServerRequestHandler;
import util.Config;

/**
 * Created by NAPOLEON on 5/14/2015.
 */
public class RemoveNoteTask extends AsyncTask <Integer, Void, Void> {

    public RemoveNoteTask() {    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(Void v){

    }

    @Override
    protected Void doInBackground(Integer... i) {
        int id = i[0];
        JSONObject json = ServerRequestHandler.deleteNoteFromServer(id);
        int responseCode = 0;
        try {
            if (json.getString(Config.KEY_SUCCESS) != null) {
                Log.i("DELETE NOTES", "Processing JSON string: " + json);
                String res = json.getString(Config.KEY_SUCCESS);
                if (Integer.parseInt(res) == 1) {

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

        return null;
    }



}
