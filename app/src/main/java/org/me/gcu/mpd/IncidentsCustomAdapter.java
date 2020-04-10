package org.me.gcu.mpd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.me.gcu.mpd.model.Incidents;

import java.util.ArrayList;

public class IncidentsCustomAdapter extends ArrayAdapter<Incidents> {

    private Context mContext;
    private int mResource;

    public IncidentsCustomAdapter(@NonNull Context context, int resource, ArrayList<Incidents> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String title = getItem(position).getTitle();
        String description = getItem(position).getDescription();
        String lat = getItem(position).getLatitude();

        String start = "";
        String end = "";

        Incidents incidents = new Incidents(title, description, lat);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView txtTitle = convertView.findViewById(R.id.txtLabel);
        TextView txtDescription = convertView.findViewById(R.id.txtDescription);
        TextView txtLat = convertView.findViewById(R.id.txtLat);

        if (description != null) {
            int dashIndex = description.indexOf("-");
            start = description.substring(0, dashIndex - 1);
            int forward = description.indexOf(">");
            int secondDashIndex = description.indexOf("-", forward);
            end = description.substring(forward + 1, secondDashIndex - 1);
        }

        txtTitle.setText(title);
        txtDescription.setText(start);
        txtLat.setText(end);

        return convertView;
    }
}
