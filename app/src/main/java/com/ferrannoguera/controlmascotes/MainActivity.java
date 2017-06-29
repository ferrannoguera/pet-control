package com.ferrannoguera.controlmascotes;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends Activity {

    //EditText NomMasc,NomTip,DatNeix,NumXip,Shit;
    MascotasDBAdapter mascotasdb;
    SharedPreferences pref = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getSharedPreferences("com.ferrannoguera.controlmascotes", MODE_PRIVATE);

        /*NomMasc = (EditText) findViewById(R.id.nomMasc); //agafa el valor del textfield
        NomTip = (EditText) findViewById(R.id.NomTip);
        DatNeix = (EditText) findViewById(R.id.DatNeix);
        NumXip = (EditText) findViewById(R.id.NumXip);
        Shit = (EditText) findViewById(R.id.Shit);

        mascotasdb = new MascotasDBAdapter(this); PER QUAN BASE DE DADES*/

    }

    public void int2(View view){
        Intent i = new Intent(this,AfegirMasc.class);
        startActivity(i);
    }

    public void intcal(View view) {
        Intent i = new Intent(this,Calendari.class);
        startActivity(i);
    }

    public void intviewmasc(View view){
        Intent i = new Intent(this,LlistaAnimals.class);
        startActivity(i);
    }

    public void close(View view){
        finish();
    }

    public void onResume() {
        if (pref.getBoolean("firstRun", true)) {
            insertarDades();
            pref.edit().putBoolean("firstRun", false).commit();
        }
    }

    public void insertarDades(){

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
        if (id == R.id.action_about) {
            return true;
        }else if(id == R.id.action_help){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
