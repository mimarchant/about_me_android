package com.example.about_me;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

public class CircularImageView extends AppCompatImageView {

    public CircularImageView(Context context) {
        super(context);
    }

    public CircularImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircularImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        BitmapDrawable drawable = (BitmapDrawable) getDrawable();
        if (drawable != null) {
            if (getWidth() == 0 || getHeight() == 0) {
                return;
            }

            Bitmap bitmap = drawable.getBitmap().copy(Bitmap.Config.ARGB_8888, true);

            int w = getWidth(), h = getHeight();

            Bitmap roundBitmap = getCroppedBitmap(bitmap, w < h ? w : h);
            canvas.drawBitmap(roundBitmap, 0, 0, null);
        } else {
            super.onDraw(canvas);
        }
    }

    private static Bitmap getCroppedBitmap(Bitmap bmp, int radius) {
        Bitmap sbmp;
        if (bmp.getWidth() != radius || bmp.getHeight() != radius) {
            float smallest = Math.min(bmp.getWidth(), bmp.getHeight());
            float factor = smallest / radius;
            sbmp = Bitmap.createScaledBitmap(bmp, (int)(bmp.getWidth() / factor), (int)(bmp.getHeight() / factor), false);
        } else {
            sbmp = bmp;
        }

        Bitmap output = Bitmap.createBitmap(radius, radius,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        Paint paint = new Paint();
        BitmapShader shader = new BitmapShader(sbmp, Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        paint.setColor(Color.parseColor("#BAB399"));

        canvas.drawCircle(radius / 2, radius / 2,
                radius / 2 - 0.1f, paint);

        return output;
    }
}
