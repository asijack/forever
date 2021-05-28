package com.asijack.common.mvvm

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.asijack.common.ui.activity.BaseActivity

/**
 * @author xinzhengjie
 * @date 2021/5/8 14:14
 */
abstract class BindingActivity<T : ViewBinding> : BaseActivity() {
    protected var binding: T? = null
    protected fun requireBinding(): T = checkNotNull(binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onPreCreateBinding(savedInstanceState)
        onCreateBinding(savedInstanceState).also {
            binding = it
            setContentView(it.root)
            it.onBindingCreated(savedInstanceState)
        }
    }

    protected open fun onPreCreateBinding(savedInstanceState: Bundle?){
    }

    protected abstract fun onCreateBinding(savedInstanceState: Bundle?): T

    protected open fun T.onBindingCreated(savedInstanceState: Bundle?) {
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}