package com.example.appcollector.ui.Fragments;

import android.app.TimePickerDialog;
import android.content.Context;
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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.appcollector.R;
import com.example.appcollector.ui.Activity.RegistroUsuarioActivity;
import com.example.appcollector.ui.Interfaces.WebServicesInterface;
import com.example.appcollector.ui.Modelos.CalleZona;
import com.example.appcollector.ui.Modelos.DenunciaCiudadano;
import com.example.appcollector.ui.Util.Util;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.navigation.Navigation.findNavController;
import static com.example.appcollector.ui.Util.Util.ARCHIVO_PREFRENCIAS;
import static java.lang.Boolean.TRUE;

public class RealizarDenunciaFragment extends Fragment {

//    TextInputLayout txtFecha_Rec, txtHora_Rec, txtNombreCompleto_Rec, txtNumPlaca_Rec, txtDescripcion_Rec;
    TextInputEditText tedFecha_Den, tedNombreCompleto_Den, tedDescripcion_Den;
    AutoCompleteTextView tedModo_Den, tedUbicacionZona_Den, tedCalleUbicacion_Den;

    Button btnRegistrarDenuncia;

    public static String [] tipo_modo = new String[]{
            "Público", "Anónimo" };

    List<CalleZona> listaCalles;
    TextInputLayout txiUbi;
    boolean validar_sppiner = false, validar_sppinerZona = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista =  inflater.inflate(R.layout.fragment_realizar_denuncia, container, false);

        tedFecha_Den = vista.findViewById(R.id.edtFecha_Den);
        tedModo_Den = vista.findViewById(R.id.edtModo_Den);
        tedNombreCompleto_Den = vista.findViewById(R.id.edtNombreCompleto_Den);
        tedDescripcion_Den = vista.findViewById(R.id.edtDescripcion_Den);

        tedUbicacionZona_Den = vista.findViewById(R.id.edtUbicacionZona_Den);
        txiUbi = vista.findViewById(R.id.txtCalleUbicacion_Den);
        tedCalleUbicacion_Den = vista.findViewById(R.id.edtCalleUbicacion_Den);

        btnRegistrarDenuncia = vista.findViewById(R.id.btnRegistrarDenuncia);

        tedFecha_Den.setKeyListener(null);
        tedModo_Den.setKeyListener(null);
        tedNombreCompleto_Den.setKeyListener(null);

        validar_sppinerZona = TRUE;
        validar_sppiner = TRUE;

        //Extrayendo Fecha del sistema
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Date date = new Date();
        tedFecha_Den.setText(dateFormat.format(date));

        //Llenando el sppiner Modo
        tedModo_Den.setText("Público"); // Dando por defecto denunciar con datos del ciudadano
        ArrayAdapter<String> adapSppinerModo = new ArrayAdapter<>(getActivity(), R.layout.adaptador_sppiner, tipo_modo);
        tedModo_Den.setAdapter(adapSppinerModo);

        tedModo_Den.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                edtUbicacionZona_Act.setText("");
                if(tedModo_Den.getText().toString().equals("Público")){
                    llenardatos();
                }
                else{
                    tedNombreCompleto_Den.setText("Anónimo");
                }

            }
        });


        SharedPreferences preferencias = this.getActivity().getSharedPreferences(ARCHIVO_PREFRENCIAS, Context.MODE_PRIVATE);

        //Llenando el campo Ciudadano (Nombre Completo)
        llenardatos();

        //Llenando el sppiner Zona
        tedUbicacionZona_Den.setText(preferencias.getString("cDescZona", "")); // Dando por defecto la Zona registrada
        ArrayAdapter<String> adapSppinerZona = new ArrayAdapter<>(getActivity(), R.layout.adaptador_sppiner, RegistroUsuarioActivity.tipo_zona);
        tedUbicacionZona_Den.setAdapter(adapSppinerZona);

        tedUbicacionZona_Den.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                edtUbicacionZona_Act.setText("");
                txiUbi.setEnabled(false);
                String cDescZona = tedUbicacionZona_Den.getText().toString();
                autorellenar(cDescZona);
            }
        });

        //Llenando el sppiner Calle
        tedCalleUbicacion_Den.setText(preferencias.getString("cNombreCalle", "")); // Dando por defecto la Calle registrada
        CalleZona oCalleZona = new CalleZona(null, null, tedUbicacionZona_Den.getText().toString(), null);
        find(oCalleZona);

        tedCalleUbicacion_Den.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                CalleZona objCallezona = (CalleZona) adapterView.getItemAtPosition(i);

                String id = objCallezona.getnCodigoCalle().toString();


                tedCalleUbicacion_Den.setError(null);
