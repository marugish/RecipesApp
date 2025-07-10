package com.example.recipesapp.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.recipesapp.R

enum class AppError(val code: Int, @StringRes val messageRes: Int, @DrawableRes val imageRes: Int) {
    BadRequest(400, R.string.bad_request, R.drawable.ic_error_bad_request),
    Unauthorized(401, R.string.unauthorized, R.drawable.ic_error_unauthorized),
    PaymentRequired(402, R.string.payment_required, R.drawable.ic_error_payment_required),
    Forbidden(403, R.string.forbidden, R.drawable.ic_error_forbidden),
    NotFound(404, R.string.not_found, R.drawable.ic_error_not_found),
    InternalServer(500, R.string.internal_server, R.drawable.ic_error_internal_server),
    Unknown(-1, R.string.unknown, R.drawable.ic_error_unknown2),
    NoInternet(-100, R.string.no_internet, R.drawable.ic_error_no_internet);

    companion object {
        fun fromCode(code: Int): AppError {
            return entries.find { it.code == code } ?: Unknown
        }
    }
}