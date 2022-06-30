package com.example.appcollector.ui.EditarPerfil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appcollector.MainActivity;
import com.example.appcollector.R;
import com.example.appcollector.ui.Activity.LoginActivity;
import com.example.appcollector.ui.Activity.RegistroUsuarioActivity;
import com.example.appcollector.ui.Interfaces.WebServicesInterface;
import com.example.appcollector.ui.Modelos.CalleZona;
import com.example.appcollector.ui.Modelos.Ciudadano;
import com.example.appcollector.ui.Util.Util;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.appcollector.ui.Util.Util.ARCHIVO_PREFRENCIAS;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class EditarPerfil extends Fragment {

    TextInputEditText edtNombre_Act, edtApellidoPat_Act, edtApellidoMat_Act, edtDNI_Act, edtCelular_Act,
            edtNumCasa_Act;
    Button btnActualizar_usuario, btnCancelar_Act;

    AutoCompleteTextView edtUbicacionZona_Act, edtUbicacion_Act;
    TextInputLayout txiUbi;
    boolean validar_sppiner = false, validar_sppinerZona = false;

    List<CalleZona> listaCalles;
    List<String> listaZona;

    String idCalle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista =  inflater.inflate(R.layout.fragment_editar_perfil, container, false);

        edtNombre_Act = vista.findViewById(R.id.edtNombre_EP);
        edtApellidoPat_Act = vista.findViewById(R.id.edtApellidoPaterno_EP);
        edtApellidoMat_Act = vista.findViewById(R.id.edtApellidoMaterno_EP);
        edtDNI_Act = vista.findViewById(R.id.edtDocumento_EP);
        edtCelular_Act = vista.findViewById(R.id.edtCelular_EP);
        edtUbicacionZona_Act = vista.findViewById(R.id.edtCalleUbicacionZona_EP);
        txiUbi = vista.findViewById(R.id.txiUbi_EP);
        edtUbicacion_Act = vista.findViewById(R.id.edtCalleUbicacion_EP);
        edtNumCasa_Act = vista.findViewById(R.id.edtNumeroUbicacion_EP);
        btnActualizar_usuario = vista.findViewById(R.id.btnActualizar_EP);
        btnCancelar_Act = vista.findViewById(R.id.btnCancelar_EP);

        edtDNI_Act.setKeyListener(null);

        SharedPreferences preferencias = this.getActivity().getSharedPreferences(ARCHIVO_PREFRENCIAS, Context.MODE_PRIVATE);
        if (preferencias.contains("cNombreCiud")) {
            edtNombre_Act.setText(preferencias.getString("cNombreCiud", ""));
            edtApellidoPat_Act.setText(preferencias.getString("cApePatCiud", ""));
            edtApellidoMat_Act.setText(preferencias.getString("cApeMatCiud", ""));
            edtDNI_Act.setText(preferencias.getString("cDNICiud", ""));
            edtCelular_Act.setText(preferencias.getString("cCelCiud", ""));
            edtUbicacionZona_Act.setText(preferencias.getString("cDescZona", ""));
            edtUbicacion_Act.setText(preferencias.getString("cNombreCalle", ""));
            edtNumCasa_Act.setText(preferencias.getString("cNumDirecCiud", ""));

            idCalle = preferencias.getString("nCodigoCalle", "");

            validar_sppinerZona = TRUE;
            validar_sppiner = TRUE;
        }

        CalleZona oCalleZona = new CalleZona(null, null, edtUbicacionZona_Act.getText().toString(), null);
        find(oCalleZona);

        listaZona = new ArrayList<>();
        listaZona = Arrays.asList(RegistroUsuarioActivity.tipo_zona);
        ArrayAdapter<String> adapSppinerZona = new ArrayAdapter<>(getActivity(), R.layout.adaptador_sppiner, listaZona);
        edtUbicacionZona_Act.setAdapter(adapSppinerZona);

        edtUbicacionZona_Act.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                txiUbi.setEnabled(false);
                String cDescZona = edtUbicacionZona_Act.getText().toString();
                autorellenar(cDescZona);
            }
        });

        edtUbicacion_Act.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                CalleZona objCallezona = (CalleZona) adapterView.getItemAtPosition(i);

                idCalle = objCallezona.getnCodigoCalle().toString();

                edtUbicacion_Act.setError(null);
                validar_sppiner = true;
            }
        });

        btnActualizar_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validar()){
                    Ciudadano oCiudadano = new Ciudadano(
                            preferencias.getString("nCodigoCiud", ""),
                            edtNombre_Act.getText().toString(),
                            edtApellidoPat_Act.getText().toString(),
                            edtApellidoMat_Act.getText().toString(),
                            edtDNI_Act.getText().toString(),
                            edtCelular_Act.getText().toString(),
                            edtNumCasa_Act.getText().toString(),
                            null,
                            null,
                            idCalle,
                            edtUbicacion_Act.getText().toString(),
                            edtUbicacionZona_Act.getText().toString(),
                            null
                    );
                    ActualizarCiudadano(oCiudadano);
                }
                else{
                    Toast.makeText(getActivity(), "Ingrese correctamente los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnCancelar_Act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });




        return vista;
    }

    public void autorellenar(String cDescZona){

        CalleZona oCalleZona = new CalleZona(null, null, cDescZona, null);

        find(oCalleZona);
        validar_sppinerZona = true;
        edtUbicacionZona_Act.setError(null);
        edtUbicacion_Act.setText("");
        validar_sppiner = false;
    }

    public boolean validar(){

        boolean retorno = true;

        String nombre = edtNombre_Act.getText().toString();
        String apellidopaterno = edtApellidoPat_Act.getText().toString();
        String apellidomaterno = edtApellidoMat_Act.getText().toString();
        String dni = edtDNI_Act.getText().toString();
        String num_casa = edtNumCasa_Act.getText().toString();

        if (nombre.isEmpty())
        {
            edtNombre_Act.setError("El Nombre no debe ser vacío");
            retorno = false;
        }
        if (apellidopaterno.isEmpty()){
            edtApellidoPat_Act.setError("El Apellido Paterno no debe ser vacío");
            retorno = false;
        }
        if (apellidomaterno.isEmpty()){
            edtApellidoMat_Act.setError("El Apellido Materno no debe ser vacío");
            retorno = false;
        }
        if (dni.isEmpty()){
            edtDNI_Act.setError("El Documento no debe ser vacío");
            retorno = false;
        }
        if (validar_sppinerZona == FALSE){
            edtUbicacionZona_Act.setError("El nombre no debe ser vacío");
            retorno = false;
        }
        if (validar_sppiner == FALSE){
            edtUbicacion_Act.setError("La dirección no debe ser vacío");
            retorno = false;
        }
        if (num_casa.isEmpty()){
            edtNumCasa_Act.setError("El número de dirección no debe ser vacío");
            retorno = false;
        }

        return retorno;
    }


    public void find(CalleZona oCalleZona){
        String url = Util.url;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();


        listaCalles = new ArrayList<>();
        ArrayAdapter<CalleZona> adaptadorSppiner = new ArrayAdapter<>(getActivity(), R.layout.adaptador_sppiner, listaCalles);

        WebServicesInterface CallezonaWebServ = retrofit.create(WebServicesInterface.class);
        Call<List<CalleZona>> call = CallezonaWebServ.find(oCalleZona);

        call.enqueue(new Callback<List<CalleZona>>() {
            @Override
            public void onResponse(Call<List<CalleZona>> call, Response<List<CalleZona>> response) {

                if(response.isSuccessful()){


                    for(CalleZona post : response.body()){

                        String nCodigoCalle = post.getnCodigoCalle();
                        String cNombreCalle = post.getcNombreCalle();
                        CalleZona calleZona = new CalleZona(nCodigoCalle, cNombreCalle, null, null);
                        listaCalles.add(calleZona);

                        edtUbicacion_Act.setAdapter(adaptadorSppiner);
                    }
                    txiUbi.setEnabled(true);

                }
                else {
                    Toast.makeText(getActivity(),"NO HAY RESPUESTA", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<CalleZona>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void ActualizarCiudadano(Ciudadano oCiudadano){

        SharedPreferences preferencias = this.getActivity().getSharedPreferences(ARCHIVO_PREFRENCIAS, Context.MODE_PRIVATE);
        String url = Util.url;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();

        WebServicesInterface CondWebServ = retrofit.create(WebServicesInterface.class);
        Call<String> call = CondWebServ.ActualizarCiudadano(oCiudadano);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    SharedPreferences.Editor editor = preferencias.edit();
                    editor.putString("nCodigoCiud", oCiudadano.getnCodigoCiud());
                    editor.putString("cNombreCiud", oCiudadano.getcNombreCiud());
                    editor.putString("cApePatCiud", oCiudadano.getcApePatCiud());
                    editor.putString("cApeMatCiud", oCiudadano.getcApeMatCiud());
                    editor.putString("cDNICiud", oCiudadano.getcDNICiud());
                    editor.putString("cCelCiud", oCiudadano.getcCelCiud());
                    editor.putString("cNumDirecCiud", oCiudadano.getcNumDirecCiud());
                    editor.putString("nCodigoCalle", oCiudadano.getnCodigoCalle());
                    editor.putString("cNombreCalle", oCiudadano.getcNombreCalle());
                    editor.putString("cDescZona", oCiudadano.getcDescZona());
                    editor.commit();
                    Toast.makeText(getActivity(), "Cambios Efectuados Exitosamente!", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(getActivity(),"NO HAY RESPUESTA", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });


    }

}