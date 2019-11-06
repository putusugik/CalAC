package sugialmantara.myapplication.ui.Rekomendasi;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import sugialmantara.myapplication.JSONParser;
import sugialmantara.myapplication.R;

public class RekomendasiACFragment extends Fragment {

    public static final String TAG_SUCCESS = "sukses";
    public static final String TAG_NAMAAC = "nama_ac";
    public static final String TAG_MERKAC = "merk_ac";
    public static final String TAG_TIPEAC = "tipe_ac";
    public final String TAG_HARGAAC = "harga";
    public static final String TAG_INDEX = "index";
    JSONParser js = new JSONParser();
    private RecyclerView recyclerView;
    JSONParser jsonParser = new JSONParser();
    public ListAdapterRekomendasi listAdapter;
    List<DataACRekomen> data = new ArrayList<>();
    private static String url_viewAC = "http://arismart.co.id/sugiklib/select_rekomendasi.php";
    public static final int CONN_TIMEOUT = 10000;
    public static final int CONN_READOUT = 15000;
    int hasil;
    JSONArray rekomen = null;
    ArrayList<HashMap<String, String>> listrek = new ArrayList<HashMap<String, String>>();

    public RekomendasiACFragment(){

    }

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        final View[] root = {inflater.inflate(R.layout.fragment_tools, container, false)};
        hasil = getArguments().getInt("hasil", -1);
        new viewRekomen().execute();



        return root[0];
        }

    private class viewRekomen extends AsyncTask<String,String, String >{


        @Override
        protected String doInBackground(String... strings) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("hasil", ""+hasil));

            JSONObject object = jsonParser.makeHttpRequest(url_viewAC, "GET", params);
            Log.d("Back: ", object.toString());

            try {
                int sukses = object.getInt(TAG_SUCCESS);
                if (sukses==1){
                    rekomen = object.getJSONArray("AC");
                    listrek.removeAll(listrek);
                    for (int i=0;i<rekomen.length();i++){
                        JSONObject obj = rekomen.getJSONObject(i);
                        HashMap<String, String> map = new HashMap<String, String>();
                        String namaAC = obj.getString(TAG_NAMAAC);
                        String merkAC = obj.getString(TAG_MERKAC);
                        String tipeAC = obj.getString(TAG_TIPEAC);
                        String hargaAC = obj.getString(TAG_HARGAAC);

                        map.put(TAG_NAMAAC, namaAC);
                        map.put(TAG_MERKAC, merkAC);
                        map.put(TAG_TIPEAC, tipeAC);
                        map.put(TAG_HARGAAC, hargaAC);
                        map.put(TAG_INDEX, ""+i);
                        listrek.add(map);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            recyclerView = (RecyclerView)getActivity().findViewById(R.id.rlListACRekomen);
            listAdapter = new ListAdapterRekomendasi(getActivity(), listrek);
            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
            recyclerView.setAdapter(listAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }

    }
}