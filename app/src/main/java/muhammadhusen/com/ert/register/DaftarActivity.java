package muhammadhusen.com.ert.register;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import muhammadhusen.com.ert.LoginActivity;
import muhammadhusen.com.ert.R;
import muhammadhusen.com.ert.api.RegisterAPI;
import muhammadhusen.com.ert.api.Value;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DaftarActivity extends AppCompatActivity {
private Toolbar mtoolbar;
private Button daftar;
private EditText tanggal,namalengkap,nohp,alamat,username,pass;
    public static final String URL = "http://192.168.43.123/ERT/api/";
    private ProgressDialog mprogres;
    final Calendar myCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

mtoolbar = (Toolbar) findViewById(R.id.regtoolbar);

        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Kembali");

tanggal = (EditText) findViewById(R.id.ttl);
namalengkap =(EditText) findViewById(R.id.namalngkp);
nohp =(EditText) findViewById(R.id.nohp);
alamat = (EditText) findViewById(R.id.alamat);
username = (EditText) findViewById(R.id.username);
pass =(EditText) findViewById(R.id.pass);
daftar=(Button) findViewById(R.id.bt_daftar);
        mprogres = new ProgressDialog(this);
        mprogres.setCancelable(false);
        mprogres.setMessage("mendaftarkan user");




        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(DaftarActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

daftar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        register();
    }
});


    }

   private  void register(){

       mprogres.show();
       String user = username.getText().toString();
       String nama = namalengkap.getText().toString();
       String no_hp = nohp.getText().toString();
       String pwd = pass.getText().toString();
       String alamatusr = alamat.getText().toString();
       String ttl = tanggal.getText().toString();
       Retrofit retrofit = new Retrofit.Builder()
               .baseUrl(URL)
               .addConverterFactory(GsonConverterFactory.create())
               .build();
       RegisterAPI api = retrofit.create(RegisterAPI.class);
       Call<Value> call = api.registerUser(nama,ttl,no_hp,alamatusr,user,pwd);
       call.enqueue(new Callback<Value>() {
           @Override
           public void onResponse(Call<Value> call, Response<Value> response) {
               String value = response.body().getValue();
               String message = response.body().getMessage();
               if (value.equals("1")) {
                   mprogres.dismiss();
                   Toast.makeText(DaftarActivity.this, message, Toast.LENGTH_SHORT).show();
                   clear();
                   Intent login = new Intent(DaftarActivity.this,LoginActivity.class);
                   startActivity(login);
               } else {
                   mprogres.dismiss();
                   Toast.makeText(DaftarActivity.this, message, Toast.LENGTH_SHORT).show();
               }


   } @Override
           public void onFailure(Call<Value> call, Throwable t) {
               t.printStackTrace();
               mprogres.dismiss();
               Toast.makeText(DaftarActivity.this, "Jaringan Error!", Toast.LENGTH_SHORT).show();
           }
       });

   }








    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        tanggal.setText(sdf.format(myCalendar.getTime()));
    }

    private void clear(){

        username.setText("");
        namalengkap.setText("");
        pass.setText("" );
        tanggal.setText("");
        nohp.setText("");
        alamat.setText("");


    }

    }

