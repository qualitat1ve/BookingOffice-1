package daotest;

import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.*;

import dao.DAOFactory;
import dao.ReservationDAOImpl;
import entity.Reservation;

public class ReservationDAOImplTest extends IntegrationTestBase {

private static ReservationDAOImpl reservationDAOImpl;
	
	@BeforeClass
	public static void getDAO() throws Exception {
		DAOFactory factory = new DAOFactory();
		reservationDAOImpl = factory.getReservationDAOImpl();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		reservationDAOImpl.getEntityManager().close();
	}
	
	@Test
	public void testCreate() {
		Reservation r = new Reservation();
		
		String customerSurname = "Surname4";
		String customerName = "Name4";
		String customerMiddlename = "Middlename4";
		String customerAddress = "Address4";
		
		GregorianCalendar gcReservation = new GregorianCalendar(2013, Calendar.DECEMBER, 10, 4, 37, 0);
		Timestamp dtReservation = new java.sql.Timestamp(gcReservation.getTime().getTime());
		
		GregorianCalendar gcPayment = new GregorianCalendar(2013, Calendar.DECEMBER, 10, 4, 40, 0);
		Timestamp dtPayment = new java.sql.Timestamp(gcPayment.getTime().getTime());
		
		r.setCustomerSurname(customerSurname);
		r.setCustomerName(customerName);
		r.setCustomerMiddlename(customerMiddlename);
		r.setCustomerAddress(customerAddress);
		r.setDateReservation(dtReservation);
		r.setDatePayment(dtPayment);		

		reservationDAOImpl.create(r);
		int id = r.getId();
		Reservation rReaded = reservationDAOImpl.read(id);
		assertTrue(id == rReaded.getId());
		assertTrue(customerSurname.equals(rReaded.getCustomerSurname()));
		assertTrue(customerName.equals(rReaded.getCustomerName()));
		assertTrue(customerMiddlename.equals(rReaded.getCustomerMiddlename()));
		assertTrue(customerAddress.equals(rReaded.getCustomerAddress()));
		
		assertTrue(dtReservation.equals(rReaded.getDateReservation()));
		assertTrue(dtPayment.equals(rReaded.getDatePayment()));
		
		reservationDAOImpl.delete(r);
	}

	@Test
	public void testUpdate() {
		Reservation r = reservationDAOImpl.read(1);
		String customerSurname = "Surname11";
		r.setCustomerSurname(customerSurname);
		
		reservationDAOImpl.update(r);
		
		Reservation rReaded = reservationDAOImpl.read(1);
		
		assertTrue(r.getId() == rReaded.getId());
		assertTrue(customerSurname.equals(rReaded.getCustomerSurname()));
		assertTrue(r.getCustomerName().equals(rReaded.getCustomerName()));
		assertTrue(r.getCustomerMiddlename().equals(rReaded.getCustomerMiddlename()));
		assertTrue(r.getCustomerAddress().equals(rReaded.getCustomerAddress()));
		
		assertTrue(r.getDateReservation().equals(rReaded.getDateReservation()));
		assertTrue(r.getDatePayment().equals(rReaded.getDatePayment()));
		
		customerSurname = "Surname1";
		r.setCustomerSurname(customerSurname);
		reservationDAOImpl.update(r);
	}

	@Test
	public void testDelete() {
		Reservation r = new Reservation();
		
		String customerSurname = "Surname4";
		String customerName = "Name4";
		String customerMiddlename = "Middlename4";
		String customerAddress = "Address4";
		
		GregorianCalendar gcReservation = new GregorianCalendar(2013, Calendar.DECEMBER, 23, 4, 37, 0);
		Timestamp dtReservation = new java.sql.Timestamp(gcReservation.getTime().getTime());
		
		GregorianCalendar gcPayment = new GregorianCalendar(2013, Calendar.DECEMBER, 23, 4, 40, 0);
		Timestamp dtPayment = new java.sql.Timestamp(gcPayment.getTime().getTime());
		
		r.setCustomerSurname(customerSurname);
		r.setCustomerName(customerName);
		r.setCustomerMiddlename(customerMiddlename);
		r.setCustomerAddress(customerAddress);
		r.setDateReservation(dtReservation);
		r.setDatePayment(dtPayment);		

		reservationDAOImpl.create(r);

		int id = r.getId();
		
		Reservation rReaded = reservationDAOImpl.read(id);
		reservationDAOImpl.delete(rReaded);
		
		rReaded = reservationDAOImpl.read(id);
		assertTrue(rReaded == null);
	}

