package muhammadhusen.com.ert.berita;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import muhammadhusen.com.ert.R;
import muhammadhusen.com.ert.result.ResultBerita;

public class RecycleBerita extends RecyclerView.Adapter<RecycleBerita.ViewHolder> {

    public static final String URL = "http://192.168.43.123/ERT/api/";
    private Context context;
    private List<ResultBerita> results;

    public RecycleBerita(Context context, List<ResultBerita> results) {
        this.context = context;
        this.results = results;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_berita ,parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ResultBerita result = results.get(position);

        holder.judul.setText(result.getJudul());
        holder.kode.setText(result.getKd_berita());
        holder.tanggal.setText(result.getTanggal().toString());
          holder.isiberita = result.getIsiberita();
          holder.sumber = result.getSumber();
          holder.nama = result.getNama_user();
    }

    @Override
    public int getItemCount() {
        return results.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
       public   String isiberita,sumber,nama;
       TextView judul , tanggal,kode;

        public ViewHolder(View itemView) {
            super(itemView);

            judul = (TextView) itemView.findViewById(R.id.judulsingle);
            tanggal = (TextView)itemView.findViewById(R.id.tglsingle);
            kode = (TextView) itemView.findViewById(R.id.idberita);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            String kd = kode.getText().toString();
            String namausr = nama;
            String judull = judul.getText().toString();
            String isi = isiberita;
            String sumberbr = sumber;
            String tanggall = tanggal.getText().toString();


            Intent i = new Intent(context, isiberita.class);
            i.putExtra("kd", kd);
            i.putExtra("namausr", namausr);
            i.putExtra("judull", judull);
            i.putExtra("isi", isi);
            i.putExtra("sumberbr", sumberbr);
            i.putExtra("tanggall", tanggall);
            context.startActivity(i);


        }
    }

    }
