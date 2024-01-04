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

public class FourthActivity extends AppCompatActivity {
    ImageView third_back_arrow, third_arrow_up;
    TextView third_title, third_subtitle, third_rating_number, third_rating_number2, more_details;
    RatingBar third_ratingbar;

    Animation from_left, from_right, from_bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        third_back_arrow = findViewById(R.id.third_back_arrow);
        third_arrow_up = findViewById(R.id.third_arrow_up);
        third_title = findViewById(R.id.infoTitle);
        third_subtitle = findViewById(R.id.third_subtitle);
        third_rating_number = findViewById(R.id.third_rating_number);
        third_rating_number2 = findViewById(R.id.third_rating_number2);
        more_details = findViewById(R.id.more_details);
        third_ratingbar = findViewById(R.id.infoRatingBar);

        third_back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FourthActivity.this, MainActivity.class);
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

        third_back_arrow.setAnimation(from_left);
        third_title.setAnimation(from_right);
        third_subtitle.setAnimation(from_right);
        third_ratingbar.setAnimation(from_left);
        third_rating_number.setAnimation(from_right);
        third_rating_number2.setAnimation(from_right);
        third_arrow_up.setAnimation(from_bottom);
        more_details.setAnimation(from_bottom);



        third_arrow_up.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FourthActivity.this, apartment2.class);

                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(third_arrow_up, "background_image_transition");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(FourthActivity.this, pairs);

                startActivity(intent, options.toBundle());
            }
        });
    }
}