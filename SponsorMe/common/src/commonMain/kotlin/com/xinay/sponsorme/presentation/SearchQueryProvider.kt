package org.jetbrains.sponsorme.presentation


interface SearchQueryProvider {
    val searchQuery: String
    fun addOnQueryChangedListener(listener: (String) -> Unit)
}