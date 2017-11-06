package edu.tecii.android.app9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    EditText edtxt1, edtxt2;
    Button btn2, btn3;
    String indiceLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        edtxt1 = (EditText)findViewById(R.id.editText);
        edtxt2 = (EditText)findViewById(R.id.editText2);
        btn2 = (Button)findViewById(R.id.button2);
        btn3 = (Button)findViewById(R.id.button3);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact contacto = new Contact();
                contacto.Nombre = edtxt1.getText().toString();
                contacto.Apellido = edtxt2.getText().toString();
                MainActivity.sql.insertarContacto(contacto);
            }
        });
    }
}
