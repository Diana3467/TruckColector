package com.example.appcollectorcond.ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.appcollectorcond.MainActivity;
import com.example.appcollectorcond.R;
import com.example.appcollectorcond.ui.Interfaces.WebServicesInterface;
import com.example.appcollectorcond.ui.Modelos.Conductor;
import com.example.appcollectorcond.ui.Modelos.Token;
import com.example.appcollectorcond.ui.Util.Util;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText tedDocumento, tedPassword;
    Button btnIniciarSesion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tedDocumento = findViewById(R.id.etDocumento);
        tedPassword = findViewById(R.id.etPassword);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String doc = tedDocumento.getText().toString();
                String pass = tedPassword.getText().toString();
                Conductor oConductor = new Conductor(null,null,null,
                                                    null,doc,null,null,null,
                                                    null, null,pass,null);
                AutentificarConductor(oConductor);

            }
        });
    }

    public void AutentificarConductor(Conductor oConductor){
        String url = Util.url;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();

        WebServicesInterface CondWebServ = retrofit.create(WebServicesInterface.class);
        Call<Token> call = CondWebServ.AutentificarConductor(oConductor);

        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if(response.isSuccessful()){
                    Token otok = response.body();
                    String mensaje_Respuesta = otok.getcMensajeAut();
                    Toast.makeText(LoginActivity.this, mensaje_Respuesta, Toast.LENGTH_SHORT).show();
                    if(mensaje_Respuesta.equals("INGRESO EXITOSO")){
                        llamarActivityMain();
                        TraerDatosConductor(oConductor.getcDNICond().toString());
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

    public void TraerDatosConductor(String cDNICond){
        String url = Util.url;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();

        WebServicesInterface CondWebServ = retrofit.create(WebServicesInterface.class);
        Call<Conductor> call = CondWebServ.TraerDatosConductor(cDNICond);

        call.enqueue(new Callback<Conductor>() {
            @Override
            public void onResponse(Call<Conductor> call, Response<Conductor> response) {
                if(response.isSuccessful()){
                    Conductor oDatosConductor = response.body();

                    SharedPreferences preferencias = getSharedPreferences(Util.ARCHIVO_PREFRENCIAS, MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferencias.edit();
                    editor.putString("nCodigoCond", oDatosConductor.getnCodigoCond());
                    editor.putString("cNombreCond", oDatosConductor.getcNombreCond());
                    editor.putString("cApePatCond", oDatosConductor.getcApePatCond());
                    editor.putString("cApeMatCond", oDatosConductor.getcApeMatCond());
                    editor.putString("cDNICond", oDatosConductor.getcDNICond());
                    editor.putString("cEdadCond", oDatosConductor.getcEdadCond());
                    editor.putString("cCelCond", oDatosConductor.getcCelCond());
                    editor.putString("cDireccCond", oDatosConductor.getcDireccCond());
                    editor.putString("cCorEleCond", oDatosConductor.getcCorEleCond());
                    editor.putString("lEstadoCond", oDatosConductor.getlEstadoCond());
                    editor.putString("cPassCond", oDatosConductor.getcPassCond());
                    editor.commit();
//                    Toast.makeText(LoginActivity.this, "Guardado en Preferencias", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(LoginActivity.this,"NO HAY RESPUESTA", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Conductor> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });


    }


}