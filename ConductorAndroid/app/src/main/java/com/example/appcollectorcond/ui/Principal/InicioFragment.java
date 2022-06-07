package com.example.appcollectorcond.ui.Principal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.appcollectorcond.R;
import com.example.appcollectorcond.ui.Modelos.Conductor;

import static androidx.navigation.Navigation.findNavController;

public class InicioFragment extends Fragment implements View.OnClickListener {

    Button btnRutaAsignada, btnReportarIncidencia;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        btnRutaAsignada = view.findViewById(R.id.btnMiRutaAsignada_Inicio);
        btnReportarIncidencia = view.findViewById(R.id.btnReportarIncidencia_Inicio);

        btnRutaAsignada.setOnClickListener(this);
        btnReportarIncidencia.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnMiRutaAsignada_Inicio:{
                findNavController(view).navigate(R.id.PassFragMiRutaAsignada);
                break;
            }
            case R.id.btnReportarIncidencia_Inicio:{

                break;
            }
        }
    }



}