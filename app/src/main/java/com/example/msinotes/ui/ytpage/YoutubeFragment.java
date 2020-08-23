package com.example.msinotes.ui.ytpage;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.msinotes.Models.UtilityClass;
import com.example.msinotes.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YoutubeFragment extends Fragment
{
    WebView mYTWeb;
    ProgressBar mProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_youtube, container, false);
        BottomNavigationView navView = getActivity().findViewById(R.id.nav_view);

        // Set nav view visibility to collapse
        navView.setVisibility(View.GONE);

        //Get the passed value sent from the previous fragment
        final String passedUrl = getArguments().getString("URL");

        mYTWeb = (WebView) view.findViewById(R.id.yt_webview);
        mProgressBar = (ProgressBar)view.findViewById(R.id.progressBarYT);

        UtilityClass.updateWebViewDefaults(mYTWeb);

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
}