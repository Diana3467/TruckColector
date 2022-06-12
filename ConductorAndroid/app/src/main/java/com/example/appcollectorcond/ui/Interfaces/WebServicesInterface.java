package com.example.appcollectorcond.ui.Interfaces;

import com.example.appcollectorcond.ui.Modelos.AsigRutaCond;
import com.example.appcollectorcond.ui.Modelos.Conductor;
import com.example.appcollectorcond.ui.Modelos.Token;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HTTP;
import retrofit2.http.Path;

public interface WebServicesInterface {

//    @GET("api/callezona")
    @HTTP(method = "POST", path = "conductor/autentificarconductor", hasBody = true)
    public Call<Token> AutentificarConductor(@Body Conductor oConductor);


    @HTTP(method = "POST", path = "conductor/traeruno/{cDNICond}", hasBody = true)
    public Call<Conductor> TraerDatosConductor(@Path("cDNICond") String cDNICond);

    @HTTP(method = "POST", path = "conductor/actualizar", hasBody = true)
    public Call<String> ActualizarConductor(@Body Conductor oConductor);

    @HTTP(method = "POST", path = "asigrutacond/lista/{nCodigoCond}", hasBody = true)
    public Call<List<AsigRutaCond>> ListarRutaAsignada(@Path("nCodigoCond") String nCodigoCond);

}
