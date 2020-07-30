package com.example.msinotes.ui;

import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.msinotes.Models.UtilityClass;
import com.example.msinotes.R;
import com.example.msinotes.SubjectsClass;
import com.example.msinotes.ui.semester.SemesterFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SubjectInfoFragment extends Fragment
{
    public WebView mWebBrowser;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_subject_info, container, false);

        BottomNavigationView navView = getActivity().findViewById(R.id.nav_view);
        navView.setBackgroundResource(R.drawable.rounded_info_button);

        String value = getArguments().getString("SubjectCode");
        final SubjectsClass subInfo = UtilityClass.getSubInfo(value);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(subInfo.mSubjectName);

        mWebBrowser = view.findViewById(R.id.webBrowser);
        Button btnNotes = view.findViewById(R.id.btnNotes);
        Button btnBook = view.findViewById(R.id.btnBook);
        Button btnAkash = view.findViewById(R.id.btnAkash);
        Button btnPprAnalysis = view.findViewById(R.id.btnPprAnalysis);


        UtilityClass.showToast(value, getContext());

        updateWebViewDefaults(mWebBrowser);

        mWebBrowser.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //progDailog.show();
                view.loadUrl(url);

                return true;
            }
            @Override
            public void onPageFinished(WebView view, final String url) {
                //progDailog.dismiss();
            }
        });

        btnNotes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                subButtonClick(subInfo.mNotes_url);
            }
        });
        btnBook.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                subButtonClick(subInfo.mBook_url);
            }
        });
        btnPprAnalysis.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                subButtonClick(subInfo.mPaper_analysis_url);
            }
        });
        btnAkash.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                subButtonClick(subInfo.mAkash_url);
            }
        });

        return view;
    }

    private  void subButtonClick(String url){
        mWebBrowser.setVisibility(View.VISIBLE);
        mWebBrowser.loadUrl(url);
    }

    private void updateWebViewDefaults(WebView webView) {

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

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
            // Hide the zoom controls for HONEYCOMB+
            settings.setDisplayZoomControls(false);
        }

        // Enable remote debugging via chrome://inspect
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

    }
}