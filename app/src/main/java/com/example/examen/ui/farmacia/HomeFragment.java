package com.example.examen.ui.farmacia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examen.R;


public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private FarmaciaAdapter farmaciaAdapter;
    private HomeViewModel farmaciaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        farmaciaViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        recyclerView = root.findViewById(R.id.recyclerViewFarmacias);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        farmaciaViewModel.getListaFarmacias().observe(getViewLifecycleOwner(), farmacias -> {
            farmaciaAdapter = new FarmaciaAdapter(requireContext(), farmacias);
            recyclerView.setAdapter(farmaciaAdapter);
        });

        return root;
    }

}