package com.example.appcollectorcond.ui.Fragment;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.appcollectorcond.R;
import com.example.appcollectorcond.ui.Interfaces.WebServicesInterface;
import com.example.appcollectorcond.ui.Modelos.Conductor;
import com.example.appcollectorcond.ui.Modelos.ReclamoConductor;
import com.example.appcollectorcond.ui.Util.Util;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.sql.Time;
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
import static com.example.appcollectorcond.ui.Util.Util.ARCHIVO_PREFRENCIAS;

public class ReportarIncidenciaFragment extends Fragment {
    TextView tvCabecera_Rec;
    TextInputLayout txtFecha_Rec, txtHora_Rec, txtNombreCompleto_Rec, txtNumPlaca_Rec, txtDescripcion_Rec;
    TextInputEditText tedFecha_Rec, tedHora_Rec, tedNombreCompleto_Rec, tedNumPlaca_Rec, tedDescripcion_Rec;

    Button btnRegistrar_Rec;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reportar_incidencia, container, false);

        tvCabecera_Rec = view.findViewById(R.id.txvCabecera_Rec);

        txtFecha_Rec = view.findViewById(R.id.txtFecha_Rec);
        txtHora_Rec = view.findViewById(R.id.txtHora_Rec);
        txtNombreCompleto_Rec = view.findViewById(R.id.txtNombreCompleto_Rec);
        txtNumPlaca_Rec = view.findViewById(R.id.txtNumPlaca_Rec);
        txtDescripcion_Rec = view.findViewById(R.id.txtDescripcion_Rec);

        tedFecha_Rec = view.findViewById(R.id.edtFecha_Rec);
        tedHora_Rec = view.findViewById(R.id.edtHora_Rec);
        tedNombreCompleto_Rec = view.findViewById(R.id.edtNombreCompleto_Rec);
        tedNumPlaca_Rec = view.findViewById(R.id.edtNumPlaca_Rec);
        tedDescripcion_Rec = view.findViewById(R.id.edtDescripcion_Rec);
        btnRegistrar_Rec = view.findViewById(R.id.btnRegistrar_Rec);



        SharedPreferences preferencias = this.getActivity().getSharedPreferences(ARCHIVO_PREFRENCIAS, Context.MODE_PRIVATE);
        if (preferencias.getString("cPlacaCar", "").equals("")) {
            tvCabecera_Rec.setText("No se le Asigno Un Carro");
            Deshabilitar_Controles();
        }
        else{
            Habilitar_Controles();
            tedFecha_Rec.setKeyListener(null);
            tedHora_Rec.setKeyListener(null);
            tedNombreCompleto_Rec.setKeyListener(null);
            tedNumPlaca_Rec.setKeyListener(null);

            tedNombreCompleto_Rec.setText(preferencias.getString("cNombreCond", "")+ ", "
                            + preferencias.getString("cApePatCond", "") +" "+  preferencias.getString("cApeMatCond", ""));

            tedNumPlaca_Rec.setText(preferencias.getString("cPlacaCar", ""));
            Permitir_Controles();
        }
        return view;
    }

    public void Deshabilitar_Controles(){
        txtFecha_Rec.setVisibility(View.INVISIBLE);
        txtHora_Rec.setVisibility(View.INVISIBLE);
        txtNombreCompleto_Rec.setVisibility(View.INVISIBLE);
        txtNumPlaca_Rec.setVisibility(View.INVISIBLE);
        txtDescripcion_Rec.setVisibility(View.INVISIBLE);
        btnRegistrar_Rec.setVisibility(View.INVISIBLE);

        tedFecha_Rec.setEnabled(false);
        tedHora_Rec.setEnabled(false);
        tedNombreCompleto_Rec.setEnabled(false);
        tedNumPlaca_Rec.setEnabled(false);
        tedDescripcion_Rec.setEnabled(false);
    }
    public void Habilitar_Controles(){
        txtFecha_Rec.setVisibility(View.VISIBLE);
        txtHora_Rec.setVisibility(View.VISIBLE);
        txtNombreCompleto_Rec.setVisibility(View.VISIBLE);
        txtNumPlaca_Rec.setVisibility(View.VISIBLE);
        txtDescripcion_Rec.setVisibility(View.VISIBLE);
        btnRegistrar_Rec.setVisibility(View.VISIBLE);

        tedFecha_Rec.setEnabled(true);
        tedHora_Rec.setEnabled(true);
        tedNombreCompleto_Rec.setEnabled(true);
        tedNumPlaca_Rec.setEnabled(true);
        tedDescripcion_Rec.setEnabled(true);
    }

    public boolean ValidarCampos(){
        if(tedDescripcion_Rec.getText().toString().equals("") || tedHora_Rec.getText().toString().equals("")){
            if(tedHora_Rec.getText().toString().equals("")) txtHora_Rec.setError("La Hora no debe ser vacío");
            if(tedDescripcion_Rec.getText().toString().equals("")) txtDescripcion_Rec.setError("La Descripción no debe ser vacío");
            return false;
        }

        else return true;
    }

    public void Permitir_Controles(){
        SharedPreferences preferencias = this.getActivity().getSharedPreferences(ARCHIVO_PREFRENCIAS, Context.MODE_PRIVATE);

        //Extrayendo Fecha del sistema
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Date date = new Date();
        tedFecha_Rec.setText(dateFormat.format(date));

        //Extrayendo Fecha del sistema
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

                    ReclamoConductor oReclamoConductor = new ReclamoConductor(
                            null,
                            FechaEnviar,
                            tedDescripcion_Rec.getText().toString(),
                            null,
                            preferencias.getString("nCodigoCond", ""),
                            null,
                            null
                    );
                    InsertarReclamoConductor(oReclamoConductor, view);

                }
                else{
                    Toast.makeText(getActivity(), "Ingrese Correctamente los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void InsertarReclamoConductor(ReclamoConductor oReclamoConductor, View view) {
        String url = Util.url;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();

        WebServicesInterface CondWebServ = retrofit.create(WebServicesInterface.class);
        Call<String> call = CondWebServ.InsertarReclamoConductor(oReclamoConductor);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    tedDescripcion_Rec.setText("");
                    Toast.makeText(getActivity(), "Incidente Registrado!", Toast.LENGTH_SHORT).show();
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