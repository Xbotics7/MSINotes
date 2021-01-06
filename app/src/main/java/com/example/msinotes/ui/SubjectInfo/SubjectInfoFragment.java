package com.example.msinotes.ui.SubjectInfo;

import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.msinotes.Models.FirebaseHelper;
import com.example.msinotes.Models.UtilityClass;
import com.example.msinotes.Models.UtilityData;
import com.example.msinotes.NotesClass;
import com.example.msinotes.R;
import com.example.msinotes.SubjectsClass;
import com.example.msinotes.YoutubeClass;
import com.example.msinotes.ui.WebFrag.WebrowserFragment;
import com.example.msinotes.ui.semester.OptionsAdaptor;
import com.example.msinotes.ui.semester.SemesterFragment;
import com.example.msinotes.ui.ytpage.YoutubeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SubjectInfoFragment extends Fragment
{
    String strSubjectCode;
    SubjectsClass subInfo;
    Button btnNotes;
    Button btnBook;
    Button btnAkash;
    Button btnPprAnalysis;
    Button btnVids;
    TextView txvSubjectCode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_subject_info, container, false);

        // Sets Background, Visibility to Visible of Nav view
        BottomNavigationView navView = getActivity().findViewById(R.id.nav_view);
        navView.setBackgroundResource(R.drawable.custom_listview_item);
        navView.setVisibility(View.VISIBLE);

        //Sets Home Button as selected in nav view
        navView.getMenu().findItem(R.id.navigation_home).setChecked(true);

        //Get the passed value sent from the previous fragment
        strSubjectCode = getArguments().getString("SubjectCode");

        //Method to get full Subject Class Info by passing subject code
        subInfo = FirebaseHelper.getSubInfo(strSubjectCode);

        Toolbar toolbar = ((AppCompatActivity) getActivity()).findViewById(R.id.toolbar);
        TextView toolbar_text = toolbar.findViewById(R.id.toolbar_title);
        toolbar_text.setText(subInfo.mSubjectName);

        txvSubjectCode = view.findViewById(R.id.textview_subject_code);
        btnNotes = view.findViewById(R.id.btnNotes);
        btnBook = view.findViewById(R.id.btnBook);
        btnAkash = view.findViewById(R.id.btnAkash);
        btnPprAnalysis = view.findViewById(R.id.btnPprAnalysis);
        btnVids = view.findViewById(R.id.btnVideos);

        txvSubjectCode.setText(strSubjectCode.toUpperCase());
        //Visibility if url exists or not
        visibilityButton();



        btnNotes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (subInfo.mNotes_url.equals("") && subInfo.mNotesList_url.size() <= 0)
                    UtilityClass.showToast("Not Available right now", getContext());
                else if (subInfo.mNotes_url.equals(""))
                    customNotesPopup();
                else
                    subButtonClick(subInfo.mNotes_url, subInfo.mSubjectName, "Notes");
            }
        });
        btnBook.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (subInfo.mBook_url.equals(""))
                    UtilityClass.showToast("Not Available right now", getContext());
                else
                    subButtonClick(subInfo.mBook_url, subInfo.mSubjectName, "Book");
            }
        });
        btnPprAnalysis.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (subInfo.mPaper_analysis_url.equals(""))
                    UtilityClass.showToast("Not Available right now", getContext());
                else
                    subButtonClick(subInfo.mPaper_analysis_url, subInfo.mSubjectName, "Paper Analysis");
            }
        });
        btnAkash.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (subInfo.mAkash_url.equals(""))
                    UtilityClass.showToast("Not Available right now", getContext());
                else
                    subButtonClick(subInfo.mAkash_url, subInfo.mSubjectName, "Akash");
            }
        });
        btnVids.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (subInfo.mYoutube_url.size() > 0)
                {
                    if (subInfo.mYoutube_url.size() == 1)
                    {
                        YoutubeClass _ytObj = subInfo.mYoutube_url.get(0);
                        ytButtonClick(_ytObj.yt_playlist_url, _ytObj.yt_name);
                    } else
                    {
                        customYTPopup();
                    }
                } else
                {
                    UtilityClass.showToast("Not Available right now", getContext());
                }
            }
        });

        return view;
    }

    private void subButtonClick(String url, String Title, String Header)
    {
        WebrowserFragment frag = new WebrowserFragment();

        // Custom Values to pass as argument when switching to another fragment
        Bundle args = new Bundle();

        args.putString("Header", Header);
        args.putString("Title", Title + " (" + Header + ")");
        args.putString("URL", url);
        frag.setArguments(args);

        FragmentTransaction fragTrans = getParentFragmentManager().beginTransaction();

        //Sets Custom animation
        fragTrans.setCustomAnimations(R.anim.slide_right, R.anim.nav_default_pop_exit_anim, R.anim.slide_left, R.anim.nav_default_pop_exit_anim);

        fragTrans.replace(R.id.frame_container, frag).addToBackStack(null);
        fragTrans.commit();
    }

    private void ytButtonClick(String url, String Title)
    {
        YoutubeFragment frag = new YoutubeFragment();

        // Custom Values to pass as argument when switching to another fragment
        Bundle args = new Bundle();
        args.putString("Header", "Video");
        args.putString("Title", Title + " Video ");
        args.putString("URL", url);
        frag.setArguments(args);

        FragmentTransaction fragTrans = getParentFragmentManager().beginTransaction();

        //Sets Custom animation
        fragTrans.setCustomAnimations(R.anim.slide_right, R.anim.nav_default_pop_exit_anim, R.anim.slide_left, R.anim.nav_default_pop_exit_anim);

        fragTrans.replace(R.id.frame_container, frag).addToBackStack(null);
        fragTrans.commit();
    }

    private void customNotesPopup()
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        View notesPopupView = getLayoutInflater().inflate(R.layout.notes_pop_up, null);

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
                subButtonClick(_notesClass.mNotes_url, _notesClass.mName, "Notes");
                dialog.hide();
            }
        });
    }

    private void customYTPopup()
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        View ytPopupView = getLayoutInflater().inflate(R.layout.yt_pop_up, null);

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
                ytButtonClick(_ytObj.yt_playlist_url, _ytObj.yt_name);
                //ytButtonClick(((YoutubeClass) subInfo.mYoutube_url.get(position)).yt_playlist_url);
                dialog.hide();
            }
        });
    }

    private void visibilityButton()
    {
        btnBook.setVisibility(subInfo.mBook_url.equals("") ? View.GONE : View.VISIBLE);
        btnAkash.setVisibility(subInfo.mAkash_url.equals("") ? View.GONE : View.VISIBLE);
        btnPprAnalysis.setVisibility(subInfo.mPaper_analysis_url.equals("") ? View.GONE : View.VISIBLE);
    }

}