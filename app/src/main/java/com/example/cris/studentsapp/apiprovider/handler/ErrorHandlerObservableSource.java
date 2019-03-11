package com.example.cris.studentsapp.apiprovider.handler;

import android.content.Context;
import android.util.Log;

import com.example.cris.studentsapp.R;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import retrofit2.HttpException;

import static com.example.cris.studentsapp.app.StudentsApplication.getContext;

/**
 * Created by Catalin Florescu on 13/12/2018.
 * Roweb
 * catalin.florescu@roweb.ro
 */
public class ErrorHandlerObservableSource<T> implements Function<Throwable, ObservableSource<T>> {

    private Context mContext;

    private static final String TAG = "ErrorHandlerObservableS";

    public ErrorHandlerObservableSource(Context context) {
        mContext = context;
    }

    @Override
    public ObservableSource<T> apply(Throwable throwable) throws Exception {
        throwable.printStackTrace();
        if (throwable instanceof HttpException) {
            HttpException exception = (HttpException) throwable;
            try {
                String errorBodyString = exception.response().errorBody().string();
                Log.e(TAG, errorBodyString);
                JSONObject jsonObject = new JSONObject(errorBodyString);
                Object errorObj = jsonObject.get("message");
                if (errorObj instanceof String) {
                    return Observable.error(new Throwable(getErrorMessage(mContext, errorObj.toString())));
                } else {
                    Type type = new TypeToken<LinkedTreeMap<String, List<String>>>() {
                    }.getType();
                    LinkedTreeMap<String, List<String>> ltm = new Gson().fromJson(jsonObject.get("message").toString(), type);
                    String error = ltm.entrySet().iterator().next().getValue().get(0);
                    return Observable.error(new Throwable(getErrorMessage(mContext, error)));
                }
            } catch (Exception e) {
                e.printStackTrace();
                return Observable.error(new Throwable(mContext.getString(R.string.alert_error_occured)));
            }
        }
        return Observable.error(new Throwable(mContext.getString(R.string.alert_error_occured)));
    }

    private String getErrorMessage(Context context, String errorMessage) {
        int resId = context.getResources().getIdentifier(errorMessage, "string", getContext().getPackageName());
        if (resId == 0) {
            return context.getResources().getString(R.string.alert_error_occured);
        } else
            return context.getResources().getString(resId);
    }
}