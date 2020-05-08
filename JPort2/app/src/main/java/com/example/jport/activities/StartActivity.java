package com.example.jport.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.ImageView;
import com.example.jport.R;


public class StartActivity extends AppCompatActivity {
    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        imageview =findViewById(R.id.imageView2);
        Animation animation= android.view.animation.AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
        imageview.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                finish();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });









    }
}
