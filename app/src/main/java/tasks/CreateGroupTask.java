package tasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import handlers.ServerRequestHandler;
import util.Config;
import util.ToastMessages;

/**
 * Created by NAPOLEON on 5/5/2015.
 */
public class CreateGroupTask extends AsyncTask<String, String, String> {

    Activity activity;
    OnTaskCompleteListener mCallback;

    public CreateGroupTask(Activity activity) {

        this.activity = activity;
        mCallback = (OnTaskCompleteListener) activity;
    }

    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String groupname) {
        if (!groupname.equalsIgnoreCase("error")) {
            ToastMessages.shortToast("Group " + groupname + " created successfully", 16, activity);
        } else {
            ToastMessages.shortToast("There was a problem creating the group", 16, activity);
        }
        mCallback.updateUserGroups(groupname);
    }

    @Override
    protected String doInBackground(String... st) {
        String groupname = st[0];
        String[] usernames = new String[st.length - 1];
        for (int i = 1; i < st.length; i++) {
            usernames[i - 1] = st[i];
        }
        JSONObject json = ServerRequestHandler.createStudentGroup(usernames, groupname);
        int responseCode = 0;
        // check for login response
        try {
            if (json.getString(Config.KEY_SUCCESS) != null) {
                Log.i("PROCESS", "Processing JSON string");
                String res = json.getString(Config.KEY_SUCCESS);
                if (Integer.parseInt(res) == 1) {
                    //Everything good
                    responseCode = 1;
                    Log.i("CREATE GROUP TASK", "Returning success, group name: " + groupname);
                    return groupname;
                } else {
                    // Error in process
                    Log.i("PROCESS", "HELP REQUEST FAILURE string");
                    responseCode = 0;
                }
            }

        } catch (NullPointerException e) {
            e.printStackTrace();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("PROCESS", "Returning from login task rsponse : " + responseCode);
        return "error";
    }

}
