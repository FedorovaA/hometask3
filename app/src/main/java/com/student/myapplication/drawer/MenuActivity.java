package com.student.myapplication.drawer;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.student.myapplication.R;
import com.student.myapplication.currency.CurrencyFragment;
import com.student.myapplication.movie.Movie;
import com.student.myapplication.movie.MovieListFragment;
import com.student.myapplication.utils.LoginController;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, CurrencyFragment.OnFragmentInteractionListener,MovieListFragment.OnFragmentInteractionListener {

    private CurrencyFragment euroFragment;
    private CurrencyFragment shekelFragment;
    private CurrencyFragment tengeFragment;
    private MovieListFragment movieListFragment;
    private TextView txtLog;
    private TextView txtDate;
    double j = 0.0;
    String[] values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        shekelFragment = CurrencyFragment.newInstance("SHEKEL");
        euroFragment = CurrencyFragment.newInstance("EURO");
        tengeFragment = CurrencyFragment.newInstance("TENGE");
        movieListFragment = MovieListFragment.newInstance();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        txtLog = headerView.findViewById(R.id.txt_login);
        txtLog.setText(LoginController.getInstance().getLogin());
        txtDate = headerView.findViewById(R.id.txt_date);
        txtDate.setText(LoginController.getInstance().getDate().toString());
        values = new String[101];
        for(int i=0;i<101;i++){
            values[i] = String.valueOf(Math.round(j*10)/10d);
            j+=0.1;
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frag_cont1,shekelFragment)
                    .commit();
        } else if (id == R.id.nav_gallery) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frag_cont1,euroFragment)
                    .commit();

        } else if (id == R.id.nav_slideshow) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frag_cont1,tengeFragment)
                    .commit();

        } else if (id == R.id.nav_manage) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frag_cont1,movieListFragment)
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onInputRubleSent(String input, String param) {
        switch (param)
        {
            case "SHEKEL":
                shekelFragment.updateCurrencyText(input);
                break;
            case "EURO":
                euroFragment.updateCurrencyText(input);
                break;
            case "TENGE":
                tengeFragment.updateCurrencyText(input);
                break;
        }
    }

    @Override
    public void onMessage(String message) {
        Toast.makeText(MenuActivity.this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInputCurrencySent(String input, String param) {
        switch (param)
        {
            case "SHEKEL":
                shekelFragment.updateRubleText(input);
                break;
            case "EURO":
                euroFragment.updateRubleText(input);
                break;
            case "TENGE":
                tengeFragment.updateRubleText(input);
                break;
        }
    }

    @Override
    public void toastMovieTitle(Movie movie) {

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = alertDialog.getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_movie, null);
        final NumberPicker np = view.findViewById(R.id.numberPicker);
        np.setMaxValue(0);
        np.setMaxValue(values.length-1);
        np.setDisplayedValues(values);
        np.setValue((int)(10*Double.valueOf(movie.getMark())));

        alertDialog.setTitle("Установить новый рейтинг");

        alertDialog.setView(view);

        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                movie.setMark(String.valueOf(values[np.getValue()]));
            }
        });

        alertDialog.show();
    }
}
