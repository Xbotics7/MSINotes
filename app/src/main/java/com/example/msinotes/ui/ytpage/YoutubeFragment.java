package com.example.msinotes.ui.ytpage;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.msinotes.Models.UtilityClass;
import com.example.msinotes.NotesClass;
import com.example.msinotes.R;
import com.example.msinotes.YoutubeClass;
import com.example.msinotes.ui.SubjectInfo.NotesOptionsAdaptor;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YoutubeFragment extends Fragment
{
    WebView mYTWeb;
    ProgressBar mProgressBar;
    ArrayList<YoutubeClass> savedYTList;
    ImageButton toolbar_bookmark;
    ImageButton toolbar_unbookmark;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_youtube, container, false);
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

        savedYTList = UtilityClass.getYTBookMarks(getContext());

        if (savedYTList.contains(new YoutubeClass("", passedUrl)))
        {
            toolbar_unbookmark.setVisibility(View.VISIBLE);
            toolbar_bookmark.setVisibility(View.GONE);
        } else
        {
            toolbar_bookmark.setVisibility(View.VISIBLE);
            toolbar_unbookmark.setVisibility(View.GONE);
        }

        mYTWeb = (WebView) view.findViewById(R.id.yt_webview);
        mProgressBar = (ProgressBar)view.findViewById(R.id.progressBarYT);

        UtilityClass.updateWebViewDefaults(mYTWeb);

        final YoutubeClass ytObj = new YoutubeClass(passedTitle, passedUrl);

        toolbar_bookmark.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                UtilityClass.saveToYTBookmark(ytObj, toolbar_bookmark, toolbar_unbookmark, getContext());
            }
        });

        toolbar_unbookmark.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                UtilityClass.removeFromYTBookMark(ytObj, toolbar_bookmark, toolbar_unbookmark, getContext());
            }
        });
        //Set a new client for default webview
        mYTWeb.setWebViewClient(new WebViewClient()
        {

            //When page begins to load, set progress bar visibility to visible
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon)
            {
                UtilityClass.injectJavascript(mYTWeb, UtilityClass.getCSS());
                mProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                view.loadUrl(url);

                return true;
            }

            @Override
            public void doUpdateVisitedHistory(WebView view, String url, boolean isReload)
            {
                if (url.contains("watch?"))
                {
                    String pattern = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";

                    Pattern compiledPattern = Pattern.compile(pattern);
                    Matcher matcher = compiledPattern.matcher(url);

                    if (matcher.find())
                    {
                        view.stopLoading();
                        view.loadData(UtilityClass.getCustomHtml(matcher.group()), "text/html", "UTF-8");
                    }
                }
                super.doUpdateVisitedHistory(view, url, isReload);
            }

            //Set progress bar visibility to gone after webview is finished loading page(in this case pdf)
            @Override
            public void onPageFinished(WebView view, final String url)
            {
                UtilityClass.injectJavascript(mYTWeb, UtilityClass.getCSS());
                mProgressBar.setVisibility(View.GONE);
            }


        });
        mYTWeb.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    WebView webView = (WebView) v;

                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_BACK:
                            if (webView.getUrl().contains("iframe"))
                            {
                                webView.loadUrl(passedUrl);
                                return true;
                            }
                            break;
                    }
                }

                return false;
            }
        });
        mYTWeb.loadUrl(passedUrl);

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