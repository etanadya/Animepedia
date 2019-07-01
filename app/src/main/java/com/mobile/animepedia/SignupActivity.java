package com.mobile.animepedia;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtEmailSignup, edtPasswordSignup, edtNameSignup, edtTglLahir;
    private Button btnGotoLogin, btnSignup;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    final String urlAdd = "http://192.168.43.18/animepedia/insert_user.php";
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    int success;
    String tag_json_obj = "json_obj_req";

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        textView = findViewById(R.id.tv);

        edtNameSignup = findViewById(R.id.edt_name);
        edtTglLahir = findViewById(R.id.edt_ttl);
        edtEmailSignup = findViewById(R.id.edt_emailSignup);
        edtPasswordSignup = findViewById(R.id.edt_passSignup);
        btnSignup = findViewById(R.id.btn_signup);
        btnSignup.setOnClickListener(this);
        btnGotoLogin = findViewById(R.id.btn_gotologin);
        btnGotoLogin.setOnClickListener(this);
        progressBar = findViewById(R.id.progressBar);



        auth = FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_signup:
                Toast.makeText(this, "Ok", Toast.LENGTH_SHORT).show();
                Signup();
                Adduser();

                break;
            case R.id.btn_gotologin:
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }

    }

    public void Signup() {
        String email = edtEmailSignup.getText().toString().trim();
        String password = edtPasswordSignup.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        //create user
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignupActivity.this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        Toast.makeText(SignupActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();

                        progressBar.setVisibility(View.GONE);

                        if (!task.isSuccessful()) {
                            Toast.makeText(SignupActivity.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(SignupActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                });

    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    public void Adduser() {

        final String nama = edtNameSignup.getText().toString().trim();
        final String tgl_lahir = edtTglLahir.getText().toString().trim();
        final String email = edtEmailSignup.getText().toString().trim();
        final String pwd = edtPasswordSignup.getText().toString().trim();

        StringRequest strReq = new StringRequest(Request.Method.POST, urlAdd, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Cek error node pada json
                    if (success == 1) {
                        Log.d("Add/update", jObj.toString());


                        Toast.makeText(SignupActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(SignupActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(SignupActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                // jika id kosong maka simpan, jika id ada nilainya maka update

                params.put("nama", nama);
                params.put("tgl_lahir", tgl_lahir);
                params.put("email", email);
                params.put("pwd", pwd);


                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

}
