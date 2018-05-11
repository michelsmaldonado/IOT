package br.cefetmg.iot.aula7;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
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


/**
 * Created by Pós Iot Sistemas de Computação.
 */


public class GetTemperaturas extends AsyncTask<Integer,String,String> {
    TextView text_retorno;
    URL url;
    String retorno="";
    String time, valor;

    public GetTemperaturas(TextView text_retorno){
        this.text_retorno = text_retorno;
        try {
            this.url = new URL("http://35.199.97.143:3000/temperatura");
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


                for (int i = 0; i < leitura.length(); i++) {
                    JSONObject c = leitura.getJSONObject(i);

                    time = c.getString("time");
                    valor = c.getString("valor");
                    retorno += "Time: "+time + " - Valor: " + valor + "\n";
                }

                urlConnection.disconnect();
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }


        return retorno;
    }
    @Override
    protected void onPostExecute(String retorno){
        Log.i("Tarefa 1 - status", "PostExecute");
        text_retorno.setText(retorno);
    }

}