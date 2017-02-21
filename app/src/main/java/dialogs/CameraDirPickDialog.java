package dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import util.ToastMessages;

/**
 * Created by arms0077 on 11/10/2015.
 */
public class CameraDirPickDialog extends DialogFragment {

    private DialogTaskListener mCallback;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ArrayList<String> listContent = new ArrayList<>();
        listContent.add(mCallback.getUsername());
        listContent.addAll(mCallback.getUserGroups());

        ListView list = new ListView(getActivity());
        final String[] content = listContent.toArray(new String[listContent.size()]);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,content);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                ToastMessages.shortToast("Clicked: " + content[position], 20, getActivity());
                doIt(content[position]);

            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        Log.i("CAMERA PICKER", "");
        builder.setTitle("Note Shot");
        // builder.setMessage("Request help");
        builder.setMessage("Where do you want to send the picture?");

        builder.setView(list);
        return builder.create();
    }

    private void doIt(String s){
        mCallback.handleCameraDir(s);
        this.dismiss();
    }

    /**
     * onAttach
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallback = (DialogTaskListener) activity;
    }

}
