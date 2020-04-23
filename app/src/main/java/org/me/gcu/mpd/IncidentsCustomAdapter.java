package org.me.gcu.mpd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;



import org.me.gcu.mpd.model.Incidents;

import java.util.ArrayList;

public class IncidentsCustomAdapter extends ArrayAdapter<Incidents> {

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    private static class ViewHolder {
        TextView title;
        TextView start;
        TextView end;
    }

    public IncidentsCustomAdapter(Context context, int resource, ArrayList<Incidents> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View result;
        ViewHolder holder;
        String title = getItem(position).getTitle();
        String description = getItem(position).getDescription();

        String start = "";
        String end = "";

        Incidents incidents = new Incidents(title, description);

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder= new ViewHolder();
            holder.title = convertView.findViewById(R.id.txtLabel);
            holder.start = convertView.findViewById(R.id.txtStart);
            holder.end = convertView.findViewById(R.id.txtEnd);

            result = convertView;

            convertView.setTag(holder);
        } else{
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        if (description.contains("Start")) {
            int dashIndex = description.indexOf("-");
            start = description.substring(0, dashIndex - 1);
            int forward = description.indexOf(">");
            int secondDashIndex = description.indexOf("-", forward);
            end = description.substring(forward + 1, secondDashIndex - 1);
        }

        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;

        holder.title.setText(incidents.getTitle());
        holder.start.setText(start);
        holder.end.setText(end);

        return convertView;
    }
}
