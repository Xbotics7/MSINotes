package com.example.msinotes.ui.semester;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.msinotes.Models.UtilityClass;
import com.example.msinotes.R;
import com.example.msinotes.SubjectsClass;

import java.util.ArrayList;

public class OptionsAdaptor extends ArrayAdapter<SubjectsClass>
{

    public OptionsAdaptor(Context context, ArrayList<SubjectsClass> subjects)
    {
        super(context, R.layout.options_row, subjects);
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
        return view;
        //return initView(position, convertView, parent);
    }


    private View initView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        SubjectsClass currentItem = getItem(position);

        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.options_row, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.sub_Name);

        if (currentItem != null)
        {

            textView.setText(currentItem.mSubjectName);
        }

        return convertView;
    }


}
