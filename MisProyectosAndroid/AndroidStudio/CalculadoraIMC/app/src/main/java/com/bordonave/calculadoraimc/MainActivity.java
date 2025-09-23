package com.bordonave.calculadoraimc;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText d_peso;
    EditText d_estatura;
    Button calcular;
    TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //recuperar el vinculo con la interfaz
        d_peso = findViewById(R.id.peso);
        d_estatura = findViewById(R.id.estatura);
        calcular = findViewById(R.id.calcular);
        resultado = findViewById(R.id.res);
        //imc=peso/estatura^2

        calcular.setOnClickListener(view -> {
            String peso = d_peso.getText().toString();
            String estatura = d_estatura.getText().toString();
            if (!peso.isEmpty() && !estatura.isEmpty()) {
                double pesoValue = Double.parseDouble(peso);
                double estaturaValue = Double.parseDouble(estatura);
                double imc = pesoValue / (estaturaValue * estaturaValue);
                resultado.setText(String.format("Tu IMC es: %.2f", imc));
            } else {
                resultado.setText("Por favor, ingresa ambos valores.");
            }
        });

    }
}