	@Test
	public void testRead() {
		Reservation r = reservationDAOImpl.read(2);
		
		GregorianCalendar gcReservation = new GregorianCalendar(2013, Calendar.DECEMBER, 5, 10, 00, 14);
		Timestamp dtReservation = new java.sql.Timestamp(gcReservation.getTime().getTime());
		
		GregorianCalendar gcPayment = new GregorianCalendar(2013, Calendar.DECEMBER, 6, 10, 00, 14);
		Timestamp dtPayment = new java.sql.Timestamp(gcPayment.getTime().getTime());
		
		assertTrue(r.getId() == 2);
		assertTrue(r.getCustomerSurname().equals("Surname2"));
		assertTrue(r.getCustomerName().equals("Name2"));
		assertTrue(r.getCustomerMiddlename().equals("Middlename2"));
		assertTrue(r.getCustomerAddress().equals("Address2"));
		
		assertTrue(r.getDateReservation().equals(dtReservation));
		assertTrue(r.getDatePayment().equals(dtPayment));
	}

	@Test
	public void testGetExpiredReservations() {
		List<Reservation> listR= reservationDAOImpl.getExpiredReservations();
		GregorianCalendar gcReservation = new GregorianCalendar(2013, Calendar.DECEMBER, 6, 10, 00, 14);
		Timestamp dtReservation = new java.sql.Timestamp(gcReservation.getTime().getTime());
		
		//Timestamp dtPayment = null;
		
		assertTrue(listR.size() == 1);
		assertTrue(listR.get(0).getId() == 3);
		assertTrue(listR.get(0).getCustomerSurname().equals("Surname3"));
		assertTrue(listR.get(0).getCustomerName().equals("Name3"));
		assertTrue(listR.get(0).getCustomerMiddlename().equals("Middlename3"));
		assertTrue(listR.get(0).getCustomerAddress().equals("Address3"));
		assertTrue(listR.get(0).getDateReservation().equals(dtReservation));
		//assertTrue(listR.get(0).getDatePayment().equals(dtPayment));
		
	}

	@Test
	public void testGetActualReservations() {
		Reservation r = new Reservation();
		
		String customerSurname = "Surname6";
		String customerName = "Name6";
		String customerMiddlename = "Middlename6";
		String customerAddress = "Address6";
		
		Timestamp dtReservation = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
		Timestamp dtPayment = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
		
		r.setCustomerSurname(customerSurname);
		r.setCustomerName(customerName);
		r.setCustomerMiddlename(customerMiddlename);
		r.setCustomerAddress(customerAddress);
		r.setDateReservation(dtReservation);
		r.setDatePayment(dtPayment);		

		reservationDAOImpl.create(r);
		
		List<Reservation> listR= reservationDAOImpl.getActualReservations();
		assertTrue(listR.size() == 1);
		assertTrue(listR.get(0).getId() == 6);
		assertTrue(listR.get(0).getCustomerSurname().equals("Surname6"));
		assertTrue(listR.get(0).getCustomerName().equals("Name6"));
		assertTrue(listR.get(0).getCustomerMiddlename().equals("Middlename6"));
		assertTrue(listR.get(0).getCustomerAddress().equals("Address6"));
		assertTrue(listR.get(0).getDateReservation().equals(dtReservation));
		assertTrue(listR.get(0).getDatePayment().equals(dtPayment));
		
	}

}