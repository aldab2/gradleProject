package com.example.android.joker;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;





public class JokeGenerator {
    public static String fetchedJoke ="";
    public static String[] jokes = {"Today at the bank, an old lady asked me to help check her balance. So I pushed her over.",
            "I bought some shoes from a drug dealer. I don't know what he laced them with, but I've been tripping all day.",
    "I told my girlfriend she drew her eyebrows too high. She seemed surprised.",
    "I got another letter from this lawyer today. It said 'Final Notice'. Good that he will not bother me anymore.",
    "I dreamed I was forced to eat a giant marshmallow. When I woke up, my pillow was gone.",
    "One of the most wonderful things in life is to wake up and enjoy a cuddle with somebody; unless you are in prison.",
    "Husband: Wow, honey, you look really different today. Did you do something to your hair?\n" +
            "-\n" +
            "Wife: Michael, Im over here!"};

   /* public static String getJoke(){
        Random rnd = new Random();
        return jokes[rnd.nextInt(jokes.length)];
    }*/
    public static String getJoke()  {

        try {
            return jsonJokeParse(getJokeResponseFromHttpUrl());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "IO EXCEPTION";

    }




    public static String getJokeResponseFromHttpUrl() throws IOException {
        URL url = new URL("https://icanhazdadjoke.com/");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
        urlConnection.setRequestProperty("Accept", "application/json");
        urlConnection.connect();

        try {

            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");


            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return "No Input";
            }
        } finally {
            urlConnection.disconnect();
        }
    }
    public static String jsonJokeParse(String json){

        String joke = json.substring(json.indexOf("joke")+7,json.indexOf("status")-3);


        return joke;
    }
}


