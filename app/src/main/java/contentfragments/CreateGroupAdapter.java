package contentfragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.classroom.applicationactivity.R;

import java.util.List;

import helper.Student;

/**
 * Created by NAPOLEON on 5/5/2015.
 */
public class CreateGroupAdapter extends ArrayAdapter<Student> {

    List<Student> students;

    static class ViewHolder{
        TextView textView;
    }

    public CreateGroupAdapter(Context context, int resource, List<Student> items){
        super(context, resource, items);
        students = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_create_group, null);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.student_adapter_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Student student = students.get(position);
        holder.textView.setText(student.getFirstname() + " " + student.getLastname());
        return convertView;
    }

}
