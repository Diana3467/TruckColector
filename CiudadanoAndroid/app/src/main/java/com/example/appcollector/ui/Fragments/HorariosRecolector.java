package com.example.appcollector.ui.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.AlarmClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appcollector.R;
import com.example.appcollector.ui.Interfaces.WebServicesInterface;
import com.example.appcollector.ui.Modelos.Horario;
import com.example.appcollector.ui.Util.Util;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.appcollector.ui.Util.Util.ARCHIVO_PREFRENCIAS;

public class HorariosRecolector extends Fragment {

    TextView txtCalleHor, txtDiasHor, txtHoraFor;

    Button btnModHorario;
    Button btnCambiarHorario, btnCancelarCambio;

    String horain1;
    ArrayList<Integer> alarmDays1= new ArrayList<Integer>();
    ArrayList<Integer> alarmDays2= new ArrayList<Integer>();

    AutoCompleteTextView horDialog;
    public static String [] horaest = new String[]{
            "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"
    };
    String hora_anterior;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_horarios_recolector, container, false);

        alarmDays2.add(Calendar.TUESDAY);
        alarmDays2.add(Calendar.THURSDAY);
        alarmDays2.add(Calendar.SATURDAY);

        alarmDays1.add(Calendar.MONDAY);
        alarmDays1.add(Calendar.WEDNESDAY);
        alarmDays1.add(Calendar.FRIDAY);

        txtCalleHor = vista.findViewById(R.id.tvCalle_Card);
        txtDiasHor = vista.findViewById(R.id.tvdias_Card);
        txtHoraFor = vista.findViewById(R.id.tvhora_Card);

        SharedPreferences preferencias = this.getActivity().getSharedPreferences(ARCHIVO_PREFRENCIAS, Context.MODE_PRIVATE);
        TraerHorario(preferencias.getString("nCodigoCalle", ""));

        btnModHorario = vista.findViewById(R.id.btnModificarRecordatorio_Horario);

        btnModHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.popup_recordatorio);
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.popup);

                btnCambiarHorario = dialog.findViewById(R.id.btnAceptarMod_Popup);
                btnCancelarCambio = dialog.findViewById(R.id.btnCancelarMod_Popup);

                ArrayAdapter<String> adapSppinerHora = new ArrayAdapter<>(getActivity(), R.layout.adaptador_sppiner, horaest);
                horDialog = dialog.findViewById(R.id.autCambiarHora);
                hora_anterior = preferencias.getString("HoraAlarma", "");
                horDialog.setText(preferencias.getString("HoraAlarma", ""));
                horDialog.setAdapter(adapSppinerHora);
                horDialog.setKeyListener(null);

                btnCambiarHorario.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SharedPreferences.Editor editor = preferencias.edit();
                        editor.putString("HoraAlarma", horDialog.getText().toString());
                        editor.commit();

                        int hin = Integer.parseInt(horain1) - 1;
                        int min = 60-Integer.parseInt(horDialog.getText().toString());

                        if(txtDiasHor.getText().toString().equals("Martes, Jueves y Sábado")){
                            Intent ialarm = new Intent(AlarmClock.ACTION_SET_ALARM)
                                    .putExtra(AlarmClock.EXTRA_MESSAGE, "DENTRO DE "+ preferencias.getString("HoraAlarma", "") +" MINUTOS PASARÁ EL CARRO RECOLECTOR POR SU ZONA")
                                    .putExtra(AlarmClock.EXTRA_HOUR, hin)
                                    .putExtra(AlarmClock.EXTRA_MINUTES, min)
                                    .putExtra(AlarmClock.EXTRA_DAYS, alarmDays2);
                            startActivity(ialarm);
                        }
                        else{
                            Intent ialarm = new Intent(AlarmClock.ACTION_SET_ALARM)
                                    .putExtra(AlarmClock.EXTRA_MESSAGE, "DENTRO DE "+ preferencias.getString("HoraAlarma", "") +" MINUTOS PASARÁ EL CARRO RECOLECTOR POR SU ZONA")
                                    .putExtra(AlarmClock.EXTRA_HOUR, hin)
                                    .putExtra(AlarmClock.EXTRA_MINUTES, min)
                                    .putExtra(AlarmClock.EXTRA_DAYS, alarmDays1);
                            startActivity(ialarm);
                        }


                        dialog.dismiss();
                    }
                });


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
                    horain1 = horainparts[0]; // HH
                    String horain2 = horainparts[1]; // MM
                    String HoraInicio = horain1 + ":" + horain2;

                    String horafin = oHorario.getcHoraFinHor();
                    String[] horafinparts = horafin.split("\\:");
                    String horafin1 = horafinparts[0]; // HH
                    String horafin2 = horafinparts[1]; // MM
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