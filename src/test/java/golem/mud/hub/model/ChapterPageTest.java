package golem.mud.hub.model;

import org.junit.Test;
import org.junit.BeforeClass;
import java.sql.ResultSet;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChapterPageTest {

	private static final ChapterPage pageClass = new ChapterPage();
	private static ResultSet resultSet;

	@BeforeClass
	public static void setup() throws Exception {
		resultSet = mock(ResultSet.class);
		when(resultSet.getInt("id")).thenReturn(1);
		when(resultSet.getInt("chapter_id")).thenReturn(5);
		when(resultSet.getString("title")).thenReturn("TITLE");
		when(resultSet.getString("body")).thenReturn("BODY");
	}

	@Test
	public void testGetTable() throws Exception {
		assertEquals("page", pageClass.getTable());
	}

	@Test
	public void testInstance() throws Exception {
		ChapterPage instance = pageClass.instance(resultSet);

		assertNotNull(instance);
		assertEquals(Integer.valueOf(1), instance.getRowId());
		assertEquals(Integer.valueOf(5), instance.getChapterRowId());
		assertEquals("TITLE", instance.getTitle());
		assertEquals("BODY", instance.getBody());
	}

	@Test
	public void testToMap() throws Exception {
		ChapterPage page = pageClass.instance(resultSet);
		Map<String, String> pageMap = page.toMap();

		assertNotNull(pageMap);
		assertEquals("1", pageMap.get("id"));
		assertEquals("5", pageMap.get("chapter_id"));
		assertEquals("TITLE", pageMap.get("title"));
		assertEquals("BODY", pageMap.get("body"));
	}
}
