package com.example.nazaifmoideen.blog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AuthActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById((R.id.password));
        Button signinButton = (Button)findViewById(R.id.signinbtn);
        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFormvalid()){
                    performSignin();
                }
            }
        });
        Button registerButton = (Button)findViewById(R.id.registerbtn);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFormvalid()){
                    performRegister();
                }

            }
        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading");
    }

    private void performSignin(){
        showProgressDialog(true);
        APIManager.getAPInterface().login(new AuthRequest(username.getText().toString().trim(), password.getText().toString().trim()))
        .enqueue(new Callback<messageResponse>() {
            @Override
            public void onResponse(Call<messageResponse> call, Response<messageResponse> response) {
                showProgressDialog(false);
                if (response.isSuccessful()) {
               navigatetoArticleListActivity();

                }else {
                    try {
                        String errorMessage = response.errorBody().string();
                        try {
                            ErrorResponse errorResponse = new Gson().fromJson(errorMessage,ErrorResponse.class);
                            alertShow("Sign in failed",errorResponse.getError());
                        }catch (JsonSyntaxException e){
                            e.printStackTrace();
                            alertShow("something wrong."," Signin failed");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        alertShow("something wrong."," Signin failed");
                    }

                }
            }

            @Override
            public void onFailure(Call<messageResponse> call, Throwable t) {
                alertShow("something wrong."," Signin failed");
                showProgressDialog(false);
            }
        });
    }

    private void performRegister(){
        showProgressDialog(true);
        APIManager.getAPInterface().registration(new AuthRequest(username.getText().toString().trim(), password.getText().toString().trim()))
                .enqueue(new Callback<messageResponse>() {
                    @Override
                    public void onResponse(Call<messageResponse> call, Response<messageResponse> response) {
                        showProgressDialog(false);
                        if (response.isSuccessful()) {
                            alertShow("welcome",response.body().getMessage());
                        }else {
                            try {
                                String errorMessage = response.errorBody().string();
                                try {
                                    ErrorResponse errorResponse = new Gson().fromJson(errorMessage,ErrorResponse.class);
                                    alertShow("Regn failed",errorResponse.getError());
                                }catch (JsonSyntaxException e){
                                    e.printStackTrace();
                                    alertShow("something wrong."," Regn failed");
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                                alertShow("something wrong."," Regn failed");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<messageResponse> call, Throwable t) {
                        alertShow("something wrong."," Regn failed");
                        showProgressDialog(false);
                    }
                });

    }


    private Boolean isFormvalid(){
        if (username.getText().toString().trim().isEmpty()) {
            Toast.makeText(this,"username cannot be empty",Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password.getText().toString().trim().isEmpty()){
            Toast.makeText(this,"Password cannot be empty",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void showProgressDialog(Boolean should){
        if(should){
            progressDialog.show();
        }else {
            progressDialog.dismiss();
        }
    }

    private void alertShow(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNeutralButton("Discard", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void navigatetoArticleListActivity(){
        Intent i = new Intent(this, ArticleListActivity.class);
        startActivity(i);
    }



    /*
    class signinTask extends AsyncTask<String, Void , Boolean>{

        String mockusername = "ijas";
        String mockpassword = "pass";
        @Override
        protected Boolean doInBackground(String... params) {
            String username = params[0];
            String password = params[1];
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return username.contentEquals(mockusername) && password.contentEquals(mockpassword);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog(true);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            showProgressDialog(false);
            if(aBoolean){
                alertShow("Success","You are in");
            }else{
                alertShow("Sign in failure","Check username/password");
            }
        }


    }*/
}