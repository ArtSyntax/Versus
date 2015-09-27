package com.example.artsyntax.versus;
import java.lang.Math;

/**
 * Created by ArtSyntax on 17/5/2558.
 */
public class User {

    int level;
    String team;
    String name;
    int userid;
    int userhp;
    int usersp;
    int maxhp;
    int maxsp;
    int attack;
    int kill;
    int death;
    int cruel;
    int delay;
    String image;

    public User()
    {

    }
    public User(int uid, String uname, String uteam)
    {
        team=uteam;
        name=uname;
        userid=uid;

        kill=0;
        death=0;
        cruel=kill-death;

        //level
        if( cruel <=0 || level<1)
            level=1;
        else
            level= (int) (Math.floor(Math.log(cruel))/Math.log(2))+1;

        attack=level;
        maxhp=47+level*3;
        maxsp=17+level*3;

        userhp=maxhp;
        usersp=maxsp;
        image="@drawable/user_"+uname.toLowerCase();

    }

}
