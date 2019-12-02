package org.jetbrains.sponsorme.presentation

class PrivacyPolicyPresenter(
    private val repository: DataRepository
) {
    fun onAcceptPrivacyPolicyClicked() {
        repository.privacyPolicyAccepted = true
    }
}