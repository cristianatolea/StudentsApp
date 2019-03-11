package com.example.cris.studentsapp.screen.login.presenter;

import android.content.Context;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.apiprovider.handler.ErrorHandlerObservableSource;
import com.example.cris.studentsapp.screen.login.model.ILoginModel;
import com.example.cris.studentsapp.screen.login.model.entity.LoginRequestEntity;
import com.example.cris.studentsapp.screen.login.model.entity.LoginResponseEntity;
import com.example.cris.studentsapp.screen.login.view.delegate.ILoginViewDelegate;
import com.example.cris.studentsapp.utils.InternetUtils;
import com.example.cris.studentsapp.utils.LocalSaving;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter implements ILoginPresenter {

    private Context mContext;
    private ILoginViewDelegate mViewDelegate;
    private ILoginModel mModel;
    private CompositeDisposable mCompositeDisposable;

    public LoginPresenter(Context context,
                          ILoginViewDelegate viewDelegate,
                          ILoginModel loginModel) {
        mContext = context;
        mViewDelegate = viewDelegate;
        mModel = loginModel;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void login(final String username, final String password) {
        if (InternetUtils.hasActiveInternetConnection(mContext)) {
            mViewDelegate.showProgress();
            mCompositeDisposable.add(Observable
                    .create(new ObservableOnSubscribe<LoginRequestEntity>() {
                        @Override
                        public void subscribe(ObservableEmitter<LoginRequestEntity> emitter) throws Exception {
                            LoginRequestEntity loginRequestEntity = new LoginRequestEntity();
                            loginRequestEntity.setUsername(username);
                            loginRequestEntity.setPassword(password);
                            emitter.onNext(loginRequestEntity);
                            emitter.onComplete();
                        }
                    })
                    .concatMap(new Function<LoginRequestEntity, ObservableSource<LoginResponseEntity>>() {
                        @Override
                        public ObservableSource<LoginResponseEntity> apply(LoginRequestEntity requestEntity) throws Exception {
                            return mModel.login(requestEntity);
                        }
                    })
                    .onErrorResumeNext(new ErrorHandlerObservableSource<LoginResponseEntity>(mContext))
                    .map(new Function<LoginResponseEntity, LoginResponseEntity>() {
                        @Override
                        public LoginResponseEntity apply(LoginResponseEntity loginResponseEntityBaseResponse) throws Exception {
                            LocalSaving.setPrivateToken(mContext, loginResponseEntityBaseResponse.getPrivateToken());
                            LocalSaving.setToken(mContext, loginResponseEntityBaseResponse.getToken());
                            LocalSaving.setLsIsLoggedIn(mContext,true);
                            return loginResponseEntityBaseResponse;
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<LoginResponseEntity>() {
                        @Override
                        public void accept(LoginResponseEntity loginResponseEntity) throws Exception {
                            if (loginResponseEntity != null) {
                                mViewDelegate.onLoginSuccess(loginResponseEntity);
                            } else
                                mViewDelegate.onLoginFailed(mContext.getString(R.string.alert_error_occured));
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            mViewDelegate.onLoginFailed(throwable.getMessage());
                        }
                    })
            );
        }
    }
}
