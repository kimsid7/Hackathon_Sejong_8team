package com.showmethemoney.sejong.showmethemoney.network

import com.showmethemoney.sejong.showmethemoney.home.model.LoginInfoRequest
import com.showmethemoney.sejong.showmethemoney.home.model.LoginInfoResponse
import com.showmethemoney.sejong.showmethemoney.home.model.PubInfoRequest
import com.showmethemoney.sejong.showmethemoney.home.model.PubInfoResponse
import com.showmethemoney.sejong.showmethemoney.home.view.frag1.model.SelectMenuRequest
import com.showmethemoney.sejong.showmethemoney.home.view.frag1.model.SelectMenuResponse
import com.showmethemoney.sejong.showmethemoney.home.view.frag1.model.StockRequest
import com.showmethemoney.sejong.showmethemoney.home.view.frag1.model.StockResponse
import com.showmethemoney.sejong.showmethemoney.home.view.frag2.model.GetMyRank
import com.showmethemoney.sejong.showmethemoney.home.view.frag2.model.GetRank
import com.showmethemoney.sejong.showmethemoney.main.model.SigninPost
import com.showmethemoney.sejong.showmethemoney.main.model.SigninResponse
import com.showmethemoney.sejong.showmethemoney.signup.model.SignupPost
import com.showmethemoney.sejong.showmethemoney.signup.model.SignupResponse
import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by LeeWoochan on 2017-06-26.
 */
interface Service{
    @POST("gambler_php/signjsontest.php")
    fun postSignup(
            @Body signupPost: SignupPost
    ): Flowable<SignupResponse>

    @POST("gambler_php/login.php")
    fun postSignin(
            @Body signinPost: SigninPost
    ): Flowable<SigninResponse>

    @POST("gambler_php/infoget.php")
    fun postLoginInfo(
            @Body loginInfoRequest: LoginInfoRequest
    ): Flowable<LoginInfoResponse>

    @POST("gambler_php/barinfo.php")
    fun getPubInfo(
            @Body pubInfoRequest: PubInfoRequest
    ): Flowable<List<PubInfoResponse>>

    @POST("gambler_php/buytest.php")
    fun buyStock(
            @Body stockRequest: StockRequest
    ): Flowable<StockResponse>

    @POST("gambler_php/sell.php")
    fun sellStock(
            @Body stockRequest: StockRequest
    ): Flowable<StockResponse>

    @POST("gambler_php/select_menu_url.php")
    fun selectMenu(
            @Body selectMenuRequest: SelectMenuRequest
    ): Flowable<List<SelectMenuResponse>>

    @GET("gambler_php/rank.php")
    fun getRankInfo(

    ): Flowable<List<GetRank>>

    @POST("gambler_php/myrank.php")
    fun getMyRankInfo(
            @Body loginInfoRequest: LoginInfoRequest
    ): Flowable<GetMyRank>
}