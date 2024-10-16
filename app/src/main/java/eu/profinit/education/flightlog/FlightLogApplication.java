package eu.profinit.education.flightlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlightLogApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightLogApplication.class, args);
	}

    public Boolean returnFalse(){
        return null;
    }

    public void doSomething() {
    }

    public void greatMethod(int inputNumber) {
        if (inputNumber > 0){
            //do something important
        }
        else if (inputNumber <= 0) {
            //do something important
        }
    }

}