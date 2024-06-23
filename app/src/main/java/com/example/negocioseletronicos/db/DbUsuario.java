package com.example.negocioseletronicos.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.negocioseletronicos.entidades.Usuarios;

import java.util.ArrayList;

public class DbUsuario extends DbHelper {
    Context context;

    public DbUsuario(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarUsuario(String nombre, String telefono, String correo_electronico){
        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("telefono", telefono);
            values.put("correo_electronico", correo_electronico);

            id = db.insert(TABLE_USUARIO, null, values);

        } catch (Exception e) {
            e.toString();
        }
        return id;
    }

    public ArrayList<Usuarios> MostrarUsuarios() {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Usuarios> listaUsuarios = new ArrayList<>();
        Usuarios usuario = null;
        Cursor cursorUsuario = null;

        cursorUsuario = db.rawQuery("SELECT * FROM " + TABLE_USUARIO, null);
        if(cursorUsuario.moveToFirst()){
            do{
                usuario = new Usuarios();
                usuario.setId(cursorUsuario.getInt(0));
                usuario.setNombre(cursorUsuario.getString(1));
                usuario.setTelefono(cursorUsuario.getString(2));
                usuario.setCorreo_electronico(cursorUsuario.getString(3));
                listaUsuarios.add(usuario);
            }while(cursorUsuario.moveToNext());
        }
        cursorUsuario.close();
        return listaUsuarios;

    }

    public Usuarios VerUsuario(int id) {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Usuarios usuario = null;
        Cursor cursorUsuario = null;

        cursorUsuario = db.rawQuery("SELECT * FROM " + TABLE_USUARIO + " WHERE id = " + id + " LIMIT 1", null);
        if(cursorUsuario.moveToFirst()){
            usuario = new Usuarios();
            usuario.setId(cursorUsuario.getInt(0));
            usuario.setNombre(cursorUsuario.getString(1));
            usuario.setTelefono(cursorUsuario.getString(2));
            usuario.setCorreo_electronico(cursorUsuario.getString(3));

        }
        cursorUsuario.close();
        return usuario;

    }

    public boolean editarUsuario(int id, String nombre, String telefono, String correo_electronico){
        boolean correcto = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_USUARIO + " SET nombre = '" + nombre + "', telefono = '" + telefono + "', correo_electronico = '" + correo_electronico + "' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception e) {
            e.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }

    public boolean eliminarUsuario(int id) {
        boolean correcto = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_USUARIO + " WHERE id = " + id);
            correcto = true;
        } catch (Exception e) {
            e.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }



}
