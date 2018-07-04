package com.abewkayew.serafelagi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rengwuxian.materialedittext.MaterialEditText;
import java.util.HashMap;
import java.util.Map;
import ru.dimorinny.floatingtextbutton.FloatingTextButton;
public class Login extends AppCompatActivity {
    public String fname,lname;
    public int number;
    EditText check_name;
    FloatingTextButton loginButton;
    MaterialEditText userEmail1;
    MaterialEditText username1;
    MaterialEditText password1;
    CheckBox loginCheckBox;
    ProgressBar loginProgressBar;
    TextView register_new_user;
    String profile_image;
    String phone;
    String url = "http://10.6.71.76/sera_felagi/login/register_modified.php";
    public static final String KEY_USER_NAME = "username";
    public static final String KEY_USER_EMAIL = "useremail";
    public static final String KEY_USER_PASSWORD = "userpassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        check_name = findViewById(R.id.check_name);
//        initialize all the view variables...
        register_new_user = findViewById(R.id.register_new_user);
        username1 = findViewById(R.id.username);
//        username1.setFloatingLabelAnimating(true);
        password1 = findViewById(R.id.password1);
        userEmail1 = findViewById(R.id.userEmail);

        loginCheckBox = findViewById(R.id.checkBoxRememberMe);
        loginProgressBar = findViewById(R.id.login_progress);
        loginButton = findViewById(R.id.loginButton);
        final String username_pref = check_name.getText().toString();
        String password_pref = password1.getText().toString();
        String email_pref = userEmail1.getText().toString();
        if(loginCheckBox.isChecked()){
            saveLoginDetails(username_pref, password_pref, email_pref, "", "0911995101");

        }

        register_new_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Login.this, username_pref, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login.this, Job_first_entry.class);
                startActivity(intent);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registerUser();
            }
        });
    }

        private void registerUser() {
                final String username = username1.getText().toString().trim();
                final String email = userEmail1.getText().toString().trim();
                final String password = password1.getText().toString().trim();
//        ksdofn
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Login.this, "Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, Job_first_entry.class);

                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            //This indicates that the reuest has either time out or there is no connection
                            Toast.makeText(getApplicationContext(), "Timeout or no connection error", Toast.LENGTH_LONG).show();
                        } else if (error instanceof AuthFailureError) {
                            // Error indicating that there was an Authentication Failure while performing the request
                            Toast.makeText(getApplicationContext(), "Authentication error", Toast.LENGTH_LONG).show();
                        } else if (error instanceof ServerError) {
                            //Indicates that the server responded with a error response
                            Toast.makeText(getApplicationContext(), "Server error", Toast.LENGTH_LONG).show();
                        } else if (error instanceof NetworkError) {
                            //Indicates that there was network error while performing the request
                            Toast.makeText(getApplicationContext(), "Network error", Toast.LENGTH_LONG).show();
                        } else if (error instanceof ParseError) {
                            // Indicates that the server response could not be parsed
                            Toast.makeText(getApplicationContext(), "Response parse error", Toast.LENGTH_LONG).show();

                        }
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put(KEY_USER_NAME, username);
                        params.put(KEY_USER_EMAIL, email);
                        params.put(KEY_USER_PASSWORD, password);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);

            }

            public String getUsername() {
                SharedPreferences sp = this.getSharedPreferences("LoginDetails", this.MODE_PRIVATE);
                return sp.getString("username", "");
            }

            public void saveLoginDetails(String username, String password,
                                          String userEmail, String profileImage, String phone) {
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("LoginDetails", getApplicationContext().MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", username);
                editor.putString("Password", password);
                editor.putString("userEmail", userEmail);
                editor.putString("profileImage", profileImage);
                editor.putString("phone", phone);
                editor.commit();
            }

            private boolean isLogedOut() {
                SharedPreferences sharedPreferences = this.getSharedPreferences("LoginDetails", this.MODE_PRIVATE);
                boolean isEmailEmpty = sharedPreferences.getString("username", "").isEmpty();
                boolean isPasswordEmpty = sharedPreferences.getString("Password", "").isEmpty();
                return isEmailEmpty || isPasswordEmpty;
            }

            @Override
            public void onBackPressed() {
                super.onBackPressed();
                finishAffinity();

            }

            //    back actionbar implementation
            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                if (item.getItemId() == android.R.id.home) {
                    finish();
                }
                return true;
            }
        }