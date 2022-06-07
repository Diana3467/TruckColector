package com.example.appcollector.ui.Interfaces;

import com.example.appcollector.ui.Modelos.CalleZona;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Path;

public interface WebServicesInterface {

//    @GET("callezona")
    @HTTP(method = "POST", path = "callezona/", hasBody = true)
    public Call<List<CalleZona>> find (@Body CalleZona ocalleZona);

}