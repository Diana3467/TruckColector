package com.example.appcollector.ui.EditarPerfil;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.appcollector.MainActivity;
import com.example.appcollector.R;
import com.example.appcollector.ui.Activity.LoginActivity;

public class EditarPerfil extends Fragment {

    EditText edtNombre_Act, edtApellidoPat_Act, edtApellidoMat_Act, edtDNI_Act, edtCelular_Act, edtUbicacion_Act, edtNumCasa_Act;
    Button btnActualizar_usuario, btnCancelar_Act;

    boolean validar_sppiner = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista =  inflater.inflate(R.layout.fragment_editar_perfil, container, false);

        edtNombre_Act = vista.findViewById(R.id.edtNombre_EP);
        edtApellidoPat_Act = vista.findViewById(R.id.edtApellidoPaterno_EP);
        edtApellidoMat_Act = vista.findViewById(R.id.edtApellidoMaterno_EP);
        edtDNI_Act = vista.findViewById(R.id.edtDocumento_EP);
        edtCelular_Act = vista.findViewById(R.id.edtCelular_EP);
        edtUbicacion_Act = vista.findViewById(R.id.edtCalleUbicacion_EP);
        edtNumCasa_Act = vista.findViewById(R.id.edtNumeroUbicacion_EP);
        btnActualizar_usuario = vista.findViewById(R.id.btnActualizar_EP);
        btnCancelar_Act = vista.findViewById(R.id.btnCancelar_EP);

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
}