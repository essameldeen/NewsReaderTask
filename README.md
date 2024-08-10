# News-Reader

## Overview

This project is a News Reader app built with Kotlin and Jetpack Compose, following the MVI architecture pattern. The app fetches news articles from a public API, displays them in a list, and allows users to view article details. It also includes search functionality, offline support, bookMark list  and dark mode.

## Features

- Fetch and display news articles.
- View detailed news articles.
- Search for articles by keywords.
- Add Article To Bookmark List 
- Remove Article From Bookmark
- Offline mode using Room database.
- Light and dark mode support.

## Architecture

The app is built using the MVI (Model-View-Intent) architecture:
- **Model**: Manages data from the API and local database.
- **Intent**: Represents user actions or intents that are dispatched to the ViewModel
- **View**: Composable UI functions that react to ViewModel state.

## Libraries Used

- **Jetpack Compose**: For UI.
- **Retrofit**: For network requests.
- **Room**: For local data storage.
- **Coroutines**: For asynchronous operations.
- **Hilt**: For dependency injection.
- **Mockk**: For Testing .

## Setup Instructions

1. **Clone the repository**:
    ```bash
    git clone https://github.com/essameldeen/NewsReaderTask.git
 
    ```

2. **Open in Android Studio**:
    - Open Android Studio.
    - Select `Open an existing project` and choose the project directory.

3. **Configure API Key**:
    - Obtain an API key from NewsAPI.
    - Create a `secrets.properties` file in the root directory.
    - Add your API key:
      ```
      NEWS_API_KEY=your_api_key_here
      ```

4. **Build and Run**:
    - Connect an Android device or start an emulator.
    - Click on `Run` in Android Studio.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request.

