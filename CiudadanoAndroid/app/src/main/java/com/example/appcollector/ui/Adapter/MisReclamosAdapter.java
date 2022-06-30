package com.example.appcollector.ui.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcollector.R;
import com.example.appcollector.ui.Modelos.ReclamoCiudadano;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MisReclamosAdapter extends RecyclerView.Adapter<MisReclamosAdapter.MisReclamosHolder>{

    List<ReclamoCiudadano> listaReclamoCiudadano;

    public MisReclamosAdapter(List<ReclamoCiudadano> listaReclamoCiudadano) {
        this.listaReclamoCiudadano = listaReclamoCiudadano;
    }

    @NonNull
    @NotNull
    @Override
    public MisReclamosAdapter.MisReclamosHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_view_mis_reclamos, parent, false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT );
        vista.setLayoutParams(layoutParams);
        return new MisReclamosAdapter.MisReclamosHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MisReclamosAdapter.MisReclamosHolder holder, int position) {

        String date = String.valueOf(listaReclamoCiudadano.get(position).getcFechaRecC());
        String[] dateparts = date.split("\\ ");
        String date1 = dateparts[0]; // FECHA
        String date2 = dateparts[1]; // HORA

        String fecha = String.valueOf(date1);
        String[] fechaparts = fecha.split("\\/");
        String fecha1 = fechaparts[0]; // Mes
        String fecha2 = fechaparts[1]; // Dia
        String fecha3 = fechaparts[2]; // Año
        String FechaEnviar = fecha2 + "/" + fecha1 + "/" + fecha3;

        String hora = String.valueOf(date2);
        String[] horaparts = hora.split("\\:");
        String hora1 = horaparts[0]; // HH
        String hora2 = horaparts[1]; // MM
        String HoraEnviar = hora1 + ":" + hora2;

        String FechaHoraEnviar = FechaEnviar + " (" + HoraEnviar + ")";
        holder.txtFecha.setText( FechaHoraEnviar );

        holder.txtDescripcion.setText( String.valueOf(listaReclamoCiudadano.get(position).getcDescripcionRecC()));

        if(listaReclamoCiudadano.get(position).getlEstadoRecC().equals("True")){
            holder.txtEstado.setText("Nuevo");
        }
        else{
            holder.txtEstado.setText("Finalizado");
        }
    }

    @Override
    public int getItemCount() {return listaReclamoCiudadano.size();}

    public class MisReclamosHolder extends RecyclerView.ViewHolder {

        TextView txtFecha, txtDescripcion, txtEstado;

        public MisReclamosHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            txtFecha = itemView.findViewById(R.id.tvFecha_Rec);
            txtDescripcion = itemView.findViewById(R.id.tvDescripcion_Rec);
            txtEstado = itemView.findViewById(R.id.tvEstado_Rec);
        }
    }
}
