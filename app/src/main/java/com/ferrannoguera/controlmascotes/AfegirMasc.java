package com.ferrannoguera.controlmascotes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.DialogPreference;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URI;


public class AfegirMasc extends Activity {

    EditText NomMasc,NumXip,d,a,m;
    Spinner NomTip;
    MascotasDBAdapter mascotasdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afegir_masc);

        NomMasc = (EditText) findViewById(R.id.NomMasc); //agafa el valor del textfield
        NomTip = (Spinner) findViewById(R.id.NomTip);
        d = (EditText) findViewById(R.id.d);
        m = (EditText) findViewById(R.id.m);
        a = (EditText) findViewById(R.id.a);
        NumXip = (EditText) findViewById(R.id.NumXip);

        mascotasdb = new MascotasDBAdapter(this);
    }

    /*private void openCamara() {
        File file = new File(Environment.getExternalStorageDirectory(), MEDIA_DIRECTORY);
        file.mkdirs();

        String path = Environment.getExternalStorageDirectory() + File.separator
                + MEDIA_DIRECTORY + File.separator + TEMPORAL_PICTURE_NAME;

        File newfile = new File(path);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newfile));
        startActivityForResult(intent, PHOTO_CODE);
    }*/


    public void addPet(View view){
        String NomM = NomMasc.getText().toString();
        String NomT = NomTip.getSelectedItem().toString();
        String DatN = d.getText().toString()+" / "+m.getText().toString()+" / "+a.getText().toString();
        String NumX = NumXip.getText().toString();

        if(NomM.isEmpty()) Message.message(this,"Faltan Campos");
        else if(NomT.isEmpty()) Message.message(this,"Faltan Campos");
        else if(DatN.isEmpty()) Message.message(this,"Faltan Campos");
        else if(NumX.isEmpty()) Message.message(this,"Faltan Campos");
        else {
            /*long id = mascotasdb.maininsert(NomM, NomT, DatN, NumX);
            if (id < 0) {
                Message.message(this, "Unsuccessfull");
            } else {
                Message.message(this, "Successfully inserted a row");
            }
        }*/
            int dia = Integer.parseInt(d.getText().toString());
            int mes = Integer.parseInt(m.getText().toString());
            int any = Integer.parseInt(a.getText().toString());
            if (dia <= 0) Message.message(this,"Dia Incorrecte");
            else if (dia >= 32) Message.message(this,"Dia Incorrecte");
            else if (mes <= 0) Message.message(this, "Mes Incorrecte");
            else if (mes >= 13) Message.message(this, "Mes Incorrecte");
            else if (any <= 1920) Message.message(this, "Any Incorrecte");
            else if (any >= 2016) Message.message(this, "Any Incorrecte");
            else {
                Intent i = new Intent(this, Editar.class);
                i.putExtra("name", NomM);
                i.putExtra("tipus", NomT);
                i.putExtra("DatN", DatN);
                i.putExtra("NumX", NumX);
                startActivity(i);
                finish();
            }
        }
    }


    public void back(View view){
        finish();
    }
}
