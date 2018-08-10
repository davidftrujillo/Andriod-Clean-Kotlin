package com.xmoba.data.model.response

import com.xmoba.data.model.user.UserEntity

/**
 * Created by david on 6/8/18.
 */
data class ResponseData(val results: List<UserEntity>, val info: ResponseInfo)