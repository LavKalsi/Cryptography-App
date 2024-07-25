# Cryptography App

A Kotlin-based Android Cryptography App that uses the AES algorithm for text encryption and decryption. The app generates a key during encryption and can store the history of encrypted and decrypted texts.

## Features

- Text encryption using AES algorithm
- Key generation during encryption
- Text decryption using the generated key
- History of encrypted and decrypted texts
- Works offline
- Uses `javax.crypto` library

## Screenshots

<table>
  <tr>
    <td align="center">Home Screen</td>
    <td align="center">Encryption</td>
    <td align="center">Decryption</td>
    <td align="center">History</td>
    <td align="center">Icon</td>
  </tr>
  <tr>
    <td><img src="https://github.com/LavKalsi/CryptographyApp/blob/master/Screenshots/Home.jpg" width="150" height="334"/></td>
    <td><img src="https://github.com/LavKalsi/CryptographyApp/blob/master/Screenshots/Encrypt.jpg" width="150" height="334"/></td>
    <td><img src="https://github.com/LavKalsi/CryptographyApp/blob/master/Screenshots/Decrypt.jpg" width="150" height="334"/></td>
    <td><img src="https://github.com/LavKalsi/CryptographyApp/blob/master/Screenshots/History.jpg" width="150" height="334"/></td>
    <td><img src="https://github.com/LavKalsi/CryptographyApp/blob/master/Screenshots/Icon.png" width="120" height="120"/></td>
  </tr>
</table>

## Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/yourusername/cryptography-app.git
    cd cryptography-app
    ```

2. Open the project in Android Studio.

3. Build and run the project on an Android device or emulator.

    OR

    Go to releases and download the apk file.

## Adding APK to GitHub

To add the APK file of your app to GitHub, follow these steps:

1. Build your app in Android Studio to generate the APK file. You can find the APK file in the `app/build/outputs/apk/` directory.

2. Create a new release on GitHub:
    - Go to the "Releases" section of your GitHub repository.
    - Click on "Draft a new release".
    - Fill in the tag version (e.g., `v1.0`) and release title (e.g., `Initial Release`).
    - Add a description for your release.
    - Attach the APK file by dragging and dropping it into the release description area or by using the "Attach binaries by dropping them here or selecting them" button.
    - Click on "Publish release" to make the release live.

## Usage

1. Open the app on your Android device.
   
2. To encrypt text:
   - Go to the Encrypt section.
   - Enter the text you want to encrypt.
   - Press the Encrypt button to generate the encrypted text and key.
   
3. To decrypt text:
   - Go to the Decrypt section.
   - Enter the encrypted text and the key.
   - Press the Decrypt button to retrieve the original text.
   
4. To view history:
   - Go to the History section to see the saved history of encrypted and decrypted texts.

## Built With

- [Kotlin](https://kotlinlang.org/) - The programming language used.
- [javax.crypto](https://docs.oracle.com/javase/8/docs/api/javax/crypto/package-summary.html) - For AES encryption and decryption.

## Contributing

Feel free to contribute to this project by submitting a pull request. Please make sure to follow the standard GitHub flow when contributing.

## Acknowledgments

- Thanks to the creators of the `javax.crypto` library.
- Inspiration and code snippets from various online resources.

## Author
Lav Kalsi
