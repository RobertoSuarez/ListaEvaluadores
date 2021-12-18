package com.example.trabajarconapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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

        txtID.setText(e.getIdevaluador());
        txtNombre.setText(e.getNombres());
        txtArea.setText(e.getArea());

        return convertView;
    }
}
