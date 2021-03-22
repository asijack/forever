package com.asijack.common.ui.activity

import com.gfd.common.mvp.presenter.BasePresenter
import com.gfd.common.mvp.view.BaseView


/**
 * @description：MVP模式下-Activity的基类
 */
abstract class BaseMvpActivity<T : BasePresenter> : BaseActivity(), BaseView {

//    @Inject
//    lateinit var mPresenter: T
//
//    override fun initOperate() {
//        initActivityInjection()
//        injectComponent()
//    }
//
//    private fun initActivityInjection() {
//    }
//
//    /**
//     * 注册依赖对象
//     */
//    abstract fun injectComponent()

}