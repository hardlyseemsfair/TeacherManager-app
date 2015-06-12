package tasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import handlers.ServerRequestHandler;
import helper.Group;
import helper.Student;
import util.Config;

/**
 * Created by NAPOLEON on 5/8/2015.
 */
public class GetGroupTask extends AsyncTask <String, Group, Group> {

    private OnTaskCompleteListener mCallback;

    public GetGroupTask(Activity activity){
        mCallback = (OnTaskCompleteListener)activity;
    }


    @Override
    public void onPostExecute(Group group){
        //todo Return the group and add it to the applications groupmanager
        mCallback.addToGroupManager(group);
    }

    public Group doInBackground(String... args){
        String groupname = args[0];
        Group group = new Group(groupname);
        JSONObject json = ServerRequestHandler.getGroupMembers(groupname);
        int responseCode = 0;
        // check for response
        Log.i("GET GROUP TASK", "Processing JSON string " + json);
        try {
            if (json.getString(Config.KEY_SUCCESS) != null) {
                Log.i("GET GROUP TASK", "Processing JSON flag success");
                String res = json.getString(Config.KEY_SUCCESS);
                if (Integer.parseInt(res) == 1) {
                    group = buildGroup(groupname, json);
                } else {
                    // Error in process
                    Log.i("STUDENT LIST", "Get group failure");
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("GET GROUP TASK", "Returning from getting group list");

        return group;
    }

    private Group buildGroup(String groupname, JSONObject json){
        Group group = new Group(groupname);
        try {
            JSONArray ja = json.getJSONArray("group");
            if(ja != null){
                int l = ja.length();
                for(int i = 0; i < l; i++){
                    JSONArray student = ja.getJSONArray(i);
                    group.add(new Student(student.getString(0), student.getString(1), student.getString(2)));
                }
            }
        } catch (JSONException je){
            Log.i("GET GROUP TASK", "Error in json");
        }
        return group;
    }

}
