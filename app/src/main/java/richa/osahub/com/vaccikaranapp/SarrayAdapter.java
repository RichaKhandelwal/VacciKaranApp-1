package richa.osahub.com.vaccikaranapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LG-11-21 on 7/21/2015.
 */
public class SarrayAdapter extends ArrayAdapter {
    LayoutInflater inflater;
    List<SfeedPojo> list=new ArrayList<>();

    public SarrayAdapter(Activity activity, List<SfeedPojo> objects) {
      
        super(activity,R.layout.layout, objects);
        list=objects;
        inflater=activity.getWindow().getLayoutInflater();
    }
    @Override
    public View getView(int position,View view,ViewGroup parent)
    {
        View rootview=inflater.inflate(R.layout.layout,parent,false);
        TextView personname=(TextView)rootview.findViewById(R.id.personname);
        TextView post=(TextView)rootview.findViewById(R.id.post);
        personname.setText(list.get(position).getUsername());
        post.setText(list.get(position).getPost());
        return rootview;
    }

}
