package za.ac.cput.olympicsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import za.ac.cput.olympicsapp.views.Add_Player_View;
import za.ac.cput.olympicsapp.views.View_Players;

public class MainActivity extends Activity {
    private Button viewPlayerBtn;
    private Button addPlayerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPlayerBtn = (Button)findViewById(R.id.btnViewPlayers);
        addPlayerBtn = (Button)findViewById(R.id.btnAddPlayer);

        viewPlayerBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i =  new Intent(MainActivity.this,View_Players.class);
                startActivity(i);
            }
        });

        addPlayerBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i =  new Intent(MainActivity.this,Add_Player_View.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
