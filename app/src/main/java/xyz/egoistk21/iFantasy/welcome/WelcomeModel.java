package xyz.egoistk21.iFantasy.welcome;

import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import xyz.egoistk21.iFantasy.bean.HttpResult;
import xyz.egoistk21.iFantasy.bean.User;
import xyz.egoistk21.iFantasy.util.ApiUtil;
import xyz.egoistk21.iFantasy.util.DBUtil;

import static xyz.egoistk21.iFantasy.util.ApiUtil.FILTER_TIMEOUT;

/**
 * Created by EGOISTK21 on 2018/3/22.
 */

class WelcomeModel implements WelcomeContract.Model {
    @Override
    public boolean isLogin() {
        return DBUtil.getLoginToken() != null;
    }

    @Override
    public boolean isOnline() {
        return true;
    }

    @Override
    public void register(String phone, String nickname, LifecycleProvider rxLifecycle, Observer<HttpResult<User>> observer) {
        ApiUtil.getRegisterApi().register(DBUtil.getLoginToken(), phone, nickname)
                .debounce(FILTER_TIMEOUT, TimeUnit.SECONDS)
                .compose(rxLifecycle.<HttpResult<User>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(observer);
    }

    @Override
    public void login(String phone, LifecycleProvider rxLifecycle, Observer<HttpResult<User>> observer) {
        ApiUtil.getLoginApi().login(DBUtil.getLoginToken(), phone)
                .debounce(FILTER_TIMEOUT, TimeUnit.SECONDS)
                .compose(rxLifecycle.<HttpResult<User>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(observer);
    }
}
