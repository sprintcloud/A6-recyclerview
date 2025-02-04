package cn.ckai.a6_recyclerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import cn.ckai.a6_recyclerview.databinding.FragmentRecyclerElementosBinding;

public class RecyclerValoracionFragment extends RecyclerElementosFragment {
    private ElementosViewModel elementosViewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        elementosViewModel = new ViewModelProvider(requireActivity()).get(ElementosViewModel.class);

        // 观察数据变化
        elementosViewModel.masValorados().observe(getViewLifecycleOwner(), new Observer<List<Elemento>>() {
            @Override
            public void onChanged(List<Elemento> elementos) {
                ElementosAdapter adapter = (ElementosAdapter) binding.recyclerView.getAdapter();
                if (adapter != null) {
                    adapter.establecerLista(elementos);
                }
            }
        });
    }
    @Override
    LiveData<List<Elemento>> obtenerElementos() {
            return elementosViewModel.masValorados();
    }
}
