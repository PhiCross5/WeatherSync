# WeatherSync
Add weather reporting and forecasting capabilities to your application.

## Project status 
A very basic weather report command is now working. Those eager to have anything in working state can grab release 1.

## requirements
This library needs java platform 11; the executable from release 1 needs a working internet connection and an OpenWeather API key.

## TODO
- Unit tests
- 1st wave of Refactoring 

## About the API's internals and the three-party model
As of this early stage, WeatherSync is split into 3 main segments: the __provider__, the __consumer__ and the __log__.
### Provider
this class is responsible for acquiring weather-related data from an external agent and generating a properly formatted __log__ upon request of the __consumer__.
### Log
this class contains whatever information the __consumer__ needs regarding weather (ex: temperature, cloudiness, pressure, humidity, UV index, dew point...)
### Consumer
this class uses whatever information needed from the __log__ it requests from the __provider__ in order to perform whatever duty it is designed to do.
A wide variety of tasks may be carried on by the consumer, with outcomes affected by the information it pulls from the __log__.

Each of these three classes may be further broken down into separate segments if the particular implementation turns out large enough.
such constructs may include things like:
- a cache subsystem for a JSON-API-based provider to optimize its network usage.
- a 4rth party __arbiter__ that checks wether the pieces of weather information required by the __consumer__ are available from a set of one or more __providers__.
- a dedicated __Parameter__ class encapsulating each parameter in the __log__, with methods to identify wether the field is available, what unit it is in and what is its value, be it numerical, boolean or an enumeration.
