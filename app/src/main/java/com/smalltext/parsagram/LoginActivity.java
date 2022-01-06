package com.smalltext.parsagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.smalltext.parsagram.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = LoginActivity.class.getSimpleName();
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (ParseUser.getCurrentUser() != null) {
            Log.i(TAG, "launch from loginactivity restart");
            launchMainActivity();
        }

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "login button clicked");
                login(binding.etUsername.getText().toString(), binding.etPassword.getText().toString());
            }
        });
        binding.etPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                Log.i(TAG, "login enter pressed");
                login(binding.etUsername.getText().toString(), binding.etPassword.getText().toString());
                return false;
            }
        });
    }

    private void login(String username, String password) {
        Log.i(TAG, "attempt to login "+username+" with password "+password);
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e == null) {
                    Log.i(TAG, "launching main");
                    launchMainActivity();
                } else {
                    if (e.getMessage() != null) {
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Log.e(TAG, "login issue", e);
                    }
                }
            }
        });
    }

    private void launchMainActivity() {
        Intent i = new Intent(this, FeedActivity.class);
        startActivity(i);
        finish();
    }
}