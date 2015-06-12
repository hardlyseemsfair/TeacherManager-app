package tasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import handlers.ServerRequestHandler;
import helper.HelpMessage;
import helper.HelpMessageManager;
import helper.NoteManager;
import util.Config;

/**
 * Created by NAPOLEON on 5/21/2015.
 */
public class CommitHelpTask extends AsyncTask<HelpMessageManager, Integer, Integer> {

    private OnTaskCompleteListener mCallback;

    public CommitHelpTask(Activity activity) {
        mCallback = (OnTaskCompleteListener) activity;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(Integer res) {
        if(res == -1){
            Log.i("COMMIT HELP", "Returning - Nothing to commit");
        } else {
            Log.i("COMMIT HELP", "Returning from commit");
        }
    }

    @Override
    protected Integer doInBackground(HelpMessageManager... obj) {
        HelpMessageManager hmm =  obj[0];
        hmm = setUpdateHelp(hmm);
        int responseCode = -1;
        //If nm and hmm are empty there is nothing to send, all current table date exist on server
        if(!hmm.isEmpty()) {
            JSONObject json = ServerRequestHandler.sendHelpToServer(hmm);
            try {
                if (json.getString(Config.KEY_SUCCESS) != null) {
                    Log.i("COMMIT HELP", "Processing JSON string: " + json);
                    String res = json.getString(Config.KEY_SUCCESS);
                    if (Integer.parseInt(res) == 1) {

                    } else {
                        // Error in process
                        Log.i("COMMIT HELP", "insert failure");
                    }
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.i("COMMIT HELP", "Returning from getting student list");

        }
        return responseCode;
    }

    private HelpMessageManager setUpdateHelp(HelpMessageManager hmm){
        HelpMessageManager newHMM = new HelpMessageManager();
        for(HelpMessage hm : hmm){
            if(hm.updateToServer()){
                newHMM.add(hm);
            }
        }
        return hmm;
    }
}
