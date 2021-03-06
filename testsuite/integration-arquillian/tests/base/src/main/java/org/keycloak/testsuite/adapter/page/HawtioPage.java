package org.keycloak.testsuite.adapter.page;

import org.keycloak.testsuite.page.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.ws.rs.core.UriBuilder;
import org.keycloak.testsuite.util.JavascriptBrowser;

import static org.keycloak.testsuite.util.WaitUtils.waitUntilElement;

/**
 * @author mhajas
 */
public class HawtioPage extends AbstractPage {

    public String getUrl() {
        if (Boolean.parseBoolean(System.getProperty("app.server.ssl.required"))) {
            return "https://localhost:" + System.getProperty("app.server.https.port", "8543") + "/hawtio";
        }
        return "http://localhost:" + System.getProperty("app.server.http.port", "8180") + "/hawtio";
    }

    @Override
    public UriBuilder createUriBuilder() {
        return UriBuilder.fromUri(getUrl());
    }

    @FindBy(xpath = "//a[@class='dropdown-toggle' and @data-original-title='Preferences and log out']")
    @JavascriptBrowser
    private WebElement dropDownMenu;

    @FindBy(xpath = "//a[@ng-click='logout()']")
    @JavascriptBrowser
    private WebElement logoutButton;

    @FindBy(xpath = "//input[@type='submit' and @value='Yes']")
    @JavascriptBrowser
    private WebElement modal;

    public void logout() {
        log.debug("logging out");
        waitUntilElement(dropDownMenu).is().clickable();
        dropDownMenu.click();
        waitUntilElement(logoutButton).is().clickable();
        logoutButton.click();
        waitUntilElement(modal).is().clickable();
        modal.click();
    }
}
