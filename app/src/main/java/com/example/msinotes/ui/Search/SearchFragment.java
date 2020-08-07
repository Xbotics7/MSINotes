package com.example.msinotes.ui.Search;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.msinotes.Models.UtilityClass;
import com.example.msinotes.R;
import com.example.msinotes.SubjectsClass;
import com.example.msinotes.ui.SubjectInfoFragment;
import com.example.msinotes.ui.semester.OptionsAdaptor;

import java.util.ArrayList;

public class SearchFragment extends Fragment
{

    ListView listView;
    EditText editTextSearch;
    ArrayList<SubjectsClass> mSubject = new ArrayList<SubjectsClass>();
    ArrayList<SubjectsClass> sortedList;
    LinearLayout mLinearSearchInfo;
    LinearLayout mLinearSearchCont;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("SEARCH");

        listView = (ListView) view.findViewById(R.id.lv_search);
        editTextSearch = (EditText) view.findViewById(R.id.edit_text_search);
        mLinearSearchInfo = (LinearLayout) view.findViewById(R.id.linear_search_info);
        mLinearSearchCont = (LinearLayout) view.findViewById(R.id.linear_search_cont);

        mLinearSearchInfo.setVisibility(View.VISIBLE);
        mLinearSearchCont.setVisibility(View.GONE);

        UtilityClass.getSubjectsList(mSubject);


        editTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                if (actionId == EditorInfo.IME_ACTION_SEARCH)
                {
                    performSearch();
                    return true;
                }
                return false;
            }
        });

        editTextSearch.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            performSearch();
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                SubjectInfoFragment frag = new SubjectInfoFragment();
                Bundle args = new Bundle();
                args.putString("SubjectCode", ((SubjectsClass) sortedList.get(position)).mSubjectCode);
                frag.setArguments(args);
                FragmentTransaction fragTrans = getParentFragmentManager().beginTransaction();
                fragTrans.setCustomAnimations(R.anim.slide_right, R.anim.nav_default_pop_exit_anim, R.anim.slide_left, R.anim.nav_default_pop_exit_anim);
                fragTrans.replace(R.id.frame_container, frag).addToBackStack(null);
                fragTrans.commit();
            }
        });
        return view;
    }

    private void performSearch()
    {
        mLinearSearchInfo.setVisibility(View.GONE);

        sortedList = new ArrayList<SubjectsClass>();
        for (SubjectsClass _objClass : mSubject)
        {
            if (_objClass.mSubjectName.toLowerCase().contains(editTextSearch.getText().toString().toLowerCase()))
            {
                sortedList.add(_objClass);
            }
        }

        OptionsAdaptor sorted_adapter = new OptionsAdaptor(getContext(), sortedList);
        listView.setAdapter(sorted_adapter);

        mLinearSearchCont.setVisibility(View.VISIBLE);
    }
}