package com.asijack.common.error

import com.bumptech.glide.load.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.text.ParseException

/**
 * 错误信息处理
 * @author xinzhengjie
 * @date 2021/5/8 13:59
 */
object ErrorProcess {
    fun processThrowable(throwable: Throwable):ErrorInfo {
        val errorInfo = ErrorInfo()
        errorInfo.errorCode = ErrorDefinition.ERROR_UNKNOWN
        when (throwable) {
            is NullPointerException -> {
            }
            is ParseException -> {
            }
            is SocketTimeoutException -> {

            }
            is ConnectException ->{

            }
            is HttpException ->{

            }
            is IllegalArgumentException ->{

            }
        }
        return errorInfo
    }
}