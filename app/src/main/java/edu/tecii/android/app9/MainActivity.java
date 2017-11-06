package edu.tecii.android.app9;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lstVw;
    Button btn1;
    static ArrayList<Contact> lista = new ArrayList<>();
    static SqlHelper sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstVw = (ListView)findViewById(R.id.listView);
        btn1 = (Button)findViewById(R.id.button);
        sql = new SqlHelper(getApplicationContext(), null);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }

    public void leerLista() {
        lista = sql.obtenerContactos();
    }

    public void onResume() {
        super.onResume();
        try {
            leerLista();
            actualizarLista();
        } catch (Exception e) {}
    }

    public void actualizarLista() {

        ArrayList<String> aux = new ArrayList<>();
        for (Contact c : lista) {
            aux.add(c.Nombre);
        }
        ArrayAdapter adapter =  new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, aux);
        lstVw.setAdapter(adapter);
    }
}
