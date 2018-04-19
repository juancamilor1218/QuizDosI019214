package com.example.acer.quizdosi019214;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.quizdosi019214.Model.Photos;
import com.example.acer.quizdosi019214.Model.User;
import com.example.acer.quizdosi019214.Parser.Json;
import com.example.acer.quizdosi019214.Parser.JsonPhotos;
import com.example.acer.quizdosi019214.URL.HttpManager;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView textView;
    List<Photos> photosList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        progressBar=(ProgressBar)findViewById(R.id.id_pb_data);
        textView=(TextView)findViewById(R.id.id_tv_data);

    }
    public void processData(){
        for (Photos str : photosList){
            textView.append(str.getTitle() + "\n");
            textView.append(str.getThumbnailUrl() + "\n");
            textView.append("\n");
        }
    }
    public void loadData() {
        if (isOnLine()) {
            PhotosTask mytask = new PhotosTask();
            mytask.execute("https://jsonplaceholder.typicode.com/photos");
        } else {
            Toast.makeText(this, "Sin conexion", Toast.LENGTH_SHORT).show();
        }
    }
    public Boolean isOnLine(){
        // Hacer llamado al servicio de conectividad utilizando el ConnectivityManager
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Obtener el estado de la conexion a internet en el dispositivo
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // Validar el estado obtenido de la conexion
        if (networkInfo != null){
            return true;
        }else {
            return false;
        }
    }


    public class PhotosTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            String content = null;
            try {
                content = HttpManager.getDataJson(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                photosList = JsonPhotos.getData(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            processData();
            progressBar.setVisibility(View.GONE);
        }
    }
}
