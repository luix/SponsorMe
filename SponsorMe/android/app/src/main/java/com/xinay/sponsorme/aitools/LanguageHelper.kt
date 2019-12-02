package com.xinay.sponsorme.aitools

import android.util.Log
import androidx.annotation.Nullable
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage
import com.google.firebase.ml.naturallanguage.languageid.FirebaseLanguageIdentification

const val TAG = "LanguageHelper"

// https://firebase.google.com/docs/ml-kit/android/identify-languages
object LanguageHelper {
    val languageIdentifier: FirebaseLanguageIdentification = FirebaseNaturalLanguage.getInstance().getLanguageIdentification()
    languageIdentifier.identifyLanguage(text)
    .addOnSuccessListener(
    object : OnSuccessListener<String?>() {
        fun onSuccess(@Nullable languageCode: String) {
            if (languageCode !== "und") {
                Log.i(com.xinay.sponsorme.aitools.TAG, "Language: $languageCode")
            } else {
                Log.i(com.xinay.sponsorme.aitools.TAG, "Can't identify language.")
            }
        }
    })
    .addOnFailureListener(
    object : OnFailureListener() {
        fun onFailure(e: Exception) { // Model couldn’t be loaded or other internal error.
            // ...
        }
    })
}