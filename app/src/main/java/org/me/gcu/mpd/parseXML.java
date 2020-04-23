package org.me.gcu.mpd;

import org.me.gcu.mpd.model.Incidents;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;

public class parseXML {

    private ArrayList<Incidents> incidentArrayList;

    public parseXML() {
        this.incidentArrayList = new ArrayList<>();
    }

    public ArrayList<Incidents> getIncidents() {
        //Collections.sort(incidentArrayList, new TitleComparator());
        return incidentArrayList;
    }

    public void parseData(String dataToParse) {

        Incidents incident = null;
        boolean inItem = false;
        String tagValue = "";

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(dataToParse));
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = xpp.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("item".equalsIgnoreCase(tagName)) {
                            inItem = true;
                            incident = new Incidents();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        tagValue = xpp.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (inItem) {
                            if ("item".equalsIgnoreCase(tagName)) {
                                incidentArrayList.add(incident);
                                inItem = false;
                            } else if ("title".equalsIgnoreCase(tagName)) {
                                incident.setTitle(tagValue);
                            } else if ("description".equalsIgnoreCase(tagName)) {
                                incident.setDescription(tagValue);
                            } else if ("link".equalsIgnoreCase(tagName)) {
                                incident.setLink(tagValue);
                            } else if ("point".equalsIgnoreCase(tagName)) {

                                String Lat = getLatitude(tagValue);
                                String Long = getLongitude(tagValue);

                                incident.setLatitude(Lat);
                                incident.setLongitude(Long);

                            } else if ("author".equalsIgnoreCase(tagName)) {
                                incident.setAuthor(tagValue);
                            } else if ("comments".equalsIgnoreCase(tagName)) {
                                incident.setComments(tagValue);
                            } else if ("pubDate".equalsIgnoreCase(tagName)) {
                                incident.setPubDate(tagValue);
                            }
                        }
                        break;

                    default:
                }
                eventType = xpp.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getLongitude (String temp){
        if (temp.length() > 0) {
            int space = temp.indexOf(" ");
            return temp.substring(space + 1);
        }
        return "";
    }

    private String getLatitude (String temp){
        if (temp.length() > 0) {
            int space = temp.indexOf(" ");
            return temp.substring(0, space);
        }
        return "";
    }



}