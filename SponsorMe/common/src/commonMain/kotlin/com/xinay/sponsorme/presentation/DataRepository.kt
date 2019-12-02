package com.xinay.sponsorme.presentation

interface DataRepository {
    var onRefreshListeners: List<() -> Unit>
    var privacyPolicyAccepted: Boolean
    var userId: String?

    suspend fun update()
    fun acceptPrivacyPolicy()
}
