package com.example.appcollectorcond.ui.Principal;

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
import com.example.appcollectorcond.ui.Adapter.MostrarIncidenciasAdapter;
import com.example.appcollectorcond.ui.Interfaces.WebServicesInterface;
import com.example.appcollectorcond.ui.Modelos.AsigRutaCond;
import com.example.appcollectorcond.ui.Modelos.ReclamoConductor;
import com.example.appcollectorcond.ui.Util.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.appcollectorcond.ui.Util.Util.ARCHIVO_PREFRENCIAS;

public class MostrarIncidenciasFragment extends Fragment {
    RecyclerView recyclerReclamoConductor;
    ArrayList<ReclamoConductor> listaReclamoConductor;

    TextView txtIncidenciasRealizadas;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mostrar_incidencias, container, false);

        listaReclamoConductor = new ArrayList<>();
        recyclerReclamoConductor = (RecyclerView)view.findViewById(R.id.idRecyclerObjectReclamosRealizados);
        recyclerReclamoConductor.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerReclamoConductor.setHasFixedSize(true);

        txtIncidenciasRealizadas = view.findViewById(R.id.txtIncidenciasRealizadas);

        SharedPreferences preferencias = this.getActivity().getSharedPreferences(ARCHIVO_PREFRENCIAS, Context.MODE_PRIVATE);
        ListarReclamosUnConductor(preferencias.getString("nCodigoCond", ""));

        return view;
    }

    private void ListarReclamosUnConductor(String nCodigoCond) {
        String url = Util.url;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();

        WebServicesInterface CondWebServ = retrofit.create(WebServicesInterface.class);
        Call<List<ReclamoConductor>> call = CondWebServ.ListarReclamosUnConductor(nCodigoCond);

        call.enqueue(new Callback<List<ReclamoConductor>>() {
            @Override
            public void onResponse(Call<List<ReclamoConductor>> call, Response<List<ReclamoConductor>> response) {
                if(response.isSuccessful()){

                    for(ReclamoConductor oReclamoConductor : response.body()){

                        ReclamoConductor newobjReclamoConductor = new ReclamoConductor(
                                oReclamoConductor.getnCodigoRecCo(),
                                oReclamoConductor.getcFechaRecCo(),
                                oReclamoConductor.getcDescripcionRecCo(),
                                oReclamoConductor.getlEstadoRecCo(),
                                null,
                                null,
                                oReclamoConductor.getcPlacaCar()
                        );

                        listaReclamoConductor.add(newobjReclamoConductor);
                    }

                    if(listaReclamoConductor.size() ==0) txtIncidenciasRealizadas.setText("Sin Rutas Asignadas");

                    MostrarIncidenciasAdapter adapter = new MostrarIncidenciasAdapter(listaReclamoConductor);
                    recyclerReclamoConductor.setAdapter(adapter);

                }
                else {
                    Toast.makeText(getActivity(),"NO HAY RESPUESTA", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<ReclamoConductor>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }
}