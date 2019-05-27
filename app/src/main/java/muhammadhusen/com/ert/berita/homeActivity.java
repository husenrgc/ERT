package muhammadhusen.com.ert.berita;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import muhammadhusen.com.ert.R;
import muhammadhusen.com.ert.Utility.SharedPrefManager;
import muhammadhusen.com.ert.Utility.navHelper;
import muhammadhusen.com.ert.api.RegisterAPI;
import muhammadhusen.com.ert.api.Value;
import muhammadhusen.com.ert.result.ResultBerita;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class homeActivity extends AppCompatActivity {
private Toolbar toolbar;

    public static final String URL = "http://192.168.43.123/ERT/api/";
    private List<ResultBerita> results = new ArrayList<>();
    private RecycleBerita viewAdapter;
    ProgressBar mProgressbar;
    RecyclerView recyclerView;


    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_berita);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        sharedPrefManager = new SharedPrefManager(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
getSupportActionBar().setTitle("Kembali");
        recyclerView = (RecyclerView) findViewById(R.id.RecBerita);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(homeActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(viewAdapter);
        setupNav();
loadberita();

    }

    private void setupNav(){


        if (sharedPrefManager.getSpLevel().equals("user")){
        BottomNavigationView btv = (BottomNavigationView) findViewById(R.id.bottomNavViewBar1);
        BottomNavigationView btv2 = (BottomNavigationView) findViewById(R.id.bottomNavViewBar);
            btv2.setVisibility(View.GONE);
            btv.setVisibility(View.VISIBLE);

        navHelper.enablenav(homeActivity.this,this, btv);
        Menu menu = btv.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        }else if (sharedPrefManager.getSpLevel().equals("admin")){

            BottomNavigationView btv = (BottomNavigationView) findViewById(R.id.bottomNavViewBar);

            BottomNavigationView btv2 = (BottomNavigationView) findViewById(R.id.bottomNavViewBar1);
            btv2.setVisibility(View.GONE);
            btv.setVisibility(View.VISIBLE);
            navHelper.enablenav(homeActivity.this,this, btv);
            Menu menu = btv.getMenu();
            MenuItem menuItem = menu.getItem(0);
            menuItem.setChecked(true);


        }else if (sharedPrefManager.getSpLevel().isEmpty()){

            BottomNavigationView btv = (BottomNavigationView) findViewById(R.id.bottomNavViewBar);
            BottomNavigationView btv2 = (BottomNavigationView) findViewById(R.id.bottomNavViewBar1);
            btv2.setVisibility(View.GONE);
            btv.setVisibility(View.VISIBLE);

            navHelper.enablenav(homeActivity.this,this, btv);
            Menu menu = btv.getMenu();
            MenuItem menuItem = menu.getItem(0);
            menuItem.setChecked(true);

        }


    }

private void loadberita(){

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    RegisterAPI api = retrofit.create(RegisterAPI.class);
    Call<Value> call = api.loadberita();
    call.enqueue(new Callback<Value>() {
        @Override
        public void onResponse(Call<Value> call, Response<Value> response) {

            String value = response.body().getValue();

            if (value.equals("1")) {

                results = response.body().getResultBerita();
                viewAdapter = new RecycleBerita(homeActivity.this, results);
                recyclerView.setAdapter(viewAdapter);
            }
        }

        @Override
        public void onFailure(Call<Value> call, Throwable t) {

        }
    });








}





}
