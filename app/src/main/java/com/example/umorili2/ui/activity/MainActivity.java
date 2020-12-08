package com.example.umorili2.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.umorili2.R;
import com.example.umorili2.ui.fragments.FragmentClassic;
import com.example.umorili2.ui.fragments.RecyclerFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.container, RecyclerFragment.newInstance())
//                    .commitNow();

            FragmentManager fm = getSupportFragmentManager();

            Fragment fragment = fm.findFragmentById(R.id.fragmentConteiner);

            if (fragment == null) {
                fragment = new RecyclerFragment();
                //fragment = new FragmentClassic();
                fm.beginTransaction().
                        add(R.id.fragmentConteiner, fragment)
                        .commit();
            }
        }


    }
}