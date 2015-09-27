package com.example.artsyntax.versus;

/**
 * Created by ArtSyntax on 17/5/2558.
 */
public class Action {

    public static User art;
    public static User mike;
    public static User god;
    public static User tete;
    public static User thisplayer;
    public static User newUser;

    static void createUser()
    {
        art = new User(1,"Art","blue");
        god = new User(1,"God","blue");
        mike = new User(1,"Mike","green");
        tete = new User(1,"Tete","green");
        thisplayer = art;
    }

    static void createUser(int uid, String uname, String uteam)
    {
        newUser = new User(uid, uname, uteam);
    }

    public void hit(User target)
    {
        if (thisplayer.usersp>0)
        {
            thisplayer.usersp--;
            target.userhp-=thisplayer.attack;
            if (target.userhp<=0)
            {
                target.death++;
                thisplayer.kill++;
                calculateStatus(target);
                calculateStatus(thisplayer);
                target.userhp=target.maxhp;
            }
        }
    }

    public void heal(User target)
    {
        if (thisplayer.usersp>0 && target.userhp<target.maxhp)
        {
            thisplayer.usersp--;
            target.userhp++;
            if (target.userhp>target.maxhp)
                target.userhp=target.maxhp;
        }
    }

    void calculateStatus(User target)
    {
        target.cruel=target.kill-target.death;
        //level
        if( target.cruel <=0 || target.level<1)
            target.level=1;
        else
            target.level= (int) (Math.floor(Math.log(target.cruel))/Math.log(2))+1;

        target.attack=target.level;
        target.maxhp=47+target.level*3;
        target.maxsp=17+target.level*3;
    }

    static void showUserStatus(User target)
    {
        System.out.println(target.level);
        System.out.println(target.team);
        System.out.println(target.name);
        System.out.println(target.userid);
        System.out.println(target.userhp);
        System.out.println(target.maxhp);
        System.out.println(target.usersp);
        System.out.println(target.maxsp);
        System.out.println(target.attack);
        System.out.println(target.kill);
        System.out.println(target.death);
        System.out.println(target.cruel);
        System.out.println(target.image);
        System.out.println();
    }

    public static void debugUser()
    {
        showUserStatus(art);
        showUserStatus(god);
        showUserStatus(tete);
        showUserStatus(thisplayer);
    }

}
