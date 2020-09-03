package com.example.schedulewithqr.model;

import android.database.Cursor;

public interface Mapper <T> {
     T collectObject(Cursor cursor);
}
