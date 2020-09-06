package com.example.msinotes.ui.SubjectInfo;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.PreferenceManager;

import com.example.msinotes.Models.UtilityClass;
import com.example.msinotes.NotesClass;
import com.example.msinotes.R;
import com.example.msinotes.SubjectsClass;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class NotesOptionsAdaptor extends ArrayAdapter<NotesClass>
{
    ArrayList<NotesClass> notesList;
    public NotesOptionsAdaptor(Context context, ArrayList<NotesClass> notesList)
    {
        super(context, R.layout.options_row, notesList);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View view = convertView;
        if (view == null)
        {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.options_row, null);
        }
//        Button callbtn= (Button)view.findViewById(R.id.btTest);
//        final TextView tbShyam = (TextView) view.findViewById(R.id.tbShyam);
//        callbtn.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                tbShyam.setText("asdjkasd");
//                UtilityClass.showToast("test", getContext());
//            }
//        });
        return initView(position, convertView, parent);
    }

    private void savedata(NotesClass subNotes, ImageButton btnBookMark, ImageButton btnUnBookMark){
            Gson gson = new Gson();
            SharedPreferences sharedPrefBookMark = PreferenceManager.getDefaultSharedPreferences(getContext());
            SharedPreferences.Editor editor = sharedPrefBookMark.edit();
            ArrayList<NotesClass> notesList = new ArrayList<>();
              String json = sharedPrefBookMark.getString("BookMark List","");
            Type type =  new TypeToken<ArrayList<NotesClass>>() {}.getType();
            notesList = gson.fromJson(json,type);

            if(notesList == null){
            notesList = new ArrayList<>();
            }

            notesList.add(subNotes);

            String json2 = gson.toJson(notesList);
            editor.putString("BookMark List", json2);
            editor.apply();

            btnBookMark.setVisibility(View.GONE);
            btnUnBookMark.setVisibility(View.VISIBLE);

    }


 private void UnBookMark(NotesClass subNotes,ImageButton btnBookMark, ImageButton btnUnBookMark){
     SharedPreferences sharedPrefBookMark = PreferenceManager.getDefaultSharedPreferences(getContext());
     SharedPreferences.Editor editor = sharedPrefBookMark.edit();
    editor.remove();
    editor.commit();

     btnBookMark.setVisibility(View.VISIBLE);
        btnUnBookMark.setVisibility(View.GONE);


 }

    private View initView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {

        loaddata();

        final NotesClass currentItem = getItem(position);

        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.options_row, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.sub_Name);

        final ImageButton btnBookMark = (ImageButton) convertView.findViewById(R.id.btnBookMark);
        btnBookMark.setVisibility(View.VISIBLE);
        final ImageButton btnUnBookMark = (ImageButton) convertView.findViewById(R.id.btnUnBookMark);
        final ImageButton btnMoreInfo = (ImageButton) convertView.findViewById(R.id.btnMoreInfo);
        btnMoreInfo.setVisibility(View.GONE);

        if (currentItem != null)
        {
            textView.setBackgroundResource(R.drawable.custom_listview_item);
            textView.setText(currentItem.mName);
            if(notesList.contains(currentItem)){
                btnUnBookMark.setVisibility(View.VISIBLE);
                btnBookMark.setVisibility(View.GONE);
            }
            else{
                btnBookMark.setVisibility(View.VISIBLE);
                btnUnBookMark.setVisibility(View.GONE);
            }
        }



        btnBookMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedata(currentItem, btnBookMark,btnUnBookMark);

            }
        });

        btnUnBookMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UnBookMark(currentItem, btnBookMark, btnUnBookMark);
            }
        });


        return convertView;
    }

    private void loaddata( ){

        if(notesList == null){
            notesList = new ArrayList<>();
        }

        Gson gson = new Gson();
        SharedPreferences sharedPrefBookMark = PreferenceManager.getDefaultSharedPreferences(getContext());
        String json = sharedPrefBookMark.getString("BookMark List",null);
        Type type =  new TypeToken<ArrayList<NotesClass>>() {}.getType();
        notesList = gson.fromJson(json,type);


    }

}
