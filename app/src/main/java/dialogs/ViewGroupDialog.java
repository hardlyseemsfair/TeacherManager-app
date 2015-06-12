package dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import helper.Group;

/**
 * Created by NAPOLEON on 5/11/2015.
 */
public class ViewGroupDialog extends DialogFragment {

    //Instance variables
    Group group;
    private DialogTaskListener mCallback;


    /**
     * onCreateDialog
     * expects arguments for tags usergroups(usergroups the user belongs to)
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final String groupname = getArguments().getString("groupname");
        group = mCallback.getGroup(groupname);

        builder.setTitle(groupname)
                .setItems(group.getStudentNamesArray(), null);
        builder.setPositiveButton("Delete Group", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                mCallback.deleteGroup(groupname);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

        return builder.create();
    }


    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        mCallback = (DialogTaskListener)activity;
    }

}

