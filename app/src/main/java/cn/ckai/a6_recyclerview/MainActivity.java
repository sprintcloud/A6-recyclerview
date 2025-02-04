package cn.ckai.a6_recyclerview;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import java.util.Objects;

import cn.ckai.a6_recyclerview.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ElementosViewModel elementosViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((binding = ActivityMainBinding.inflate(getLayoutInflater())).getRoot());

        elementosViewModel = new ViewModelProvider(this).get(ElementosViewModel.class);

        if (getSupportActionBar() != null) {
            Objects.requireNonNull(getSupportActionBar()).hide();
        }
        NavController navController = ((NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment)).getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavView, navController);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller,
                                             @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if (destination.getId() == R.id.nuevoElementoFragment
                        || destination.getId() == R.id.mostrarElementoFragment) {
                    binding.bottomNavView.setVisibility(View.GONE);
                } else {
                    binding.bottomNavView.setVisibility(View.VISIBLE);
                }

                if (destination.getId() == R.id.recyclerBuscarFragment) {
                    binding.searchView.setVisibility(View.VISIBLE);
                    binding.searchView.setIconified(false);
                    binding.searchView.requestFocusFromTouch();
                } else {
                    binding.searchView.setVisibility(View.GONE);
                }

                binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) { return false; }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        elementosViewModel.establecerTerminoBusqueda(newText);
                        return false;
                    }
                });
            }
        });
    }
}
