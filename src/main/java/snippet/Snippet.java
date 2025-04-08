package snippet;

public class Snippet {
	@Test
		public void testAddBcc() throws Exception {
			email.addBcc(TEST_EMAILS);
			
			assertEquals(3, email.getBccAddresses().size());
		}
}

