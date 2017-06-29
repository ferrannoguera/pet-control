package com.ferrannoguera.controlmascotes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AfegirCita extends Activity {
    MascotasDBAdapter mascotasdb;
    String MASCOTASELECTED;
    private String dia;
    private String mes;
    private String any;
    private String titol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afegir_cita);

        Bundle dades = getIntent().getExtras();
        if(dades == null) return;
        titol = dades.getString("titol");
        dia = dades.getString("dia");
        mes = dades.getString("mes");
        any = dades.getString("any");

        TextView tit = (TextView)findViewById(R.id.titol);
        tit.setText(titol);
        TextView data = (TextView)findViewById(R.id.Data);
        data.setText(dia+" / "+mes+" / "+any);

        mascotasdb = new MascotasDBAdapter(this);

        final String[] petnames = mascotasdb.getallpetnamescancelar();
        final Button button = (Button) findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //final CharSequence[] options = {/*"Tomar Foto",*/"Galeria","Cancelar"};
                final AlertDialog.Builder builder = new AlertDialog.Builder(AfegirCita.this);
                builder.setTitle("Escoje una Mascota");
                builder.setItems(petnames, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int seleccion){
                        if (petnames[seleccion] == "CANCELAR"){
                            dialog.dismiss();
                        }
                        else {
                            MASCOTASELECTED = petnames[seleccion];
                            button.setText(petnames[seleccion]);
                        }
                    }
                });
                builder.show();
            }
        });
    }


    public void addData(View view){
        Button button2 = (Button) findViewById(R.id.button2);
        if (button2.getText().equals("CLICAR")) Message.message(this,"Seleccione una mascota");
        else{
            long id = mascotasdb.insertdata(MASCOTASELECTED,dia,mes,any,titol);
            if (id < 0) {
                Message.message(this, "Unsuccessfull");
            } else {
                Message.message(this, "Successfully inserted a row");
                finish();
            }
        }
    }


    public void back(View view){
        finish();
    }

}

