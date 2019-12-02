package com.xinay.sponsorme.aitools

import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions
import com.google.firebase.ml.common.modeldownload.FirebaseModelManager
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateRemoteModel
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions
import com.xinay.sponsorme.aitools.TranslatorHelper.languageTranslator

// https://firebase.google.com/docs/ml-kit/android/translate-text
object TranslatorHelper {
    // Create an English-Spanish translator:
    val options = FirebaseTranslatorOptions.Builder()
        .setSourceLanguage(FirebaseTranslateLanguage.EN)
        .setTargetLanguage(FirebaseTranslateLanguage.ES)
        .build()
    val languageTranslator = FirebaseNaturalLanguage.getInstance().getTranslator(options)
}

fun verifyModelReady() {
    languageTranslator.downloadModelIfNeeded()
        .addOnSuccessListener {
            // Model downloaded successfully. Okay to start translating.
            // (Set a flag, unhide the translation UI, etc.)
        }
        .addOnFailureListener { exception ->
            // Model couldn’t be downloaded or other internal error.
            // ...
        }
}

fun translate() {
    languageTranslator.translate(text)
        .addOnSuccessListener { translatedText ->
            // Translation successful.
        }
        .addOnFailureListener { exception ->
            // Error.
            // ...
        }
}

fun downloadTranslationModels() {
    val modelManager = FirebaseModelManager.getInstance()

// Get translation models stored on the device.
    modelManager.getDownloadedModels(FirebaseTranslateRemoteModel::class.java)
        .addOnSuccessListener { models ->
            // ...
        }
        .addOnFailureListener {
            // Error.
        }

// Delete the German model if it's on the device.
    val deModel = FirebaseTranslateRemoteModel.Builder(FirebaseTranslateLanguage.DE).build()
    modelManager.deleteDownloadedModel(deModel)
        .addOnSuccessListener {
            // Model deleted.
        }
        .addOnFailureListener {
            // Error.
        }

// Download the French model.
    val frModel = FirebaseTranslateRemoteModel.Builder(FirebaseTranslateLanguage.FR).build()
    val conditions = FirebaseModelDownloadConditions.Builder()
        .requireWifi()
        .build()
    modelManager.download(frModel, conditions)
        .addOnSuccessListener {
            // Model downloaded.
        }
        .addOnFailureListener {
            // Error.
        }
}