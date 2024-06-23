package com.example.negocioseletronicos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.negocioseletronicos.db.DbUsuario;

public class RegistroUsuario extends AppCompatActivity {
    EditText editNombre, editTelefono, editCorreo;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        getSupportActionBar().setTitle("Registro de usuarios");

        editNombre = findViewById(R.id.editNombre);
        editTelefono = findViewById(R.id.editTelefono);
        editCorreo = findViewById(R.id.editCorreo);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DbUsuario dbUsuario = new DbUsuario(RegistroUsuario.this);
                long id = dbUsuario.insertarUsuario(editNombre.getText().toString(),
                        editTelefono.getText().toString(),
                        editCorreo.getText().toString());
                if(id > 0) {
                    Toast.makeText(RegistroUsuario.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                    limpiar();
                } else {
                    Toast.makeText(RegistroUsuario.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void limpiar() {
        editNombre.setText("");
        editTelefono.setText("");
        editCorreo.setText("");
    }
}