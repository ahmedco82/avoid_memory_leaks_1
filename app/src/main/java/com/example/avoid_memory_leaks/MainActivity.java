package com.example.avoid_memory_leaks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.squareup.leakcanary.RefWatcher;

//https://android.jlelse.eu/9-ways-to-avoid-memory-leaks-in-android-b6d81648e35e
//https://www.raywenderlich.com/4690472-memory-leaks-in-android
// https://codingwithmitch.com/blog/memory-leaks-on-android/

public class MainActivity extends AppCompatActivity {

  //  private TextView mTextView;
    MyAsyncTask myAsyncTask;

    //https://www.youtube.com/watch?v=bNM_3YkK2Ws
   //https://codingwithmitch.com/blog/memory-leaks-on-android/
   // https://www.youtube.com/watch?v=a5YV6cwerX0
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //   mTextView = findViewById(R.id.txt);
        if(myAsyncTask!=null){
          finish();
        }
        myAsyncTask = new MyAsyncTask(MainActivity.this);
        myAsyncTask.execute();

    }

    @Override
    protected void onStart() {
        super.onStart();
        RefWatcher refWatcher = MyApplication.getRefWatcher(this);
        refWatcher.watch(this);
        //mApplication.onCreate();
    }

    private class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        private TextView textViewReference;
        private Context mContext;

        public MyAsyncTask(Context context) {
           mContext = context;
          //  this.textViewReference = textView;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.ic_launcher_background);
            try {
                Thread.sleep(9000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
          //  textViewReference.setText("some text");
        }
    }
}

