package org.me.gcu.mpd;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.me.gcu.mpd.ui.CurrentFragment;
import org.me.gcu.mpd.ui.HomeFragment;
import org.me.gcu.mpd.ui.IncidentsFragment;
import org.me.gcu.mpd.ui.PlannedFragment;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, myAsyncTask.GetXMLData
{
    private HomeFragment homeFragment;
    private IncidentsFragment incidentsFragment;
    private CurrentFragment currentFragment;
    private PlannedFragment plannedFragment;

    private String returnedDataUrl;
    private String returnedDataUrl2;
    private String returnedDataUrl3;


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        final String url1 = "https://trafficscotland.org/rss/feeds/currentincidents.aspx";
        final String url2 = "https://trafficscotland.org/rss/feeds/roadworks.aspx";
        final String url3 = "https://trafficscotland.org/rss/feeds/plannedroadworks.aspx";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myAsyncTask myAsyncTask = new myAsyncTask(this);
        myAsyncTask.execute(url1, url2, url3);

        homeFragment = new HomeFragment();
        incidentsFragment = new IncidentsFragment();
        currentFragment = new CurrentFragment();
        plannedFragment = new PlannedFragment();

        setFragment(homeFragment);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_incidents:
                        Bundle argumentsIncidents = new Bundle();
                        argumentsIncidents.putString( "Data" , returnedDataUrl);
                        incidentsFragment.setArguments(argumentsIncidents);
                        setFragment(incidentsFragment);
                        break;
                    case R.id.nav_current:
                        Bundle argumentsCurrent = new Bundle();
                        argumentsCurrent.putString( "Data" , returnedDataUrl2);
                        currentFragment.setArguments(argumentsCurrent);
                        setFragment(currentFragment);
                        break;
                    case R.id.nav_planned:
                        Bundle argumentsPlanned = new Bundle();
                        argumentsPlanned.putString( "Data" , returnedDataUrl3);
                        plannedFragment.setArguments(argumentsPlanned);
                        setFragment(plannedFragment);
                        break;
                    default:
                        setFragment(homeFragment);
                }
                return true;
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void getXMLData(String[] data) {
        returnedDataUrl = data[0];
        returnedDataUrl2 = data[1];
        returnedDataUrl3 = data[2];
        Log.e("getData","DATA RECEIVED");

        }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}
