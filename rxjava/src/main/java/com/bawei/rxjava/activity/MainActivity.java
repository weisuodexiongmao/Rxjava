package com.bawei.rxjava.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bawei.rxjava.Bean.Beanweixin;
import com.bawei.rxjava.R;
import com.bawei.rxjava.api.Api;
import com.bawei.rxjava.inter.Apiweixin;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getNoParams();
    }
    public void getNoParams(){
        //得到网络请求数据源
        Retrofit retrofit=new Retrofit.Builder().baseUrl(Api.BASE_PATH).addConverterFactory(GsonConverterFactory.create())
               //支持Rxjava
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        Apiweixin apiweixin = retrofit.create(Apiweixin.class);
        //得到Observable被观察者      生产时间 得到数据源
        Observable<Beanweixin> observable = apiweixin.getNoParams();
         //被观察者订阅观察者  默认在同一个线程
        observable
                //指定io线程做耗时操作
                .subscribeOn(Schedulers.io())
                //指定更新UI在主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Beanweixin>() {
            @Override
            public void onCompleted() {//完成

            }

            @Override
            public void onError(Throwable e) {//失败

            }

            @Override
            public void onNext(Beanweixin beanweixin) {//消费事件
                List<Beanweixin.ResultBean.DataBean> data = beanweixin.getResult().getData();
                for (Beanweixin.ResultBean.DataBean list:data) {
                    System.out.println("****************"+list.getTitle());
                }
            }
        });

    }
}
