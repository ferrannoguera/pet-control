package com.ferrannoguera.controlmascotes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class DadesMasc extends Activity {

    MascotasDBAdapter mascotasdb;
    private TextView t1;
    private TextView t2;
    private TextView t3;
    private TextView t4;
    private ImageView t5;
    private TextView t6;
    private String nom;
    private String tipus;
    private byte[] aux;
    private String tesp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mascotasdb = new MascotasDBAdapter(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dades_masc);

        Bundle dades = getIntent().getExtras();
        if(dades == null) return;
        nom = dades.getString("name");


        String data = mascotasdb.getNomM(nom);
        t1 = (TextView)findViewById(R.id.t1);
        t1.setText(data);

        data = mascotasdb.getNTip(nom);
        t2 = (TextView)findViewById(R.id.t2);
        t2.setText(data);

        data = mascotasdb.getdatn(nom);
        t3 = (TextView)findViewById(R.id.t3);
        t3.setText(data);

        data = mascotasdb.getnumx(nom);
        t4 = (TextView)findViewById(R.id.t4);
        t4.setText(data);

        aux = mascotasdb.getimg(nom);
        Bitmap bmap = BitmapFactory.decodeByteArray(aux,0,aux.length);
        t5 = (ImageView)findViewById(R.id.t5);
        t5.setImageBitmap(bmap);

        tesp = mascotasdb.getTesp(nom);
        t6 = (TextView)findViewById(R.id.t6);
        t6.setText(tesp);

        Button button = (Button) findViewById(R.id.eliminar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] options = {"SI", "NO"};
                final AlertDialog.Builder builder = new AlertDialog.Builder(DadesMasc.this);
                builder.setTitle("Esta seguro?");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int seleccion) {
                        if (options[seleccion] == "SI") {
                            borrar_mascota();
                        } else if (options[seleccion] == "NO") {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            }
        });

        Button gotollcites = (Button) findViewById(R.id.gotollcites);
        gotollcites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] options = {"Vacunacion", "Desparacitacion", "Veterinario", "Cancelar"};
                final AlertDialog.Builder builder = new AlertDialog.Builder(DadesMasc.this);
                builder.setTitle("Seleccione tipo de cita");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int seleccion) {
                        if (options[seleccion] == "Vacunacion") {
                            tipus = "Vacunacion";
                            schedules();
                        } else if (options[seleccion] == "Desparacitacion") {
                            tipus = "Desparacitacion";
                            schedules();
                        } else if (options[seleccion] == "Veterinario") {
                            tipus = "Veterinario";
                            schedules();
                        } else if (options[seleccion] == "Cancelar"){
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            }
        });
    }


    public void back(View view){
        finish();
    }

    public void editar(View view){
        Intent i = new Intent(this,OnlyEdit.class);
        i.putExtra("NOM", nom);
        i.putExtra("IMG", aux);
        i.putExtra("TESP", tesp);
        startActivity(i);
    }

    public void schedules(){
        Intent i = new Intent(this,LlistaCites.class);
        i.putExtra("NOM",nom);
        i.putExtra("TIPUS",tipus);
        startActivity(i);
    }


    public void borrar_mascota(){
        mascotasdb.borrarMasc(nom);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        aux = mascotasdb.getimg(nom);
        Bitmap bmap = BitmapFactory.decodeByteArray(aux,0,aux.length);
        t5 = (ImageView)findViewById(R.id.t5);
        t5.setImageBitmap(bmap);

        tesp = mascotasdb.getTesp(nom);
        t6 = (TextView)findViewById(R.id.t6);
        t6.setText(tesp);
    }
}
