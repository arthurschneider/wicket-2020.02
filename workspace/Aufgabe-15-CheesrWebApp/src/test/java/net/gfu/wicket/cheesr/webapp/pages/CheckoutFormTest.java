package net.gfu.wicket.cheesr.webapp.pages;

import net.gfu.wicket.cheesr.webapp.TestWicketApplication;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;

import net.gfu.wicket.cheesr.webapp.WicketApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CheckoutFormTest {
	
	private WicketTester tester;
	private FormTester formTester;

	@BeforeEach
	public void setUp() {
		tester = new WicketTester(new TestWicketApplication());
		tester.startPage(Index.class);
		
		// Fill shopping cart and switch to checkout page
		
		tester.clickLink("cheeses:0:add");
		tester.clickLink("cheeses:1:add");
		tester.clickLink("checkout");
		
		// Fill checkout form
		
	    formTester = tester.newFormTester("form");
	    
	    formTester.setValue("name", "Max Mustermann");
	    formTester.setValue("street", "Musterstr. 123");
	    formTester.setValue("zipcode", "12345");
	}
	
	@Test
	public void testFields(){
		tester.assertRequired("form:name");
		tester.assertRequired("form:street");
		tester.assertRequired("form:zipcode");
		tester.assertRequired("form:city");
	}
	
	@Test
	public void testOrderCancelled(){
		tester.clickLink("form:cancel");
		tester.assertRenderedPage(Index.class);
	}
	
	@Test
	public void testOrderFailure(){
	    formTester.submit();
	    
	    tester.assertErrorMessages("Bitte tragen Sie einen Wert im Feld 'city' ein.");
	}
	
	@Test
	public void testOrderSuccess(){
	    formTester.setValue("city", "Musterstadt");
	    formTester.submit();
	    
	    tester.assertNoErrorMessage();
	}
}