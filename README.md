## NewsApp with MVVM Architecture   
<br>

![git banner for mvvm repo new](https://github.com/sanketpatel29/NewsApp/assets/41280216/cbc363ee-34c5-497f-94f4-6327c9db3107)

<br>
<br>

The repository takes you on an extensive Android development journey using the MVVM architecture, powered by cutting-edge technologies, showcased through various branches.

1. [with_xml_and_dagger](https://github.com/sanketpatel29/NewsApp/tree/with_xml_and_dagger)) : <br> Initial implementation featuring traditional UI with XML layouts, employing Dagger 2 for dependency injection.
2. [with_xml_and_dagger_hilt](https://github.com/sanketpatel29/NewsApp/tree/with_xml_and_dagger_hilt): <br> Evolutionary stage integrating Hilt, transitioning from Dagger 2, while retaining XML layouts.
3. [with_jetpack_compose_and_hilt](https://github.com/sanketpatel29/NewsApp/tree/with_jetpack_compose_and_hilt): <br> Advanced transformation phase, integrating Dagger Hilt alongside the transition from XML layouts to Jetpack Compose, marking a modernized UI approach.
4. [main](https://github.com/sanketpatel29/NewsApp/tree/main) : <br> The final version incorporates Jetpack Compose for the user interface, Dagger Hilt for managing dependencies, and Room database for handling data, making it the most up-to-date iteration of the architecture.
   
<br>
<br>

## Fuled with bellow technologies
- MVVM Architecture
- Kotlin
- Dagger2
- Hilt
- Retrofit
- Coroutines
- Stateflow
- Flows and operators
- View binding
- Jetpack Compose
- Room database
- Worker
- Navigation with Compose

<br>
<br>


## Features Implemeted

- News Fetching
- Top Headlines
- News by Source
- News by Country
- News by Language
- Search News by Text
- Offline Mode
- Automatic Morning News Fetch using Worker
- Notifications after News Fetch
- Pagination for Top Headlines


<br>
<br>

## Application Architecture 

![mvvm_archetecture](https://github.com/sanketpatel29/NewsApp/assets/41280216/dad7a0ff-e328-41ff-a238-9a03cc35354e)

<br>
<br>

## Application's package and file structure is depicted bellow


```
.
`-- com
`-- sanket
`-- newsapp
|-- NewsApplication.kt
|-- apputils
|   |-- Constants.kt
|   |-- IOUtils.kt
|   |-- NetworkHelper.kt
|   |-- NetworkHelperImpl.kt
|   |-- TimeUtil.kt
|   |-- logger
|   |   |-- AppLogger.kt
|   |   `-- Logger.kt
|   `-- typealias.kt
|-- data
|   |-- api
|   |   |-- HeaderInterceptor.kt
|   |   `-- NetworkService.kt
|   |-- local
|   |   |-- AppDatabase.kt
|   |   |-- AppDatabaseService.kt
|   |   |-- DatabaseService.kt
|   |   |-- dao
|   |   |   `-- NewsArticlesDao.kt
|   |   `-- entity
|   |       |-- Article.kt
|   |       `-- Source.kt
|   |-- model
|   |   |-- ApiArticle.kt
|   |   |-- ApiSource.kt
|   |   |-- Country.kt
|   |   |-- Language.kt
|   |   |-- NewsSourcesResponse.kt
|   |   `-- TopHeadlineResponse.kt
|   `-- repository
|       |-- CountriesRepository.kt
|       |-- LanguagesRepository.kt
|       |-- NewsSourceRepository.kt
|       |-- TopHeadlinePagingSource.kt
|       `-- TopHeadlineRepository.kt
|-- di
|   |-- module
|   |   |-- ActivityModule.kt
|   |   `-- ApplicationModule.kt
|   `-- qualifier.kt
|-- notifications
|   `-- NotificationHelper.kt
|-- ui
|   |-- base
|   |   |-- BaseActivity.kt
|   |   |-- CommonUi.kt
|   |   |-- NewsNavHost.kt
|   |   `-- UiState.kt
|   |-- countries
|   |   |-- CountriesScreen.kt
|   |   `-- CountriesViewModel.kt
|   |-- home
|   |   |-- HomeActivity.kt
|   |   `-- HomeScreen.kt
|   |-- language
|   |   |-- LanguageViewModel.kt
|   |   `-- LanguagesScreen.kt
|   |-- newssource
|   |   |-- NewsSourceViewModel.kt
|   |   `-- SourcesScreen.kt
|   |-- offlinearticle
|   |   |-- OfflineArticleScreen.kt
|   |   `-- OfflineArticleViewModel.kt
|   |-- pagination
|   |   |-- PaginationTopHeadlineScreen.kt
|   |   `-- PaginationTopHeadlineViewModel.kt
|   |-- search
|   |   |-- SaerchScreen.kt
|   |   `-- SearchViewModel.kt
|   |-- theme
|   |   |-- Color.kt
|   |   |-- Theme.kt
|   |   `-- Type.kt
|   `-- topheadline
|       |-- TopHeadLineScreen.kt
|       `-- TopHeadlineViewModel.kt
`-- worker
`-- NewsWorker.kt
```

<br>
<br>

## Screenshots

![image](https://github.com/sanketpatel29/NewsApp/assets/41280216/008582c1-ac87-4388-a785-3c3a36a3a432)

<br>

## 🚀 About Me

Hello! <br>

I am Sanket Patel, a Lead Android Developer with a passion for coding and technology. I specialize in crafting innovative solutions and thrive on challenges. During the day, I'm fully immersed in app development, and after hours, I stay up-to-date with the latest tech trends. If you have any questions or need assistance with anything Android-related, let's connect and dive into all things software development together!
- [LinkedIn](https://www.linkedin.com/in/sanket-patel-56164734/)
- [GitHub](https://github.com/sanketpatel29)
