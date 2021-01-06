package com.example.msinotes.ui.SubjectInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.msinotes.Models.UtilityClass;
import com.example.msinotes.NotesClass;
import com.example.msinotes.R;
import com.example.msinotes.YoutubeClass;

import java.util.ArrayList;

public class YTOptionsAdaptor extends ArrayAdapter<YoutubeClass>
{
    ArrayList<YoutubeClass> mYTList;
    ArrayList<YoutubeClass> savedYTList;

    public YTOptionsAdaptor(Context context, ArrayList<YoutubeClass> ytList)
    {
        super(context, R.layout.options_row, ytList);
        this.mYTList = ytList;
        savedYTList = UtilityClass.getYTBookMarks(getContext());
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
        final YoutubeClass currentItem = getItem(position);

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
            textView.setText(currentItem.yt_name);

            if (savedYTList.contains(currentItem))
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
                UtilityClass.saveToYTBookmark(currentItem, btnBookMark, btnUnBookMark, getContext());
            }
        });

        btnUnBookMark.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                UtilityClass.removeFromYTBookMark(currentItem, btnBookMark, btnUnBookMark, getContext());
                if (UtilityClass.isBookmark)
                {
                    mYTList.remove(currentItem);
                    notifyDataSetChanged();
                }
            }
        });

        return convertView;
    }

}
