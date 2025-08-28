package com.ozalp.velorasports.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ozalp.velorasports.data.remote.dto.response.abstracts.VeloraResponse
import retrofit2.Response

inline fun <reified D, R> handleNetworkResponse(
    response: Response<VeloraResponse<D>>,
    crossinline mapData: (D) -> R
): Resource<R> {

    if (!response.isSuccessful) {
        val fallbackMessage = "Sunucu hatası: ${response.code()}"
        val parsedMessage = parseErrorMessage<D>(response) ?: fallbackMessage
        return Resource.Error(parsedMessage)
    }

    val body = response.body() ?: return Resource.Error("Boş response gövdesi")

    if (!body.success) {
        val message = body.message?.takeIf { it.isNotBlank() } ?: "İstek başarısız"
        return Resource.Error(message)
    }

    val data = body.data ?: return Resource.Error(body.message?.takeIf { it.isNotBlank() } ?: "Veri bulunamadı")

    return try {
        Resource.Success(mapData(data))
    } catch (t: Throwable) {
        Resource.Error(t.message ?: "Veri dönüştürme sırasında hata oluştu")
    }
}

inline fun <reified T> parseErrorMessage(response: Response<VeloraResponse<T>>): String? {
    return try {
        val errorBodyString = response.errorBody()?.string() ?: return null
        val type = object : TypeToken<VeloraResponse<T>>() {}.type
        val parsed: VeloraResponse<T>? = Gson().fromJson(errorBodyString, type)
        parsed?.message?.takeIf { it.isNotBlank() }
    } catch (_: Throwable) {
        null
    }
}


