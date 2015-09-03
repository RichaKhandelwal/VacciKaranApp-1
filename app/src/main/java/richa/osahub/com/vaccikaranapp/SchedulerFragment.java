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
        VaccinationsPojo vaccination = new VaccinationsPojo("Hepatitis B", "at birth","Protects your child against hepatitis B, a potentially serious disease");
        VaccinationsPojo vaccination1= new VaccinationsPojo("Polio","at birth","The polio vaccine protects against poliomyelitis (polio), a highly infectious disease caused by a virus that invades the nervous system.");
        VaccinationsPojo vaccination2= new VaccinationsPojo("Tuberculosis","birth to 6 weeks","The tuberculosis vaccine is recommended only for those children living with someone with TB who either cannot take the antibiotics required to treat the infection or who is infected with a strain that is highly resistant to all antibiotics.");
        List<VaccinationsPojo> vaccinations = new ArrayList<>();
        vaccinations.add(vaccination);
        vaccinations.add(vaccination1);
        vaccinations.add(vaccination2);

        vaccinationArrayAdapter = new VaccinationListAdapter(getActivity(), vaccinations);
        listView.setAdapter(vaccinationArrayAdapter);
        return rootView;
    }


}
