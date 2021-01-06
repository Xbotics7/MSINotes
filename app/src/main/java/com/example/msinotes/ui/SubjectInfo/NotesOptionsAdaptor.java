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

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class NotesOptionsAdaptor extends ArrayAdapter<NotesClass>
{
    ArrayList<NotesClass> mNotesList;
    ArrayList<NotesClass> savedNotesList;

    public NotesOptionsAdaptor(Context context, ArrayList<NotesClass> notesList)
    {

        super(context, R.layout.options_row, notesList);
        this.mNotesList = notesList;
        savedNotesList = UtilityClass.getNotesBookMarks(getContext());
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

        return initView(position, convertView, parent);
    }

    private View initView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {


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

            if (savedNotesList.contains(currentItem))
            {
                btnUnBookMark.setVisibility(View.VISIBLE);
                btnBookMark.setVisibility(View.GONE);
            } else
            {
                btnBookMark.setVisibility(View.VISIBLE);
                btnUnBookMark.setVisibility(View.GONE);

            }
        }

        btnBookMark.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                UtilityClass.saveToBookmark(currentItem, btnBookMark, btnUnBookMark, getContext());
            }
        });

        btnUnBookMark.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                UtilityClass.removeFromBookMark(currentItem, btnBookMark, btnUnBookMark, getContext());
                if (UtilityClass.isBookmark)
                {
                    mNotesList.remove(currentItem);
                    notifyDataSetChanged();
                }
            }
        });


        return convertView;
    }

}
