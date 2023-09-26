package hieucdph29636.fpoly.assignment_mob2041_ph29636;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ImageView logo = findViewById(R.id.logo);
        ImageView load = findViewById(R.id.load);
        Glide.with(this).load(R.drawable.sun_book).into(logo);
        Glide.with(this).load(R.drawable.loading).into(load);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this,LoginActivity.class);
                startActivity(intent);

            }
        },3250);

    }
}