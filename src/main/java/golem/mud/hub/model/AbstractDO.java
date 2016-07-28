package golem.mud.hub.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public abstract class AbstractDO {

	public abstract Integer getRowId();
	public abstract void setRowId(Integer rowId);

	public abstract String getTable();
	public abstract AbstractDO instance(ResultSet result) throws SQLException;

	public abstract Map<String, String> toMap();

    protected void addNotNull(final String name, final String value, final Map<String, String> map) {
		if (value != null && !value.isEmpty()) {
			map.put(name, "\""+value+"\"");
		}
	}
}
