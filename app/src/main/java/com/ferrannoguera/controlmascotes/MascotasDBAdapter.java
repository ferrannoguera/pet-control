package com.ferrannoguera.controlmascotes;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.SQLException;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

        import java.sql.Blob;
        import java.util.ArrayList;

/**
 * Created by ferran.noguera on 6/1/16.
 */
public class MascotasDBAdapter{

    MascotasDB mascotasDB;

    public MascotasDBAdapter(Context context){

        mascotasDB = new MascotasDB(context);
    }

    public long maininsert(String nomm,String nomt,String datn,String numx){
        SQLiteDatabase db = mascotasDB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MascotasDB.N_MASC, nomm);
        contentValues.put(MascotasDB.N_TIP,nomt);
        contentValues.put(MascotasDB.DATN, datn);
        contentValues.put(MascotasDB.NUMX, numx);
        long id = db.insert(MascotasDB.TABLE_NAME, null, contentValues); //-id if something wrong
        return id;
    }


    public void borrarMasc(String nom){
        SQLiteDatabase db = mascotasDB.getWritableDatabase();
        String query = "DELETE FROM "+ mascotasDB.TABLE_NAME + " WHERE " + mascotasDB.N_MASC + " IN ('"
                + nom + "')";
        db.execSQL(query);
        query = "DELETE FROM "+ mascotasDB.ITABLE_NAME + " WHERE " + mascotasDB.N_MASC + " IN ('"
                + nom + "')";
        db.execSQL(query);
        query = "DELETE FROM "+ mascotasDB.ESPTABLE_NAME + " WHERE " + mascotasDB.N_MASC + " IN ('"
                + nom + "')";
        db.execSQL(query);
        query = "DELETE FROM "+ mascotasDB.DATTABLE_NAME + " WHERE " + mascotasDB.N_MASC + " IN ('"
                + nom + "')";
        db.execSQL(query);
    }

    public void borrarData(String nom, String d, String m, String a) {
        SQLiteDatabase db = mascotasDB.getWritableDatabase();
        String query = "DELETE FROM "+ mascotasDB.DATTABLE_NAME + " WHERE " + mascotasDB.N_MASC + " IN ('"
                + nom + "') AND "+mascotasDB.DIA+" IN ('" + d + "') AND "+mascotasDB.MES+" IN ('"+m+ "') AND "
                +mascotasDB.ANY+" IN ('" + a + "')";

        db.execSQL(query);
    }


    public long insertImg(String nxip, byte[] img){
        SQLiteDatabase db = mascotasDB.getWritableDatabase();
        String query = "DELETE FROM "+ mascotasDB.ITABLE_NAME + " WHERE " + mascotasDB.N_MASC + " IN ('"
                + nxip + "')";
        db.execSQL(query);
        ContentValues contentValues = new ContentValues();
        contentValues.put(MascotasDB.N_MASC, nxip);
        contentValues.put(MascotasDB.IMAGE, img);
        long id = db.insert(MascotasDB.ITABLE_NAME,null,contentValues);
        return id;
    }

    public long inserttesp(String nomm, String ralph){
        SQLiteDatabase db = mascotasDB.getWritableDatabase();
        String query = "DELETE FROM " + mascotasDB.ESPTABLE_NAME + " WHERE " + mascotasDB.N_MASC + " IN ('"
                + nomm + "')";
        db.execSQL(query);
        ContentValues contentValues = new ContentValues();
        contentValues.put(MascotasDB.N_MASC, nomm);
        contentValues.put(MascotasDB.ESPECIAL, ralph);
        long id = db.insert(MascotasDB.ESPTABLE_NAME, null,contentValues);
        return id;
    }

    public long insertdata(String nxip, String dia,String mes,String any,String tipus){
        SQLiteDatabase db = mascotasDB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MascotasDB.N_MASC, nxip);
        contentValues.put(MascotasDB.DIA, dia);
        contentValues.put(MascotasDB.MES, mes);
        contentValues.put(MascotasDB.ANY, any);
        contentValues.put(MascotasDB.TIPUS,tipus);
        long id = db.insert(MascotasDB.DATTABLE_NAME,null,contentValues);
        return id;
    }

    public String getDia(String name) {
        SQLiteDatabase db = mascotasDB.getWritableDatabase();
        String query = "SELECT * FROM " + mascotasDB.DATTABLE_NAME + " WHERE " + mascotasDB.N_MASC + " IN ('"
                + name + "')";
        Cursor c = db.rawQuery(query,null);
        c.moveToNext();
        String aux = c.getString(2);
        return aux;
    }

    public String getMes(String name) {
        SQLiteDatabase db = mascotasDB.getWritableDatabase();
        String query = "SELECT * FROM " + mascotasDB.DATTABLE_NAME + " WHERE " + mascotasDB.N_MASC + " IN ('"
                + name + "')";
        Cursor c = db.rawQuery(query,null);
        c.moveToNext();
        String aux = c.getString(3);
        return aux;
    }

    public String getAny(String name) {
        SQLiteDatabase db = mascotasDB.getWritableDatabase();
        String query = "SELECT * FROM " + mascotasDB.DATTABLE_NAME + " WHERE " + mascotasDB.N_MASC + " IN ('"
                + name + "')";
        Cursor c = db.rawQuery(query,null);
        c.moveToNext();
        String aux = c.getString(4);
        return aux;
    }

    public String getTesp(String name){
        SQLiteDatabase db = mascotasDB.getWritableDatabase();
        String query = "SELECT * FROM " + mascotasDB.ESPTABLE_NAME + " WHERE " + mascotasDB.N_MASC + " IN ('"
                + name + "')";
        Cursor c = db.rawQuery(query,null);
        c.moveToNext();
        String aux = c.getString(1);
        return aux;
    }

    public String getNTip(String name) {
        SQLiteDatabase db = mascotasDB.getWritableDatabase();
        String query = "SELECT * FROM " + mascotasDB.TABLE_NAME + " WHERE " + mascotasDB.N_MASC + " IN ('"
                + name + "')";
        Cursor c = db.rawQuery(query,null);
        c.moveToNext();
        String aux = c.getString(1);
        return aux;
    }

    public String getNomM(String name) {
        SQLiteDatabase db = mascotasDB.getWritableDatabase();
        String query = "SELECT * FROM " + mascotasDB.TABLE_NAME + " WHERE " + mascotasDB.N_MASC + " IN ('"
                + name + "')";
        Cursor c = db.rawQuery(query,null);
        c.moveToNext();
        String aux = c.getString(0);
        return aux;
    }

    public String getdatn(String name) {
        SQLiteDatabase db = mascotasDB.getWritableDatabase();
        String query = "SELECT * FROM " + mascotasDB.TABLE_NAME + " WHERE " + mascotasDB.N_MASC + " IN ('"
                + name + "')";
        Cursor c = db.rawQuery(query, null);
        c.moveToNext();
        String aux = c.getString(2);
        return aux;
    }

    public String getnumx(String name) {
        SQLiteDatabase db = mascotasDB.getWritableDatabase();
        String query = "SELECT * FROM " + mascotasDB.TABLE_NAME + " WHERE " + mascotasDB.N_MASC + " IN ('"
                + name + "')";
        Cursor c = db.rawQuery(query, null);
        c.moveToNext();
        String aux = c.getString(3);
        return aux;
    }


    public byte[] getimg(String name){
        SQLiteDatabase db = mascotasDB.getWritableDatabase();
        String query = "SELECT * FROM " + mascotasDB.ITABLE_NAME + " WHERE " + mascotasDB.N_MASC + " IN ('"
                + name + "')";
        Cursor c = db.rawQuery(query,null);
        c.moveToNext();
        byte[] aux = new byte[]{};
        aux = c.getBlob(1);
        return aux;
    }

    public String[] getallpetnames() {
        SQLiteDatabase db = mascotasDB.getWritableDatabase();
        String[] columns = {MascotasDB.N_MASC, MascotasDB.N_TIP, MascotasDB.DATN,
                MascotasDB.NUMX};
        Cursor cursor = db.query(mascotasDB.TABLE_NAME, columns, null, null, null, null, null);
        ArrayList<String> noms = new ArrayList<String>();
        while (cursor.moveToNext()) {
            noms.add(cursor.getString(0));
        }
        String[] stringArray = noms.toArray(new String[0]); //////////////////FUNCIONARA????
        return stringArray;
    }


    public String[] getallvacunacio(){
        SQLiteDatabase db = mascotasDB.getWritableDatabase();
        String[] columns = {MascotasDB.DIA,MascotasDB.MES,MascotasDB.ANY,MascotasDB.TIPUS};
        Cursor cursor = db.query(mascotasDB.DATTABLE_NAME, columns, null, null, null, null, null);
        ArrayList<String> dates = new ArrayList<String>();
        while(cursor.moveToNext()){
            if (cursor.getString(3).equals("Vacunacion")) {
                dates.add(cursor.getString(0) + " / " + cursor.getString(1) + " / " + cursor.getString(2));
            }
        }
        String[] stringArray = dates.toArray(new String[0]);
        return stringArray;
    }

    public String[] getalldesparacitacio(){
        SQLiteDatabase db = mascotasDB.getWritableDatabase();
        String[] columns = {MascotasDB.DIA,MascotasDB.MES,MascotasDB.ANY,MascotasDB.TIPUS};
        Cursor cursor = db.query(mascotasDB.DATTABLE_NAME, columns,null,null,null,null,null);
        ArrayList<String> dates = new ArrayList<String>();
        while(cursor.moveToNext()){
            if (cursor.getString(3).equals("Desparacitacion")) {
                dates.add(cursor.getString(0) + " / " + cursor.getString(1) + " / " + cursor.getString(2));
            }
        }
        String[] stringArray = dates.toArray(new String[0]);
        return stringArray;
    }

    public String[] getallveterinari(){
        SQLiteDatabase db = mascotasDB.getWritableDatabase();
        String[] columns = {MascotasDB.DIA,MascotasDB.MES,MascotasDB.ANY,MascotasDB.TIPUS};
        Cursor cursor = db.query(mascotasDB.DATTABLE_NAME, columns,null,null,null,null,null);
        ArrayList<String> dates = new ArrayList<String>();
        while(cursor.moveToNext()){
            if (cursor.getString(3).equals("Veterinario"))
                dates.add(cursor.getString(0)+" / "+cursor.getString(1)+" / "+cursor.getString(2));
        }
        String[] stringArray = dates.toArray(new String[0]);
        return stringArray;
    }


    public String[] getallpetnamescancelar() {
        SQLiteDatabase db = mascotasDB.getWritableDatabase();
        String[] columns = {MascotasDB.N_MASC, MascotasDB.N_TIP, MascotasDB.DATN,
                MascotasDB.NUMX};
        Cursor cursor = db.query(mascotasDB.TABLE_NAME, columns, null, null, null, null, null);
        ArrayList<String> noms = new ArrayList<String>();
        while (cursor.moveToNext()) {
            noms.add(cursor.getString(0));
        }
        noms.add("CANCELAR");
        String[] stringArray = noms.toArray(new String[0]); //////////////////FUNCIONARA????
        return stringArray;
    }






    static class MascotasDB extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "controlmascotsesdatabase";
        private static final String TABLE_NAME = "CNTRLMASCOTESTABLE";
        private static final int DATABASE_VERSION = 2; //augmentar per onUpgrade

        private static final String UID = "_id";

        private static final String N_MASC = "NomM";
        private static final String N_TIP = "NomT";
        private static final String DATN = "DatN";
        private static final String NUMX = "NumX";

        private static final String MAIN_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + N_MASC + " VARCHAR(255) PRIMARY KEY,"
                + " " + NUMX + " VARCHAR(255), "
                + " " + N_TIP + " VARCHAR(255), "
                + " " + DATN + " VARCHAR(255)); ";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        private Context context;

        private static final String ITABLE_NAME = "TAULA_IMATGES";
        private static final String IMAGE = "image";
        private static final String CREATE_TIMAGE = "CREATE TABLE " + ITABLE_NAME + " (" + N_MASC + " VARCHAR(255) PRIMARY KEY,"
                + " " + IMAGE + " BLOB);";

        private static final String ESPECIAL = "ralph";
        private static final String ESPTABLE_NAME = "TAULA_ESPECIAL";
        private static final String CREATE_ESP = "CREATE TABLE " + ESPTABLE_NAME + " (" + N_MASC + " VARCHAR(255) PRIMARY KEY,"
                + " " + ESPECIAL + " VARCHAR(255));";

        private static final String DIA = "dia";
        private static final String MES = "mes";
        private static final String ANY = "any";
        private static final String TIPUS = "tipus";
        private static final String DATTABLE_NAME = "TAULA_DATES";
        private static final String CREATE_DAT = "CREATE TABLE " + DATTABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " " + N_MASC + " VARCHAR(255), "
                + " " + DIA + " VARCHAR(255), "
                + " " + MES + " VARCHAR(255), "
                + " " + ANY + " VARCHAR(255), "
                + " " + TIPUS + " VARCHAR(255));";

        public MascotasDB(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //CREATE TABLE VIVZTABLE (_id INTEGER PRMIMARY KEY AUTOINCREMENT, NAME VARCHAR(255));
            try {
                db.execSQL(MAIN_TABLE);
                db.execSQL(CREATE_TIMAGE);
                db.execSQL(CREATE_ESP);
                db.execSQL(CREATE_DAT);
            } catch (SQLException e) {
                Message.message(context, "" + e);
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL(DROP_TABLE);
                Message.message(context, "onUpgrade called");
                onCreate(db);
            } catch (SQLException e) {
                Message.message(context, "" + e);
            }
        }
    }
}



