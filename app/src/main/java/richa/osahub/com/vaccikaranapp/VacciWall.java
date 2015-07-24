package richa.osahub.com.vaccikaranapp;

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

    List<Sfeed> listOfFeeds = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacci_wall);
        posts = (ListView) findViewById(R.id.posts);

        for (int i = 0; i < 10; i++) {
            Sfeed sfeed = new Sfeed("abc", new Date(), "xyz");
            listOfFeeds.add(sfeed);
        }

        SarrayAdapter sarrayAdapter = new SarrayAdapter(VacciWall.this, listOfFeeds);
        posts.setAdapter(sarrayAdapter);

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
