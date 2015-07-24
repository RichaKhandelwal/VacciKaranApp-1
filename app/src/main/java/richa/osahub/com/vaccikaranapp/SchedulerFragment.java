package richa.osahub.com.vaccikaranapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class SchedulerFragment extends Fragment {
    ArrayAdapter<VaccinationsPojo> vaccinationArrayAdapter;
    ListView listView;

    public SchedulerFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_scheduler, container, false);
        listView = (ListView) rootView.findViewById(R.id.listView);
        VaccinationsPojo vaccination = new VaccinationsPojo("abc", "def","jkh jkh jhdgsdfghs sdfjsij dafh");
        VaccinationsPojo vaccination1= new VaccinationsPojo("abcd","hgdg","hgsdhug adhgdf dgdgjsa bdfhg");
        VaccinationsPojo vaccination2= new VaccinationsPojo("abcd","hgdg","hgsdhug adhgdf dgdgjsa bdfhg");
        List<VaccinationsPojo> vaccinations = new ArrayList<>();
        vaccinations.add(vaccination);
        vaccinations.add(vaccination1);
        vaccinations.add(vaccination2);

        vaccinationArrayAdapter = new VaccinationListAdapter(getActivity(), vaccinations);
        listView.setAdapter(vaccinationArrayAdapter);
        return rootView;
    }


}
