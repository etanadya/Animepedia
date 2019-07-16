package com.mobile.animepedia;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputLayout edtEmailLogin,edtPasswordLogin;
    private Button btnLogin, btnGotoSigniup;
    private FirebaseAuth auth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmailLogin = findViewById(R.id.edt_email);
        edtPasswordLogin = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
        btnGotoSigniup = findViewById(R.id.btn_gotoregister);
        btnGotoSigniup.setOnClickListener(this);
        progressBar = findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                Signin();

                break;
            case R.id.btn_gotoregister:
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                break;
        }

    }
    public void Signin(){
        String email = edtEmailLogin.getEditText().getText().toString();
        final String password = edtPasswordLogin.getEditText().getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toasty.info(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toasty.info(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {

                        progressBar.setVisibility(View.GONE);
                        if (!task.isSuccessful()) {

                            if (password.length() < 6) {
                                edtPasswordLogin.setError(getString(R.string.minimum_password));
                            } else {
                                Toasty.error(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            Toasty.success(LoginActivity.this,"Succes Login",Toasty.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
    }
}
