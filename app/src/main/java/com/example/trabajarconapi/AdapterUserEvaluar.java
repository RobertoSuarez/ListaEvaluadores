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

public class AdapterUserEvaluar extends ArrayAdapter<UserEvaluar> {
    public AdapterUserEvaluar(Context context, ArrayList<UserEvaluar> userEvaluar) {
        super(context, 0, userEvaluar);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        UserEvaluar e = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_evaluar_layout, parent, false);
        }

        // traer los componentes
        ImageView imgAvatar = (ImageView) convertView.findViewById(R.id.img_card_evaluar_avatar);
        TextView txtNombre = (TextView)convertView.findViewById(R.id.txt_card_evaluar_nombres);
        TextView txtCargo = (TextView)convertView.findViewById(R.id.txt_card_evaluar_cargo);
        TextView txtGenero = (TextView)convertView.findViewById(R.id.txt_card_evaluar_genero);
        TextView txtSituacion = (TextView)convertView.findViewById(R.id.txt_card_evaluar_situacion);

        if (e.getImagen() == null) {
            imgAvatar.setImageResource(R.drawable.avatar);
        } else {
            imgAvatar.setImageBitmap(e.getImagen());
        }




        txtNombre.setText(e.getNombres());
        txtCargo.setText(e.getCargo());
        txtGenero.setText(e.getGenero());
        txtSituacion.setText(e.getSituacion());

        return convertView;
    }
}
