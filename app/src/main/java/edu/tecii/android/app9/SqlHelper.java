package edu.tecii.android.app9;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Adrian on 23/10/2017.
 */

public class SqlHelper extends SQLiteOpenHelper {

    final static String baseDatos = "BaseD.db";
    String tabla1 = "Contactos";
    String columNombre = "Nombre";
    String columApellido = "Apellido";
    String columId = "Id";

    public SqlHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, baseDatos, factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE " + tabla1 + " (" + columId + " INTEGER PRIMARY KEY, "
                + columNombre + " TEXT, " + columApellido + " TEXT)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROPE IF EXIST " + tabla1);
        this.onCreate(sqLiteDatabase);
    }

    public void insertarContacto(Contact contact) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores;
        valores = new ContentValues();
        valores.put(columNombre, contact.Nombre);
        valores.put(columApellido, contact.Apellido);
        db.insert(tabla1, null, valores);
        //db.update();
        //db.delete();
        db.close();
    }

    public void actualizarContacto(Contact contact) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores;
        valores = new ContentValues();
        valores.put(columNombre, contact.Nombre);
        valores.put(columApellido, contact.Apellido);
        //db.update(tabla1, valores, columId = ""+contact.Telefono, null);
        db.update(tabla1, valores, columId + " = ? ", new String[]{contact.Telefono + ""});
        db.close();
    }

    public void eliminarContacto(Contact contact) {

        SQLiteDatabase db = getWritableDatabase();
        db.delete(tabla1, columId + " = ? ", new String[]{contact.Telefono + ""});
        db.close();
    }

    public ArrayList<Contact> obtenerContactos() {

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(tabla1, new String[]{columId, columNombre, columApellido}, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();//Se puede utilizar. No es necesario para este ejemplo
            ArrayList<Contact> lista = new ArrayList<>();
            while(!cursor.isAfterLast()) {
                Contact contacto = new Contact();
                contacto.Nombre = cursor.getString(1);
                contacto.Apellido = cursor.getString(2);
                contacto.Telefono = cursor.getString(0);
                lista.add(contacto);
                cursor.moveToNext();
            }
            db.close();
            return lista;
        } else {
            db.close();
            return null;
        }
    }
}
