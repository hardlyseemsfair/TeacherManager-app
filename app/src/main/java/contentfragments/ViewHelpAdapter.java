package contentfragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.classroom.applicationactivity.R;

import java.util.ArrayList;
import java.util.List;

import helper.HelpMessage;
import helper.HelpMessageManager;
import helper.Note;

/**
 * Created by NAPOLEON on 5/19/2015.
 */
public class ViewHelpAdapter extends ArrayAdapter<HelpMessage> {

    private static final int MAX_NOTE_LENGTH = 50;

    static class ViewHolder {
        TextView student;
        TextView filename;
        TextView rating;
        TextView message;
    }

    HelpMessageManager manager;

    public ViewHelpAdapter(Context c, int resource, ArrayList<HelpMessage> list ){
        super(c, resource, list);
        manager = (HelpMessageManager)list;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListView listView = (ListView) parent;
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_view_help, null);
            holder = new ViewHolder();
            holder.student = (TextView) convertView.findViewById(R.id.student_name);
            holder.filename = (TextView) convertView.findViewById(R.id.filename);
//            holder.rating = (TextView) convertView.findViewById(R.id.rating);
//            holder.message = (TextView) convertView.findViewById(R.id.message);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        HelpMessage helpMessage = manager.get(position);
        holder.student.setText(helpMessage.getStudent_name());
        holder.filename.setText(helpMessage.getFilename());
        //holder.rating.setText(helpMessage.getRatingAsString());
        //String shortnote = helpMessage.getMessage();
//        if (shortnote.length() > MAX_NOTE_LENGTH) {
//            shortnote = shortnote.substring(0, MAX_NOTE_LENGTH) + "...";
//        } else if (shortnote.isEmpty()) {
//            shortnote = " -- ";
//        }
//        holder.message.setText(shortnote);
//        if(helpMessage.getViewed() == 1){
//            listView.getChildAt(position).setBackgroundColor(Color.GREEN);
//        }
        return convertView;
    }

}
