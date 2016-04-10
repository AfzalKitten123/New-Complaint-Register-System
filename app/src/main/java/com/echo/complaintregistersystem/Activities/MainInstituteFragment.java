package com.echo.complaintregistersystem.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.echo.complaintregistersystem.Fragments.ResolvedInstituteFragment;
import com.echo.complaintregistersystem.Fragments.UnresolvedInstituteFragment;
import com.echo.complaintregistersystem.R;

public class MainInstituteFragment extends Fragment {

    private FragmentTabHost myTabHost;

    public MainInstituteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_institute, container, false);
        myTabHost = (FragmentTabHost) rootView.findViewById(android.R.id.tabhost);

        myTabHost.setup(getActivity(), getChildFragmentManager(), android.R.id.tabcontent);
        myTabHost.addTab(myTabHost.newTabSpec("tab1").setIndicator("UnResolved"), UnresolvedInstituteFragment.class, null);
        myTabHost.addTab(myTabHost.newTabSpec("tab2").setIndicator("Resolved"), ResolvedInstituteFragment.class, null);

        return rootView;
    }
}