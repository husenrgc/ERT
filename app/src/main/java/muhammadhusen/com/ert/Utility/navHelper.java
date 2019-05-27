package muhammadhusen.com.ert.Utility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import muhammadhusen.com.ert.R;
import muhammadhusen.com.ert.berita.homeActivity;
import muhammadhusen.com.ert.berita.tulisBerita;

public class navHelper {

    public static void enablenav(final Context context, final Activity call, BottomNavigationView view){

        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_view:
                        Intent intent1 = new Intent(context, homeActivity.class);
                        context.startActivity(intent1);
                        call.overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

                        break;
                    case R.id.ic_tulis:

                        Intent intent2 = new Intent(context,tulisBerita.class);
                        context.startActivity(intent2);

                        call.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        call.finish();
                        break;



                }

                return false;
            }
        });

    }

}