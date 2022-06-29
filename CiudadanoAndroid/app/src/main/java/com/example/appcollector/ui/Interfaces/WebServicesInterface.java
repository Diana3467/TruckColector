package com.example.appcollector.ui.Interfaces;

import com.example.appcollector.ui.Modelos.CalleZona;
import com.example.appcollector.ui.Modelos.Ciudadano;
import com.example.appcollector.ui.Modelos.DenunciaCiudadano;
import com.example.appcollector.ui.Modelos.Horario;
import com.example.appcollector.ui.Modelos.ReclamoCiudadano;
import com.example.appcollector.ui.Modelos.Token;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Path;

public interface WebServicesInterface {

//    @GET("callezona")
    @HTTP(method = "POST", path = "ciudadano/autentificarciudadano", hasBody = true)
    public Call<Token> AutentificarCiudadano(@Body Ciudadano oCiudadano);

    @HTTP(method = "POST", path = "ciudadano/traeruno/{cDNICiud}", hasBody = true)
    public Call<Ciudadano> TraerDatosCiudadano(@Path("cDNICiud") String cDNICiud);

    @HTTP(method = "POST", path = "callezona/", hasBody = true)
    public Call<List<CalleZona>> find (@Body CalleZona ocalleZona);

    @HTTP(method = "POST", path = "ciudadano", hasBody = true)
    public Call<Token> GrabarCiudadano(@Body Ciudadano oCiudadano);

    @HTTP(method = "POST", path = "ciudadano/actualizar", hasBody = true)
    public Call<String> ActualizarCiudadano(@Body Ciudadano oCiudadano);

    @HTTP(method = "POST", path = "denunciaciudadano/insertar", hasBody = true)
    public Call<String> InsertarDenunciaCiudadano(@Body DenunciaCiudadano oDenunciaCiudadano);

    @HTTP(method = "POST", path = "denunciaciudadano/lista/{nCodigoCiud}", hasBody = true)
    public Call<List<DenunciaCiudadano>> ListarDenunciasUnCiudadano(@Path("nCodigoCiud") String nCodigoCiud);

    @HTTP(method = "POST", path = "ciudadano/traerhorario/{nCodigoCalle}", hasBody = true)
    public Call<Horario> TraerHorario(@Path("nCodigoCalle") String nCodigoCalle);

    @HTTP(method = "POST", path = "reclamociudadano/insertar", hasBody = true)
    public Call<String> InsertarReclamoCiudadano(@Body ReclamoCiudadano oReclamoCiudadano);

    @HTTP(method = "POST", path = "reclamociudadano/lista/{nCodigoCiud}", hasBody = true)
    public Call<List<ReclamoCiudadano>> ListarReclamosUnCiudadano(@Path("nCodigoCiud") String nCodigoCiud);

}