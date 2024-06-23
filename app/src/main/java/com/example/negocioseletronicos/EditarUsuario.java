package com.example.negocioseletronicos;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.negocioseletronicos.db.DbUsuario;
import com.example.negocioseletronicos.entidades.Usuarios;

public class EditarUsuario extends AppCompatActivity {
    EditText editNombre, editTelefono, editCorreo;
    Button btnRegistrar;
    boolean correcto = false;
    Usuarios usuario;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);
        getSupportActionBar().setTitle("Edicion de usuarios");

        editNombre = findViewById(R.id.editNombre);
        editTelefono = findViewById(R.id.editTelefono);
        editCorreo = findViewById(R.id.editCorreo);
        btnRegistrar = findViewById(R.id.btnRegistrar);


        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DbUsuario dbUsuario = new DbUsuario(EditarUsuario.this);
        usuario = dbUsuario.VerUsuario(id);

        if(usuario != null) {
            editNombre.setText(usuario.getNombre());
            editTelefono.setText(usuario.getTelefono());
            editCorreo.setText(usuario.getCorreo_electronico());
        }

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editNombre.getText().toString().equals("") && !editTelefono.getText().toString().equals("")) {
                    correcto = dbUsuario.editarUsuario(id, editNombre.getText().toString(), editTelefono.getText().toString(), editCorreo.getText().toString());
                    if(correcto){
                        Toast.makeText(EditarUsuario.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(EditarUsuario.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditarUsuario.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void verRegistro(){
        Intent intent = new Intent(this, VerUsuario.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}