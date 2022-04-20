package com.example.kathbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import java.util.ArrayList;
import java.util.List;

public class Teachers extends AppCompatActivity {
    private TeachersAdapter adapter;
    private List<Teachersitem> exampleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fillExampleList();

        setUpRecyclerView();
    }

    private void fillExampleList() {
        exampleList = new ArrayList<>();
        exampleList.add(new Teachersitem(R.drawable.principal, "Mahesh Chandra Luitel", "Phone Number : 9841256820", "E-mail : mahesh@kathford.edu.np"));
        exampleList.add(new Teachersitem(R.drawable.prabinkumar, "Prabin Kumar Jha", "Phone Number : 9805954520", "E-mail : er.prabin@kathford.edu.np"));
        exampleList.add(new Teachersitem(R.drawable.arjankumar, "Arjan Kumar Sunar", "Phone Number : 9851145108", "E-mail : aarjan.sunar@kathford.edu.np"));
        exampleList.add(new Teachersitem(R.drawable.kiranbagale, "Kiran Bagale", "Phone Number : 9841136593", "E-mail : kiran.bagale@kathford.edu.np"));
        exampleList.add(new Teachersitem(R.drawable.sashiramdahal, "Sashiram Dahal", "Phone Number : 9751015533", "E-mail : er.shashiram@kathford.edu.np"));
        exampleList.add(new Teachersitem(R.drawable.dharmathapa, "Dharma Thapa", "Phone Number : 9851101980", "E-mail : dharma.thapa@kathford.edu.np"));
        exampleList.add(new Teachersitem(R.drawable.harithapa, "Hari Thapa", "Phone Number : 9843684295", "E-mail : harithapa698@gmail.com"));
        exampleList.add(new Teachersitem(R.drawable.biswashpokharel, "Biswas Pokharel", "Phone Number : 9803544296", "E-mail : bishwas.pokharel@kathford.edu.np"));
        exampleList.add(new Teachersitem(R.drawable.bishnukc, "Bishnu KC", "Phone Number : 9849686465", "E-mail : bishnu.kc@kathford.edu.np"));
        exampleList.add(new Teachersitem(R.drawable.laxmantimilsina, "Laxman Timilsina", "Phone Number : 9851239492", "E-mail : laxman.timilsina@kathford.edu.np"));
        exampleList.add(new Teachersitem(R.drawable.bikaladhikari, "Bikal Adhikari", "Phone Number : 9846518168", "E-mail : bikaladhikari@kathford.edu.np"));
        exampleList.add(new Teachersitem(R.drawable.jalauddinmansur, "Jalauddin Mansur", "Phone Number : 9841707779", "E-mail : er.jalauddin@kathford.edu.np"));
        exampleList.add(new Teachersitem(R.drawable.amritkarki, "Amrit Karki", "Phone Number : 9849781278", "E-mail : amritkarki25@gmail.com"));
        exampleList.add(new Teachersitem(R.drawable.manojadhikari, "Manoj Adhikari", "Phone Number : 9805416542", "E-mail : manoj.adhikari@kathford.edu.np"));
        exampleList.add(new Teachersitem(R.drawable.debendrabaniya, "Debendra Baniya", "Phone Number : ", "E-mail : "));
        exampleList.add(new Teachersitem(R.drawable.sachinkumaryadav, "Sachin Kumar Yadav", "Phone Number : 9840063456", "E-mail : "));

    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new TeachersAdapter(exampleList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent searchIntent = new Intent(Teachers.this, MainActivity.class);
        searchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(searchIntent);
    }
}