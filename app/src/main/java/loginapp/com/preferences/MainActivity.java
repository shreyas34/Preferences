package loginapp.com.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private CheckBox checkboxid;
    private Button buttonid;
    private SharedPreferences preferences;
    private final static String PREF_CREDENTIALS="LOGIN";
    private final static String PREF_USER="username";
    private final static String PREF_PASS="password";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameEditText=findViewById(R.id.usernameEditText);
        passwordEditText=findViewById(R.id.passwordEditText);
        checkboxid=findViewById(R.id.checkboxid);
        buttonid=findViewById(R.id.buttonid);
        preferences=getSharedPreferences(PREF_CREDENTIALS, Context.MODE_PRIVATE);

        if(isLoggedIn())
        {
            String username=preferences.getString(PREF_USER,"");
            String password=preferences.getString(PREF_PASS,"");
            usernameEditText.setText(username);
            passwordEditText.setText(password);
        }

        buttonid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=usernameEditText.getText().toString();
                String password=passwordEditText.getText().toString();
                if(checkboxid.isChecked())
                {
                    preferences.edit().putString(PREF_USER,username).apply();
                    preferences.edit().putString(PREF_PASS,password).apply();
                }
                Toast.makeText(MainActivity.this,"login success",Toast.LENGTH_SHORT).show();

            }
        });


    }
    private boolean isLoggedIn()
    {
        return preferences.contains(PREF_USER)&& preferences.contains(PREF_PASS);
    }
}
