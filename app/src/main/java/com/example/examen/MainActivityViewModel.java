package com.example.examen;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Boolean> access;
    private MutableLiveData<String> error;
    private HashMap<String,String> users = new HashMap<>();


    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        users.put("admin","123");
    }

    public LiveData<Boolean> getAccess(){
        if(access == null){
            this.access = new MutableLiveData<>();
        }
        return access;
    }

    public LiveData<String> getError(){
        if(error == null){
            this.error = new MutableLiveData<>();
        }
        return error;
    }

    public void login(String username , String password){
        if(!users.containsKey(username)){
            error.setValue("Usuario no registrado");
        }else if(!users.get(username).equals(password)){
            error.setValue("Contraseña incorrecta");
        }else{
            access.setValue(true);
        }

    }
}
