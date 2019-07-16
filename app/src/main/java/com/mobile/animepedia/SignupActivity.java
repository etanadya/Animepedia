package com.mobile.animepedia;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    TextInputLayout edtEmailSignup, edtPasswordSignup, edtNameSignup, edtRePassword;
    EditText edtTglLahir, edtTempatLahir;
    private Button btnGotoLogin, btnSignup;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    final String urlAdd = "http://3jnc.tech/animepedia/api/user/insert_user.php";
//    final String urlAdd = "http://192.168.43.18/animepedia/insert_user.php";
    private static final String TAG = SignupActivity.class.getSimpleName();
    private static final String     TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    int success;
    String tag_json_obj = "json_obj_req";
    final Calendar myCalendar = Calendar.getInstance();

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        textView = findViewById(R.id.tv);

//        edtNameSignup = findViewById(R.id.edt_name);
//        edtTempatLahir = findViewById(R.id.edt_tempatSignup);
//        edtTglLahir = findViewById(R.id.edt_ttl);
        edtRePassword = findViewById(R.id.edt_RepassSignup);
        edtEmailSignup = findViewById(R.id.edt_emailSignup);
        edtPasswordSignup = findViewById(R.id.edt_passSignup);
        btnSignup = findViewById(R.id.btn_signup);
        btnSignup.setOnClickListener(this);
        btnGotoLogin = findViewById(R.id.btn_gotologin);
        btnGotoLogin.setOnClickListener(this);
        progressBar = findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();

//        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
//
//            @Override
//            public void onDateSet(DatePicker view, int year, int monthOfYear,
//                                  int dayOfMonth) {
//                // TODO Auto-generated method stub
//                myCalendar.set(Calendar.YEAR, year);
//                myCalendar.set(Calendar.MONTH, monthOfYear);
//                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                updateLabel();
//            }
//
//        };
//        edtTglLahir.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new DatePickerDialog(SignupActivity.this, date, myCalendar
//                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
//                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
//            }
//
//        });

    }

    private void updateLabel() {
        String myFormat = "dd-MM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edtTglLahir.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_signup:
//                Toast.makeText(this, "Ok", Toast.LENGTH_SHORT).show();
                Signup();
//                Adduser();

                break;
            case R.id.btn_gotologin:
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }

    }

    public void Signup() {
        String email = edtEmailSignup.getEditText().getText().toString().trim();
        String password = edtPasswordSignup.getEditText().getText().toString().trim();
        String rePassword = edtRePassword.getEditText().getText().toString().trim();
//        String pass = null;
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
        if (TextUtils.isEmpty(rePassword)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (rePassword.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.equals(rePassword)){
            String pass = password;
            progressBar.setVisibility(View.VISIBLE);
            //create user
            auth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(SignupActivity.this, new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            Toasty.success(SignupActivity.this, "Sign Up Success : " + task.isSuccessful(), Toast.LENGTH_SHORT).show();

                            progressBar.setVisibility(View.GONE);

                            if (!task.isSuccessful()) {
                                Toasty.error(SignupActivity.this, "Authentication failed." + task.getException(),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(SignupActivity.this, MainActivity.class));
                                Toasty.success(SignupActivity.this,"Succes Login",Toasty.LENGTH_SHORT).show();

                                finish();
                            }
                        }
                    });

        }else {
            Toasty.error(getApplicationContext(), "Password no match", Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    public void Adduser() {

        final String nama = edtNameSignup.getEditText().getText().toString().trim();
        final String tgl_lahir = edtTglLahir.getText().toString().trim();
        final String tempat_lahir = edtTempatLahir.getText().toString().trim();
        final String email = edtEmailSignup.getEditText().getText().toString().trim();
        final String pwd = edtPasswordSignup.getEditText().getText().toString().trim();

        StringRequest strReq = new StringRequest(Request.Method.POST , urlAdd, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Cek error node pada json
                    if (success == 1) {
                        Log.d("Add", jObj.toString());


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
