package net.gfu.wicket.backend;

import net.gfu.wicket.backend.bo.Cart;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BOServiceTest {

	private BOServices services = new BOServices();
	
	@Test
	public void testAllCheesesOk() {
		assertEquals(12, services.allCheeses().size());
	}

	@Test
	public void testOrderPlace(){
		services.proccessOrder(new Cart());
		assertTrue(true); // Kein Fehler, alles ok
	}
}