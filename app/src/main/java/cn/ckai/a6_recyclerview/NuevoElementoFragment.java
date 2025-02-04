package cn.ckai.a6_recyclerview;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import cn.ckai.a6_recyclerview.databinding.FragmentNuevoElementoBinding;

public class NuevoElementoFragment extends Fragment {
    private static final int PERMISSION_REQUEST_READ_MEDIA = 1;
    private FragmentNuevoElementoBinding binding;
    private String selectedImageId; // 用于存储图片的自定义 ID
    private NavController navController;
    private ActivityResultLauncher<Intent> pickImageLauncher;
    private Uri selectedImageUri;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentNuevoElementoBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ElementosViewModel elementosViewModel = new ViewModelProvider(requireActivity()).get(ElementosViewModel.class);
        navController = Navigation.findNavController(view);

        binding.selectBookImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImagePicker();
            }
        });

        binding.crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre_libro = binding.nombreLibro.getText().toString().trim();
                String nombre_author = binding.nombreAuthor.getText().toString().trim();
                String description = binding.descripcion.getText().toString().trim();
                String ISBN = binding.ISBN.getText().toString().trim();

                if (nombre_libro.isEmpty() || nombre_author.isEmpty() || description.isEmpty() || ISBN.isEmpty()) {
                    Toast.makeText(requireContext(), "请填写所有必填字段", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isValidISBN(ISBN)) {
                    Toast.makeText(requireContext(), "请输入有效的 ISBN 码", Toast.LENGTH_SHORT).show();
                    return;
                }

                Elemento elemento = new Elemento(nombre_libro, description, ISBN, nombre_author, selectedImageId);
                elementosViewModel.insertar(elemento);
                navController.popBackStack();
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pickImageLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == getActivity().RESULT_OK) {
                            Intent data = result.getData();
                            if (data != null) {
                                selectedImageUri = data.getData();
                                // 调用保存图片到内部存储的方法
                                selectedImageId = saveImageToInternalStorage(selectedImageUri);
                                if (selectedImageId != null) {
                                    // 保存成功，可以在这里更新 UI 显示图片
                                    try {
                                        Bitmap bitmap = getBitmapFromUri(selectedImageUri);
                                        binding.bookImagePreview.setImageBitmap(bitmap);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                        Toast.makeText(requireContext(), "加载图片失败", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(requireContext(), "保存图片失败", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                });
    }

    private void openImagePicker() {
        String permission;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permission = Manifest.permission.READ_MEDIA_IMAGES;
        } else {
            permission = Manifest.permission.READ_EXTERNAL_STORAGE;
        }

        if (ContextCompat.checkSelfPermission(requireContext(), permission)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{permission},
                    PERMISSION_REQUEST_READ_MEDIA);
        } else {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            pickImageLauncher.launch(intent);
        }
    }

    /**
     * 将选择的图片保存到应用的内部存储，并返回唯一的图片 ID
     *
     * @param imageUri 选择图片的 Uri
     * @return 保存成功返回图片 ID，失败返回 null
     */
    private String saveImageToInternalStorage(Uri imageUri) {
        try {
            InputStream inputStream = requireContext().getContentResolver().openInputStream(imageUri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

            // 生成唯一的文件名作为图片 ID
            String imageId = UUID.randomUUID().toString() + ".jpg";
            File file = new File(requireContext().getFilesDir(), imageId);
            FileOutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.close();

            return imageId;
        } catch (IOException e) {
            Log.e("NuevoElementoFragment", "保存图片失败: " + e.getMessage());
            return null;
        }
    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        InputStream inputStream = requireContext().getContentResolver().openInputStream(uri);
        return BitmapFactory.decodeStream(inputStream);
    }

    private boolean isValidISBN(String isbn) {
        return isbn.matches("\\d{10}|\\d{13}");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}