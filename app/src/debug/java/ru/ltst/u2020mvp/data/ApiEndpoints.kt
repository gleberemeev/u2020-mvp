package ru.ltst.u2020mvp.data

import ru.ltst.u2020mvp.data.api.ApiModule

enum class ApiEndpoints private constructor(val displayedName: String, val url: String?) {
    PRODUCTION("Production", ApiModule.PRODUCTION_API_URL.toString()),
    MOCK_MODE("Mock Mode", "http://localhost/mock/"),
    CUSTOM("Custom", null);

    override fun toString(): String {
        return displayedName
    }

    companion object {

        fun from(endpoint: String): ApiEndpoints {
            for (value in values()) {
                if (value.url != null && value.url == endpoint) {
                    return value
                }
            }
            return CUSTOM
        }

        fun isMockMode(endpoint: String): Boolean {
            return from(endpoint) == MOCK_MODE
        }
    }
}
