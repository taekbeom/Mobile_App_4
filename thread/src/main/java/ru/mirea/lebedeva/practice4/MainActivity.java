package ru.mirea.lebedeva.practice4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int counter = 0;

    int lessonCount;
    int dayCount;
    int resultCount;

    TextView result;
    EditText lesson;
    EditText day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView infoTextView = findViewById(R.id.textView);
        Thread mainThread = Thread.currentThread();
        infoTextView.setText("Current thread: " + mainThread.getName());

        mainThread.setName("MireaThread");
        infoTextView.append("\n New thread name: " + mainThread.getName());

        result = findViewById(R.id.textView2);

        lesson = findViewById(R.id.lesson);
        day = findViewById(R.id.day);

    }

    public void onClick(View view) {

        Runnable runnable = new Runnable() {
            public void run() {
                int numberThread = counter++;
                Log.i("ThreadProject", "Index of executed thread: " + numberThread);
                long endTime = System.currentTimeMillis() + 20 * 1000;
                while (System.currentTimeMillis() < endTime) {
                    synchronized (this) {
                        try {
                            wait(endTime - System.currentTimeMillis());
                        } catch (Exception e) { }
                    }
                }
                Log.i("ThreadProject", "Index of finished thread: " + numberThread);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void onClickLessonsPerDay(View view){
        Runnable runnable = new Runnable() {
            public void run() {
                synchronized (this) {
                        try {
                            try{
                                lessonCount = Integer.parseInt(lesson.getText().toString());
                                dayCount = Integer.parseInt(day.getText().toString());
                            }
                            catch (Exception exception){}
                            resultCount = lessonCount / dayCount;
                            result.setText(String.valueOf(resultCount));
                        } catch (Exception e) { }
                    }
                }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}