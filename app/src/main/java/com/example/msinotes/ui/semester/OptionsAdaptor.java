package com.example.msinotes.ui.semester;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.msinotes.MainActivity;
import com.example.msinotes.Models.FirebaseHelper;
import com.example.msinotes.Models.UtilityClass;
import com.example.msinotes.NotesClass;
import com.example.msinotes.R;
import com.example.msinotes.SubjectsClass;
import com.example.msinotes.YoutubeClass;
import com.example.msinotes.ui.SubjectInfo.NotesOptionsAdaptor;
import com.example.msinotes.ui.SubjectInfo.YTOptionsAdaptor;
import com.example.msinotes.ui.WebFrag.WebrowserFragment;
import com.example.msinotes.ui.ytpage.YoutubeFragment;

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

        return initView(position, convertView, parent);
    }


    private View initView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        SubjectsClass currentItem = getItem(position);

        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.options_row, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.sub_Name);
        final TextView textViewSC = convertView.findViewById(R.id.textview_sc_moreinfo);
        final ImageButton btnMoreInfo = (ImageButton) convertView.findViewById(R.id.btnMoreInfo);
        final LinearLayout lvMoreInfo = (LinearLayout) convertView.findViewById(R.id.linear_layout_lv_moreinfo);
        Button btnNotes = (Button) convertView.findViewById(R.id.btnNotesMoreInfo);
        Button btnBook = convertView.findViewById(R.id.btnBookMoreInfo);
        Button btnAkash = convertView.findViewById(R.id.btnAkashMoreInfo);
        Button btnVids = convertView.findViewById(R.id.btnVideosMoreInfo);

        btnMoreInfo.setVisibility(View.VISIBLE);
        btnMoreInfo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (lvMoreInfo.getVisibility() == View.VISIBLE)
                {
                    btnMoreInfo.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                    lvMoreInfo.setVisibility(View.GONE);
                } else
                {
                    btnMoreInfo.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                    lvMoreInfo.setVisibility(View.VISIBLE);
                }
            }
        });

        final View finalConvertView = convertView;
        btnNotes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SubjectsClass subInfo = FirebaseHelper.getSubInfo(textViewSC.getText().toString());
                if (subInfo.mNotes_url.equals("") && subInfo.mNotesList_url.size() <= 0)
                    UtilityClass.showToast("Not Available right now", getContext());
                else if (subInfo.mNotes_url.equals(""))
                    customNotesPopup(subInfo, finalConvertView);
                else
                    subButtonClick(subInfo.mNotes_url, finalConvertView, subInfo.mSubjectName, "Notes");
            }
        });
        btnBook.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SubjectsClass subInfo = FirebaseHelper.getSubInfo(textViewSC.getText().toString());
                if (subInfo.mBook_url.equals(""))
                    UtilityClass.showToast("Not Available right now", getContext());
                else
                    subButtonClick(subInfo.mBook_url, finalConvertView, subInfo.mSubjectName, "Books");
            }
        });
        btnAkash.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                SubjectsClass subInfo = FirebaseHelper.getSubInfo(textViewSC.getText().toString());
                if (subInfo.mAkash_url.equals(""))
                    UtilityClass.showToast("Not Available right now", getContext());
                else
                    subButtonClick(subInfo.mAkash_url, finalConvertView, subInfo.mSubjectName, "Akash");
            }
        });
        btnVids.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                SubjectsClass subInfo = FirebaseHelper.getSubInfo(textViewSC.getText().toString());
                if (subInfo.mYoutube_url.size() > 0)
                {
                    if (subInfo.mYoutube_url.size() == 1)
                    {
                        YoutubeClass _ytObj = subInfo.mYoutube_url.get(0);
                        ytButtonClick(_ytObj.yt_playlist_url, finalConvertView, _ytObj.yt_name);
                    } else
                    {
                        customYTPopup(subInfo, finalConvertView);
                    }
                } else
                {
                    UtilityClass.showToast("Not Available right now", getContext());
                }
            }
        });

        if (currentItem != null)
        {
            textView.setBackgroundResource(R.drawable.custom_listview_item);
            textView.setText(currentItem.mSubjectName);
            textViewSC.setText(currentItem.mSubjectCode);
        }

        return convertView;
    }

    private void customNotesPopup(final SubjectsClass subInfo, View view)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        View notesPopupView = LayoutInflater.from(getContext()).inflate(R.layout.notes_pop_up, null);

        NotesOptionsAdaptor adapter = new NotesOptionsAdaptor(getContext(), subInfo.mNotesList_url);

        final ListView listView = (ListView) notesPopupView.findViewById(R.id.lv_Notes_Popup);
        listView.setAdapter(adapter);

        dialogBuilder.setView(notesPopupView);
        final AlertDialog dialog = dialogBuilder.create();
        dialog.show();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                NotesClass _notesClass = (NotesClass) subInfo.mNotesList_url.get(position);
                subButtonClick(_notesClass.mNotes_url, view, _notesClass.mName, "Notes");
                dialog.hide();
            }
        });
    }

    private void customYTPopup(final SubjectsClass subInfo, View view)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        View ytPopupView = LayoutInflater.from(getContext()).inflate(R.layout.yt_pop_up, null);

        YTOptionsAdaptor adapter = new YTOptionsAdaptor(getContext(), subInfo.mYoutube_url);

        final ListView listView = (ListView) ytPopupView.findViewById(R.id.lv_YT_Popup);
        listView.setAdapter(adapter);

        dialogBuilder.setView(ytPopupView);
        final AlertDialog dialog = dialogBuilder.create();
        dialog.show();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                YoutubeClass _ytObj = subInfo.mYoutube_url.get(position);
                ytButtonClick(_ytObj.yt_playlist_url, view, _ytObj.yt_name);
                dialog.hide();
            }
        });
    }

    private void subButtonClick(String url, View view, String Title, String Header)
    {
        AppCompatActivity activity = (AppCompatActivity) view.getContext();
        WebrowserFragment frag = new WebrowserFragment();

        // Custom Values to pass as argument when switching to another fragment
        Bundle args = new Bundle();
        args.putString("Header", Header);
        args.putString("Title", Title + " (" + Header + ")");
        args.putString("URL", url);
        frag.setArguments(args);

        FragmentTransaction fragTrans = activity.getSupportFragmentManager().beginTransaction();

        //Sets Custom animation
        fragTrans.setCustomAnimations(R.anim.slide_right, R.anim.nav_default_pop_exit_anim, R.anim.slide_left, R.anim.nav_default_pop_exit_anim);

        fragTrans.replace(R.id.frame_container, frag).addToBackStack(null);
        fragTrans.commit();
    }

    private void ytButtonClick(String url, View view, String Title)
    {
        AppCompatActivity activity = (AppCompatActivity) view.getContext();
        YoutubeFragment frag = new YoutubeFragment();

        // Custom Values to pass as argument when switching to another fragment
        Bundle args = new Bundle();
        args.putString("Header", "Video");
        args.putString("Title", Title + " Video ");
        args.putString("URL", url);
        frag.setArguments(args);

        FragmentTransaction fragTrans = activity.getSupportFragmentManager().beginTransaction();

        //Sets Custom animation
        fragTrans.setCustomAnimations(R.anim.slide_right, R.anim.nav_default_pop_exit_anim, R.anim.slide_left, R.anim.nav_default_pop_exit_anim);

        fragTrans.replace(R.id.frame_container, frag).addToBackStack(null);
        fragTrans.commit();
    }


}
