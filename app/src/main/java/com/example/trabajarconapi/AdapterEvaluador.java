package com.example.trabajarconapi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class AdapterEvaluador extends ArrayAdapter<Evaluador> {
    public AdapterEvaluador(Context context, ArrayList<Evaluador> evaluadors) {
        super(context, 0, evaluadors);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Evaluador e = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_layout, parent, false);
        }

        TextView txtID = (TextView)convertView.findViewById(R.id.txtID);
        TextView txtNombre = (TextView) convertView.findViewById(R.id.itemNombre);
        TextView txtArea = (TextView)convertView.findViewById(R.id.txtArea);

        ImageView img = (ImageView)convertView.findViewById(R.id.imageViewAvatar);


        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {

                    URL urlimg = new URL(e.getImgJPG());
                    Bitmap bitmap = BitmapFactory.decodeStream(urlimg.openConnection().getInputStream());
                    e.setImagen(bitmap);
                    img.setImageBitmap(bitmap);

                } catch (Exception ex) {
                    System.out.println(ex.toString());
                    e.setImagen(null);
                }
            }
        });



        if (e.getImagen() == null) {
            img.setImageResource(R.drawable.avatar);
            //thread.start();
        } else {
            img.setImageBitmap(e.getImagen());
        }


//        // Mostramos la imagen
//        try {
//            HttpsTrustManager.allowAllSSL();
//            URL urlimg = new URL(e.getImgJPG());
//            Bitmap bitmap = BitmapFactory.decodeStream(urlimg.openConnection().getInputStream());
//            img.setImageBitmap(bitmap);
//        } catch (Exception exception) {
//            System.out.println(exception.toString());
//            img.setBackgroundResource(R.drawable.avatar);
//        }


        txtID.setText(e.getIdevaluador());
        txtNombre.setText(e.getNombres());
        txtArea.setText(e.getArea());

        return convertView;
    }
}
