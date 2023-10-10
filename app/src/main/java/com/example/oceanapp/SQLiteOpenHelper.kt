package com.example.oceanapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.Cursor
import android.database.sqlite.SQLiteOpenHelper

class SQLiteOpenHelper {
    class MyDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
        companion object {
            private const val DATABASE_NAME = "mydatabase.db"
            private const val DATABASE_VERSION = 1
        }

        override fun onCreate(db: SQLiteDatabase?) {

             db?.execSQL("""
                 CREATE TABLE IF NOT EXISTS lakes (LakeID INTEGER PRIMARY KEY,
        LakeName TEXT NOT NULL,
        Latitude REAL NOT NULL,
        Longitude REAL NOT NULL)
        """)

            db?.execSQL("""INSERT INTO lakes (LakeName, Latitude, Longitude)
VALUES
    ('Lake George', 43.426674, -73.712415),
    ('Cayuga Lake', 42.765611, -76.682178),
    ('Seneca Lake', 42.634485, -76.912588),
    ('Oneida Lake', 43.191822, -76.187014),
    ('Lake Champlain', 44.641052, -73.373276),
    ('Lake Ronkonkoma', 40.821202, -73.118095),
    ('Lake Lauderdale', 43.161318, -73.285200),
    ('Lake Oscawana', 41.380991, -73.874678),
    ('Meadow Lake', 40.734989, -73.848193),
    ('Lake Carmel', 41.499343, -73.680596),
    ('Lake Massawippi', 45.019606, -72.135933),
    ('Lake Neatahwanta', 43.450229, -76.298372),
    ('Lake Nymph', 42.179684, -73.874160),
    ('Lake Ontario', 43.528157, -76.243597),
    ('Lake Shenorock', 41.288898, -73.769356),
    ('Lake Skannatati', 41.221179, -74.004232),
    ('Lake Vanare', 43.532292, -73.839064)""")
                 

        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            print("haha")
        }

        fun getName(): Cursor? {

            // here we are creating a readable
            // variable of our database
            // as we want to read value from it
            val db = this.readableDatabase

            // below code returns a cursor to
            // read data from the database
            return db.rawQuery("SELECT * FROM " + "lakes", null)

        }
    }
}
