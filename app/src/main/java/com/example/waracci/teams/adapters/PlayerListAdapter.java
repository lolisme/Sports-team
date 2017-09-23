package com.example.waracci.teams.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.waracci.teams.R;
import com.example.waracci.teams.models.Player;
import com.example.waracci.teams.ui.PlayerActivityDetail;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by waracci on 9/19/17.
 */

public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.PlayerViewHolder> {
    private ArrayList<Player> mPlayers = new ArrayList<>();
    private Context mContext;

    public PlayerListAdapter(Context context, ArrayList<Player> players) {
        mContext = context;
        mPlayers = players;
    }

    @Override
    public PlayerListAdapter.PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_list_item, parent, false);
        PlayerViewHolder viewHolder = new PlayerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PlayerListAdapter.PlayerViewHolder holder, int position) {
       holder.bindPlayer(mPlayers.get(position));
    }

    @Override
    public int getItemCount() {
        return mPlayers.size();
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.playerImageView) ImageView mPlayerImageView;
        @Bind(R.id.playerName) TextView mPlayerName;
        @Bind(R.id.position) TextView mPosition;
        @Bind(R.id.team) TextView mTeam;

        private Context mContext;

        public  PlayerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, PlayerActivityDetail.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("players", Parcels.wrap(mPlayers));
            mContext.startActivity(intent);
        }

        public void bindPlayer(Player player) {
            Picasso.with(mContext)
                    .load(player.getImage())
                    .into(mPlayerImageView);
            mPlayerName.setText(player.getName());
            mPosition.setText(player.getPosition());
            mTeam.setText(player.getTeam());
        }
    }
}
