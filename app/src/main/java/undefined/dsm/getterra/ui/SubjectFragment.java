package undefined.dsm.getterra.ui;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import undefined.dsm.getterra.R;

public class SubjectFragment extends Fragment {
    public SubjectFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_subject, container, false);
    }
}
