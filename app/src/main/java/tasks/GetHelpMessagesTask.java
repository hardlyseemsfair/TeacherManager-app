package tasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import handlers.ServerRequestHandler;
import helper.HelpMessage;
import helper.HelpMessageManager;
import util.Config;

/**
 * Created by NAPOLEON on 5/21/2015.
 */
public class GetHelpMessagesTask extends AsyncTask<Void, HelpMessageManager, HelpMessageManager> {


    private final int ID = 0;
    private final int USERNAME = 1;
    private final int FILENAME = 2;
    private final int RATING = 3;
    private final int MESSAGE = 4;
    private final int VIEWED = 5;
    private OnTaskCompleteListener mCallback;

    public GetHelpMessagesTask(Activity activity) {
        mCallback = (OnTaskCompleteListener) activity;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(HelpMessageManager hmm) {
        mCallback.setHelpMessages(hmm);
    }

    @Override
    protected HelpMessageManager doInBackground(Void... v) {
        HelpMessageManager hmm = new HelpMessageManager();
        JSONObject json = ServerRequestHandler.getHelpMessages();
        int responseCode = 0;
        try {
            if (json.getString(Config.KEY_SUCCESS) != null) {
                Log.i("GET HELP", "Processing JSON string: " + json);
                String res = json.getString(Config.KEY_SUCCESS);
                if (Integer.parseInt(res) == 1) {
                    hmm = buildHelpMessageManager(json);
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
        return hmm;
    }

    private HelpMessageManager buildHelpMessageManager(JSONObject json) {
        HelpMessageManager hmm = new HelpMessageManager();
        hmm.add(new HelpMessage(-1,"Touch to open", "",-1,"",-1));
        try {
            JSONArray helparray = json.getJSONArray("help_messages");
            if (helparray != null) {
                Log.i("GET HELP", "Help Array: " + helparray);
                for (int i = 0; i < helparray.length(); i++) {
                    JSONArray helpObj = helparray.getJSONArray(i);
                    Log.i("GET HELP", "Help obj: " + helpObj);
                    HelpMessage hm = new HelpMessage(helpObj.getInt(ID), helpObj.getString(USERNAME),
                            helpObj.getString(FILENAME), helpObj.getInt(RATING),
                            helpObj.getString(MESSAGE), helpObj.getInt(VIEWED));
                    hmm.add(hm);
                }
            }
        } catch (JSONException je) {
            Log.i("GET HELP", "HELP build failure");
        }
        Log.i("GET HELP", "Help messages: " + hmm);
        return hmm;
    }

}
