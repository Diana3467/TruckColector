package com.example.appcollector.ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appcollector.MainActivity;
import com.example.appcollector.R;
import com.example.appcollector.ui.Interfaces.WebServicesInterface;
import com.example.appcollector.ui.Modelos.Ciudadano;
import com.example.appcollector.ui.Modelos.Token;
import com.example.appcollector.ui.Util.Util;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.appcollector.ui.Util.Util.ARCHIVO_PREFRENCIAS;

public class SplashActivity extends AppCompatActivity {

    ImageView logo;
    TextView logotexto;
    Animation animation;
    int direccion = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferencias = this.getSharedPreferences(ARCHIVO_PREFRENCIAS, Context.MODE_PRIVATE);
        if (preferencias.contains("cNombreCiud")) {
            Ciudadano oCiudadano = new Ciudadano(null,null,null,
                    null,preferencias.getString("cDNICiud", ""),null,null,null,
                    preferencias.getString("cDNICiud", ""), null, null, null, null);

            AutentificarCiudadano(oCiudadano);
        }

        setContentView(R.layout.activity_splash);
        logo = findViewById(R.id.imgLogoSplash);
        logotexto =findViewById(R.id.tvLogoTextoSplash);
        animation = AnimationUtils.loadAnimation(this, R.anim.animation);
        logo.startAnimation(animation);
        logotexto.startAnimation(animation);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if(direccion ==1){
                    llamarMainActivity();
                }
                else{
                    llamarLoginActivity();
                }
            }
        }, 3000);
    }

    private void llamarMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    private void llamarLoginActivity() {
        Intent i = new Intent(this, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
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

                    if(mensaje_Respuesta.equals("INGRESO EXITOSO")){
                        direccion = 1;
                    }
                    else{
                        borrarPreferencias();
                        Toast.makeText(SplashActivity.this,"PROBLEMAS CON SUS CREDENCIALES"
                                + System.getProperty ("line.separator") +"VUELVA A INGRESAR!!", Toast.LENGTH_SHORT).show();
                        llamarLoginActivity();

                    }
                }
                else {
                    Toast.makeText(SplashActivity.this,"NO HAY RESPUESTA", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Toast.makeText(SplashActivity.this, t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void borrarPreferencias(){
        SharedPreferences.Editor borrandosharedpreferences = getSharedPreferences(Util.ARCHIVO_PREFRENCIAS, MODE_PRIVATE).edit();
        borrandosharedpreferences.clear().apply();
    }
}