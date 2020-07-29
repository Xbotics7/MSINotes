package com.example.msinotes;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;

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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

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

        //End
        //hello secksi boi

        //shyam part 2

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
                    selectedFragment = new navigation_settings();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, selectedFragment).commit();

            return true;
        }
    };


}