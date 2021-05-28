package com.asijack.provider.router.server

import com.asijack.common.ui.fragment.BaseFragment


/**
 * 定义Home模块提供的服务
 */
interface HomeService {

    fun getHomeFragment(): BaseFragment

}