package com.example.msinotes.ui.home;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.msinotes.R;
import com.example.msinotes.ui.SemesterFragment;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        Button btnFrag = (Button)root.findViewById(R.id.btnSem1);

        final FrameLayout frmMain = (FrameLayout)root.findViewById(R.id.frame_home);
        btnFrag.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                frmMain.setVisibility(View.VISIBLE);
                FragmentTransaction frag = getFragmentManager().beginTransaction();
                frag.replace(R.id.frame_home, new SemesterFragment());
                frag.commit();
            }
        });
        return root;
    }



}