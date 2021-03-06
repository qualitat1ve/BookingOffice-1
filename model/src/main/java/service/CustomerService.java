package service;

import java.sql.Date;
import java.util.List;

import model.*;
import dao.*;


public class CustomerService {
	private TicketDAO ticketDao;
	private ReservationDAO reservationDao;
	private FlightDAO flightDao;
	
	public CustomerService(TicketDAO ticketDao, ReservationDAO reservationDao,
			FlightDAO flightDao) {
		super();
		this.ticketDao = ticketDao;
		this.reservationDao = reservationDao;
		this.flightDao = flightDao;
	}

	void bookTickets(List<Ticket> listT, Reservation r) {
		reservationDao.create(r);
		for (Ticket t : listT) {
			t.setReservationId(r.getId());
			t.setStatus(TicketStatus.BOOKED);
			ticketDao.update(t);
		}
	}
	
	List<Flight> findNecesseryFlights(String arrival, Date departureDate) {
		return flightDao.find(arrival, departureDate);
	}
	
}
