package com.example.recipesapp.util

import androidx.annotation.StringRes
import com.example.recipesapp.R

/*enum class AppError(val code: Int, val message: String) {
    BadRequest(400, "Неверный запрос. Попробуйте изменить параметры."),     // Обработка ошибки 400 (Bad Request)
    Unauthorized(401, "Вы не авторизованы. Пожалуйста, войдите в аккаунт."),// Обработка ошибки 401 (Unauthorized)
    PaymentRequired(402, "Исчерпан лимит запросов. Повторите позже."),      // Проблема с квотами (quotas)
    Forbidden(403, "Доступ запрещён."),
    NotFound(404, "Ресурс не найден."),                                     // Обработка ошибки 404 (Not Found - Ресурс не найден)
    InternalServer(500, "Ошибка сервера. Повторите попытку позже."),        // Обработка ошибки 500 (Internal Server Error - Внутренняя ошибка сервера)
    Unknown(-1, "Неизвестная ошибка.")
}*/

enum class AppError(val code: Int, @StringRes val messageRes: Int) {
    BadRequest(400, R.string.bad_request),
    Unauthorized(401, R.string.unauthorized),
    PaymentRequired(402, R.string.payment_required),
    Forbidden(403, R.string.forbidden),
    NotFound(404, R.string.not_found),
    InternalServer(500, R.string.internal_server),
    Unknown(-1, R.string.unknown)
}