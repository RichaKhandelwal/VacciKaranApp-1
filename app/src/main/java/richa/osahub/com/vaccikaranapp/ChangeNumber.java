package richa.osahub.com.vaccikaranapp;

/**
 * Created by Dell on 29-07-2015.
 */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ChangeNumber extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_change_number);
        Button save;
        final EditText sphone;
        sphone=(EditText)findViewById(R.id.sNewNumver);
        sphone.getText();
        save =(Button)findViewById(R.id.sSave);
        final SharedPreferences prefs= getSharedPreferences("Richie",MODE_PRIVATE);
        final SharedPreferences.Editor edit = prefs.edit();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert;
                alert = new AlertDialog.Builder(ChangeNumber.this);
                alert.setTitle("Confirmation");
                alert.setMessage("  Are you sure you want to change your number to  " + sphone.getText().toString()) ;
                alert.setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intet = new Intent(getApplicationContext(), HomeActivity.class);
                        edit.putString("phone", sphone.getText().toString());
                        edit.apply();
                        Toast.makeText(getApplicationContext(), "Number changed Successfully", Toast.LENGTH_LONG).show();

                        startActivity(intet);
                    }
                });
                alert.setNegativeButton("Deny", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                        Toast.makeText(getApplicationContext(),"Number did not changed",Toast.LENGTH_LONG).show();
                        startActivity(intent);
                    }
                });
                alert.show();

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

