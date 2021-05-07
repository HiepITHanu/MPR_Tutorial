package com.example.myteam;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private ImageView avatar;
    private ImageButton nextBtn;
    private ImageButton prevBtn;
    private TextView nameTv;
    private TextView emailTv;
    private static int id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextBtn = findViewById(R.id.nextBtn);
        prevBtn = findViewById(R.id.prevBtn);
        avatar = findViewById(R.id.avatar);
        nameTv = findViewById(R.id.nameTv);
        emailTv = findViewById(R.id.emailTv);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id++;
                new DownloadTask().execute("https://jsonplaceholder.typicode.com/users/" + id);
                new ImageDownload().execute("https://robohash.org/" + id + "?set=set2");
            }
        });

        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id--;
                new DownloadTask().execute("https://jsonplaceholder.typicode.com/users/" + id);
                new ImageDownload().execute("https://robohash.org/" + id + "?set=set2");
            }
        });

    }

    private class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            URL url ;
            HttpURLConnection urlConnection;
            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();

                Scanner sc = new Scanner(urlConnection.getInputStream());
                StringBuilder result = new StringBuilder();
                String line;
                while(sc.hasNextLine()) {
                    line = sc.nextLine();
                    result.append(line);
                }
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject root = new JSONObject(s);
                String name = root.getString("name");
                String email = root.getString("email");
                nameTv.setText(name);
                emailTv.setText(email);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class ImageDownload extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());
                return bitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            avatar.setImageBitmap(bitmap);
        }
    }

}