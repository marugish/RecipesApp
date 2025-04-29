package com.example.recipesapp.util

enum class AppError(val code: Int, val message: String) {
    BadRequest(400, "Неверный запрос. Попробуйте изменить параметры."),     // Обработка ошибки 400 (Bad Request)
    Unauthorized(401, "Вы не авторизованы. Пожалуйста, войдите в аккаунт."),// Обработка ошибки 401 (Unauthorized)
    PaymentRequired(402, "Исчерпан лимит запросов. Повторите позже."),      // Проблема с квотами (quotas)
    Forbidden(403, "Доступ запрещён."),
    NotFound(404, "Ресурс не найден."),                                     // Обработка ошибки 404 (Not Found - Ресурс не найден)
    InternalServer(500, "Ошибка сервера. Повторите попытку позже."),        // Обработка ошибки 500 (Internal Server Error - Внутренняя ошибка сервера)
    Unknown(-1, "Неизвестная ошибка.")
}