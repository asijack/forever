package com.gfd.common.mvp.view


/**
 * @description：
 */
interface BaseView {

    /**
     * 显示Loading
     */
    fun showLoading()

    /**
     * 隐藏Loading
     */
    fun showContent()

    /**
     * 访问错误
     */
    fun error(){}

    /**
     * 空数据
     */
    fun empty(){}
}