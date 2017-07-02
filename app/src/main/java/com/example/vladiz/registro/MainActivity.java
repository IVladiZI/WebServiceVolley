package com.example.vladiz.registro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
// implementamos View.OnClickListener para los eventos click en botones
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button bLista;
    Button bRegustroUsuario;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bLista=(Button) findViewById(R.id.bLista); //SE ENLAZA EL BOTON CON EL BOTON DEL XML
        bRegustroUsuario=(Button) findViewById(R.id.bRegistrar);//    ////
        bLista.setOnClickListener(this);                        //SE DA UN EVENTO AL BOTON
        bRegustroUsuario.setOnClickListener(this);              //   /////
    }

//MANEJO DE LOS EVENTOS
    public void onClick(View v) {
        //VERIFICO QUE id FUE PULSADA ASI PODEMOS HACER UNA ACCION EN BASE AL BOTON PRESIONADO
        if (R.id.bLista == v.getId()){
            Intent i = new Intent(this, lista.class );
            startActivity(i);
        }
        if (R.id.bRegistrar == v.getId()){
            Intent i = new Intent(this, usuario.class );
            startActivity(i);
        }
    }
}
