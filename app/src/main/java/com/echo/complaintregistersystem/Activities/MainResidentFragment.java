package com.echo.complaintregistersystem.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.echo.complaintregistersystem.Fragments.ResolvedResidentFragment;
import com.echo.complaintregistersystem.Fragments.UnresolvedResidentFragment;
import com.echo.complaintregistersystem.R;


public class MainResidentFragment extends Fragment {

    private FragmentTabHost myTabHost;

    public MainResidentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_resident, container, false);
        myTabHost = (FragmentTabHost) rootView.findViewById(android.R.id.tabhost);

        myTabHost.setup(getActivity(), getChildFragmentManager(), android.R.id.tabcontent);
        myTabHost.addTab(myTabHost.newTabSpec("tab1").setIndicator("UnResolved"), UnresolvedResidentFragment.class, null);
        myTabHost.addTab(myTabHost.newTabSpec("tab2").setIndicator("Resolved"), ResolvedResidentFragment.class, null);

        return rootView;
    }
}
