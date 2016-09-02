package za.ac.cput.olympicsapp.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import za.ac.cput.olympicsapp.MainActivity;
import za.ac.cput.olympicsapp.R;
import za.ac.cput.olympicsapp.domain.Player;
import za.ac.cput.olympicsapp.factories.PlayerFactory;
import za.ac.cput.olympicsapp.services.PlayerService;
import za.ac.cput.olympicsapp.services.impl.PlayerServiceImpl;

public class Add_Player_View extends Activity {
    private Button btnSave;
    private Button btnCancel;
    private EditText txtName;
    private EditText txtSurname;
    private EditText txtAge;
    private String playerExist = "";
    Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__player__view);


        btnCancel = (Button)findViewById(R.id.btnCancel);
        btnSave = (Button)findViewById(R.id.btnSave);
        txtName = (EditText) findViewById(R.id.txtName);
        txtSurname = (EditText)findViewById(R.id.txtSurname);
        txtAge = (EditText)findViewById(R.id.txtAge);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Add_Player_View.this, MainActivity.class);
                finish();
                startActivity(i);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener(){
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
                            PlayerService service = new PlayerServiceImpl();

                            String name = txtName.getText().toString();
                            String surname = txtSurname.getText().toString();
                            String age = txtAge.getText().toString();
//                            player = new Player.Builder()
//                                    .PlayerName(name)
//                                    .PlayerSurname(surname)
//                                    .PlayerAge(age)
//                                    .build();

                  //  Player factory = PlayerFactory.getPlayer(name,surname,age);
                        Player factory = new Player(name,surname,age);
                            playerExist = service.addPlayer(factory);
                        }
                    });
                    thread.start();

                    try {
                        thread.join();

                    }catch (Exception e){

                        e.printStackTrace();
                    }
                }

                if(playerExist == null){

                    Toast.makeText(getApplicationContext(), "Successfully added a new player",
                            Toast.LENGTH_LONG).show();

                    Intent i = new Intent(Add_Player_View.this, View_Players.class);
                    finish();
                    startActivity(i);
                }else {
                    txtName.requestFocus();
                    txtName.setError("Already exists.");
                }
            }
        });

    }

    public void addPlayer(){

    }
}
