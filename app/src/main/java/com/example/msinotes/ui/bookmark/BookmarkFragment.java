package com.example.msinotes.ui.bookmark;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.PreferenceManager;

import com.example.msinotes.Models.UtilityClass;
import com.example.msinotes.NotesClass;
import com.example.msinotes.R;
import com.example.msinotes.YoutubeClass;
import com.example.msinotes.ui.SubjectInfo.NotesOptionsAdaptor;
import com.example.msinotes.ui.SubjectInfo.YTOptionsAdaptor;
import com.example.msinotes.ui.WebFrag.WebrowserFragment;
import com.example.msinotes.ui.ytpage.YoutubeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class BookmarkFragment extends Fragment
{
    ArrayList<NotesClass> notesList;
    ArrayList<YoutubeClass> ytList;
    ListView listView;
    ListView listView_yt;
    LinearLayout linear_bookmark;
    LinearLayout linear_bookmark_yt;
    LinearLayout linear_noBookmark;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_bookmark, container, false);

        UtilityClass.isBookmark = true;

        BottomNavigationView navView = getActivity().findViewById(R.id.nav_view);
        navView.setVisibility(View.VISIBLE);

        navView.getMenu().findItem(R.id.navigation_bookmark).setChecked(true);

        Toolbar toolbar = ((AppCompatActivity) getActivity()).findViewById(R.id.toolbar);
        TextView toolbar_text = toolbar.findViewById(R.id.toolbar_title);
        toolbar_text.setText("BOOKMARK");

        linear_bookmark = root.findViewById(R.id.linear_Bookmark);
        linear_bookmark_yt = root.findViewById(R.id.linear_Bookmark_yt);
        linear_noBookmark = root.findViewById(R.id.linear_noBookmark);

        listView = (ListView) root.findViewById(R.id.notesBookMarkList);
        listView_yt = (ListView) root.findViewById(R.id.ytBookMarkList);

        getNotesBookmark();
        getYTBookmark();

        if (notesList.size() <= 0 && ytList.size() <= 0)
        {
            linear_noBookmark.setVisibility(View.VISIBLE);
        } else
        {
            linear_noBookmark.setVisibility(View.GONE);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                NotesClass _notesClass = (NotesClass) notesList.get(position);
                subButtonClick(_notesClass.mNotes_url, _notesClass.mName, "Notes");

            }
        });
        listView_yt.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                YoutubeClass _ytObj = (YoutubeClass) ytList.get(position);
                ytButtonClick(_ytObj.yt_playlist_url, _ytObj.yt_name);

            }
        });
        return root;
    }


    @Override
    public void onStop()
    {
        UtilityClass.isBookmark = false;
        super.onStop();
    }

    private void getNotesBookmark()
    {
        notesList = UtilityClass.getNotesBookMarks(getContext());
        NotesOptionsAdaptor adapter = new NotesOptionsAdaptor(getContext(), notesList);
        listView.setAdapter(adapter);

        if (notesList.size() <= 0)
        {
            linear_bookmark.setVisibility(View.GONE);
        } else
        {
            linear_bookmark.setVisibility(View.VISIBLE);
        }
    }

    private void getYTBookmark()
    {
        ytList = UtilityClass.getYTBookMarks(getContext());
        YTOptionsAdaptor adapter = new YTOptionsAdaptor(getContext(), ytList);
        listView_yt.setAdapter(adapter);

        if (ytList.size() <= 0)
        {
            linear_bookmark_yt.setVisibility(View.GONE);
        } else
        {
            linear_bookmark_yt.setVisibility(View.VISIBLE);
        }
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
}