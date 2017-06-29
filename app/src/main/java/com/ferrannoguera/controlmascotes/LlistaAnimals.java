package com.ferrannoguera.controlmascotes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;


public class LlistaAnimals extends Activity {

    MascotasDBAdapter mascotasdb;
    private static String NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llista_animals);

        mascotasdb = new MascotasDBAdapter(this);

        String[] petnames = mascotasdb.getallpetnames();

        //PRIMER ADAPTER PERQUE QUEDI SENSE FOTOS SEGON PER FERLO BONICOT
        //ListAdapter myAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,petnames);
        ListAdapter myAdapter = new CustomAdapter(this,petnames);
        ListView myListView = (ListView) findViewById(R.id.myListView);
        myListView.setAdapter(myAdapter);
        myListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        NAME = String.valueOf(parent.getItemAtPosition(position));
                        gotodadesmasc();
                    }
                }
        );

    }


    void gotodadesmasc() {
        Intent i = new Intent(this, DadesMasc.class);
        i.putExtra("name",NAME);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String[] petnames = mascotasdb.getallpetnames();

        //PRIMER ADAPTER PERQUE QUEDI SENSE FOTOS SEGON PER FERLO BONICOT
        //ListAdapter myAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,petnames);
        ListAdapter myAdapter = new CustomAdapter(this,petnames);
        ListView myListView = (ListView) findViewById(R.id.myListView);
        myListView.setAdapter(myAdapter);
        myListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        NAME = String.valueOf(parent.getItemAtPosition(position));
                        gotodadesmasc();
                    }
                }
        );
    }

    public void back(View view){
        finish();
    }
}
