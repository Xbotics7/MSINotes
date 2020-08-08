package com.example.msinotes.ui;

import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.msinotes.Models.UtilityClass;
import com.example.msinotes.R;
import com.example.msinotes.SubjectsClass;
import com.example.msinotes.ui.WebFrag.WebrowserFragment;
import com.example.msinotes.ui.semester.SemesterFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SubjectInfoFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_subject_info, container, false);

        BottomNavigationView navView = getActivity().findViewById(R.id.nav_view);
        navView.setBackgroundResource(R.drawable.rounded_info_button);
        navView.setVisibility(View.VISIBLE);
        navView.getMenu().findItem(R.id.navigation_home).setChecked(true);

        String value = getArguments().getString("SubjectCode");
        final SubjectsClass subInfo = UtilityClass.getSubInfo(value);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(subInfo.mSubjectName);

        Button btnNotes = view.findViewById(R.id.btnNotes);
        Button btnBook = view.findViewById(R.id.btnBook);
        Button btnAkash = view.findViewById(R.id.btnAkash);
        Button btnPprAnalysis = view.findViewById(R.id.btnPprAnalysis);
        Button btnVids = view.findViewById(R.id.btnVideos);


        btnNotes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (subInfo.mNotes_url.equals(""))
                    UtilityClass.showToast("Not Available right now", getContext());
                else
                    subButtonClick(subInfo.mNotes_url);
            }
        });
        btnBook.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (subInfo.mBook_url.equals(""))
                    UtilityClass.showToast("Not Available right now", getContext());
                else
                    subButtonClick(subInfo.mBook_url);
            }
        });
        btnPprAnalysis.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (subInfo.mPaper_analysis_url.equals(""))
                    UtilityClass.showToast("Not Available right now", getContext());
                else
                    subButtonClick(subInfo.mPaper_analysis_url);
            }
        });
        btnAkash.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (subInfo.mAkash_url.equals(""))
                    UtilityClass.showToast("Not Available right now", getContext());
                else
                    subButtonClick(subInfo.mAkash_url);
            }
        });
        btnVids.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                UtilityClass.showToast("Coming soon...", getContext());
            }
        });

        return view;
    }

    private void subButtonClick(String url)
    {
        WebrowserFragment frag = new WebrowserFragment();
        Bundle args = new Bundle();
        args.putString("URL", url);
        frag.setArguments(args);
        FragmentTransaction fragTrans = getParentFragmentManager().beginTransaction();
        fragTrans.setCustomAnimations(R.anim.slide_right, R.anim.nav_default_pop_exit_anim, R.anim.slide_left, R.anim.nav_default_pop_exit_anim);
        fragTrans.replace(R.id.frame_container, frag).addToBackStack(null);
        fragTrans.commit();
    }

}