package br.cefetmg.iot.aula7;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by Pós Iot Sistemas de Computação.
 */


public class GetTemperaturaRecente extends AsyncTask<Integer,String,String> {
    TextView text_retorno;
    URL url;
    String retorno;
    String time, valor;

    public GetTemperaturaRecente(TextView text_retorno){
        this.text_retorno = text_retorno;
        try {
            this.url = new URL("http://35.199.97.143:3000/temperatura/recente");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }



    @Override
    protected void onPreExecute(){
        Log.i("Tarefa 1 - status", "PreExecute");
        text_retorno.setText("iniciando");
    }
    @Override
    protected String doInBackground(Integer... params) {
        Log.i("Tarefa 1 - status", "InBackground");
            while (true) {

                try {

                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                    StringBuilder builder = new StringBuilder();

                    String inputString;
                    while ((inputString = bufferedReader.readLine()) != null) {
                        builder.append(inputString);
                    }


                    JSONArray leitura = new JSONArray(builder.toString());

                    time = leitura.getJSONObject(0).getString("time");
                    valor = leitura.getJSONObject(0).getString("valor");
                    retorno = "Time: " + time + " - Valor: " + valor + "\n";

                    publishProgress(retorno);

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    urlConnection.disconnect();
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

                if(isCancelled())
                    break;

            }
        return null;
    }

    protected void onProgressUpdate(String... params){
        Log.i("Tarefa 1 - status", "ProgressUpdate");
        text_retorno.setText(params[0]);

    }

}