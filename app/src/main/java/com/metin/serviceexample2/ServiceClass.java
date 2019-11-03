package com.metin.serviceexample2;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class ServiceClass extends Service {

    private static final String TAG = "ServiceClass";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return null;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: " + "starting service");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: " + "stopping service");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {  //Her startService çağrıldığında çağrılır
        Log.d(TAG, "onStartCommand: " + "calling Async Class");

        AsyncTaskingClass asyncTaskingClass = new AsyncTaskingClass();
        asyncTaskingClass.execute(5000);

        return super.onStartCommand(intent, flags, startId);
    }

    class AsyncTaskingClass extends AsyncTask<Integer, Void, Void>{

        private static final String TAG = "AsyncTaskingClass";

        @Override
        protected void onPreExecute() {  //İlk bu metot çağrılır
            Log.d(TAG, "onPreExecute: " + "starting thread");
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Integer... integers) {  //Arkaplan işlerini bu halleder
            Log.d(TAG, "doInBackground: " + "doing some stuff on thread");

            try {
                Thread.sleep(integers[0]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {  //İlerleme çubuğunu kullanmak için bu metot kullanılır
            Log.d(TAG, "onProgressUpdate: " + "i am moving ahead hold on");
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {  //En son bu metot çağrılır, istenirse sonuçlar buna gönderilebilir
            Log.d(TAG, "onPostExecute: " + "thread ended");
            super.onPostExecute(aVoid);
        }
    }
}
