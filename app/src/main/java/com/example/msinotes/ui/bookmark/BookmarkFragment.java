package com.example.msinotes.ui.bookmark;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.PreferenceManager;

import com.example.msinotes.NotesClass;
import com.example.msinotes.R;
import com.example.msinotes.ui.SubjectInfo.NotesOptionsAdaptor;
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

}