package contentfragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.classroom.applicationactivity.R;

import java.util.ArrayList;

import dialogs.ViewGroupsDialog;
import dialogs.ViewNoteDialog;
import helper.Note;
import helper.NoteManager;
import helper.Student;

/**
 * Created by NAPOLEON on 5/12/2015.
 */
public class ViewNoteFragment extends Fragment {

    public static final ViewNoteFragment newInstance() {
        ViewNoteFragment vnf = new ViewNoteFragment();
        return vnf;
    }

    private NoteManager noteManager;
    private ApplicationFragmentListener mCallback;
    private ListView listView;

    /**
     * onCreate
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_note, container, false);
        listView = (ListView) view.findViewById(R.id.note_list);
        setAdapter();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    Note n = noteManager.get(i);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("note", n);
                    ViewNoteDialog dialog = new ViewNoteDialog();
                    dialog.setArguments(bundle);
                    dialog.show(getActivity().getSupportFragmentManager(), "viewNoteDialog");
                }
            }
        });
        return view;
    }


    public void setAdapter(){
        noteManager = mCallback.getNoteManager();
        ViewNoteAdapter adapter = new ViewNoteAdapter(getActivity(), 0, noteManager);
        listView.setAdapter(adapter);
    }


    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        mCallback = (ApplicationFragmentListener) activity;
    }
}


