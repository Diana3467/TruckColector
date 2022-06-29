package com.example.appcollector.ui.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appcollector.R;
import com.example.appcollector.ui.Activity.SplashActivity;
import com.example.appcollector.ui.Interfaces.WebServicesInterface;
import com.example.appcollector.ui.Modelos.Ciudadano;
import com.example.appcollector.ui.Modelos.Horario;
import com.example.appcollector.ui.Modelos.ReclamoCiudadano;
import com.example.appcollector.ui.Modelos.Token;
import com.example.appcollector.ui.Util.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.navigation.Navigation.findNavController;
import static com.example.appcollector.ui.Util.Util.ARCHIVO_PREFRENCIAS;

public class HorariosRecolector extends Fragment {

    TextView txtCalleHor, txtDiasHor, txtHoraFor;

    Spinner spinCountry;
    Button btnModHorario, btnCancelHorario;
    Button btnCambiarHorario, btnCancelarCambio;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_horarios_recolector, container, false);

//        spinCountry= vista.findViewById(R.id.spinOtraconsulta_Horario);//fetch the spinner from layout file
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
//                android.R.layout.simple_spinner_item, getResources()
//                .getStringArray(R.array.calles));//setting the country_array to spinner
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinCountry.setAdapter(adapter);
        //if you want to set any action you can do in this listener
//        spinCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> arg0, View arg1,
//                                       int position, long id) {
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> arg0) {
//            }
//        });
        txtCalleHor = vista.findViewById(R.id.tvCalle_Card);
        txtDiasHor = vista.findViewById(R.id.tvdias_Card);
        txtHoraFor = vista.findViewById(R.id.tvhora_Card);

        SharedPreferences preferencias = this.getActivity().getSharedPreferences(ARCHIVO_PREFRENCIAS, Context.MODE_PRIVATE);
        TraerHorario(preferencias.getString("nCodigoCalle", ""));

        btnModHorario = vista.findViewById(R.id.btnModificarRecordatorio_Horario);
        btnCancelHorario = vista.findViewById(R.id.btnDesactivarRecordatorio_Horario);
        btnModHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.popup_recordatorio);
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.popup);

                btnCambiarHorario = dialog.findViewById(R.id.btnAceptarMod_Popup);
                btnCancelarCambio = dialog.findViewById(R.id.btnCancelarMod_Popup);

                btnCancelarCambio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        return vista;
    }

    public void TraerHorario(String nCodigoCalle) {
        SharedPreferences preferencias = this.getActivity().getSharedPreferences(ARCHIVO_PREFRENCIAS, Context.MODE_PRIVATE);

        String url = Util.url;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();

        WebServicesInterface CondWebServ = retrofit.create(WebServicesInterface.class);
        Call<Horario> call = CondWebServ.TraerHorario(nCodigoCalle);

        call.enqueue(new Callback<Horario>() {
            @Override
            public void onResponse(Call<Horario> call, Response<Horario> response) {
                if (response.isSuccessful()) {
                    Horario oHorario = response.body();

                    String horain = oHorario.getcHoraInicioHor();
                    String[] horainparts = horain.split("\\:");
                    String horain1 = horainparts[0]; // HH
                    String horain2 = horainparts[1]; // MM
//                    String horain3 = horainparts[2]; // SS
                    String HoraInicio = horain1 + ":" + horain2;

                    String horafin = oHorario.getcHoraFinHor();
                    String[] horafinparts = horafin.split("\\:");
                    String horafin1 = horafinparts[0]; // HH
                    String horafin2 = horafinparts[1]; // MM
//                    String horafin3 = horafinparts[2]; // SS
                    String HoraFin = horafin1 + ":" + horafin2;

                    String HoraFinal = HoraInicio + " - " + HoraFin;

                    String CalleIns = preferencias.getString("cNombreCalle", "") + "  N°" + preferencias.getString("cNumDirecCiud", "");

                    txtCalleHor.setText(CalleIns);
                    txtDiasHor.setText(oHorario.getcDiasHor());
                    txtHoraFor.setText(HoraFinal);


                } else {
                    Toast.makeText(getActivity(), "NO HAY RESPUESTA", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Horario> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}