package codepoet.ragnarok.server;

import codepoet.ragnarok.exception.HubException;
import codepoet.ragnarok.hub.PageData;
import codepoet.ragnarok.hub.PageRouter;
import codepoet.ragnarok.hub.Route;
import codepoet.ragnarok.model.PlayerDO;
import codepoet.ragnarok.render.Renderer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client implements Runnable {

	private final static Logger LOGGER = Logger.getLogger(Client.class.getName());

	private final Session session;
	private final PageRouter pageRouter;

	public Client(Session session, PageRouter pageRouter) {
		this.session = session;
		this.pageRouter = pageRouter;
	}

	@Override
	public void run() {
		Renderer renderer = session.getRenderer();

		try {
			PlayerDO player = login(renderer);

			Route route = new Route.Builder("home").build();
			String input = null;

			do {
				route.setInput(input);
				PageData page = pageRouter.route(route);

				renderer.write(page.getDisplay());
				input = renderer.prompt(player.getUsername() + " | " + page.getPrompt());

				route = (page.getRoutes().containsKey("*")) ? page.getRoutes().get("*") : page.getRoutes().get(input.toLowerCase());
			} while (route != null);

		} catch (Exception exception) {
			renderer.writeln("An error has occurred: " + exception.getMessage(), Renderer.RED);
			LOGGER.log(Level.SEVERE, exception.getMessage());
		} finally {
			LOGGER.log(Level.INFO, "Closing Session {0}", session.getId());
			session.close();
		}
	}

	private PlayerDO login(Renderer renderer) throws Exception {
		PlayerDO player = new PlayerDO();
		player.setUsername(renderer.prompt("Enter your username"));
		player.setPassword(renderer.prompt("Enter your password"));
		int status = pageRouter.login(player.getUsername(), player.getPassword());

		if (status == 0) {
			String create = renderer.prompt("No account found for " + player.getUsername() + " would you like to create one? (y/n)");
			if (create.equalsIgnoreCase("y")) {
				register(player.getUsername(), player.getPassword(), renderer);
			} else {
				return login(renderer);
			}
		} else if (status == -1) {
			renderer.writeln("Invalid Password!", Renderer.RED);
			return login(renderer);
		} else {
			renderer.writeln("Login Successful!", Renderer.GREEN);
		}

		return player;
	}

	private void register(String username, String password, Renderer renderer) throws Exception, HubException {
		boolean isRegistered = pageRouter.register(username, password);

		if (isRegistered) {
			renderer.writeln("Account Created!", Renderer.GREEN);
		} else {
			throw new HubException("Failed to Create Account");
		}
	}
}
