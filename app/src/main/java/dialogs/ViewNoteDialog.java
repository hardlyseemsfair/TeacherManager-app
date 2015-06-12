package dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.classroom.applicationactivity.R;

import helper.Note;

/**
 * Created by NAPOLEON on 5/14/2015.
 */
public class ViewNoteDialog  extends DialogFragment{

    private DialogTaskListener mCallback;
    Note note;

    /**
     * onCreateDialog
     * expects arguments for tags usergroups(usergroups the user belongs to)
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View v = getCustomView();
        note = getArguments().getParcelable("note");
        TextView cat = (TextView) v.findViewById(R.id.heading_content);
        TextView title = (TextView) v.findViewById(R.id.title_content);
        TextView message = (TextView) v.findViewById(R.id.note_content);
        TextView rating = (TextView) v.findViewById(R.id.metric_content);
        cat.setText(note.getCategory());
        title.setText(note.getTitle());
        message.setText(note.getMessage());
        rating.setText(note.getRatingString());
        builder.setTitle("Note");
        builder.setPositiveButton("Delete Note", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                Log.i("VIEW NOTE", "Deleting note");
                mCallback.removeNote(note);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.

            }
        });
        builder.setView(v);
        return builder.create();
    }


    private View getCustomView() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View v = inflater.inflate(R.layout.dialog_view_note, null);
        return v;
    }




    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        mCallback = (DialogTaskListener)activity;
    }
}
