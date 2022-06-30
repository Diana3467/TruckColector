package com.example.appcollector.ui.Inicio;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.appcollector.R;

import static androidx.navigation.Navigation.findNavController;


public class Inicio extends Fragment {

    Button btnHorario, btnRealizarReclamo, btnRelizarDenuncia;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_inicio, container, false);
        btnHorario = vista.findViewById(R.id.btnHorariosRecolector_Inicio);
        btnRealizarReclamo = vista.findViewById(R.id.btnReclamos_Inicio);
        btnRelizarDenuncia = vista.findViewById(R.id.btnDenunciaDelitoAmbiental_Inicio);

        btnHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findNavController(view).navigate(R.id.PassFragHorarioRecolector);
            }
        });

        btnRealizarReclamo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findNavController(view).navigate(R.id.PassFragRealizarReclamo);
            }
        });
        btnRelizarDenuncia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findNavController(view).navigate(R.id.PassFragRealizarDenuncia);
            }
        });

        return vista;
    }
}