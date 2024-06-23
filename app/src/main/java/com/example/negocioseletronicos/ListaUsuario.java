package com.example.negocioseletronicos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.negocioseletronicos.adaptadores.ListaUsuariosAdapter;
import com.example.negocioseletronicos.db.DbUsuario;
import com.example.negocioseletronicos.entidades.Usuarios;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListaUsuario extends AppCompatActivity {
    RecyclerView listaUsuario;
    ArrayList<Usuarios> listaArrayUsuarios;
    FloatingActionButton btnRegistrarUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuario);
        getSupportActionBar().setTitle("Lista de usuarios");

        listaUsuario = findViewById(R.id.listaUsuario);
        listaUsuario.setLayoutManager(new LinearLayoutManager(this));
        btnRegistrarUsuario = findViewById(R.id.btnRegistrarUsuario);

        DbUsuario dbUsuario = new DbUsuario(ListaUsuario.this);
        listaArrayUsuarios = new ArrayList<>();

        ListaUsuariosAdapter adapter = new ListaUsuariosAdapter(dbUsuario.MostrarUsuarios());
        listaUsuario.setAdapter(adapter);

        btnRegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaUsuario.this, RegistroUsuario.class);
                startActivity(intent);
            }
        });
    }
}