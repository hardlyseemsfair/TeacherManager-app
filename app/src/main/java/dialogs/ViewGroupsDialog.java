package dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.classroom.applicationactivity.R;

/**
 * Created by NAPOLEON on 5/8/2015.
 */
public class ViewGroupsDialog extends DialogFragment{

    //Instance variables
    private String[] usergroups;
    private DialogTaskListener mCallback;


    /**
     * onCreateDialog
     * expects arguments for tags usergroups(usergroups the user belongs to)
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        usergroups = getArguments().getStringArray("usergroups");
        builder.setTitle("Select group to view")
                .setItems(usergroups, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        onSelect(which);
                    }
                });

        return builder.create();
    }


    /**
     * Selector for action
     * @param pos index of selected item
     */
    private void onSelect(int pos){
        String group = usergroups[pos];
        mCallback.onGroupSelected(group);

    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        mCallback = (DialogTaskListener) activity;
    }

}
