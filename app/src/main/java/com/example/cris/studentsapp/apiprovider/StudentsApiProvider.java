package com.example.cris.studentsapp.apiprovider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class StudentsApiProvider {

    private static final long RETROFIT_TIME_OUT = 300;

    private String mBaseUrl;
    private OkHttpClient.Builder mOkHttpClient;
    private Gson mGson;
    private boolean mHasRxJava2Converter = false;
    private boolean mCanLog = false;

    private StudentsApiProvider() {
    }

    private static OkHttpClient.Builder getClient(OkHttpClient.Builder builder,
                                                  long timeout,
                                                  TimeUnit timeUnit) {
        return builder
                .connectTimeout(timeout, timeUnit)
                .readTimeout(timeout, timeUnit)
                .writeTimeout(timeout, timeUnit);
    }

    private static boolean getBuildConfigValue(@NonNull Context context) {
        try {
            Class<?> clazz = Class.forName(context.getPackageName() + ".BuildConfig");
            Field field = clazz.getField("DEBUG");
            return field.getBoolean(null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Create an instance of retrofit with provided interface. Method will return same interface
     *
     * @param apiClass Your interface who contains all your api methods {YourInterface}
     * @param <S>      Interface class {YouInterface.class}
     * @return Return an instance of your provided interfaces constructed with retrofit
     */
    @NonNull
    public <S> S createClient(@NonNull Class<S> apiClass) {
        Retrofit.Builder retrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl);

        if (mHasRxJava2Converter) {
            retrofit.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        }

        if (mGson != null) {
            retrofit.addConverterFactory(GsonConverterFactory.create(mGson));
        }
        if (mCanLog) {
            mOkHttpClient.addInterceptor(getInterceptor());
        }

        retrofit.client(mOkHttpClient.build());
        return retrofit.build().create(apiClass);
    }

    @NonNull
    public <S> S createClientWithToken(@NonNull Class<S> apiClass, Context context) {
        Retrofit.Builder retrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl);

        if (mHasRxJava2Converter) {
            retrofit.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        }

        if (mGson != null) {
            retrofit.addConverterFactory(GsonConverterFactory.create(mGson));
        }
        if (mCanLog) {
            mOkHttpClient.addInterceptor(getInterceptor());
        }

//        mOkHttpClient.authenticator(new RefreshTokenAuthentication(context));
//        mOkHttpClient.addInterceptor(new HeaderTokenInterceptor(context));

        retrofit.client(mOkHttpClient.build());
        return retrofit.build().create(apiClass);
    }

    // default logging interceptor who logs on Logcat
    private HttpLoggingInterceptor getInterceptor() {
        // pretty request logger
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    public static final class Builder {
        private String mBaseUrl;
        private OkHttpClient.Builder mOkHttpClient = null;
        private GsonBuilder mGson = null;
        private boolean mHasRxJava2Converter = false;
        private boolean mCanLog = false;

        /**
         * Builder constructor
         *
         * @param context Context to access state of debug build of calling app/package.
         *                Context must not be null
         * @param baseUrl Base url for created api.
         *                String must not be null
         */
        public Builder(@NonNull Context context, @NonNull String baseUrl) {
            if (context == null) {
                throw new NullPointerException("Context must not be null");
            }
            if (TextUtils.isEmpty(baseUrl)) {
                throw new IllegalArgumentException("BaseUrl must be non null or empty");
            }

            this.mBaseUrl = baseUrl;
            mGson = new GsonBuilder().setLenient();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            mCanLog = getBuildConfigValue(context);
            mOkHttpClient = getClient(builder, RETROFIT_TIME_OUT, TimeUnit.SECONDS);
        }

        /**
         * Create request timeout
         *
         * @param timeout Timeout in milliseconds must be bigger than 0 and smaller than one hour
         * @return Return builder to continue creation
         */
        public Builder withTimeout(long timeout, @NonNull TimeUnit timeUnit) {
            if (timeUnit == null) {
                throw new NullPointerException("TimeUnit must not be null");
            }
            if (timeUnit.toMillis(timeout) <= 0) {
                throw new IllegalArgumentException("Timeout must be bigger than zero(0)," +
                        " actual value is " + String.valueOf(timeout) + " " + timeUnit.toString());
            }
            if (timeUnit.toMillis(timeout) >= Integer.MAX_VALUE) {
                throw new IllegalArgumentException("Timeout should be smaller," +
                        " actual value is " + String.valueOf(timeout) + " " + timeUnit.toString());
            }
            mOkHttpClient = getClient(mOkHttpClient, timeout, timeUnit);
            return this;
        }

        /**
         * Is not demanded to provide this instance !!
         * We provide a default instance with 30 seconds timeout
         * <p>
         * <p>
         * Provide a custom OkHttp instance to the Builder.
         * Can contains an interceptor, an authenticator, etc
         *
         * @param okHttpClient Custom OkHttp instance
         * @return Return builder to continue creation
         */
        public Builder withOkHttpClient(@NonNull OkHttpClient.Builder okHttpClient) {
            if (okHttpClient == null) {
                throw new NullPointerException("OkHttp.Builder must not be null");
            }
            this.mOkHttpClient = okHttpClient;
            return this;
        }

        /**
         * Is not demanded to provide this instance !!
         * <p>
         * Provide a custom Gson to builder. By default he has just lenient property
         * Can be added a custom typed adapter or more
         *
         * @param gson Custom Gson instance
         * @return Return builder to continue creation
         */
        public Builder withGson(@NonNull GsonBuilder gson) {
            if (gson == null) {
                throw new NullPointerException("Gson must not be null");
            }
            this.mGson = gson;
            return this;
        }

        /**
         * If your request responses requires a RxJava conversion, you can set true on this method
         * and we provide RxJavaCallAdapter.
         *
         * @param hasRx2JavaConverter if is set to {@code true}, Retrofit response will be converted
         *                            to RxJava observable types
         * @return Return builder to continue creation
         */
        public Builder hasRx2JavaConverter(boolean hasRx2JavaConverter) {
            this.mHasRxJava2Converter = hasRx2JavaConverter;
            return this;
        }

        /**
         * Give date format patter to gson builder
         * If you use withGson, this will be ignored
         *
         * @param pattern date pattern
         * @return Return builder to continue creation
         */
        public Builder withDateFormat(String pattern) {
            mGson.setDateFormat(pattern);
            return this;
        }

        /**
         * Log requests and responses to Logcat
         * By default, if is set to true, logger will log only on debug mode of the calling app
         *
         * @param canLog Set to true if you want to log requests and responses (only in debug)
         * @return Return builder to continue creation
         */
        public Builder canLog(boolean canLog) {
            this.mCanLog = canLog;
            return this;
        }

        // generate instance of RetrofitApiProvider with previously provided values
        public StudentsApiProvider build() {
            StudentsApiProvider retrofitApiProvider = new StudentsApiProvider();
            retrofitApiProvider.mCanLog = this.mCanLog;
            retrofitApiProvider.mOkHttpClient = this.mOkHttpClient;
            retrofitApiProvider.mGson = this.mGson.create();
            retrofitApiProvider.mBaseUrl = this.mBaseUrl;
            retrofitApiProvider.mHasRxJava2Converter = this.mHasRxJava2Converter;
            return retrofitApiProvider;
        }
    }
}
