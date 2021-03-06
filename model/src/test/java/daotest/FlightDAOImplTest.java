package daotest;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import model.Flight;

import org.junit.*;

import dao.*;

public class FlightDAOImplTest extends IntegrationTestBase {
	private static FlightDAOImpl flightDAOImpl;
		
	@BeforeClass
	public static void getDAO() throws Exception {
		DAOFactory factory = new DAOFactory();
		flightDAOImpl = factory.getFlightDAOImpl();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		flightDAOImpl.getEntityManager().close();
	}
	
	@Test
	public void testCreate() {
		Flight f = new Flight();
		
		GregorianCalendar gcCreated = new GregorianCalendar(2013, Calendar.DECEMBER, 22, 9, 44, 0);
		Timestamp dtCreated = new java.sql.Timestamp(gcCreated.getTime().getTime());
		
		String flightNumber = "PF-143";
		
		String departure = "Kyiv";
		
		String arrival = "London";
		
		GregorianCalendar gcDeparture = new GregorianCalendar(2013, Calendar.DECEMBER, 25, 10, 50, 0);
		Timestamp dtDeparture = new java.sql.Timestamp(gcDeparture.getTime().getTime());
		
		GregorianCalendar gcArrival = new GregorianCalendar(2013, Calendar.DECEMBER, 25, 12, 5, 0);
		Timestamp dtArrival = new java.sql.Timestamp(gcArrival.getTime().getTime());
		
		int ticketAmount = 3;
		
		double ticketPrice = 3000;
		f.setDateCreated(dtCreated);
		f.setFlightNumber(flightNumber);
		f.setDeparture(departure);
		f.setArrival(arrival);
		f.setDateDeparture(dtDeparture);
		f.setDateArrival(dtArrival);
		f.setTicketAmount(ticketAmount);
		f.setTicketPrice(ticketPrice);

		flightDAOImpl.create(f);
		int id = f.getId();
		Flight fReaded = flightDAOImpl.read(id);
		assertTrue(id == fReaded.getId());
		assertTrue(dtCreated.equals(fReaded.getDateCreated()));
		assertTrue(flightNumber.equals(fReaded.getFlightNumber()));
		assertTrue(departure.equals(fReaded.getDeparture()));
		assertTrue(arrival.equals(fReaded.getArrival()));
		assertTrue(dtDeparture.equals(fReaded.getDateDeparture()));
		assertTrue(dtArrival.equals(fReaded.getDateArrival()));
		assertTrue(ticketAmount == fReaded.getTicketAmount());
		assertTrue(ticketPrice == fReaded.getTicketPrice());
		
		flightDAOImpl.delete(f);
	}

	@Test
	public void testUpdate() {
		Flight f = flightDAOImpl.read(1);
		int ticketAmount = 20;
		f.setTicketAmount(ticketAmount);
		
		flightDAOImpl.update(f);
		
		Flight fReaded = flightDAOImpl.read(1);
		
		assertTrue(f.getId() == fReaded.getId());
		assertTrue(f.getDateCreated().equals(fReaded.getDateCreated()));
		assertTrue(f.getFlightNumber().equals(fReaded.getFlightNumber()));
		assertTrue(f.getDeparture().equals(fReaded.getDeparture()));
		assertTrue(f.getArrival().equals(fReaded.getArrival()));
		assertTrue(f.getDateDeparture().equals(fReaded.getDateDeparture()));
		assertTrue(f.getDateArrival().equals(fReaded.getDateArrival()));
		assertTrue(ticketAmount == fReaded.getTicketAmount());
		assertTrue(f.getTicketPrice() == fReaded.getTicketPrice());	
		
		ticketAmount = 10;
		f.setTicketAmount(ticketAmount);
		
		flightDAOImpl.update(f);
	}

	@Test
	public void testDelete() {
		Flight f = new Flight();
		
		GregorianCalendar gcCreated = new GregorianCalendar(2013, Calendar.DECEMBER, 22, 9, 44, 0);
		Timestamp dtCreated = new java.sql.Timestamp(gcCreated.getTime().getTime());
		
		String flightNumber = "PF-143";
		
		String departure = "Kyiv";
		
		String arrival = "London";
		
		GregorianCalendar gcDeparture = new GregorianCalendar(2013, Calendar.DECEMBER, 25, 10, 50, 0);
		Timestamp dtDeparture = new java.sql.Timestamp(gcDeparture.getTime().getTime());
		
		GregorianCalendar gcArrival = new GregorianCalendar(2013, Calendar.DECEMBER, 25, 12, 5, 0);
		Timestamp dtArrival = new java.sql.Timestamp(gcArrival.getTime().getTime());
		
		int ticketAmount = 3;
		
		double ticketPrice = 3000;
		f.setDateCreated(dtCreated);
		f.setFlightNumber(flightNumber);
		f.setDeparture(departure);
		f.setArrival(arrival);
		f.setDateDeparture(dtDeparture);
		f.setDateArrival(dtArrival);
		f.setTicketAmount(ticketAmount);
		f.setTicketPrice(ticketPrice);

		flightDAOImpl.create(f);

		int id = f.getId();
		
		Flight fReaded = flightDAOImpl.read(id);
		flightDAOImpl.delete(fReaded);
		
		fReaded = flightDAOImpl.read(id);
		assertTrue(fReaded == null);
	}

	@Test
	public void testRead() {
		
		GregorianCalendar gcCreated = new GregorianCalendar(2013, Calendar.DECEMBER, 01, 10, 20, 0);
		Timestamp dtCreated = new java.sql.Timestamp(gcCreated.getTime().getTime());
		
		GregorianCalendar gcDeparture = new GregorianCalendar(2013, Calendar.DECEMBER, 25, 10, 20, 0);
		Timestamp dtDeparture = new java.sql.Timestamp(gcDeparture.getTime().getTime());
		
		GregorianCalendar gcArrival = new GregorianCalendar(2013, Calendar.DECEMBER, 25, 12, 10, 0);
		Timestamp dtArrival = new java.sql.Timestamp(gcArrival.getTime().getTime());
		
		Flight f = flightDAOImpl.read(2);
		assertTrue(f.getId() == 2);
		assertTrue(dtCreated.equals(f.getDateCreated()));
		assertTrue(f.getFlightNumber().equals("AQ-021"));
		assertTrue(f.getDeparture().equals("Kyiv"));
		assertTrue(f.getArrival().equals("Roma"));
		assertTrue(f.getDateDeparture().equals(dtDeparture));
		assertTrue(f.getDateArrival().equals(dtArrival));
		assertTrue(f.getTicketAmount() == 5);
		assertTrue(f.getTicketPrice() == 1500);
	}

	@Test
	public void testFind() {
		String arrival = "New York";
		GregorianCalendar gcDeparture = new GregorianCalendar(2013, Calendar.DECEMBER, 28);
		java.sql.Date dtDeparture = new java.sql.Date(gcDeparture.getTime().getTime());
		List<Flight> listF = flightDAOImpl.find(arrival, dtDeparture);
		assertTrue(listF.size() == 1);
	}

}
