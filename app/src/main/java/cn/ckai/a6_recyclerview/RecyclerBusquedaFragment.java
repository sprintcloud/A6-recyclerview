package cn.ckai.a6_recyclerview;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class RecyclerBusquedaFragment extends RecyclerElementosFragment {
    ElementosViewModel elementosViewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        elementosViewModel = new ViewModelProvider(requireActivity()).get(ElementosViewModel.class);
    }

    @Override
    LiveData<List<Elemento>> obtenerElementos() {
        return elementosViewModel.buscar();
    }


}   
