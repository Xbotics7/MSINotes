package com.example.msinotes.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.msinotes.R;

public class SemesterFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_semester, container, false);

        Button btnFrag = (Button)view.findViewById(R.id.btnSubject);

        final FrameLayout frmMain = (FrameLayout)view.findViewById(R.id.frame_semester);
        btnFrag.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                frmMain.setVisibility(View.VISIBLE);
                FragmentTransaction frag = getFragmentManager().beginTransaction();
                frag.replace(R.id.frame_semester, new SubjectInfoFragment());
                frag.commit();
            }
        });
        return view;
    }
}