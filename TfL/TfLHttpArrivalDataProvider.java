package ca.ubc.cs.cpsc210.mindthegap.TfL;

import ca.ubc.cs.cpsc210.mindthegap.model.ArrivalBoard;
import ca.ubc.cs.cpsc210.mindthegap.model.Line;
import ca.ubc.cs.cpsc210.mindthegap.model.Station;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Wrapper for TfL Arrival Data Provider
 */
public class TfLHttpArrivalDataProvider extends AbstractHttpDataProvider {
    private Station stn;

    public TfLHttpArrivalDataProvider(Station stn) {
        super();
        this.stn = stn;
    }

    @Override
    /**
     * Produces URL used to query TfL web service for expected arrivals at
     * station specified in call to constructor.
     *
     * @returns URL to query TfL web service for arrival data
     */
    protected URL getURL() throws MalformedURLException {
        String stnid= stn.getID();
        Set<Line> ls= stn.getLines();
        String request ="https://api.tfl.gov.uk/Line/";
        for(Line l : ls) {
            request += l.getId()+",";
        }
        int l = request.length();
        request= request.substring(0,l-1);
        request += "/Arrivals?stopPointId="+stnid+"&app_id=&app_key=";
        // TODO Phase 2 Task 7
        return new URL(request);
    }

}
