package com.xinay.sponsorme.repository

import com.xinay.sponsorme.api.ServiceApi
import com.xinay.sponsorme.api.UpdateProblem
import com.xinay.sponsorme.presentation.DataRepository
import com.xinay.sponsorme.storage.Settings
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import kotlin.properties.Delegates.observable
import kotlin.properties.ReadWriteProperty

class SponsorMeDataRepository(
    endPoint: String,
    uid: String,
    private val settings: Settings
) : DataRepository {
    private val api = ServiceApi(endPoint)

    override var userId: String? by bindToPreferencesByKey("userIdKey", String.serializer())

    override var privacyPolicyAccepted: Boolean
        get() = settings.getBoolean("privacyPolicyAcceptedKey", false)
        set(value) {
            settings.putBoolean("privacyPolicyAcceptedKey", value)
        }

    private var loggedIn: Boolean = false

    init {
        if (userId == null) userId = uid
    }

    override var onRefreshListeners: List<() -> Unit> = emptyList()

    override suspend fun update() {
        val state = try {
            if (!loggedIn) {
                api.createUser(userId!!)
                loggedIn = true
            }
            //api.getAll(userId)
            api.createUser("anonymous")
        } catch (cause: Throwable) {
            throw UpdateProblem()
        }

        callRefreshListeners()
    }

    override fun acceptPrivacyPolicy() {
        privacyPolicyAccepted = true
    }

    private fun callRefreshListeners() {
        onRefreshListeners.forEach { it() }
    }

    /*
     * Local storage
     */

    private inline fun <reified T : Any> read(key: String, elementSerializer: KSerializer<T>) =
        settings
            .getString(key, "")
            .takeUnless { it.isBlank() }
            ?.let {
                try {
                    Json.parse(elementSerializer, it)
                } catch (_: Throwable) {
                    null
                }
            }

    private inline fun <reified T : Any> write(
        key: String,
        obj: T?,
        elementSerializer: KSerializer<T>
    ) {
        settings.putString(key, if (obj == null) "" else Json.stringify(elementSerializer, obj))
    }

    private inline fun <reified T : Any> bindToPreferencesByKey(
        key: String,
        elementSerializer: KSerializer<T>
    ): ReadWriteProperty<Any?, T?> = observable(read(key, elementSerializer)) { _, _, new ->
        write(key, new, elementSerializer)
    }
}