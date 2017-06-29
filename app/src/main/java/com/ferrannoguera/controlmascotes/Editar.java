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
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;


public class Editar extends Activity {
    MascotasDBAdapter mascotasdb;

    private String APP_DIRECTORY = "myPicture/App";
    private String MEDIA_DIRECTORY = APP_DIRECTORY + "media";
    private String TEMPORAL_PICTURE_NAME = "temporal.jpg";

    private final int PHOTO_CODE = 100;
    private final int SELECT_PICTURE = 200;


    private ImageView imageView;
    private EditText myEditText;

    private static Boolean PHOTO = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        mascotasdb = new MascotasDBAdapter(this);

        //Bundle d = getIntent().getExtras();
        //if(d == null) return;
        //byte[] aux = d.getByteArray("IMG");
        //String tesp = d.getString("TESP");

        myEditText = (EditText) findViewById(R.id.myEditText);
        //myEditText.setText(tesp);

        imageView = (ImageView) findViewById(R.id.photoViewer);
        //Bitmap bmap = BitmapFactory.decodeByteArray(aux, 0, aux.length);
        //imageView.setImageBitmap(bmap);

        Button button = (Button) findViewById(R.id.photoButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] options = {/*"Tomar Foto",*/"Galeria", "Cancelar"};
                final AlertDialog.Builder builder = new AlertDialog.Builder(Editar.this);
                builder.setTitle("Escoje una opcion");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int seleccion) {
                        /*if(options[seleccion] == "Tomar Foto") {
                            openCamara();
                        }else */
                        if (options[seleccion] == "Galeria") {
                            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            intent.setType("image/*");
                            startActivityForResult(intent.createChooser(intent, "Selecciona app de imagen"), SELECT_PICTURE);
                            PHOTO = true;
                        } else if (options[seleccion] == "Cancelar") {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case PHOTO_CODE:
                if (resultCode == RESULT_OK){
                    String dir = Environment.getExternalStorageDirectory() + File.separator
                            + MEDIA_DIRECTORY + File.separator + TEMPORAL_PICTURE_NAME;
                    decodeBitmap(dir);
                }
                break;

            case SELECT_PICTURE:
                if (resultCode == RESULT_OK){
                    Uri path = data.getData();
                    imageView.setImageURI(path);
                }
                break;
        }
    }

    public void decodeBitmap(String dir){
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeFile(dir);

        imageView.setImageBitmap(bitmap);
    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    public void addPet(View view){
        Bundle dades = getIntent().getExtras();
        if(dades == null) return;
        String NomM = dades.getString("name");
        String NomT = dades.getString("tipus");
        String DatN = dades.getString("DatN");
        String NumX = dades.getString("NumX");
        long id = mascotasdb.maininsert(NomM, NomT, DatN, NumX);
        if (id < 0) {
            Message.message(this, "Unsuccessfull");
        } else {
            Message.message(this, "Successfully inserted a row");
        }
        if (PHOTO) {
            imageView.buildDrawingCache();
            Bitmap bmap = imageView.getDrawingCache();
            byte[] img = getBytes(bmap);

            id = mascotasdb.insertImg(NomM, img);
            if (id < 0) {
                Message.message(this, "Unsuccessfull");
            } else {
                Message.message(this, "Successfully inserted a row");
            }
        }
       else {
            byte[] dummy = new byte[] { (byte)0x1010};
            //if (dummy[0] == (byte)0x1010) Message.message(this,"THXGOD"); VERYMUCHIMPORTNAT
            //Message.message(this,dummy.toString());
            id = mascotasdb.insertImg(NomM,dummy);
            if (id < 0) {
                Message.message(this, "Unsuccessfull");
            } else {
                Message.message(this, "Successfully inserted a row");
            }
        }
        String esp = myEditText.getText().toString();
        if (!esp.isEmpty()) {
            id = mascotasdb.inserttesp(NomM, esp);
            if (id < 0) {
                Message.message(this, "Unsuccessfull");
            } else {
                Message.message(this, "Successfully inserted a row");
            }
        }
        else {
            id = mascotasdb.inserttesp(NomM, "Sin Tratamiento Especial");
            if (id < 0) {
                Message.message(this, "Unsuccessfull");
            } else {
                Message.message(this, "Successfully inserted a row");
            }
        }
        finish();
    }

    public void back(View view){
        finish();
    }
}

