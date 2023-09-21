package com.example.examen.ui.farmacia;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private MutableLiveData<List<Farmacia>> listaFarmacias;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        listaFarmacias = new MutableLiveData<>();
        cargarFarmacias();
    }

    public LiveData<List<Farmacia>> getListaFarmacias() {
        return listaFarmacias;
    }

    private void cargarFarmacias() {
        List<Farmacia> farmacias = new ArrayList<>();
        farmacias.add(new Farmacia("FARMACIA SANTA TERESA", "-33.29047", "-66.33675", "Av. Justo Daract & Almte Brown, D5700 San Luis"));
        farmacias.add(new Farmacia("farmacia los alamos", "-33.29648", "-66.33912", "San Martín 1196, D5700 San Luis"));
        listaFarmacias.setValue(farmacias);
    }


}