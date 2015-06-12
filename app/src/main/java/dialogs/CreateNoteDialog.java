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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.classroom.applicationactivity.R;

import java.util.ArrayList;
import java.util.Arrays;

import helper.Note;
import helper.Student;

/**
 * Created by NAPOLEON on 5/11/2015.
 */
public class CreateNoteDialog extends DialogFragment {

    ArrayList<Student> students;
    DialogTaskListener mCallback;
    int metricvalue = -1;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View v = getCustomView();
        builder.setTitle("Create note");

        students = getArguments().getParcelableArrayList("students");
        String[] list = new String[students.size()+1];
        list[0] = "General note";
        for(int i = 0; i < list.length-1; i++){
            list[i+1] = students.get(i).toString();
        }
        Log.v("CREATE NOTE", Arrays.toString(list));
        final Spinner spinner = (Spinner) v.findViewById(R.id.heading_content);
        final ArrayAdapter<String> spinnerList = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, list);
        spinnerList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerList);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        final EditText title = (EditText) v.findViewById(R.id.title_content);
        final EditText notetext = (EditText) v.findViewById(R.id.note_content);
        final CheckBox checkBox = (CheckBox) v.findViewById(R.id.include_metric_check);
        final SeekBar seekbar = (SeekBar) v.findViewById(R.id.seekbar);
        final TextView seekValue = (TextView) v.findViewById(R.id.seekbar_value);
        seekValue.setText(Integer.toString(seekbar.getProgress()));
        seekbar.setVisibility(View.GONE);
        seekValue.setVisibility(View.GONE);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekValue.setText(Integer.toString(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    seekbar.setVisibility(View.VISIBLE);
                    seekValue.setVisibility(View.VISIBLE);
                } else {
                    seekbar.setVisibility(View.GONE);
                    seekValue.setVisibility(View.GONE);
                    metricvalue = -1;
                }
            }
        });
        builder.setView(v);
        builder.setPositiveButton("Create Note", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                if(checkBox.isChecked()) metricvalue = seekbar.getProgress();
                Note note = new Note(spinner.getSelectedItem().toString(),
                        title.getText().toString(),
                        notetext.getText().toString(),
                        metricvalue);
                mCallback.addNote(note);
            }

        });
        //Set cancel button
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled, no action required
            }
        });
        return builder.create();
    }


    private View getCustomView() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View v = inflater.inflate(R.layout.dialog_create_note, null);
        return v;
    }

    public void onAttach(Activity activity){
        super.onAttach(activity);
        mCallback = (DialogTaskListener) activity;
    }


}
