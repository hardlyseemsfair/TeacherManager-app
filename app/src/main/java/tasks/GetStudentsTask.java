package tasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

import handlers.ServerRequestHandler;
import helper.Student;
import util.Config;
import util.MessageBank;

/**
 * Created by NAPOLEON on 5/5/2015.
 */
public class GetStudentsTask extends AsyncTask <String, ArrayList<Student>, ArrayList<Student>> {

    private OnTaskCompleteListener mCallback;

    public GetStudentsTask(Activity activity) {
        mCallback = (OnTaskCompleteListener) activity;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(ArrayList<Student> result) {
        Log.v("STUDENT LIST", "LIST: " + result.toString());
        mCallback.setStudentList(result);
    }

    @Override
    protected ArrayList<Student> doInBackground(String... v) {
        ArrayList<Student> students = new ArrayList<>();
        JSONObject json = ServerRequestHandler.getStudentList();
        int responseCode = 0;
        // check for response
        Log.i("STUDENT LIST", "Processing JSON string " + json);
        try {
            if (json.getString(Config.KEY_SUCCESS) != null) {
                Log.i("STUDENT LIST", "Processing JSON string");
                String res = json.getString(Config.KEY_SUCCESS);
                if (Integer.parseInt(res) == 1) {
                    students = buildStudentList(json);
                } else {
                    // Error in process
                    Log.i("STUDENT LIST", "Get list failure");
                }
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("STUDENT LIST", "Returning from getting student list");
        return students;
    }

    private ArrayList<Student> buildStudentList(JSONObject json){
        ArrayList<Student> students = new ArrayList<>();
        try {
            JSONArray ja = json.getJSONArray("students");
            if(ja != null){
                int l = ja.length();
                for(int i = 0; i < l; i++){
                    JSONArray student = ja.getJSONArray(i);
                    students.add(new Student(student.getString(0), student.getString(1), student.getString(2)));
                }
            }
        } catch (JSONException je){
            Log.i("STUDENT LIST", "Error in json");
        }
        return students;
    }



}
