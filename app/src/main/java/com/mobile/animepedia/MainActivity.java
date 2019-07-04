package com.mobile.animepedia;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mobile.animepedia.Adapter.HomeAdapter;
import com.mobile.animepedia.Api.AnimepediaApi;
import com.mobile.animepedia.Model.AnimepediaItem;
import com.mobile.animepedia.Presenter.HomePresenter;
import com.mobile.animepedia.View.MainView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainView {
    FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    RecyclerView rvHome;


    HomePresenter homePresenter;
    HomeAdapter homeAdapter;
    AnimepediaApi animepediaApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvHome = findViewById(R.id.rv_list_beranda);
        auth = FirebaseAuth.getInstance();

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };

//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        rvHome.setLayoutManager(manager);
//        rvHome.setHasFixedSize(true);
//        homeAdapter = new HomeAdapter(this);
//        rvHome.setAdapter(homeAdapter);
        tampilAnimepedia();

    }

    private void tampilAnimepedia() {

        homeAdapter = new HomeAdapter(this);
        animepediaApi = new AnimepediaApi();
        rvHome.setLayoutManager(new LinearLayoutManager(this));
        homePresenter = new HomePresenter(this, animepediaApi, this);
        homePresenter.LoadAnimepedia();
        rvHome.setAdapter(homeAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_signout:
                auth.signOut();
                Toast.makeText(this, "Item 1 Selected", Toast.LENGTH_SHORT).show();

                break;
        }

        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }

    @Override
    public void showAnimepedia(ArrayList<AnimepediaItem> animepediaItems) {

        homeAdapter.setAnimepediaItem(animepediaItems);
        rvHome.setAdapter(homeAdapter);
        homeAdapter.notifyDataSetChanged();

    }
}
