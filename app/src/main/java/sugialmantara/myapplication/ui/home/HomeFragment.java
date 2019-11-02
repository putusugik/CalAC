package sugialmantara.myapplication.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import sugialmantara.myapplication.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    EditText et_panjang, et_lebar, et_tinggi;
    Button butt_hitung;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        et_panjang = root.findViewById(R.id.e_panjang);
        et_lebar = root.findViewById(R.id.e_lebar);
        et_tinggi = root.findViewById(R.id.e_tinggi);
        butt_hitung = root.findViewById(R.id.bt_hitung);

       butt_hitung.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               
           }
       });

        return root;
    }
}