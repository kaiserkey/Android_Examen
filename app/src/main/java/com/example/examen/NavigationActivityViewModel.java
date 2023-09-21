package com.example.examen;


import android.app.AlertDialog;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

public class NavigationActivityViewModel {
    private MutableLiveData<Boolean> m;

    public MutableLiveData<Boolean> getM() {
        if (m == null) {
            m = new MutableLiveData<>();
        }
        return m;
    }

    public void dialogoSalida() { m.setValue(true); }

    public void salir(Context context){
        new AlertDialog.Builder(context)
                .setTitle("Salida")
                .setMessage("Desea salir?")
                .setPositiveButton("Si", (dialog, which) -> ((AppCompatActivity) context).finishAndRemoveTask())
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                .show();
    }
}
