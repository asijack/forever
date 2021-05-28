package com.asijack.forever.ui

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.asijack.common.mvvm.BindingActivity
import com.asijack.common.ui.fragment.BaseFragment
import com.asijack.forever.R
import com.asijack.forever.databinding.AppActivityMainBinding
import com.asijack.provider.router.component.Router
import com.asijack.provider.router.server.HomeService
import com.asijack.provider.router.server.MeService
import com.asijack.provider.router.server.VideoService
import kotlinx.android.synthetic.main.app_activity_main.*
import java.util.*

/**
 * @author xinzhengjie
 * @date 2021/3/20 15:50
 */
class MainActivity : BindingActivity<AppActivityMainBinding>() {
    private var mHomeFragment: BaseFragment? = null
    private var mMeFragment: BaseFragment? = null
    private var mVideoFragment: BaseFragment? = null
    private var clickTime: Long = 0
    private val mStack = Stack<BaseFragment>()

    override fun isSetPaddingTop(): Boolean = false

    companion object {
        private const val STATE_BAR_POSITION = 1
    }

     private fun setListener() {
         bottomBar.setOnTabSelectListener {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN) //显示状态栏
            when (it) {
                R.id.tab_home -> {//首页
                    changeFragment(0)
                }
//                R.id.tab_music -> {//音乐
//
//                    changeFragment(1)
//                }
//                R.id.tab_play -> {//直播
//                    changeFragment(2)
//                }
//                R.id.tab_mine -> {//相声
//                    changeFragment(3)
//                }
            }
        }
    }

    /** 初始化fragment*/
    private fun initFragment() {
        val bt = supportFragmentManager.beginTransaction()
        val router = Router.instance
        val homeService = router.getService(HomeService::class.simpleName)
        val videoService = router.getService(VideoService::class.simpleName)
        val meService = router.getService(MeService::class.simpleName)
        if (homeService != null) {
            mHomeFragment = (homeService as HomeService).getHomeFragment()
            if (mHomeFragment != null) {
                bt.add(R.id.rootLay, mHomeFragment!!)
                mStack.add(mHomeFragment)
            }
        }
        if (homeService != null) {
            mVideoFragment = (videoService as VideoService).getVideoFragment()
            if (mVideoFragment != null) {
                bt.add(R.id.rootLay, mVideoFragment!!)
                mStack.add(mVideoFragment)
            }
        }
        if (homeService != null) {
            mMeFragment = (meService as MeService).getMeFragment()
            if (mMeFragment != null) {
                bt.add(R.id.rootLay, mMeFragment!!)
                mStack.add(mMeFragment)
            }
        }
        if (mStack.size != 0) {
            bt.commit()
        }
    }

    /**
     * 切换fragment显示
     * @param position Int
     */
    private fun changeFragment(position: Int) {
        if (position >= mStack.size) {
            return
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            window.decorView.systemUiVisibility = if (position == STATE_BAR_POSITION) {
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            } else {
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }
        val bt = supportFragmentManager.beginTransaction()
        mStack.forEach {
            bt.hide(it)
        }
        bt.show(mStack[position])
        bt.commit()
    }

    /**
     * 连续按下两次退出程序
     */
    private fun exit() {
        if ((System.currentTimeMillis() - clickTime) > 2000) {
            Toast.makeText(this, getString(R.string.common_exit_app), Toast.LENGTH_SHORT).show()
            clickTime = System.currentTimeMillis()
        } else {
            this.finish()
        }
    }

    override fun onBackPressed() {
        val isBackVideo = mVideoFragment?.onKeyBackPressed() ?: false
        if (!isBackVideo) {
            exit()
        }
    }

    override fun onCreateBinding(savedInstanceState: Bundle?): AppActivityMainBinding {
        return AppActivityMainBinding.inflate(layoutInflater)
    }

    override fun AppActivityMainBinding.onBindingCreated(savedInstanceState: Bundle?) {
        initFragment()
        setListener()
    }
}

