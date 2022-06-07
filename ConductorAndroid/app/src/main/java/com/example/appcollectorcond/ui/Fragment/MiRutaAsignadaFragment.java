package com.example.appcollectorcond.ui.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appcollectorcond.R;
import com.example.appcollectorcond.ui.Adapter.AsignacionRutasAdapter;
import com.example.appcollectorcond.ui.Interfaces.WebServicesInterface;
import com.example.appcollectorcond.ui.Modelos.AsigRutaCond;
import com.example.appcollectorcond.ui.Modelos.Conductor;
import com.example.appcollectorcond.ui.Util.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.appcollectorcond.ui.Util.Util.ARCHIVO_PREFRENCIAS;

public class MiRutaAsignadaFragment extends Fragment {

    RecyclerView recyclerAsigRutaCond;
    ArrayList<AsigRutaCond> listaAsigRutaCond;

    TextView txtRutaAsignada;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mi_ruta_asignada, container, false);

        listaAsigRutaCond = new ArrayList<>();
        recyclerAsigRutaCond = (RecyclerView)view.findViewById(R.id.idRecyclerObject);
        recyclerAsigRutaCond.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerAsigRutaCond.setHasFixedSize(true);

        txtRutaAsignada = view.findViewById(R.id.txtRutaAsignada);

        SharedPreferences preferencias = this.getActivity().getSharedPreferences(ARCHIVO_PREFRENCIAS, Context.MODE_PRIVATE);
        ListarRutaAsignada(preferencias.getString("nCodigoCond", ""));
        return view;
    }

    public void ListarRutaAsignada(String nCodigoCond){

        String url = Util.url;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();

        WebServicesInterface CondWebServ = retrofit.create(WebServicesInterface.class);
        Call<List<AsigRutaCond>> call = CondWebServ.ListarRutaAsignada(nCodigoCond);

        call.enqueue(new Callback<List<AsigRutaCond>>() {
            @Override
            public void onResponse(Call<List<AsigRutaCond>> call, Response<List<AsigRutaCond>> response) {
                if(response.isSuccessful()){

                    for(AsigRutaCond oAsigRutaCond : response.body()){

                        AsigRutaCond newobjAsigRutaCond = new AsigRutaCond(
                                oAsigRutaCond.getcInfoRuta(),
                                oAsigRutaCond.getcDescriRuta(),
                                oAsigRutaCond.getcDias(),
                                oAsigRutaCond.getdFechaInicio(),
                                oAsigRutaCond.getdFechaFin(),
                                oAsigRutaCond.getcHoraInicioHor(),
                                oAsigRutaCond.getcHoraFinHor()
                        );

                        listaAsigRutaCond.add(newobjAsigRutaCond);
                    }

                    if(listaAsigRutaCond.size() ==0) txtRutaAsignada.setText("Sin Rutas Asignadas");

                    AsignacionRutasAdapter adapter = new AsignacionRutasAdapter(listaAsigRutaCond);
                    recyclerAsigRutaCond.setAdapter(adapter);
                    Toast.makeText(getActivity(), "Recuperado Exitosamente!", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(getActivity(),"NO HAY RESPUESTA", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<AsigRutaCond>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });


    }




}