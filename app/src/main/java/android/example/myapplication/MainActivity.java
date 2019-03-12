package android.example.myapplication;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);
        //setContentView(R.layout.layout_login);
        //setContentView(R.layout.activity_main);

    }

    public void btn_toLogin(View view) {
        setContentView(R.layout.layout_login);
    }

    public void btn_SignUp(View view) {

        String username;
        String email;
        String password;

        username = ((TextView) findViewById(R.id.name)).getText().toString();
        email = ((TextView) findViewById(R.id.email)).getText().toString();
        password = ((TextView) findViewById(R.id.password)).getText().toString();

        new SignUp().execute(username, email, password);
/*
        SharedPreferences email_pass = getSharedPreferences("password",MODE_PRIVATE);
        SharedPreferences email_user = getSharedPreferences("username",MODE_PRIVATE);

        SharedPreferences.Editor editor_pass = email_pass.edit();
        SharedPreferences.Editor editor_user = email_user.edit();

        editor_pass.putString(email, password);
        editor_user.putString(email,username);

        editor_pass.commit();
        editor_user.commit();

        Toast.makeText(this,"Registration success",Toast.LENGTH_LONG).show();


        setContentView(R.layout.layout_login);//
*/
    }


    public void btn_toRegister(View view) {
        setContentView(R.layout.layout_register);
    }


    public void btn_Login(View view) {

        String email;
        String password;

        email = ((TextView) findViewById(R.id.email)).getText().toString();
        password = ((TextView) findViewById(R.id.password)).getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "The email and password cannot be empty", Toast.LENGTH_LONG).show();
            return;
        }
        new Login().execute(email, password);
        /*

        SharedPreferences email_pass=getSharedPreferences("password",MODE_PRIVATE);
        String lod_password;

        lod_password=email_pass.getString(email,"");
        if(password.equals(lod_password))
        {
            setContentView(R.layout.activity_main);
            return;
        }
*/

    }

    public void btnLogout(View view) {
        setContentView(R.layout.layout_login);
        return;
    }

    private class SignUp extends AsyncTask<String, Void, Boolean> {

        protected Boolean doInBackground(String... strings) {
            SharedPreferences email_pass = getSharedPreferences("password", MODE_PRIVATE);
            SharedPreferences email_user = getSharedPreferences("username", MODE_PRIVATE);

            SharedPreferences.Editor editor_pass = email_pass.edit();
            SharedPreferences.Editor editor_user = email_user.edit();

            editor_pass.putString(strings[1], strings[2]);
            editor_user.putString(strings[1], strings[0]);

            editor_user.commit();
            editor_pass.commit();

            return Boolean.TRUE;
        }
        protected void onPostExecute(Boolean success) {
            if (success) {
                Toast.makeText(getApplicationContext(), "Registration success", Toast.LENGTH_LONG).show();
                setContentView(R.layout.layout_login);//
            }
        }
    }



    private class Login extends AsyncTask<String, Void, Boolean> {

        protected Boolean doInBackground(String... strings) {

            SharedPreferences email_pass = getSharedPreferences("password", MODE_PRIVATE);
            String lod_password;

            lod_password = email_pass.getString(strings[0], "");
          //  Log.d("原有",lod_password);
         //   Log.d("现在输入",strings[1]);
            if (strings[1].equals(lod_password)) {

                return true;
            }
            else {
                return false;
            }
        }

        protected void onPostExecute(Boolean success) {
            if (success) {
                setContentView(R.layout.activity_main);
                Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "Password is wrong", Toast.LENGTH_LONG).show();
            }
        }


    }
}
