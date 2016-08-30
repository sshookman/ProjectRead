package golem.mud.story.das.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;

public class DisplayComponentDO extends AbstractComponentDO {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	@Override
	public String getTable() {
		return "c_display";
	}

	@Override
	public DisplayComponentDO instance(ResultSet result) throws SQLException {
		DisplayComponentDO instance = new DisplayComponentDO();
		instance.setRowId(result.getInt("id"));
		instance.setEntityId(result.getInt("entity_id"));
		instance.setMessage(result.getString("message"));
		return instance;
	}	

	@Override
	public Map<String, String> toMap() {
		Map<String, String> dataMap = new HashMap<String, String>();
		addNotNull("entity_id", entityId, dataMap);
		addNotNull("message", message, dataMap);
		return dataMap;
	}
}
