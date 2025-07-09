package com.example.usuariosapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText campoNombre, campoEmail;
    Button botonGuardar, verUsuarios;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        campoNombre = findViewById(R.id.campoNombre);
        campoEmail = findViewById(R.id.campoEmail);
        botonGuardar = findViewById(R.id.botonGuardar);
        verUsuarios = findViewById(R.id.verUsuarios);
        dbHelper = new DBHelper(this);

        botonGuardar.setOnClickListener(v -> {
            String nombre = campoNombre.getText().toString();
            String email = campoEmail.getText().toString();
            guardarUsuario(nombre, email);
        });

        verUsuarios.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ListaUsuarios.class));
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void guardarUsuario(String nombre, String email){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre",nombre);
        values.put("email", email);
        db.insert("usuarios", null, values);
        Toast.makeText(this, "Usuario guardado", Toast.LENGTH_SHORT).show();
        campoNombre.setText("");
        campoEmail.setText("");
    }
}