package com.ferrannoguera.controlmascotes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;


public class Calendari extends Activity {
    CalendarView calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendari);
        // this can be add to make calendar
        calendar = (CalendarView) findViewById(R.id.calendari);
        calendar.setShowWeekNumber(false);
        calendar.setFirstDayOfWeek(2);


        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view,
                                            int year, int month, int dayOfMonth) {
                Toast.makeText(getApplicationContext(),
                        dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
                final int y = year;
                final int m = month;
                final int d = dayOfMonth;
                final CharSequence[] options = {"Vacunacion","Desparacitacion","Cita Veterinario","Atras"};
                final AlertDialog.Builder builder = new AlertDialog.Builder(Calendari.this);
                builder.setTitle("Escoje una opcion");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int seleccion) {
                        if (options[seleccion] == "Vacunacion") {
                            goVacunacion(y,m,d);
                        } else if (options[seleccion] == "Desparacitacion") {
                            goDesparacitacion(y,m,d);
                        } else if (options[seleccion] == "Cita Veterinario"){
                            goVeterinario(y,m,d);
                        } else if (options[seleccion] == "Atras"){
                            dialog.dismiss();
                        }

                    }
                });
                builder.show();
            }
        });
    }

    public void goVacunacion(int year, int month, int dayOfMonth){
        Intent i = new Intent(this,AfegirCita.class);
        i.putExtra("titol","Vacunacion");
        int x = dayOfMonth;
        int y = month+1;
        int z = year;
        String d = ""+x;
        String m = ""+y;
        String a = ""+z;
        i.putExtra("dia",d);
        i.putExtra("mes",m);
        i.putExtra("any",a);
        startActivity(i);
    }

    public void goDesparacitacion(int year, int month, int dayOfMonth){
        Intent i = new Intent(this,AfegirCita.class);
        i.putExtra("titol","Desparacitacion");
        int x = dayOfMonth;
        int y = month+1;
        int z = year;
        String d = ""+x;
        String m = ""+y;
        String a = ""+z;
        i.putExtra("dia",d);
        i.putExtra("mes",m);
        i.putExtra("any",a);
        startActivity(i);
    }

    public void goVeterinario(int year, int month, int dayOfMonth){
        Intent i = new Intent(this,AfegirCita.class);
        i.putExtra("titol","Veterinario");
        int x = dayOfMonth;
        int y = month+1;
        int z = year;
        String d = ""+x;
        String m = ""+y;
        String a = ""+z;
        i.putExtra("dia",d);
        i.putExtra("mes",m);
        i.putExtra("any",a);
        startActivity(i);
    }


    public void back(View view){
        finish();
    }
}

