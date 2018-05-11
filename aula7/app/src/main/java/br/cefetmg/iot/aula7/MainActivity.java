package br.cefetmg.iot.aula7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView text_result;
    Button button_recente;
    Button button_list;
    Button button_atualizar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_recente = (Button) findViewById(R.id.button_recente);

        button_recente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent temperatura_recente = new Intent(getApplicationContext(), TemperaturaRecenteActivity.class);

               startActivity(temperatura_recente);

            }
        });

        button_list = (Button) findViewById(R.id.button_list);

        button_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent temperatura_list = new Intent(getApplicationContext(), TemperaturasActivity.class);

                startActivity(temperatura_list);

            }
        });

        button_atualizar = (Button) findViewById(R.id.button_atualizar);

        button_atualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent temperatura_atualizar = new Intent(getApplicationContext(), TemperaturaAtualizarActivity.class);

                startActivity(temperatura_atualizar);

            }
        });


    }
}
