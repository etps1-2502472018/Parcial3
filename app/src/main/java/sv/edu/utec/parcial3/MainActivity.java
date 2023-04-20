package sv.edu.utec.parcial3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.utec.parcial3.Helper.adminsql;

public class MainActivity extends AppCompatActivity {

    EditText edtNom,edtAp,edtTel,edtEmail;
    Button insertar,borrar,actualizar,buscarN,buscarA,buscarE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNom = findViewById(R.id.edtNombre);
        edtAp = findViewById(R.id.edtApellido);
        edtTel= findViewById(R.id.edtTelefono);
        edtEmail= findViewById(R.id.edtEmail);

        insertar= findViewById(R.id.btnInsertar);
        borrar= findViewById(R.id.btnBorrar);
        actualizar= findViewById(R.id.btnActualizar);
        buscarN= findViewById(R.id.btnBuscarN);
        buscarA = findViewById(R.id.btnBuscarA);
        buscarE = findViewById(R.id.btnBuscarE);

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adminsql admin = new adminsql(getApplicationContext(),"parcial",null,1);
                SQLiteDatabase bd = admin.getWritableDatabase();

                String nom=edtNom.getText().toString();
                String ape=edtAp.getText().toString();
                String tel=edtTel.getText().toString();
                String ema=edtEmail.getText().toString();

                ContentValues info = new ContentValues();
                info.put("Nombre",nom);
                info.put("Apellido",ape);
                info.put("Telefono",tel);
                info.put("Email",ema);

                bd.insert("contactos",null,info);
                bd.close();

                Toast.makeText(MainActivity.this, "Insert exitoso", Toast.LENGTH_SHORT).show();
            }
        });

        //Nota: Solo ingresar el nombre para borrar
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adminsql admin = new adminsql(getApplicationContext(),"parcial",null,1);
                SQLiteDatabase bd = admin.getWritableDatabase();

                String nom=edtNom.getText().toString();

                int cat=bd.delete("contactos", "Nombre='"+nom+"'",null);
                bd.close();

                edtNom.setText("");
                edtAp.setText("");
                edtTel.setText("");
                edtEmail.setText("");

                if(cat==1){
                    Toast.makeText(MainActivity.this, "Contacto borrado con exito", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(MainActivity.this, "Error al borrar contacto", Toast.LENGTH_SHORT).show();
                }

            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adminsql admin = new adminsql(getApplicationContext(),"parcial",null,1);
                SQLiteDatabase bd = admin.getWritableDatabase();

                String nom=edtNom.getText().toString();
                String ape=edtAp.getText().toString();
                String tel=edtTel.getText().toString();
                String ema=edtEmail.getText().toString();

                ContentValues info = new ContentValues();
                info.put("Nombre",nom);
                info.put("Apellido",ape);
                info.put("Telefono",tel);
                info.put("Email",ema);

                int cat=bd.update("contactos",info, "Nombre='"+nom+"'",null);
                bd.close();

                if(cat==1){
                    Toast.makeText(MainActivity.this, "Contacto modificado con exito", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(MainActivity.this, "Error al modificar el contacto", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buscarN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adminsql admin = new adminsql(getApplicationContext(),"parcial",null,1);
                SQLiteDatabase bd = admin.getWritableDatabase();

                String nom=edtNom.getText().toString();

                Cursor filas =bd.rawQuery("select Nombre,Apellido,Telefono,Email from contactos " +
                        "where Nombre='"+nom+"'", null);

                if (filas.moveToFirst()){
                    edtNom.setText(filas.getString(0));
                    edtAp.setText(filas.getString(1));
                    edtTel.setText(filas.getString(2));
                    edtEmail.setText(filas.getString(3));
                }else{
                    Toast.makeText(MainActivity.this, "Contacto no encontrado", Toast.LENGTH_SHORT).show();
                }
                bd.close();

            }
        });

        buscarA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adminsql admin = new adminsql(getApplicationContext(),"parcial",null,1);
                SQLiteDatabase bd = admin.getWritableDatabase();

                String ape=edtAp.getText().toString();

                Cursor filas =bd.rawQuery("select Nombre,Apellido,Telefono,Email from contactos " +
                        "where Apellido='"+ape+"'", null);

                if (filas.moveToFirst()){
                    edtNom.setText(filas.getString(0));
                    edtAp.setText(filas.getString(1));
                    edtTel.setText(filas.getString(2));
                    edtEmail.setText(filas.getString(3));
                }else{
                    Toast.makeText(MainActivity.this, "Contacto no encontrado", Toast.LENGTH_SHORT).show();
                }
                bd.close();

            }
        });

        buscarE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adminsql admin = new adminsql(getApplicationContext(),"parcial",null,1);
                SQLiteDatabase bd = admin.getWritableDatabase();

                String ema=edtEmail.getText().toString();

                Cursor filas =bd.rawQuery("select Nombre,Apellido,Telefono,Email from contactos " +
                        "where Email='"+ema+"'", null);

                if (filas.moveToFirst()){
                    edtNom.setText(filas.getString(0));
                    edtAp.setText(filas.getString(1));
                    edtTel.setText(filas.getString(2));
                    edtEmail.setText(filas.getString(3));
                }else{
                    Toast.makeText(MainActivity.this, "Contacto no encontrado", Toast.LENGTH_SHORT).show();
                }
                bd.close();

            }
        });

    }
}