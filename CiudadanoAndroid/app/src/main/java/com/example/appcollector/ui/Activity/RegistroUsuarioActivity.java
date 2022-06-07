package com.example.appcollector.ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appcollector.MainActivity;
import com.example.appcollector.R;
import com.example.appcollector.databinding.ActivityMainBinding;
import com.example.appcollector.ui.Interfaces.WebServicesInterface;
import com.example.appcollector.ui.Modelos.CalleZona;
import com.example.appcollector.ui.Util.Util;
import com.google.android.datatransport.runtime.retries.Retries;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


public class RegistroUsuarioActivity extends AppCompatActivity {


    EditText edtNombre, edtApellidoPaterno, edtApellidoMaterno, edtDNI, edtCelular, edtUbicacionZona, edtUbicacion, edtNumCasa;
    Button btnregistrar_nuevo_usuario;
    TextView nuevoUsuario;
    TextInputLayout txiUbi;
    boolean validar_sppiner = false, validar_sppinerZona = false;

    AutoCompleteTextView autoCompleteTextView;

    List<CalleZona> listaCalles;

    public static String [] tipo_zona = new String[]{
            "Umuto", "Incho", "Siglo XX", "Juan Parra del Riego", "La Esperenza", "San Pedro", "Centro Mercado",
            "Chacra Vieja", "Batanyacu - Polideportivo", "La Victoria", "Centro - Instituciones", "Lamblaspata",
            "Melchor Gonzales", "Cantuta - Covica - Mejorada Alta", "Millotingo", "Pio Pata - La Florida",
            "El Embudo - Agua de las Vírgenes", "Los Andes - Las Brisas", "Justicia Paz y Vida", "Registros y Terminal"
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        nuevoUsuario = findViewById(R.id.nuevoUsuario_Reg);
        edtNombre = findViewById(R.id.edtNombre_Reg);
        edtApellidoPaterno = findViewById(R.id.edtApellidoPaterno_Reg);
        edtApellidoMaterno = findViewById(R.id.edtApellidoMaterno_Reg);
        edtDNI = findViewById(R.id.edtDocumento_Reg);
        edtCelular = findViewById(R.id.edtCelular_Reg);

        autoCompleteTextView = findViewById(R.id.edtCalleUbicacion_Reg);

        edtUbicacionZona = findViewById(R.id.edtUbicacionZona_Reg);
        txiUbi = findViewById(R.id.txiUbi_Reg);
        edtUbicacion = findViewById(R.id.edtCalleUbicacion_Reg);
        edtNumCasa = findViewById(R.id.edtNumeroUbicacion_Reg);
        btnregistrar_nuevo_usuario = findViewById(R.id.btnRegistrarUsuario_Reg);

        txiUbi.setEnabled(false);


        ArrayAdapter<String> adapSppinerZona = new ArrayAdapter<>(this, R.layout.adaptador_sppiner, tipo_zona);
        AutoCompleteTextView autoCompleteTextViewZona = findViewById(R.id.edtUbicacionZona_Reg);
        autoCompleteTextViewZona.setAdapter(adapSppinerZona);

        autoCompleteTextViewZona.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                txiUbi.setEnabled(false);
                String cDescZona = autoCompleteTextViewZona.getText().toString();
                CalleZona oCalleZona = new CalleZona(null, null, cDescZona, null);

                find(oCalleZona);
                validar_sppinerZona = true;
//                edtUbicacion.setText(autoCompleteTextView.getText().toString());
                edtUbicacionZona.setError(null);
//                Toast.makeText(RegistroUsuarioActivity.this, autoCompleteTextViewZona.getText().toString(), Toast.LENGTH_SHORT).show();
                edtUbicacion.setText("");
                validar_sppiner = false;



            }
        });

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                CalleZona objCallezona = (CalleZona) adapterView.getItemAtPosition(i);

                String id = objCallezona.getnCodigoCalle().toString();


                edtUbicacion.setError(null);
                Toast.makeText(RegistroUsuarioActivity.this, id, Toast.LENGTH_SHORT).show();
                validar_sppiner = true;
            }
        });

        btnregistrar_nuevo_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validar()){
                    Toast.makeText(RegistroUsuarioActivity.this, "Datos Registrados!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(RegistroUsuarioActivity.this, "Ingrese correctamente los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        nuevoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegistroUsuarioActivity.this, LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });



    }

    public boolean validar(){

        boolean retorno = true;

        String nombre = edtNombre.getText().toString();
        String apellidopaterno = edtApellidoPaterno.getText().toString();
        String apellidomaterno = edtApellidoMaterno.getText().toString();
        String dni = edtDNI.getText().toString();
        String celular = edtCelular.getText().toString();
        String ubicacion = edtUbicacion.getText().toString();
        String num_casa = edtNumCasa.getText().toString();

        if (nombre.isEmpty())
        {
            edtNombre.setError("El Nombre no debe ser vacío");
            retorno = false;
        }
        if (apellidopaterno.isEmpty()){
            edtApellidoPaterno.setError("El Apellido Paterno no debe ser vacío");
            retorno = false;
        }
        if (apellidomaterno.isEmpty()){
            edtApellidoMaterno.setError("El Apellido Materno no debe ser vacío");
            retorno = false;
        }
        if (dni.isEmpty()){
            edtDNI.setError("El Documento no debe ser vacío");
            retorno = false;
        }
//        if (celular.isEmpty()){
//            edtCelular.setError("El Celular no debe ser vacío");
//            retorno = false;
//        }
        if (validar_sppinerZona == FALSE){
            edtUbicacionZona.setError("El nombre no debe ser vacío");
            retorno = false;
        }
        if (validar_sppiner == FALSE){
            edtUbicacion.setError("La dirección no debe ser vacío");
            retorno = false;
        }
        if (num_casa.isEmpty()){
            edtNumCasa.setError("El número de dirección no debe ser vacío");
            retorno = false;
        }

        return retorno;
    }



    public void find(CalleZona oCalleZona){
        // ((MainActivity)getActivity()).changeNavHeaderData();

        String url = Util.url;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();


        listaCalles = new ArrayList<>();
        ArrayAdapter<CalleZona> adaptadorSppiner = new ArrayAdapter<>(RegistroUsuarioActivity.this, R.layout.adaptador_sppiner, listaCalles);

        WebServicesInterface CallezonaWebServ = retrofit.create(WebServicesInterface.class);
        Call<List<CalleZona>> call = CallezonaWebServ.find(oCalleZona);

        call.enqueue(new Callback<List<CalleZona>>() {
            @Override
            public void onResponse(Call<List<CalleZona>> call, Response<List<CalleZona>> response) {

                    if(response.isSuccessful()){


                        for(CalleZona post : response.body()){

                            String nCodigoCalle = post.getnCodigoCalle();
                            String cNombreCalle = post.getcNombreCalle();
                            CalleZona calleZona = new CalleZona(nCodigoCalle, cNombreCalle, null, null);
                            listaCalles.add(calleZona);

//                            Toast.makeText(RegistroUsuarioActivity.this,"El Nombre de la calle es: "+ cNombreCalle, Toast.LENGTH_SHORT).show();
//                            Log.e("TAG","El Nombre de la calle es: " + cNombreCalle);
//                            adaptadorSppiner.setDropDownViewResource(R.layout.adaptador_sppiner);
//                            edtUbicacion.setAdapter(adaptadorSppiner);

                            autoCompleteTextView.setAdapter(adaptadorSppiner);
                        }
                        txiUbi.setEnabled(true);

                    }
                    else {
                        Toast.makeText(RegistroUsuarioActivity.this,"NO HAY RESPUESTA", Toast.LENGTH_SHORT).show();
                    }

            }

            @Override
            public void onFailure(Call<List<CalleZona>> call, Throwable t) {
                Toast.makeText(RegistroUsuarioActivity.this, t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });

    }
}