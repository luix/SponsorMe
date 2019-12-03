package com.xinay.sponsorme.aitools

import android.util.Log
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage
import com.google.firebase.ml.naturallanguage.languageid.FirebaseLanguageIdentification
import java.lang.Exception

const val TAG = "LanguageHelper"

// https://firebase.google.com/docs/ml-kit/android/identify-languages
object LanguageHelper {

}

fun onLanguage(text: String)  {
    val languageIdentifier: FirebaseLanguageIdentification = FirebaseNaturalLanguage.getInstance().languageIdentification
    languageIdentifier.identifyLanguage(text)
        .addOnSuccessListener(
            object : OnSuccessListener<String> {
                override fun onSuccess(languageCode: String?) {
                    if (languageCode !== "und") {
                        Log.i(com.xinay.sponsorme.aitools.TAG, "Language: $languageCode")
                    } else {
                        Log.i(com.xinay.sponsorme.aitools.TAG, "Can't identify language.")
                    }
                }
            })
        .addOnFailureListener(
            object : OnFailureListener() {
                override fun onFailure(e: Exception) { // Model could not be loaded or other internal error.
                    Log.e(com.xinay.sponsorme.aitools.TAG, "Exception: $e")
                }
            })
}