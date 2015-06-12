package contentfragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.classroom.applicationactivity.R;

import java.util.ArrayList;

import helper.Note;
import helper.Student;

/**
 * Created by NAPOLEON on 5/12/2015.
 */
public class ViewNoteAdapter extends ArrayAdapter<Note> {

    private static final int MAX_NOTE_LENGTH = 50;

    static class ViewHolder{
        TextView category;
        TextView title;
        TextView note;
        TextView metric;
    }
    
    ArrayList<Note> notes;

    public ViewNoteAdapter(Context c, int resource, ArrayList<Note> list ){
        super(c, resource, list);
        notes = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_view_note, null);
            holder = new ViewHolder();
            holder.category = (TextView) convertView.findViewById(R.id.category);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.note = (TextView) convertView.findViewById(R.id.note);
            holder.metric = (TextView) convertView.findViewById(R.id.metric);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Note workingnote = notes.get(position);
        holder.category.setText(workingnote.getCategory());
        holder.title.setText(workingnote.getTitle());

        String shortnote = workingnote.getMessage();
        if(shortnote.length() > MAX_NOTE_LENGTH) shortnote = shortnote.substring(0, MAX_NOTE_LENGTH) + "...";
        holder.note.setText(shortnote);
        if(position == 0){
            holder.metric.setText("#");
        } else if (workingnote.getRating() == -1){
            holder.metric.setText("-");
        } else {
            holder.metric.setText(workingnote.getRatingString());
        }
        return convertView;
    }





}
