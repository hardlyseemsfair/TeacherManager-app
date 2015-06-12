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

import dialogs.ViewHelpDialog;
import dialogs.ViewNoteDialog;
import helper.HelpMessage;
import helper.HelpMessageManager;
import helper.Note;
import helper.NoteManager;

/**
 * Created by NAPOLEON on 5/19/2015.
 */
public class ViewHelpFragment extends Fragment {

    public static final ViewHelpFragment newInstance() {
        ViewHelpFragment vhf = new ViewHelpFragment();
        return vhf;
    }

    private HelpMessageManager manager;
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
        View view = inflater.inflate(R.layout.fragment_view_help, container, false);
        listView = (ListView) view.findViewById(R.id.help_list);
        setAdapter();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HelpMessage m = manager.get(i);
                Bundle bundle = new Bundle();
                bundle.putParcelable("help", m);
                //TODO convert to view dialog
                ViewHelpDialog dialog = new ViewHelpDialog();
                dialog.setArguments(bundle);
                dialog.show(getActivity().getSupportFragmentManager(), "viewHelpDialog");
            }
        });
        return view;
    }

    public void setAdapter(){
        manager = mCallback.getHelpMessages();
        ViewHelpAdapter adapter = new ViewHelpAdapter(getActivity(), 0, manager);
        listView.setAdapter(adapter);
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        mCallback = (ApplicationFragmentListener) activity;
    }
}
