package com.example.vladiz.registro;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
//ESTOS IMPORTS SON NECESARIOS AL MOMENTO DE USAR VOLLEY, SE LOS IMPORTA AL MOMENTO DE CREAR EL CODIGO
// O ANTES SI ES QUE NO ESTA IMPORTADO NOS MADARA UN ERROR CON ALT+ENTER NOS DARA LA OPSION DE IMPORTARLO
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
//↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
import java.util.ArrayList;
import java.util.Vector;
//ES NECESARIO HACER UN IMPLEMENTS DE View.OnClickListener PARA EL MANEJO DE EVENTOS EN BOTONES
public class lista extends AppCompatActivity implements View.OnClickListener{
    //↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
    //SE CREA LA LISTA, EL VECTOR DE PERSONAS Y EL ADAPTADOR DE USUARIOS
    ListView lista;                             //LISTA PARA MOSTRAR LOS DATOS
    Vector<String> personas=new Vector<String>();   //VECTOR DONDE SE ALMACENARA LOS DATOS DE LA PERSONA
    ArrayAdapter<String> usuarios;              // ADAPTADOR DONDE SE ALMACENARA EL VECTOR PARA LUEGO MOSTRARLO EN LA LISTA
    Button bActualizar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        bActualizar=(Button)findViewById(R.id.bActualizar);
        bActualizar.setOnClickListener(this);
        lista=(ListView)findViewById(R.id.lvLista);
        usuarios= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, personas); //SE CREA EL ADAPTADOR Y SE PONE UN MODELO DE LISTA SIMPLE, ASI TAMBIEN SE AGREGA EL VECTOR
        llenar();                               //CON ESTE METODO LLENAREMOS LA LISTA AL INICIO DE LA APLICACION PARA MOSTRARLA POR SI LA BASE DE DATOS YA ESTUVIESE LLENA
    }

    @Override
    public void onClick(View v) { //METODO PARA EL View.OnClickListener
        personas.removeAllElements(); // COMO EL BOTON ES PARA ACTUALIZAR SE DEBE VACIAR EL VECTOR PERSONAS
        usuarios.addAll(personas); // PARA LUEGO INCLUIRLO EN EL ADAPTADOR DE USUARIOS
        lista.setAdapter(usuarios); //Y LUEGO INCLUIRLO A LA LISTA A MOSTRARSE
        llenar();                   // CON ESTE METODO VOLVEREMOS A LLENAR LA LISTA
    }


public void llenar(){ //METODO PARA LENAR LA LISTA
// ACA ES DONDE SE HACE USO DE LA LIBRERIA VOLLEY
    RequestQueue cola = Volley.newRequestQueue(this);                  //SE CREA UNA COLA PARA ALMACENAR LAS PETICIONES
    String url = "http://169.254.91.218:8080/webservice/consulta.php";//ACA SE MANDA LA DIRECCION DONDE ESTA GUARDADA EL PHP QUE NOS HARA UNA CONSULTA DE TIPO QUERI A LA BASE DE DATOS DEVOLVIENDONOS UN JASON
                                                                        // USUALMENTE XAMMP TIENE CONFLICTOS CON LOS PUERTOS 80 TUVE QUE CAMBIAR A 8080 LUEGO VERIFICAR LA IP Q SE MANEJA EN TU MAQUINA ES DONDE ESTARA LA BASE DE DATOS
    StringRequest req = new StringRequest(url, new Response.Listener<String>() { // ACA SE MANDA LA VARIABLE  url DONDE SE ALMACENA LO QUE VAYAMOS A RECIBIR ASI TAMBIEN SE DECLARA LA ESTRUCTURA QUE ALMACENARA LOQUE RECIBAMOS
        @Override
        public void onResponse(String response) { // LO QUE NOS DEVUELVE ES UN RESPONSE AHI SE HA GUARDADO NUESTRA PETICION LO QUE SE LLAMA CONSUMIR
            //PARTE LOGICA PARA MANEJAR LA LISTA Y ASI LLENARLA PARA LUEGO MOSTRARLA SE HACE UN TRY CATCH POR DEFECTO PARA ASI MANEJAR LOS ERRORES QUE PUEDAN DEVOLVERNOS
            try {
                int i=0,es=0;
                response=response.replace("][",","); //SE INTERCAMBIA LOS VALORES DEVUELTOS..LOS CORCHETES Y COMAS POR COMAS
                JSONArray ja= new JSONArray(response); //SE ALMACENA EL RESPONSE EN UN ARRAY
                es=ja.length();                     //GUARDO EL TAMAÑO DEL ARRAY
                //CON UN WHILE CONTROLO EL ALAMACENADO EN EL VECTOR
                while(i<es){
                    personas.add(ja.getString(i));  // VOY LLENANDO EL VECTOR
                    i++;
                }
                usuarios.addAll(personas);          //LUEGO DE LLENADO EL VECTOR LO MANDO AL ADAPTADOR
                //EL CODIGO SIGUIENTE ES PARA ERRORES Y SU RESPECTIVO MANEJO RECOMIENDO AVERIGUAR SOBRE MANEJO DE TRY CATCH
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    },
            //ACA DEVUELVE Y MANEJA LOS ERRORES QUE PODAMOS TENER AL MOMENTO DE MANEJAR LA LIBRERIA VOLLEY
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            }
    );
    cola.add(req);                                  //SE ALMACENA EL req EN LA COLA DE VOLLEY
    lista.setAdapter(usuarios);                     //ALMACENAMOS EL ADAPTADOR EN LA LISTA EN ESTE CASO LA LISTA YA ESTARA LLENA
}


}


