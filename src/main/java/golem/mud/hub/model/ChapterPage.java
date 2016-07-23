package golem.mud.hub.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;

public class ChapterPage extends AbstractDataObject {

	private Integer rowId;
	private Integer chapterRowId;
	private String title;
	private String body;

	@Override
	public Integer getRowId() {
		return rowId;
	}
	
	@Override
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public Integer getChapterRowId() {
		return chapterRowId;
	}

	public void setChapterRowId(Integer chapterRowId) {
		this.chapterRowId = chapterRowId;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String getTable() {
		return "page";
	}

	@Override
	public ChapterPage instance(ResultSet result) throws SQLException {
		ChapterPage instance = new ChapterPage();
		instance.setRowId(result.getInt("id"));
		instance.setChapterRowId(result.getInt("chapter_id"));
		instance.setTitle(result.getString("title"));
		instance.setBody(result.getString("body"));
		return instance;
	}

	@Override
	public Map<String, String> toMap() {
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("id", getRowId().toString());
		dataMap.put("chapter_id", getChapterRowId().toString());
		dataMap.put("title", getTitle());
		dataMap.put("body", getBody());
		return dataMap;
	}
}