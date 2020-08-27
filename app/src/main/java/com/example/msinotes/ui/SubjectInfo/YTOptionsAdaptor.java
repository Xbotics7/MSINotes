package com.example.msinotes.ui.SubjectInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.msinotes.R;
import com.example.msinotes.YoutubeClass;

import java.util.ArrayList;

public class YTOptionsAdaptor extends ArrayAdapter<YoutubeClass>
{
    public YTOptionsAdaptor(Context context, ArrayList<YoutubeClass> ytList)
    {
        super(context, R.layout.options_row, ytList);
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


    private View initView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        YoutubeClass currentItem = getItem(position);

        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.options_row, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.sub_Name);

        if (currentItem != null)
        {
            textView.setBackgroundResource(R.drawable.custom_listview_item);
            textView.setText(currentItem.yt_name);
        }

        return convertView;
    }

}
