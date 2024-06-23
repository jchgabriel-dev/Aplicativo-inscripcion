package com.example.negocioseletronicos;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.negocioseletronicos.db.DbUsuario;
import com.example.negocioseletronicos.entidades.Usuarios;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerUsuario extends AppCompatActivity {
    EditText editNombre, editTelefono, editCorreo;
    Usuarios usuario;
    FloatingActionButton fabEditar;
    FloatingActionButton fabEliminar;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_usuario);
        getSupportActionBar().setTitle("Vista de usuarios");

        editNombre = findViewById(R.id.editNombre);
        editTelefono = findViewById(R.id.editTelefono);
        editCorreo = findViewById(R.id.editCorreo);
        fabEditar = findViewById(R.id.fabEditar);
        fabEliminar = findViewById(R.id.fabEliminar);

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

        DbUsuario dbUsuario = new DbUsuario(VerUsuario.this);
        usuario = dbUsuario.VerUsuario(id);

        if(usuario != null) {
            editNombre.setText(usuario.getNombre());
            editTelefono.setText(usuario.getTelefono());
            editCorreo.setText(usuario.getCorreo_electronico());

            editNombre.setInputType(InputType.TYPE_NULL);
            editTelefono.setInputType(InputType.TYPE_NULL);
            editCorreo.setInputType(InputType.TYPE_NULL);
        }

        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerUsuario.this, EditarUsuario.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerUsuario.this);
                builder.setMessage("¿Desea eliminar este registro?").setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(dbUsuario.eliminarUsuario(id)){
                            lista();
                        }
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
            }
        });
    }
    private void lista(){
        Intent intent = new Intent(this, VerUsuario.class);
        startActivity(intent);
    }
}