package com.bawei.rxjava.inter;

import com.bawei.rxjava.Bean.Beanweixin;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by 猥琐的熊猫 on 2017/11/5.
 */

public interface Apiweixin {
    @GET("toutiao/index?key=e76b62dbe5ce78645516fe866dc7058b&type=top")
    Observable<Beanweixin> getNoParams();
}
