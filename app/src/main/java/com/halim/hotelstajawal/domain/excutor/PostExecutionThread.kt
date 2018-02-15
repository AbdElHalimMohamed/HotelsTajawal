package com.halim.hotelstajawal.domain.excutor

import io.reactivex.Scheduler

interface PostExecutionThread {
    val scheduler: Scheduler
}
