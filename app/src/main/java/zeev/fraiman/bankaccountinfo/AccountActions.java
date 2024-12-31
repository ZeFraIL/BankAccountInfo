package zeev.fraiman.bankaccountinfo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Calendar;

public class AccountActions extends AppCompatActivity {

    Context context;
    TextView tvAction, tvWhen;
    EditText etMoney;
    RadioButton rbIn, rbOut;
    Button bTime, bDate, bAddAction, bViewAll;
    String stMoney="", stTime="", stDate="";
    int count=1;
    Intent go;
    Account account;
    HelperDB helperDB;
    SQLiteDatabase db;
    Calendar calendar;
    int year_now, month_now, day_now, hour_now, minute_now;
    int year_my, month_my, day_my, hour_my, minute_my;
    LocalDateTime localDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_account_actions);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initComponents();

        bAddAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                account.setAccNum("#"+count);
                stMoney=etMoney.getText().toString();
                if (stMoney.equals(""))  {
                    Toast.makeText(context,
                            "Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                account.setAccSum(stMoney);
                if (rbIn.isChecked())  {
                    account.setAccType("in");
                }
                if (rbOut.isChecked())  {
                    account.setAccType("out");
                }
                account.setAccDate(stDate);
                account.setAccTime(stTime);
                count++;
                etMoney.setText("");

                ContentValues cv=new ContentValues();

                cv.put(helperDB.ACCOUNT_NUM, account.getAccNum());
                cv.put(helperDB.ACCOUNT_SUM, account.getAccSum());
                cv.put(helperDB.ACCOUNT_TIME, account.getAccTime());
                cv.put(helperDB.ACCOUNT_DATE, account.getAccDate());
                cv.put(helperDB.ACCOUNT_TYPE, account.getAccType());


                db=helperDB.getWritableDatabase();
                db.insert(helperDB.ACCOUNT_TABLE, null, cv);
                db.close();
            }
        });

        bTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stTime="";
                TimePickerDialog tpd=new TimePickerDialog(context,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                hour_my=hourOfDay;
                                minute_my=minute;
                                stTime=""+hour_my+":"+minute_my;
                                tvWhen.setText(stDate+" in "+stTime);
                            }
                        },0,0,true);
                tpd.show();
            }
        });


        bDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd=new DatePickerDialog(context,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                year_my=year;
                                month_my=month;
                                day_my=dayOfMonth;
                                stDate=""+day_my+"/"+(month_my+1)+"/"+year_my;
                                tvWhen.setText(stDate+" in "+stTime);
                            }
                        },year_now,month_now,day_now);
                dpd.getDatePicker().setMinDate(System.currentTimeMillis());
                dpd.show();

            }
        });

        bViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(go);
            }
        });
    }

    private void initComponents() {
        context=AccountActions.this;
        tvAction=findViewById(R.id.tvAction);
        tvWhen=findViewById(R.id.tvWhen);
        etMoney=findViewById(R.id.etMoney);
        rbIn=findViewById(R.id.rbIn);
        rbOut=findViewById(R.id.rbOut);
        bTime=findViewById(R.id.bTime);
        bDate=findViewById(R.id.bDate);
        bAddAction=findViewById(R.id.bAddAction);
        bViewAll=findViewById(R.id.bViewAll);
        tvAction.setText("Action #"+count);
        go=new Intent(context, ViewListOfActions.class);
        helperDB=new HelperDB(context);
        account=new Account("","","nothing","nothing","");

        calendar=Calendar.getInstance();

        year_now=calendar.get(Calendar.YEAR);
        month_now=calendar.get(Calendar.MONTH);
        day_now=calendar.get(Calendar.DAY_OF_MONTH);
        hour_now=calendar.get(Calendar.HOUR);
        minute_now=calendar.get(Calendar.MINUTE);

        localDateTime=LocalDateTime.now();
        int year = localDateTime.getYear();
        Month month = localDateTime.getMonth();
        int dayOfMonth = localDateTime.getDayOfMonth();
        int hour = localDateTime.getHour();
        int minute = localDateTime.getMinute();
        int second = localDateTime.getSecond();
    }
}