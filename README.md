# TrackerApp (JourneyToSouth) Application

TrackerApp(JourneyToSouth)  is a Kotlin-based Android application that demonstrates the use of Lazy loading in Compose, showcasing a journey with progress tracking between multiple stations.

## Features

- **Lazy Loading:** Utilizes Compose's LazyColumn to efficiently load and display a list of stations.
- **Dynamic Progress Bar:** Visualizes the progress of the journey with a customizable linear progress bar.
- **Unit Conversion:** Allows users to switch between kilometers and miles for distance measurements Using Toggle button.

## Getting Started

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/JourneyToSouth.git
## Screenshots

<img src="https://github.com/Yogender21505/TrackerApp/assets/104339650/823a99ee-4143-4fbd-9958-7fa569e6767c" width="200">
<img src="https://github.com/Yogender21505/TrackerApp/assets/104339650/208dd3e4-74ba-46bd-9e40-54fc7a76d1a7" width="200">
<img src="https://github.com/Yogender21505/TrackerApp/assets/104339650/770238bf-bba1-4e46-af61-3e59f5b2bf02" width="200">
<img src="https://github.com/Yogender21505/TrackerApp/assets/104339650/75cc8be4-13b3-4f7d-9dd5-1f77f472799e" width="200">
<img src="https://github.com/Yogender21505/TrackerApp/assets/104339650/bdf6c06d-f53e-48f9-bcb4-21cf34bf6046" width="200">
<img src="https://github.com/Yogender21505/TrackerApp/assets/104339650/29e3d026-d9e1-4d8c-bc0c-274acfca9606" width="200">


## Usage

1. Launch the app on an Android emulator or device.
2. On the main screen, click on "Normal List" or "Lazy List" to navigate to the respective screens.
3. On each list screen, use the "NextStop" button to progress through the journey, and the "toggleButton" button to switch distance units.
4. Explore the dynamic progress bar and details for each station.

## Dependencies

- [Compose](https://developer.android.com/jetpack/compose): Modern Android UI toolkit.
- [AndroidX Navigation](https://developer.android.com/guide/navigation): Navigation component for navigating between screens.

## Code Structure

- **MainActivity:** The entry point of the application with navigation using `NavHost`.
- **MainPage:** Composable function for the main screen with navigation buttons.
- **NormalListScreen:** Composable function for the screen demonstrating a normal list with a dynamic progress bar.
- **LazyListScreen:** Composable function for the screen demonstrating lazy loading with a dynamic progress bar.
- **List_of_TextBox:** Composable function to display individual station details within a list.
- **progressBar:** Composable function to display the linear progress bar.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
