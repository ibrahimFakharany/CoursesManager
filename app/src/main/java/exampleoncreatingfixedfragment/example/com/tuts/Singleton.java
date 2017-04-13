package exampleoncreatingfixedfragment.example.com.tuts;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by 450 G1 on 21/03/2017.
 */


public class Singleton {
    private static Singleton mInstance;
    private RequestQueue mRequestQueue;
    private Context mContext;

    private Singleton(Context mContext) {
        this.mContext = mContext;
        mRequestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue(){
        if(mRequestQueue == null)
        {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }
    public static synchronized Singleton getInstance(Context context){
        if(mInstance == null){
            mInstance =new Singleton(context);
        }
        return mInstance;
    }
    public <T>void addRequestQue(Request<T> request){
        mRequestQueue.add(request);
    }

}
