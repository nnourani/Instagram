package info.instagram.instagram.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import info.instagram.instagram.R;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

    }
}
