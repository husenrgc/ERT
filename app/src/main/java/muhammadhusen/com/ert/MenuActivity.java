package muhammadhusen.com.ert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import muhammadhusen.com.ert.berita.homeActivity;

public class MenuActivity extends AppCompatActivity {
private Button berita,aspirasi,gallery,kas,download,about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        berita =(Button) findViewById(R.id.menuBerita);


        berita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, homeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

            }
        });
    }
}
