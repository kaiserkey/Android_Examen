package com.example.examen.ui.mapa;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.examen.ui.farmacia.Farmacia;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class GalleryViewModel extends AndroidViewModel {

    private Context context;
    private FusedLocationProviderClient fp;
    private MutableLiveData<Location> ml;
    private MutableLiveData<List<Farmacia>> farmacias;
    private LatLng ubicacion;

    public GalleryViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
        fp = LocationServices.getFusedLocationProviderClient(context);
    }

    public LiveData<Location> getMl() {
        if (ml == null) {
            ml = new MutableLiveData<>();
        }
        return ml;
    }

    public LiveData<List<Farmacia>> getFarmacias() {
        if (farmacias == null) {
            farmacias = new MutableLiveData<>();
        }
        return farmacias;
    }

    public LatLng getUbicacion() {
        return ubicacion;
    }

    public void obtenerUbicacionUsuario() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        Task<Location> tarea = fp.getLastLocation();
        tarea.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    ml.postValue(location);
                }
            }
        });
    }

    public void obtenerFarmaciasCercanas() {
        List<Farmacia> listaFarmacias = obtenerFarmaciasCercanasDesdeAPI();
        farmacias.postValue(listaFarmacias);
    }

    private List<Farmacia> obtenerFarmaciasCercanasDesdeAPI() {
        List<Farmacia> listaFarmacias = new ArrayList<>();

        // Agregar las farmacias a la lista
        listaFarmacias.add(new Farmacia("FARMACIA SANTA TERESA", "-33.29047", "-66.33675", "Av. Justo Daract & Almte Brown, D5700 San Luis"));
        listaFarmacias.add(new Farmacia("farmacia los alamos", "-33.29648", "-66.33912", "San Martín 1196, D5700 San Luis"));

        return listaFarmacias;
    }


}