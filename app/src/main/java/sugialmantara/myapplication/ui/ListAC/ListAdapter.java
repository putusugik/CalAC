package sugialmantara.myapplication.ui.ListAC;

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

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<DataAC> data = Collections.emptyList();
    int temp;


    public ListAdapter(Context context, List<DataAC> dataACS){
        this.context = context;
        inflater =LayoutInflater.from(context);
        this.data = dataACS;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.from(parent.getContext()).inflate(R.layout.list_ac, parent, false);
        ListViewHolder holder = new ListViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //((ListViewHolder) holder ).bindView(position);
        DecimalFormat decim = new DecimalFormat("#,###.##");
        final ListViewHolder holder1 =(ListViewHolder) holder;
        final DataAC dataAC =data.get(position);
        holder1.tvnama.setText(dataAC.Nama_AC);
        holder1.tvmerk.setText(dataAC.Merk_AC);
        holder1.tvtipe.setText(dataAC.Tipe_AC);
        temp = Integer.parseInt(dataAC.Harga_AC);
        holder1.tvharga.setText("Rp. "+decim.format(temp));

    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public DataAC getCurrent(int pos){
        return data.get(pos);
    }

    public void clear(){
        data.clear();
        notifyDataSetChanged();
    }

    public void addAll (List<DataAC> list){
        data.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private class ListViewHolder extends RecyclerView.ViewHolder {
         TextView tvnama, tvmerk, tvharga, tvtipe;
         ImageView iv;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvnama = itemView.findViewById(R.id.namaAC);
            tvmerk = itemView.findViewById(R.id.merkAC);
            tvtipe = itemView.findViewById(R.id.tipeAC);
            tvharga = itemView.findViewById(R.id.hargaAC);
            iv = itemView.findViewById(R.id.imgAC);

        }

    }

}
