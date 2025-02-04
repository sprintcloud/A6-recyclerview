package cn.ckai.a6_recyclerview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import java.io.File;

public class ImageLoader {

    public static void loadImageById(Context context, String imageId, ImageView imageView) {
        int resourceId = getResourceIdFromImageId(context, imageId);
        if (resourceId != 0) {
            Drawable drawable = ContextCompat.getDrawable(context, resourceId);
            imageView.setImageDrawable(drawable);
        } else {
            // 尝试从内部存储加载图片
            loadImageFromInternalStorage(context, imageId, imageView);
        }
    }

    private static int getResourceIdFromImageId(Context context, String imageId) {
        switch (imageId) {
            case "cien_anyos_de_soledad":
                return R.drawable.cien_anyos_de_soledad;
            case "mundo_ordinario":
                return R.drawable.mundo_ordinario;
            case "la_luna_y_las_seis_peniques":
                return R.drawable.la_luna_y_las_seis_peniques;
            case "vivir":
                return R.drawable.vivir;
            case "pequenyo_principe":
                return R.drawable.pequenyo_principe;
            case "codigo_da_vinci":
                return R.drawable.codigo_da_vinci;
            default:
                return 0;
        }
    }

    private static void loadImageFromInternalStorage(Context context, String imageId, ImageView imageView) {
        File file = new File(context.getFilesDir(), imageId);
        if (file.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            imageView.setImageBitmap(bitmap);
        }
    }
}