package com.example.reservationservice;

import java.util.stream.Stream;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.stereotype.Component;

// we are going to build a simple API which we can work then we ll see patterns and complexities that naturally become a problem once u ve started to scale out and
// have lots of collobrating services distrubuted over the network

@SpringBootApplication
public class ReservationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationServiceApplication.class, args);
	}
}

@Component
class SampleDataCLR implements CommandLineRunner {

	private final ReservationRepository reservationRepository;

	@Autowired
	public SampleDataCLR(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Stream.of("Josh", "Ahmet", "Mehmet", "Hasan", "Huseyin", "Ayse", "Ayla")
				.forEach(name -> reservationRepository.save(new Reservation(name)));
		reservationRepository.findAll().forEach(System.out::println);
	}
}


@RepositoryRestController
interface ReservationRepository  extends JpaRepository<Reservation, Long>{
	
}


/**
 * @author acolak
 *
 */
@Entity // JPA entity
class Reservation {
	

	@Id @GeneratedValue
	private long id;
	
	private String reservationName; // reservation_name in the DB
	
	@Override
	public String toString() {
		return "Reservation [id=" + id + ", reservationName=" + reservationName + "]";
	}
	
	public Reservation() {
		// TODO Auto-generated constructor stub
	}

	public Reservation(String reservationName) {
		super();
		this.reservationName = reservationName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

}