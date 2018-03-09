package fziviello.observer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    private TextView Txt_random;
    private MyObservable myObservable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Txt_random=(TextView) findViewById(R.id.id_txt_random);

        myObservable = MyObservable.getInstance();
        myObservable.addObserver(this);
        myObservable.loadRandomData();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(myObservable !=null)
        {
            myObservable.deleteObserver(this);
        }

    }

    @Override
    public void update(Observable observable, Object obj) {

        if (observable!=null && observable instanceof MyObservable) {
            MyObservable myObservable =(MyObservable)observable;
            Txt_random.setText(myObservable.getNumRandom());
        }
    }
}
