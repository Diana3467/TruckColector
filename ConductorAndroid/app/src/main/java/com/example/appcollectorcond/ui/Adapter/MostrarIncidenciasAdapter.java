package com.example.appcollectorcond.ui.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcollectorcond.R;
import com.example.appcollectorcond.ui.Modelos.AsigRutaCond;
import com.example.appcollectorcond.ui.Modelos.ReclamoConductor;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MostrarIncidenciasAdapter extends RecyclerView.Adapter<MostrarIncidenciasAdapter.UsuariosHolder>{

    List<ReclamoConductor> listaReclamoConductor;

    public MostrarIncidenciasAdapter(List<ReclamoConductor> listaReclamoConductor){
        this.listaReclamoConductor = listaReclamoConductor;
    }

    @NonNull
    @NotNull
    @Override
    public MostrarIncidenciasAdapter.UsuariosHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_view_mostrar_incidencias, parent, false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT );
        vista.setLayoutParams(layoutParams);
        return new MostrarIncidenciasAdapter.UsuariosHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MostrarIncidenciasAdapter.UsuariosHolder holder, int position) {

        String fecha = String.valueOf(listaReclamoConductor.get(position).getcFechaRecCo());
        String[] fechaparts = fecha.split("\\ ");
        String fecha1 = fechaparts[0]; // FECHA
        holder.txtFecha.setText( fecha1 );

        holder.txtPlaca.setText( "Placa: "+
                String.valueOf(listaReclamoConductor.get(position).getcPlacaCar()));

        holder.txtDescripcion.setText( "Incidente: "+
                String.valueOf(listaReclamoConductor.get(position).getcDescripcionRecCo()));

        if(listaReclamoConductor.get(position).getlEstadoRecCo().equals("True")){
            holder.txtEstado.setText( "Estado: "+ String.valueOf("Nuevo"));
        }
        else{
            holder.txtEstado.setText( "Estado: "+ String.valueOf("Finalizado"));
        }

    }

    @Override
    public int getItemCount() {return listaReclamoConductor.size();}

    public class UsuariosHolder extends RecyclerView.ViewHolder {
        TextView txtFecha, txtPlaca, txtDescripcion, txtEstado;

        public UsuariosHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txtFecha = itemView.findViewById(R.id.tvFecha_Inc);
            txtPlaca = itemView.findViewById(R.id.tvPlaca_Inc);
            txtDescripcion = itemView.findViewById(R.id.tvDescripcion_Inc);
            txtEstado = itemView.findViewById(R.id.tvEstado_Inc);
        }
    }
}
