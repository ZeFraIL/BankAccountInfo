package zeev.fraiman.bankaccountinfo;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ViewListOfActions extends AppCompatActivity {

    Context context;
    HelperDB helperDB;
    SQLiteDatabase db;
    Cursor cursor;
    Account account;
    ArrayList<Account> accounts;
    ArrayList<String> accNum;
    ArrayAdapter<String> adapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_of_actions);


        initComponents();

        buildInfo();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                account=accounts.get(position);
                viewAccInfo(account);
            }
        });
    }

    private void viewAccInfo(Account account) {
        AlertDialog.Builder adb=new AlertDialog.Builder(context);
        adb.setTitle("Information\n======================");
        adb.setMessage(account.toString());
        adb.setCancelable(false);
        adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        adb.create().show();
    }

    private void buildInfo() {
        accNum=new ArrayList<>();
        accounts=new ArrayList<>();
        db=helperDB.getReadableDatabase();
        cursor=db.query(helperDB.ACCOUNT_TABLE,
                null,null,null,
                null,null,null);
        if (cursor.getCount()==0)  {
            Toast.makeText(context, "Not found information", Toast.LENGTH_SHORT).show();
            db.close();
            return;
        }
        cursor.moveToFirst();
        while (!cursor.isAfterLast())  {
            account=new Account("","","","","");
            account.setAccNum(cursor.getString((int)cursor.getColumnIndex(helperDB.ACCOUNT_NUM)));
            account.setAccSum(cursor.getString((int)cursor.getColumnIndex(helperDB.ACCOUNT_SUM)));
            account.setAccType(cursor.getString((int)cursor.getColumnIndex(helperDB.ACCOUNT_TIME)));
            account.setAccDate(cursor.getString((int)cursor.getColumnIndex(helperDB.ACCOUNT_DATE)));
            account.setAccType(cursor.getString((int)cursor.getColumnIndex(helperDB.ACCOUNT_TYPE)));
            accounts.add(account);
            accNum.add(account.getAccNum());
            cursor.moveToNext();
        }
        db.close();
        adapter=new ArrayAdapter<>(context,
                android.R.layout.simple_list_item_1,
                accNum);
        lv.setAdapter(adapter);

    }

    private void initComponents() {
        context=this;
        helperDB=new HelperDB(context);
        lv=findViewById(R.id.lv);
    }
}