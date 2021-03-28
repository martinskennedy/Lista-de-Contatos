package com.example.listadecontatos

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
// Classe que representa cada contato da lista
@Parcelize  // Permite o trafego de dados entre as telas
data class Contact (
    var name: String,
    var phone: String,
    var photograph: String
) : Parcelable