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

import org.me.gcu.mpd.R;
import org.me.gcu.mpd.parseXML;

public class PlannedFragment extends Fragment {

    private View view;
    private ListView listView;
    private Context mContext;
    private parseXML parseXMLPlanned;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_planned, container, false);

        listView = view.findViewById(R.id.planned_listView);

        Bundle arguments = getArguments();
        String xmlData = arguments.getString("Data");

        parseXMLPlanned = new parseXML();
        parseXMLPlanned.parseData(xmlData);


        ArrayAdapter arrayAdapter = new ArrayAdapter<>(mContext, R.layout.listview_row, parseXMLPlanned.getIncidents());
        listView.setAdapter(arrayAdapter);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(mContext, R.layout.listview_row, parseXMLPlanned.getIncidents());
        listView.setAdapter(arrayAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

}
