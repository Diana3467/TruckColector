package com.example.appcollector.ui.Inicio;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.appcollector.R;
import com.example.appcollector.ui.Fragments.HorariosRecolector;

import static androidx.navigation.Navigation.findNavController;


public class Inicio extends Fragment {

    Fragment frgHorario;
    Button btnHorario, btnRealizarReclamo, btnRelizarDenuncia;
    public Inicio() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_inicio, container, false);
        btnHorario = vista.findViewById(R.id.btnHorariosRecolector_Inicio);
        btnRealizarReclamo = vista.findViewById(R.id.btnReclamos_Inicio);
        btnRelizarDenuncia = vista.findViewById(R.id.btnDenunciaDelitoAmbiental_Inicio);

        btnHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                pasarFragment();
                findNavController(view).navigate(R.id.PassFragHorarioRecolector);

//                frgHorario fr = new frgHorario();
//                getActivity().getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.HoraiosRecolector,fr)
//                        .addToBackStack(null)
//                        .commit();
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
    public void pasarFragment(){

        HorariosRecolector fr = new HorariosRecolector();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment_content_main,fr)
                .addToBackStack(null)
                .commit();
    }
}