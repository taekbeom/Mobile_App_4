package ru.mirea.lebedeva.loadermanger;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;

import java.util.concurrent.ThreadLocalRandom;

public class MyLoader extends AsyncTaskLoader<String> {
    private String wordGiven;
    public static final String ARG_WORD = "word";


    public StringBuffer word;
    public String result;
    public int count;
    public int max;

    public MyLoader(@NonNull Context context, Bundle args) {
        super(context);
        if (args != null)
            wordGiven = args.getString(ARG_WORD);
    }
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
    @Override
    public String loadInBackground() {
        //SystemClock.sleep(5000);

        result = "";

        word = new StringBuffer(wordGiven);
        max = word.length();

        for (int i = 0; i < max; i++) {
            count = ThreadLocalRandom.current().nextInt(word.length());
            result += word.charAt(count);
            word.deleteCharAt(count);
        }

        return result;
    }
}
