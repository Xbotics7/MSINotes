package com.example.msinotes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.example.msinotes.Models.UtilityClass;
import com.example.msinotes.ui.Search.SearchFragment;
import com.example.msinotes.ui.bookmark.BookmarkFragment;
import com.example.msinotes.ui.home.HomeFragment;
import com.example.msinotes.ui.settings.navigation_settings;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.msinotes.SettingsActivity;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedMainPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String storeStartUpPage = sharedMainPreferences.getString(getString(R.string.key_theme), "1");
        setCustomTheme(storeStartUpPage);
        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.nav_view);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new HomeFragment()).commit();
        navView.setOnNavigationItemSelectedListener(navListner);

        //Shyam Start
        //This Remove shadows from action bar
        try
        {
            getSupportActionBar().setElevation(30);
        }
        catch (Exception ex){

        }

        UtilityClass.showToast(storeStartUpPage, this);

        //End
        //hello secksi boi

        //shyam part 2

    }

    private void setCustomTheme(String key){
        switch (key){
            case "1":
                setTheme(R.style.light_theme);
                break;
            case "2":
                setTheme(R.style.dark_theme);
                //RelativeLayout rLayout = findViewById(R.id.container);
                //rLayout.setBackgroundColor(Color.parseColor("#171717"));
                break;
        }
    }



    private BottomNavigationView.OnNavigationItemSelectedListener navListner = new BottomNavigationView.OnNavigationItemSelectedListener()
    {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.navigation_home :
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.navigation_search:
                    selectedFragment = new SearchFragment();
                    break;
                case R.id.navigation_bookmark :
                    selectedFragment = new BookmarkFragment();
                    break;
                    case R.id.navigation_settings :
                        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                        startActivity(intent);
                    break;
            }

            if(item.getItemId() != R.id.navigation_settings)
            {

                getSupportFragmentManager().beginTransaction().setCustomAnimations( R.anim.nav_default_enter_anim, R.anim.nav_default_exit_anim).replace(R.id.frame_container, selectedFragment).commit();
            }
            return true;
        }
    };


}