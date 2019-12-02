package com.xinay.sponsorme.presentation

class MainPresenter(
    private val navigationManager: NavigationManager,
    private val repository: DataRepository
) {
    fun onCreate() {
        if (!repository.privacyPolicyAccepted) {
            navigationManager.showPrivacyPolicyDialog()
        }
    }
}