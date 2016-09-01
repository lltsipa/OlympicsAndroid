package za.ac.cput.olympicsapp.conf.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by Lonwabo on 8/28/2016.
 */
public class App extends Application {

    public static Context context;

    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return App.context;
    }

}
