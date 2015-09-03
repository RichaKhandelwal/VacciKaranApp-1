package richa.osahub.com.vaccikaranapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*
 * Created by Dell on 29-07-2015.
*/
public class ChangePassword extends AppCompatActivity {
    EditText oldPass, newPass, reenter;
    Button confirm;
    Boolean validateFlag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        oldPass = (EditText) findViewById(R.id.oldPass);
        newPass = (EditText) findViewById(R.id.newPass);
        reenter = (EditText) findViewById(R.id.reenter);
        confirm = (Button) findViewById(R.id.confirm);


        final SharedPreferences prefs = getSharedPreferences("Richie", MODE_PRIVATE);
        final SharedPreferences.Editor edit = prefs.edit();
        newPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newPass.setHintTextColor(Color.GRAY);
                newPass.setTextColor(Color.BLACK);
            }
        });

        reenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reenter.setHintTextColor(Color.GRAY);
                reenter.setTextColor(Color.BLACK);
            }
        });

       /* newPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(prefs.contains("password") && prefs.getString("password", "").equals(oldPass.getText().toString()))) {
                    Toast.makeText(getApplicationContext(),"Incorrect Password",Toast.LENGTH_SHORT).show();
                    oldPass.setTextColor(Color.RED);
                }
            }

        });
        */

        newPass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!(prefs.contains("password") && prefs.getString("password", "").equals(oldPass.getText().toString()))) {
                    Toast.makeText(getApplicationContext(),"Incorrect Password",Toast.LENGTH_SHORT).show();
                    oldPass.setTextColor(Color.RED);
                }
                return false;
            }
        });

        oldPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldPass.setHintTextColor(Color.GRAY);
                oldPass.setTextColor(Color.BLACK);
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean flag = Validate();
           //     if (prefs.contains("password") && prefs.getString("password", "").equals(oldPass.getText().toString())){
                    if(flag){
                        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                        edit.putString("password",newPass.getText().toString());
                        Toast.makeText(getApplicationContext(),"Password Changed Successfully",Toast.LENGTH_SHORT).show();
                        edit.apply();
                        startActivity(intent);
             //       }
                }

            }
        });

    }


    public Boolean Validate(){
        validateFlag = true;
        if (reenter.getText().toString().equals("")) {
            validateFlag = false;
            Toast.makeText(getApplicationContext(), "Re-Enter Password", Toast.LENGTH_LONG).show();
            reenter.setHintTextColor(Color.RED);
        }

            if (!newPass.getText().toString().equals(reenter.getText().toString())) {
                validateFlag = false;
               Toast.makeText(getApplicationContext(),"Password did not Match ..",Toast.LENGTH_LONG).show();
                reenter.setTextColor(Color.RED);
            }



        return validateFlag;
    }
}