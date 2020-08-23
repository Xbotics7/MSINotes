package com.example.msinotes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.msinotes.Models.UtilityClass;

public class SettingsActivity extends AppCompatActivity implements
        PreferenceFragmentCompat.OnPreferenceStartFragmentCallback
{

    private static final String TITLE_TAG = "settingsActivityTitle";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new HeaderFragment())
                    .commit();
        } else
        {
            setTitle(savedInstanceState.getCharSequence(TITLE_TAG));
        }
        getSupportFragmentManager().addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener()
                {
                    @Override
                    public void onBackStackChanged()
                    {
                        if (getSupportFragmentManager().getBackStackEntryCount() == 0)
                        {
                            setTitle(R.string.title_activity_settings);
                        }
                    }
                });
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {
        if (getSupportActionBar().getTitle().toString().equals("Settings"))
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        super.onBackPressed();

    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        // Save current activity title so we can set it again after a configuration change
        outState.putCharSequence(TITLE_TAG, getTitle());
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        if (getSupportFragmentManager().popBackStackImmediate())
        {
            return true;
        }
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onPreferenceStartFragment(PreferenceFragmentCompat caller, Preference pref)
    {
        // Instantiate the new Fragment
        final Bundle args = pref.getExtras();
        final Fragment fragment = getSupportFragmentManager().getFragmentFactory().instantiate(
                getClassLoader(),
                pref.getFragment());
        fragment.setArguments(args);
        fragment.setTargetFragment(caller, 0);
        // Replace the existing Fragment with the new Fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.settings, fragment)
                .addToBackStack(null)
                .commit();
        setTitle(pref.getTitle());
        return true;
    }

    public static class HeaderFragment extends PreferenceFragmentCompat
    {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey)
        {
            setPreferencesFromResource(R.xml.header_preferences, rootKey);
        }
    }

    public static class GeneralFragment extends PreferenceFragmentCompat
    {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey)
        {
            setPreferencesFromResource(R.xml.general_preferences, rootKey);
            final ListPreference selected_theme = (ListPreference) getPreferenceManager().findPreference("key_theme");

            SharedPreferences sharedMainPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            String storeStartUpPage = sharedMainPreferences.getString(getString(R.string.key_theme), "1");
            selected_theme.setSummary(storeStartUpPage.equals("1") ? "Light Theme" : "Dark Theme");
            selected_theme.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener()
            {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue)
                {
                    String strTheme = newValue.toString().equals("1") ? "Light Theme" : "Dark Theme";
                    selected_theme.setSummary(strTheme);
                    UtilityClass.showToast("Theme changed to " + strTheme, getContext());
                    return true;
                }
            });
        }

    }

    //    public static class MessagesFragment extends PreferenceFragmentCompat
//    {
//
//        @Override
//        public void onCreatePreferences(Bundle savedInstanceState, String rootKey)
//        {
//            setPreferencesFromResource(R.xml.messages_preferences, rootKey);
//        }
//    }
    public static class AboutFragment extends PreferenceFragmentCompat
    {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey)
        {
            setPreferencesFromResource(R.xml.about_preferences, rootKey);


        }

    }

    public static class ChangelogFragment extends PreferenceFragmentCompat
    {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey)
        {
            setPreferencesFromResource(R.xml.changelog_preferences, rootKey);


        }

    }


    //    public static class SyncFragment extends PreferenceFragmentCompat
//    {
//
//        @Override
//        public void onCreatePreferences(Bundle savedInstanceState, String rootKey)
//        {
//            setPreferencesFromResource(R.xml.sync_preferences, rootKey);
//        }
//    }
}