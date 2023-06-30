package com.example.about_me;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.about_me.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.followBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              followMe();
            }
        });

        binding.contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutMeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void followMe() {
        String url = "https://www.instagram.com/mimarchantt/";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        try {
            startActivity(i);
            Toast.makeText(getBaseContext(), "Gracias por el follow <3", Toast.LENGTH_LONG).show();
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getBaseContext(), "No application can handle this request. Please install a web browser or check your URL.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}