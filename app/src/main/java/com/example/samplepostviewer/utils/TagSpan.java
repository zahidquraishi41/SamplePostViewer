package com.example.samplepostviewer.utils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ReplacementSpan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;


public class TagSpan extends ReplacementSpan {
    public static final int CORNER_RADIUS = 5;
    private static final HashMap<String, SpannableString> cache = new HashMap<>();

    public static SpannableString getTag(String text) {
        if (cache.containsKey(text)) return cache.get(text);
        String space = "\u00A0";
        SpannableString span = new SpannableString(space + text + space);
        span.setSpan(new TagSpan(), 0, span.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        cache.put(text, span);
        return span;
    }

    private int uniqueColor(int hash) {
        int r = (hash >> 16) & 0xFF;
        int g = (hash >> 8) & 0xFF;
        int b = (hash) & 0xFF;

        float[] hsv = new float[3];
        Color.RGBToHSV(r, g, b, hsv);
        hsv[2] = Math.min(hsv[2], 0.5f);

        return Color.HSVToColor(hsv);
    }

    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, @Nullable Paint.FontMetricsInt fm) {
        return Math.round(paint.measureText(text, start, end));
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
        int originalColor = paint.getColor();
        int hashCode = text.subSequence(start, end).hashCode();
        paint.setColor(uniqueColor(hashCode));
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        canvas.drawRoundRect(
                x,
                y + fontMetrics.ascent,
                x + paint.measureText(text, start, end),
                y + fontMetrics.descent,
                CORNER_RADIUS,
                CORNER_RADIUS,
                paint
        );
        paint.setColor(originalColor);
        canvas.drawText(text, start, end, x, y, paint);
    }
}
