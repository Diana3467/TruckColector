package com.example.appcollectorcond.ui.Principal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.appcollectorcond.R;
import com.example.appcollectorcond.ui.Activity.LoginActivity;
import com.example.appcollectorcond.ui.Interfaces.WebServicesInterface;
import com.example.appcollectorcond.ui.Modelos.Conductor;
import com.example.appcollectorcond.ui.Util.Util;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.navigation.Navigation.findNavController;
import static com.example.appcollectorcond.ui.Util.Util.ARCHIVO_PREFRENCIAS;

public class EditarDatosFragment extends Fragment implements View.OnClickListener {

    TextInputEditText tedNombreCon, tedApellidoPaternoCon, tedApellidoMaternoCon, tedDocumentoCon,
                        tedEdadCon, tedCelularCon, tedDireccionCon, tedEmailCon;
    Button btnActualizarDatos, btnCancelar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_editar_datos, container, false);

        tedNombreCon = view.findViewById(R.id.edtNombre_EP);
        tedApellidoPaternoCon = view.findViewById(R.id.edtApellidoPaterno_EP);
        tedApellidoMaternoCon = view.findViewById(R.id.edtApellidoMaterno_EP);
        tedDocumentoCon = view.findViewById(R.id.edtDocumento_EP);
        tedEdadCon = view.findViewById(R.id.edtEdad_EP);
        tedCelularCon = view.findViewById(R.id.edtCelular_EP);
        tedDireccionCon = view.findViewById(R.id.edtDireccion_EP);
        tedEmailCon = view.findViewById(R.id.edtEmail_EP);
        btnActualizarDatos = view.findViewById(R.id.btnActualizar_EP);
        btnCancelar = view.findViewById(R.id.btnCancelar_EP);

        tedDocumentoCon.setEnabled(false);

        SharedPreferences preferencias = this.getActivity().getSharedPreferences(ARCHIVO_PREFRENCIAS, Context.MODE_PRIVATE);
        if (preferencias.contains("cNombreCond")) {
            tedNombreCon.setText(preferencias.getString("cNombreCond", ""));
            tedApellidoPaternoCon.setText(preferencias.getString("cApePatCond", ""));
            tedApellidoMaternoCon.setText(preferencias.getString("cApeMatCond", ""));
            tedDocumentoCon.setText(preferencias.getString("cDNICond", ""));
            tedEdadCon.setText(preferencias.getString("cEdadCond", ""));
            tedCelularCon.setText(preferencias.getString("cCelCond", ""));
            tedDireccionCon.setText(preferencias.getString("cDireccCond", ""));
            tedEmailCon.setText(preferencias.getString("cCorEleCond", ""));
        }

        btnActualizarDatos.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);


        return  view;
    }

    @Override
    public void onClick(View view) {
        SharedPreferences preferencias = this.getActivity().getSharedPreferences(ARCHIVO_PREFRENCIAS, Context.MODE_PRIVATE);
        switch(view.getId()){
            case R.id.btnActualizar_EP:{
                Conductor oConductor = new Conductor(
                        preferencias.getString("nCodigoCond", ""),
                        tedNombreCon.getText().toString(),
                        tedApellidoPaternoCon.getText().toString(),
                        tedApellidoMaternoCon.getText().toString(),
                        tedDocumentoCon.getText().toString(),
                        tedEdadCon.getText().toString(),
                        tedCelularCon.getText().toString(),
                        tedDireccionCon.getText().toString(),
                        tedEmailCon.getText().toString(),
                        null,
                        null,
                        null
                        );
                ActualizarConductor(oConductor);
                //Toast.makeText(getActivity(), "Actualizando", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btnCancelar_EP:{

                findNavController(view).navigate(R.id.PassFragInicio);
                break;
            }
        }
    }

    public void ActualizarConductor(Conductor oConductor){

        SharedPreferences preferencias = this.getActivity().getSharedPreferences(ARCHIVO_PREFRENCIAS, Context.MODE_PRIVATE);
        String url = Util.url;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();

        WebServicesInterface CondWebServ = retrofit.create(WebServicesInterface.class);
        Call<String> call = CondWebServ.ActualizarConductor(oConductor);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    SharedPreferences.Editor editor = preferencias.edit();
                    editor.putString("nCodigoCond", oConductor.getnCodigoCond());
                    editor.putString("cNombreCond", oConductor.getcNombreCond());
                    editor.putString("cApePatCond", oConductor.getcApePatCond());
                    editor.putString("cApeMatCond", oConductor.getcApeMatCond());
                    editor.putString("cDNICond", oConductor.getcDNICond());
                    editor.putString("cEdadCond", oConductor.getcEdadCond());
                    editor.putString("cCelCond", oConductor.getcCelCond());
                    editor.putString("cDireccCond", oConductor.getcDireccCond());
                    editor.putString("cCorEleCond", oConductor.getcCorEleCond());
                    editor.putString("lEstadoCond", oConductor.getlEstadoCond());
                    editor.putString("cPassCond", oConductor.getcPassCond());
                    editor.commit();
//                    Toast.makeText(getActivity(), response.body().toString(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(), "Cambios Efectuados Exitosamente!", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(getActivity(),"NO HAY RESPUESTA", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });


    }

}