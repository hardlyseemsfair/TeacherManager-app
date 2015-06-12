package contentfragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.classroom.applicationactivity.R;

import java.util.ArrayList;
import java.util.List;

import helper.Student;
import util.Config;
import util.SessionLog;
import util.ToastMessages;

/**
 * Created by NAPOLEON on 5/5/2015.
 */
public class CreateGroupFragment extends Fragment {

    private ApplicationFragmentListener mCallback;
    private SessionLog sessionLog;

    EditText groupName;

    ListView studentList;
    ListView proposedGroup;

    List<Student> students;
    List<Student> groupMembers = new ArrayList<>();



    public static final CreateGroupFragment newInstance(ArrayList<Student> students) {
        CreateGroupFragment cgf = new CreateGroupFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("students", students);
        cgf.setArguments(args);
        return cgf;
    }


    /**
     * onCreate
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_group, container, false);
        if(savedInstanceState != null) {
            students = getArguments().getParcelableArrayList("students");
        } else {
            students = mCallback.getStudentList();
            Log.w("CREATE GROUP FRAG", "Bundle empty");
        }
        groupName = (EditText) view.findViewById(R.id.group_name);
        groupName.requestFocus();
        InputMethodManager img = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        img.showSoftInput(groupName, 0);
        Log.v("CREATE GROUP FRAG", "Student list: " + students);
        studentList = (ListView) view.findViewById(R.id.student_list);
        proposedGroup = (ListView) view.findViewById(R.id.group_list);
        studentList.setAdapter(getAdapter(students));
        proposedGroup.setAdapter(getAdapter(groupMembers));
        studentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                addStudentToGroup(students.get(i));
            }
        });
        proposedGroup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               removeStudentFromGroup(i);
            }
        });

        Button createGroupButton = (Button) view.findViewById(R.id.create_group_button);
        Button cancelButton = (Button) view.findViewById(R.id.cancel_create_group_button);
        createGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(groupMembers.isEmpty()) {
                    ToastMessages.shortToast("Group is empty. First add members to create a group.", 16, getActivity());
                } else if (groupName.getText().toString().isEmpty() || groupName.getText().toString().equalsIgnoreCase("")){
                    ToastMessages.shortToast("You must provide a group name.", 16, getActivity());
                } else {
                    String name = Config.formatGroupNameDisplay(groupName.getText().toString());
                    mCallback.createNewStudentGroup(groupMembers, name);
                    groupName.clearFocus();
                    getActivity().onBackPressed();

                }

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                getActivity().onBackPressed();
            }
        });

        return view;
    }


    private CreateGroupAdapter getAdapter(List<Student> st){
        return new CreateGroupAdapter(getActivity(), R.layout.adapter_create_group, st);
    }

    private void addStudentToGroup(Student s){
        if(!groupMembers.contains(s)) groupMembers.add(s);
        proposedGroup.setAdapter(getAdapter(groupMembers));
    }

    private void removeStudentFromGroup(int position){
        Student s = groupMembers.get(position);
        Log.v("CREATE GROUP FRAG", "Attempting to remove " + s + " from collection " + groupMembers + " at index " + position);
        groupMembers.remove(s);
        proposedGroup.setAdapter(getAdapter(groupMembers));
    }



    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        mCallback = (ApplicationFragmentListener) activity;
        sessionLog = mCallback.getSessionLog();
    }


}
