package com.example.negocioseletronicos.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.negocioseletronicos.ListaUsuario;
import com.example.negocioseletronicos.R;
import com.example.negocioseletronicos.VerUsuario;
import com.example.negocioseletronicos.entidades.Usuarios;

import java.util.ArrayList;

public class ListaUsuariosAdapter extends RecyclerView.Adapter<ListaUsuariosAdapter.UsuarioViewHolder> {
    ArrayList<Usuarios> listaUsuarios;

    public ListaUsuariosAdapter(ArrayList<Usuarios> listaUsuarios){
        this.listaUsuarios = listaUsuarios;
    }

    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_usuario, null,false);
        return new UsuarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder holder, int position) {
        holder.textItemNombre.setText(listaUsuarios.get(position).getNombre());
        holder.textItemTelefono.setText(listaUsuarios.get(position).getTelefono());
        holder.textItemCorreo.setText(listaUsuarios.get(position).getCorreo_electronico());
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public class UsuarioViewHolder extends RecyclerView.ViewHolder {
        TextView textItemNombre, textItemTelefono, textItemCorreo;

        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);

            textItemNombre = itemView.findViewById(R.id.textItemNombre);
            textItemTelefono = itemView.findViewById(R.id.textItemTelefono);
            textItemCorreo = itemView.findViewById(R.id.textItemCorreo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerUsuario.class);
                    intent.putExtra("ID", listaUsuarios.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });

        }
    }
}
