package za.ac.cput.olympicsapp.views;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import za.ac.cput.olympicsapp.R;
import za.ac.cput.olympicsapp.domain.Player;
import za.ac.cput.olympicsapp.services.PlayerService;
import za.ac.cput.olympicsapp.services.impl.PlayerServiceImpl;

public class View_Players extends Activity {
    private List<Player> playerList;
    private ArrayList<String> stringPlayerList;
    private ListView listViewPlayers;
    List<Player> player = new LinkedList<Player>();
    String playerExist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__players);

        listViewPlayers = (ListView)findViewById(R.id.listViewPlayers);





        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                PlayerService service = new PlayerServiceImpl();

                playerList = service.getAllPlayers();

                stringPlayerList = new ArrayList<>();

                for(Player player: playerList){
                    stringPlayerList.add(player.getId() +"  "+ player.getPlayerName() +"  "+ player.getPlayerSurname()+"\n"+player.getPlayerAge());
                }
            }
        });

        thread.start();

        try {
            thread.join();
        }catch (Exception e){
            e.printStackTrace();
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(View_Players.this,android.R.layout.simple_list_item_1,android.R.id.text1, stringPlayerList);
        listViewPlayers.setAdapter(adapter);

        listViewPlayers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, final View v, final int position, long id) {
                AlertDialog.Builder adb = new AlertDialog.Builder(View_Players.this);
                adb.setTitle("Edit?");
                adb.setMessage("Are you sure you want to edit player?");
                final int positionToRemove = position;
                adb.setNegativeButton("No", null);
                adb.setPositiveButton("Yes", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        TextView text = (TextView)v;
                        String id = text.getText().toString().substring(0, text.getText().toString().indexOf(" "));
                        Intent i = new Intent(View_Players.this, Update_Player.class);
                        i.putExtra("id", id);
                        finish();
                        startActivity(i);
                    }
                });
                adb.show();
            }
        });

//        listViewPlayers.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {
//                AlertDialog.Builder adb = new AlertDialog.Builder(View_Players.this);
//                adb.setTitle("Edit?");
//                adb.setMessage("Are you sure you want to delete player?");
//                final int positionToRemove = position;
//                adb.setNegativeButton("No", null);
//                adb.setPositiveButton("Yes", new AlertDialog.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        TextView text = (TextView)view;
//                        String id = text.getText().toString().substring(0, text.getText().toString().indexOf(" "));
//                        final Long playerId = Long.parseLong(id);
//
//                        Thread thread = new Thread(new Runnable() {
//                            @Override
//                            public void run() {
//                                PlayerService service = new PlayerServiceImpl();
//                                playerList = service.getAllPlayers();
//
//                                for(Player p : playerList)
//                                {
//                                    if(p.getId() == playerId){
//                                         service.removePlayer(p);
//
//                                        break;
//                                    }
//
//                                }
//
//
//                            }
//                        });
//
//                        thread.start();
//
//                        try{
//                            thread.join();
//                        }catch (InterruptedException e){
//                            e.printStackTrace();
//                        }
//
//                        stringPlayerList.remove(position);
//
//                        adapter.notifyDataSetChanged();
//
//                    }
//                });
//                adb.show();
//                return true;
//            }
//        });
    }
}
