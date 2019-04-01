package com.qingmei2.samplepaging.ui.header_multitype

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.qingmei2.samplepaging.R
import com.qingmei2.samplepaging.ui.header_proxy.HeaderProxyAdapter
import com.qingmei2.samplepaging.viewmodel.CommonViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_header_multitype.*
import java.util.concurrent.TimeUnit

/**
 * 直接通过多类型列表的方式实现Header
 *
 * 实际上是有缺陷的，详情参考[HeaderMultiTypeAdapter]和运行后的效果
 */
class HeaderMultiTypeActivity : AppCompatActivity() {

    private lateinit var mAdapter: HeaderMultiTypeAdapter

    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T = CommonViewModel(application) as T
        }).get(CommonViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_header_multitype)

        mAdapter = HeaderMultiTypeAdapter()
        recyclerView.adapter = mAdapter

        binds()

        viewModel.getRefreshLiveData().observe(this, Observer { mAdapter.submitList(it) })
    }

    private fun binds() {
        // 模拟下拉刷新
        mSwipeRefreshLayout.setOnRefreshListener {
            mSwipeRefreshLayout.isRefreshing = true
            Observable.just(0)
                    .delay(2, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext {
                        mSwipeRefreshLayout.isRefreshing = false
                        mAdapter.submitList(null)
                        viewModel.getRefreshLiveData()
                                .observe(this, Observer { mAdapter.submitList(it) })
                    }
                    .subscribe()
        }
    }
}
