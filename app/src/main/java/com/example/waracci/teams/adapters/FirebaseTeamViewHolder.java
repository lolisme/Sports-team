package com.example.waracci.teams.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.waracci.teams.Constants;
import com.example.waracci.teams.R;
import com.example.waracci.teams.models.Player;
import com.example.waracci.teams.ui.PlayerActivityDetail;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by waracci on 9/22/17.
 */

public class FirebaseTeamViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    View mView;
    Context mContext;

    public FirebaseTeamViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }
    public void bindPlayer(Player player) {
        ImageView playerImageView = (ImageView) mView.findViewById(R.id.playerImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.playerName);
        TextView positionTextView = (TextView) mView.findViewById(R.id.position);
        TextView teamTextView = (TextView) mView.findViewById(R.id.team);

        Picasso.with(mContext)
                .load(player.getImage())
                .into(playerImageView);
        nameTextView.setText(player.getName());
        teamTextView.setText(player.getTeam());
        positionTextView.setText(player.getPosition());
    }
    @Override
    public void onClick (View view) {
        final ArrayList<Player> players = new ArrayList<>();

        DatabaseReference ref = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_SPORT_TEAMS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    players.add(snapshot.getValue(Player.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, PlayerActivityDetail.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("players", Parcels.wrap(players));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
