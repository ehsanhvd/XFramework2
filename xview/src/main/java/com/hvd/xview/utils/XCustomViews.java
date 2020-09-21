package com.hvd.xview.utils;

import android.graphics.Typeface;

import java.util.ArrayList;
import java.util.List;

public class XCustomViews {
    private static List<Typeface> typefaces = new ArrayList<>();

    public static List<Typeface> getTypefaces() {
        return typefaces;
    }

    public static void setTypefaces(List<Typeface> typefaces) {
        XCustomViews.typefaces = typefaces;
    }

    public static void addTypeFace(Typeface typeface) {
        XCustomViews.typefaces.add(typeface);
    }
}
