package sales.com.repository.sqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Gembit Soultan on 4/14/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String databaseName = "sales.db";
    private String createTableQuery;
    private String dropTableQuery;

    public DatabaseHelper(Context context, String createTableQuery, String dropTableQuery) {
        super(context, databaseName, null, 1);
        this.createTableQuery = createTableQuery;
        this.dropTableQuery = dropTableQuery;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(this.createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(this.dropTableQuery);
        this.onCreate(db);
    }

    public SQLiteDatabase getDatabase() {
        return this.getWritableDatabase();
    }
}
