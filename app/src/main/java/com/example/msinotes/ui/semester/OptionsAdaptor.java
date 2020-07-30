package com.example.msinotes.ui.semester;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.msinotes.R;
import com.example.msinotes.SubjectsClass;

import java.util.ArrayList;

public class OptionsAdaptor extends ArrayAdapter<SubjectsClass>
{

    public OptionsAdaptor(Context context, ArrayList<SubjectsClass> subjects) {
        super(context, R.layout.options_row, subjects);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }


    private View initView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        SubjectsClass currentItem = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.options_row, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.sub_Name);

        if (currentItem != null) {

            textView.setText(currentItem.mSubjectName);
        }

        return convertView;
    }
}
