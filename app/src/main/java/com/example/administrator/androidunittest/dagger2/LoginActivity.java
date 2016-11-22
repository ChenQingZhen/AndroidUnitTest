package com.example.administrator.androidunittest.dagger2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.administrator.androidunittest.R;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity {
    @Inject
    LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ComponentHolder.getAppComponent().inject(this);

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=((EditText)findViewById(R.id.username)).getText().toString();
                String password=((EditText)findViewById(R.id.password)).getText().toString();
                mLoginPresenter.login(username,password);
            }
        });

    }
}
