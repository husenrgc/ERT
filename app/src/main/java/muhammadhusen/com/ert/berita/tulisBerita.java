package muhammadhusen.com.ert.berita;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import muhammadhusen.com.ert.R;
import muhammadhusen.com.ert.Utility.SharedPrefManager;
import muhammadhusen.com.ert.Utility.navHelper;
import muhammadhusen.com.ert.api.RegisterAPI;
import muhammadhusen.com.ert.api.Value;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class tulisBerita extends AppCompatActivity {
private Toolbar toolbar;
private Button uploadberita;
    private ProgressDialog mProgres;
    public static final String URL = "http://192.168.43.123/ERT/api/";
private EditText judul, isi, sumber;
SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tulis_berita);
        toolbar = (Toolbar) findViewById(R.id.tulistoolbar);
         judul = findViewById(R.id.judulberita);
         isi = findViewById(R.id.isiberita);
         sumber = findViewById(R.id.sumberberita);
         uploadberita =findViewById(R.id.uploadberita);
        sharedPrefManager = new SharedPrefManager(this);
        mProgres = new ProgressDialog(this);
        mProgres.setMessage("Upload Berita...");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Kembali");
        setupNav();



        uploadberita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tulisberita();
            }
        });

    }




    private void setupNav(){

        BottomNavigationView btv = (BottomNavigationView) findViewById(R.id.bottomNavViewBar);
        BottomNavigationView btv2 = (BottomNavigationView) findViewById(R.id.bottomNavViewBar1);
        btv.setVisibility(View.VISIBLE);
        btv2.setVisibility(View.GONE);
        navHelper.enablenav(tulisBerita.this,this, btv);
        Menu menu = btv.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
    }


    private void tulisberita(){
        mProgres.show();
        Calendar c1 = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        //SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
        String strdate1 = sdf1.format(c1.getTime());
        String tanggal = strdate1;
        String nama =  sharedPrefManager.getSPNama();
        String username = sharedPrefManager.getSpUsername();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)

                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RegisterAPI api = retrofit.create(RegisterAPI.class);
        Call<Value> call = api.tulisnews(username,nama,isi.getText().toString(),judul.getText().toString(),sumber.getText().toString(), tanggal);
     call.enqueue(new Callback<Value>() {
         @Override
         public void onResponse(Call<Value> call, Response<Value> response) {
             String value = response.body().getValue();
             String message = response.body().getMessage();
             if (value.equals("1")) {
                 mProgres.dismiss();
                 Toast.makeText(tulisBerita.this, message, Toast.LENGTH_SHORT).show();
                 startActivity(new Intent(tulisBerita.this, homeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                 overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                 finish();

             }else { mProgres.dismiss();
             Toast.makeText(tulisBerita.this, message, Toast.LENGTH_SHORT).show();}




         }

         @Override
         public void onFailure(Call<Value> call, Throwable t) {
             mProgres.dismiss();
         }
     });


    }



}
