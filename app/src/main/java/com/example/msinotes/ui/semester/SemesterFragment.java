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
import com.example.msinotes.ui.SubjectInfoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SemesterFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_semester, container, false);

        BottomNavigationView navView = getActivity().findViewById(R.id.nav_view);
        navView.setBackgroundResource(R.drawable.custom_listview_item);

        String value = getArguments().getString("Key");


        final ArrayList<SubjectsClass> mSubject = new ArrayList<SubjectsClass>();

        UtilityClass.getSubjectsList(mSubject, value, (AppCompatActivity)getActivity());


        OptionsAdaptor adapter = new OptionsAdaptor(getContext(), mSubject);

        final ListView listView = (ListView)view.findViewById(R.id.sub_Name);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SubjectInfoFragment frag = new SubjectInfoFragment();
                Bundle args = new Bundle();
                args.putString("SubjectCode", ((SubjectsClass) mSubject.get(position)).mSubjectCode);
                frag.setArguments(args);
                FragmentTransaction fragTrans = getParentFragmentManager().beginTransaction();
                fragTrans.replace(R.id.frame_container, frag).addToBackStack(null);
                fragTrans.commit();
            }
        });
        return view;
    }
}