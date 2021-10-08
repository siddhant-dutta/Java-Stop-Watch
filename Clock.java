import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
// import java.text.SimpleDateFormat;


interface Stopwatch {
    void start(); // this method starts counting time

    void stop(); // returns the time interval from the start time to end time

    void resetTime(); // sets the stopwatch to 0

    void getCurrentTime();
}

interface AlarmClock {
    void setAlarm(); // set alarm (can set multiple alarms)

    void getAlarm(); // returns the time from now to the closest alarm

    void getAllAlarms(); // returns all the alarms which are sorted based on time in ascending order

    void getCurrentTime();
}

class Clock implements Stopwatch, AlarmClock {
    Scanner sc = new Scanner(System.in);
    boolean stopwatch = false;
    Instant start;
    Instant end;
    SortedSet<String> alarms = new TreeSet<String>();

// STOPWATCH FEATURES
    public void getCurrentTime() {
        System.out.println(java.time.LocalTime.now() );
    }

    public void start() {
        if (this.stopwatch == true) {
            System.out.println("Your stopwatch is already running!!");
            return;
        }
        this.stopwatch = true;
        System.out.println("Stopwatch Started");
        this.start = Instant.now();
    }

    public void stop() {
        if (this.stopwatch) {
            this.end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            System.out.println("Time taken: " + timeElapsed.toSeconds() + " seconds");
            this.stopwatch = false;
        } else {
            System.out.println("Please Start the Stop watch first");
        }
    }

    public void resetTime() {
        System.out.println("Stopwatch has been reset !!!");
        this.stopwatch = false;
    }

// ALARMCLOCK FEATURES
    public void setAlarm() {
        String time;
        System.out.println("Set an alarm (HH:mm) : ");
        time = sc.nextLine();
        this.alarms.add(time);
    }

    public void getAlarm() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        String formatted_time = LocalTime.now().format(dtf);
        boolean is_neg_present = false; 
        // Iterator alarm = alarms.iterator();
        for(String alarm : this.alarms)
        {
            if(formatted_time.compareTo(alarm) < 0){
                System.out.println(alarm );
                is_neg_present=true; 
                break; 
            }
        }
        if(is_neg_present == false){
            System.out.println("You don't have any pending alarms !!! ");
        }
    }
    
    public void getAllAlarms() {
        System.out.println(this.alarms);
    }
}

class UI {
    Clock clock = new Clock();
    
    void clockHomeScreen() {
        System.out.println("\n");
        Scanner sc = new Scanner(System.in);
        int choice;
        System.out.println("Choose the required function : ");
        System.out.println("0) Current Time ");
        System.out.println("1) Stopwatch");
        System.out.println("2) Alarmclock");
        System.out.println("3) Exit");
        
        choice = sc.nextInt();
        
        switch (choice) {
            case 0: {
                this.clock.getCurrentTime();
                clockHomeScreen();
            }
            case 1: {
                stopWatchScreen();
            }
            
            case 2: {
                alarmClockScreen();
            }
            
            case 3: {
                System.out.println("Thanks for using our app :) ");
                System.exit(0);
            }
            
            default: {
                System.out.println("You entered the wrong option please select again .");
                clockHomeScreen();
            }
        }
        sc.close();
    }
    
    void stopWatchScreen() {
        System.out.println("\n");
        Scanner sc = new Scanner(System.in);
        int choice;
        System.out.println("Select the operation you want to perform : ");
        System.out.println("0) Current Time ");
        System.out.println("1) Start the stopwatch");
        System.out.println("2) Stop the stopwatch");
        System.out.println("3) Reset the stopwatch");
        System.out.println("4) Exit");
        
        choice = sc.nextInt();
        switch (choice) {
            case 0: {
                this.clock.getCurrentTime();
                stopWatchScreen();
            }
            case 1: {
                this.clock.start();
                stopWatchScreen();
            }
            case 2: {
                this.clock.stop();
                stopWatchScreen();
            }
            case 3: {
                this.clock.resetTime();
                stopWatchScreen();
            }
            case 4: {
                clockHomeScreen();
            }
            default: {
                System.out.println("You entered the wrong option please select again .");
                stopWatchScreen();
            }
        }
        sc.close();
    }
    
    void alarmClockScreen() {
        System.out.println("\n");
        Scanner sc = new Scanner(System.in);
        int choice;

        System.out.println("Select the operation you want to perform : ");
        System.out.println("0) Current Time ");
        System.out.println("1) Set an alarm");
        System.out.println("2) Upcoming alarm ");
        System.out.println("3) Display all alarms");
        System.out.println("4) Exit");
        
        choice = sc.nextInt();
        switch (choice) {
            case 0: {
                this.clock.getCurrentTime();
                alarmClockScreen();
            }
            case 1: {
                this.clock.setAlarm();
                alarmClockScreen();
            }
            case 2: {
                this.clock.getAlarm();
                alarmClockScreen();
            }
            case 3: {
                this.clock.getAllAlarms();
                alarmClockScreen();
            }
            case 4: {
                clockHomeScreen();
            }
            default: {
                System.out.println("You entered the wrong option please select again .");
                alarmClockScreen();
            }
        }
        sc.close();
    }
}

class Main {
    public static void main(String[] args) {
        UI CLOCK_APP = new UI();
        CLOCK_APP.clockHomeScreen();  
    }
}



/*
 ******************** PLANNING ********************
 * 
 * 
 * choice : Alarmclock Stopwatch Close the app
 * 
 * 
 * Stopwatch : (Start Reset Stop) --> 1. Do you want to exit ?
 * 
 * 
 * Alarmclock : (1. Set Alarm 2. get alarm 3. get all alarms) HH/mm
 * 
 * 
 */