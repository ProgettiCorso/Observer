package fziviello.observer;

import android.os.Handler;
import java.util.Observable;
import java.util.Random;


public class MyObservable extends Observable {

    private String numRandom;

    private static Object objLock = new Object();
    private static MyObservable MY_OBSERVABLE = null;

    public static MyObservable getInstance() {

        synchronized (objLock) {

            if (MY_OBSERVABLE == null)
            {
                MY_OBSERVABLE = new MyObservable();
            }

            return MY_OBSERVABLE;
        }
    }

    public void loadRandomData() {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable()  {
            @Override
            public void run() {
                Random r = new Random();
                setNumRandom(String.valueOf(r.nextInt(10000 - 6000) + 6000));
                handler.postDelayed(this, 1000);
            }
        };

        handler.post(runnable);
    }


    public void setNumRandom(String numRandom) {
        this.numRandom = numRandom;
        setChanged();
        notifyObservers();
    }

    public String getNumRandom() {
        return numRandom;
    }
}
