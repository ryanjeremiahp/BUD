package com.example.bud;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class AnalyticsFragment extends Fragment {
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Comment
         View v = inflater.inflate(R.layout.activity_analysis, container, false);
         Button toSubAnalytics = v.findViewById(R.id.moreAnalytics);
         toSubAnalytics.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Fragment fragment = new SubAnalyticsFragment();
                 FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                 FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                 fragmentTransaction.replace(R.id.fragment_container, fragment);
                 fragmentTransaction.addToBackStack(null);
                 fragmentTransaction.commit();
             }
         });
        return v;
    }

}
