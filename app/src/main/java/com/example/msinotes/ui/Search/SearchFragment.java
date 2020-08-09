package com.example.msinotes.ui.Search;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
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
import com.example.msinotes.ui.SubjectInfo.SubjectInfoFragment;
import com.example.msinotes.ui.semester.OptionsAdaptor;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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

        //Sets Home Button as selected in nav view
        BottomNavigationView navView = getActivity().findViewById(R.id.nav_view);
        navView.getMenu().findItem(R.id.navigation_search).setChecked(true);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("SEARCH");

        listView = (ListView) view.findViewById(R.id.lv_search);
        editTextSearch = (EditText) view.findViewById(R.id.edit_text_search);
        mLinearSearchInfo = (LinearLayout) view.findViewById(R.id.linear_search_info);
        mLinearSearchCont = (LinearLayout) view.findViewById(R.id.linear_search_cont);

        mLinearSearchInfo.setVisibility(View.VISIBLE);
        mLinearSearchCont.setVisibility(View.GONE);

        //Gets all the subjects as ArrayItem into "mSubject" ArrayList;
        UtilityClass.getSubjectsList(mSubject);

        //Listener attached to handle function when search box text is changed
        editTextSearch.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                performSearch(false);
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });


        //When user presses search button
        editTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                if (actionId == EditorInfo.IME_ACTION_SEARCH)
                {
                    performSearch(true);
                    return true;
                }
                return false;
            }
        });

        //When user presses enter in search box
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
                            performSearch(true);
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

        //Listener attached to Listview when item in Listview is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                SubjectInfoFragment frag = new SubjectInfoFragment();

                // Custom Values to pass as argument when switching to another fragment
                Bundle args = new Bundle();
                args.putString("SubjectCode", ((SubjectsClass) sortedList.get(position)).mSubjectCode);
                frag.setArguments(args);

                FragmentTransaction fragTrans = getParentFragmentManager().beginTransaction();

                //Custom Animations
                fragTrans.setCustomAnimations(R.anim.slide_right, R.anim.nav_default_pop_exit_anim, R.anim.slide_left, R.anim.nav_default_pop_exit_anim);

                fragTrans.replace(R.id.frame_container, frag).addToBackStack(null);
                fragTrans.commit();
            }
        });
        return view;
    }

    private void performSearch(boolean hideKeyboard)
    {
        //Code to hide keyboard after user searches.
        if (hideKeyboard)
        {
            editTextSearch.clearFocus();
            InputMethodManager in = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            in.hideSoftInputFromWindow(editTextSearch.getWindowToken(), 0);
        }

        //Hides SearchInfo Linear Layout
        mLinearSearchInfo.setVisibility(View.GONE);

        sortedList = new ArrayList<SubjectsClass>();

        //Loop to get items containing users text in search box
        //in this case we are checking Subject name and Subject Code
        for (SubjectsClass _objClass : mSubject)
        {
            if (_objClass.mSubjectName.toLowerCase().contains(editTextSearch.getText().toString().toLowerCase()) || _objClass.mSubjectCode.toLowerCase().contains(editTextSearch.getText().toString().toLowerCase()))
            {
                sortedList.add(_objClass);
            }
        }

        //Attaching Sorted List to Listview
        OptionsAdaptor sorted_adapter = new OptionsAdaptor(getContext(), sortedList);
        listView.setAdapter(sorted_adapter);

        //Show Search Container Linear Layout which has Listview
        mLinearSearchCont.setVisibility(View.VISIBLE);
    }
}