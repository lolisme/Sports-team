package com.example.waracci.teams.models;

import org.parceler.Parcel;

/**
 * Created by waracci on 9/19/17.
 */

@Parcel
public class Player {
    private String name;
    private String id;
    private String nationality;
    private String sport;
    private String dateOfBirth;
    private String dateSigned;
    private String gender;
    private String position;
    private String height;
    private String weight;
    private String imageUrl;
    private String team;
    private String description;
    private String pushId;

    public Player() {}

    public Player(String name, String id, String nationality, String sport, String dateOfBirth, String dateSigned, String gender, String position, String height, String  weight, String imageUrl, String team, String description) {
        this.name = name;
        this.id = id;
        this.nationality = nationality;
        this.sport = sport;
        this.dateOfBirth = dateOfBirth;
        this.dateSigned = dateSigned;
        this.gender = gender;
        this.position = position;
        this.height = height;
        this.weight = weight;
        this.imageUrl = imageUrl;
        this.team = team;
        this.description = description;
    }

    public String getName (){
        return name ;
    }
    public String getPlayerId (){
        return id ;
    }
    public String getNationality (){
        return nationality ;
    }
    public String getSport (){
        return sport ;
    }
    public String getDateOfBirth (){
        return dateOfBirth ;
    }
    public String getDateSigned (){
        return dateSigned ;
    }
    public String getGender (){
        return gender ;
    }
    public String getPosition (){
        return position ;
    }
    public String getHeight (){
        return height ;
    }
    public String getWeight (){
        return weight ;
    }
    public String getImage (){
        return imageUrl ;
    }
    public String getTeam (){
        return team ;
    }
    public String getDescription (){
        return description ;
    }
    public String getPushId() {
        return pushId;
    }
    public void setPushId( String pushId) {
        this.pushId = pushId;
    }
}




//, String id, String nationality, String sport, String dateOfBirth, String dateSigned, String gender, String position, String height, String  weight, String imageUrl, String team, String description