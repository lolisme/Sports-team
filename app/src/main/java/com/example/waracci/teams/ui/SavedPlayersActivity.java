package com.example.waracci.teams.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.waracci.teams.Constants;
import com.example.waracci.teams.R;
import com.example.waracci.teams.adapters.FirebaseTeamViewHolder;
import com.example.waracci.teams.models.Player;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedPlayersActivity extends AppCompatActivity {

    private DatabaseReference mTeamsReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //Reuse the players activity to display the saved players.
        setContentView(R.layout.activity_players);
        ButterKnife.bind(this);

        mTeamsReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_SPORT_TEAMS);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Player, FirebaseTeamViewHolder>
                (Player.class, R.layout.player_list_item, FirebaseTeamViewHolder.class, mTeamsReference) {
            @Override
            protected void populateViewHolder(FirebaseTeamViewHolder viewHolder, Player model, int position) {
                viewHolder.bindPlayer(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}