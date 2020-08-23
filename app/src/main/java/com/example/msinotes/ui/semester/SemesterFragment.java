package com.example.msinotes.ui.semester;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.msinotes.MainActivity;
import com.example.msinotes.Models.UtilityClass;
import com.example.msinotes.R;
import com.example.msinotes.SubjectsClass;
import com.example.msinotes.ui.SubjectInfo.SubjectInfoFragment;
import com.example.msinotes.ui.WebFrag.WebrowserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SemesterFragment extends Fragment
{
    String strBCACode = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_semester, container, false);

        // Sets Background, Visibility to Visible of Nav view
        BottomNavigationView navView = getActivity().findViewById(R.id.nav_view);
        navView.setBackgroundResource(R.drawable.custom_listview_item);
        navView.setVisibility(View.VISIBLE);

        //Sets Home Button as selected in nav view
        navView.getMenu().findItem(R.id.navigation_home).setChecked(true);

        //Get the passed values sent from previous fragments
        strBCACode = getArguments().getString("Key");


        final ArrayList<SubjectsClass> mSubject = new ArrayList<SubjectsClass>();

        //Method to get subjects items to "mSubject" Array List
        UtilityClass.getSubjectsList(mSubject, strBCACode, ((AppCompatActivity) getActivity()).getSupportActionBar());


        OptionsAdaptor adapter = new OptionsAdaptor(getContext(), mSubject);

        Button btnSyllbus = (Button) view.findViewById(R.id.btnSyllabus);
        final ListView listView = (ListView) view.findViewById(R.id.sub_Name);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                SubjectInfoFragment frag = new SubjectInfoFragment();

                // Custom Values to pass as argument when switching to another fragment
                Bundle args = new Bundle();
                args.putString("SubjectCode", ((SubjectsClass) mSubject.get(position)).mSubjectCode);
                frag.setArguments(args);

                FragmentTransaction fragTrans = getParentFragmentManager().beginTransaction();

                //Sets Custom Animations
                fragTrans.setCustomAnimations(R.anim.slide_right, R.anim.nav_default_pop_exit_anim, R.anim.slide_left, R.anim.nav_default_pop_exit_anim);
                fragTrans.replace(R.id.frame_container, frag).addToBackStack(null);
                fragTrans.commit();
            }
        });

        btnSyllbus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                WebrowserFragment frag = new WebrowserFragment();

                // Custom Values to pass as argument when switching to another fragment
                Bundle args = new Bundle();
                args.putString("URL", UtilityClass.getSyllabusUrl(strBCACode));
                frag.setArguments(args);

                FragmentTransaction fragTrans = getParentFragmentManager().beginTransaction();

                //Sets Custom Animations
                fragTrans.setCustomAnimations(R.anim.slide_right, R.anim.nav_default_pop_exit_anim, R.anim.slide_left, R.anim.nav_default_pop_exit_anim);
                fragTrans.replace(R.id.frame_container, frag).addToBackStack(null);
                fragTrans.commit();
            }
        });
        return view;
    }
}