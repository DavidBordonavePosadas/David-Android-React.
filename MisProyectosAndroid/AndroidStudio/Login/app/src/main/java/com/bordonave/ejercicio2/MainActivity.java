package com.bordonave.ejercicio2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.bordonave.ejercicio2.R;

public class MainActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin, btnCancel;
    private TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel);
        tvMessage = findViewById(R.id.tvMessage);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    tvMessage.setText("Por favor, complete todos los campos");
                    tvMessage.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                    return;
                }

                // Verificar si el username coincide con el password
                if (username.equals(password)) {
                    tvMessage.setText("Acceso correcto");
                    tvMessage.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                } else {
                    tvMessage.setText("Acceso denegado");
                    tvMessage.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Limpiar los campos
                etUsername.setText("");
                etPassword.setText("");
                tvMessage.setText("");
            }
        });
    }
}
