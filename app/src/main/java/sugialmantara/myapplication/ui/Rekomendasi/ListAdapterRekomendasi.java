package sugialmantara.myapplication.ui.Rekomendasi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapterRekomendasi extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    int temp;


    private ArrayList<HashMap<String,String>> data;
    private ArrayList<HashMap<String,String>> dataBackup;
    private static LayoutInflater inflater = null;
    HashMap<String,String> booking = new HashMap<String, String>();


    public ListAdapterRekomendasi(Context context, ArrayList<HashMap<String, String>> dataACS){
        this.context = context;
        inflater =LayoutInflater.from(context);
        this.data = dataACS;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    /*@NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.from(parent.getContext()).inflate(R.layout.list_acrekomen, parent, false);
        ListViewHolder holder = new ListViewHolder(view);

        return holder;
    }*/

    /*@Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //((ListViewHolder) holder ).bindView(position);
        DecimalFormat decim = new DecimalFormat("#,###.##");
        final ListViewHolder holder1 =(ListViewHolder) holder;
        final DataACRekomen dataAC =data.get(position);
        holder1.tvnama.setText(dataAC.Nama_ACRekomen);
        holder1.tvmerk.setText(dataAC.Merk_ACRekomen);
        holder1.tvtipe.setText(dataAC.Tipe_ACRekomen);
        temp = Integer.parseInt(dataAC.Harga_ACRekomen);
        holder1.tvharga.setText("Rp. "+decim.format(temp));

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

    }*/

}
