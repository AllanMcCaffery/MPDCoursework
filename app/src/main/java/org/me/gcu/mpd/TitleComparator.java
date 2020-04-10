package org.me.gcu.mpd;

import org.me.gcu.mpd.model.Incidents;

import java.util.Comparator;

public class TitleComparator implements Comparator<Incidents> {
    @Override
    public int compare(Incidents o1, Incidents o2) {
        return o1.getTitle().compareTo(o2.getTitle());
    }
}
