package com.example.waracci.teams.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.waracci.teams.Constants;
import com.example.waracci.teams.R;
import com.example.waracci.teams.models.Player;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerDetailsFragment extends Fragment implements View.OnClickListener{

    //private TextView mPlayerName;

    @Bind(R.id.xPlayerImageImageView) ImageView mPlayerImageView;
    @Bind(R.id.xPlayerNameTextView) TextView mPlayerName;
    @Bind(R.id.xPlayerIdTextView) TextView mPlayerId;
    @Bind(R.id.xPlayerNationality) TextView mPlayerNationality;
    @Bind(R.id.xPlayerSportTextView) TextView mPlayerSport;
    @Bind(R.id.xPlayerDateOfBirthTextView) TextView mPlayerDateOfBirth;
    @Bind(R.id.xPlayerDateOfSigning) TextView mPlayerDateSigned;
    @Bind(R.id.xPlayerPosition) TextView mPlayerPosition;
    @Bind(R.id.xPlayerHeight) TextView mPlayerHeight;
    @Bind(R.id.xPlayerWeightTextView) TextView mPlayerWeight;
    @Bind(R.id.xPlayerTeamTextView) TextView mPlayerTeam;
    @Bind(R.id.xSavePlayer)
    Button mSavePlayerx;
//    @Bind(R.id.playerDesciptionTextView) TextView mPlayerDescription;


    private Player mPlayer;

    public static PlayerDetailsFragment newInstance(Player player) {
        // Required empty public constructor
        PlayerDetailsFragment playerDetailsFragment = new PlayerDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable("player", Parcels.wrap(player));
        playerDetailsFragment.setArguments(args);
        return playerDetailsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPlayer = Parcels.unwrap(getArguments().getParcelable("player"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_details, container, false);
        ButterKnife.bind(this, view); //error here.
        //mPlayerName = (TextView) findViewById(R.id.playerName);
        mSavePlayerx.setOnClickListener(this);

        Picasso.with(view.getContext())
                .load(mPlayer.getImage())
                .into(mPlayerImageView);

        mPlayerName.setText("Name: " + mPlayer.getName());
        mPlayerId.setText("Id: " + mPlayer.getPlayerId());
        mPlayerNationality.setText("Athlete Nationality: " + mPlayer.getNationality());
        mPlayerSport.setText("Athlete Sport: " + mPlayer.getSport());
        mPlayerDateOfBirth.setText("Athlete Date of Birth: " + mPlayer.getDateOfBirth());
        mPlayerDateSigned.setText("Athlete Date Signed: " + mPlayer.getDateSigned());
//        mPlayerGender.setText(mPlayer.getGender());
        mPlayerPosition.setText("Athlete Position: " + mPlayer.getPosition());
        mPlayerHeight.setText("Athlete Height: " + mPlayer.getHeight());
        mPlayerWeight.setText("Athlete Weight: " + mPlayer.getWeight());
        mPlayerTeam.setText("Athlete Team: " + mPlayer.getTeam());
//        mPlayerDescription.setText(mPlayer.getDescription());


        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == mSavePlayerx) {
            DatabaseReference teamsRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_SPORT_TEAMS);
            teamsRef.push().setValue(mPlayer);
            Toast.makeText(getContext(), "Successfully saved to firebase", Toast.LENGTH_SHORT).show();
        }
    }

}
