package com.example.appcollector.ui.Fragments;

import android.app.Dialog;
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

import com.example.appcollector.R;

public class HorariosRecolector extends Fragment {
    Spinner spinCountry;
    Button btnModHorario, btnCancelHorario;
    Button btnCambiarHorario, btnCancelarCambio;

    public HorariosRecolector() {
        // Required empty public constructor
    }

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
}