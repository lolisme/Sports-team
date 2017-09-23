package com.example.waracci.teams.services;

import android.util.Log;

import com.example.waracci.teams.Constants;
import com.example.waracci.teams.models.Player;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by waracci on 9/19/17.
 */

public class PlayerService {
    public static void findPlayers(String team, Callback callback) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.QUERY_PARAMETER, team);

        String url = urlBuilder.build().toString();

        OkHttpClient client = new OkHttpClient();

        Request request= new Request.Builder()
                .url(url)
                .build();

        Log.v("url mate", url);
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Player> processResults(Response response) {
        ArrayList<Player> players = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            Log.v("json data data", jsonData);
            if(response.isSuccessful()) {
                JSONObject sportJSON = new JSONObject(jsonData);

                JSONArray playersJSON = sportJSON.getJSONArray("player");

                Log.v("JSON", playersJSON.toString());

                for (int i = 0; i < playersJSON.length(); i++) {
                    JSONObject playersResponseJSON = playersJSON.getJSONObject(i);
                    String name = playersResponseJSON.getString("strPlayer");
                    String id = playersResponseJSON.getString("idPlayer");
                    String nationality = playersResponseJSON.getString("strNationality");
                    String sport = playersResponseJSON.getString("strSport");
                    String dateOfBirth = playersResponseJSON.getString("dateBorn");
                    String dateSigned = playersResponseJSON.getString("dateSigned");
                    String gender = playersResponseJSON.getString("strGender");
                    String position = playersResponseJSON.getString("strPosition");
                    String height = playersResponseJSON.getString("strHeight");
                    String weight = playersResponseJSON.getString("strWeight");
                    String image = playersResponseJSON.getString("strThumb");
                    String team = playersResponseJSON.getString("strTeam");
                    String description = playersResponseJSON.getString("strDescriptionEN");

                    //more data points

                    Player player = new Player(name, id, nationality,sport, dateOfBirth, dateSigned, gender, position, height, weight, image, team, description);
                    players.add(player);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return players;
    }
}
