package com.example.appcollector.ui.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appcollector.MainActivity;
import com.example.appcollector.R;
import com.example.appcollector.ui.Interfaces.WebServicesInterface;
import com.example.appcollector.ui.Modelos.Ciudadano;
import com.example.appcollector.ui.Modelos.Token;
import com.example.appcollector.ui.Util.Util;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText tedDocumento, tedPassword;
    TextView btnNuevoUsuario;
    Button btnIniciarSesion;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tedDocumento = findViewById(R.id.etDocumento);
        tedPassword = findViewById(R.id.etPassword);
        btnNuevoUsuario = findViewById(R.id.btnNuevoUsuario);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String doc = tedDocumento.getText().toString();
                String pass = tedPassword.getText().toString();
                Ciudadano oCiudadano = new Ciudadano(null,null,null,
                        null,doc,null,null,null,
                        pass, null, null, null, null);

                AutentificarCiudadano(oCiudadano);
//                Intent i = new Intent(LoginActivity.this, MainActivity.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(i);
            }
        });
        btnNuevoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegistroUsuarioActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

    }


    public void AutentificarCiudadano(Ciudadano oCiudadano){
        String url = Util.url;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();

        WebServicesInterface CondWebServ = retrofit.create(WebServicesInterface.class);
        Call<Token> call = CondWebServ.AutentificarCiudadano(oCiudadano);

        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if(response.isSuccessful()){
                    Token otok = response.body();
                    String mensaje_Respuesta = otok.getcMensajeAut();
                    Toast.makeText(LoginActivity.this, mensaje_Respuesta, Toast.LENGTH_SHORT).show();
                    if(mensaje_Respuesta.equals("INGRESO EXITOSO")){

                        TraerDatosCiudadano(oCiudadano.getcDNICiud().toString());
                    }
                }
                else {
                    Toast.makeText(LoginActivity.this,"NO HAY RESPUESTA", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void llamarActivityMain(){
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    public void TraerDatosCiudadano(String cDNICiud){
        String url = Util.url;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();

        WebServicesInterface CondWebServ = retrofit.create(WebServicesInterface.class);
        Call<Ciudadano> call = CondWebServ.TraerDatosCiudadano(cDNICiud);

        call.enqueue(new Callback<Ciudadano>() {
            @Override
            public void onResponse(Call<Ciudadano> call, Response<Ciudadano> response) {
                if(response.isSuccessful()){
                    Ciudadano oDatosCiudadano = response.body();

                    SharedPreferences preferencias = getSharedPreferences(Util.ARCHIVO_PREFRENCIAS, MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferencias.edit();
                    editor.putString("nCodigoCiud", oDatosCiudadano.getnCodigoCiud());
                    editor.putString("cNombreCiud", oDatosCiudadano.getcNombreCiud());
                    editor.putString("cApePatCiud", oDatosCiudadano.getcApePatCiud());
                    editor.putString("cApeMatCiud", oDatosCiudadano.getcApeMatCiud());
                    editor.putString("cDNICiud", oDatosCiudadano.getcDNICiud());
                    editor.putString("cCelCiud", oDatosCiudadano.getcCelCiud());
                    editor.putString("cNumDirecCiud", oDatosCiudadano.getcNumDirecCiud());
                    editor.putString("nCodigoCalle", oDatosCiudadano.getnCodigoCalle());
                    editor.putString("cNombreCalle", oDatosCiudadano.getcNombreCalle());
                    editor.putString("cDescZona", oDatosCiudadano.getcDescZona());
                    editor.commit();
//                    Toast.makeText(LoginActivity.this, "Guardado en Preferencias", Toast.LENGTH_SHORT).show();

                    llamarActivityMain();

                }
                else {
                    Toast.makeText(LoginActivity.this,"NO HAY RESPUESTA", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Ciudadano> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Desea salir de Truck Collector?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
            builder.show();
        }
        return super.onKeyDown(keyCode, event);
    }
}