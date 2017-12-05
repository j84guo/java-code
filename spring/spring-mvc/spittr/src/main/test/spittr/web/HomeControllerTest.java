package spittr.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import spittr.web.HomeController;

public class HomeControllerTest {
	@Test
	public void testHomePage() throws Exception {

		HomeController controller = new HomeController();
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(get("/")).andExpect(view().name("home"));
	}
}

@Test
	public void shouldShowRecentSpittles() throws Exception {
		
		List<Spittle> expectedSpittles = createSpittleList(20);
		
		SpittleRepository mockRepository = mock(SpittleRepository.class);
		when(mockRepository.findSpittles(Long.MAX_VALUE, 20)).thenReturn(expectedSpittles);
		
		SpittleController controller = new SpittleController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller).setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp")).build();
		
		mockMvc.perform(get("/spittles"))
			.andExpect(view().name("spittles"))
			.andExpect(model().attributeExists("spittleList"))
			.andExpect(model().attribute("spittleList", hasItems(expectedSpittles.toArray())));
	}

	private List<Spittle> createSpittleList(int count) {

		List<Spittle> spittles = new ArrayList<Spittle>();
		for (int i=0; i < count; i++) {
			spittles.add(new Spittle("Spittle " + i, new Date()));
		}

		return spittles;
	}