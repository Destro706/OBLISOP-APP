package de.oblisop.destro706;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends Activity {

    private EditText username,password;
    private ProgressDialog dialog = null;
    private static String username1;
    private static final String FILENAME1 = "UsernameOblisop";
    private static final String FILENAME2 = "PasswordOblisop";
    private static final String VAL_KEY = "ValueKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login = (Button) findViewById(R.id.login);
        Button register = (Button) findViewById(R.id.registerbtn);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        SharedPreferences sharedPrefs1 = getSharedPreferences(FILENAME1, 0);
        username.setText(sharedPrefs1.getString(VAL_KEY, ""));
        sharedPrefs1.getString(getUsername1(), "");
        SharedPreferences sharedPrefs2 = getSharedPreferences(FILENAME2, 0);
        password.setText(sharedPrefs2.getString(VAL_KEY, ""));

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = ProgressDialog.show(LoginActivity.this, "", "Authorisiere Benutzer...", true);
                new Thread(new Runnable() {
                    public void run() {
                        login();
                    }
                }).start();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, Register.class));
            }
        });
    }

   private void login(){
        try{

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://www.oblisop.de/check.php");
            List<NameValuePair> nameValuePairs = new ArrayList<>(2);
            nameValuePairs.add(new BasicNameValuePair("username", username.getText().toString().trim()));
            nameValuePairs.add(new BasicNameValuePair("password", password.getText().toString().trim()));
            username1 = username.getText().toString();
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            final String response = httpclient.execute(httppost, responseHandler);
            System.out.println("Response : " + response);
            runOnUiThread(new Runnable() {
                public void run() {
                    dialog.dismiss();
                }
            });

            if(response.equalsIgnoreCase("User Found")){
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(LoginActivity.this, "Login erfolgreich", Toast.LENGTH_SHORT).show();
                    }
                });

                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                LoginActivity.this.finish();

            }else{
                showAlert();
            }

        }catch(Exception e){
            dialog.dismiss();
            System.out.println("Exception : " + e.getMessage());
        }
    }
    private void showAlert(){
        LoginActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("Login Error.");
                builder.setMessage("Benutzer nicht gefunden")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    public static String getUsername1() {
        return username1;
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences sharedPrefs1 = getSharedPreferences(FILENAME1, 0);
        SharedPreferences.Editor editor1 = sharedPrefs1.edit();
        editor1.putString(VAL_KEY, username.getText().toString());
        editor1.apply();

        SharedPreferences sharedPrefs2 = getSharedPreferences(FILENAME2, 0);
        SharedPreferences.Editor editor2 = sharedPrefs2.edit();
        editor2.putString(VAL_KEY, password.getText().toString());
        editor2.apply();
    }

}
