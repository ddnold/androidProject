# Overgrowth
### The application to help you manage your plants

**Created by:**
Dustin Nold and Wesley Millett (Team 10)

### Supported devices
Overgrowth will run on approximately 90.6% of Samsung devices. If your device uses API 26: Android 8.0 (Oreo) or newer API Overgrowth should function on your device.

### Database Usage
Overgrowth uses a Firebase Real-time Database in order to provide the user with over 1000 different plants to view or manage. 

The database may contain the following information about the plants when applicable:
- Common Name(s)
- Scientific/Botanical Name(s)
- An image
- The family
- The preferred soil PH level
- Other information regarding the soil
- The time the plant blooms
- The hardiness zones
- The type
- Preferred sun exposure
- The native area
- The size
- Best practices for watering
- The bloom color(s)
- The growing time

When a user adds a new plant it will be stored in a local SQLite database that is kept on the device.


**Because Overgrowth doesn't store your plants remotely, you will have to recreate your personal list upon uninstallation of the application**

### Application Usage notes

- The browse plants fragment is initially sorted by common name.
- When searching for a plant you can only search by the common name.
