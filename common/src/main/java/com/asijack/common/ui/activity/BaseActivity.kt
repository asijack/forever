package com.asijack.common.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.asijack.common.R
import com.asijack.common.common.AppManager
import com.asijack.common.ui.widgets.StateView
import com.gyf.immersionbar.ImmersionBar

/**
 * @description：Activity的基类
 */
abstract class BaseActivity : AppCompatActivity() {

    /** 多状态布局View*/
    private var statusView: StateView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManager.instance.addActivity(this)
        if (isSetStateBar()) {
            setStatusBar()
        }
    }

    /** 是都设置沉浸式状态来，true：设置，默认为设置*/
    open fun isSetStateBar() = true

    /** 配置多状态布局*/
    private fun setStatusLayout() {
        statusView = StateView.inject(findViewById(R.id.content_id))
    }

    /**
     * 是否设置多状态View 默认为false
     * @return Boolean true:设置
     */
    open fun isSetStateView(): Boolean = false

    /** 显示Loading*/
    fun showLoading() {
        statusView?.showLoading()
    }

    /** 显示内容*/
    fun showContent() {
        statusView?.showContent()
    }

    /**
     * 设置透明状态栏
     */
    private fun setStatusBar() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f)
                .navigationBarColor(R.color.as_status_bar_color)
                .keyboardEnable(true)
                .init()
        //添加内容布局距离屏幕的距离
        if (isSetPaddingTop()) {
            val rootView = this.window.decorView.findViewById(android.R.id.content) as ViewGroup
            rootView.setPadding(
                    0,
                    ImmersionBar.getStatusBarHeight(this),
                    0,
                    0
            )
            rootView.setBackgroundColor(resources.getColor(R.color.as_theme_white))
        }
    }

    /**
     * 是否设置布局与状态栏之间的paddingTop,默认值为true
     * @return Boolean
     */
    open fun isSetPaddingTop(): Boolean = true

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.finishActivity(this)
    }

}
