package ca.ubc.cs.cpsc210.mindthegap.ui;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import ca.ubc.cs.cpsc210.mindthegap.R;
import ca.ubc.cs.cpsc210.mindthegap.model.ArrivalBoard;
import ca.ubc.cs.cpsc210.mindthegap.model.Station;
import ca.ubc.cs.cpsc210.mindthegap.model.StationManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment to display list of arrival boards
 */
public class ArrivalBoardListFragment extends ListFragment {
    private ArrivalGroupListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<ArrivalBoard> arrivalBoards = getArrivalBoardsForSelectedStation();
        adapter = new ArrivalGroupListAdapter(arrivalBoards);

        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.arrivalboard_fragment_list_layout, null);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ArrivalBoard ag = adapter.getItem(position);

        ((ArrivalBoardListSelectionListener) getActivity()).onArrivalBoardListItemSelected(ag);
    }

    /**
     * Get list of arrival boards at selected station
     *
     * @return  list of arrival boards at selected station
     */
    public ArrayList<ArrivalBoard> getArrivalBoardsForSelectedStation() {
        Station s= StationManager.getInstance().getSelected();
        ArrayList<ArrivalBoard> abs = new ArrayList<ArrivalBoard>();
        for(ArrivalBoard ab: s){
            abs.add(ab);
        }
        //TODO: Phase 2 Task 8
        return abs;
    }

    /**
     * Array adapter for list of arrival boards displayed to user
     */
    private class ArrivalGroupListAdapter extends ArrayAdapter<ArrivalBoard> {
        public ArrivalGroupListAdapter(ArrayList<ArrivalBoard> arrivalGroupList) {
            super(getActivity(), 0, arrivalGroupList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.arrival_group_item, null);
            }

            ArrivalBoard ab = getItem(position);
            TextView line = (TextView) convertView.findViewById(R.id.line);
            line.setText(ab.getLine().getName());
            TextView direction = (TextView) convertView.findViewById(R.id.direction);
            direction.setText(ab.getTravelDirn());

            TextView overbar = (TextView) convertView.findViewById(R.id.overbar);
            overbar.setBackgroundColor(ab.getLine().getColour());

            return convertView;
        }
    }
}
