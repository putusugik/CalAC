package sugialmantara.myapplication.ui.ListAC;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

public class ListACFragment extends Fragment {

    private RecyclerView recyclerView;
    private ListAdapter listAdapter;
    List<DataAC> data = new ArrayList<>();
    private static String url_viewAC = "http://10.0.2.2/calac/select_listac.php";
    public static final int CONN_TIMEOUT = 10000;
    public static final int CONN_READOUT = 15000;

    public ListACFragment(){

    }

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {

        final View[] root = {inflater.inflate(R.layout.fragment_gallery, container, false)};
        new viewAC().execute();

        return root[0];
    }

    private class viewAC extends AsyncTask<String, String, String> {
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
                conn.setRequestMethod("GET");
                conn.setDoOutput(true);
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
        protected void onPostExecute(String result) {
            try{
                JSONObject object = new JSONObject(result);
                JSONArray jsonArray = object.getJSONArray("AC");
                for (int i=0; i < jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    DataAC dataAC= new DataAC();
                    dataAC.Nama_AC=jsonObject.getString("nama_ac");
                    dataAC.Merk_AC=jsonObject.getString("merk_ac");
                    dataAC.Tipe_AC=jsonObject.getString("tipe_ac");
                    dataAC.Harga_AC=jsonObject.getString("harga");
                    data.add(dataAC);
                }
                recyclerView = (RecyclerView)getActivity().findViewById(R.id.rlListAC);
                listAdapter = new ListAdapter(getActivity(), data);
                recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
                recyclerView.setAdapter(listAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                //Toast.makeText(getActivity(), "SUKSES", Toast.LENGTH_LONG).show();

            } catch (JSONException e) {
                Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
                Log.d("ERROR: ", e.toString());

            }
        }
    }
}