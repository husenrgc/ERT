package muhammadhusen.com.ert;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;

import muhammadhusen.com.ert.Utility.SharedPrefManager;
import muhammadhusen.com.ert.register.DaftarActivity;

public class MainActivity extends AppCompatActivity {
    RelativeLayout rel1,rel2;
    Button login;
    SharedPrefManager sharedPrefManager;
    Button daftar;
    Handler handler = new Handler();
    Runnable run = new Runnable() {
        @Override
        public void run() {
            if (sharedPrefManager.getSPSudahLogin()){
startActivity(new Intent(MainActivity.this, MenuActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
finish();
        }
        else {
                startActivity(new Intent(MainActivity.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();


            }


        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPrefManager = new SharedPrefManager(this);

        handler.postDelayed(run,4000);






}}
