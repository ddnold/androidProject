package com.example.overgrowthapp.ui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.overgrowthapp.ui.home.plantPersonal;
import java.util.ArrayList;

public class PlantDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "plant_database";
    private static final int DATABASE_VERSION = 2;

    // Table name and column names
    private static final String TABLE_NAME = "plants";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_COMMON_ID = "common_id";
    private static final String COLUMN_BOTANICAL_ID = "botanical_id";
    private static final String COLUMN_IMG_SRC = "img_src";
    private static final String COLUMN_COMMON_ID_CAPS = "common_id_caps";
    private static final String COLUMN_FAMILY = "family";
    private static final String COLUMN_SOIL_PH = "soil_ph";
    private static final String COLUMN_SOIL = "soil";
    private static final String COLUMN_BLOOM_TIME = "bloom_time";
    private static final String COLUMN_HARDINESS_ZONE = "hardiness_zone";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_URL = "url";
    private static final String COLUMN_SUN = "sun";
    private static final String COLUMN_NATIVE_AREA = "native_area";
    private static final String COLUMN_COLOR = "color";
    private static final String COLUMN_SIZE = "size";
    private static final String COLUMN_TOXICITY = "toxicity";
    private static final String COLUMN_WATER = "water";
    private static final String COLUMN_BLOOM_COLOR = "bloom_color";
    private static final String COLUMN_GROWING_TIME = "growing_time";
    private static final String COLUMN_TIMER_START = "timer_start";
    private static final String COLUMN_TIMER_END = "timer_end";

    // SQL statement to create the plants table
    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_COMMON_ID + " TEXT, " +
                    COLUMN_BOTANICAL_ID + " TEXT, " +
                    COLUMN_IMG_SRC + " TEXT, " +
                    COLUMN_COMMON_ID_CAPS + " TEXT, " +
                    COLUMN_FAMILY + " TEXT, " +
                    COLUMN_SOIL_PH + " TEXT, " +
                    COLUMN_SOIL + " TEXT, " +
                    COLUMN_BLOOM_TIME + " TEXT, " +
                    COLUMN_HARDINESS_ZONE + " TEXT, " +
                    COLUMN_TYPE + " TEXT, " +
                    COLUMN_URL + " TEXT, " +
                    COLUMN_SUN + " TEXT, " +
                    COLUMN_NATIVE_AREA + " TEXT, " +
                    COLUMN_COLOR + " TEXT, " +
                    COLUMN_SIZE + " TEXT, " +
                    COLUMN_TOXICITY + " TEXT, " +
                    COLUMN_WATER + " TEXT, " +
                    COLUMN_BLOOM_COLOR + " TEXT, " +
                    COLUMN_GROWING_TIME + " TEXT, " +
                    COLUMN_TIMER_START + " LONG, " +
                    COLUMN_TIMER_END + " LONG)";

    public PlantDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(DROP_TABLE);
        db.execSQL(CREATE_TABLE);
    }

    public void deletePlant(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("plants", "id" + " = ?", new String[] { String.valueOf(id) });
        db.close();
    }



    public boolean addPlant(plantPersonal plant) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("common_id", plant.getCommonID());
        values.put("botanical_id", plant.getBotanicalID());
        values.put("img_src", plant.getImgSrc());
        values.put("common_id_caps", plant.getCommonIDCAPS());
        values.put("family", plant.getFamily());
        values.put("soil_ph", plant.getSoilPH());
        values.put("soil", plant.getSoil());
        values.put("bloom_time", plant.getBloomTime());
        values.put("hardiness_zone", plant.getHardinessZone());
        values.put("type", plant.getType());
        values.put("url", plant.getUrl());
        values.put("sun", plant.getSun());
        values.put("native_area", plant.getNativeArea());
        values.put("color", plant.getColor());
        values.put("size", plant.getSize());
        values.put("toxicity", plant.getToxicity());
        values.put("water", plant.getWater());
        values.put("bloom_color", plant.getBloomColor());
        values.put("growing_time", plant.getGrowingTime());
        values.put("timer_start", plant.getTimerStart());
        values.put("timer_end", plant.getTimerEnd());
        long result = db.insert("plants", null, values);
        return result != -1;
    }

    public boolean updateTimer(plantPersonal plantToChange) {
        if(plantToChange.timerStart == plantToChange.timerEnd){
            return false;
        }
        else {
            long schedule = plantToChange.timerEnd - plantToChange.timerStart;
            plantToChange.timerStart = System.currentTimeMillis();
            plantToChange.timerEnd = schedule + plantToChange.timerStart;
            return true;
        }
    }


    public ArrayList<plantPersonal> getAllPlants() {
        ArrayList<plantPersonal> plants = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM plants", null);
        if (cursor.moveToFirst()) {
            do {
                plantPersonal plant = new plantPersonal();
                plant.setId(cursor.getInt(cursor.getColumnIndex("id")));
                plant.setTimerEnd(cursor.getLong(cursor.getColumnIndex("timer_end")));
                plant.setTimerStart(cursor.getLong(cursor.getColumnIndex("timer_start")));
                plant.setCommonID(cursor.getString(cursor.getColumnIndex("common_id")));
                plant.setBotanicalID(cursor.getString(cursor.getColumnIndex("botanical_id")));
                plant.setImgSrc(cursor.getString(cursor.getColumnIndex("img_src")));
                plant.setCommonIDCAPS(cursor.getString(cursor.getColumnIndex("common_id_caps")));
                plant.setFamily(cursor.getString(cursor.getColumnIndex("family")));
                plant.setSoilPH(cursor.getString(cursor.getColumnIndex("soil_ph")));
                plant.setSoil(cursor.getString(cursor.getColumnIndex("soil")));
                plant.setBloomTime(cursor.getString(cursor.getColumnIndex("bloom_time")));
                plant.setHardinessZone(cursor.getString(cursor.getColumnIndex("hardiness_zone")));
                plant.setType(cursor.getString(cursor.getColumnIndex("type")));
                plant.setUrl(cursor.getString(cursor.getColumnIndex("url")));
                plant.setSun(cursor.getString(cursor.getColumnIndex("sun")));
                plant.setNativeArea(cursor.getString(cursor.getColumnIndex("native_area")));
                plant.setColor(cursor.getString(cursor.getColumnIndex("color")));
                plant.setSize(cursor.getString(cursor.getColumnIndex("size")));
                plant.setToxicity(cursor.getString(cursor.getColumnIndex("toxicity")));
                plant.setWater(cursor.getString(cursor.getColumnIndex("water")));
                plant.setBloomColor(cursor.getString(cursor.getColumnIndex("bloom_color")));
                plant.setGrowingTime(cursor.getString(cursor.getColumnIndex("growing_time")));
                plants.add(plant);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return plants;
    }




}

