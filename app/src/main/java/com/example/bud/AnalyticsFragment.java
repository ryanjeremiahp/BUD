package com.example.bud;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class AnalyticsFragment extends Fragment {
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
