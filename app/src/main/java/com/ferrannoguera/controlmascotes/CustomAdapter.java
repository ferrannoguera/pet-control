package com.ferrannoguera.controlmascotes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ferry on 05/06/2016.
 */
class CustomAdapter extends ArrayAdapter<String> {

    MascotasDBAdapter mascotasdb;

    public CustomAdapter(Context context, String[] petnames) {
        super(context,R.layout.custom_row, petnames);
        mascotasdb = new MascotasDBAdapter(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater myInflater = LayoutInflater.from(getContext());
        View CustomView = myInflater.inflate(R.layout.custom_row, parent, false);

        String name = getItem(position);
        TextView myText = (TextView) CustomView.findViewById(R.id.myText);


        myText.setText(name);
        byte[] aux = mascotasdb.getimg(name);

        if (aux[0] == (byte)0x1010) {
            ImageView myImage = (ImageView)CustomView.findViewById(R.id.myImage);
            myImage.setVisibility(View.INVISIBLE);
        }
        else {
            Bitmap bmap = BitmapFactory.decodeByteArray(aux, 0, aux.length);
            ImageView myImage = (ImageView) CustomView.findViewById(R.id.myImage);
            myImage.setImageBitmap(bmap);
        }
        return CustomView;
    }
}
