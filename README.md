# Back Button for Wear OS

An Android Wear (Watch) application that adds back button functionality to Wear OS devices by utilizing an accessibility service.

## Overview

This app allows users to remap a hardware button (or use the app directly) to perform a global back action on Wear OS devices. It's particularly useful for devices that may not have a dedicated back button or for users who want to customize their navigation experience.

## Features

- Provides global back navigation on Wear OS devices
- Uses Android Accessibility Service for system-level back actions
- Remaps hardware buttons to perform back navigation
- Simple, one-tap activation
- No persistent UI - runs in the background

## How It Works

1. The app uses an Accessibility Service to perform global back actions
2. When launched, the app checks if the accessibility service is enabled
3. If enabled, it immediately performs a back action and closes
4. If not enabled, it redirects the user to accessibility settings for one-time setup

## Setup

1. Install the app on your Wear OS device
2. Launch the app
3. When prompted, go to the Accessibility settings
4. Enable the "Back Button Service"
5. Return to the app (or press your remapped hardware button)

## Usage

Once the accessibility service is enabled, you can:

- Launch the app directly to perform a back action
- Remap a hardware button to launch this app for quick back navigation

## Technical Details

- Target SDK: 36
- Minimum SDK: 30 (Android 11)
- Built with Kotlin and Jetpack Compose
- Uses `GLOBAL_ACTION_BACK` from AccessibilityService

## Permissions

This app requires the Accessibility Service permission to function, which allows it to perform global actions on the device. The permission is only used for the back navigation feature and no data is collected or transmitted.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.