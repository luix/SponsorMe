package com.xinay.sponsorme.presentation

interface NavigationManager {
    fun showSessionList()
    fun showSessionDetails(sessionId: String)
    fun showPrivacyPolicyDialog()
}