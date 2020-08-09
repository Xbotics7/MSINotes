package com.example.msinotes.ui.WebFrag;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.msinotes.Models.UtilityClass;
import com.example.msinotes.R;
import com.example.msinotes.SubjectsClass;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WebrowserFragment extends Fragment
{
    WebView mWebBrowser;
    ProgressBar mProgressBar;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_webrowser, container, false);
        BottomNavigationView navView = getActivity().findViewById(R.id.nav_view);

        // Set nav view visibility to collapse
        navView.setVisibility(View.GONE);

        //Get the passed value sent from the previous fragment
        String passedUrl = getArguments().getString("URL");

        mWebBrowser = view.findViewById(R.id.webBrowser);
        mProgressBar = view.findViewById(R.id.progressBarWeb);

        //Method to update necessary webview properties;
        updateWebViewDefaults(mWebBrowser);

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

    private void updateWebViewDefaults(WebView webView)
    {

        WebSettings settings = webView.getSettings();
        // Enable Javascript
        settings.setJavaScriptEnabled(true);
        settings.setLoadsImagesAutomatically(true);
        // Configure the client to use when opening URLs

        // Use WideViewport and Zoom out if there is no viewport defined
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        // Enable pinch to zoom without the zoom buttons
        settings.setBuiltInZoomControls(true);

        settings.setDisplayZoomControls(false);

        // Enable remote debugging via chrome://inspect
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            WebView.setWebContentsDebuggingEnabled(true);
        }

    }
}