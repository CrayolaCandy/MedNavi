import org.cef.CefApp;
import org.cef.CefApp.CefAppState;
import org.cef.CefClient;
import org.cef.CefSettings;
import org.cef.browser.CefMessageRouter;
import org.cef.handler.CefAppHandlerAdapter;

import tests.detailed.handler.MessageRouterHandler;
import tests.detailed.handler.MessageRouterHandlerEx;

public class CefAppManager {
    private static CefApp cefApp;
    private static CefClient client;

    public static CefApp getCefAppInstance() {
        if (cefApp == null) {
            CefSettings settings = new CefSettings();
            settings.windowless_rendering_enabled = false;

            // Add an App Handler once
            CefApp.addAppHandler(new CefAppHandlerAdapter(null) {
                @Override
                public void stateHasChanged(CefAppState state) {
                    if (state == CefAppState.TERMINATED) {
                        System.exit(0);
                    }
                }
            });

            cefApp = CefApp.getInstance(settings);
        }
        return cefApp;
    }

    public static CefClient getClientInstance() {
        if (client == null) {
            client = getCefAppInstance().createClient();
            CefMessageRouter msgRouter = CefMessageRouter.create();
            msgRouter.addHandler(new MessageRouterHandler(), true);
            msgRouter.addHandler(new MessageRouterHandlerEx(client), false);
            client.addMessageRouter(msgRouter);
        }
        return client;
    }
}
