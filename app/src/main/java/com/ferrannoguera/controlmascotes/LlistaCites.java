package com.ferrannoguera.controlmascotes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class LlistaCites extends Activity {

    MascotasDBAdapter mascotasdb;
    private static String NAME;
    private static String DATA;
    private static String TIPUS;
    private String[] dates;
    private View lastTouchedView;
    private String dia;
    private String mes;
    private String any;
    private Bundle dades;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llista_cites);

        mascotasdb = new MascotasDBAdapter(this);

        dades = getIntent().getExtras();
        if(dades == null) return;
        NAME = dades.getString("NOM");
        TIPUS= dades.getString("TIPUS");


        if(TIPUS.equals("Vacunacion")) dates = mascotasdb.getallvacunacio();
        else if(TIPUS.equals("Desparacitacion")) dates = mascotasdb.getalldesparacitacio();
        else if (TIPUS.equals("Veterinario")) dates = mascotasdb.getalldesparacitacio();

        TextView TT = (TextView) findViewById(R.id.TT);
        TT.setText(TIPUS);

        //PRIMER ADAPTER PERQUE QUEDI SENSE FOTOS SEGON PER FERLO BONICOT
        //ListAdapter myAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dates);
        ListAdapter myAdapter = new CA2(this,dates);
        ListView myListView = (ListView) findViewById(R.id.CitesList);
        myListView.setAdapter(myAdapter);

        lastTouchedView = myListView;
                myListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        int c = view.getSolidColor();
                        lastTouchedView.setBackgroundColor(c);
                        DATA = String.valueOf(parent.getItemAtPosition(position));
                        changecolor(view);
                        eliminardata();

                        //gotodadesmasc();
                    }
                }
        );
    }

    public void changecolor(View view){
        //lastTouchedView.setBackgroundColor(Color.WHITE);
        int restar = 0;
        String s = DATA.substring(0, 1);
        String s1 = DATA.substring(1,2);

        if (s1.equals(" ")){
            dia = s;
            ++restar;
            s = DATA.substring(5-restar,6-restar);
            s1 = DATA.substring(6-restar,7-restar);
            if (s1.equals(" ")){
                mes = s;
                ++restar;
                s = DATA.substring(10-restar,11-restar);
                s1 = DATA.substring(11-restar,12-restar);
                String s2 = DATA.substring(12-restar,13-restar);
                String s3 = DATA.substring(13-restar,14-restar);
                any = s+s1+s2+s3;
            }
            else{
                mes = s+s1;
                s = DATA.substring(10-restar,11-restar);
                s1 = DATA.substring(11-restar,12-restar);
                String s2 = DATA.substring(12-restar,13-restar);
                String s3 = DATA.substring(13-restar,14-restar);
                any = s+s1+s2+s3;
            }
        }
        else {
            dia = s+s1;
            s = DATA.substring(5-restar,6-restar);
            s1 = DATA.substring(6-restar,7-restar);
            if (s1.equals(" ")){
                mes = s;
                ++restar;
                s = DATA.substring(10-restar,11-restar);
                s1 = DATA.substring(11-restar,12-restar);
                String s2 = DATA.substring(12-restar,13-restar);
                String s3 = DATA.substring(13-restar,14-restar);
                any = s+s1+s2+s3;
            }
            else{
                mes = s+s1;
                s = DATA.substring(10-restar,11-restar);
                s1 = DATA.substring(11-restar,12-restar);
                String s2 = DATA.substring(12-restar,13-restar);
                String s3 = DATA.substring(13-restar,14-restar);
                any = s+s1+s2+s3;
            }
        }

        //Message.message(this,"dia: "+dia+" mes: "+mes+" any: "+any);
        view.setBackgroundColor(Color.GRAY);
        lastTouchedView = view;
    }

    //public void borrar_cita()

    public void eliminardata(){
        Button b = (Button)findViewById(R.id.b);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] options = {"SI", "NO"};
                final AlertDialog.Builder builder = new AlertDialog.Builder(LlistaCites.this);
                builder.setTitle("Esta Seguro?");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int seleccion) {
                        if (options[seleccion] == "SI") {
                            borrar_cita();
                        } else if (options[seleccion] == "NO") {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            }
        });
    }

    public void borrar_cita(){
        mascotasdb.borrarData(NAME,dia,mes,any);
        onCreate(dades);
    }


    public void back(View view) {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_llista_cites, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


class CA2 extends ArrayAdapter<String> {

    MascotasDBAdapter mascotasdb;

    public CA2(Context context, String[] petnames) {
        super(context,R.layout.custom_row_2, petnames);
        mascotasdb = new MascotasDBAdapter(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater myInflater = LayoutInflater.from(getContext());
        View CustomView = myInflater.inflate(R.layout.custom_row_2, parent, false);

        String name = getItem(position);
        TextView myText = (TextView) CustomView.findViewById(R.id.d1);
        myText.setText(name);

        return CustomView;
    }
}
