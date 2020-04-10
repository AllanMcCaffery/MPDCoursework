package org.me.gcu.mpd.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.me.gcu.mpd.IncidentsCustomAdapter;
import org.me.gcu.mpd.MapsActivity;
import org.me.gcu.mpd.R;
import org.me.gcu.mpd.model.Incidents;
import org.me.gcu.mpd.parseXML;

public class PlannedFragment extends Fragment {

    private View view;
    private ListView listView;
    private TextView textView;
    private Context mContext;
    private parseXML parseXMLPlanned;
    private IncidentsCustomAdapter incidentsCustomAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_planned, container, false);
        textView = view.findViewById(R.id.searchText);
        listView = view.findViewById(R.id.planned_listView);
        Bundle arguments = getArguments();
        String xmlData = arguments.getString("Data");

        parseXMLPlanned = new parseXML();
        parseXMLPlanned.parseData(xmlData);


        incidentsCustomAdapter = new IncidentsCustomAdapter(mContext, R.layout.listview_row, parseXMLPlanned.getIncidents());
        listView.setAdapter(incidentsCustomAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Incidents in = (Incidents) listView.getItemAtPosition(position);
                Double Lat = Double.parseDouble(in.getLatitude());
                Double Long = Double.parseDouble(in.getLongitude());
                System.out.println("LAT - " + Lat );
                System.out.println("LONG - " + Long );
                Intent i = new Intent(getContext(), MapsActivity.class);
                i.putExtra("Latitude", Lat);
                i.putExtra("Longitude", Long);
                startActivity(i);
            }
        });

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        IncidentsCustomAdapter incidentsCustomAdapter = new IncidentsCustomAdapter(mContext, R.layout.listview_row, parseXMLPlanned.getIncidents());
        listView.setAdapter(incidentsCustomAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

}
