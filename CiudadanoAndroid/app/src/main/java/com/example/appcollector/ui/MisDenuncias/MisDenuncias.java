package com.example.appcollector.ui.MisDenuncias;

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
import com.example.appcollector.ui.Adapter.MisDenunciasAdapter;
import com.example.appcollector.ui.Adapter.MisReclamosAdapter;
import com.example.appcollector.ui.Interfaces.WebServicesInterface;
import com.example.appcollector.ui.Modelos.DenunciaCiudadano;
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

public class MisDenuncias extends Fragment {

    RecyclerView recyclerDenunciaCiudadano;
    ArrayList<DenunciaCiudadano> listaDenunciaCiudadano;

    TextView txtDenunciaRealizados;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mis_denuncias, container, false);

        listaDenunciaCiudadano = new ArrayList<>();
        recyclerDenunciaCiudadano = (RecyclerView)view.findViewById(R.id.idRecyclerObjectDenunciasRealizadas);
        recyclerDenunciaCiudadano.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerDenunciaCiudadano.setHasFixedSize(true);

        txtDenunciaRealizados = view.findViewById(R.id.txtDenunciasRealizadas);

        SharedPreferences preferencias = this.getActivity().getSharedPreferences(ARCHIVO_PREFRENCIAS, Context.MODE_PRIVATE);
        ListarDenunciasUnCiudadano(preferencias.getString("nCodigoCiud", ""));


        return view;
    }

    private void ListarDenunciasUnCiudadano(String nCodigoCiud) {
        String url = Util.url;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();

        WebServicesInterface CondWebServ = retrofit.create(WebServicesInterface.class);
        Call<List<DenunciaCiudadano>> call = CondWebServ.ListarDenunciasUnCiudadano(nCodigoCiud);

        call.enqueue(new Callback<List<DenunciaCiudadano>>() {
            @Override
            public void onResponse(Call<List<DenunciaCiudadano>> call, Response<List<DenunciaCiudadano>> response) {
                if(response.isSuccessful()){

                    for(DenunciaCiudadano oDenunciaCiudadano: response.body()){

                        DenunciaCiudadano newobjDenunciaCiudadano = new DenunciaCiudadano(
                                oDenunciaCiudadano.getnCodigoDenC(),
                                oDenunciaCiudadano.getcModoDenC(),
                                oDenunciaCiudadano.getcFechaDenC(),
                                null,
                                oDenunciaCiudadano.getcUbicacionDenC(),
                                oDenunciaCiudadano.getcDescripcionDenC(),
                                oDenunciaCiudadano.getlEstadoDenCo(),
                                null
                        );

                        listaDenunciaCiudadano.add(newobjDenunciaCiudadano);
                    }

                    if(listaDenunciaCiudadano.size() ==0) txtDenunciaRealizados.setText("No tiene Reclamos Registrados");

                    MisDenunciasAdapter adapter = new MisDenunciasAdapter(listaDenunciaCiudadano);
                    recyclerDenunciaCiudadano.setAdapter(adapter);

                }
                else {
                    Toast.makeText(getActivity(),"NO HAY RESPUESTA", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<DenunciaCiudadano>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

}