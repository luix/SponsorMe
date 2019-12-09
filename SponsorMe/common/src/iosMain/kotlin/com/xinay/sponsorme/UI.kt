package com.xinay.sponsorme

import kotlinx.coroutines.*
import platform.darwin.*
import kotlin.coroutines.CoroutineContext

class UI : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        val queue = dispatch_get_main_queue()
        dispatch_async(queue) {
            block.run()
        }
    }
}