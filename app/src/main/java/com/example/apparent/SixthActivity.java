package com.example.apparent;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class SixthActivity extends AppCompatActivity {
    ImageView fifth_back_arrow, fifth_arrow_up;
    TextView fifth_title, fifth_subtitle, fifth_rating_number, fifth_rating_number2, more_details;
    RatingBar fifth_ratingbar;


    Animation from_left, from_right, from_bottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth);

        fifth_back_arrow = findViewById(R.id.fifth_back_arrow);
        fifth_arrow_up = findViewById(R.id.fifth_arrow_up);
        fifth_title = findViewById(R.id.fifth_title);
        fifth_subtitle = findViewById(R.id.fifth_subtitle);
        fifth_rating_number = findViewById(R.id.fifth_rating_number);
        fifth_rating_number2 = findViewById(R.id.fifth_rating_number2);
        more_details = findViewById(R.id.more_details);
        fifth_ratingbar = findViewById(R.id.fifth_ratingbar);

        fifth_back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SixthActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        this.getWindow().getDecorView().setSystemUiVisibility(

                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );

        from_left = AnimationUtils.loadAnimation(this, R.anim.anim_from_left);
        from_right = AnimationUtils.loadAnimation(this, R.anim.anim_from_right);
        from_bottom = AnimationUtils.loadAnimation(this, R.anim.anim_from_bottom);

        fifth_back_arrow.setAnimation(from_left);
        fifth_title.setAnimation(from_right);
        fifth_subtitle.setAnimation(from_right);
        fifth_ratingbar.setAnimation(from_left);
        fifth_rating_number.setAnimation(from_right);
        fifth_rating_number2.setAnimation(from_right);
        fifth_arrow_up.setAnimation(from_bottom);
        more_details.setAnimation(from_bottom);



        fifth_arrow_up.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SixthActivity.this, apartment4.class);

                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(fifth_arrow_up, "background_image_transition");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SixthActivity.this, pairs);

                startActivity(intent, options.toBundle());
            }
        });

    }
}