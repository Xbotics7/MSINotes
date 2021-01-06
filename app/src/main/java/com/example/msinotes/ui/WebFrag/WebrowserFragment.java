package com.example.msinotes.ui.WebFrag;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.msinotes.Models.UtilityClass;
import com.example.msinotes.NotesClass;
import com.example.msinotes.R;
import com.example.msinotes.SubjectsClass;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class WebrowserFragment extends Fragment
{
    WebView mWebBrowser;
    ProgressBar mProgressBar;
    ArrayList<NotesClass> savedNotesList;
    ImageButton toolbar_bookmark;
    ImageButton toolbar_unbookmark;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_webrowser, container, false);
        BottomNavigationView navView = getActivity().findViewById(R.id.nav_view);

        // Set nav view visibility to collapse
        navView.setVisibility(View.GONE);

        Toolbar toolbar = ((AppCompatActivity) getActivity()).findViewById(R.id.toolbar);
        toolbar_bookmark = toolbar.findViewById(R.id.btnToolbarBookMark);
        toolbar_unbookmark = toolbar.findViewById(R.id.btnToolbarUnBookMark);
        toolbar_bookmark.setVisibility(View.VISIBLE);
        TextView toolbar_text = toolbar.findViewById(R.id.toolbar_title);

        String passedHeader = getArguments().getString("Header");
        final String passedTitle = getArguments().getString("Title");
        final String passedUrl = getArguments().getString("URL");

        toolbar_text.setText(passedHeader);

        savedNotesList = UtilityClass.getNotesBookMarks(getContext());

        if (savedNotesList.contains(new NotesClass("", passedUrl)))
        {
            toolbar_unbookmark.setVisibility(View.VISIBLE);
            toolbar_bookmark.setVisibility(View.GONE);
        } else
        {
            toolbar_bookmark.setVisibility(View.VISIBLE);
            toolbar_unbookmark.setVisibility(View.GONE);
        }


        mWebBrowser = view.findViewById(R.id.webBrowser);
        mProgressBar = view.findViewById(R.id.progressBarWeb);


        UtilityClass.updateWebViewDefaults(mWebBrowser);

        final NotesClass notesObj = new NotesClass(passedTitle, passedUrl);

        toolbar_bookmark.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                UtilityClass.saveToBookmark(notesObj, toolbar_bookmark, toolbar_unbookmark, getContext());
            }
        });

        toolbar_unbookmark.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                UtilityClass.removeFromBookMark(notesObj, toolbar_bookmark, toolbar_unbookmark, getContext());
            }
        });

        //Set a new client for default webview
        mWebBrowser.setWebViewClient(new WebViewClient()
        {

            //When page begins to load, set progress bar visibility to visible
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon)
            {
                mProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                view.loadUrl(url);

                return true;
            }

            //Set progress bar visibility to gone after webview is finished loading page(in this case pdf)
            @Override
            public void onPageFinished(WebView view, final String url)
            {
                mProgressBar.setVisibility(View.GONE);
            }


        });

        mWebBrowser.loadUrl(passedUrl);
        return view;
    }

    @Override
    public void onStop()
    {
        toolbar_unbookmark.setVisibility(View.GONE);
        toolbar_bookmark.setVisibility(View.GONE);
        super.onStop();
    }





}