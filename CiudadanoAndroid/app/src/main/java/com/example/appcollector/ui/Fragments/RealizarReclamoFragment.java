package com.example.appcollector.ui.Fragments;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.appcollector.R;
import com.example.appcollector.ui.Interfaces.WebServicesInterface;
import com.example.appcollector.ui.Modelos.ReclamoCiudadano;
import com.example.appcollector.ui.Util.Util;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.navigation.Navigation.findNavController;
import static com.example.appcollector.ui.Util.Util.ARCHIVO_PREFRENCIAS;

public class RealizarReclamoFragment extends Fragment {

    TextInputEditText tedFecha_Rec, tedHora_Rec, tedNombreCompleto_Rec, tedApellidosCompleto_Rec, tedDescripcion_Rec;

    Button btnRegistrar_Rec;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_realizar_reclamo, container, false);

        tedFecha_Rec = vista.findViewById(R.id.edtFecha_Rec);
        tedHora_Rec = vista.findViewById(R.id.edtHora_Rec);
        tedNombreCompleto_Rec = vista.findViewById(R.id.edtNombreCompleto_Rec);
        tedApellidosCompleto_Rec = vista.findViewById(R.id.edtApellidosCompleto_Rec);
        tedDescripcion_Rec = vista.findViewById(R.id.edtDescripcion_Rec);
        btnRegistrar_Rec = vista.findViewById(R.id.btnRegistrar_Rec);

        tedFecha_Rec.setKeyListener(null);
        tedHora_Rec.setKeyListener(null);
        tedNombreCompleto_Rec.setKeyListener(null);
        tedApellidosCompleto_Rec.setKeyListener(null);

        SharedPreferences preferencias = this.getActivity().getSharedPreferences(ARCHIVO_PREFRENCIAS, Context.MODE_PRIVATE);

        //Llenando Fecha del sistema
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Date date = new Date();
        tedFecha_Rec.setText(dateFormat.format(date));

        //Cambiar Hora del sistema
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        tedHora_Rec.setText(df.format(Calendar.getInstance().getTime()));

        //Cuadro de Dialogo de la Hora
        tedHora_Rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar getDate = Calendar.getInstance();

                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                getDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                getDate.set(Calendar.MINUTE, minute);
                                SimpleDateFormat timeformat=new SimpleDateFormat("HH:mm a");
                                String formatedHour = timeformat.format(getDate.getTime());
                                tedHora_Rec.setText(formatedHour);

                            }
                        }, getDate.get(Calendar.HOUR_OF_DAY), getDate.get(Calendar.MINUTE), false);

                timePickerDialog.show();
            }
        });

        //Llenando el campo Nombres
        tedNombreCompleto_Rec.setText(preferencias.getString("cNombreCiud", ""));

        //Llenando el campo Apellidos
        tedApellidosCompleto_Rec.setText(preferencias.getString("cApePatCiud", "") +" "+  preferencias.getString("cApeMatCiud", ""));


        btnRegistrar_Rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ValidarCampos() == true){
                    SharedPreferences.Editor editor = preferencias.edit();

                    String fecha = String.valueOf(tedFecha_Rec.getText().toString());
                    String[] fechaparts = fecha.split("\\-");
                    String fecha1 = fechaparts[0]; // Dia
                    String fecha2 = fechaparts[1]; // Mes
                    String fecha3 = fechaparts[2]; // Año
                    String FechaEnviar = fecha2 + "-" + fecha1 + "-" + fecha3;

                    //Juntando la fecha y la hora del sistema para enviar como parametro
                    String FechaHoraEnviar = FechaEnviar + " " + tedHora_Rec.getText().toString();

                    ReclamoCiudadano oReclamoCiudadano = new ReclamoCiudadano(
                            null,
                            FechaHoraEnviar,
                            tedDescripcion_Rec.getText().toString(),
                            null,
                            preferencias.getString("nCodigoCiud", ""),
                            null
                    );
                    InsertarReclamoCiudadano(oReclamoCiudadano, view);

                }
                else{
                    Toast.makeText(getActivity(), "Ingrese Correctamente los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return vista;
    }


    public boolean ValidarCampos(){
        if(tedDescripcion_Rec.getText().toString().equals("")){
            if(tedDescripcion_Rec.getText().toString().equals("")) tedDescripcion_Rec.setError("La Descripción no debe ser vacío");
            return false;
        }

        else return true;
    }

    public void InsertarReclamoCiudadano(ReclamoCiudadano oReclamoCiudadano, View view) {
        String url = Util.url;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();

        WebServicesInterface CondWebServ = retrofit.create(WebServicesInterface.class);
        Call<String> call = CondWebServ.InsertarReclamoCiudadano(oReclamoCiudadano);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    tedDescripcion_Rec.setText("");
                    Toast.makeText(getActivity(), "Reclamo Registrado!", Toast.LENGTH_SHORT).show();
                    findNavController(view).navigate(R.id.PassFragInicio2);

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