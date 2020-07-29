package com.example.msinotes.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.msinotes.MainActivity;
import com.example.msinotes.R;
import com.example.msinotes.ui.semester.SemesterFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button btnSem1 = (Button)view.findViewById(R.id.btnSem1);
        Button btnSem2 = (Button)view.findViewById(R.id.btnSem2);
        Button btnSem3 = (Button)view.findViewById(R.id.btnSem3);
        Button btnSem4 = (Button)view.findViewById(R.id.btnSem4);
        Button btnSem5 = (Button)view.findViewById(R.id.btnSem5);
        Button btnSem6 = (Button)view.findViewById(R.id.btnSem6);

        BottomNavigationView navView = getActivity().findViewById(R.id.nav_view);
        navView.setBackgroundResource(R.drawable.rounded_button);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("HOME");

        btnSem1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                semButtonClick("sem 1");
            }
        });
        btnSem2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                semButtonClick("sem 2");
            }
        });
        btnSem3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                semButtonClick("sem 3");
            }
        });
        btnSem4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                semButtonClick("sem 4");
            }
        });
        btnSem5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                semButtonClick("sem 5");
            }
        });
        btnSem6.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                semButtonClick("sem 6");
            }
        });
        return view;
    }

    private  void semButtonClick(String argument){
        FragmentTransaction fragTrans = getParentFragmentManager().beginTransaction();
        SemesterFragment frag = new SemesterFragment();
        Bundle args = new Bundle();
        args.putString("Key", argument);
        frag.setArguments(args);
        fragTrans.replace(R.id.frame_container, frag).addToBackStack(null);
        fragTrans.commit();
    }



}