package richa.osahub.com.vaccikaranapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class VacciWall extends AppCompatActivity {


    ListView posts;

    List<SfeedPojo> listOfFeeds = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacci_wall);
        posts = (ListView) findViewById(R.id.posts);

        for (int i = 0; i < 2; i++) {
            SfeedPojo sfeedPojo = new SfeedPojo("Neha", new Date(), "how will this be solved");
            listOfFeeds.add(sfeedPojo);
        }

        SarrayAdapter sarrayAdapter = new SarrayAdapter(VacciWall.this, listOfFeeds);
        posts.setAdapter(sarrayAdapter);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_notificationsettings) {
            Intent intent = new Intent(getApplicationContext(),NotificationSettings.class);
            startActivity(intent);
            //        finish();
            return true;

        }
        if(id == R.id.action_profile){
            Intent intent = new Intent(getApplicationContext(),Profile.class);
            startActivity(intent);
            //       finish();
            return true;

        }
        if (id == R.id.action_changeNumber){
            Intent intent = new Intent(getApplicationContext(),ChangeNumber.class);
            startActivity(intent);
            //     finish();
            return true;
        }
        if (id == R.id.action_changePassword){
            Intent intent = new Intent(getApplicationContext(),ChangePassword.class);
            startActivity(intent);
            //   finish();
            return true;
        }
        if (id == R.id.action_about_app){
            Intent intent = new Intent(getApplicationContext(),HelpActivity.class);

            startActivity(intent);
//            finish();
            return true;
        }
        if(id == R.id.action_about_us){
            Intent intent = new Intent(getApplicationContext(),AboutUsActivity.class);
            startActivity(intent);
            //            finish();
            return true;
        }
        if(id == R.id.action_signOut){
            final SharedPreferences prefs = getSharedPreferences("Richie", MODE_PRIVATE);
            final SharedPreferences.Editor edit = prefs.edit();

            Intent intent = new Intent(getApplicationContext(),SigninActivity.class);
            edit.putString("email", "");
            edit.putString("password","");
            edit.apply();
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
