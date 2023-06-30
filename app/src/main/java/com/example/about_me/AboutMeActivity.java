package com.example.about_me;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.about_me.databinding.ActivityAboutMeBinding;

public class AboutMeActivity extends AppCompatActivity {
    ActivityAboutMeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAboutMeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWhatsApp();
            }
        });

        binding.emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

        binding.linkedinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLinkedin();
            }
        });

        binding.callmeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callMe();
            }
        });
    }

    private void openWhatsApp() {
        // Creación de método con intent implícito para enviar mensaje por Whatsapp.

            String contact = "+56934332449";
            String url = "https://api.whatsapp.com/send?phone="+ contact +"&text= Holi, que ondi";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
    }

    private void sendEmail() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"mimarchtt@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Hola Miguel :D");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getBaseContext(), "No hay un cliente de correos instalado.", Toast.LENGTH_SHORT).show();
        }
    }

    private void showLinkedin() {
        String url = "https://www.linkedin.com/in/miguel-marchant/";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        try {
            startActivity(i);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getBaseContext(), "No application can handle this request. Please install a web browser or check your URL.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void callMe() {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:+56934332449"));
        startActivity(callIntent);
    }


}