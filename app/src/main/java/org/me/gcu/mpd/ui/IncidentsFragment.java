package org.me.gcu.mpd.ui;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.me.gcu.mpd.IncidentsCustomAdapter;
import org.me.gcu.mpd.R;
import org.me.gcu.mpd.parseXML;

public class IncidentsFragment extends Fragment {

    private View view;
    private ListView listView;
    private Context mContext;
    private parseXML parseXMLIncidents;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_incidents, container,false);

        listView = view.findViewById(R.id.incidents_listView);

        Bundle arguments = getArguments();
        String xmlData = arguments.getString("Data");

        parseXMLIncidents = new parseXML();
        parseXMLIncidents.parseData(xmlData);

        IncidentsCustomAdapter incidentsCustomAdapter = new IncidentsCustomAdapter(mContext, R.layout.listview_row, parseXMLIncidents.getIncidents());
        listView.setAdapter(incidentsCustomAdapter);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        IncidentsCustomAdapter incidentsCustomAdapter = new IncidentsCustomAdapter(mContext, R.layout.listview_row, parseXMLIncidents.getIncidents());
        listView.setAdapter(incidentsCustomAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
}