//                Toast.makeText(getActivity(), id, Toast.LENGTH_SHORT).show();
                validar_sppiner = true;
            }
        });

        btnRegistrarDenuncia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ValidarCampos() == true){
                    SharedPreferences.Editor editor = preferencias.edit();

                    String fecha = String.valueOf(tedFecha_Den.getText().toString());
                    String[] fechaparts = fecha.split("\\-");
                    String fecha1 = fechaparts[0]; // Dia
                    String fecha2 = fechaparts[1]; // Mes
                    String fecha3 = fechaparts[2]; // Año
                    String FechaEnviar = fecha2 + "-" + fecha1 + "-" + fecha3;

                    //Extrayendo Hora del sistema
                    SimpleDateFormat df = new SimpleDateFormat("HH:mm");
                    String Hora = df.format(Calendar.getInstance().getTime()).toString();

                    //Juntando la fecha y la hora del sistema para enviar como parametro
                    String FechaHoraEnviar = FechaEnviar + " " + Hora;

                    DenunciaCiudadano oDenunciaCiudadano = new DenunciaCiudadano(
                            null,
                            tedModo_Den.getText().toString(),
                            FechaHoraEnviar,
                            preferencias.getString("nCodigoCalle", ""),
                            null,
                            tedDescripcion_Den.getText().toString(),
                            null,
                            preferencias.getString("nCodigoCiud", "")
                    );

                    InsertarDenunciaCiudadano(oDenunciaCiudadano, view);
                    Toast.makeText(getActivity(), "ENVIADOO", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(getActivity(), "Ingrese Correctamente los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return vista;
    }

    public void llenardatos(){
        //Llenando el campo Ciudadano (Nombre Completo)
        SharedPreferences preferencias = this.getActivity().getSharedPreferences(ARCHIVO_PREFRENCIAS, Context.MODE_PRIVATE);
        tedNombreCompleto_Den.setText(preferencias.getString("cNombreCiud", "")+ ", "
                + preferencias.getString("cApePatCiud", "") +" "+  preferencias.getString("cApeMatCiud", ""));

    }

    public void autorellenar(String cDescZona){

        CalleZona oCalleZona = new CalleZona(null, null, cDescZona, null);

        find(oCalleZona);
        validar_sppinerZona = true;
//                edtUbicacion.setText(autoCompleteTextView.getText().toString());
        tedUbicacionZona_Den.setError(null);
//                Toast.makeText(RegistroUsuarioActivity.this, autoCompleteTextViewZona.getText().toString(), Toast.LENGTH_SHORT).show();
        tedCalleUbicacion_Den.setText("");
        validar_sppiner = false;
    }

    public boolean ValidarCampos(){
        if(tedDescripcion_Den.getText().toString().equals("")){
            if(tedDescripcion_Den.getText().toString().equals("")) tedDescripcion_Den.setError("La Descripción no debe ser vacío");
            return false;
        }

        else return true;
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

                        tedCalleUbicacion_Den.setAdapter(adaptadorSppiner);
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

    public void InsertarDenunciaCiudadano(DenunciaCiudadano oDenunciaCiudadano, View view) {
        String url = Util.url;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();

        WebServicesInterface CondWebServ = retrofit.create(WebServicesInterface.class);
        Call<String> call = CondWebServ.InsertarDenunciaCiudadano(oDenunciaCiudadano);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    tedDescripcion_Den.setText("");
                    Toast.makeText(getActivity(), "Denuncia Registrada!", Toast.LENGTH_SHORT).show();
                    findNavController(view).navigate(R.id.PassFragInicio3);

                } else {
                    Toast.makeText(getActivity(), "NO HAY RESPUESTA", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}