package br.ufscar.mds.android.nufscar.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import br.ufscar.mds.android.nufscar.NewsFeed;
import br.ufscar.mds.android.nufscar.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

    }

    @Override
    protected void onResume(){
        super.onResume();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, NewsFeed.class);
                startActivity(intent);
                finish();
            }
        },5000);


    }
}
