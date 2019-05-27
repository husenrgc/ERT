package muhammadhusen.com.ert;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import muhammadhusen.com.ert.Utility.SharedPrefManager;
import muhammadhusen.com.ert.api.RegisterAPI;
import muhammadhusen.com.ert.api.Value;
import muhammadhusen.com.ert.register.DaftarActivity;
import muhammadhusen.com.ert.result.ResultUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
private Button login,daftar;
private EditText username,pass;
    public static final String URL = "http://192.168.43.123/ERT/api/";
    private List<ResultUser> results = new ArrayList<>();
    private ProgressDialog mprogres;
    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
setupwidgedts();

buttonlistener();



    }


//    validasi user

    public void requestlogin(){

        String usrnm = username.getText().toString();
        String pwd = pass.getText().toString();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterAPI api = retrofit.create(RegisterAPI.class);
        Call<Value> call = api.loginUser(usrnm,pwd);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {

                String value = response.body().getValue();

                String message = "Berhasil Login";
                if (value.equals("1")) {
                    mprogres.dismiss();
                    results = response.body().getResultUsers();
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                    ResultUser rs = results.get(0);

                    String nama =  rs.getNama();
                    String ttl = rs.getTtl();
                    String usernames = rs.getUsername();
                    String alamat = rs.getAlamat();
                    String pp = rs.getFoto_profil();
                    String nohp = rs.getNo_hp();
                    String level =rs.getLevel();


                    sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA,nama );
                    sharedPrefManager.saveSPString(SharedPrefManager.SP_TTL,ttl);
                    sharedPrefManager.saveSPString(sharedPrefManager.SP_ALAMAT,alamat);
                    sharedPrefManager.saveSPString(sharedPrefManager.SP_NO_HP,nohp);
                    sharedPrefManager.saveSPString(sharedPrefManager.SP_FOTO_PROFIL,pp);
                    sharedPrefManager.saveSPString(sharedPrefManager.SP_LEVEL,level);
                    sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);

                    startActivity(new Intent(LoginActivity.this, MenuActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                    finish();

                   }
                else {
                    mprogres.dismiss();
                    Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                }}



            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                t.printStackTrace();
                mprogres.dismiss();
                Toast.makeText(LoginActivity.this, "Jaringan Error!", Toast.LENGTH_SHORT).show();
            }
        });



    }



//    insiliasi objek layout
    public void setupwidgedts(){

        sharedPrefManager = new SharedPrefManager(this);
        login = (Button) findViewById(R.id.btlogin);
        daftar =(Button) findViewById(R.id.bt_log_daftar);
        username =(EditText) findViewById(R.id.login_username);
        pass =(EditText) findViewById(R.id.login_pass);
        mprogres = new ProgressDialog(this);
        mprogres.setCancelable(false);
        mprogres.setMessage("mencocokan data");
    }


//    listener button yang ada

    public void buttonlistener(){

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestlogin();

            }
        });

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, DaftarActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });


    }
}
