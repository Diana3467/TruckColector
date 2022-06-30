package com.example.appcollector.ui.MisReclamos;

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

import com.example.appcollector.R;
import com.example.appcollector.ui.Adapter.MisReclamosAdapter;
import com.example.appcollector.ui.Interfaces.WebServicesInterface;
import com.example.appcollector.ui.Modelos.ReclamoCiudadano;
import com.example.appcollector.ui.Util.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.appcollector.ui.Util.Util.ARCHIVO_PREFRENCIAS;

public class MisReclamos extends Fragment {

    RecyclerView recyclerReclamoCiudadano;
    ArrayList<ReclamoCiudadano> listaReclamoCiudadano;

    TextView txtReclamosRealizados;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mis_reclamos, container, false);

        listaReclamoCiudadano = new ArrayList<>();
        recyclerReclamoCiudadano = (RecyclerView)view.findViewById(R.id.idRecyclerObjectReclamosRealizados);
        recyclerReclamoCiudadano.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerReclamoCiudadano.setHasFixedSize(true);

        txtReclamosRealizados = view.findViewById(R.id.txtReclamosRealizados);

        SharedPreferences preferencias = this.getActivity().getSharedPreferences(ARCHIVO_PREFRENCIAS, Context.MODE_PRIVATE);
        ListarReclamosUnCiudadano(preferencias.getString("nCodigoCiud", ""));

        return view;
    }

    private void ListarReclamosUnCiudadano(String nCodigoCiud) {
        String url = Util.url;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();

        WebServicesInterface CondWebServ = retrofit.create(WebServicesInterface.class);
        Call<List<ReclamoCiudadano>> call = CondWebServ.ListarReclamosUnCiudadano(nCodigoCiud);

        call.enqueue(new Callback<List<ReclamoCiudadano>>() {
            @Override
            public void onResponse(Call<List<ReclamoCiudadano>> call, Response<List<ReclamoCiudadano>> response) {
                if(response.isSuccessful()){

                    for(ReclamoCiudadano oReclamoCiudadano: response.body()){

                        ReclamoCiudadano newobjReclamoCiudadano = new ReclamoCiudadano(
                                oReclamoCiudadano.getnCodigoRecC(),
                                oReclamoCiudadano.getcFechaRecC(),
                                oReclamoCiudadano.getcDescripcionRecC(),
                                oReclamoCiudadano.getlEstadoRecC(),
                                null,
                                null
                        );

                        listaReclamoCiudadano.add(newobjReclamoCiudadano);
                    }

                    if(listaReclamoCiudadano.size() ==0) txtReclamosRealizados.setText("No tiene Reclamos Registrados");

                    MisReclamosAdapter adapter = new MisReclamosAdapter(listaReclamoCiudadano);
                    recyclerReclamoCiudadano.setAdapter(adapter);

                }
                else {
                    Toast.makeText(getActivity(),"NO HAY RESPUESTA", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<ReclamoCiudadano>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

}