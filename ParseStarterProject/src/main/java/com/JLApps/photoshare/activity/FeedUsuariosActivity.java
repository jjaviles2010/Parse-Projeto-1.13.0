package com.JLApps.photoshare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.JLApps.photoshare.R;
import com.JLApps.photoshare.adapter.FeedsAdapter;

import java.util.ArrayList;
import java.util.List;

public class FeedUsuariosActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listViewFeeds;
    private ArrayList<ParseObject> listFeeds;
    private ArrayAdapter<ParseObject> adapter;
    private ParseQuery<ParseObject> query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_usuarios);

        listViewFeeds = (ListView)findViewById(R.id.list_feed_usuario);
        toolbar = (Toolbar)findViewById(R.id.toolbar_feed_usuario);


        Intent intent = getIntent();
        String userName = intent.getStringExtra("selectedUserName");

        if(userName != null){
            //Inicializa lista e adapter
            listFeeds = new ArrayList<>();
            adapter = new FeedsAdapter(FeedUsuariosActivity.this,listFeeds);
            listViewFeeds.setAdapter(adapter);

            toolbar.setTitle(userName);
            toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left);
            toolbar.setTitleTextColor(R.color.cinzaEscuro);
            setSupportActionBar(toolbar);

            loadUserFeeds(userName);
        }
    }

    private void loadUserFeeds(String userName){

        query = ParseQuery.getQuery("Imagem");
        query.whereEqualTo("username",userName);
        query.orderByDescending("createdAt");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null)//sucesso!
                {
                    listFeeds.clear();
                    for (ParseObject user: objects){
                        listFeeds.add(user);
                    }
                    adapter.notifyDataSetChanged();

                }else {
                    Toast.makeText(getApplicationContext(),"Erro ao procurar Feeds! Tente mais tarde.",Toast.LENGTH_LONG).show();
                }
            }
        });


    }

}
