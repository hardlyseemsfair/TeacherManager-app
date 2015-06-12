package tasks;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import handlers.ServerRequestHandler;
import helper.ServerDeleteResponse;
import util.Config;

/**
 * Created by NAPOLEON on 5/11/2015.
 */
public class DeleteGroupTask extends AsyncTask <String, Integer, Integer> {


    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(Integer result) {
        Log.v("DELETE GROUP TASK", "Group deleted");
    }

    @Override
    protected Integer doInBackground(String... values) {
        String groupname = values[0];
        Log.v("DELETE GROUP TASK", "Deleteing group from server");
        JSONObject json = ServerRequestHandler.deleteGroupFromServer(groupname);
        // check for response
        int resp = 0;
        try {
            if (json.getString(Config.KEY_SUCCESS) != null) {
                Log.i("DELETE FROM SERVER TASK", "Processing JSON string");
                String res = json.getString(Config.KEY_SUCCESS);
                if (Integer.parseInt(res) == 1) {
                    // Everything good, build the message list
                    resp = 1;
                } else {
                    Log.i("DELETE GROUP TASK", "FAILURE");
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("DELETE GROUP TASK", "Returning from deleting group");
        return resp;
    }




}





