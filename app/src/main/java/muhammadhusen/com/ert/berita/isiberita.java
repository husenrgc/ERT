package muhammadhusen.com.ert.berita;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import muhammadhusen.com.ert.R;

public class isiberita extends AppCompatActivity {
private TextView judul,tanggal,penulis,isii,sumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isiberita);

        judul = findViewById(R.id.judulisiberita);
        tanggal = findViewById(R.id.tglisiberita);
        penulis = findViewById(R.id.penulisberita);
        isii = findViewById(R.id.isi);
        sumber = findViewById(R.id.sumberisi);
        Intent intent = getIntent();
        String kd = intent.getStringExtra("kd");
        String namausr = intent.getStringExtra("namausr");
        String judull = intent.getStringExtra("judull");
        String isi = intent.getStringExtra("isi");
        String sumberbr = intent.getStringExtra("sumberbr");
        String tanggall = intent.getStringExtra("tanggall");


        judul.setText(judull);
        tanggal.setText(tanggall);
        penulis.setText(namausr);
        isii.setText(isi);
        sumber.setText(sumberbr);


    }
}
