package com.example.mealsplanner;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class SplashActivity extends AppCompatActivity {

    private LottieAnimationView animationView;
    private TextView splashText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        animationView = findViewById(R.id.lottieAnimationView);
        splashText = findViewById(R.id.splash_text);

        animationView.addAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                String splashMessage = "Fuel your day plan your way";
                animateText(splashText, splashMessage, 20);

                // Start the MainActivity after the text animation is complete
                splashText.postDelayed(() -> {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }, (splashMessage.length() * 20) + 1900);
            }
        });
    }

    private void animateText(final TextView textView, final String text, final int delay) {
        textView.setText("");
        Handler handler = new Handler();

        for (int i = 0; i < text.length(); i++) {
            final int index = i;
            handler.postDelayed(() -> {
                textView.append(String.valueOf(text.charAt(index))); // Append one character at a time
            }, delay * index); // Delay increases with each character
        }
    }
}
