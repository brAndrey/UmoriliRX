package com.example.umorili2.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.umorili2.R;
import com.example.umorili2.ui.fragments.RecyclerFragment;
import com.example.umorili2.viewmodel.MainAvtivityViewModel;
import com.example.umorili2.viewmodel.ViewModelFectory;

public class MainActivity extends AppCompatActivity {

    private  MainAvtivityViewModel mainAvtivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.container, RecyclerFragment.newInstance())
//                    .commitNow();
            ViewModelFectory   viewModelFectory = new ViewModelFectory();

            // подписываемся на ViewModel
            mainAvtivityViewModel = new ViewModelProvider(this,viewModelFectory).get(MainAvtivityViewModel.class);

            IntRemoteFragment();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_remote) {
            Log.e(this.getClass().getSimpleName(),"Clear base");
            //mainActivityViewModel.ClearBase();
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_local) {
            Log.e(this.getClass().getSimpleName(),"Clear base");
            //mainActivityViewModel.ClearBase();
            return true;
        }

        if (id == R.id.action_clear) {
            mainAvtivityViewModel.ClearBase();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private void IntLocalFragment(){

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

    private void IntRemoteFragment(){

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