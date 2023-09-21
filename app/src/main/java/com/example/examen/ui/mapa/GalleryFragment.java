package com.example.examen.ui.mapa;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.examen.R;
import com.example.examen.ui.farmacia.Farmacia;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class GalleryFragment extends Fragment implements OnMapReadyCallback {

    private GalleryViewModel viewModel;
    private GoogleMap googleMap;
    private MapView mapView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        // Inicializa el ViewModel
        viewModel = new ViewModelProvider(this).get(GalleryViewModel.class);

        // Inicializa el MapView
        mapView = root.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        // Observa la ubicación del usuario desde el ViewModel
        viewModel.getMl().observe(getViewLifecycleOwner(), new Observer<Location>() {
            @Override
            public void onChanged(Location location) {
                actualizarUbicacionUsuario(location);
            }
        });

        viewModel.obtenerUbicacionUsuario();

        return root;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        LatLng ubicacionUsuario = viewModel.getUbicacion();

        if (ubicacionUsuario != null) {
            Location ubicacionLatLng = new Location(ubicacionUsuario.toString());
            actualizarUbicacionUsuario(ubicacionLatLng); // Pasa ubicacionLatLng en lugar de ubicacionUsuario
        }

        // Obtiene y agrega los marcadores de las farmacias cercanas
        viewModel.getFarmacias().observe(getViewLifecycleOwner(), new Observer<List<Farmacia>>() {
            @Override
            public void onChanged(List<Farmacia> farmacias) {
                agregarMarcadoresFarmacias(farmacias);
            }
        });

        viewModel.obtenerFarmaciasCercanas();
    }

    // Método para actualizar la ubicación del usuario en el mapa
    private void actualizarUbicacionUsuario(Location location) {
        if (googleMap != null && location != null) {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            //googleMap.clear();
            googleMap.addMarker(new MarkerOptions().position(latLng).title("Mi Ubicación"));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
        }
    }

    private void agregarMarcadoresFarmacias(List<Farmacia> farmacias) {
        if (googleMap != null) {
            googleMap.clear();

            int anchoDeseado = 60; // Ancho en píxeles
            int altoDeseado = 60;  // Alto en píxeles

            // Agregar marcadores de farmacias obtenidas desde la API
            if (farmacias != null && !farmacias.isEmpty()) {
                for (Farmacia farmacia : farmacias) {
                    LatLng latLng = new LatLng(Double.parseDouble(farmacia.getLat()), Double.parseDouble(farmacia.getLon()));


                    Bitmap iconoFarmacia = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.farmacia), anchoDeseado, altoDeseado, false);

                    MarkerOptions markerFarmacia = new MarkerOptions()
                            .position(latLng)
                            .title(farmacia.getNombre())
                            .snippet("Dirección: " + farmacia.getDireccion())
                            .icon(BitmapDescriptorFactory.fromBitmap(iconoFarmacia));

                    googleMap.addMarker(markerFarmacia);
                    System.out.println("Agregando marcador de farmacia: " + Double.parseDouble(farmacia.getLat()) + " " + Double.parseDouble(farmacia.getLon()));
                }
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}