package sugialmantara.myapplication.ui.Rekomendasi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import sugialmantara.myapplication.R;

public class ListAdapterRekomendasi extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    List<DataACRekomen> data = Collections.emptyList();
    int temp;
    private static LayoutInflater inflater = null;



    public ListAdapterRekomendasi(Context context, List<DataACRekomen> dataACS){
        this.context = context;
        inflater =LayoutInflater.from(context);
        this.data = dataACS;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.from(parent.getContext()).inflate(R.layout.list_acrekomen, parent, false);
        ListViewHolder holder = new ListViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //((ListViewHolder) holder ).bindView(position);
        DecimalFormat decim = new DecimalFormat("#,###.##");
        final ListViewHolder holder1 =(ListViewHolder) holder;
        final DataACRekomen dataACRekomen = data.get(position);
        holder1.tvnama.setText(dataACRekomen.Nama_ACRekomen);
        holder1.tvmerk.setText(dataACRekomen.Merk_ACRekomen);
        holder1.tvtipe.setText(dataACRekomen.Tipe_ACRekomen);
        temp = Integer.parseInt(dataACRekomen.Harga_ACRekomen);
        holder1.tvharga.setText("Rp. "+decim.format(temp));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    private class ListViewHolder extends RecyclerView.ViewHolder {
         TextView tvnama, tvmerk, tvharga, tvtipe;
         ImageView iv;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvnama = itemView.findViewById(R.id.namaACre);
            tvmerk = itemView.findViewById(R.id.merkACre);
            tvtipe = itemView.findViewById(R.id.tipeACre);
            tvharga = itemView.findViewById(R.id.hargaACre);
            iv = itemView.findViewById(R.id.imgACre);

        }

    }

}
