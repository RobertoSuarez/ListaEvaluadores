package com.example.trabajarconapi;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EvaludorActivity extends AppCompatActivity {

    private Evaluador evaluador;
    private RequestQueue cola;
    private ListView lvAEvaluar;
    private AdapterUserEvaluar adapterUserEvaluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaludor);

        cola = Volley.newRequestQueue(this);

        TextView txtEvaludor = (TextView)findViewById(R.id.txt_evaluador_nombre);
        lvAEvaluar = (ListView)findViewById(R.id.lv_a_evaluar);

        ArrayList<UserEvaluar> AEvaluar = new ArrayList<>();
        adapterUserEvaluar = new AdapterUserEvaluar(this, AEvaluar);
        lvAEvaluar.setAdapter(adapterUserEvaluar);


        // Resivimos el objeto que nos envian de MainActivity
        evaluador = (Evaluador) getIntent().getSerializableExtra("evaluador");

        txtEvaludor.setText(evaluador.getNombres());
        getListadoAEvaluar();
    }

    private void getListadoAEvaluar() {
        String url = "https://evaladmin.uteq.edu.ec/ws/listadoaevaluar.php?e="+this.evaluador.getIdevaluador();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray listaaevaluar = response.getJSONArray("listaaevaluar");
                            int len = listaaevaluar.length();

                            for (int i = 0; i < len; i++) {
                                JSONObject jsonUserE = listaaevaluar.getJSONObject(i);
                                UserEvaluar UserE = new UserEvaluar(
                                        jsonUserE.getString("Nombres"),
                                        jsonUserE.getString("cargo"),
                                        jsonUserE.getString("fechafin"),
                                        jsonUserE.getString("fechainicio"),
                                        jsonUserE.getString("genero"),
                                        jsonUserE.getString("id"),
                                        jsonUserE.getString("idevaluado"),
                                        jsonUserE.getString("imgJPG"),
                                        jsonUserE.getString("imgjpg"),
                                        jsonUserE.getString("situacion"),
                                        null
                                );



                                // insertamos a la ui
                                adapterUserEvaluar.add(UserE);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.toString());
                    }
                }
        );

        cola.add(jsonObjectRequest);
    }
}