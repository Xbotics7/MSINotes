package com.example.msinotes.ui.bookmark;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.PreferenceManager;

import com.example.msinotes.NotesClass;
import com.example.msinotes.R;
import com.example.msinotes.ui.SubjectInfo.NotesOptionsAdaptor;
import com.example.msinotes.ui.WebFrag.WebrowserFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class BookmarkFragment extends Fragment {
    ArrayList<NotesClass> notesList;
    ListView listView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_bookmark, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("BOOKMARK");

       listView = (ListView) root.findViewById(R.id.notesBookMarkList);
        loaddata();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                subButtonClick(((NotesClass) notesList.get(position)).mNotes_url);

            }
        });
        return root;
    }
    public void loaddata(){

        if(notesList == null){
            notesList = new ArrayList<>();
        }

        Gson gson = new Gson();
        SharedPreferences sharedPrefBookMark = PreferenceManager.getDefaultSharedPreferences(getContext());
        String json = sharedPrefBookMark.getString("BookMark List",null);
        Type type =  new TypeToken<ArrayList<NotesClass>>() {}.getType();
        notesList = gson.fromJson(json,type);

        NotesOptionsAdaptor adapter = new NotesOptionsAdaptor(getContext(), notesList);


        listView.setAdapter(adapter);

    }

    private void subButtonClick(String url)
    {
        WebrowserFragment frag = new WebrowserFragment();

        // Custom Values to pass as argument when switching to another fragment
        Bundle args = new Bundle();
        args.putString("URL", url);
        frag.setArguments(args);

        FragmentTransaction fragTrans = getParentFragmentManager().beginTransaction();

        //Sets Custom animation
        fragTrans.setCustomAnimations(R.anim.slide_right, R.anim.nav_default_pop_exit_anim, R.anim.slide_left, R.anim.nav_default_pop_exit_anim);

        fragTrans.replace(R.id.frame_container, frag).addToBackStack(null);
        fragTrans.commit();
    }
}