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

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AsignacionRutasAdapter extends RecyclerView.Adapter<AsignacionRutasAdapter.UsuariosHolder>{

    List<AsigRutaCond> listaAsigRutaCond;

    public AsignacionRutasAdapter(List<AsigRutaCond> listaAsigRutaCond){
        this.listaAsigRutaCond = listaAsigRutaCond;
    }

    @NonNull
    @NotNull
    @Override
    public AsignacionRutasAdapter.UsuariosHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_view_ruta_asignada, parent, false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT );
        vista.setLayoutParams(layoutParams);
        return new AsignacionRutasAdapter.UsuariosHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AsignacionRutasAdapter.UsuariosHolder holder, int position) {

        holder.txtEtiquetaDesechar.setText( String.valueOf(listaAsigRutaCond.get(position).getcInfoRuta()));
        holder.txtInicioFin.setText( "Lugares: "+ System.getProperty ("line.separator") +
                                        String.valueOf(listaAsigRutaCond.get(position).getcDescriRuta()));

        holder.txtDias.setText( "Dias: "+ System.getProperty ("line.separator") +
                String.valueOf(listaAsigRutaCond.get(position).getcDias()));

        String fechainicio = String.valueOf(listaAsigRutaCond.get(position).getdFechaInicio());
        String[] fechainicioparts = fechainicio.split("\\ ");
        String fechainicio1 = fechainicioparts[0]; // FECHA
//        String fechainicio2 = parts[1]; // HRS
        holder.txtFechaInicio.setText( "Fecha Inicio: "+ System.getProperty ("line.separator") +fechainicio1 );

        String fechafin = String.valueOf(listaAsigRutaCond.get(position).getdFechaFin());
        String[] fechafinparts = fechafin.split("\\ ");
        String fechafin1 = fechafinparts[0]; // FECHA
//        String fechainicio2 = parts[1]; // HRS
        holder.txtFechaFin.setText( "Fecha Fin: "+ System.getProperty ("line.separator") +fechafin1 );



        String horainicio = String.valueOf(listaAsigRutaCond.get(position).getcHoraInicioHor());
        String[] horainicioparts = horainicio.split("\\:");

        if(Integer.parseInt(horainicioparts[0])>=0 && Integer.parseInt(horainicioparts[0])<=12){
            String horainicio1 = horainicioparts[0] + ":" + horainicioparts[1]; // HH:MM
            holder.txtHoraInicio.setText( "Hora Inicio: " + horainicio1 + "  a.m.");
        }
        else {
            int hi = Integer.parseInt(horainicioparts[0]);
            hi = hi-12;
            String horainicio1 = String.valueOf(hi) + ":" + horainicioparts[1]; // HH:MM

            holder.txtHoraInicio.setText( "Hora Inicio: " + horainicio1 + "  p.m.");
        }




        String horafin = String.valueOf(listaAsigRutaCond.get(position).getcHoraFinHor());
        String[] horafinparts = horafin.split("\\:");

        if(Integer.parseInt(horafinparts[0])>=0 && Integer.parseInt(horafinparts[0])<=12){

            String horafin1 = horafinparts[0] + ":" + horafinparts[1]; // HH:MM
            holder.txtHoraFin.setText( "Hora Fin: " + horafin1 + "  a.m.");
        }
        else{
            int hf = Integer.parseInt(horafinparts[0]);
            hf = hf-12;
            String horafin1 = String.valueOf(hf) + ":" + horafinparts[1]; // HH:MM
            holder.txtHoraFin.setText( "Hora Fin: " + horafin1 + "  p.m.");
        }



//        holder.txtHoraFin.setText( String.valueOf(listaAsigRutaCond.get(position).getcHoraFinHor()));

        holder.imagen.setImageResource(R.drawable.camion_recolector_img);
    }

    @Override
    public int getItemCount() {return listaAsigRutaCond.size();}

    public class UsuariosHolder extends RecyclerView.ViewHolder {

        TextView txtEtiquetaDesechar, txtInicioFin, txtFechaInicio, txtFechaFin, txtHoraInicio, txtHoraFin, txtDias;
        ImageView imagen;

        public UsuariosHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imagen =itemView.findViewById(R.id.imgDetalleDesechar);
            txtEtiquetaDesechar =itemView.findViewById(R.id.tvEtiquetaDesechar);
            txtInicioFin =itemView.findViewById(R.id.tvInicioFin);
            txtDias =itemView.findViewById(R.id.tvDias);
            txtFechaInicio =itemView.findViewById(R.id.tvFechaInicio);
            txtFechaFin =itemView.findViewById(R.id.tvFechaFin);
            txtHoraInicio =itemView.findViewById(R.id.tvHoraInicio);
            txtHoraFin =itemView.findViewById(R.id.tvHoraFin);
        }
    }
}
