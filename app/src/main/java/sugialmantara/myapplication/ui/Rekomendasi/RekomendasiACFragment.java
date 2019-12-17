package sugialmantara.myapplication.ui.Rekomendasi;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import sugialmantara.myapplication.R;

public class RekomendasiACFragment extends Fragment {

    private RecyclerView recyclerView;
    TextView textView;
    public ListAdapterRekomendasi listAdapter;
    List<DataACRekomen> data = new ArrayList<>();
    private static String url_viewAC = "http://arismart.co.id/sugiklib/select_rekomendasi.php";
    public static final int CONN_TIMEOUT = 10000;
    public static final int CONN_READOUT = 15000;
    int hasil;


    public RekomendasiACFragment(){

    }

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        final View[] root = {inflater.inflate(R.layout.fragment_tools, container, false)};
        hasil = getArguments().getInt("hasil", -1);

        new viewRekomen().execute();

        return root[0];
        }


    private class viewRekomen extends AsyncTask<String, String, String>{
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected String doInBackground(String... strings) {
            try{
                url = new URL(url_viewAC);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return e.toString();
            }
            try{
                conn = (HttpURLConnection)url.openConnection();
                conn.setReadTimeout(CONN_READOUT);
                conn.setConnectTimeout(CONN_TIMEOUT);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                Uri.Builder builder = new Uri.Builder().appendQueryParameter("hasil", String.valueOf(hasil));
                String query = builder.build().getEncodedQuery();
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();

            } catch (IOException e1) {
                e1.printStackTrace();
                return e1.toString();
            }
            try{
                int response_code = conn.getResponseCode();
                if(response_code == HttpURLConnection.HTTP_OK){
                    InputStream inputStream = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine())!=null){
                        result.append(line);
                    }
                    return (result.toString());
                }else {
                    return ("tidak sukses");
                }

            } catch (IOException e2) {
                e2.printStackTrace();
                return e2.toString();
            }finally {
                conn.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject object = new JSONObject(s);
                JSONArray jsonArray = object.getJSONArray("AC");
                Log.d("JSONAAAAA: ",jsonArray.toString());
                for (int i = 0; i < jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    DataACRekomen dataACRekomen = new DataACRekomen();
                    dataACRekomen.Nama_ACRekomen=jsonObject.getString("nama_ac");
                    dataACRekomen.Merk_ACRekomen=jsonObject.getString("merk_ac");
                    dataACRekomen.Tipe_ACRekomen=jsonObject.getString("tipe_ac");
                    dataACRekomen.Harga_ACRekomen=jsonObject.getString("harga");
                    data.add(dataACRekomen);
                    Log.d("DATA: ", data.toString());
                }

                recyclerView = (RecyclerView)getActivity().findViewById(R.id.rlListACRekomen);
                if (data.isEmpty()){
                    textView = (TextView) getActivity().findViewById(R.id.textView3);
                    Toast.makeText(getActivity(), "Tidak ada yang cocok", Toast.LENGTH_SHORT).show();
                }else {
                    listAdapter = new ListAdapterRekomendasi(getActivity(), data);
                    recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
                    recyclerView.setAdapter(listAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}