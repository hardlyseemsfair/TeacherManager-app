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

import helper.HelpMessage;
import helper.Note;

/**
 * Created by NAPOLEON on 5/21/2015.
 */
public class ViewHelpDialog extends DialogFragment {


    private DialogTaskListener mCallback;
    HelpMessage helpMessage;

    /**
     * onCreateDialog
     * expects arguments for tags usergroups(usergroups the user belongs to)
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View v = getCustomView();
        helpMessage = getArguments().getParcelable("help");
        TextView name = (TextView) v.findViewById(R.id.student_name_content);
        TextView file = (TextView) v.findViewById(R.id.filename_content);
        TextView rating = (TextView) v.findViewById(R.id.rating_content);
        TextView message = (TextView) v.findViewById(R.id.message);
        name.setText(helpMessage.getStudent_name());
        String filename = helpMessage.getFilename();
        try {
            filename = filename.substring(0, filename.lastIndexOf('.'));
        } catch (StringIndexOutOfBoundsException e){}
        file.setText(filename);
        rating.setText(helpMessage.getRatingAsString());
        message.setText(helpMessage.getMessage());
        builder.setTitle("Help Message");
        builder.setPositiveButton("Delete Message", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                mCallback.removeHelpMessage(helpMessage);
                mCallback.updateHelpFragment();
            }
        });
        if(helpMessage.getViewed() != 1) {
            builder.setNeutralButton("Mark as Viewed", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int whichButton) {
                    //Delete message
                    helpMessage.setViewed(1);
                    mCallback.updateHelpFragment();
                }
            });
        }
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                //cancle


            }
        });
        builder.setView(v);
        return builder.create();
    }


    private View getCustomView() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View v = inflater.inflate(R.layout.dialog_view_help, null);
        return v;
    }




    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        mCallback = (DialogTaskListener)activity;
    }


}
