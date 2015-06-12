package tasks;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import handlers.ServerRequestHandler;
import util.Config;

/**
 * Created by NAPOLEON on 5/21/2015.
 */
public class RemoveHelpTask extends AsyncTask<Integer, Void, Void> {

    public RemoveHelpTask() {    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(Void v){

    }

    @Override
    protected Void doInBackground(Integer... i) {
        int id = i[0];
        JSONObject json = ServerRequestHandler.deleteHelpFromServer(id);
        int responseCode = 0;
        try {
            if (json.getString(Config.KEY_SUCCESS) != null) {
                Log.i("REMOVE HELP", "Processing JSON string: " + json);
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
        Log.i("REMOVE HELP", "Returning from getting notes list");

        return null;
    }

}
