package com.example.waracci.teams.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.waracci.teams.Constants;
import com.example.waracci.teams.R;
import com.example.waracci.teams.adapters.PlayerListAdapter;
import com.example.waracci.teams.models.Player;
import com.example.waracci.teams.services.PlayerService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class PlayersActivity extends AppCompatActivity  {


    //shared preferences initialize values
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentTeam;

   @Bind(R.id.playersListView) ListView mPlayersListView;
    @Bind(R.id.teamDisplay) TextView mTeamDsiplay;
    @Bind(R.id.recyclerView)

    public RecyclerView mRecyclerView;

    private PlayerListAdapter mAdapter;

    private ArrayList<Player> mPlayers = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String team = intent.getStringExtra("team");
        mTeamDsiplay.setText("Team search for: " + team);
        getPlayers(team);

        //test for shared preferences
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentTeam = mSharedPreferences.getString(Constants.PREFERENCES_TEAM_KEY, null);

        //if there is no recent team searched for
        if (mRecentTeam != null) {
            getPlayers(mRecentTeam);
        }

        //log the recent team saved, to LogCat
       // Log.d("Shared pref location", mRecentTeam);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                addToSharedPreferences(query);
                getPlayers(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void getPlayers(String team) {
        final PlayerService playerService = new PlayerService();

        playerService.findPlayers(team, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response){
                    mPlayers = playerService.processResults(response);

                    PlayersActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter = new PlayerListAdapter(getApplicationContext(), mPlayers);
                            mRecyclerView.setAdapter(mAdapter);

                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PlayersActivity.this);

                            mRecyclerView.setLayoutManager(layoutManager);
                            mRecyclerView.setHasFixedSize(true);
//
                        }
                    });

            }
        });
    }

    private void addToSharedPreferences(String team) {
        mEditor.putString(Constants.PREFERENCES_TEAM_KEY, team).apply();
    }
}
