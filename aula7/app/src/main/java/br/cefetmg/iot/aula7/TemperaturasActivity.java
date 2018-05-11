package br.cefetmg.iot.aula7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TemperaturasActivity extends AppCompatActivity {

    TextView text_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperaturas);

        text_result = (TextView) findViewById(R.id.text_result);
        GetTemperaturas req = new GetTemperaturas(text_result);
        req.execute();
    }
}
