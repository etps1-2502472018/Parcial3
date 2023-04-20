package sv.edu.utec.parcial3.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class adminsql extends SQLiteOpenHelper {
    public adminsql(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL("CREATE TABLE contactos (Nombre text, " +
                "Apellido text, " +
                "Telefono text," +
                "Email text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
