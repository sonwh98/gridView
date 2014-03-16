package com.datayumyum.pos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.ImageView;

/**
 * Created by sto on 3/14/14.
 */
public class ItemButton extends ImageView {
    String name;

    public ItemButton(Context context, String name) {
        super(context);
        this.name = name;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setColor(Color.BLACK);

        canvas.drawText(name, 10, 10, p);
    }
}
