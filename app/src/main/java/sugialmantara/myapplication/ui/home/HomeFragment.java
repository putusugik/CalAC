package sugialmantara.myapplication.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import sugialmantara.myapplication.R;
import sugialmantara.myapplication.ui.Rekomendasi.RekomendasiACFragment;

public class HomeFragment extends Fragment {


    EditText et_panjang, et_lebar, et_tinggi;
    String panjang, lebar, tinggi;
    Button butt_hitung;
    TextView tv_hasil;
    Integer epanjang, elebar, etinggi;
    int hasil;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        et_panjang = root.findViewById(R.id.e_panjang);
        et_lebar = root.findViewById(R.id.e_lebar);
        et_tinggi = root.findViewById(R.id.e_tinggi);
        butt_hitung = root.findViewById(R.id.bt_hitung);
        tv_hasil = root.findViewById(R.id.tHasil);
        tv_hasil.setEnabled(false);

       butt_hitung.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
            panjang = et_panjang.getText().toString();
            lebar = et_lebar.getText().toString();
            tinggi = et_tinggi.getText().toString();
            if (panjang.isEmpty() || lebar.isEmpty() || tinggi.isEmpty()){
                Toast.makeText(getActivity(), "Masukan data belum lengkap", Toast.LENGTH_SHORT).show();
            } else {
                int epjg = Integer.parseInt(et_panjang.getText().toString());
                int elbr = Integer.parseInt(et_lebar.getText().toString());
                int etgg = Integer.parseInt(et_tinggi.getText().toString());
                hasil = epjg * elbr * etgg * 150;
                if (hasil < 4000){
                    DecimalFormat decim = new DecimalFormat("#,###.##");
                    tv_hasil.setText("Kebutuhan ruangan ");
                    tv_hasil.append(decim.format(hasil )+" Btu/h, Minimal 4.000 btu/h.");
                    tv_hasil.setEnabled(false);
                }else {
                    DecimalFormat decim = new DecimalFormat("#,###.##");
                    tv_hasil.setText("Kebutuhan ruangan ");
                    tv_hasil.append(decim.format(hasil )+" Btu/h, Klik disini untuk melihat rekomendasi produk.");
                    tv_hasil.setEnabled(true);
                }
            }
           }
       });

       tv_hasil.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               FragmentManager fm = getFragmentManager();
               FragmentTransaction ft = fm.beginTransaction();
               RekomendasiACFragment tf = new RekomendasiACFragment();
               ft.replace(R.id.nav_host_fragment, tf);
               Bundle args = new Bundle();
               args.putInt("hasil", hasil);
               tf.setArguments(args);
               ft.commit();
           }
       });

        return root;
    }
}