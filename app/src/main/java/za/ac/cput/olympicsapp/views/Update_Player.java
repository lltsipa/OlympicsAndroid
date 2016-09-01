package za.ac.cput.olympicsapp.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import za.ac.cput.olympicsapp.R;
import za.ac.cput.olympicsapp.domain.Player;
import za.ac.cput.olympicsapp.factories.PlayerFactory;
import za.ac.cput.olympicsapp.services.PlayerService;
import za.ac.cput.olympicsapp.services.impl.PlayerServiceImpl;

public class Update_Player extends AppCompatActivity {

    private Button btnEdit;
    private Button btnCancel;
    private EditText txtName;
    private EditText txtSurname;
    private EditText txtAge;


    private String playerExist = "";
    private Player player;
    PlayerService service;


    private List<Player> playerList;
    private List<Player> stringPlayerList;
    List<Player> playerLinkedList = new LinkedList<Player>();
    Long id = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__player);

        btnCancel = (Button)findViewById(R.id.btnCancel);
        btnEdit = (Button)findViewById(R.id.btnEdit);
        txtName = (EditText) findViewById(R.id.txtName);
        txtSurname = (EditText)findViewById(R.id.txtSurname);
        txtAge = (EditText)findViewById(R.id.txtAge);


        final String id = getIntent().getStringExtra("id");

        final Long playerId = Long.parseLong(id);

        try{
            if(playerId != null){

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        service = new PlayerServiceImpl();
                        playerList = service.getAllPlayers();

                        for(Player p : playerList)
                        {
                            if(p.getId() == playerId){
                               // playerExist = service.updatePlayer(p);
                                txtName.setText(p.getPlayerName());
                                txtSurname.setText(p.getPlayerSurname());
                                txtAge.setText(p.getPlayerAge());
                                break;
                            }

                        }


                    }
                });

                thread.start();

                try{
                    thread.join();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
              //return;
             }
        }catch (Exception e){
            e.printStackTrace();
        }




//

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Update_Player.this, View_Players.class);
                finish();
                startActivity(i);
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtName.getText().toString().equals("")) {

                    txtName.requestFocus();
                    txtName.setError("Cannot be empty!");
                }
                else if(txtSurname.getText().toString().equals("")) {

                    txtSurname.requestFocus();
                    txtSurname.setError("Cannot be empty!");
                }
                else if (txtAge.getText().toString().equals("")) {

                    txtAge.requestFocus();
                    txtAge.setError("Cannot be empty!");
                }else {
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                           // service = new PlayerServiceImpl();

                            String name = txtName.getText().toString();
                            String surname = txtSurname.getText().toString();
                            String age = txtAge.getText().toString();

                            Player factory = new Player(playerId, name,surname,age);

                            playerExist = service.updatePlayer(factory);
                        }
                    });
                    thread.start();

                    try {
                        thread.join();

                    }catch (Exception e){

                        e.printStackTrace();
                    }
                }

                if(playerExist != null){

                    Toast.makeText(getApplicationContext(), "Successfully updated player details",
                            Toast.LENGTH_LONG).show();

                    Intent i = new Intent(Update_Player.this, View_Players.class);
                    finish();
                    startActivity(i);
                }else {
                    txtName.requestFocus();
                    txtName.setError("Already exists.");
                }
            }
        });
    }
}
