package codepoet.ragnarok.hub.page;

import codepoet.ragnarok.annotation.Page;
import codepoet.ragnarok.hub.PageData;
import codepoet.ragnarok.hub.Pageable;
import codepoet.ragnarok.hub.Route;
import codepoet.ragnarok.model.PlayerDO;
import codepoet.vaultmonkey.service.SqliteDataService;
import codepoet.venalartificer.TemplateBuilder;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Page(name = "home")
public class HomePage implements Pageable {

	private Map<String, Object> templateData;
	private TemplateBuilder templateBuilder;
	private Map<String, Route> routes;
	private SqliteDataService<PlayerDO> playerDataService;

	@Autowired
	public HomePage(TemplateBuilder templateBuilder, SqliteDataService playerDataService) {
		this.templateBuilder = templateBuilder;
		this.playerDataService = playerDataService;
		this.templateData = new HashMap<>();

		this.routes = new HashMap<>();
		this.routes.put("a", new Route.Builder("archives").build());
		this.routes.put("b", new Route.Builder("bookmarks").build());
		this.routes.put("s", new Route.Builder("settings").build());
		this.routes.put("h", new Route.Builder("help").build());
		this.routes.put("e", null);
	}

	@Override
	public PageData render(Map<String, String> params, String password) {
		String username = params.get("username");
		boolean isRegistered = params.get("isRegistered").equalsIgnoreCase("true");
		String updates = null;

		if (!isRegistered) {
			try {
				PlayerDO player = new PlayerDO();
				player.setUsername(username);
				player.setPassword(password);
				playerDataService.create(player);
				updates = "Account Created!";
			} catch (Exception ex) {
				updates = "Failed to Create Account!";
			}
		}

		templateData.put("hasUpdates", updates != null);
		templateData.put("updates", updates);
		String renderText = templateBuilder.render("Home", templateData);
		PageData.Builder pageData = new PageData.Builder(renderText, username);

		for (Map.Entry<String, Route> route : routes.entrySet()) {
			pageData.route(route.getKey(), route.getValue());
		}

		return pageData.build();
	}
}