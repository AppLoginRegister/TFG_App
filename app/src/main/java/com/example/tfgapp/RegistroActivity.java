package com.example.tfgapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {

    EditText editUsuario, editPassword, editCorreo, editValidar;
    Button btn;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        editUsuario = findViewById(R.id.editTextRegistrarUsuario);
        editPassword = findViewById(R.id.editTextRegistrarPassword);
        editCorreo = findViewById(R.id.editTextRegistrarCorreo);
        editValidar = findViewById(R.id.editTextRegistrarValidarPassword);
        btn = findViewById(R.id.buttonRegistro);
        textView = findViewById(R.id.textViewExisteUsuario);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = editUsuario.getText().toString();
                String password = editPassword.getText().toString();
                String correo = editCorreo.getText().toString();
                String validar = editValidar.getText().toString();

                DataBase dataBase = new DataBase(getApplicationContext(), "TFG APP", null, 1);

                if (usuario.length() == 0 || password.length() == 0 || correo.length() == 0 || validar.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Por favor, completa todos los campos!", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.compareTo(validar) == 0) {
                        if (isValid(password)) {
                            dataBase.registro(usuario, password, correo);
                            Toast.makeText(getApplicationContext(), "Registro completado!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegistroActivity.this, LoginActivity.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "La constraseña debe contener al menos 1 letra, 1 dígito y un símbolo.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Password y Validar Password no coinciden!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistroActivity.this, LoginActivity.class));
            }
        });
    }

    public static boolean isValid (String passwordhere) {
        int f1=0, f2=0, f3=0;

        if (passwordhere.length() < 6) {
            return false;
        } else {
            // Cadena de letras
            for (int p=0; p<passwordhere.length(); p++){
                if (Character.isLetter(passwordhere.charAt(p))) {
                    f1=1;
                }
            }
            // Cadena de Dígitos
            for (int r=0; r<passwordhere.length(); r++){
                if (Character.isDigit(passwordhere.charAt(r))){
                    f2=1;
                }
            }

            for (int s=0; s<passwordhere.length(); s++){
                char c = passwordhere.charAt(s);
                if(c>33 && c<=46 || c==64){
                    f3=1;
                }
            }

            if (f1==1 && f2==1 && f3==1)
                return true;
            return false;
        }
    }
}