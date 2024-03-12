package com.sanket.newsapp.apputils

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

object Constants {

    const val BASE_URL = "https://newsapi.org/v2/"
    const val COUNTRY = "us"

    const val FILE_COUNTRIES = "countries.json"
    const val FILE_LANGUAGES = "languages.json"

    sealed class NewsType : Parcelable {
        @Parcelize
        data class COUNTRY(var countryCode: String, var type: String = "COUNTRY") : NewsType()

        @Parcelize
        data class SOURCE(var sounrceId: String, var type: String = "SOURCE") : NewsType()

        @Parcelize
        data class LANGUAGE(var languageIds: String, var type: String = "LANGUAGE") : NewsType()
    }

    object NewsBy {
        object IntentParam {
            object Key {
                const val NEWS_TYPE = "newsType"
                const val NEWS_TYPE_ID = "newsTypeId"
            }

            object Value {
                const val COUNTRY = "COUNTRY"
                const val SOURCE = "SOURCE"
                const val LANGUAGE = "LANGUAGE"
            }
        }
    }

    val COUNTRIES_SUPPORTED_BY_NEWS_API = arrayListOf(
        "ae",
        "ar",
        "at",
        "au",
        "be",
        "bg",
        "br",
        "ca",
        "ch",
        "cn",
        "co",
        "cu",
        "cz",
        "de",
        "eg",
        "fr",
        "gb",
        "gr",
        "hk",
        "hu",
        "id",
        "ie",
        "il",
        "in",
        "it",
        "jp",
        "kr",
        "lt",
        "lv",
        "ma",
        "mx",
        "my",
        "ng",
        "nl",
        "no",
        "nz",
        "ph",
        "pl",
        "pt",
        "ro",
        "rs",
        "ru",
        "sa",
        "se",
        "sg",
        "si",
        "sk",
        "th",
        "tr",
        "tw",
        "ua",
        "us",
        "ve",
        "za"
    )

    interface ApiHeaders {
        interface Key {
            companion object {
                const val API_KEY = "X-Api-Key"
            }
        }

        interface Value {
            companion object {
                const val API_KEY = "c5fb8da0aa4c4e818e1db97a06162d7d"
            }
        }

    }
}