package com.example.msinotes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.msinotes.Models.UtilityClass;
import com.example.msinotes.ui.Search.SearchFragment;
import com.example.msinotes.ui.SubjectInfo.NotesOptionsAdaptor;
import com.example.msinotes.ui.bookmark.BookmarkFragment;
import com.example.msinotes.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.msinotes.SettingsActivity;

public class MainActivity extends AppCompatActivity
{

    BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //This stores Theme value index
        SharedPreferences sharedMainPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String storeStartUpPage = sharedMainPreferences.getString(getString(R.string.key_theme), "1");

        //Function to set theme
        setCustomTheme(storeStartUpPage);

        setContentView(R.layout.activity_main);

        navView = findViewById(R.id.nav_view);

        //Default fragment to on app launch
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new HomeFragment()).commit();

        //Listener attached to nav view (bottom view)
        navView.setOnNavigationItemSelectedListener(navListner);

        try
        {
            //Add shadows from action bar
            getSupportActionBar().setElevation(30);
        } catch (Exception ex)
        {

        }
        changelogPop();

    }

    private void setCustomTheme(String key)
    {
        // Set Style from style.xml
        switch (key)
        {
            case "1":
                setTheme(R.style.light_theme);
                break;
            case "2":
                setTheme(R.style.dark_theme);
                break;
        }
    }

    private void changelogPop()
    {
        SharedPreferences sharedMainPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean _showChangelog = false;
        if (sharedMainPreferences.contains("changelog_val"))
        {
            String storeStartUpPage = sharedMainPreferences.getString("changelog_val", getString(R.string.app_version));
            if (storeStartUpPage.equals(getString(R.string.app_version)))
            {
                _showChangelog = false;
            } else
            {
                _showChangelog = true;
                SharedPreferences.Editor editor = sharedMainPreferences.edit();
                editor.putString("changelog_val", getString(R.string.app_version));
                editor.apply();
            }

        } else
        {
            _showChangelog = true;
            SharedPreferences.Editor editor = sharedMainPreferences.edit();
            editor.putString("changelog_val", getString(R.string.app_version));
            editor.apply();
        }

        if (_showChangelog)
        {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            View changelogPopupView = getLayoutInflater().inflate(R.layout.layout_changelog, null);

            dialogBuilder.setView(changelogPopupView);
            final AlertDialog dialog = dialogBuilder.create();
            dialog.show();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListner = new BottomNavigationView.OnNavigationItemSelectedListener()
    {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            Fragment selectedFragment = null;
            switch (item.getItemId())
            {
                case R.id.navigation_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.navigation_search:
                    selectedFragment = new SearchFragment();
                    break;
                case R.id.navigation_bookmark:
                    selectedFragment = new BookmarkFragment();
                    break;
                case R.id.navigation_settings:
                    Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                    startActivity(intent);
                    break;
            }
            if (item.getItemId() != R.id.navigation_settings)
            {
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.nav_default_enter_anim, R.anim.nav_default_exit_anim).replace(R.id.frame_container, selectedFragment).commit();
            }
            return true;
        }
    };


}