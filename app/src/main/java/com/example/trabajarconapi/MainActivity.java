package com.example.trabajarconapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.security.cert.Certificate;

public class MainActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private TextView textInfo;
    private ListView lvEvaluadores;
    private ArrayList<Evaluador> evaluadores;
    private AdapterEvaluador adapterEvaluador;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Permitir todos los sertificados ssl
        HttpsTrustManager.allowAllSSL();

        textInfo = (TextView)findViewById(R.id.textInfo);
        lvEvaluadores = (ListView)findViewById(R.id.lvEvaluadores);

        requestQueue = Volley.newRequestQueue(this);

        // Iniciando la lista de evaluadores
        evaluadores = new ArrayList<Evaluador>();
        // Adaptando a los evaluadores
        adapterEvaluador = new AdapterEvaluador(this, evaluadores);

        lvEvaluadores.setAdapter(adapterEvaluador);
        lvEvaluadores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Evaluador e = adapterEvaluador.getItem(position);
                TextView selecionado = (TextView) findViewById(R.id.txtSelecionado);
                selecionado.setText(e.getNombres());
            }
        });

        loadEvaluadores();

    }

    private void loadEvaluadores() {
        String url = "https://gorest.co.in/public/v1/users";
        String urlEvaluadores = "https://evaladmin.uteq.edu.ec/ws/listadoevaluadores.php";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                urlEvaluadores,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray data = response.getJSONArray("listaaevaluador");
                            int len = data.length();
                            // Iteramos los datos de cada objeto.
                            for (int i = 0; i < len; i++) {
                                JSONObject evaluador = data.getJSONObject(i);
                                Evaluador eva = new Evaluador(
                                        evaluador.getString("idevaluador"),
                                        evaluador.getString("area"),
                                        evaluador.getString("imgJPG"),
                                        evaluador.getString("imgjpg"),
                                        evaluador.getString("nombres")
                                );
                                adapterEvaluador.add(eva);
                                //textInfo.append(evaluador.getString("nombres")+"\n");

                            }
                        } catch (JSONException e) {
                            System.out.println(e.toString());
                        }

                        System.out.println("fin de petición");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.toString());
                    }
                }
        );

        requestQueue.add(jsonObjectRequest);
    }




}