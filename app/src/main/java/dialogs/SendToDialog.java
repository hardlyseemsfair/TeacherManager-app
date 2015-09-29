package dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.classroom.applicationactivity.R;

import util.Config;
import util.SessionLog;
import util.ToastMessages;

/**
 * Created by NAPOLEON on 5/17/2015.
 */
public class SendToDialog extends DialogFragment {


    //Instance Variables
    private String filename;
    private String username;
    private String workingDIR;
    private String[] options = {Config.STUDENT, Config.GROUP, Config.STUDENT_SET, Config.GROUP_SET};
    private DialogTaskListener mCallback;
    private SessionLog sessionLog;

    /**
     * onCreateDialog
     * expects arguments for tags filename(filename to delete), username (current user) and dirPath(directory of file to be deleted)
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        filename = getArguments().getString("filename");
        username = getArguments().getString("username");
        workingDIR = getArguments().getString("workingDIR");
        builder.setTitle("Send file " + filename + " to..");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                onSelect(which);
            }
        });
        return builder.create();
    }

    private void onSelect(int i){

        if(options[i].equals(Config.STUDENT)){
            mCallback.sendFileToTarget(workingDIR, filename, Config.STUDENT);
        } else if (options[i].equals(Config.GROUP)){
            mCallback.sendFileToTarget(workingDIR, filename, Config.GROUP);
        } else if (options[i].equals(Config.STUDENT_SET)){
            mCallback.sendFileToSet(workingDIR, filename, Config.STUDENT_SET);
        } else if (options[i].equals(Config.GROUP_SET)){
            mCallback.sendFileToSet(workingDIR, filename, Config.GROUP_SET);
        }
    }

    public void onAttach(Activity activity){
        super.onAttach(activity);
        mCallback = (DialogTaskListener) activity;
    }

}
