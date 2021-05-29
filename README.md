# GoJek Parking-lot problem solution

Project Requirements
* JDK 1.8
* Maven 3
* Junit 4

The project can be run as follows in one of the two ways :

./parking_lot.sh <input_filepath>
The inputs commands are expected and taken from the file specified
./parking_lot.sh This will start the program in interactive mode.

#Build Instructions

Run the following command -

mvn clean install
#Running the project

From the project directory, run this command

`$ java -jar target/gojek-1.0.jar (Interactive command-line mode)`

`$ java -jar target/gojek-1.0.jar <inputfile> (File input)`

#Implementation Details
Due to the restriction of using 3rd party dependencies, I've used Java Data Structures for storage, which means data is being stored in RAM.
Using some In memory RDBMS like H2 DB makes more sense.

The backbone of the solution are 4 DAOs.
* `ParkingSlotAvailabilityDaoImpl` - This DAO keeps track of available slots which are vacant and returns the nearest slot available when a parking request is received.
It uses MinHeap (hence achieving the time complexity O(log N) instead of brute-force O(N)), to get manage the empty/available slots.

* `ParkingSlotColorInfoDaoImpl` - This DAO is useful for answering the color based queries (find the slots or registration numbers having color X) with constant time (time complexity O(1)).
It uses 2 HashMaps, first to map a color with a set of Slot Numbers and second to map color with a set of Registration numbers.

* `ParkingSlotPositionDaoImpl` - It represents the parking lot as an ArrayList with nth bucket of ArrayList representing the (n+1)th slot.
It is useful for answering the queries like 'Get status of parking lot'

* `ParkingSlotRegNumberInfoDaoImpl` - This is useful for answering the queries like 'get slot number for vehicle with Registration number x'.
It keeps a HashMap as mapping of Registration number with Slot number.

#Design Patterns
* **Singleton Pattern:** I've used Singleton pattern for all DAOs and Service layer. Since, in an actual production environment, the state is expected to be mainly maintained in DB.
So, keeping DAOs and Service(s) as singleton classes makes sense.<br>
_Caveat:_ Since in our code, DAOs are actually acting as DB layer (keeping data in main memory), keeping them singleton for unit tests made tests inter-dependent.
i.e. behaviour of one test will depend on the sequence in which tests are called. So, for unit tests I instantiate DAOs every time I run a test and throw the instance afterwards.
This is the reason visibility of constructors of DAOs has been kept package-private.
* **Inversion of Control:** All DAOs and service(s) are accessed using interfaces and hence are oblivious to implementation details.
 
