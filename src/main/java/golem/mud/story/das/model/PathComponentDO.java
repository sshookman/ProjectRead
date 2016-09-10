package golem.mud.story.das.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;

import golem.mud.common.enums.Direction;

public class PathComponentDO extends AbstractComponentDO {

    private Integer destEntityId;
    private String name;
    private Direction direction;
    private String displayMessage;
    private String transitionMessage;

    public Integer getDestEntityId() {
        return destEntityId;
    }

    public void setDestEntityId(Integer destEntityId) {
        this.destEntityId = destEntityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }

    public String getTransitionMessage() {
        return transitionMessage;
    }

    public void setTransitionMessage(String transitionMessage) {
        this.transitionMessage = transitionMessage;
    }

	@Override
	public String getTable() {
		return "c_path";
	}

	@Override
	public PathComponentDO instance(ResultSet result) throws SQLException {
		PathComponentDO instance = new PathComponentDO();
		instance.setRowId(result.getInt("id"));
		instance.setEntityId(result.getInt("entity_id"));
		instance.setDestEntityId(result.getInt("dest_entity_id"));
		instance.setName(result.getString("name"));
		instance.setDirection(Direction.valueOf(result.getString("direction")));
		instance.setDisplayMessage(result.getString("display_message"));
		instance.setTransitionMessage(result.getString("transition_message"));
		return instance;
	}	

	@Override
	public Map<String, String> toMap() {
		Map<String, String> dataMap = new HashMap<String, String>();
		addNotNull("entity_id", entityId, dataMap);
		addNotNull("dest_entity_id", destEntityId, dataMap);
		addNotNull("name", name, dataMap);
		addNotNull("direction", direction, dataMap);
		addNotNull("display_message", displayMessage, dataMap);
		addNotNull("transition_message", transitionMessage, dataMap);
		return dataMap;
	}
}
