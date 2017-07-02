package com.example.vladiz.registro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//ESTOS IMPORTS SON NECESARIOS AL MOMENTO DE USAR VOLLEY, SE LOS IMPORTA AL MOMENTO DE CREAR EL CODIGO
// O ANTES SI ES QUE NO ESTA IMPORTADO NOS MADARA UN ERROR CON ALT+ENTER NOS DARA LA OPSION DE IMPORTARLO
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
//↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
// implementamos View.OnClickListener para los eventos click en botones
public class usuario extends AppCompatActivity implements View.OnClickListener {
    // SE CREAN LOS EditText DONDE SE RECOGERAN LOS DATOS
    EditText cedula;
    EditText nombre;
    EditText fechanac;
    EditText direccion;
    Button bEnviar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        bEnviar=(Button)findViewById(R.id.bEnviar); //ENLAZA BOTON CON XML
        bEnviar.setOnClickListener(this);           //EVENTO DEL BOTON

        cedula=(EditText)findViewById(R.id.etCedula);   //SE ENLAZA LOS EditText CON LOS DEL XML
        nombre=(EditText)findViewById(R.id.etNombre);   //     /////
        fechanac=(EditText)findViewById(R.id.etFechaNac);//     /////
        direccion=(EditText)findViewById(R.id.etDireccion);//     /////
    }

    @Override
    public void onClick(View v) {                          //METODO PARA MANEJO DE EVENTOS CLICK
        RequestQueue cola = Volley.newRequestQueue(this);   //SE CREA UNA COLA PARA ALMACENAR LAS PETICIONES

        //ACA SE MANDA UNA VES MAS LA DIRECCION DONDE ESTA EL PHP EN ESTE CASO EL PHP NECESITA PARAMETROS LOS CUALES LOS RECOGE
        //DE LOS EditText
        String url = "http://169.254.91.218:8080/webservice/registro.php?" +
                "cedula="+cedula.getText()+
                "&nombre="+nombre.getText()+
                "&fechanac="+fechanac.getText()+
                "&direccion="+direccion.getText();
        //EN ESTE CASO NO NECESITAMOS MANIPULAR EL CODIGO ASI QUE SOLO SE ALMACENARA EN LA COLA DEL VOLLEY
        StringRequest req = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );
        cola.add(req); //SE ALMACENA EL req
        Toast toast = Toast.makeText(getApplicationContext(), "Usuario Añadido", Toast.LENGTH_SHORT); //UN MENSAJE QUE DICE QUE SE ADJUNTO A LA PERSONA

        toast.show();
        //Y AL MOMENTO VOLVEMOS A LA ACTIVITY PRINCIPAL
        Intent i = new Intent(this, MainActivity.class );
        startActivity(i);
    }
}
