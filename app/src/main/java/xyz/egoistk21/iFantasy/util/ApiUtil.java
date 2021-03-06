package xyz.egoistk21.iFantasy.util;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import xyz.egoistk21.iFantasy.bean.HttpResult;
import xyz.egoistk21.iFantasy.bean.RawPlayer;
import xyz.egoistk21.iFantasy.bean.RecruitInfo;
import xyz.egoistk21.iFantasy.bean.RecruitResult;
import xyz.egoistk21.iFantasy.bean.RecruitedPlayer;
import xyz.egoistk21.iFantasy.bean.User;

/**
 * Created by EGOISTK21 on 2018/3/22.
 */

public class ApiUtil {
    public static final int FILTER_TIMEOUT = 1;
    private static final int TIMEOUT = 5;
    private static final String ROOT = "http://139.198.14.181/api/v1/";
    private static OkHttpClient sOkHttpClient =
            new OkHttpClient
                    .Builder()
                    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .build();
    private static Retrofit sRetrofit =
            new Retrofit
                    .Builder()
                    .client(sOkHttpClient)
                    .baseUrl(ROOT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

    private ApiUtil() {
    }

    public static VerificationApi getVerifyCodeAPI() {
        return sRetrofit.create(VerificationApi.class);
    }

    public static RegisterApi getRegisterApi() {
        return sRetrofit.create(RegisterApi.class);
    }

    public static LoginApi getLoginApi() {
        return sRetrofit.create(LoginApi.class);
    }

    public static RecruitInfoApi getRecruitInfoApi() {
        return sRetrofit.create(RecruitInfoApi.class);
    }

    public static LuckyRecruitApi getLuckyRecruitApi() {
        return sRetrofit.create(LuckyRecruitApi.class);
    }

    public static PentaLuckyRecruitApi getPentaLuckyRecruitApi() {
        return sRetrofit.create(PentaLuckyRecruitApi.class);
    }

//    public interface TestOnlineApi {
//        @Headers("User-Agent:iFantasy-android")
//        @POST("user/online")
//        @FormUrlEncoded
//        Observable<HttpResult<String>> isOnline(@Header("Authorization") String accesstoken,
//                                                @Field("user_id") int id);
//    }
//
//    public static TestOnlineApi getTestOnlineApi() {
//        return sRetrofit.create(TestOnlineApi.class);
//    }

    public static RecruitShowRawPlayerApi getRecruitShowRawPlayerApi() {
        return sRetrofit.create(RecruitShowRawPlayerApi.class);
    }

    public static DirectRecruitApi getDirectRecruitApi() {
        return sRetrofit.create(DirectRecruitApi.class);
    }

    public static TripleDirectRecruitApi getTripleDirectRecruitApi() {
        return sRetrofit.create(TripleDirectRecruitApi.class);
    }

    public interface VerificationApi {
        @Headers("User-Agent:iFantasy-android")
        @POST("user/verification")
        @FormUrlEncoded
        Observable<HttpResult<User>> verify(@Field("phone") String phone,
                                            @Field("zone") String zone,
                                            @Field("code") String code);
    }

    public interface RegisterApi {
        @Headers("User-Agent:iFantasy-android")
        @POST("user/register")
        @FormUrlEncoded
        Observable<HttpResult<User>> register(@Header("Authorization") String temptoken,
                                              @Field("phone") String phone,
                                              @Field("nickname") String nickname);
    }

    public interface LoginApi {
        @Headers("User-Agent:iFantasy-android")
        @POST("user/login")
        @FormUrlEncoded
        Observable<HttpResult<User>> login(@Header("Authorization") String logintoken,
                                           @Field("phone") String phone);
    }

    public interface RecruitInfoApi {
        @Headers("User-Agent:iFantasy-android")
        @GET("recruit/get_recruit_info")
        Observable<HttpResult<RecruitInfo>> getRecruitInfo(@Query("user_id") int user_id);
    }

    public interface LuckyRecruitApi {
        @Headers("User-Agent:iFantasy-android")
        @POST("recruit/one_recruit")
        @FormUrlEncoded
        Observable<HttpResult<RecruitResult>> recruit(@Field("user_id") int user_id);
    }

    public interface PentaLuckyRecruitApi {
        @Headers("User-Agent:iFantasy-android")
        @POST("recruit/five_recruit")
        @FormUrlEncoded
        Observable<HttpResult<ArrayList<RecruitResult>>> recruit(@Field("user_id") int userId);
    }

    public interface RecruitShowRawPlayerApi {
        @Headers("User-Agent:iFantasy-android")
        @GET("recruit/show_all_payer")
        Observable<HttpResult<ArrayList<RawPlayer>>> showPlayer(@Query("pos") int position,
                                                                @Query("type") int type);
    }

    public interface DirectRecruitApi {
        @Headers("User-Agent:iFantasy-android")
        @POST("recruit/recruit")
        @FormUrlEncoded
        Observable<HttpResult<RecruitedPlayer>> recruit(@Field("user_id") int userId,
                                                        @Field("player_id") int playerId);
    }

    public interface TripleDirectRecruitApi {
        @Headers("User-Agent:iFantasy-android")
        @POST("recruit/buy_theme")
        @FormUrlEncoded
        Observable<HttpResult<ArrayList<RecruitedPlayer>>> recruit(@Field("user_id") int userId,
                                                                   @Field("theme_id") int themeId);
    }

}
