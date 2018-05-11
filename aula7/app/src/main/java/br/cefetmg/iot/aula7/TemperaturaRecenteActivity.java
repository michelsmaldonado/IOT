package br.cefetmg.iot.aula7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TemperaturaRecenteActivity extends AppCompatActivity {

    TextView text_result;
    GetTemperaturaRecente req;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperatura_recente);

        text_result = (TextView) findViewById(R.id.text_result);
        req = new GetTemperaturaRecente(text_result);
        req.execute();
    }

    @Override
    protected void onPause() {
        super.onPause();
        req.cancel(true);
    }
}
