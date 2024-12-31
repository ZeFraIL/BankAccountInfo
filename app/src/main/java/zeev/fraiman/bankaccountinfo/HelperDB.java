package zeev.fraiman.bankaccountinfo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class HelperDB extends SQLiteOpenHelper {

    public static final String DB_FILE="account_info.db";

    public static final String ACCOUNT_TABLE="Account";
    public static final String ACCOUNT_NUM="acc_num";
    public static final String ACCOUNT_SUM="acc_sum";
    public static final String ACCOUNT_TIME="acc_time";
    public static final String ACCOUNT_DATE="acc_date";
    public static final String ACCOUNT_TYPE="acc_type";

    public static final String WORKERS_TABLE="Workers";
    public static final String WORKERS_NAME="worker_name";
    public static final String WORKERS_ID="worker_id";

    public HelperDB(@Nullable Context context) {
        super(context, DB_FILE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String st="CREATE TABLE IF NOT EXISTS "+ACCOUNT_TABLE;
        st+=" ( "+ACCOUNT_NUM+" TEXT, ";
        st+=   ACCOUNT_SUM+" TEXT, ";
        st+=   ACCOUNT_TIME+" TEXT, ";
        st+=   ACCOUNT_DATE+" TEXT, ";
        st+=   ACCOUNT_TYPE+" TEXT);";
        db.execSQL(st);

        st="CREATE TABLE IF NOT EXISTS "+WORKERS_TABLE;
        st+=" ( "+WORKERS_NAME+" TEXT, ";
        st+=   WORKERS_ID+" TEXT);";
        db.execSQL(st);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
